/*
    tmde-app-curricula is a software that helps students build their curricula and
    see what curricular units they can register to, and track how their career was
    or will be.
    Copyright (C) 2023  Santiago Nicolás Díaz Conde, Santiago Freire López

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
const fetchCacheUC = new Map();

function addToSelect(select, idelement){
  let option = document.createElement('option');
  option.setAttribute('value', idelement);
  option.appendChild(document.createTextNode(idelement));
  select.appendChild(option);
}

function addToSelectWithIdName(select, idelement){
  let option = document.createElement('option');
  option.setAttribute('value', idelement.id);
  option.appendChild(document.createTextNode(idelement.name));
  select.appendChild(option);
}

function getFaculties(select) {
  const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty`;
  fetch(url)
  .then(response => response.json())
  .then(data => {
	for (var i = 0; i < data.length; i++){
		addToSelect(select, data[i]);
	}
  })
  .catch(error => {
      console.error("Error al consultar la API:", error);
  });
}

function getUCS(select, faculty){
	const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/ucs?faculty=${faculty}`;
	fetch(url)
  .then(response => response.json())
  .then(data => {
	for (var i = 0; i < data.length; i++){
		addToSelect(select, data[i]);
	}
  })
  .catch(error => {
      console.error("Error al consultar la API:", error);
  });
}

function getCareers(select, faculty){
  const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty?faculty=${faculty}`;
  fetch(url)
  .then(response => response.json())
  .then(data => {
	for (var i = 0; i < data["Careers"].length; i++){
		addToSelect(select, data["Careers"][i]);
	}
  })
  .catch(error => {
      console.error("Error al consultar la API:", error);
  });
}

function getPlans(select, faculty, career){
  const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/Carrera?faculty=${faculty}&career=${career}`;
  fetch(url)
  .then(response => response.json())
  .then(data => {
	for (var i = 0; i < data["Plans"].length; i++){
		addToSelect(select, data["Plans"][i]);
	}
  })
  .catch(error => {
      console.error("Error al consultar la API:", error);
  });
}

function getPlanUCS(select, faculty, career, plan){
	const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/Carrera/Plan/ucs?faculty=${faculty}&career=${career}&plan=${plan}&withName=True`;
	fetch(url)
	.then(response => response.json())
	.then(data => {
		for (var i = 0; i < data.length; i++){
			addToSelectWithIdName(select, data[i]);
		}
    
    /*
    Test
    */
    const element = document.getElementById('ucs');
    const choices = new Choices(element, {
        searchEnabled: true
    });
	})
	.catch(error => {
      console.error("Error al consultar la API:", error);
  	});
}

function getEvaluations(select, typeBoolean, UC, faculty){
	const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/ucs?faculty=${faculty}&curricularUnit=${UC}`;
	fetch(url)
	.then(response => response.json())
	.then(data => {
		if(typeBoolean){
			var evaluations = data.ExamEvaluation;
			for (var i = 0; i < evaluations.length; i++){
				addToSelect(select, evaluations[i]);
			}
		} else {
			for (var i = 0; i < data.Course.length; i++){
				for(var j = 0; j < data.Course[i].CourseEvaluation.length; j++){
					addToSelect(select, data.Course[i].CourseEvaluation[j]);
				}
			}
		}
	})
	.catch(error => {
      console.error("Error al consultar la API:", error);
  	});
}

function getMaterias(select, faculty, career, plan){
	const url = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/Carrera/Plan/Subjects?faculty=${faculty}&career=${career}&plan=${plan}`;
	fetch(url)
	.then(response => response.json())
	.then(data => {
		for(var i = 0; i < data.length; i++){
			addToSelect(select, data[i]);
		}
	})
	.catch(error => {
      console.error("Error al consultar la API:", error);
  	});
}

function findKeyByValue(obj, value) {
    for (let key in obj) {
      if (obj[key]['materia_id'] === value) {
        return obj[key]['id']; // Return the key if the value is found
      }
    }
    return null; // Return null if the value is not found
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
      let apiUrl = `https://tmde-api.fapret.com:8443/curricula_microservice/Faculty/ucs?faculty=${facultyName}&curricularUnit=${cu_id}`;
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