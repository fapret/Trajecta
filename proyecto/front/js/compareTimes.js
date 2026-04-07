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
function compareDiscovers() {
    const allDiscoveriesSelectA = document.getElementById("discoveriesA");
    const uuidA = allDiscoveriesSelectA.value;
    const allDiscoveriesSelectB = document.getElementById("discoveriesB");
    const uuidB = allDiscoveriesSelectB.value;

    const resultadoDiv = document.getElementById("resultado");

    const url = buildPMWorkspaceUrl(`http://127.0.0.1:9005/${uuidA}/${uuidB}`);
    fetch(url)
    .then(response => response.text())
    .then(text => {
        // Replace NaN with null for JSON parsing
        const safeText = text.replace(/\bNaN\b/g, "null");
        return JSON.parse(safeText);
    })
    .then(data => {
        if (!Array.isArray(data) || data.length === 0) {
            resultadoDiv.innerHTML = "No data found.";
            return;
        }

        // Build table headers
        const headers = Object.keys(data[0]);
        const headerRow = document.getElementById("tableHeader");
        headerRow.innerHTML = headers.map(h => `<th>${h}</th>`).join("");

        // Build table rows
        const body = document.getElementById("tableBody");
        body.innerHTML = data.map(row => {
            return `<tr>${headers.map(h => `<td>${row[h] ?? ""}</td>`).join("")}</tr>`;
        }).join("");

    })
    .catch(error => {
        console.error("Error al consultar la API:", error);
        resultadoDiv.innerHTML = "Error al consultar la API: "+error;
    });
}