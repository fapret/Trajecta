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
function seeDiscoverStats(mode=2) {
    const allDiscoveriesSelect = document.getElementById("discoveries");
    const uuid = allDiscoveriesSelect.value;
    const url = `http://127.0.0.1:9009/${mode}/${uuid}`;
    fetch(url)
    .then(response => response.json())
    .then(data => {
        const loader = document.getElementById("loadingcontent");
        loader.style.display = "none";
        const resultadoDiv = document.getElementById("resultado");

        resultadoDiv.innerHTML = `
            <br>
            <label class="info-value">${data.num_variants}</label>
            <label class="info-label" data-lang="NumVariants">Variants</label>
            <br>
            <label class="info-value">${data.num_traces}</label>
            <label class="info-label" data-lang="NumTraces">Students/Traces</label>
            <br>
            <label class="info-label" data-lang="StartActivities">Start activities:</label>
        `;
        for (const activity in data.start_activities) {
            resultadoDiv.innerHTML += `<p>${activity}</p>`;
        };
        resultadoDiv.innerHTML += `
            <br>
            <label class="info-label" data-lang="EndActivities">End activities:</label>
        `;
        for (const activity in data.end_activities) {
            resultadoDiv.innerHTML += `<p>${activity}</p>`;
        };
        resultadoDiv.innerHTML += `
            <br>
            <label class="info-label" data-lang="AllActivities">All activities:</label>
        `;
        data.all_activities.forEach(activity => {
            resultadoDiv.innerHTML += `<p>${activity}</p>`;
        });
        const savedLang = localStorage.getItem("lang") || "es";
        translatePage(savedLang);
    })
    .catch(error => {
        console.error("Error al consultar la API:", error);
        const resultadoDiv = document.getElementById("resultado");
        resultadoDiv.innerHTML = "Error al consultar la API: "+error;
    });
}