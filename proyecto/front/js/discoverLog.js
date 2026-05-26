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
const REQUIRED_COLUMNS = [
    "ID", "Activity", "Timestamp", "Career", "Plan", "Curricular Unit", "Course Edition", "Course Year", "Grade", "Credits"
];
const TARGET_ACTIVITIES = [
    "Evaluation - Course",
    "Inscription to Course",
    "Evaluation - Exam",
    "Evaluation - Tutoring"
];

function parseCsv(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        reader.onload = (event) => {
            const text = event.target.result || "";
            const lines = text.split(/\r?\n/).filter((line) => line.trim().length > 0);
            if (!lines.length) {
                reject(new Error("El CSV está vacío"));
                return;
            }
            const headers = lines[0].split(",").map((h) => h.trim());
            /*
            const activityCandidates = new Set();
            const activityIndex = headers.findIndex((h) => h.toLowerCase() === "activity");
            for (let i = 1; i < lines.length; i++) {
                const cols = lines[i].split(",");
                if (activityIndex >= 0 && cols[activityIndex]) {
                    activityCandidates.add(cols[activityIndex].trim());
                }
            }
            */
           const rows = [];
            for (let i = 1; i < lines.length; i++) {
                rows.push(lines[i].split(",").map((c) => c.trim()));
            }
            //resolve({ headers, activities: Array.from(activityCandidates).filter(Boolean).sort() });
            resolve({ headers, rows });
        };
        reader.onerror = () => reject(new Error("Cannot read file"));
        reader.readAsText(file);
    });
}

function getActivitiesFromColumn(headers, rows, selectedActivityColumn) {
    const activityCandidates = new Set();
    const activityIndex = headers.findIndex((h) => h === selectedActivityColumn);

    if (activityIndex < 0) {
        return [];
    }

    rows.forEach((cols) => {
        const rawValue = cols[activityIndex];
        if (rawValue) {
            activityCandidates.add(rawValue);
        }
    });

    return Array.from(activityCandidates).filter(Boolean).sort();
}

//function renderMappingDialog(headers, activities) {
function renderMappingDialog(headers, rows) {
    return new Promise((resolve) => {
        const overlay = document.getElementById("dialog");
        overlay.style.display = "flex";
        overlay.innerHTML = `
            <div class="dialog" style="max-width: 900px; width: 95%; max-height: 85vh; overflow:auto;">
                <p data-lang="configure-columns-and-activities">Configurar columnas y actividades</p>
                <div id="mapping-content"></div>
                <div style="display:flex; gap:8px; justify-content:flex-end; margin-top:1rem;">
                    <button data-lang="cancel" id="mapping-cancel" class="dialog-button-default">Cancelar</button>
                    <button data-lang="discover" id="mapping-confirm" class="dialog-button-recommended">Descubrir</button>
                </div>
            </div>`;
        const content = overlay.querySelector("#mapping-content");
        const defaultActivityHeader = headers.find((h) => h.toLowerCase() === "activity") || headers[0];

        //const columnRows = REQUIRED_COLUMNS.map((col) => {
        //    const options = headers.map((h) => `<option value="${h}" ${h === col ? "selected" : ""}>${h}</option>`).join("");
        //    return `<label style="display:flex; gap:8px; margin-bottom:6px;"><span style="min-width:180px;">${col}</span><select data-col="${col}">${options}</select></label>`;
        //}).join("");
        const columnRows = REQUIRED_COLUMNS.map((col) => {
            const defaultHeader = col === "Activity"
                ? defaultActivityHeader
                : (headers.includes(col) ? col : headers[0]);
            const options = headers.map((h) => `<option value="${h}" ${h === defaultHeader ? "selected" : ""}>${h}</option>`).join("");
            return `<label style="display:flex; gap:8px; margin-bottom:6px;"><span style="min-width:180px;">${col}</span><select data-col="${col}">${options}</select></label>`;
        }).join("");

        //const activityRows = activities.length
        //    ? activities.map((act) => {
        //        const opts = TARGET_ACTIVITIES.map((t) => `<option value="${t}">${t}</option>`).join("");
        //        return `<label style="display:flex; gap:8px; margin-bottom:6px;"><span style="min-width:260px;">${act}</span><select data-activity="${act}">${opts}</select></label>`;
        //    }).join("")
        //    : "<p data-lang=\"no-auto-mapping-detected\">No se detectaron actividades para mapear automáticamente.</p>";

        //content.innerHTML = `<h3 data-lang="column-mapping">Mapeo de columnas</h3>${columnRows}<h3 data-lang="activity-mapping">Mapeo de actividades</h3>${activityRows}`;
        content.innerHTML = `<h3 data-lang="column-mapping">Mapeo de columnas</h3>${columnRows}<h3 data-lang="activity-mapping">Mapeo de actividades</h3><div id="activity-mapping-container"></div>`;

        function renderActivityMapping() {
            const selectedActivityHeader = content.querySelector('select[data-col="Activity"]').value;
            const activityContainer = content.querySelector("#activity-mapping-container");
            const activities = getActivitiesFromColumn(headers, rows, selectedActivityHeader);

            if (!activities.length) {
                activityContainer.innerHTML = "<p>No se detectaron actividades para la columna seleccionada.</p>";
                return;
            }

            const activityRows = activities.map((act) => {
                const opts = TARGET_ACTIVITIES.map((t) => `<option value="${t}">${t}</option>`).join("");
                return `<label style="display:flex; gap:8px; margin-bottom:6px;"><span style="min-width:260px;">${act}</span><select data-activity="${act}">${opts}</select></label>`;
            }).join("");

            activityContainer.innerHTML = activityRows;
        }

        const activityColumnSelect = content.querySelector('select[data-col="Activity"]');
        activityColumnSelect.addEventListener("change", renderActivityMapping);
        renderActivityMapping();

        overlay.querySelector("#mapping-cancel").onclick = () => {
            overlay.style.display = "none";
            resolve(null);
        };


        overlay.querySelector("#mapping-confirm").onclick = () => {
            const columnMapping = {};
            content.querySelectorAll("select[data-col]").forEach((sel) => {
                columnMapping[sel.getAttribute("data-col")] = sel.value;
            });
            const activityMapping = {};
            content.querySelectorAll("select[data-activity]").forEach((sel) => {
                activityMapping[sel.getAttribute("data-activity")] = sel.value;
            });
            overlay.style.display = "none";
            resolve({ columnMapping, activityMapping });
        };

        const savedLang = localStorage.getItem("lang") || "es";
        translatePage(savedLang);
    });
}

async function discover_log(mode = 0) {
    const model_file = document.getElementById("file").files[0];
    const name = document.getElementById("name").value;

    let mappings;
    try {
        const preview = await parseCsv(model_file);
        mappings = await renderMappingDialog(preview.headers, preview.rows);
        if (!mappings) {
            return;
        }
    } catch (error) {
        alert(error.message);
        return;
    }

    const loader = document.getElementById("loadingcontent");
    loader.style.display = "flex";

    var url = `http://127.0.0.1:9000/${mode}`;
    var formData = new FormData();
    formData.append('file', model_file);
    formData.append('name', name);
    formData.append('column_mapping', JSON.stringify(mappings.columnMapping));
    formData.append('activity_mapping', JSON.stringify(mappings.activityMapping));

    // Configurar las opciones de la solicitud
    var options = {
        method: 'POST',
        body: formData

    };

    fetch(url, options)
        .then(response => response.json())
        .then(data => {
            loader.style.display = "none";
            alert("Descubierto con id: " + data.uuid);
        })
        .catch(error => {
            loader.style.display = "none";
            console.error("Error al consultar la API:", error);
            const resultadoDiv = document.getElementById("resultado");
            resultadoDiv.style.display = "block";
            resultadoDiv.innerHTML = "Error al consultar la API: "+error;
        });
}