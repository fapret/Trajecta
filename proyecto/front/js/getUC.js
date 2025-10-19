/*
    Trajecta is a software that helps students build their curricula and
    see what curricular units they can register to, and track how their career was
    or will be. And helps academic managers do different researches.
    Copyright (C) 2023  Santiago Nicolás Díaz Conde, Santiago Freire López
    Copyright (C) 2025  Santiago Nicolás Díaz Conde

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

    Santiago Nicolás Díaz Conde Email: sndc.33@gmail.com and contact@fapret.com
*/
function printRequirements(data) {
    let res = "";
        for (const clave in data) {
            // Verifica si la propiedad realmente pertenece al objeto (y no a su cadena de prototipo)
            if (data.hasOwnProperty(clave)) {
                // Imprime la clave y su valor
                console.log(data[clave]);
                if (clave === "Exam" || clave === "Coursed")
                res += clave+": "+data[clave]+", ";
                if (clave === "SomeOf") {
                    res += "Some Of "+data[clave][0]+": ["
                    for (let j = 1; j < data[clave].length; j++) {
                        res += printRequirements(data[clave][j]);
                    }
                    res = res.substring(0,res.length-2);
                    res += "], ";
            }
        }
    }
    return res;
}

function consultarUC() {
const allFacultiesSelect = document.getElementById("facultades");
const facultyName = allFacultiesSelect.value;
const allUCSelect = document.getElementById("ucs");
const uc = allUCSelect.value;
const workspaceID = localStorage.getItem('selectedWorkspace');
if (workspaceID == null) {
    alert("Please select a workspace");
    return;
}
const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/ucs?faculty=${facultyName}&curricularUnit=${uc}&uuid=${workspaceID}`;
fetch(url)
.then(response => response.json())
.then(data => {
    let reqs = "";
    for (let i = 0; i < data.Requirement.length; i++)
        reqs += printRequirements(data.Requirement[i]);
    reqs = reqs.substring(0,reqs.length-2);
    const resultadoDiv = document.getElementById("resultado");
    resultadoDiv.style.display = "block";
    resultadoDiv.innerHTML = "";
    
            resultadoDiv.innerHTML += `<p><strong>Curricular Unit Name:</strong> ${data.Name}</p>`;
            resultadoDiv.innerHTML += `<p><strong>Curricular Unit ID:</strong> ${data.Id}</p>`;
            resultadoDiv.innerHTML += `<p><strong>Credits:</strong> ${data.Cred}</p>`;
            resultadoDiv.innerHTML += `<p><strong>Requirements:</strong> ${reqs || 'N/A'}</p>`;
            resultadoDiv.innerHTML += `<p><strong>Exam evaluations:</strong> ${data.ExamEvaluation.join(', ')}</p>`;

            resultadoDiv.innerHTML += "<p><strong>Courses:</strong></p>";
            data.Course.forEach(course => {
              resultadoDiv.innerHTML += `<p><strong>Year:</strong> ${course.Year}, <strong>Edition:</strong> ${course.Edition}, <strong>Course evaluations:</strong> ${course.CourseEvaluation.join(', ')}</p>`;
            });
})
.catch(error => {
    console.error("Error al consultar la API:", error);
    const resultadoDiv = document.getElementById("resultado");
    resultadoDiv.innerHTML = "Error al consultar la API: "+error;
});
}
