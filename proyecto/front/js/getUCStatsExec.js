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
const allFacultiesSelect = document.getElementById("facultades");
const allUCSelect = document.getElementById("ucs");
const allDiscoveriesSelect = document.getElementById("discoveries");
const selectFacultyBtn = document.getElementById("markFaculty");
const selectUCBtn = document.getElementById("markUC");
const selectBtn = document.getElementById("seeStatistics");
selectUCBtn.disabled = true;
selectBtn.disabled = true;
google.charts.load('current',{packages:['corechart']});
const graphList = document.getElementById("graphList");
const result = document.getElementById("resultado");

getFaculties(allFacultiesSelect);
getDiscoveries(allDiscoveriesSelect);
selectFacultyBtn.addEventListener("click", () => {
    getUCS(allUCSelect, allFacultiesSelect.value);
    selectUCBtn.disabled = false;
});
selectUCBtn.addEventListener("click", () => {
    selectBtn.disabled = false;
});
selectBtn.addEventListener("click", () => {
    graphList.style.display = "flex";
    result.style.display = "block";
    displayUCStats();
});