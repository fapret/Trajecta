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
const allFacultiesSelect = document.getElementById("facultades");
const allCareersSelect = document.getElementById("carreras");
const allPlansSelect = document.getElementById("planes");
const allUCSSelect = document.getElementById("ucs");
const allCourseSSelect = document.getElementById("courses");
const selectCourseBtn = document.getElementById("markCourse");
const model_file = document.getElementById("file");

selectCourseBtn.disabled = true;

getFaculties(allFacultiesSelect);
allFacultiesSelect.addEventListener("change", () => {
    allCareersSelect.innerHTML ="";
    allPlansSelect.innerHTML ="";
    allUCSSelect.innerHTML ="";
    allCourseSSelect.innerHTML ="";
    getCareers(allCareersSelect, allFacultiesSelect.value);
	selectCourseBtn.disabled = true;
});
allCareersSelect.addEventListener("change", () => {
    allPlansSelect.innerHTML ="";
    allUCSSelect.innerHTML ="";
    allCourseSSelect.innerHTML ="";
    getPlans(allPlansSelect, allFacultiesSelect.value, allCareersSelect.value);
    selectCourseBtn.disabled = true;
});
allPlansSelect.addEventListener("change", () => {
    allUCSSelect.innerHTML ="";
    allCourseSSelect.innerHTML ="";
    getPlanUCS(allUCSSelect, allFacultiesSelect.value, allCareersSelect.value, allPlansSelect.value);
    selectCourseBtn.disabled = true;
});
allUCSSelect.addEventListener("change", () => {
    allCourseSSelect.innerHTML ="";
    getCourses(allCourseSSelect, allUCSSelect.value, allFacultiesSelect.value);
    selectCourseBtn.disabled = false;
});
selectCourseBtn.addEventListener("click", () => {
    agregar_Curso();
});