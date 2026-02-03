/*
    tmde-app-curricula is a software that helps students build their curricula and
    see what curricular units they can register to, and track how their career was
    or will be.
    Copyright (C) 2023  Santiago Nicolás Díaz Conde, Santiago Freire López
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
const allEvaluationSelect = document.getElementById("evaluations");
const selectEvaluationBtn = document.getElementById("markEvaluation");
const model_file = document.getElementById("file");
const type = document.getElementById("evaluacion");

selectEvaluationBtn.disabled = true;

getFaculties(allFacultiesSelect);
allFacultiesSelect.addEventListener("change", () => {
    allCareersSelect.innerHTML ="";
    allPlansSelect.innerHTML ="";
    allUCSSelect.innerHTML ="";
    allEvaluationSelect.innerHTML ="";
    getCareers(allCareersSelect, allFacultiesSelect.value);
	selectEvaluationBtn.disabled = true;
});
allCareersSelect.addEventListener("change", () => {
    allPlansSelect.innerHTML ="";
    allUCSSelect.innerHTML ="";
    allEvaluationSelect.innerHTML ="";
    getPlans(allPlansSelect, allFacultiesSelect.value, allCareersSelect.value);
    selectEvaluationBtn.disabled = true;
});
allPlansSelect.addEventListener("change", () => {
    allUCSSelect.innerHTML ="";
    allEvaluationSelect.innerHTML ="";
    getPlanUCS(allUCSSelect, allFacultiesSelect.value, allCareersSelect.value, allPlansSelect.value);
    selectEvaluationBtn.disabled = true;
});
allUCSSelect.addEventListener("change", () => {
    allEvaluationSelect.innerHTML ="";
    getEvaluations(allEvaluationSelect, type.value, allUCSSelect.value, allFacultiesSelect.value);
    selectEvaluationBtn.disabled = false;
});
type.addEventListener("change", () => {
    allEvaluationSelect.innerHTML ="";
    getEvaluations(allEvaluationSelect, type.value, allUCSSelect.value, allFacultiesSelect.value);
    selectEvaluationBtn.disabled = false;
});
selectEvaluationBtn.addEventListener("click", () => {
    agregar_Evaluacion();
});