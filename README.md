*** SYSC3110 Milestone 4 Monopoly Project README ***

Project Authors: Connor Marcus, Noah Hammoud, George Pantazopoulos and Vahid Foroughi.
Note about the deliverables: The source code was worked on in pairs over Discord, which is why the Github repository says only two of the authors made contributions. Thus, commits from Noah contains work done by both Noah and Vahid, and commits from Connor contains work done by both Connor and George.
------------------------------------
Project Deliverables:
The Milestone 4 Monopoly project consists of an executable JAR file with the source code and JUnit tests, a documentation and design file, and UML class and sequence diagrams. The source code is made up of the following 20 classes and interfaces: BoardPanel, Dice, FileSelector, GameLogPanel, MonopolyBoard, MonopolyController, MonopolyModel, MonopolyObserver, MonopolyView, OwnableProperty, Player, Property, PropertyGoToJail, PropertyFreeSpace, PropertyNameIcon, PropertyStreet, PropertyRailroad, PropertyTax, PropertyUtility, and SidePanel. The additional JUnit class tests the MonopolyModel to ensure that the code works as expected. The documentation and design file contains a description of the classes, an explanation of the design decisions, and a user manual. Lastly, the UML class and sequence diagrams provide a model of the problem domain and relevant runtime scenarios. 
------------------------------------
Changes Made in Milestone 4:
- Added the ability to save the Monopoly game and load a saved game
- When clicking the "x" button on the game window it will no longer quit immediately (it will provide the option to save)
- Added the ability to customize the property names on the Monopoly board
- Added the ability to customize the currency symbol in the game
- Added a file selector to import/export customization and save files
- Added the Jackson library in the project to parse JSON
------------------------------------
Steps to run the project executable:
1. Download the project zip file.
2. Extract the zip file.
3. Navigate to the directory containing the extracted zip file using the command line.
4. Execute the JAR file on the command line (e.g.: java -jar SYSC3110Project.jar).
Note 1: The steps assume that Java is already installed on the system and that the system can run jar files on the command line.
Note 2: More details about how to play the game can be found in the user manual.
Note 3: The Monopoly game can be customized with custom property names and currency. International customization options of the game can be imported with the JSON files found at: https://github.com/ConnorMarcus/MonopolySYSC3110/tree/main/out/production/SYSC3110Project/customization 
------------------------------------
Roadmap Ahead:
N/A
------------------------------------
Written by: Connor Marcus
