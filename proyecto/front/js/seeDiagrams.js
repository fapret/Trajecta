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
function seeDiagram(mode, type=0, activity=100, paths=100, filtermode=2) {
    const allDiscoveriesSelect = document.getElementById("discoveries");
    const uuid = allDiscoveriesSelect.value;
    const img = document.getElementById('diagram');
    const diagramsButtons = document.getElementById('diagramsButtons');
    let modestr;
    switch (mode) {
        case 1:
            modestr = 'dfg';
            break;
        case 2:
            modestr = 'bpmn';
            break;
        case 3:
            modestr = 'pnml_alpha';
            break;
        case 4:
            modestr = 'pnml_heuristics';
            break;
        case 5:
            modestr = 'ptml';
            break;
        case 6:
            modestr = "perf_dfg";
            break;
        case 7:
            modestr = "pnml_inductive";
            break;
        default:
            return;
    }
    let baseUrl;

    if (window.location.protocol === "file:") {
        // Opened directly as file:///...
        baseUrl = "http://127.0.0.1:9001";
    }
    else if (
        window.location.hostname === "localhost" ||
        window.location.hostname === "127.0.0.1"
    ) {
        // Running from local web server
        baseUrl = "http://127.0.0.1:9001";
    }
    else {
        // Running from trajecta-pm.fapret.com or any other web host
        baseUrl = "https://trajecta-pm.fapret.com/viewer";
    }
    const url = `${baseUrl}/${modestr}/${uuid}/${type}?activity=${activity}&path=${paths}&filtermode=${filtermode}`;
    img.src = url;
    img.classList.remove('hidden');
    diagramsButtons.classList.remove('hidden');
    let loader = document.getElementById("loadingcontent");
    loader.style.display = "none";
}