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
function displayPlanStats(){
    const allDiscoveriesSelect = document.getElementById("discoveries");
    const uuid = allDiscoveriesSelect.value;
    const allPlansSelect = document.getElementById("planes");
    const plan = allPlansSelect.value;
    const allCareersSelect = document.getElementById("carreras");
    const career = allCareersSelect.value;
    let baseUrl;

    if (window.location.protocol === "file:") {
        // Opened directly as file:///...
        baseUrl = "http://127.0.0.1:9006/planstats";
    }
    else if (
        window.location.hostname === "localhost" ||
        window.location.hostname === "127.0.0.1"
    ) {
        // Running from local web server
        baseUrl = "http://127.0.0.1:9006/planstats";
    }
    else {
        // Running from trajecta-pm.fapret.com or any other web host
        baseUrl = "https://trajecta-pm.fapret.com/planstats";
    }
    const url = `${baseUrl}/${uuid}/${career}/${plan}`;
    fetch(url)
    .then(response => response.json())
    .then(data => {
        const resultadoDiv = document.getElementById("resultado");
        resultadoDiv.innerHTML = `
            <label class="info-label" data-lang="discoverUUID">UUID de Descubrimiento:</label>
            <label class="info-value">${data.uuid}</label>
            <br>
            <label class="info-label" data-lang="uc">Carrera:</label>
            <label class="info-value">${data.career}</label>
            <br>
            <label class="info-label" data-lang="plan">Plan:</label>
            <label class="info-value">${data.plan}</label>
            <br>
            <br>
            <label class="info-value">${data.inscriptionsCounter}</label>
            <label class="info-label" data-lang="studentsInscriptions">Estudiantes se inscribieron al plan</label>
            
        `;

        const savedLang = localStorage.getItem("lang") || "es";
        translatePage(savedLang);

        const graph0 = document.getElementById("graph0");

        const bins = {};
        for (let i = 0; i <= 500; i += 25) {
            let label;
            if (i === 500) {
                label = "500+";
            } else {
                label = `${i}-${i + 24}`;
            }
            bins[label] = 0;
        }

        for (const [creditsStr, count] of Object.entries(data.studentsCreditsDistribution)) {
            const credits = parseInt(creditsStr, 10);
            if (credits >= 500) {
                bins["500+"] += count;
            } else {
                const binStart = Math.floor(credits / 25) * 25;
                const label = `${binStart}-${binStart + 24}`;
                bins[label] += count;
            }
        }

        const distributionArray = [['Credits Interval', 'Students']];
        for (const [label, count] of Object.entries(bins)) {
            distributionArray.push([label, count]);
        }

        const data0 = google.visualization.arrayToDataTable(distributionArray);
        const graph0options = {
            title: 'Credits Distribution',
            hAxis: {title: 'Credits'},
            vAxis: {title: 'Amount of students'},
            legend: 'none'
        };
        const chart0 = new google.visualization.AreaChart(graph0);
        chart0.draw(data0, graph0options);
    })
    .catch(error => {
        console.error("Error al consultar la API:", error);
        const resultadoDiv = document.getElementById("resultado");
        resultadoDiv.innerHTML = "Error al consultar la API: "+error;
    });
}