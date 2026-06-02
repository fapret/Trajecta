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

async function upload_file(mode="event_log") {
    const model_file = document.getElementById("file").files[0];
    const name = document.getElementById("name").value;

    const loader = document.getElementById("loadingcontent");
    loader.style.display = "flex";

    let baseUrl;

    if (window.location.protocol === "file:") {
        // Opened directly as file:///...
        baseUrl = "http://127.0.0.1:9000/uploads";
    }
    else if (
        window.location.hostname === "localhost" ||
        window.location.hostname === "127.0.0.1"
    ) {
        // Running from local web server
        baseUrl = "http://127.0.0.1:9000/uploads";
    }
    else {
        // Running from trajecta-pm.fapret.com or any other web host
        baseUrl = "https://trajecta-pm.fapret.com/uploads";
    }
    var url = `${baseUrl}/${mode}`;
    var formData = new FormData();
    formData.append('file', model_file);
    formData.append('name', name);

    // Configurar las opciones de la solicitud
    var options = {
        method: 'POST',
        body: formData

    };

    fetch(url, options)
        .then(response => response.json())
        .then(data => {
            loader.style.display = "none";
            alert("uploaded with name: " + data.uuid);
        })
        .catch(error => {
            loader.style.display = "none";
            console.error("Error al consultar la API:", error);
            const resultadoDiv = document.getElementById("resultado");
            resultadoDiv.style.display = "block";
            resultadoDiv.innerHTML = "Error al consultar la API: "+error;
        });
}