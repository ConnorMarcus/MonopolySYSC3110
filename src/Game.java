import java.util.*;

public class Game {
    private MonopolyBoard board;
    private List<Player> playerList;
    private Dice dice;
    private int turn;
    private CommandList commands;

    public Game() {
        this.board = new MonopolyBoard();
        this.playerList = new ArrayList<>();
        this.dice = new Dice(2);
        this.commands = new CommandList(new HashMap<>(Map.of("HELP", this::PrintCommands, "ROLL", this::TakeTurn, "PASS", this::PassTurn, "INFO", this::Info, "QUIT", this::Quit)));
    }

    public void Play() throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome To Monopoly!");
        int numPlayers = 0;
        while(numPlayers <= 1) {
            System.out.println("How many players would like to play: ");
            try {
                numPlayers = Integer.parseInt(in.nextLine());
            }
            catch (Exception e) {
                System.out.println("Invalid Input!");
                continue;
            }
            if(numPlayers <= 1) {
                System.out.println("The number of players must be greater than one!");
            }
        }
        CreatePlayers(numPlayers);

        turn = 0;
        //Game Loop
        while (true) {
            PrintTurn(((this.turn) % this.playerList.size()) + 1);
            System.out.println("Please choose what you want to do (type help for all commands): ");
            String command = in.nextLine();
            while (!commands.executeCommand(command)) {
                System.out.println("Please choose what you want to do (type help for all commands): ");
                command = in.nextLine();
            }
            turn = (turn+1) % (numPlayers);
        }
    }

    private void CreatePlayers(int numPlayers) {
        for (int i = 0; i<numPlayers; i++) {
            playerList.add(new Player(String.valueOf(i+1)));
        }
    }

    private void PrintTurn(int playerNumber) {
        System.out.println("It is Player " + playerNumber + "'s turn.");
    }

    private boolean PrintCommands() {
        System.out.println("List of all Available Commands: roll, pass, info, help, quit");
        System.out.println("roll: rolls the dice when it is your turn");
        System.out.println("pass: pass your turn once you have rolled the dice");
        System.out.println("info: get information about all the players, their position, and their properties");
        System.out.println("help: get information about all the available commands");
        System.out.println("quit: quit the game (all progress will be lost)");
        return false;
    }

    private Boolean Quit() {
        System.out.println("Thank you for playing!");
        System.exit(0);
        return true; //Must have a return value to use function as a Callable
    }

    private boolean TakeTurn() {
        Player turnPlayer = this.playerList.get(this.turn);
        if (turnPlayer.isTookTurn()) {
            System.out.println("You have already taken your turn!");
        }
        else {
            int roll = dice.rollDice();
            System.out.println("You have rolled a " + roll + "!");
            int newPosition = turnPlayer.getPosition() + roll;
            turnPlayer.setPosition(newPosition);
            Property propertyLandedOn = this.board.getProperty(newPosition);
            System.out.println("You have landed on " + propertyLandedOn + "!");
            Player owner = ownedProperty(propertyLandedOn);
            if (owner != null) {
                propertyLandedOn.Landed(owner, turnPlayer);
                //If player is bankrupt after paying rent
                if (turnPlayer.getIsBankrupt()) bankrupt(turnPlayer);
            }
            else {
                buyHandler(propertyLandedOn);
            }
            turnPlayer.setTookTurn(true);
        }
        return false;
    }

    private boolean PassTurn() {
        Player turnPlayer = this.playerList.get(this.turn);
        if (turnPlayer.isTookTurn()) {
            System.out.println("Player " + turnPlayer.getIdentifier() + " has finished their turn!");
            turnPlayer.setTookTurn(false);
            return true;
        }
        else {
            System.out.println("You must roll before you can finish your turn!");
            return false;
        }
    }

    private boolean Info() {
        for (Player player : playerList) {
            System.out.println("\nPlayer " + player.getIdentifier() + ":\nCurrent position: " + board.getProperty(player.getPosition()) + "\nMoney: " + player.getMoney() + "\nProperties: " +  player.getPropertyString());
        }
        return false;
    }

    private void buyHandler(Property property) {
        String answer = "";
        Scanner in = new Scanner(System.in);
        System.out.println("Would you like to buy " + property.getName() + " for $" + property.getPrice() + "? (y/n)");
        answer = in.nextLine().toLowerCase();
        while (!(answer.equals("y")) && !(answer.equals("n"))) {
            System.out.println("Please enter y or n");
            System.out.println("Would you like to buy " + property.getName() + " for $" + property.getPrice() + "? (y/n)");
            answer = in.nextLine().toLowerCase();
        }
        if (answer.equals("y")) {
            this.Buy(property);
        }
    }

    private void Buy(Property property) {
        Player turnPlayer = this.playerList.get(turn);
        turnPlayer.purchaseProperty(property, property.getPrice());
    }

    //Checks if any player owns the property
    private Player ownedProperty(Property property) {
        for (Player owner: this.playerList) {
            if (owner.ownsProperty(property)) return owner;
        }
        return null;
    }

    private void bankrupt(Player p) {
        System.out.println("You can not pay your debts and have gone bankrupt thanks for playing!");
        this.playerList.remove(p);

        if (this.playerList.size() == 1) {
            System.out.println("Player " + this.playerList.get(0).getIdentifier() + " wins!");
            System.exit(0);
        }
    }


    //Not needed until Milestone 3
    private boolean Upgrade() {
        Player turnPlayer = this.playerList.get(turn);
        Set<String> groups = turnPlayer.getPropertyGroups(this.board);
        if (groups.size() == 0) {
            System.out.println("You currently have no property sets so you cannot buy houses/hotels!");
        }
        else {
            Scanner in = new Scanner(System.in);
            System.out.println("What property would you like to buy a house/hotel on (type a number):");
            int count = 0;
            Map<Integer, PropertyStreet>  propertyMap = new HashMap<>();
            for (String colour : groups) {
                for (PropertyStreet p : this.board.getPropertyGroup(colour)) {
                    count += 1;
                    propertyMap.put(count, p);
                    System.out.println(count + " - " + p);
                }
            }

            int choice = 0;
            while (choice <= 0 || choice > propertyMap.size()) {
                try {
                    choice = Integer.parseInt(in.nextLine());
                }
                catch (Exception e) {
                    System.out.println("Invalid Input!");
                    continue;
                }
                if (choice <= 0 || choice > propertyMap.size()) {
                    System.out.println("That is not a valid choice!");
                }
            }

            PropertyStreet selectedProperty = propertyMap.get(choice);
            //selectedProperty.buyHouse();
            //System.out.println("There are now currently " + selectedProperty.getNumHouses() + " on property " + selectedProperty.getName());
        }
        return false;
    }

}
