SERVER

How to run this software through Github codespaces:
=================================================================================================
1) Navigate to the depository url : https://github.com/LukeK2021/frameworks_and_languages_module
2) Click the green code button, a dropdown menu will appear. From there select the codespaces tab.
3) Select the small plus symbol.
4) A new window will appear in the browser, this is a cloud based ide containing files necessary to run the software.
5) Open a new terminal from the dropdown menu in the top right of the IDE.
6) In the terminal navigate to the server directory (cmd: "cd server").
7) Then run type "make build" into the terminal, this will build the container that will contain our server.
8) Once "make build" is complete, type "make run" and this will launch the server on http://localhost:8000
9) query the server will some curl commands. (see bottom of this document)

How to run on your local machine:
=================================================================================================
System Requirements:
First ensure that you have the latest version of python, gitbash installed and a text based ide of your choice (I am using Visual Studio code)and docker. Links to these can be found below:
Python: https://www.python.org/downloads/
Visual Studio code: https://code.visualstudio.com/download
Docker: https://www.docker.com/products/docker-desktop/
Gitbash: https://git-scm.com/download/win
(Note a system with at least 16gb of ram is reccommended, as the docker windows env is a memory hog.)

1) Clone this repository via the green code button, navigating to the local tab and selecting "Download zip", once downloaded extract this folder to an easy to access location (EG Desktop).
2) Open Visual studio code, in the file dropdown menu and select "open folder" navigating to the folder we created in step one.
3) Now you should see all the project files.
4) Open a new terminal from the terminal drop down menu in the upper left of the vs code window, then navigate to the server directory.(cmd: "cd server)
5) From here in the terminal window run the make build command (cmd: make build), this will the build a container that contains our server within it.
(Note: To run make commands in visual studio code the "make" extension in the vs code extensions library may be needed depending on the system. In addition the docker program needs to be open in order for the container to be built).
6) once the container build is complete we can begin hosting the server by invoking make run. (cmd:make run) This will begin running the server making it accessable at http://localhost:8000/
7) query the server will some curl commands. (see bottom of this document)


Example curl commands, execute these in the terminal to interact with server (See docs/images/webservice.md):
curl -X POST http://localhost:8000/item -H "Content-Type: application/json" -d '{"user_id": "user1234", "keywords": ["hammer", "nails", "tools"], "description": "A hammer and nails set. In canterbury", "lat": 51.2798438, "lon": 1.0830275}'
curl -X GET http://localhost:8000/items
curl -X GET http://localhost:8000/item/1

More on curl: https://linuxhint.com/curl_bash_examples/