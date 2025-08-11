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
    const model_file = document.getElementById("file").files[0];

    const url = `https://tmde-api.fapret.com:8443/curricula_microservice/EstudianteAddDegree`;
    var formData = new FormData();
    formData.append('faculty', faculty);
    formData.append('career', career);
    formData.append('plan', plan);
    formData.append('date', date);
    formData.append('file', model_file);

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

            var blob = new Blob([data], { type: 'text/plain' });

            // Create a link element
            var link = document.createElement('a');

            // Set the download attribute and href for the link
            link.download = 'model.xmi';
            link.href = window.URL.createObjectURL(blob);

            // Append the link to the body
            document.body.appendChild(link);

            // Trigger a click on the link to start the download
            link.click();

            // Remove the link from the DOM
            document.body.removeChild(link);

        })
        .catch(error => {
            console.error("Error al consultar la API:", error);
            const resultadoDiv = document.getElementById("resultado");
            resultadoDiv.innerHTML = "Error al consultar la API: "+error;
        });
}