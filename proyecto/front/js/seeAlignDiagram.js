/*
    Trajecta is a software that helps students build their curricula and
    see what curricular units they can register to, and track how their career was
    or will be. And helps academic managers do different researches.
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
function seeDiagram(mappings) {
    const allDiscoveriesSelect = document.getElementById("discoveries");
    const allReferencesSelect = document.getElementById("references");
    const uuid = allDiscoveriesSelect.value;
    const refuuid = allReferencesSelect.value;
    const img = document.getElementById('diagram');
    const diagramsButtons = document.getElementById('diagramsButtons');
    let baseUrl;

    if (window.location.protocol === "file:") {
        // Opened directly as file:///...
        baseUrl = "http://127.0.0.1:9008/alignement";
    }
    else if (
        window.location.hostname === "localhost" ||
        window.location.hostname === "127.0.0.1"
    ) {
        // Running from local web server
        baseUrl = "http://127.0.0.1:9008/alignement";
    }
    else {
        // Running from trajecta-pm.fapret.com or any other web host
        baseUrl = "https://trajecta-pm.fapret.com/alignement";
    }

    const query =
    `?ids=${encodeURIComponent(
        mappings.caseColumns.join(",")
    )}` +
    `&activities=${encodeURIComponent(
        mappings.activityColumns.join(",")
    )}` +
    `&timestamp=${encodeURIComponent(
        mappings.timestampColumn
    )}`;

    const url = `${baseUrl}/${refuuid}/${uuid}${query}`;
    img.src = url;
    img.classList.remove('hidden');
    diagramsButtons.classList.remove('hidden');
    let loader = document.getElementById("loadingcontent");
    loader.style.display = "none";
}

async function getColumns(uuid)
{
    let baseUrl;

    if (
        window.location.protocol === "file:" ||
        window.location.hostname === "localhost" ||
        window.location.hostname === "127.0.0.1"
    ) {
        baseUrl = "http://127.0.0.1:9000/log-columns";
    }
    else {
        baseUrl = "https://trajecta-pm.fapret.com/log-columns";
    }

    const response =
        await fetch(`${baseUrl}/${uuid}`);

    return await response.json();
}

function renderAlignmentDialog(headers)
{
    return new Promise((resolve) =>
    {
        const overlay = document.getElementById("dialog");

        overlay.style.display = "flex";

        const options = headers.map(h =>
            `<option value="${h}">${h}</option>`
        ).join("");

        overlay.innerHTML = `
        <div class="dialog"
             style="max-width:900px;width:95%;">

            <h2 data-lang="alignConfig">Alignment Configuration</h2>
            <p data-lang="alignConfigDesc">Es necesario que selecciones que columnas son Case ID, Activity y la columna de timestamp. Puedes seleccionar varias columnas como Case ID y Activity.</p>

            <label data-lang="alignConfigCaseIDColumns">Case ID Columns</label>
            <select id="caseColumns"
                    multiple
                    size="8"
                    style="width:100%;">
                ${options}
            </select>

            <br><br>

            <label data-lang="alignConfigActivityColumns">Activity Columns</label>
            <select id="activityColumns"
                    multiple
                    size="8"
                    style="width:100%;">
                ${options}
            </select>

            <br><br>

            <label data-lang="alignConfigTimestampColumn">Timestamp Column</label>
            <select id="timestampColumn"
                    style="width:100%;">
                ${options}
            </select>

            <br><br>

            <div style="display:flex;gap:8px;justify-content:flex-end;">
                <button id="alignCancel"
                        class="dialog-button-default">
                    Cancelar
                </button>

                <button id="alignConfirm"
                        class="dialog-button-recommended" data-lang="alignConfigConfirmBtn">
                    Ver Tabla de Alineamiento
                </button>
            </div>

        </div>
        `;

        document.getElementById("alignCancel")
            .onclick = () =>
        {
            overlay.style.display = "none";
            resolve(null);
        };

        document.getElementById("alignConfirm")
            .onclick = () =>
        {
            const caseColumns =
                Array.from(
                    document.getElementById("caseColumns")
                        .selectedOptions
                ).map(o => o.value);

            const activityColumns =
                Array.from(
                    document.getElementById("activityColumns")
                        .selectedOptions
                ).map(o => o.value);

            const timestampColumn =
                document.getElementById("timestampColumn").value;

            overlay.style.display = "none";

            resolve({
                caseColumns,
                activityColumns,
                timestampColumn
            });
        };
    });
}