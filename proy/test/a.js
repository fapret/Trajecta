const fs = require('fs');
const util = require('util');
Ecore = require('ecore/dist/ecore.xmi');
//Ecore = require('ecore/dist/ecore.xmi');
//const Ecore = require('ecore');
//import Ecore from './src/ecore.mjs';
const resourceSet = Ecore.ResourceSet.create();

function processFile(file) {

    var resource = resourceSet.create({uri : file});

    var fileContents = fs.readFileSync(file, 'utf8');

    try {
        resource.parse(fileContents, Ecore.XMI);
    } catch(err) {
        console.log('*** Failed parsing file: ' + file);
        console.trace(err);
        return;
    }

    var firstElement = resource.get('contents').first();
    if(firstElement.eClass.values.name === 'EPackage') {
        // This is an EPackage, so add it to the registry
        console.log("::: Adding to registry: " + firstElement.get('name'));
        Ecore.EPackage.Registry.register(firstElement);
    }

    if (false) {
        console.log("::: JSON Dump of " + file);
        console.log(util.inspect(resource.to(Ecore.JSON), false, null));
    }

    if (false) {
        console.log("::: XMI Dump of " + file);
        console.log(resource.to(Ecore.XMI, true));
    }

}

function extract_classes(uri) {

    // llegar a esto (y el siguiente foreach) costó más de lo que parece
    var eClasses = Ecore.EPackage.Registry.getEPackage(uri).get('eClassifiers').filter(function (eClassifier) {
        return (eClassifier.eClass);
    });


    // para cada una de las eclasses registradas en el package, las guardo en un diccionario para más fácil acceso
    eClasses.forEach(function(eClassInstance) {
        var eClassName = eClassInstance.get('name');
        set_classes[eClassName] = eClassInstance;
        set_classes[eClassName]['references'] = {};
    });

}

function get_references(eclass) {
    const all = set_classes[eclass].get('eAllStructuralFeatures');
    var eReferences = all.filter(function (classifier) {
        return classifier.eClass === Ecore.EReference;
    });

    set_classes[eclass]['references'] = eReferences;
}

function main() {
    const filePath = '/Users/sfreire/facultad/TMDE/tmde04/proy/test/IngComputacion.xmi';
    const fileContent = fs.readFileSync(filePath, 'utf-8');

    processFile('asignaturas.ecore');
    processFile('estudiantes-references.ecore');
    resourceSet.parse(fileContent, Ecore.XMI);

    var metamodelos_URI = new Set(["asignaturasURI", "estudiantesURI"]);
    metamodelos_URI.forEach(function (current_uri) {
        extract_classes(current_uri);
    })

    var r = set_classes['Root'].create();
    var juancito = set_classes['Student'].create({Id : '1', Name : 'Juancito'});
    r.set('Student', juancito);
    console.log(r.get('Student').get('Name')); // Juancito

/*
    var contents = xmiResource.getContents();

    // Access the elements from the XMI model
    contents.forEach(function (element) {
        // Perform actions with the elements as needed
        console.log(element); // Example: Print the element to the console
    }); */
}

var set_classes = {};
main();