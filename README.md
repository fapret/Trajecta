## Change readme language
[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/fapret/tmde-app-curricula/blob/main/README.md)
# About the Project
The **Trajecta** project originated from the Model-Driven Engineering Workshop course from the University of the Republic in Uruguay initially created by [@fapret](https://github.com/fapret) and [@sfreirelp](https://github.com/sfreirelp) using only Model-Based Systems Engineering (MBSE). And then continued by Santiago Diaz [@fapret](https://github.com/fapret) as research for the COAL group of the Institute of Computer Engineering from the same University adding its Process Mining (PM) capabilities, extending the app with new functionalities and ehnancing its usability. Developed using tools like Eclipse, Java, Python, Pm4py and Ecore. And licensed under GNU GPL v3.

The goal of the project is to create an application utilizing Model-Based Systems Engineering (MBSE) and Process Mining (PM) to assist students throughout their academic journey, especially by helping them understand which courses they can take, thus providing better guidance and offering a retrospective view of their academic career. And for academic managers, helping them understand, improve and enhance different aspects of the curriculas they work with.

During development, the project focused on how the University of the Republic (UdelaR) operates, specifically the Computer Engineering program, though it is expected to work for other degree programs as well due to the generalization aimed.

The project is currently running and can be used at: [https://Trajecta.fapret.com](https://Trajecta.fapret.com)

Execution requirements:

- Tomcat (Recommended to run WAR files)
- Java
- Ecore Library (Only for development, dependencies included in project buildpath)
- Python
- Graphviz (Optional, Required to make diagrams for Process Mining Modules)

The next libraries of Python are Required:
- Pm4Py
- Flask
- Flask cors

# International Process Mining Conference
This application was presented in ICPM 2025, and its corresponding Demo paper its available at documents folder.

# Building
We provide Github actions workflow that builds the .WAR for you and gets uploaded as artifact to your github repository so you can upload it to your tomcat server.
For the Front-End Website, it works as a static website, and we provide both github actions workflow to make a github pages, as an gitlab CI/CD to make a gitlab pages to run it. The website is located at `front` folder inside `proyecto` folder.

# Running
As mentioned early, you must upload the .WAR file to a tomcat server, this will provide the MDE functionalities.
On the other side, to run the Process Mining functionalities, you can run them using `python start_scripts.py` located at `PM_microservice` folder inside `proyecto` folder.
The next pip installs are required to run the Python code:
`pip install pm4py`
`pip install flask`
`pip install flask_cors`

For the graphs functionalities, Graphviz must be installed on the system.

# Project Demo Video
https://github.com/fapret/Trajecta/blob/main/demo.mp4

# Project Images
Complete curriculum view  
![image](https://github.com/user-attachments/assets/1c90a30a-b830-49ba-8e5b-8f1778bc46ff)

Courses a student can enroll in  
![image](https://github.com/user-attachments/assets/5e051c01-e122-4b3c-84eb-d84761eb7025)

Course data query  
![image](https://github.com/user-attachments/assets/edd120c7-81d8-482b-9458-8aaadfc5dce9)

Check if a course is available to take  
![image](https://github.com/user-attachments/assets/185f65b2-75b7-4c29-ba32-d59c3685ec5c)

# Credits
- Santiago Nicolás Díaz Conde <santiago.nicolas.diaz.conde [at] fing.edu.uy>
- Santiago Freire López <santiago.freire [at] fing.edu.uy>