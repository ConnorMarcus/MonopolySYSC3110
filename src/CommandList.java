//Written by: Connor Marcus

import java.util.Map;

/**
 * Class to encapsulate all the possible text based commands for the Monopoly game
 */
public class CommandList {
    private Map<String, Runnable> commands;

    /**
     * Default constructor; initializes default Map of commands
     **/
    public CommandList(Map<String, Runnable> commands) {
        this.commands = commands;
    }


    public void executeCommand(String commandString) {
        if (this.commands.containsKey(commandString.toUpperCase())) {
            this.commands.get(commandString.toUpperCase()).run();
        }
        else {
            System.out.println("Invalid Command!");
        }
    }

}
