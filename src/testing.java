import java.util.HashMap;
import java.util.Map;

public class testing {
    public static void main(String[] args) {
        System.out.println("testing");
        System.out.println("testing vahid");
        CommandList c = new CommandList(new HashMap<>(Map.of("HELP", () -> System.out.println("List of Available Commands: roll, buy, pass, help"))));
        c.executeCommand("help");
    }
}
