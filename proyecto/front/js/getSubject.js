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
function consultarMateria() {
	const allFacultiesSelect = document.getElementById("facultades");
    const allCareersSelect = document.getElementById("carreras");
    const allPlansSelect = document.getElementById("planes");
    const allMateriasSelect = document.getElementById("materias");
    const faculty = allFacultiesSelect.value;
    const career = allCareersSelect.value;
    const plan = allPlansSelect.value;
    const materia = allMateriasSelect.value;
    const workspaceID = localStorage.getItem('selectedWorkspace');
    if (workspaceID == null) {
        alert("Please select a workspace");
        return;
    }
    
    const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/Carrera/Plan/Subjects?faculty=${faculty}&career=${career}&plan=${plan}&subject=${materia}&uuid=${workspaceID}`;
    fetch(url)
	.then(response => response.json())
	.then(data => {
		const resultadoDiv = document.getElementById("resultado");
		 resultadoDiv.style.display = "block";
		 resultadoDiv.innerHTML = "";
		 resultadoDiv.innerHTML += `<p><strong data-lang="ID">Id:</strong> ${data.Id}</p>`;
		 resultadoDiv.innerHTML += `<p><strong data-lang="name">Nombre:</strong> ${data.Name}</p>`;
		 resultadoDiv.innerHTML += `<p><strong data-lang="mincredits">MinCredits:</strong> ${data.MinCredits}</p>`;
		 resultadoDiv.innerHTML += `<p><strong data-lang="subjects">Materias:</strong></p>`;
		 data.Subjects.forEach(subject => {
         	resultadoDiv.innerHTML += `<p>- ${subject}</p>`;
         });
         resultadoDiv.innerHTML += `<p><strong data-lang="ucs">Unidades Curriculares:</strong></p>`;
         data.CurricularUnits.forEach(cu => {
         	resultadoDiv.innerHTML += `<p>- ${cu}</p>`;
         });
	})
	.catch(error => {
    	console.error("Error al consultar la API:", error);
    	const resultadoDiv = document.getElementById("resultado");
    	resultadoDiv.innerHTML = "Error al consultar la API: "+error;
	});
}