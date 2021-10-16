import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//class for testing purposes
public class testing {
    public static void main(String[] args) throws Exception {
        Dice d = new Dice(2);
        //CommandList c = new CommandList(new HashMap<>(Map.of("HELP", () -> System.out.println("List of Available Commands: roll, buy, pass, help"), "ROLL", d::rollDice)));
        //c.executeCommand("roll");
        Game g = new Game();
        g.Play();
    }
}
