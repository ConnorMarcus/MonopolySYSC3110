*** SYSC3110 Milestone 2 Monopoly Project README ***

Project Authors: Connor Marcus, Noah Hammoud, George Pantazopoulos and Vahid Foroughi.
------------------------------------
Project Deliverables:
The Milestone 2 Monopoly project consists of an executable jar file with the source code and JUnit tests, a documentation and design file, and UML class and sequence diagrams. The source code is made up of the following 13 classes and interfaces: BoardPanel, Dice, GameLogPanel, MonopolyBoard, MonopolyController, MonopolyModel, MonopolyObserver, MonopolyView, Player, Property, PropertyFreeSpace, PropertyStreet, and SidePanel. The additional JUnit class tests the MonopolyModel to ensure that the code works as expected. The documentation and design file contains a description of the classes, an explanation of the design decisions, and a user manual. Lastly, the UML class and sequence diagrams provide a model of the problem domain and relevant runtime scenarios. 
------------------------------------
Changes Made in Milestone 2:
- Removed some of the classes, such as CommandList, that were only used for the text-based UI.
- Added some classes to support the swing-based GUI.
- Changed the colour of the first property group from brown to purple to be consistent with the GUI. 
- Added the Utility and Free Space properties (which replace Chance/Community Chest) to the game board. These properties both act as free spaces in this milestone.
- Added a directory containing all the images used in the GUI.
- Added a String attribute in the Player class that corresponds to file path of their player icon.
------------------------------------
Steps to run the project executable:
1. Download the project zip file.
2. Extract the zip file.
3. Navigate to the directory containing the extracted zip file using the command line.
4. Execute the jar file on the command line (e.g.: java -jar SYSC3110Project.jar).
Note 1: The steps assume that Java is already installed on the system and that the system can run jar files on the command line.
Note 2: More details about how to play the game can be found in the user manual.
------------------------------------
Roadmap Ahead:
The next step in the project is to add the logic for the remaining properties such as jail, the railroads, and the utilities. The option to play against "AI" players will also be added in the next iteration. 
------------------------------------
Written by: Connor Marcus
