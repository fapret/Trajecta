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
const WORKSPACES_BASE_URL = 'https://tmde-api.fapret.com:8443/curricula_microservice/Workspaces';
const API_BASE_PREFIX = 'https://tmde-api.fapret.com:8443/curricula_microservice/';
const WORKSPACE_TOKEN_STORAGE_PREFIX = 'workspaceToken:';

const selector = document.getElementById('workspaceSelector');
const choices = new Choices(selector, {
    searchPlaceholderValue: 'Search workspaces...',
    shouldSort: false,
    removeItemButton: false,
});

let cachedWorkspaces = [];

function getWorkspaceToken(uuid) {
    if (!uuid) return null;
    return sessionStorage.getItem(`${WORKSPACE_TOKEN_STORAGE_PREFIX}${uuid}`);
}

function setWorkspaceToken(uuid, token) {
    if (!uuid) return;
    const key = `${WORKSPACE_TOKEN_STORAGE_PREFIX}${uuid}`;
    if (token) {
        sessionStorage.setItem(key, token);
    } else {
        sessionStorage.removeItem(key);
    }
}

function normalizeWorkspace(workspaceEntry) {
    if (typeof workspaceEntry === 'string') {
        return { uuid: workspaceEntry, protected: false };
    }
    return {
        uuid: workspaceEntry.uuid,
        protected: Boolean(workspaceEntry.protected)
    };
}

async function unlockWorkspace(uuid, promptMessage = `Workspace ${uuid} is protected. Enter password:`) {
    const password = prompt(promptMessage);
    if (!password) {
        return false;
    }

    const formData = new FormData();
    formData.append('uuid', uuid);
    formData.append('password', password);

    const response = await fetch(`${WORKSPACES_BASE_URL}/Unlock`, {
        method: 'POST',
        body: formData,
        skipWorkspaceAuthRetry: true,
    });

    if (!response.ok) {
        throw new Error('Invalid workspace password');
    }

    let token = null;
    const contentType = response.headers.get('content-type') || '';
    if (contentType.includes('application/json')) {
        const payload = await response.json();
        token = payload?.token || payload?.workspaceToken || payload?.accessToken || null;
    } else {
        token = (await response.text()).trim();
    }

    if (!token) {
        throw new Error('Unlock endpoint returned no token');
    }

    setWorkspaceToken(uuid, token);
    return true;
}

function shouldAttachWorkspaceAuth(requestUrl) {
    return requestUrl.startsWith(API_BASE_PREFIX)
        && !requestUrl.includes('/Workspaces')
        && !requestUrl.includes('/Workspaces/Unlock');
}

function installWorkspaceAuthFetchInterceptor() {
    if (window.__workspaceAuthFetchInstalled) {
        return;
    }

    window.__workspaceAuthFetchInstalled = true;
    const originalFetch = window.fetch.bind(window);

    window.fetch = async (input, init = {}) => {
        const requestUrl = typeof input === 'string' ? input : input.url;
        if (!shouldAttachWorkspaceAuth(requestUrl)) {
            return originalFetch(input, init);
        }

        const selectedWorkspace = localStorage.getItem('selectedWorkspace');
        const token = getWorkspaceToken(selectedWorkspace);
        const headers = new Headers(init.headers || (input instanceof Request ? input.headers : undefined));

        if (token) {
            headers.set('Authorization', `Bearer ${token}`);
        }

        const requestInit = { ...init, headers };
        let response = await originalFetch(input, requestInit);

        if (
            response.status === 401
            && !requestInit._workspaceAuthRetried
            && !requestInit.skipWorkspaceAuthRetry
            && selectedWorkspace
        ) {
            const workspace = cachedWorkspaces.find(item => item.uuid === selectedWorkspace);
            if (workspace?.protected) {
                const unlocked = await unlockWorkspace(selectedWorkspace, `Session expired for protected workspace ${selectedWorkspace}. Enter password:`);
                if (unlocked) {
                    const retryToken = getWorkspaceToken(selectedWorkspace);
                    if (retryToken) {
                        headers.set('Authorization', `Bearer ${retryToken}`);
                    }
                    response = await originalFetch(input, { ...requestInit, headers, _workspaceAuthRetried: true });
                }
            }
        }

        return response;
    };
}

function installWorkspaceAuthXHRInterceptor() {
    if (window.__workspaceAuthXHRInstalled) {
        return;
    }

    window.__workspaceAuthXHRInstalled = true;

    const originalOpen = XMLHttpRequest.prototype.open;
    const originalSend = XMLHttpRequest.prototype.send;

    XMLHttpRequest.prototype.open = function (method, url, async, user, password) {
        this.__workspaceAuthUrl = url;
        this.__workspaceAuthMethod = method;
        this.__workspaceAuthRetried = false;
        return originalOpen.call(this, method, url, async, user, password);
    };

    XMLHttpRequest.prototype.send = function (body) {
        const url = this.__workspaceAuthUrl;
        if (!url || !shouldAttachWorkspaceAuth(url)) {
            return originalSend.call(this, body);
        }

        const workspaceId = localStorage.getItem('selectedWorkspace');
        const token = getWorkspaceToken(workspaceId);
        if (token) {
            this.setRequestHeader('Authorization', `Bearer ${token}`);
        }

        this.addEventListener('load', async () => {
            if (this.status !== 401 || this.__workspaceAuthRetried || !workspaceId) {
                return;
            }

            const workspace = cachedWorkspaces.find(item => item.uuid === workspaceId);
            if (!workspace?.protected) {
                return;
            }

            try {
                const unlocked = await unlockWorkspace(workspaceId, `Session expired for protected workspace ${workspaceId}. Enter password:`);
                if (!unlocked) {
                    return;
                }

                const retryToken = getWorkspaceToken(workspaceId);
                if (!retryToken) {
                    return;
                }

                this.__workspaceAuthRetried = true;
                this.open(this.__workspaceAuthMethod || 'GET', url, true);
                this.setRequestHeader('Authorization', `Bearer ${retryToken}`);
                this.send(body);
            } catch (error) {
                console.error('Workspace unlock failed after 401:', error);
            }
        });

        return originalSend.call(this, body);
    };
}

async function loadWorkspaces() {
    try {
        const response = await fetch(WORKSPACES_BASE_URL, { skipWorkspaceAuthRetry: true });
        if (!response.ok) throw new Error('Failed to fetch workspaces');

        const rawWorkspaces = await response.json();
        cachedWorkspaces = rawWorkspaces.map(normalizeWorkspace);
        const workspaceUUIDs = cachedWorkspaces.map(workspace => workspace.uuid);

        choices.clearChoices();
        choices.setChoices(
            [
                { value: '__create__', label: '➕ Create new workspace', customProperties: { isCreateOption: true } },
                ...workspaceUUIDs.map(uuid => ({ value: uuid, label: uuid }))
            ],
            'value',
            'label',
            true
        );

        const savedUUID = localStorage.getItem('selectedWorkspace');
        if (savedUUID && workspaceUUIDs.includes(savedUUID)) {
            choices.setChoiceByValue(savedUUID);
            setSelectedLabel(savedUUID);
        }
    } catch (err) {
        console.error(err);
        alert('Error loading workspaces');
    }
}

function setSelectedLabel(uuid) {
    const selectedItem = selector.closest('.choices').querySelector('.choices__item--selectable');
    if (selectedItem) {
        selectedItem.innerHTML = `Workspace: <span class="workspace-label">${uuid}</span>`;
    }
}

selector.addEventListener('change', async (e) => {
    const value = e.target.value;

    if (value === '__create__') {
        const uuid = prompt('Enter new workspace UUID:');
        if (!uuid) {
            const saved = localStorage.getItem('selectedWorkspace');
            if (saved) choices.setChoiceByValue(saved);
            return;
        }

        try {
            const formData = new FormData();
            formData.append('uuid', uuid);

            const response = await fetch(WORKSPACES_BASE_URL, {
                method: 'POST',
                body: formData,
                skipWorkspaceAuthRetry: true,
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
        try {
            const selectedWorkspace = cachedWorkspaces.find(workspace => workspace.uuid === value);
            if (selectedWorkspace?.protected) {
                const unlocked = await unlockWorkspace(value);
                if (!unlocked) {
                    const saved = localStorage.getItem('selectedWorkspace');
                    if (saved) choices.setChoiceByValue(saved);
                    return;
                }
            }

            localStorage.setItem('selectedWorkspace', value);
            setSelectedLabel(value);
            console.log('Selected workspace:', value);
        } catch (error) {
            console.error(error);
            alert('Unable to unlock workspace');
            const saved = localStorage.getItem('selectedWorkspace');
            if (saved) {
                choices.setChoiceByValue(saved);
                setSelectedLabel(saved);
            }
        }
    }
});

installWorkspaceAuthFetchInterceptor();
installWorkspaceAuthXHRInterceptor();
loadWorkspaces();
