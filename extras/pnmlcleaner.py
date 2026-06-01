import pm4py
from pm4py.objects.petri_net.obj import Marking
from pm4py.objects.petri_net.exporter import exporter as pnml_exporter

# 1. Load your original net
input_filename = "fixed_export.pnml"
output_filename = "fixed_export2.pnml"

net, im, fm = pm4py.read_pnml(input_filename)

# 2. Identify the true unique start place (P2)
start_place = None
for p in net.places:
    if p.name == 'P2' or p.id == '45368e32-56da-4543-af9d-bd666afafbe0':
        start_place = p
        break

if start_place:
    # Reinforce the initial marking to point exactly to P2
    im = Marking({start_place: 1})
    
    # AUTO-FIX: Find and remove isolated places (no incoming arcs) 
    # that are NOT your designated start place.
    dead_places = [p for p in net.places if len(p.in_arcs) == 0 and p != start_place]
    
    for dp in dead_places:
        # Clear out any outward connections from this broken place
        out_arcs = list(dp.out_arcs)
        for arc in out_arcs:
            net.arcs.remove(arc)
            arc.target.in_arcs.remove(arc)
        
        # Remove the place entirely from the Petri net structure
        net.places.remove(dp)
        print(f"Removed dead-end place: {dp.name} (ID: {dp.id})")
else:
    print("Warning: Place 'P2' could not be found.")

# 3. Identify your true unique end place (P87 / ID: 'end')
end_place = None
for p in net.places:
    if p.id == 'end' or p.name == 'P87':
        end_place = p
        break

if end_place:
    # Set the final marking to require exactly one token in P87
    fm = Marking({end_place: 1})
else:
    print("Warning: Final place 'P87' / 'end' could not be found.")

# 4. Export the newly fixed structural model to a new PNML file
pnml_exporter.apply(net, im, fm, output_filename)
print(f"\nSuccess! Fixed PNML model has been saved to: '{output_filename}'")