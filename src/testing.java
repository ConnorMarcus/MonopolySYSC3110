import java.util.HashMap;
import java.util.Map;

//class for testing purposes
public class testing {
    public static void main(String[] args) {
        Dice d = new Dice();
        CommandList c = new CommandList(new HashMap<>(Map.of("HELP", () -> System.out.println("List of Available Commands: roll, buy, pass, help"), "ROLL", d::rollDice)));
        c.executeCommand("roll");
    }
}
