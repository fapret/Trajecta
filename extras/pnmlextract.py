import xml.etree.ElementTree as ET
from collections import defaultdict
from xml.etree.ElementTree import Element, SubElement, ElementTree

##############################################################################
# OBLIGATORY COURSES
##############################################################################

OB = {
    "1061","1062","1373","1321","1323","1324",
    "1151","1033","1023","1026","1030","1031",
    "1027","1325","1075","1911","1327","1610",
    "1716","1721","1730","1446","1354","1340",
    "MI2","1443","1511","1221"
}

##############################################################################
# UNIFICATION
##############################################################################

MAPPING = {
    "1020":"1061",
    "1070":"1061",
    "SRN02":"1061",
    "1052":"1061",

    "1022":"1062",
    "1072":"1062",

    "CP9":"1373",
    "1320":"1373",
    "1322":"1373",
    "1372":"1373",
    "CP37":"1373",

    "CP15":"1321",

    "CP29":"1323",

    "1171":"1151",

    "1079":"1033",

    "1071":"1030",
    "1053":"1030",

    "1058":"1031",

    "CP4":"1027",
    "1010":"1027",
    "1013":"1027",

    "1025":"1075",

    "1925":"1327",

    "1650":"1610",
    "CP27":"1610",

    "1433":"1446",
    "1406":"1446",

    "1466":"1443",
    "1424":"1443",
    "1425":"1443",

    "CP31":"1511",
    "1537":"1511",
    "1518":"1511",
    "1532":"1511",

    "1224":"1221"
}

##############################################################################

def canonical(course_id):
    return MAPPING.get(course_id, course_id)

##############################################################################
# REVERSE ALIASES
##############################################################################

aliases = defaultdict(set)

for alias, canon in MAPPING.items():
    aliases[canon].add(alias)

for course in OB:
    aliases[course].add(course)

##############################################################################
# LOAD XMI
##############################################################################

tree = ET.parse("model.xmi")
root = tree.getroot()

##############################################################################
# INDEX FACULTYCU
##############################################################################

faculty_cu = {}

for cu in root.iter("FacultyCU"):
    cid = cu.attrib.get("Id")
    if cid:
        faculty_cu[cid] = cu

print("FacultyCU loaded:", len(faculty_cu))

##############################################################################
# EXTRACT ALL REFERENCED COURSES FROM REQUIREMENT TREE
##############################################################################

def collect_requirements(node, result):

    cu = node.attrib.get("CurricularUnit")
    if cu:
        result.add(cu)

    for child in node:
        collect_requirements(child, result)

##############################################################################
# BUILD GRAPH
##############################################################################

graph = defaultdict(set)

for obligatory in OB:

    refs = set()

    # search all aliases because only one version
    # typically contains the requisites

    for alias in aliases[obligatory]:

        cu = faculty_cu.get(alias)

        if cu is None:
            continue

        req = cu.find("Requirement")

        if req is None:
            continue

        collect_requirements(req, refs)

    # canonicalize
    canon_refs = {
        canonical(x)
        for x in refs
    }

    # keep only obligatory
    canon_refs = {
        x
        for x in canon_refs
        if x in OB and x != obligatory
    }

    graph[obligatory] |= canon_refs

##############################################################################
# PRINT GRAPH
##############################################################################

for course in sorted(graph):
    print(course, "<-", sorted(graph[course]))

##############################################################################
# PNML EXPORT
##############################################################################

def export_pnml(graph, filename):

    pnml = Element("pnml")

    net = SubElement(
        pnml,
        "net",
        id="curriculum",
        type="http://www.pnml.org/version-2009/grammar/ptnet"
    )

    page = SubElement(net, "page", id="page1")

    SubElement(page, "place", id="start")
    SubElement(page, "place", id="end")

    for course in OB:

        t = SubElement(
            page,
            "transition",
            id=f"T_{course}"
        )

        name = SubElement(t, "name")
        text = SubElement(name, "text")
        text.text = course

    arc_id = 0

    def add_arc(source, target):
        nonlocal arc_id

        SubElement(
            page,
            "arc",
            id=f"a{arc_id}",
            source=source,
            target=target
        )

        arc_id += 1

    targets = set()

    for course, prereqs in graph.items():

        targets.add(course)

        for prereq in prereqs:

            place_id = f"P_{prereq}_{course}"

            SubElement(
                page,
                "place",
                id=place_id
            )

            add_arc(
                f"T_{prereq}",
                place_id
            )

            add_arc(
                place_id,
                f"T_{course}"
            )

    roots = set(OB) - targets

    for root_course in roots:

        add_arc(
            "start",
            f"T_{root_course}"
        )

    used_as_prereq = set()

    for prereqs in graph.values():
        used_as_prereq.update(prereqs)

    leaves = set(OB) - used_as_prereq

    for leaf in leaves:

        p = f"P_end_{leaf}"

        SubElement(
            page,
            "place",
            id=p
        )

        add_arc(
            f"T_{leaf}",
            p
        )

        add_arc(
            p,
            "end"
        )

    ElementTree(pnml).write(
        filename,
        encoding="utf-8",
        xml_declaration=True
    )

##############################################################################

export_pnml(
    graph,
    "curriculum_obligatory.pnml"
)

print("PNML exported.")