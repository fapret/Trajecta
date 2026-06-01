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
function displayUCStats(){
    const loader = document.getElementById("loadingcontent");
    loader.style.display = "flex";
    const allUCSelect = document.getElementById("ucs");
    const uc = allUCSelect.value;
    const allDiscoveriesSelect = document.getElementById("discoveries");
    const uuid = allDiscoveriesSelect.value;
    let baseUrl;

    if (window.location.protocol === "file:") {
        // Opened directly as file:///...
        baseUrl = "http://127.0.0.1:9003/custats";
    }
    else if (
        window.location.hostname === "localhost" ||
        window.location.hostname === "127.0.0.1"
    ) {
        // Running from local web server
        baseUrl = "http://127.0.0.1:9003/custats";
    }
    else {
        // Running from trajecta-pm.fapret.com or any other web host
        baseUrl = "https://trajecta-pm.fapret.com/custats";
    }
    const url = `${baseUrl}/${uuid}/${uc}`;
    fetch(url)
    .then(response => response.json())
    .then(data => {
        loader.style.display = "none";
        const resultadoDiv = document.getElementById("resultado");
        resultadoDiv.innerHTML = `
            <label class="info-label" data-lang="discoverUUID">UUID de Descubrimiento:</label>
            <label class="info-value">${data.uuid}</label>
            <br>
            <label class="info-label" data-lang="uc">Unidad Curricular:</label>
            <label class="info-value">${data.uc}</label>
            <br>
            <br>
            <label class="info-value">${data.didCourseAmount}</label>
            <label class="info-label" data-lang="studentsCoursedUC">Estudiantes cursaron la Unidad Curricular</label>
            <br>
            <label class="info-value">${data.didExamAmount}</label>
            <label class="info-label" data-lang="studentsDidExam">Estudiantes dieron un examen</label>
            <br>
            <label class="info-value">${data.exoneratedAmount}</label>
            <label class="info-label" data-lang="studentsExonerated">Estudiantes exoneraron</label>
            <br>
            <label class="info-value">${data.approvedAmount}</label>
            <label class="info-label" data-lang="studentsHaveCourse">Estudiantes tienen el curso o exoneraron</label>
        `;

        const savedLang = localStorage.getItem("lang") || "es";
        translatePage(savedLang);

        //Enrollments Graph
        const graph0 = document.getElementById("graph0");
        const enrollmentsArray = [['Course', 'Enrollments']];
        for (const [course, count] of Object.entries(data.enrollments)) {
            enrollmentsArray.push([course, count]);
        }
        const data0 = google.visualization.arrayToDataTable(enrollmentsArray);
        const graph0options = {
            title: 'Enrollments',
            hAxis: {title: 'Course Edition'},
            vAxis: {title: 'Amount of enrollments'},
            legend: 'none'
        };
        const chart0 = new google.visualization.ScatterChart(graph0);
        chart0.draw(data0, graph0options);

        //courseExonerations Graph
        const graph1 = document.getElementById("graph1");
        const courseExonerationsArray = [['Course', 'Exonerations']];
        for (const [course, count] of Object.entries(data.courseExonerations)) {
            courseExonerationsArray.push([course, count]);
        }
        const data1 = google.visualization.arrayToDataTable(courseExonerationsArray);
        const graph1options = {
            title: 'Course Exonerations',
            hAxis: {title: 'Course Edition'},
            vAxis: {title: 'Amount of exonerations'},
            legend: 'none'
        };
        const chart1 = new google.visualization.ScatterChart(graph1);
        chart1.draw(data1, graph1options);

        //courseApproves Graph
        const graph2 = document.getElementById("graph2");
        const courseApprovedArray = [['Course', 'Approved']];
        for (const [course, count] of Object.entries(data.courseApproved)) {
            courseApprovedArray.push([course, count]);
        }
        const data2 = google.visualization.arrayToDataTable(courseApprovedArray);
        const graph2options = {
            title: 'Course approvals',
            hAxis: {title: 'Course Edition'},
            vAxis: {title: 'Amount of approved'},
            legend: 'none'
        };
        const chart2 = new google.visualization.ScatterChart(graph2);
        chart2.draw(data2, graph2options);

        //examAttemptsPerStudent Graph
        const graph3 = document.getElementById("graph3");
        const examAttemptsPerStudentArray = [['Amount', 'Students']];
        for (const [amount, count] of Object.entries(data.examAttemptsPerStudent)) {
            examAttemptsPerStudentArray.push([amount, count]);
        }
        const data3 = google.visualization.arrayToDataTable(examAttemptsPerStudentArray);
        const graph3options = {
            title: 'Exam attempts',
            hAxis: {title: 'Amount of exams done'},
            vAxis: {title: 'Amount of students'},
            legend: 'none'
        };
        const chart3 = new google.visualization.ColumnChart(graph3);
        chart3.draw(data3, graph3options);

        //courseAttemptsPerStudent Graph
        const graph4 = document.getElementById("graph4");
        const courseAttemptsPerStudentArray = [['Amount', 'Students']];
        for (const [amount, count] of Object.entries(data.courseAttemptsPerStudent)) {
            courseAttemptsPerStudentArray.push([amount, count]);
        }
        const data4 = google.visualization.arrayToDataTable(courseAttemptsPerStudentArray);
        const graph4options = {
            title: 'Course attempts'
        };
        const chart4 = new google.visualization.PieChart(graph4);
        chart4.draw(data4, graph4options);

        //approvedExamNotes Graph
        const graph5 = document.getElementById("graph5");
        const approvedExamNotesArray = [['Note', 'Amount']];
        for (const [note, count] of Object.entries(data.approvedExamNotes)) {
            approvedExamNotesArray.push([note, count]);
        }
        const data5 = google.visualization.arrayToDataTable(approvedExamNotesArray);
        const graph5options = {
            title: 'Approved Exam notes'
        };
        const chart5 = new google.visualization.AreaChart(graph5);
        chart5.draw(data5, graph5options);

        //approvedCourseNotes Graph
        const graph6 = document.getElementById("graph6");
        const approvedCourseNotesArray = [['Note', 'Amount']];
        for (const [note, count] of Object.entries(data.approvedCourseNotes)) {
            approvedCourseNotesArray.push([note, count]);
        }
        const data6 = google.visualization.arrayToDataTable(approvedCourseNotesArray);
        const graph6options = {
            title: 'Approved Course notes'
        };
        const chart6 = new google.visualization.AreaChart(graph6);
        chart6.draw(data6, graph6options);

        //ExamExonerationRatio Graph
        const graph7 = document.getElementById("graph7");
        const ExamExonerationRatioArray = [['Exam', 'Ratio']];
        for (const [exam, ratio] of Object.entries(data.ExamExonerationRatio)) {
            ExamExonerationRatioArray.push([exam, ratio]);
        }
        const data7 = google.visualization.arrayToDataTable(ExamExonerationRatioArray);
        const graph7options = {
            title: 'Ratio of exam exoneration'
        };
        const chart7 = new google.visualization.BarChart(graph7);
        chart7.draw(data7, graph7options);

        //CourseExonerationRatio Graph
        const graph8 = document.getElementById("graph8");
        const CourseExonerationRatioArray = [['Course', 'Ratio']];
        for (const [course, ratio] of Object.entries(data.courseExonerationRatio)) {
            CourseExonerationRatioArray.push([course, ratio]);
        }
        const data8 = google.visualization.arrayToDataTable(CourseExonerationRatioArray);
        const graph8options = {
            title: 'Ratio of course exoneration'
        };
        const chart8 = new google.visualization.LineChart(graph8);
        chart8.draw(data8, graph8options);

        //CourseApprovalRatio Graph
        const graph9 = document.getElementById("graph9");
        const CourseApprovalRatioArray = [['Course', 'Ratio']];
        for (const [course, ratio] of Object.entries(data.courseApprovalRatio)) {
            CourseApprovalRatioArray.push([course, ratio]);
        }
        const data9 = google.visualization.arrayToDataTable(CourseApprovalRatioArray);
        const graph9options = {
            title: 'Ratio of course approval'
        };
        const chart9 = new google.visualization.LineChart(graph9);
        chart9.draw(data9, graph9options);

    })
    .catch(error => {
        console.error("Error al consultar la API:", error);
        const resultadoDiv = document.getElementById("resultado");
        resultadoDiv.innerHTML = "Error al consultar la API: "+error;
    });
}