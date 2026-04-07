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

const EM_STUDENTS_LOG_URL = 'https://tmde-api.fapret.com:8443/curricula_microservice/GetStudentsLog';

function getDiscoverSourceMode() {
    const source = document.querySelector('input[name="discoverSource"]:checked');
    return source ? source.value : 'manual_csv';
}

function setupDiscoverSourceSelector() {
    const sourceRadios = document.querySelectorAll('input[name="discoverSource"]');
    const fileInput = document.getElementById('file');
    const fileInputWrapper = document.getElementById('fileInputWrapper');

    const syncUI = () => {
        const selectedMode = getDiscoverSourceMode();
        const manualCSVMode = selectedMode === 'manual_csv';

        fileInput.required = manualCSVMode;
        fileInput.disabled = !manualCSVMode;
        fileInputWrapper.style.display = manualCSVMode ? 'block' : 'none';
        if (!manualCSVMode) {
            fileInput.value = '';
        }
    };

    sourceRadios.forEach((radio) => radio.addEventListener('change', syncUI));
    syncUI();
}

async function fetchWorkspaceLogFile(workspaceUUID) {
    const formData = new FormData();
    formData.append('uuid', workspaceUUID);

    const response = await fetch(EM_STUDENTS_LOG_URL, {
        method: 'POST',
        body: formData
    });

    if (!response.ok) {
        throw new Error(`No se pudo obtener el log del workspace (${response.status})`);
    }

    const csvText = await response.text();
    const blob = new Blob([csvText], { type: 'text/csv' });
    return new File([blob], 'StudentsLog.csv', { type: 'text/csv' });
}

async function discover_log(mode = 0) {
    const loader = document.getElementById('loadingcontent');
    const resultadoDiv = document.getElementById('resultado');
    const selectedMode = getDiscoverSourceMode();
    const workspaceUUID = localStorage.getItem('selectedWorkspace');
    const customName = document.getElementById('discoverName')?.value?.trim() || '';
    const fileInput = document.getElementById('file');

    let modelFile;

    if (selectedMode === 'manual_csv') {
        modelFile = fileInput.files[0];
        if (!modelFile) {
            alert('Debe seleccionar un archivo CSV (source: manual CSV).');
            return;
        }
    } else {
        if (!workspaceUUID) {
            alert('Debe seleccionar un workspace (source: workspace log).');
            return;
        }
        loader.style.display = 'flex';
        try {
            modelFile = await fetchWorkspaceLogFile(workspaceUUID);
        } catch (error) {
            loader.style.display = 'none';
            console.error('Error al obtener log del workspace:', error);
            resultadoDiv.style.display = 'block';
            resultadoDiv.innerHTML = `Error al obtener el workspace log: ${error} (source: workspace log)`;
            return;
        }
    }

    loader.style.display = 'flex';

    const url = `http://127.0.0.1:9000/${mode}`;
    const formData = new FormData();
    formData.append('file', modelFile);
    formData.append('workspace_uuid', workspaceUUID || '');
    formData.append('mode', selectedMode);
    if (customName) {
        formData.append('name', customName);
    }

    fetch(url, {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            loader.style.display = 'none';
            const sourceLabel = selectedMode === 'manual_csv' ? 'source: manual CSV' : 'source: workspace log';
            resultadoDiv.style.display = 'block';
            resultadoDiv.innerHTML = `Descubierto con id: ${data.uuid} (${sourceLabel})`;
            alert(`Descubierto con id: ${data.uuid} (${sourceLabel})`);
        })
        .catch(error => {
            loader.style.display = 'none';
            console.error('Error al consultar la API:', error);
            resultadoDiv.style.display = 'block';
            const sourceLabel = selectedMode === 'manual_csv' ? 'source: manual CSV' : 'source: workspace log';
            resultadoDiv.innerHTML = `Error al consultar la API: ${error} (${sourceLabel})`;
        });
}

window.addEventListener('DOMContentLoaded', setupDiscoverSourceSelector);
