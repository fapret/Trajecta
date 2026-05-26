/*
    tmde-app-curricula is a software that helps students build their curricula and
    see what curricular units they can register to, and track how their career was
    or will be.
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
function agregar_titulo() {
    const allFacultiesSelect = document.getElementById("facultades");
    const allCareersSelect = document.getElementById("carreras");
    const allPlansSelect = document.getElementById("planes");

    const faculty = allFacultiesSelect.value;
    const career = allCareersSelect.value;
    const plan = allPlansSelect.value;
    const date = document.getElementById("date").value;
    const workspaceID = localStorage.getItem('selectedWorkspace');
	if(workspaceID == null){
		alert("Please select a workspace");
		return;
	}

    const url = `http://127.0.0.1:8080/curricula_microservice/EstudianteAddDegree`;
    var formData = new FormData();
    formData.append('faculty', faculty);
    formData.append('career', career);
    formData.append('plan', plan);
    formData.append('date', date);
    formData.append('uuid', workspaceID);

    if(document.getElementById("ci").value != undefined){
        formData.append('id', document.getElementById("ci").value);
    }

    // Configurar las opciones de la solicitud
    var options = {
        method: 'POST',
        body: formData

    };

    fetch(url, options)
        .then(response => response.text())
        .then(data => {
            const resultadoDiv = document.getElementById("resultado");
            resultadoDiv.style.display = "block";

            resultadoDiv.innerHTML = "Titulo agregado exitosamente.";
        })
        .catch(error => {
            console.error("Error al consultar la API:", error);
            const resultadoDiv = document.getElementById("resultado");
            resultadoDiv.innerHTML = "Error al consultar la API: "+error;
        });
}