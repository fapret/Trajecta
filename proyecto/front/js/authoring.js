/* Faculty model authoring. Existing consultation/student pages keep their own scripts. */
document.addEventListener('DOMContentLoaded', () => {
  const host = document.querySelector('[data-authoring]');
  if (!host) return;
  const kind = host.dataset.authoring;
  const paths = {faculty: '/Faculty', career: '/Faculty/Carrera', plan: '/Faculty/Carrera/Plan',
    subject: '/Faculty/Carrera/Plan/Subjects', unit: '/Faculty/ucs', edit: '/Faculty/ucs',
    course: '/Faculty/ucs/course', evaluation: '/Faculty/ucs/evaluation', scale: '/Faculty/grade-scales'};
  const base = (location.protocol === 'file:' || ['localhost', '127.0.0.1'].includes(location.hostname))
    ? 'http://127.0.0.1:8080' : 'https://tmde-api.fapret.com';
  const $ = id => document.getElementById(id);
  const t = key => translations[localStorage.getItem('lang') || 'es']?.[key] || key;
  const field = (id, key, control) => `<div class="field" id="${id}Field"><label for="${id}" data-lang="${key}"></label>${control}</div>`;
  const input = (id, key, type = 'text', extra = '') => field(id, key, `<input id="${id}" type="${type}" ${extra}>`);
  const select = (id, key, extra = '') => field(id, key, `<select id="${id}" ${extra}></select>`);
  const number = (id, key, value = 0, min = 0) => input(id, key, 'number', `required min="${min}" step="1" value="${value}"`);
  const thresholdKeys = ['examApprovalGrade', 'courseApprovalGrade', 'coursePartialApprovalGrade', 'partialApprovalGrade', 'tutoringApprovalGrade'];
  let markup = '';
  if (kind !== 'faculty') markup += select('faculty', 'faculty', 'required');
  if (['plan', 'subject'].includes(kind)) markup += select('career', 'career', 'required');
  if (kind === 'plan') markup += select('mode', 'aOperation', 'required') + select('existingPlan', 'plan');
  if (kind === 'subject') markup += select('plan', 'plan', 'required');
  if (['edit', 'course', 'evaluation'].includes(kind)) markup += select('unit', 'uc', 'required');
  if (kind === 'scale') markup += select('scaleToEdit', 'aScaleToEdit');
  if (['faculty', 'career', 'subject', 'unit', 'edit', 'scale'].includes(kind)) {
    if (['subject', 'unit', 'edit'].includes(kind)) markup += input('id', 'genericID', kind === 'subject' ? 'number' : 'text', `required ${kind === 'subject' ? 'min="0" step="1"' : ''} ${kind === 'edit' ? 'readonly' : ''}`);
    markup += input('name', 'name', 'text', 'required');
  }
  if (kind === 'plan') markup += number('year', 'year', new Date().getFullYear(), 0) + select('planType', 'aPlanType', 'required') + number('minCred', 'aMinimumCredits');
  if (['subject', 'unit', 'edit'].includes(kind)) markup += number('cred', 'credits');
  if (kind === 'subject') markup += select('parent', 'parentSubject') + select('units', 'aUnitsOptional', 'multiple');
  if (['plan', 'unit', 'edit'].includes(kind)) markup += select('gradeScale', 'aGradeScale', kind === 'plan' ? 'required' : '')
    + '<a class="action-link" href="./grade-scales.html" data-lang="aManageScales"></a>';
  if (['unit', 'edit'].includes(kind)) markup += input('valid', 'aValid', 'checkbox', 'checked') + select('memberships', 'aMemberships', 'multiple')
    + '<p class="hint" data-lang="aMembershipHelp"></p>' + field('requirements', 'RequirementJson', '<textarea id="requirements" spellcheck="false"></textarea>')
    + '<p class="hint" data-lang="aRequirementsHelp"></p><details><summary data-lang="aExamples"></summary><pre id="examples"></pre></details>'
    + '<button type="button" id="format" data-lang="aFormatJson"></button>';
  if (kind === 'course') markup += number('year', 'year', new Date().getFullYear(), 1) + number('edition', 'edition', 1, 1);
  if (kind === 'evaluation') markup += select('type', 'aEvaluationType', 'required') + input('date', 'aDate', 'date', 'required') + select('course', 'aCourse', 'required');
  if (kind === 'scale') {
    [3, 6, 3, 60, 0].forEach((value, i) => { markup += input(thresholdKeys[i], `a${thresholdKeys[i]}`, 'number', `required step="any" value="${value}"`); });
    markup += '<p class="hint" data-lang="aScaleImpact"></p><pre id="scaleUsage"></pre>';
  }
  host.innerHTML = `<h1 data-lang="aTitle_${kind}"></h1><p class="status" id="authoringStatus" role="status" aria-live="polite"></p>
    <form id="authoringForm"><fieldset id="authoringFields" style="border:0;padding:0;display:grid;gap:1rem">${markup}
    <button type="submit" id="save" data-lang="aSave"></button></fieldset></form>`;
  let lastStatus = ['', false, ''];
  const status = (key, error = false, detail = '') => {
    lastStatus = [key, error, detail];
    $('authoringStatus').textContent = key ? t(key) + (detail ? ` ${detail}` : '') : '';
    $('authoringStatus').className = `status ${error ? 'error' : key === 'aSaved' ? 'success' : ''}`;
  };
  const option = (s, value, text, key) => {
    const o = new Option(text, value); if (key) o.dataset.lang = key; s.add(o);
  };
  const options = (id, rows, empty = 'aChoose') => {
    const el = $(id); if (!el) return;
    el.replaceChildren();
    if (!el.multiple) option(el, '', t(empty), empty);
    rows.forEach(row => option(el, String(row.value), row.label, row.key));
  };
  const show = (id, visible) => { if ($(`${id}Field`)) $(`${id}Field`).hidden = !visible; if ($(id)) $(id).disabled = !visible; };
  const values = id => [...$(id).selectedOptions].map(o => o.value);
  let controller, version = 0, plans = [], subjects = [], scales = [], courses = [], loadedUnit = null, busy = false, loading = false;
  const workspace = () => localStorage.getItem('selectedWorkspace');
  async function api(path, params = {}, method = 'GET', body, signal) {
    if (!workspace()) throw new Error(t('aSelectWorkspace'));
    const url = new URL(`${base}/curricula_microservice${path}`);
    Object.entries({uuid: workspace(), ...params}).forEach(([key, value]) => url.searchParams.set(key, value));
    const response = await fetch(url, {method, signal, ...(body === undefined ? {} : {headers: {'Content-Type': 'application/json'}, body: JSON.stringify(body)})});
    const text = await response.text(); let data;
    try { data = JSON.parse(text); } catch { throw new Error(text || `HTTP ${response.status}`); }
    if (!response.ok) throw new Error(data.error || `HTTP ${response.status}`);
    return data;
  }
  async function load(task, success = false) {
    if (busy) return;
    controller?.abort(); controller = new AbortController(); const signal = controller.signal, token = ++version;
    loading = true; $('save').disabled = true; status('aLoading');
    try {
      await task(signal);
      if (token !== version) return;
      translatePage(localStorage.getItem('lang') || 'es'); status(success ? 'aSaved' : '');
    } catch (e) { if (e.name !== 'AbortError' && token === version) status('aError', true, e.message); }
    finally { if (token === version) { loading = false; updateEnabled(); } }
  }
  function updateEnabled() {
    let ready = !busy && !loading && !!workspace();
    for (const id of ['faculty', 'career', 'plan', 'unit']) if ($(id)?.required && !$(id).disabled && !$(id).value) ready = false;
    if (kind === 'plan' && (!$('gradeScale').value || ($('mode').value === 'edit' && !$('existingPlan').value))) ready = false;
    if (kind === 'edit' && !loadedUnit) ready = false;
    if (kind === 'evaluation' && $('type').value !== 'ExamEvaluation' && !$('course').value) ready = false;
    $('save').disabled = !ready;
  }
  function resetUnit() {
    loadedUnit = null;
    if (kind === 'edit') {
      ['id', 'name', 'requirements'].forEach(id => $(id).value = ''); $('cred').value = 0; $('valid').checked = true;
      if ($('memberships')) [...$('memberships').options].forEach(o => o.selected = false);
      if ($('gradeScale')) $('gradeScale').value = '';
    }
  }
  async function initialize(signal) {
    plans = []; subjects = []; scales = []; courses = []; resetUnit();
    ['faculty', 'career', 'plan', 'unit', 'gradeScale', 'memberships', 'parent', 'units', 'course', 'existingPlan', 'scaleToEdit'].forEach(id => { if ($(id)) options(id, []); });
    if (!workspace()) throw new Error(t('aSelectWorkspace'));
    if (kind !== 'faculty') {
      const faculties = await api('/Faculty', {}, 'GET', undefined, signal);
      options('faculty', faculties.map(name => ({value: name, label: name})), faculties.length ? 'aChoose' : 'aNoFaculties');
    }
  }
  async function facultyChanged(signal) {
    plans = []; subjects = []; scales = []; courses = []; resetUnit();
    ['career', 'plan', 'unit', 'gradeScale', 'memberships', 'parent', 'units', 'course', 'existingPlan', 'scaleToEdit'].forEach(id => options(id, []));
    if (kind === 'scale') resetScale();
    if (!$('faculty').value) return;
    const faculty = $('faculty').value;
    const [f, scaleData, units] = await Promise.all([
      api('/Faculty', {faculty}, 'GET', undefined, signal),
      ['plan', 'unit', 'edit', 'scale'].includes(kind) ? api(paths.scale, {faculty}, 'GET', undefined, signal) : [],
      ['unit', 'edit', 'course', 'evaluation', 'subject'].includes(kind) ? api(paths.unit, {faculty, withName: true}, 'GET', undefined, signal) : []
    ]);
    if (signal.aborted) return;
    options('career', f.Careers.map(name => ({value: name, label: name})), f.Careers.length ? 'aChoose' : 'aNoCareers');
    options('unit', units.map(cu => ({value: cu.id, label: `${cu.id} — ${cu.name}`})), units.length ? 'aChoose' : 'aNoUnits');
    options('units', units.map(cu => ({value: cu.id, label: `${cu.id} — ${cu.name}`})));
    scales = scaleData;
    options('gradeScale', scales.map(s => ({value: s.name, label: s.name})), kind === 'plan' ? (scales.length ? 'aChoose' : 'aNoScales') : 'aInherit');
    options('scaleToEdit', scales.map(s => ({value: s.name, label: s.name})), 'aNewScale');
    if (['unit', 'edit'].includes(kind)) {
      const allPlans = [];
      for (const career of f.Careers) {
        const c = await api(paths.career, {faculty, career}, 'GET', undefined, signal);
        for (const plan of c.Plans) allPlans.push(await api(paths.plan, {faculty, career, plan}, 'GET', undefined, signal));
      }
      if (signal.aborted) return;
      const memberships = [];
      for (const p of allPlans) {
        if (p.Type === 'SubjectPlan') memberships.push({value: JSON.stringify({career: p.Career, plan: p.Year}), label: `${p.Career} / ${p.Year}`});
        else for (const id of p.Subjects) {
          const s = await api(paths.subject, {faculty, career: p.Career, plan: p.Year, subject: id}, 'GET', undefined, signal);
          memberships.push({value: JSON.stringify({career: p.Career, plan: p.Year, subject: Number(id)}), label: `${p.Career} / ${p.Year} / ${s.Name} (${id})`});
        }
      }
      if (!signal.aborted) options('memberships', memberships);
    }
  }
  async function careerChanged(signal) {
    plans = []; subjects = []; ['plan', 'existingPlan', 'parent'].forEach(id => options(id, []));
    if (!$('career').value) return;
    const context = {faculty: $('faculty').value, career: $('career').value};
    const c = await api(paths.career, context, 'GET', undefined, signal);
    const data = await Promise.all(c.Plans.map(plan => api(paths.plan, {...context, plan}, 'GET', undefined, signal)));
    if (signal.aborted) return;
    plans = data;
    options('plan', plans.filter(p => p.Type === 'CreditsPlan').map(p => ({value: p.Year, label: String(p.Year)})), 'aChooseCreditPlan');
    options('existingPlan', plans.map(p => ({value: p.Year, label: String(p.Year)})));
  }
  async function planChanged(signal) {
    options('parent', [], 'aNoParent'); subjects = [];
    if (!$('plan').value) return;
    const context = {faculty: $('faculty').value, career: $('career').value, plan: Number($('plan').value)};
    const p = await api(paths.plan, context, 'GET', undefined, signal);
    subjects = await Promise.all(p.Subjects.map(subject => api(paths.subject, {...context, subject}, 'GET', undefined, signal)));
    if (!signal.aborted) options('parent', subjects.map(s => ({value: s.Id, label: `${s.Name} (${s.Id})`})), 'aNoParent');
  }
  async function unitChanged(signal) {
    resetUnit(); courses = []; options('course', []);
    if (!$('unit').value) return;
    const cu = await api(paths.unit, {faculty: $('faculty').value, curricularUnit: $('unit').value}, 'GET', undefined, signal);
    if (signal.aborted) return;
    courses = cu.Course;
    options('course', courses.map(c => ({value: `${c.Year}/${c.Edition}`, label: `${c.Year} / ${c.Edition}`})), courses.length ? 'aChoose' : 'aNoCourses');
    if (kind === 'edit') {
      loadedUnit = cu; $('id').value = cu.Id; $('name').value = cu.Name; $('cred').value = cu.Cred;
      $('valid').checked = cu.Valid; $('gradeScale').value = cu.GradeScale || '';
      $('requirements').value = cu.Requirement.length ? JSON.stringify(cu.Requirement[0], null, 2) : '';
      const selected = new Set(cu.Memberships.map(m => JSON.stringify(m)));
      [...$('memberships').options].forEach(o => { o.selected = selected.has(o.value); });
    }
  }
  function resetScale() {
    $('name').readOnly = false; $('name').value = '';
    [3, 6, 3, 60, 0].forEach((v, i) => $(thresholdKeys[i]).value = v); $('scaleUsage').textContent = '';
  }
  function scaleChanged() {
    const s = scales.find(s => s.name === $('scaleToEdit').value);
    if (!s) return resetScale();
    $('name').value = s.name; $('name').readOnly = true;
    thresholdKeys.forEach(key => $(key).value = s[key]);
    $('scaleUsage').textContent = `${t('aPlans')}: ${s.plans.map(p => `${p.career} / ${p.plan}`).join(', ') || '—'}\n${t('aUnits')}: ${s.curricularUnits.join(', ') || '—'}`;
  }
  function modeChanged() {
    const edit = $('mode').value === 'edit';
    show('existingPlan', edit); ['year', 'planType'].forEach(id => show(id, !edit)); show('minCred', !edit && $('planType').value === '0');
    if (edit) $('gradeScale').value = plans.find(p => String(p.Year) === $('existingPlan').value)?.GradeScale || '';
    updateEnabled();
  }
  $('authoringForm').addEventListener('submit', async event => {
    event.preventDefault(); if (busy || $('save').disabled || !$('authoringForm').reportValidity()) return;
    const params = kind === 'faculty' ? {} : {faculty: $('faculty').value}; let body = {}, method = 'POST';
    try {
      if (['faculty', 'career'].includes(kind)) body = {name: $('name').value};
      if (kind === 'plan') {
        const edit = $('mode').value === 'edit'; method = edit ? 'PUT' : 'POST';
        body = {career: $('career').value, plan: Number(edit ? $('existingPlan').value : $('year').value), gradeScale: $('gradeScale').value};
        if (!edit) Object.assign(body, {planType: Number($('planType').value), minCred: Number($('minCred').value)});
      }
      if (kind === 'subject') body = {career: $('career').value, plan: Number($('plan').value), subjectID: Number($('id').value), subjectName: $('name').value,
        subjectCredits: Number($('cred').value), Parent: $('parent').value ? Number($('parent').value) : -1, curricularUnits: values('units')};
      if (['unit', 'edit'].includes(kind)) {
        const requirements = $('requirements').value.trim();
        body = {id: $('id').value, name: $('name').value, cred: Number($('cred').value), valid: $('valid').checked,
          gradeScale: $('gradeScale').value || null, memberships: values('memberships').map(JSON.parse), requirements: requirements ? JSON.parse(requirements) : null};
        if (kind === 'edit') method = 'PUT';
      }
      if (kind === 'course') body = {id: $('unit').value, year: Number($('year').value), edition: Number($('edition').value)};
      if (kind === 'evaluation') {
        body = {id: $('unit').value, type: $('type').value, date: $('date').value};
        if (body.type !== 'ExamEvaluation') { const [year, edition] = $('course').value.split('/').map(Number); Object.assign(body, {year, edition}); }
      }
      if (kind === 'scale') {
        body = {name: $('name').value}; thresholdKeys.forEach(key => { body[key] = Number($(key).value); if (!Number.isFinite(body[key])) throw new Error(t('aFiniteThreshold')); });
        method = $('scaleToEdit').value ? 'PUT' : 'POST';
      }
    } catch (e) { status('aError', true, e.message); return; }
    busy = true; controller?.abort(); ++version; $('authoringFields').disabled = true; choices.disable(); status('aSaving');
    try {
      await api(paths[kind], params, method, body);
      busy = false; $('authoringFields').disabled = false; choices.enable();
      if (kind === 'scale') {
        const name = body.name;
        await load(async signal => { scales = await api(paths.scale, params, 'GET', undefined, signal);
          options('scaleToEdit', scales.map(s => ({value: s.name, label: s.name})), 'aNewScale'); $('scaleToEdit').value = name; scaleChanged(); }, true);
      } else if (kind === 'edit') await load(unitChanged, true);
      else if (kind === 'plan') await load(careerChanged, true);
      else if (kind === 'subject') await load(planChanged, true);
      else { status('aSaved'); updateEnabled(); }
    } catch (e) { busy = false; $('authoringFields').disabled = false; choices.enable(); status('aError', true, e.message); updateEnabled(); }
  });
  const on = (id, event, fn) => { $(id)?.addEventListener(event, fn); };
  on('faculty', 'change', () => load(facultyChanged)); on('career', 'change', () => load(careerChanged));
  on('plan', 'change', () => load(planChanged)); on('unit', 'change', () => load(unitChanged));
  on('scaleToEdit', 'change', scaleChanged); on('gradeScale', 'change', updateEnabled); on('course', 'change', updateEnabled);
  on('mode', 'change', modeChanged); on('existingPlan', 'change', modeChanged); on('planType', 'change', modeChanged);
  on('type', 'change', () => { show('course', $('type').value !== 'ExamEvaluation'); updateEnabled(); });
  on('format', 'click', () => { try { const value = $('requirements').value.trim(); if (value) $('requirements').value = JSON.stringify(JSON.parse(value), null, 2); status(''); } catch (e) { status('aError', true, e.message); } });
  if (kind === 'plan') {
    options('mode', [{value: 'create', label: t('aCreatePlan'), key: 'aCreatePlan'}, {value: 'edit', label: t('aAssignScale'), key: 'aAssignScale'}]); $('mode').value = 'create';
    options('planType', [{value: 0, label: t('aCreditPlan'), key: 'aCreditPlan'}, {value: 1, label: t('aSubjectPlan'), key: 'aSubjectPlan'}]); $('planType').value = '0'; modeChanged();
  }
  if (kind === 'evaluation') {
    options('type', ['ExamEvaluation', 'CourseEvaluation', 'PartialEvaluation', 'TutoringEvaluation'].map(value => ({value, label: t(`a${value}`), key: `a${value}`})));
    $('type').value = 'ExamEvaluation'; show('course', false);
  }
  if ($('examples')) $('examples').textContent = JSON.stringify({SomeOf: [2, {Coursed: 'UC101'}, {Exam: 'UC102'}, {NOT: {RegisteredTo: 'UC103'}}, {CreditsOnPlan: '2026,Career,60'}, {CreditsOnSubject: '100,20'}]}, null, 2);
  document.addEventListener('workspaceChanged', () => { if (!busy) load(initialize); });
  $('languageSelector')?.addEventListener('change', () => { status(...lastStatus); if (kind === 'scale') scaleChanged(); });
  load(initialize);
});
