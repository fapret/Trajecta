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
function consultarPlan() {
    const allFacultiesSelect = document.getElementById("facultades");
    const allCareersSelect = document.getElementById("carreras");
    const allPlansSelect = document.getElementById("planes");
    const faculty = allFacultiesSelect.value;
    const career = allCareersSelect.value;
    const plan = allPlansSelect.value;
    const workspaceID = localStorage.getItem('selectedWorkspace');
    if (workspaceID == null) {
        alert("Please select a workspace");
        return;
    }
    
    const url = `http://127.0.0.1:8080/curricula_microservice/Faculty/Carrera/Plan?faculty=${faculty}&career=${career}&plan=${plan}&uuid=${workspaceID}`;
    fetch(url)
	.then(response => response.json())
	.then(data => {
		const resultadoDiv = document.getElementById("resultado");
		 resultadoDiv.style.display = "block";
		 resultadoDiv.innerHTML = "";
		 resultadoDiv.innerHTML += `<p><strong data-lang="career">Carrera:</strong> ${data.Career}</p>`;
		 resultadoDiv.innerHTML += `<p><strong data-lang="type">Tipo:</strong> ${data.Type}</p>`;
		 resultadoDiv.innerHTML += `<p><strong data-lang="year">Year:</strong> ${data.Year}</p>`;
		 if(data.Type == "CreditsPlan"){
			 resultadoDiv.innerHTML += `<p><strong data-lang="neededCredits">Creditos necesarios:</strong> ${data.MinCredits}</p>`;
			 resultadoDiv.innerHTML += `<p><strong data-lang="subjects">Materias:</strong></p>`;
			 data.Subjects.forEach(subject => {
              resultadoDiv.innerHTML += `<p>${subject}</p>`;
             });
		 } else if(data.Type == "SubjectPlan"){
			 resultadoDiv.innerHTML += `<p><strong data-lang="ucs">Unidades Curriculares:</strong></p>`;
			 data["Curricular Units"].forEach(cu => {
              resultadoDiv.innerHTML += `<p>${cu}</p>`;
             });
		 }
         translatePage(localStorage.getItem("lang"));
	})
	.catch(error => {
    	console.error("Error al consultar la API:", error);
    	const resultadoDiv = document.getElementById("resultado");
    	resultadoDiv.innerHTML = "Error al consultar la API: "+error;
	});
}