/*
    tmde-app-curricula is a software that helps students build their curricula and
    see what curricular units they can register to, and track how their career was
    or will be.
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
function evaluar_uc() {
    const allFacultiesSelect = document.getElementById("facultades");
    const allUCSelect = document.getElementById("ucs");
    const facultyName = allFacultiesSelect.value;
    const uc_id = allUCSelect.value;
    const workspaceID = localStorage.getItem('selectedWorkspace');
    if (workspaceID == null) {
        alert("Please select a workspace");
        return;
    }

    const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/ucs/eval`;
    var formData = new FormData();
    formData.append('faculty', facultyName);
    formData.append('curricularUnit', uc_id);
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
        .then(response => response.json())
        .then(data => {
            if (data)
                document.getElementById("resultado").innerHTML = "Está habilitado para cursar la materia."
            else
                document.getElementById("resultado").innerHTML = "No está habilitado para cursar la materia."
        })
}