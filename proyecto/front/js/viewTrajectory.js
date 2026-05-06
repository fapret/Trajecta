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
function mostrar_trayectoria() {
    const allFacultiesSelect = document.getElementById("facultades");
    const allCareersSelect = document.getElementById("carreras");
    const allPlansSelect = document.getElementById("planes");

    const facultyName = allFacultiesSelect.value;
    const career = allCareersSelect.value;
    const plan = allPlansSelect.value;
    const workspaceID = localStorage.getItem('selectedWorkspace');
    if (workspaceID == null) {
        alert("Please select a workspace");
        return;
    }

    var nodos = [];
    var nodos_0 = [];
    var nodos_process = {};
    var nodos_final = [];
    var aristas = [];

    const url = `http://127.0.0.1:8080/curricula_microservice/Faculty/Carrera/Plan/eval`;
    var formData = new FormData();
    formData.append('faculty', facultyName);
    formData.append('career', career);
    formData.append('plan', plan);
    formData.append('uuid', workspaceID);
    formData.append('valid_flag', 'true');
    if(document.getElementById("ci").value != undefined){
        formData.append('id', document.getElementById("ci").value);
    }

    // Configurar las opciones de la solicitud
    var options = {
        method: 'POST',
        body: formData

    };

    function processSomeOfGraph(reqFather, aristas, materias, nodos_0){
        for (let j = 1; j < reqFather.length; j++) {
            let a = reqFather[j];
            let b = Object.keys(a);
            let req = b[0];
            if (req === "Coursed") {
                let source_index = findKeyByValue(nodos_0, a['Coursed']);
                let dest_index = findKeyByValue(nodos_0, materias);
                let aaa = {
                    from: source_index,
                    to: dest_index,
                    color: "#0000FF",
                    arrows: "to",
                    dashes: true
                };
                aristas.push(aaa);
            } else if (req === "Exam") {
                let source_index = findKeyByValue(nodos_0, a['Exam']);
                let dest_index = findKeyByValue(nodos_0, materias);
                let aaa = {
                    from: source_index,
                    to: dest_index,
                    color: "#0000FF",
                    arrows: "to"
                };
                aristas.push(aaa);
            } else if (req === "SomeOf") {
                processSomeOfGraph(a['SomeOf'], aristas, materias, nodos_0);
            }
        }
    }

    fetch(url, options)
        .then(response => response.json())
        .then(materias => {
            var formDataCopy = formData;

            for (let i = 0; i < materias.length; i++) {
                nodos_0.push({id: i + 1, materia_id: materias[i]});
            }

            for (let i = 0; i < materias.length; i++) {
                let apiUrl = `http://127.0.0.1:8080/curricula_microservice/Faculty/ucs?faculty=${facultyName}&uuid=${workspaceID}&curricularUnit=` + materias[i];
                fetch(apiUrl)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('No se pudo obtener la respuesta de la API');
                        }
                        return response.json(); // Parsear la respuesta JSON
                    })
                    .then(data => {
                        nodos.push({id: i + 1, materia_id: materias[i], materia_name: data['Name']});
                        nodos_process[materias[i]] = null;

                        if ((data.Requirement).length > 0) {
                            //PENDIENTE: si es un someof, tengo que ver cuales de las N se cumple,
                            //y mostrar solo las aristas de ella. por ejemplo, tprog y sus 2 vias de cursado
                            if (Object.keys(data.Requirement[0])[0] === "SomeOf") {
                                processSomeOfGraph(data.Requirement[0]['SomeOf'], aristas, materias[i], nodos_0);
                            }
                            else {
                                let a = data.Requirement[0];
                                let b = Object.keys(a);
                                let req = b[0];
                                if (req === "Coursed") {
                                    let source_index = findKeyByValue(nodos_0, a['Coursed']);
                                    let dest_index = findKeyByValue(nodos_0, materias[i]);
                                    let aaa = {
                                        from: source_index,
                                        to: dest_index,
                                        color: "#0000FF",
                                        arrows: "to",
                                        dashes: true
                                    };
                                    aristas.push(aaa);
                                } else if (req === "Exam") {
                                    let source_index = findKeyByValue(nodos_0, a['Exam']);
                                    let dest_index = findKeyByValue(nodos_0, materias[i]);
                                    let aaa = {
                                        from: source_index,
                                        to: dest_index,
                                        color: "#0000FF",
                                        arrows: "to"
                                    };
                                    aristas.push(aaa);
                                }
                            }
                        }

                        //solo hago la solicitud al final
                        if (i === materias.length-1) {
                            // Realiza la segunda solicitud AJAX con formDataCopy
                            var xhr2 = new XMLHttpRequest();
                            if(document.getElementById("ci").value != undefined){
                                xhr2.open('GET', `http://127.0.0.1:8080/curricula_microservice/Faculty/Carrera/Plan/evaluations?uuid=${workspaceID}&faculty=${facultyName}&career=${career}&plan=${plan}&id=${document.getElementById("ci").value}`, true);
                            } else {
                                xhr2.open('GET', `http://127.0.0.1:8080/curricula_microservice/Faculty/Carrera/Plan/evaluations?uuid=${workspaceID}&faculty=${facultyName}&career=${career}&plan=${plan}`, true);
                            }
                            xhr2.onload = async function () {
                                if (xhr2.status >= 200 && xhr2.status < 300) {
                                    // Procesa la respuesta de la segunda solicitud aquí
                                    var notas = JSON.parse(xhr2.responseText);

                                    for (let i = 0; i < notas.length; i++) {
                                        //solo reemplazo el estado de aprobación si no tenía datos o es de curso aprobado a examen
                                        if (nodos_process[notas[i]['id']] == null) {
                                            nodos_process[notas[i]['id']] = notas[i]['type'];
                                        }
                                        //si tengo registrado un curso aprobado pero aparece un examen
                                        //o curso exonerado, cuenta como examen
                                        if ((nodos_process[notas[i]['id']] === "CourseEvaluation" && notas[i]['type'] === "ExamEvaluation") || (notas[i]['type'] === "CourseEvaluation" && notas[i]['grade'] >= 6)) {
                                            nodos_process[notas[i]['id']] = "ExamEvaluation";
                                        }
                                    }

                                    for (let i = 0; i < materias.length; i++) {
                                        let nodo_id = "";
                                        let nodo_name = "";
                                        let nodo_materia_id = "";
                                        for (let j = 0; j < nodos.length; j++)
                                            if (nodos[j]['materia_id'] === materias[i]) {
                                                nodo_id = nodos[j]['id'];
                                                nodo_name = nodos[j]['materia_name'];
                                                nodo_materia_id = nodos[j]['materia_id']
                                                break;
                                            }


                                        const result = await getMaxRequirementLevel(facultyName, materias[i]);

                                        if (nodos_process[materias[i]] == null)
                                            nodos_final.push({
                                                id: nodo_id,
                                                label: nodo_name,
                                                level: result,
                                                materia_id: nodo_materia_id,
                                                shape: "box",
                                                color: "#FFFFFF"
                                            });
                                        else if (nodos_process[materias[i]] === "CourseEvaluation")
                                            nodos_final.push({
                                                id: nodo_id,
                                                label: nodo_name,
                                                level: result,
                                                materia_id: nodo_materia_id,
                                                shape: "box",
                                                color: "#FFFF00"
                                            });
                                        else
                                            nodos_final.push({
                                                id: nodo_id,
                                                label: nodo_name,
                                                level: result,
                                                materia_id: nodo_materia_id,
                                                shape: "box",
                                                color: "#008000",
                                                font: {color: "white"}
                                            });

                                    }
                                    let nodos_dt = new vis.DataSet(nodos_final);
                                    let aristas_dt = new vis.DataSet(aristas);
                                    var container = document.getElementById("resultado");

                                    var data2 = {
                                        nodes: nodos_dt,
                                        edges: aristas_dt,
                                    };
                                    directionInput = "DU";
                                    var options = {
                                        edges: {
                                            smooth: {
                                                type: "cubicBezier",
                                                forceDirection: "horizontal",
                                                roundness: 0.4,
                                            },
                                        },
                                        layout: {
                                            hierarchical: {
                                                direction: directionInput,
                                                sortMethod: 'directed',
                                                nodeSpacing: 400, //separacion horizontal
                                                levelSeparation: 150, // separacion vertical
                                            },
                                        },
                                        physics: false,
                                    };
                                    var network = new vis.Network(container, data2, options);
                                    let loader = document.getElementById("loadingcontent");
                                    loader.style.display = "none";
                                } else {
                                    console.error('Error en la segunda solicitud: ' + xhr2.response);
                                }
                            };
                            xhr2.send(formDataCopy);
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                    });
            }
        })
}

async function processSomeOf(someOfArray, req_values, facultyName) {
    for (let j = 0; j < someOfArray.length; j++) {
      const subReq = someOfArray[j];
  
      if (subReq.Exam) {
        req_values.push(await getMaxRequirementLevel(facultyName, subReq.Exam));
      } else if (subReq.Coursed) {
        req_values.push(await getMaxRequirementLevel(facultyName, subReq.Coursed));
      } else if (subReq.SomeOf) {
        // Recursive call for nested SomeOf
        await processSomeOf(subReq.SomeOf, req_values, facultyName);
      }
    }
  }

async function getMaxRequirementLevel(facultyName, cu_id) {
    let cacheKey = `${facultyName}-${cu_id}`;
    let data;
    try {
      if (fetchCacheUC.has(cacheKey)) {
        data = fetchCacheUC.get(cacheKey);
      } else {
        const workspaceID = localStorage.getItem('selectedWorkspace');
        if (workspaceID == null) {
            alert("Invalid workspace");
            return 0;
        }
        let apiUrl = `http://127.0.0.1:8080/curricula_microservice/Faculty/ucs?faculty=${facultyName}&curricularUnit=${cu_id}&uuid=${workspaceID}`;
        const response = await fetch(apiUrl);
  
        if (!response.ok) {
          throw new Error('No se pudo obtener la respuesta de la API');
        }
    
        data = await response.json();
        fetchCacheUC.set(cacheKey, data);
      }
  
      if (!data['Requirement'] || data['Requirement'].length === 0)
          return 0;
      const req_values = [];
  
      for (let i = 0; i < data['Requirement'].length; i++) {
        const subRequirement = data['Requirement'][i];
        if (subRequirement.Exam) {
          req_values.push(await getMaxRequirementLevel(facultyName, subRequirement.Exam));
        } else if (subRequirement.Coursed) {
          req_values.push(await getMaxRequirementLevel(facultyName, subRequirement.Coursed));
        } else if (subRequirement.SomeOf) {
          await processSomeOf(subRequirement.SomeOf, req_values, facultyName);
        }
      }
      return req_values.length > 0 ? 1 + Math.max(...req_values) : 0;
    } catch (error) {
      console.error(error);
      return 0;
    }
}