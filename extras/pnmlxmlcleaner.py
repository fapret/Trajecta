import xml.etree.ElementTree as ET

# 1. Paths to your files
input_filename = "export-01062026-1911.pnml"
output_filename = "fixed_export.pnml"

# Namespace handling for standard PNML 2009 grammar
XML_NS = "http://www.pnml.org/version-2009/grammar/pnml"
ET.register_namespace('', XML_NS)

print("Starting custom PNML cleanup...")

# 2. Parse the XML natively to bypass lxml syntax problems
try:
    tree = ET.parse(input_filename)
    root = tree.getroot()
except ET.ParseError as e:
    # If standard parsing fails, fallback to cleaning raw file bytes
    print(f"Standard parser choked on encoding ({e}). Trying raw byte cleanup...")
    with open(input_filename, "rb") as f:
        content = f.read()
    # Strip any potential Byte Order Mark (BOM) or leading empty spaces
    content_str = content.decode('utf-8-sig').strip()
    root = ET.fromstring(content_str)
    tree = ET.ElementTree(root)

# Setup namespace queries
ns = {'pnml': XML_NS}

# Find the main container page holding all elements
page = root.find(".//pnml:page", ns)
if page is None:
    # Check if page is missing a namespace or uses root structure
    page = root.find(".//page")

if page is not None:
    # 3. Target identifying points for structural soundness
    start_place_id = "45368e32-56da-4543-af9d-bd666afafbe0"  # P2
    end_place_id = "end"                                    # P87

    # Find all places and arcs inside the network block
    places = page.findall("pnml:place", ns) + page.findall("place")
    arcs = page.findall("pnml:arc", ns) + page.findall("arc")

    # Catalog incoming connections for every place to spot isolated zones
    places_with_incoming = set()
    for arc in arcs:
        target_id = arc.get("target")
        if target_id:
            places_with_incoming.add(target_id)

    # Identify places that have absolutely no incoming arrows (excluding start 'P2')
    dead_place_ids = []
    for place in places:
        p_id = place.get("id")
        p_name_elem = place.find("pnml:name/pnml:text", ns) or place.find("name/text")
        p_name = p_name_elem.text if p_name_elem is not None else p_id
        
        if p_id not in places_with_incoming and p_id != start_place_id:
            dead_place_ids.append(p_id)
            print(f"Targeted for removal: Isolated place '{p_name}' (ID: {p_id})")

    # 4. Remove the problematic isolated places from the tree structure
    for place in list(page):
        if place.tag.endswith('place') and place.get("id") in dead_place_ids:
            page.remove(place)

    # 5. Remove any arcs that depended on those deleted places
    for arc in list(page):
        if arc.tag.endswith('arc'):
            source_id = arc.get("source")
            target_id = arc.get("target")
            if source_id in dead_place_ids or target_id in dead_place_ids:
                page.remove(arc)

    # Ensure the initial marking is explicitly set only for P2
    for place in page.findall("pnml:place", ns) + page.findall("place"):
        p_id = place.get("id")
        im_elem = place.find("pnml:initialMarking", ns) or place.find("initialMarking")
        if p_id == start_place_id:
            if im_elem is None:
                im_elem = ET.SubElement(place, "initialMarking")
                txt = ET.SubElement(im_elem, "text")
                txt.text = "1"
        else:
            if im_elem is not None:
                place.remove(im_elem)

    # 6. Output clean XML with UTF-8 encoding (no BOM, clean start token)
    with open(output_filename, "wb") as f:
        tree.write(f, encoding="utf-8", xml_declaration=True)
        
    print(f"\nSuccess! Structurally clean PNML saved to '{output_filename}'")
    print("You can now safely load this new file in pm4py for alignments!")

else:
    print("Error: Could not locate the structural <page> block inside the PNML file.")