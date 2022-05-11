# 9322-finGrp5

To run the game~

Open cmd or terminal.

Server Side First:
1. cd to the server package folder.
2. start orbd -ORBInitialHost localhost -ORBInitialPort 8888
3. start java UnscramblerServer -ORBInitialPort 8888 -ORBInitialHost localhost
4. there should be 2 terminals opened at this point.

Client Side Second:
1. cd to the client package folder.
2. java UnscramblerClient -ORBInitialHost localhost -ORBInitialPort 8888

Client Side Third (Python):
1. Edit Configurations to -ORBInitRef NameService=corbaname::localhost:8888

Notes:
-If it doesnt work try compiling them first "javac javaFilename.java"

======================================

Set up: 
1. Unzip Python36

Pycharm:
1. File > Settings > Project Interpreter > Add Interpreter > Find path to the newly unzipped python38 exe file.
2. Click Ok
3. Run the python file.