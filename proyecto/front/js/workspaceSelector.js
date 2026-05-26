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
const selector = document.getElementById('workspaceSelector');
const choices = new Choices(selector, {
    searchPlaceholderValue: 'Search workspaces...',
    shouldSort: false,
    removeItemButton: false,
});

async function loadWorkspaces() {
    try {
        //const response = await fetch('http://localhost:8080/curricula_microservice/Workspaces');
        const response = await fetch('http://127.0.0.1:8080/curricula_microservice/Workspaces');
        if (!response.ok) throw new Error('Failed to fetch workspaces');
        const workspaces = await response.json(); // ["uuid1", "uuid2", ...]

        // Clear and add options
        choices.clearChoices();
        choices.setChoices(
            [
                { value: '__create__', label: '➕ Create new workspace', customProperties: { isCreateOption: true } },
                ...workspaces.map(uuid => ({ value: uuid, label: uuid }))
            ],
            'value',
            'label',
            true
        );

        // Restore previous selection
        const savedUUID = localStorage.getItem('selectedWorkspace');
        if (savedUUID && workspaces.includes(savedUUID)) {
            choices.setChoiceByValue(savedUUID);
            setSelectedLabel(savedUUID);
        }
    } catch (err) {
        console.error(err);
        alert('Error loading workspaces');
    }
}

// Update the visible selected label
function setSelectedLabel(uuid) {
    const selectedItem = selector.closest('.choices').querySelector('.choices__item--selectable');
    if (selectedItem) {
        selectedItem.innerHTML = `Workspace: <span class="workspace-label">${uuid}</span>`;
        //selectedItem.textContent = `Workspace: ${uuid}`;
    }
}

selector.addEventListener('change', async (e) => {
    const value = e.target.value;

    if (value === '__create__') {
        const uuid = prompt('Enter new workspace UUID:');
        if (!uuid) {
            // Revert to previous selection if canceled
            const saved = localStorage.getItem('selectedWorkspace');
            if (saved) choices.setChoiceByValue(saved);
            return;
        }

        try {
            const formData = new FormData();
            formData.append('uuid', uuid);

            //const response = await fetch('http://localhost:8080/curricula_microservice/Workspaces', {
            const response = await fetch('http://127.0.0.1:8080/curricula_microservice/Workspaces', {
                method: 'POST',
                body: formData
            });

            if (!response.ok) throw new Error('Failed to create workspace');

            alert('Workspace created successfully!');
            await loadWorkspaces();
            choices.setChoiceByValue(uuid);
            localStorage.setItem('selectedWorkspace', uuid);
            setSelectedLabel(uuid);
        } catch (err) {
            console.error(err);
            alert('Error creating workspace');
        }
    } else {
        localStorage.setItem('selectedWorkspace', value);
        setSelectedLabel(value);
        console.log('Selected workspace:', value);
    }
});

// Initial load
loadWorkspaces();
