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
function seeConformance(mode) {
    const allDiscoveriesSelect = document.getElementById("discoveries");
    const allReferencesSelect = document.getElementById("references");
    const uuid = allDiscoveriesSelect.value;
    const refuuid = allReferencesSelect.value;
    let baseUrl;

    if (window.location.protocol === "file:") {
        // Opened directly as file:///...
        baseUrl = "http://127.0.0.1:9007";
    }
    else if (
        window.location.hostname === "localhost" ||
        window.location.hostname === "127.0.0.1"
    ) {
        // Running from local web server
        baseUrl = "http://127.0.0.1:9007";
    }
    else {
        // Running from trajecta-pm.fapret.com or any other web host
        baseUrl = "https://trajecta-pm.fapret.com/conformance";
    }
    const url = `${baseUrl}/${mode}/${refuuid}/${uuid}`;
    fetch(url)
    .then(response => response.json())
    .then(data => {
        const loader = document.getElementById("loadingcontent");
        loader.style.display = "none";
        const resultadoDiv = document.getElementById("resultado");

        let tbr = data["token_based_replay"];
        let n = tbr.length;

        let avgConsumed = tbr.reduce((sum, t) => sum + t["consumed_tokens"], 0) / n;
        let avgMissing = tbr.reduce((sum, t) => sum + t["missing_tokens"], 0) / n;
        let avgProduced = tbr.reduce((sum, t) => sum + t["produced_tokens"], 0) / n;
        let avgRemaining = tbr.reduce((sum, t) => sum + t["remaining_tokens"], 0) / n;

        resultadoDiv.innerHTML = `
            <br>
            <label class="info-value">${n}</label>
            <label class="info-label" data-lang="TotalTraces">Total Traces</label>
            <br>
            <label class="info-value">${data["fitness"]["average_trace_fitness"]}</label>
            <label class="info-label" data-lang="avgTraceFitness">Average Trace Fitness</label>
            <br>
            <label class="info-value">${data["fitness"]["log_fitness"]}</label>
            <label class="info-label" data-lang="logFitness">Log Fitness</label>
            <br>
            <label class="info-value">${data["precision"]}</label>
            <label class="info-label" data-lang="precision">Precision</label>
            <br>
            <label class="info-value">${data["fitness"]["percentage_of_fitting_traces"]}</label>
            <label class="info-label" data-lang="percFittingTraces">Percentage of Fitting Traces</label>
            <br>
            <label class="info-value">${avgConsumed}</label>
            <label class="info-label" data-lang="avgConsumedTokens">Average Consumed Tokens</label>
            <br>
            <label class="info-value">${avgMissing}</label>
            <label class="info-label" data-lang="avgMissedTokens">Average Missed Tokens</label>
            <br>
            <label class="info-value">${avgProduced}</label>
            <label class="info-label" data-lang="avgProducedTokens">Average Produced Tokens</label>
            <br>
            <label class="info-value">${avgRemaining}</label>
            <label class="info-label" data-lang="avgRemainingTokens">Average Remaining Tokens</label>
        `;
    })
    .catch(error => {
        console.error("Error al consultar la API:", error);
        const resultadoDiv = document.getElementById("resultado");
        resultadoDiv.innerHTML = "Error al consultar la API: "+error;
    });
}