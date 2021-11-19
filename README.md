*** SYSC3110 Milestone 3 Monopoly Project README ***

Project Authors: Connor Marcus, Noah Hammoud, George Pantazopoulos and Vahid Foroughi.
------------------------------------
Project Deliverables:
The Milestone 3 Monopoly project consists of an executable jar file with the source code and JUnit tests, a documentation and design file, and UML class and sequence diagrams. The source code is made up of the following 18 classes and interfaces: BoardPanel, Dice, GameLogPanel, MonopolyBoard, MonopolyController, MonopolyModel, MonopolyObserver, MonopolyView, OwnableProperty, Player, Property, PropertyGoToJail, PropertyFreeSpace, PropertyStreet, PropertyRailroad, PropertyTax, PropertyUtility, and SidePanel. The additional JUnit class tests the MonopolyModel to ensure that the code works as expected. The documentation and design file contains a description of the classes, an explanation of the design decisions, and a user manual. Lastly, the UML class and sequence diagrams provide a model of the problem domain and relevant runtime scenarios. 
------------------------------------
Changes Made in Milestone 3:
- Added houses and hotels to the the game.
- Added the logic for the Jail, Go To Jail, GO, Railroad, Utility, and Tax properties.
- Added the ability to play against AI opponents.
- Changed the GameLog class to support multiple lines of text and store the last ten events of the game.
- Rolling doubles now gives the player an extra turn.
- Rolling doubles three times in a row now sends the player directly to jail.
------------------------------------
Steps to run the project executable:
1. Download the project zip file.
2. Extract the zip file.
3. Navigate to the directory containing the extracted zip file using the command line.
4. Execute the jar file on the command line (e.g.: java -jar SYSC3110Project.jar).
**Note 1: The steps assume that Java is already installed on the system and that the system can run jar files on the command line.
**Note 2: More details about how to play the game can be found in the user manual.
------------------------------------
Roadmap Ahead:
The next step in the project is to add the ability to save and load the game. Also, customization options will added such as the ability to change the street names and currency. 
------------------------------------
Written by: Connor Marcus
