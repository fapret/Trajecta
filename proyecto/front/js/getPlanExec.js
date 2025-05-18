/*
    tmde-app-curricula is a software that helps students build their curricula and
    see what curricular units they can register to, and track how their career was
    or will be.
    Copyright (C) 2023  Santiago Nicolás Díaz Conde, Santiago Freire López

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
*/
const allFacultiesSelect = document.getElementById("facultades");
const allCareersSelect = document.getElementById("carreras");
const allPlansSelect = document.getElementById("planes");
const selectFacultyBtn = document.getElementById("markFaculty");
const selectCareerBtn = document.getElementById("markCarrera");
const selectPlanBtn = document.getElementById("markPlan");

selectCareerBtn.disabled = true;
selectPlanBtn.disabled = true;

getFaculties(allFacultiesSelect);
selectFacultyBtn.addEventListener("click", () => {
    allCareersSelect.innerHTML ="";
    allPlansSelect.innerHTML ="";
    getCareers(allCareersSelect, allFacultiesSelect.value);
    selectCareerBtn.disabled = false;
    selectPlanBtn.disabled = true;

});
selectCareerBtn.addEventListener("click", () => {
    allPlansSelect.innerHTML ="";
    getPlans(allPlansSelect, allFacultiesSelect.value, allCareersSelect.value);
    selectPlanBtn.disabled = false;
});
selectPlanBtn.addEventListener("click", () => {
    consultarPlan();
});