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

	Santiago Nicolás Díaz Conde Email: sndc.33@gmail.com and contact@fapret.com
*/
const allFacultiesSelect = document.getElementById("facultades");
const allUCSelect = document.getElementById("ucs");
const selectFacultyBtn = document.getElementById("markFaculty");
const consultBtn = document.getElementById("markUC");
consultBtn.disabled = true;

getFaculties(allFacultiesSelect);
selectFacultyBtn.addEventListener("click", () => {
  getUCS(allUCSelect, allFacultiesSelect.value);
  consultBtn.disabled = false;
});
consultBtn.addEventListener("click", () => {
  consultarUC();
});