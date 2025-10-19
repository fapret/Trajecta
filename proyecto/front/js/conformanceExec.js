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
const allDiscoveriesSelect = document.getElementById("discoveries");
const allReferencesSelect = document.getElementById("references");
const result = document.getElementById("resultado");

const seeConformancePNA = document.getElementById("seeConformancePNA");
const seeConformancePNH = document.getElementById("seeConformancePNH");
const seeConformancePNI = document.getElementById("seeConformancePNI");

getDiscoveries(allDiscoveriesSelect, 2);
getDiscoveries(allReferencesSelect, 1);
seeConformancePNA.addEventListener("click", () => {
    result.style.display = "block";
    const loader = document.getElementById("loadingcontent");
    loader.style.display = "flex";
    seeConformance("alpha");
});
seeConformancePNH.addEventListener("click", () => {
    result.style.display = "block";
    const loader = document.getElementById("loadingcontent");
    loader.style.display = "flex";
    seeConformance("heuristics");
});
seeConformancePNI.addEventListener("click", () => {
    result.style.display = "block";
    const loader = document.getElementById("loadingcontent");
    loader.style.display = "flex";
    seeConformance("inductive");
});