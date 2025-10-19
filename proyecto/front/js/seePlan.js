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
function consultarPlan() {
	const allFacultiesSelect = document.getElementById("facultades");
	const faculty = allFacultiesSelect.value;
	const allCareersSelect = document.getElementById("carreras");
	const career = allCareersSelect.value;
	const allPlansSelect = document.getElementById("planes");
	const plan = allPlansSelect.value;
  const workspaceID = localStorage.getItem('selectedWorkspace');
  if (workspaceID == null) {
      alert("Please select a workspace");
      return;
  }

	const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/Carrera/Plan/ucs?faculty=${faculty}&career=${career}&plan=${plan}&valid_flag=true&uuid=${workspaceID}`;
    var nodos = [];
    var nodos_0 = [];
    var nodos_process = {};
    var nodos_final = [];
    var aristas = [];
    
    function processSomeOfGraph(reqFather, aristas, materias, nodos_0){
      for (let j = 1; j < (reqFather).length; j++) {
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

    fetch(url)
            .then(response => response.json())
            .then(materias => {

			const facultyName = document.getElementById("facultades").value;
			
              for (let i = 0; i < materias.length; i++) {
                nodos_0.push({id: i + 1, materia_id: materias[i]});
              }

              for (let i = 0; i < materias.length; i++) {
                let apiUrl = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/ucs?faculty=${faculty}&curricularUnit=` + materias[i];
                fetch(apiUrl)
                        .then(async response => {
                          if (!response.ok) {
                            throw new Error('No se pudo obtener la respuesta de la API');
                          }
                          return response.json();
                          //return await response.json(); // Parsear la respuesta JSON
                        })
                        .then(async data => {
                          const result = await getMaxRequirementLevel(facultyName, materias[i]);
                          nodos.push({
                            id: i + 1,
                            materia_id: materias[i],
                            materia_name: data['Name'],
                            level: result,
                            label: data['Name'],
                            shape: "box",
                            color: "#F28C28",
                            font: {color: "white"}
                          });
                          nodos_process[materias[i]] = null;

                          if (data.Requirement && (data.Requirement).length > 0) {
                            //todo: si es un someof, tengo que ver cuales de las N se cumple, y mostrar solo las aristas de ella. por ejemplo, tprog y sus 2 vias de cursado
                            if (Object.keys(data.Requirement[0])[0] === "SomeOf") {
                              processSomeOfGraph(data.Requirement[0]['SomeOf'], aristas, materias[i], nodos_0);
                            } else {
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
                          let nodos_dt = new vis.DataSet(nodos);
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
                        })

                        .catch(error => {
                          console.error('Error:', error);
                        });
              }

            });
}