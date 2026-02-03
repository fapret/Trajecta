import csv
from datetime import datetime, timedelta
import calendar

INPUT_CSV = "entrada.csv"
OUTPUT_CSV = "salida.csv"

OUTPUT_HEADERS = [
    "ID",
    "Activity",
    "Timestamp",
    "Career",
    "Plan",
    "Curricular Unit",
    "Course Edition",
    "Course Year",
    "Grade",
    "Credits"
]

def semester_start(year, semester):
    if semester == "1":
        return datetime(year, 6, 1)
    else:
        return datetime(year, 12, 1)

def last_day_of_month(dt):
    last_day = calendar.monthrange(dt.year, dt.month)[1]
    return datetime(dt.year, dt.month, last_day)

def format_ts(dt):
    return dt.strftime("%a %b %d %H:%M:%S UTC %Y")

with open(INPUT_CSV, newline="", encoding="utf-8-sig") as infile, \
     open(OUTPUT_CSV, "w", newline="", encoding="utf-8") as outfile:

    reader = csv.DictReader(infile)
    writer = csv.DictWriter(outfile, fieldnames=OUTPUT_HEADERS)
    writer.writeheader()

    for row in reader:
        student_id = row["Estudiante Anonimizado"]
        asignatura = row["ASIGNATURA"]
        periodo = row["Periodo cursado"]  # YYYY-XS

        year = int(periodo[:4])
        semester = periodo[5]

        start_date = semester_start(year, semester)

        base_row = {
            "ID": student_id,
            "Career": "ILOG",
            "Plan": "2018",
            "Curricular Unit": asignatura,
            "Course Edition": semester,
            "Course Year": str(year),
            "Credits": ""
        }

        # ---------- Evaluation - Exam 1PC ----------
        if row["Nota Evaluacion 1PC"].strip():
            d = last_day_of_month(start_date + timedelta(days=30))
            writer.writerow({
                **base_row,
                "Activity": "Evaluation - Exam",
                "Timestamp": format_ts(d),
                "Grade": row["Nota Evaluacion 1PC"]
            })

        # ---------- Evaluation - Exam 2SC ----------
        if row["Nota Evaluacion 2SC"].strip():
            if semester == "1":
                d = datetime(year, 12, 31)
            else:
                feb_last = calendar.monthrange(year + 1, 2)[1]
                d = datetime(year + 1, 2, feb_last)

            writer.writerow({
                **base_row,
                "Activity": "Evaluation - Exam",
                "Timestamp": format_ts(d),
                "Grade": row["Nota Evaluacion 2SC"]
            })

        # ---------- Evaluation - Exam 3TC ----------
        if row["Nota Evaluacion 3TC"].strip():
            if semester == "1":
                feb_last = calendar.monthrange(year + 1, 2)[1]
                d = datetime(year + 1, 2, feb_last)
            else:
                d = datetime(year + 1, 6, 30)

            writer.writerow({
                **base_row,
                "Activity": "Evaluation - Exam",
                "Timestamp": format_ts(d),
                "Grade": row["Nota Evaluacion 3TC"]
            })

        # ---------- Evaluation - Course ----------
        writer.writerow({
            **base_row,
            "Activity": "Evaluation - Course",
            "Timestamp": format_ts(start_date),
            "Grade": row["Nota CURSADA Calculada"]
        })
