/*
    Trajecta is a software that helps students build their curricula and
    see what curricular units they can register to, and track how their career was
    or will be. And helps academic managers do different researches.
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
var leftnavbutton = document.getElementById('btnleftnav');
var leftnav = document.getElementById('leftnav');

leftnavbutton.addEventListener("click", changeLeftNavState);

function changeLeftNavState(){
  if (leftnav.classList.contains("left-nav-active")){
    leftnav.classList.remove("left-nav-active");
  } else {
    leftnav.classList.add("left-nav-active");
  }
}

// Share authoring navigation across existing application pages.
const authoringNav = document.createElement('details');
authoringNav.className = 'authoring-nav';
authoringNav.open = true;
const authoringSummary = document.createElement('summary');
authoringSummary.className = 'left-nav-cat';
authoringSummary.dataset.lang = 'aAdministration';
authoringSummary.textContent = 'ADMINISTRACIÓN DEL MODELO';
authoringNav.appendChild(authoringSummary);
const authoringLinks = [
  ['make-faculty.html', 'aTitle_faculty', 'Crear facultad', 'add-company-svgrepo-com.svg'],
  ['make-career.html', 'aTitle_career', 'Crear carrera', 'add-document-note-svgrepo-com.svg'],
  ['make-plan.html', 'aTitle_plan', 'Crear plan / asignar escala', 'file-multiple-svgrepo-com.svg'],
  ['make-subject.html', 'aTitle_subject', 'Crear materia', 'add-file-svgrepo-com.svg'],
  ['make-uc.html', 'aTitle_unit', 'Crear unidad curricular', 'add-file-svgrepo-com_2.svg'],
  ['edit-uc.html', 'aTitle_edit', 'Editar unidad curricular', 'file-error-svgrepo-com.svg'],
  ['add-course-uc.html', 'aTitle_course', 'Crear curso', 'education-book-add-svgrepo-com.svg'],
  ['create-evaluation.html', 'aTitle_evaluation', 'Crear evaluación', 'file-signed-svgrepo-com.svg'],
  ['grade-scales.html', 'aTitle_scale', 'Escalas de calificaciones', 'add-category-svgrepo-com.svg'],
];
for (const [page, key, label, image] of authoringLinks) {
  const row = document.createElement('div'); row.className = 'left-nav-div';
  const link = document.createElement('a'); link.className = 'left-nav-a'; link.href = './' + page;
  const icon = document.createElement('img'); icon.src = './images/' + image; icon.alt = '';
  const text = document.createElement('p'); text.dataset.lang = key; text.textContent = label;
  link.appendChild(icon); link.appendChild(text); row.appendChild(link); authoringNav.appendChild(row);
}
const sidebarFooter = Array.from(leftnav.children).find(child => child.tagName === 'P' && child.textContent.includes('Copyright'));
leftnav.insertBefore(authoringNav, sidebarFooter || null);
