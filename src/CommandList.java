import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Class to encapsulate all the possible text based commands for the Monopoly game.
 *
 * @author Connor Marcus
 */
public class CommandList {
    private Map<String, Callable> commands;


    /**
     * Default constructor; initializes default Map of commands.
     *
     * @param commands the Map of commands
     */
    public CommandList(Map<String, Callable> commands) {
        this.commands = new HashMap<>();
        //Ensures that all command Strings are Uppercase
        for (String command : commands.keySet()) {
            this.commands.put(command.toUpperCase(), commands.get(command));
        }
    }


    /**
     * Executes a command from the CommandList given the string form of the command.
     *
     * @param commandString the command to execute
     * @return true if the command causes a player's turn to end, and false otherwise
     * @throws Exception the exception that may be raised by the Callable
     */
    public boolean executeCommand(String commandString) throws Exception {
        if (this.commands.containsKey(commandString.toUpperCase())) {
            return (boolean) this.commands.get(commandString.toUpperCase()).call(); //Callable must return boolean value
        }
        System.out.println("Invalid Command");
        return false;
    }

}
