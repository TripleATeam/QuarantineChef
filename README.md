"# QuarantineChef" 



DATABASE SETUP:
The Neo4j server can either be run locally or on a remote server. The following instructions will be regarding a server hosted by the Neo4j sandbox. 

Step 1:
Go to https://sandbox.neo4j.com/login and login with any account.

Step 2:
In the top-left corner, click the button "New Project". When the options show, select "Blank Project". This will create a Neo4j server with minimal restrictions and no presets.

Step 3:
The sandbox is created.

Step 4:
Fortunately, Cypher (Neo4j) queries can be run from Java! In order to make the website work correctly, you must configure your server to the driver already installed in the source code of the project.

To do so, select the drop-down arrow on the right end of the sandbox. Select "Connect via drivers" from the given options. 

Step 5:
Select Java. 

Step 6:
In the doQuery method, replace line 3 of the method (the one that starts with "Driver driver...") with Line 14 from the given code presented to you. This will make it so that the queries are now targetting your sandbox.
