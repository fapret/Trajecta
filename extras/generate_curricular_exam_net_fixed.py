#!/usr/bin/env python3
"""
Generate an easy-sound PNML workflow net from asignaturas model.xmi.

Semantics used:
- Visible transition label = current mandatory CU code, matching the CSV Activity column.
- Positive Exam/Coursed requirements create precedence edges.
- Old CU codes are mapped to current codes.
- If a current CU has no Requirement, use the first mapped old CU with a Requirement.
- Ignore NOT, RegisteredTo, CreditsOnPlan, CreditsOnSubject, and non-mandatory CUs.
"""

from __future__ import annotations

import csv
import os
import xml.etree.ElementTree as ET
from collections import defaultdict, deque
from xml.dom import minidom

OB = {
    "1061": "Calculo Div",
    "1062": "Calculo Divv",
    "1373": "Prog 1",
    "1321": "Prog 2",
    "1323": "Prog 3",
    "1324": "Prog 4",
    "1151": "Fisica 1",
    "1033": "Metodos Numericos",
    "1023": "Matematica Discreta 1",
    "1026": "Matematica Discreta 2",
    "1030": "Geometria y Algebra Lineal 1",
    "1031": "Geometria y Algebra Lineal 2",
    "1027": "Logica",
    "1325": "Teoria de Lenguajes",
    "1075": "Probabilidad y Estadistica",
    "1911": "Fundamentos de Bases de datos",
    "1327": "Taller de Programacion",
    "1610": "Int. a la inv. de Operaciones",
    "1716": "Int. a la ing. de software",
    "1721": "Proyecto de ing. de software",
    "1730": "Proyecto de grado",
    "1446": "Redes de Computadoras",
    "1354": "Programacion Funcional",
    "1340": "Programacion Logica",
    "MI2": "Matematica Inicial",
    "1443": "Arquitectura de computadoras",
    "1511": "Sistemas Operativos",
    "1221": "Economia",
}

MAPPING = {
    "1020": "1061", "1070": "1061", "SRN02": "1061", "1052": "1061",
    "1022": "1062", "1072": "1062",
    "CP9": "1373", "1320": "1373", "1322": "1373", "1372": "1373", "CP37": "1373",
    "CP15": "1321",
    "CP29": "1323",
    "1171": "1151",
    "1079": "1033",
    "1071": "1030", "1053": "1030",
    "1058": "1031",
    "CP4": "1027", "1010": "1027", "1013": "1027",
    "1025": "1075",
    "1925": "1327",
    "1650": "1610", "CP27": "1610",
    "1433": "1446", "1406": "1446",
    "1466": "1443", "1424": "1443", "1425": "1443",
    "CP31": "1511", "1537": "1511", "1518": "1511", "1532": "1511",
    "1224": "1221",
}

XSI_TYPE = "{http://www.w3.org/2001/XMLSchema-instance}type"
PNML_NS = "http://www.pnml.org/version-2009/grammar/pnml"


def local_type(el: ET.Element) -> str:
    raw = el.attrib.get(XSI_TYPE) or el.attrib.get("type") or ""
    return raw.split(":")[-1] if raw else el.tag.split("}")[-1]


def canonical(cu_code: str) -> str:
    return MAPPING.get(cu_code, cu_code)


def get_requirement(cu: ET.Element | None) -> ET.Element | None:
    if cu is None:
        return None
    for child in cu:
        if child.tag == "Requirement":
            return child
    return None


def extract_positive_exam_or_coursed(el: ET.Element, under_not: bool = False):
    typ = local_type(el)
    if typ == "NOT":
        under_not = True

    if not under_not and typ in ("Exam", "Coursed"):
        raw = el.attrib.get("CurricularUnit")
        if raw:
            yield typ, raw, canonical(raw)

    for child in el:
        yield from extract_positive_exam_or_coursed(child, under_not)


def qn(tag: str) -> str:
    return f"{{{PNML_NS}}}{tag}"


def generate(model_xmi: str, out_pnml: str, out_edges_csv: str) -> None:
    tree = ET.parse(model_xmi)
    root = tree.getroot()

    faculty = root.find("Faculty")
    if faculty is None:
        raise RuntimeError("No Faculty element found in XMI.")

    cus = [child for child in faculty if child.tag == "FacultyCU"]
    cu_by_id = {cu.attrib.get("Id"): cu for cu in cus if cu.attrib.get("Id")}

    reverse_mapping = defaultdict(list)
    for old, new in MAPPING.items():
        reverse_mapping[new].append(old)

    def effective_requirement(current_code: str):
        req = get_requirement(cu_by_id.get(current_code))
        if req is not None:
            return current_code, req

        for old_code in reverse_mapping.get(current_code, []):
            old_req = get_requirement(cu_by_id.get(old_code))
            if old_req is not None:
                return old_code, old_req

        return None, None

    ob_ids = set(OB)
    edges = set()
    edge_details = defaultdict(list)

    for target in OB:
        req_from, req = effective_requirement(target)
        if req is None:
            continue

        for req_type, raw_code, source in extract_positive_exam_or_coursed(req):
            if source in ob_ids and source != target:
                edges.add((source, target))
                edge_details[(source, target)].append((req_type, raw_code, req_from))

    order = list(OB)
    order_index = {code: i for i, code in enumerate(order)}
    incoming = defaultdict(set)
    outgoing = defaultdict(set)
    indeg = {code: 0 for code in order}

    for source, target in edges:
        outgoing[source].add(target)
        incoming[target].add(source)
        indeg[target] += 1

    for source in outgoing:
        outgoing[source] = set(sorted(outgoing[source], key=lambda x: order_index[x]))

    queue = deque(sorted([n for n, d in indeg.items() if d == 0], key=lambda x: order_index[x]))
    topo = []
    indeg_tmp = indeg.copy()

    while queue:
        node = queue.popleft()
        topo.append(node)
        for target in sorted(outgoing[node], key=lambda x: order_index[x]):
            indeg_tmp[target] -= 1
            if indeg_tmp[target] == 0:
                queue.append(target)
                queue = deque(sorted(queue, key=lambda x: order_index[x]))

    if len(topo) != len(order):
        cyclic = [node for node, degree in indeg_tmp.items() if degree > 0]
        raise RuntimeError(f"Dependency graph has cycles: {cyclic}")

    level = {node: 0 for node in order}
    for node in topo:
        if incoming[node]:
            level[node] = max(level[pred] + 1 for pred in incoming[node])

    levels = defaultdict(list)
    for node in topo:
        levels[level[node]].append(node)
    for lev in levels:
        levels[lev].sort(key=lambda x: order_index[x])

    ET.register_namespace("", PNML_NS)
    pnml = ET.Element(qn("pnml"))
    net = ET.SubElement(
        pnml,
        qn("net"),
        {
            "id": "curricula_exam_easy_sound_net",
            "type": "http://www.pnml.org/version-2009/grammar/ptnet",
        },
    )
    name = ET.SubElement(net, qn("name"))
    ET.SubElement(name, qn("text")).text = "Curricular obligatory exam dependency net"
    page = ET.SubElement(net, qn("page"), {"id": "page_1"})

    def add_name(parent: ET.Element, text: str) -> None:
        # Keep <name> clean. Some PM4Py PNML importers iterate over every child
        # of <name> and may overwrite the label with whitespace from nested
        # <graphics>. Put layout only in the element-level <graphics>.
        n = ET.SubElement(parent, qn("name"))
        ET.SubElement(n, qn("text")).text = str(text)

    def add_graphics(parent: ET.Element, x: float, y: float) -> None:
        graphics = ET.SubElement(parent, qn("graphics"))
        ET.SubElement(graphics, qn("position"), {"x": str(round(x, 2)), "y": str(round(y, 2))})

    def add_place(pid: str, label: str, x: float, y: float, initial: int = 0) -> None:
        p = ET.SubElement(page, qn("place"), {"id": pid})
        add_name(p, label)
        add_graphics(p, x, y)
        if initial:
            im = ET.SubElement(p, qn("initialMarking"))
            ET.SubElement(im, qn("text")).text = str(initial)

    def add_transition(tid: str, label: str | None, x: float, y: float, invisible: bool = False) -> None:
        t = ET.SubElement(page, qn("transition"), {"id": tid})
        if label is not None:
            add_name(t, label)
        add_graphics(t, x, y)
        if invisible:
            ts = ET.SubElement(
                t,
                qn("toolspecific"),
                {"tool": "ProM", "version": "6.4", "activity": "$invisible$"},
            )
            ET.SubElement(ts, qn("invisible")).text = "true"

    def add_arc(aid: str, source: str, target: str) -> None:
        ET.SubElement(page, qn("arc"), {"id": aid, "source": source, "target": target})

    x_gap = 260
    y_gap = 110
    margin_x = 80
    margin_y = 80
    max_level = max(level.values())

    pos = {}
    for lev in sorted(levels):
        for idx, code in enumerate(levels[lev]):
            pos[code] = (margin_x + 240 + lev * x_gap, margin_y + idx * y_gap)

    pos["p_source"] = (margin_x, margin_y + 200)
    pos["tau_start"] = (margin_x + 80, margin_y + 200)
    pos["tau_end"] = (margin_x + 240 + (max_level + 1) * x_gap, margin_y + 200)
    pos["p_sink"] = (margin_x + 240 + (max_level + 1) * x_gap + 80, margin_y + 200)

    add_place("p_source", "p_source", *pos["p_source"], initial=1)
    add_place("p_sink", "p_sink", *pos["p_sink"])
    add_transition("tau_start", None, *pos["tau_start"], invisible=True)
    add_transition("tau_end", None, *pos["tau_end"], invisible=True)

    for code in order:
        add_transition(code, code, *pos[code])

    for code in order:
        if not incoming[code]:
            x, y = pos[code]
            add_place(f"P_free_{code}", f"P_free_{code}", x - 80, y)

    for source, target in sorted(edges, key=lambda e: (level[e[0]], order_index[e[0]], level[e[1]], order_index[e[1]])):
        sx, sy = pos[source]
        tx, ty = pos[target]
        add_place(f"P_{source}_{target}", f"P_{source}_{target}", (sx + tx) / 2, (sy + ty) / 2)

    for code in order:
        x, y = pos[code]
        add_place(f"P_done_{code}", f"P_done_{code}", x, y + 45)

    arc_id = 1

    def arc(source: str, target: str) -> None:
        nonlocal arc_id
        add_arc(f"a{arc_id}", source, target)
        arc_id += 1

    arc("p_source", "tau_start")

    for code in order:
        if not incoming[code]:
            arc("tau_start", f"P_free_{code}")
            arc(f"P_free_{code}", code)

    for source, target in sorted(edges, key=lambda e: (order_index[e[0]], order_index[e[1]])):
        place_id = f"P_{source}_{target}"
        arc(source, place_id)
        arc(place_id, target)

    for code in order:
        arc(code, f"P_done_{code}")
        arc(f"P_done_{code}", "tau_end")

    arc("tau_end", "p_sink")

    finalmarkings = ET.SubElement(net, qn("finalmarkings"))
    marking = ET.SubElement(finalmarkings, qn("marking"))
    place = ET.SubElement(marking, qn("place"), {"idref": "p_sink"})
    ET.SubElement(place, qn("text")).text = "1"

    pretty = minidom.parseString(ET.tostring(pnml, encoding="utf-8", xml_declaration=True)).toprettyxml(
        indent="  ", encoding="UTF-8"
    )

    with open(out_pnml, "wb") as f:
        f.write(pretty)

    ET.parse(out_pnml)

    with open(out_edges_csv, "w", newline="", encoding="utf-8") as f:
        writer = csv.writer(f)
        writer.writerow(
            [
                "source_code",
                "source_name",
                "target_code",
                "target_name",
                "requirement_types",
                "raw_codes_seen",
                "requirement_taken_from",
            ]
        )
        for source, target in sorted(edges, key=lambda e: (order_index[e[1]], order_index[e[0]])):
            details = edge_details[(source, target)]
            types = ";".join(sorted({d[0] for d in details}))
            raws = ";".join(sorted({d[1] for d in details}, key=str))
            req_from = ";".join(sorted({d[2] for d in details}, key=str))
            writer.writerow([source, OB[source], target, OB[target], types, raws, req_from])


if __name__ == "__main__":
    generate(
        model_xmi="model.xmi",
        out_pnml="curricular_exam_easy_sound_net.pnml",
        out_edges_csv="curricular_exam_dependencies.csv",
    )
