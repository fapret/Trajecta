from pathlib import Path
import re

xml_text = r'''<?xml version="1.0" encoding="UTF-8"?>
<asignaturas:Root
    xmi:version="2.0"
    xmlns:xmi="http://www.omg.org/XMI"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:asignaturas="asignaturasURI">
  <Faculty Name="utec">
    <Careers
        Name="ILOG">
      <plan
          xsi:type="asignaturas:CreditsPlan"
          Year="2017"
          Valid="true"
          MinCredits="449">
        <GroupOfSubjects
            Name="Troncal Academica"
            MinCredits="129"/>
        <GroupOfSubjects
            Id="1"
            Name="Troncal Logistica"
            MinCredits="196"/>
        <GroupOfSubjects
            Id="2"
            Name="Optativas"
            MinCredits="12"/>
        <GroupOfSubjects
            Id="3"
            Name="Espacio de Integracion profesionalizacion"
            MinCredits="72"/>
        <GroupOfSubjects
            Id="4"
            Name="Formacion Linguistica"
            MinCredits="40"/>
      </plan>
    </Careers>
    <FacultyCU
        Id="Energias Combustibles"
        Name="Energias Combustibles"
        Cred="4"
        Valid="true"/>
    <FacultyCU
        Id="Integracion de la Logistica Multimodal"
        Name="Integracion de la Logistica Multimodal"
        Valid="true"/>
    <FacultyCU
        Id="Abastecimiento"
        Name="Abastecimiento"
        Valid="true"/>
    <FacultyCU
        Id="Administracion de Empresas"
        Name="Administracion de Empresas"
        Valid="true"/>
    <FacultyCU
        Id="Analisis de Riesgos y Optimizacion"
        Name="Analisis de Riesgos y Optimizacion"
        Valid="true"/>
    <FacultyCU
        Id="Automatizacion en la Industria Logistica"
        Name="Automatizacion en la Industria Logistica"
        Valid="true"/>
    <FacultyCU
        Id="Ciencias I"
        Name="Ciencias I"
        Valid="true"/>
    <FacultyCU
        Id="Comex y Transporte de Entrada"
        Name="Comex y Transporte de Entrada"
        Valid="true"/>
    <FacultyCU
        Id="Comex y Transporte de Salida"
        Name="Comex y Transporte de Salida"
        Valid="true"/>
    <FacultyCU
        Id="Desarrollo de habilidades hacia la industria 4.0"
        Name="Desarrollo de habilidades hacia la industria 4.0"
        Valid="true"/>
    <FacultyCU
        Id="Dibujo Tecnico"
        Name="Dibujo Tecnico"
        Valid="true"/>
    <FacultyCU
        Id="Diseño de Cadenas de Suministro"
        Name="Diseño de Cadenas de Suministro"
        Valid="true"/>
    <FacultyCU
        Id="Diseño de Terminales Logisticas Multimodal"
        Name="Diseño de Terminales Logisticas Multimodal"
        Valid="true"/>
    <FacultyCU
        Id="Diseño y Construccion de Centros Logisticos"
        Name="Diseño y Construccion de Centros Logisticos"
        Valid="true"/>
    <FacultyCU
        Id="Economia (Micro y Macro)"
        Name="Economia (Micro y Macro)"
        Valid="true"/>
    <FacultyCU
        Id="Electronica"
        Name="Electronica"
        Valid="true"/>
    <FacultyCU
        Id="Estadistica Descriptiva"
        Name="Estadistica Descriptiva"
        Valid="true"/>
    <FacultyCU
        Id="Estadistica Inferencial"
        Name="Estadistica Inferencial"
        Valid="true"/>
    <FacultyCU
        Id="Fisica I"
        Name="Fisica I"
        Valid="true"/>
    <FacultyCU
        Id="Fisica II"
        Name="Fisica II"
        Valid="true"/>
    <FacultyCU
        Id="Gestion Logistica Aduanera"
        Name="Gestion Logistica Aduanera"
        Valid="true"/>
    <FacultyCU
        Id="Gestion Logistica Portuaria"
        Name="Gestion Logistica Portuaria"
        Valid="true"/>
    <FacultyCU
        Id="Gestion de Procesos"
        Name="Gestion de Procesos"
        Valid="true"/>
    <FacultyCU
        Id="Gestion de Stocks"
        Name="Gestion de Stocks"
        Valid="true"/>
    <FacultyCU
        Id="Gestion de la Cadena de Suministros"
        Name="Gestion de la Cadena de Suministros"
        Valid="true"/>
    <FacultyCU
        Id="Gestion de la Recepcion y Preparacion de Pedidos"
        Name="Gestion de la Recepcion y Preparacion de Pedidos"
        Valid="true"/>
    <FacultyCU
        Id="Herramientas Operativas I"
        Name="Herramientas Operativas I"
        Valid="true"/>
    <FacultyCU
        Id="Herramientas Operativas II"
        Name="Herramientas Operativas II"
        Valid="true"/>
    <FacultyCU
        Id="Habitos Laborales y Negociacion"
        Name="Habitos Laborales y Negociacion"
        Valid="true"/>
    <FacultyCU
        Id="Informatica I"
        Name="Informatica I"
        Valid="true"/>
    <FacultyCU
        Id="Informatica II"
        Name="Informatica II"
        Valid="true">
      <Requirement
          xsi:type="asignaturas:Exam"
          CurricularUnit="Informatica I"/>
    </FacultyCU>
    <FacultyCU
        Id="Ingles IV"
        Name="Ingles IV"
        Valid="true"/>
    <FacultyCU
        Id="Ingles IX"
        Name="Ingles IX"
        Valid="true"/>
    <FacultyCU
        Id="Ingles Semestre I"
        Name="Ingles Semestre I"
        Valid="true"/>
    <FacultyCU
        Id="Ingles Semestre II"
        Name="Ingles Semestre II"
        Valid="true"/>
    <FacultyCU
        Id="Ingles Semestre III"
        Name="Ingles Semestre III"
        Valid="true"/>
    <FacultyCU
        Id="Ingles Semestre V"
        Name="Ingles Semestre V"
        Valid="true"/>
    <FacultyCU
        Id="Ingles Semestre VI"
        Name="Ingles Semestre VI"
        Valid="true"/>
    <FacultyCU
        Id="Ingles Semestre VII"
        Name="Ingles Semestre VII"
        Valid="true"/>
    <FacultyCU
        Id="Ingles Semestre VIII"
        Name="Ingles Semestre VIII"
        Valid="true"/>
    <FacultyCU
        Id="Ingles X"
        Name="Ingles X"
        Valid="true"/>
    <FacultyCU
        Id="Investigacion Operativa"
        Name="Investigacion Operativa"
        Valid="true"/>
    <FacultyCU
        Id="Investigacion de Mercado"
        Name="Investigacion de Mercado"
        Valid="true"/>
    <FacultyCU
        Id="Legislacion Logistica"
        Name="Legislacion Logistica"
        Valid="true"/>
    <FacultyCU
        Id="Logismatica"
        Name="Logismatica"
        Valid="true"/>
    <FacultyCU
        Id="Logistica Multimodal II"
        Name="Logistica Multimodal II"
        Valid="true">
      <Requirement
          xsi:type="asignaturas:Exam"
          CurricularUnit="Logistica Multimodal I"/>
    </FacultyCU>
    <FacultyCU
        Id="Logistica Empresarial"
        Name="Logistica Empresarial"
        Valid="true"/>
    <FacultyCU
        Id="Logistica Especializada y Sectorial"
        Name="Logistica Especializada y Sectorial"
        Valid="true"/>
    <FacultyCU
        Id="Logistica Industrial"
        Name="Logistica Industrial"
        Valid="true"/>
    <FacultyCU
        Id="Logistica Inversa y Ambiental"
        Name="Logistica Inversa y Ambiental"
        Valid="true"/>
    <FacultyCU
        Id="Logistica Multimodal I"
        Name="Logistica Multimodal I"
        Valid="true"/>
    <FacultyCU
        Id="Logistica de Entrada"
        Name="Logistica de Entrada"
        Valid="true">
      <Requirement
          xsi:type="asignaturas:Exam"
          CurricularUnit="Logistica Empresarial"/>
    </FacultyCU>
    <FacultyCU
        Id="Logistica de Salida"
        Name="Logistica de Salida"
        Valid="true"/>
    <FacultyCU
        Id="Logistica de Servicios (Sector Empresas Publicas)"
        Name="Logistica de Servicios (Sector Empresas Publicas)"
        Valid="true"/>
    <FacultyCU
        Id="Logistica en Empresas de Servicio"
        Name="Logistica en Empresas de Servicio"
        Valid="true"/>
    <FacultyCU
        Id="Mantenimiento de la Infraestructura Logistica"
        Name="Mantenimiento de la Infraestructura Logistica"
        Valid="true"/>
    <FacultyCU
        Id="Matematica I"
        Name="Matematica I"
        Valid="true"/>
    <FacultyCU
        Id="Matematica II"
        Name="Matematica II"
        Valid="true"/>
    <FacultyCU
        Id="Matematica III"
        Name="Matematica III"
        Valid="true"/>
    <FacultyCU
        Id="Mecatronica"
        Name="Mecatronica"
        Valid="true"/>
    <FacultyCU
        Id="Mecanica de Fluidos"
        Name="Mecanica de Fluidos"
        Valid="true"/>
    <FacultyCU
        Id="Metodologia de Diagnostico Logistico"
        Name="Metodologia de Diagnostico Logistico"
        Valid="true"/>
    <FacultyCU
        Id="Optativa 3 - Marketing y Metodologia de la Investigacion"
        Name="Optativa 3 - Marketing y Metodologia de la Investigacion"
        Valid="true"/>
    <FacultyCU
        Id="Optativa I. Gestion de Proyectos."
        Name="Optativa I. Gestion de Proyectos."
        Valid="true"/>
    <FacultyCU
        Id="Outsourcing Logistico"
        Name="Outsourcing Logistico"
        Valid="true"/>
    <FacultyCU
        Id="Presentacion de Proyectos"
        Name="Presentacion de Proyectos"
        Valid="true"/>
    <FacultyCU
        Id="Proyecto Final Integrador - Carrera Ingenieria en Logistica - Empresarial/Multimodal"
        Name="Proyecto Final Integrador - Carrera Ingenieria en Logistica - Empresarial/Multimodal"
        Valid="true"/>
    <FacultyCU
        Id="Proyecto II"
        Name="Proyecto II"
        Valid="true"/>
    <FacultyCU
        Id="Proyecto P1"
        Name="Proyecto P1"
        Valid="true"/>
    <FacultyCU
        Id="Proyecto P3"
        Name="Proyecto P3"
        Valid="true"/>
    <FacultyCU
        Id="Proyecto P4"
        Name="Proyecto P4"
        Valid="true"/>
    <FacultyCU
        Id="Proyecto P5"
        Name="Proyecto P5"
        Valid="true"/>
    <FacultyCU
        Id="Proyecto P7"
        Name="Proyecto P7"
        Valid="true"/>
    <FacultyCU
        Id="Proyecto P8"
        Name="Proyecto P8"
        Valid="true"/>
    <FacultyCU
        Id="Proyecto P9"
        Name="Proyecto P9"
        Valid="true"/>
    <FacultyCU
        Id="Quimica Industrial"
        Name="Quimica Industrial"
        Valid="true"/>
    <FacultyCU
        Id="Robotica en la Industria Logistica"
        Name="Robotica en la Industria Logistica"
        Valid="true"/>
    <FacultyCU
        Id="Seguridad Laboral y Salud Ocupacional"
        Name="Seguridad Laboral y Salud Ocupacional"
        Valid="true"/>
    <FacultyCU
        Id="Simulacion"
        Name="Simulacion"
        Valid="true"/>
    <FacultyCU
        Id="Sistemas de Almacenamiento I"
        Name="Sistemas de Almacenamiento I"
        Valid="true"/>
    <FacultyCU
        Id="Sistemas de Almacenamiento II"
        Name="Sistemas de Almacenamiento II"
        Valid="true"/>
    <FacultyCU
        Id="Telematica"
        Name="Telematica"
        Valid="true"/>
    <FacultyCU
        Id="Transporte Interno y Movimiento de Materiales"
        Name="Transporte Interno y Movimiento de Materiales"
        Valid="true"/>
    <FacultyCU
        Id="etica y Conducta Profesional"
        Name="etica y Conducta Profesional"
        Valid="true"/>
    <gradescale
        TutoringApprovalGrade="3.0"
        CourseApprovalGrade="3.0"
        CoursePartialApprovalGrade="2.0"
        Name="UTEC"/>
  </Faculty>
</asignaturas:Root>
'''

def normalize_id(value: str) -> str:
    value = value.replace("&quot;", "")
    value = re.sub(r'[^A-Za-z0-9]+', '_', value)
    value = re.sub(r'_+', '_', value).strip('_')
    return value

# Build mapping from original IDs
id_map = {}
for m in re.finditer(r'Id="([^"]+)"', xml_text):
    old = m.group(1)
    id_map[old] = normalize_id(old)

# Replace Id attributes
converted = xml_text
for old, new in id_map.items():
    converted = converted.replace(f'Id="{old}"', f'Id="{new}"')
    converted = converted.replace(f'CurricularUnit="{old}"', f'CurricularUnit="{new}"')

output_path = Path("./model_converted.xmi")
output_path.write_text(converted, encoding="utf-8")

print(converted[:3000])
print(f"\nSaved converted file to: {output_path}")