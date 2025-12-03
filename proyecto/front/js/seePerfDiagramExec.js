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
const result = document.getElementById("resultado");

const seeDiagramPerf = document.getElementById("seePerformanceDiagramDFG");

const acts = document.getElementById("activities");
const pths = document.getElementById("paths");

const zoomIN = document.getElementById("zoomIN");
const zoomOUT = document.getElementById("zoomOUT");
const ResetBtn = document.getElementById("ResetBtn");

getDiscoveries(allDiscoveriesSelect, 2);
seeDiagramPerf.addEventListener("click", () => {
    result.style.display = "block";
    const loader = document.getElementById("loadingcontent");
    loader.style.display = "flex";
    seeDiagram(6, 2, acts.value, pths.value);
});
/////////////////////////////
const img = document.getElementById('diagram');
let position = { x: 0, y: 0 };
let isDragging = false;
let dragStart = { x: 0, y: 0 };
let scale = 1;

function updateTransform() {
    img.style.transform = `translate(${position.x}px, ${position.y}px) scale(${scale})`;
}

function zoom(factor, centerX = result.clientWidth / 2, centerY = result.clientHeight / 2) {
    if (img.classList.contains('hidden')) return;

    const rect = img.getBoundingClientRect();
    const offsetX = centerX - rect.left;
    const offsetY = centerY - rect.top;

    const prevScale = scale;
    scale *= factor;
    scale = Math.max(0.2, Math.min(scale, 5));

    position.x -= offsetX * (scale - prevScale) / prevScale;
    position.y -= offsetY * (scale - prevScale) / prevScale;

    updateTransform();
}

result.addEventListener('wheel', (e) => {
    e.preventDefault();
    zoom(e.deltaY < 0 ? 1.1 : 0.9, e.clientX, e.clientY);
});

result.addEventListener('mousedown', (e) => {
    if (img.classList.contains('hidden')) return;
    isDragging = true;
    result.style.cursor = 'grabbing';
    dragStart.x = e.clientX - position.x;
    dragStart.y = e.clientY - position.y;
});

result.addEventListener('mouseup', () => {
    isDragging = false;
    result.style.cursor = 'grab';
});

result.addEventListener('mouseleave', () => {
    isDragging = false;
    result.style.cursor = 'grab';
});

result.addEventListener('mousemove', (e) => {
    if (isDragging) {
        position.x = e.clientX - dragStart.x;
        position.y = e.clientY - dragStart.y;
        updateTransform();
    }
});

zoomIN.addEventListener("click", () => {
    zoom(1.2);
});

zoomOUT.addEventListener("click", () => {
    zoom(0.8);
});

ResetBtn.addEventListener("click", () => {
    scale = 1;
    position = { x: 0, y: 0 };
    updateTransform();
});