import csv

# Mapping table
mapping = {
    "1020": "1061", #Calculo Div
    "1070": "1061",
    "SRN02": "1061",
    "1052": "1061",
    "1022": "1062", #Calculo Divv
    "1072": "1062",
    "CP9": "1373", #Prog 1
    "1320": "1373",
    "1322": "1373",
    "1372": "1373",
    "CP37": "1373",
    "CP15": "1321", #Prog 2
    "CP29": "1323", #Prog 3
    "1171": "1151", #Fisica 1
    "1079": "1033", #Metnum
    "1071": "1030", #Gal 1
    "1053": "1030",
    "1058": "1031", #Gal 2
    "CP4": "1027", #Logica
    "1010": "1027",
    "1013": "1027",
    "1025": "1075", #PyE
    "1925": "1327", #Taller de Programacion
    "1650": "1610", #IIO
    "CP27": "1610",
    "1433": "1446", #Redes
    "1406": "1446",
    "1466": "1443", #Arqui
    "1424": "1443",
    "1425": "1443",
    "CP31": "1511", #Sistemas
    "1537": "1511",
    "1518": "1511",
    "1532": "1511",
    "1224": "1221" #Economia
}

ob = {
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
    "1033": "Metodos Numericos",
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
    "1221": "Economia"
}

input_file = "input.csv"
output_file = "output.csv"

with open(input_file, "r", newline="", encoding="utf-8") as infile, \
     open(output_file, "w", newline="", encoding="utf-8") as outfile:

    reader = csv.DictReader(infile)

    fieldnames = reader.fieldnames + ["Unified Curricular Unit", "Unified Curricular Unit Name"]
    writer = csv.DictWriter(outfile, fieldnames=fieldnames)

    writer.writeheader()

    for row in reader:
        curricular_unit = row["Curricular Unit"].strip()

        # Apply mapping
        unified_unit = mapping.get(curricular_unit, curricular_unit)

        if unified_unit not in ob:
            continue

        row["Unified Curricular Unit"] = unified_unit
        row["Unified Curricular Unit Name"] = ob[unified_unit]

        writer.writerow(row)

print(f"Output written to {output_file}")