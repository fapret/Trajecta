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
function seeDiagram(mode) {
    const allDiscoveriesSelect = document.getElementById("discoveries");
    const allReferencesSelect = document.getElementById("references");
    const uuid = allDiscoveriesSelect.value;
    const refuuid = allReferencesSelect.value;
    const img = document.getElementById('diagram');
    const diagramsButtons = document.getElementById('diagramsButtons');
    let modestr;
    switch (mode) {
        case 3:
            modestr = 'alpha';
            break;
        case 4:
            modestr = 'heuristics';
            break;
        case 7:
            modestr = "inductive";
            break;
        default:
            return;
    }
    const url = `http://127.0.0.1:9008/${modestr}/${refuuid}/${uuid}`;
    img.src = url;
    img.classList.remove('hidden');
    diagramsButtons.classList.remove('hidden');
    let loader = document.getElementById("loadingcontent");
    loader.style.display = "none";
}