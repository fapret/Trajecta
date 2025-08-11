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
function seeDiagram(mode) {
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
        default:
            return;
    }
    const url = `http://127.0.0.1:9001/${modestr}/${uuid}`;
    img.src = url;
    img.classList.remove('hidden');
    diagramsButtons.classList.remove('hidden');
}