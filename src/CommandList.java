//Written by: Connor Marcus

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Class to encapsulate all the possible text based commands for the Monopoly game
 */
public class CommandList {
    private Map<String, Callable> commands;

    /**
     * Default constructor; initializes default Map of commands
     *
     * @param commands the Map of commands
     */
    public CommandList(Map<String, Callable> commands) {
        this.commands = commands;
    }


    public boolean executeCommand(String commandString) throws Exception {
        if (this.commands.containsKey(commandString.toUpperCase())) {
            return (boolean) this.commands.get(commandString.toUpperCase()).call();
        }
        return false;
    }

}
