const {test} = require('node:test');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const context = vm.createContext({localStorage: {getItem: () => 'es'}, translations: {es: {}}});
vm.runInContext(fs.readFileSync(path.join(__dirname, '../js/discoverLog.js'), 'utf8'), context);
const parse = text => JSON.parse(JSON.stringify(context.parseCsvText(text)));

test('preview reads UTF-8 BOM, quoted commas, quotes, and multiline values', () => {
    const log = parse('\uFEFFstudent,"unit,name",note\r\n0001,"Algebra, I","He said ""yes""\r\nagain"\r\n');
    assert.deepEqual(log.headers, ['student','unit,name','note']);
    assert.deepEqual(log.rows, [['0001','Algebra, I','He said "yes"\r\nagain']]);
    assert.equal(log.rowCount, 1);
});
test('empty logs, duplicate headers, invalid quotes, and ragged rows are rejected', () => {
    for (const value of ['', 'id,action\n', 'id,id\n1,x', 'id,action\n1,"unclosed', 'id,action\n1,x,y']) {
        assert.throws(() => parse(value));
    }
});
test('preview keeps source column order and does not inject missing fields', () => {
    const log = parse('grade,student,unit\n6,0001,001\n4,0002,002\n3,0003,003\n2,0004,004');
    assert.deepEqual(log.headers, ['grade','student','unit']);
    assert.equal(log.rows.length, 3);
    assert.equal(log.rowCount, 4);
    assert.equal(log.rows[0][1], '0001');
});

const config = {
    activity_order: ['Action','Activity Type','Unified Curricular Unit','Unified Curricular Unit Name'],
    activity_alternatives: [['Action','Activity Type'],['Activity']],
    fallbacks: {'Unified Curricular Unit':'Curricular Unit'},
    optional_activity_fields: ['Unified Curricular Unit Name'],
};
const parts = mapping => JSON.parse(JSON.stringify(context.mappingActivityParts(mapping, config)));
test('preview accepts Activity with unit fallback and no unified name', () => {
    assert.deepEqual(parts({'Activity':'label','Curricular Unit':'unit'}),['Activity','Curricular Unit']);
    assert.deepEqual(parts({'Action':'action','Activity Type':'kind','Curricular Unit':'unit'}),['Action','Activity Type','Curricular Unit']);
});
test('complete Action pair has priority and optional unified fields keep API order', () => {
    assert.deepEqual(parts({'Activity':'label','Action':'action','Activity Type':'kind','Curricular Unit':'unit',
        'Unified Curricular Unit':'unified','Unified Curricular Unit Name':'name'}),
        ['Action','Activity Type','Unified Curricular Unit','Unified Curricular Unit Name']);
    assert.equal(parts({'Action':'action','Curricular Unit':'unit'}),null);
});
