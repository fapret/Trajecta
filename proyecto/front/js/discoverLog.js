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
function mappingText(key) {
    const language = localStorage.getItem('lang') || 'es';
    return translations[language]?.[key] || key;
}

// CSV records may contain commas, escaped quotes, and line breaks inside quotes.
function parseCsvText(text) {
    text = text.replace(/^\uFEFF/, '');
    let headers, field = '', record = [], quoted = false, closed = false, rowCount = 0;
    const rows = [];
    function finishRecord() {
        record.push(field); field = ''; closed = false;
        if (record.length === 1 && !record[0].trim()) { record = []; return; }
        if (!headers) {
            headers = record.map(value => value.trim());
            if (headers.some(value => !value) || new Set(headers).size !== headers.length) {
                throw new Error(mappingText('mappingInvalidHeaders'));
            }
        } else {
            if (record.length !== headers.length) throw new Error(mappingText('mappingInvalidRow') + ` ${rowCount + 2}`);
            rowCount++;
            if (rows.length < 3) rows.push(record);
        }
        record = [];
    }
    for (let i = 0; i < text.length; i++) {
        const char = text[i];
        if (quoted) {
            if (char === '"') {
                if (text[i + 1] === '"') { field += '"'; i++; }
                else { quoted = false; closed = true; }
            } else field += char;
        } else if (char === ',') {
            record.push(field); field = ''; closed = false;
        } else if (char === '\n' || char === '\r') {
            if (char === '\r' && text[i + 1] === '\n') i++;
            finishRecord();
        } else if (char === '"' && !field && !closed) quoted = true;
        else if (char === '"' || closed && !/\s/.test(char)) throw new Error(mappingText('mappingInvalidCsv'));
        else if (!closed) field += char;
    }
    if (quoted) throw new Error(mappingText('mappingInvalidCsv'));
    if (field || record.length || closed) finishRecord();
    if (!headers || !rowCount) throw new Error(mappingText('mappingEmptyCsv'));
    return {headers, rows, rowCount};
}

async function parseCsv(file) {
    if (!file) throw new Error(mappingText('mappingChooseFile'));
    return parseCsvText(await file.text());
}

function mappingActivityParts(mapping, configuration) {
    const alternatives = configuration.activity_alternatives || [['Action', 'Activity Type']];
    const prefix = alternatives.find(names => names.every(name => mapping[name]));
    if (!prefix) return null;
    return [...prefix, ...configuration.activity_order.filter(name => !alternatives.flat().includes(name))]
        .flatMap(name => mapping[name] ? [name] : configuration.fallbacks?.[name] && mapping[configuration.fallbacks[name]]
            ? [configuration.fallbacks[name]] : configuration.optional_activity_fields?.includes(name) ? [] : [name]);
}

function renderMappingDialog(headers, rows, configuration) {
    return new Promise(resolve => {
        const overlay = document.getElementById('dialog');
        overlay.innerHTML = `
            <section class="dialog column-mapping-dialog" role="dialog" aria-modal="true" aria-labelledby="mapping-title">
                <h2 id="mapping-title" data-lang="mappingTitle"></h2>
                <p data-lang="mappingHelp"></p>
                <p id="mapping-required"></p>
                <p data-lang="mappingActivityRequired"></p>
                <p data-lang="mappingOptionalUnits"></p>
                <div class="mapping-table-scroll"><table class="mapping-table">
                    <thead><tr><th data-lang="mappingSource"></th><th data-lang="mappingMeaning"></th><th data-lang="mappingSample"></th></tr></thead>
                    <tbody id="mapping-content"></tbody>
                </table></div>
                <div><strong data-lang="mappingActivityOrder"></strong><p id="mapping-order"></p></div>
                <div><strong data-lang="mappingActivityPreview"></strong><p id="mapping-preview"></p></div>
                <p id="mapping-error" role="alert" aria-live="polite"></p>
                <div class="mapping-controls">
                    <button type="button" data-lang="cancel" id="mapping-cancel" class="dialog-button-default"></button>
                    <button type="button" data-lang="discover" id="mapping-confirm" class="dialog-button-recommended"></button>
                </div>
            </section>`;
        const content = overlay.querySelector('#mapping-content');
        const selects = [];
        const assigned = new Set();
        const types = configuration.column_types;
        headers.forEach((header, index) => {
            const row = document.createElement('tr');
            const name = document.createElement('th'); name.scope = 'row';
            const label = document.createElement('label'); label.htmlFor = `column-meaning-${index}`; label.textContent = header; name.append(label);
            const cell = document.createElement('td');
            const select = document.createElement('select'); select.id = label.htmlFor; select.dataset.source = header;
            select.add(new Option(mappingText('mappingUnclassified'), ''));
            for (const type of types) {
                const suffix = type.role === 'activity' ? ' (Activity)' : type.role === 'other' ? ' (Other)' : '';
                select.add(new Option(type.name + suffix, type.name));
            }
            const exact = types.find(type => type.name.toLowerCase() === header.toLowerCase());
            if (exact && !assigned.has(exact.name)) { select.value = exact.name; assigned.add(exact.name); }
            cell.append(select);
            const samples = document.createElement('td');
            samples.className = 'mapping-samples';
            samples.textContent = rows.map(values => values[index].replace(/\s+/g, ' ').slice(0, 100)).filter(Boolean).join(' · ') || '—';
            row.append(name, cell, samples); content.append(row); selects.push(select);
        });
        overlay.querySelector('#mapping-required').textContent = mappingText('mappingRequired') + ' ' + types.filter(type => type.required).map(type => type.name).join(', ');
        function currentMapping() {
            return Object.fromEntries(selects.filter(select => select.value).map(select => [select.value, select.dataset.source]));
        }
        function validate() {
            const meanings = selects.map(select => select.value).filter(Boolean);
            const duplicates = [...new Set(meanings.filter((value, index) => meanings.indexOf(value) !== index))];
            const missing = types.filter(type => type.required && !meanings.includes(type.name)).map(type => type.name);
            selects.forEach(select => select.setAttribute('aria-invalid', duplicates.includes(select.value) ? 'true' : 'false'));
            const errors = [];
            if (duplicates.length) errors.push(mappingText('mappingDuplicates') + ' ' + duplicates.join(', '));
            if (missing.length) errors.push(mappingText('mappingMissing') + ' ' + missing.join(', '));
            const mapping = currentMapping();
            const activityParts = mappingActivityParts(mapping, configuration);
            if (!activityParts) errors.push(mappingText('mappingActivityRequired'));
            overlay.querySelector('#mapping-error').textContent = errors.join('\n');
            overlay.querySelector('#mapping-confirm').disabled = !!errors.length;
            overlay.querySelector('#mapping-order').textContent = activityParts ? activityParts.join(' → ')
                : configuration.activity_alternatives.map(names => names.join(' + ')).join(' / ');
            overlay.querySelector('#mapping-preview').textContent = (activityParts || []).flatMap(name => {
                const index = headers.indexOf(mapping[name]);
                let value = index < 0 ? '…' : rows[0][index];
                if (name === 'Unified Curricular Unit' && !value.trim() && configuration.fallbacks?.[name]) {
                    const fallback = headers.indexOf(mapping[configuration.fallbacks[name]]);
                    value = fallback < 0 ? '…' : rows[0][fallback];
                }
                if (configuration.optional_activity_fields?.includes(name) && !value.trim()) return [];
                return [value || 'nan'];
            }).join(' - ');
            return !errors.length;
        }
        function close(result) {
            overlay.style.display = 'none'; overlay.removeEventListener('keydown', onKey);
            resolve(result);
        }
        function onKey(event) { if (event.key === 'Escape') close(null); }
        selects.forEach(select => select.addEventListener('change', validate));
        overlay.querySelector('#mapping-cancel').onclick = () => close(null);
        overlay.querySelector('#mapping-confirm').onclick = () => { if (validate()) close({columnMapping: currentMapping()}); };
        overlay.addEventListener('keydown', onKey);
        translatePage(localStorage.getItem('lang') || 'es');
        validate(); overlay.style.display = 'flex'; selects[0]?.focus();
    });
}

function discoveryServiceUrl() {
    return location.protocol === 'file:' || ['localhost', '127.0.0.1'].includes(location.hostname)
        ? 'http://127.0.0.1:9000' : 'https://trajecta-pm.fapret.com';
}

let discoveryInProgress = false;
async function discover_log(mode = 0) {
    if (discoveryInProgress) return;
    const file = document.getElementById('file').files[0];
    const button = document.getElementById('discoverLog');
    const loader = document.getElementById('loadingcontent');
    const result = document.getElementById('resultado');
    discoveryInProgress = true; button.disabled = true;
    try {
        if (!file) throw new Error(mappingText('mappingChooseFile'));
        let mappings;
        if (mode !== 'pnml') {
            const preview = await parseCsv(file);
            const response = await fetch(`${discoveryServiceUrl()}/discoverer/column-types`);
            if (!response.ok) throw new Error(mappingText('mappingConfigurationError'));
            const configuration = await response.json();
            mappings = await renderMappingDialog(preview.headers, preview.rows, configuration);
            if (!mappings) return;
        }
        loader.style.display = 'flex'; result.style.display = 'none';
        const data = new FormData(); data.append('file', file); data.append('name', document.getElementById('name').value);
        if (mappings) data.append('column_mapping', JSON.stringify(mappings.columnMapping));
        const response = await fetch(`${discoveryServiceUrl()}/discoverer/${mode}`, {method: 'POST', body: data});
        const job = await response.json();
        if (!response.ok) throw new Error(job.error || mappingText('mappingUploadError'));
        result.textContent = mappingText('mappingStarted') + ' ' + job.uuid;
        result.style.display = 'block';
    } catch (error) {
        result.textContent = error.message;
        result.style.display = 'block';
    } finally {
        loader.style.display = 'none'; button.disabled = false; discoveryInProgress = false;
    }
}
