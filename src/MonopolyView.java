import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.IntStream;

/**
 * MonopolyView class which handles the GUI.
 * @author Connor Marcus
 */
public class MonopolyView extends JFrame implements MonopolyObserver {
    private BoardPanel boardPanel;
    private SidePanel sidePanel;
    private GameLogPanel gameLogPanel;
    private MonopolyModel model;
    public static final String MULTIPLAYER_STRING = "Multiplayer";
    public static final String AI_STRING = "AI";

    /**
     * Constructor for MonopolyView class.
     * @param title String title of JFrame
     */
    public MonopolyView(String title) {
        super(title);
        String[] options = {MULTIPLAYER_STRING, AI_STRING};
        int choice = JOptionPane.showOptionDialog(null, "Select type of opponents:",
                "Game type",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice == -1) {
            System.exit(0);
        }
        int numberOfPlayers = this.gamePrompt(options[choice]);
        this.model = new MonopolyModel(options[choice], numberOfPlayers);
        this.model.addMonopolyObserver(this);
        this.boardPanel = new BoardPanel(this.model.getBoard(), this.model.getPlayerList());
        this.sidePanel = new SidePanel(this.model);
        this.gameLogPanel = new GameLogPanel();
        JPanel mainPanel = new JPanel(); //Container panel with flow layout
        mainPanel.add(boardPanel);
        mainPanel.add(sidePanel);
        this.add(this.gameLogPanel, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Option Dialog that Appears at th beginning of the game
     * @param gameType The type of game that is being player
     * @return The integer corresponding to the user's choice
     */
    private int gamePrompt(String gameType) {
        String[] options;
        String message;
        if (gameType.equals(MULTIPLAYER_STRING)) {
            options = new String[]{"4", "3", "2"};
            message = "How many players would like to play:";
        }
        else {
            options = new String[]{"3", "2", "1"};
            message = "How many AI players would you like to play against:";
        }
        int choice = JOptionPane.showOptionDialog(null, message,
                "Choose Players",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[2]);
        if (choice == -1) {
            System.exit(0);
        }
        if (gameType.equals(AI_STRING)) return Integer.parseInt(options[choice])+1;
        return Integer.parseInt(options[choice]);
    }

    /**
     * Handles what happens when a player takes their turn.
     * @param player The Player who took their turn
     * @param roll The list of the players roll
     * @param propertyLandedOn The property that the player landed on
     */
    @Override
    public void handleTakeTurn(Player player, int[] roll, Property propertyLandedOn) {
        int rollSum = IntStream.of(roll).sum();
        this.boardPanel.updatePlayerLabelPosition(player);
        this.boardPanel.updateDice(roll[0], roll[1]);
        String gameLogString = "Player " + player.getIdentifier() + " has rolled a " + rollSum + ". They are now on " + propertyLandedOn + ".";
        this.gameLogPanel.updateGameLog(gameLogString);
        this.sidePanel.enableButton("Pass", true);
        if (player.isPassingGo()) {
            gameLogString += " Player " + player.getIdentifier() + " has passed GO to collect $200. ";
        }
        if (propertyLandedOn instanceof PropertyUtility) ((PropertyUtility) propertyLandedOn).setDiceRoll(rollSum);
        gameLogString += " " + propertyLandedOn.landed(player);
        this.gameLogPanel.updateGameLog(gameLogString);
        if (propertyLandedOn.getName().equals("Go To Jail")) this.boardPanel.updatePlayerLabelPosition(player);
    }

    /**
     * Handles what happens when a player passes their turn.
     * @param player The Player who passed their turn.
     */
    @Override
    public void handlePassTurn(Player player) {
        this.gameLogPanel.updateGameLog("Player " + player.getIdentifier() + " has finished their turn.");
        this.sidePanel.enableButton("Roll", true);
    }

    /**
     * Handles what happens when a player becomes bankrupt.
     * @param player The Player who has gone bankrupt
     */
    @Override
    public void handleBankrupt(Player player) {
        this.gameLogPanel.updateGameLog("Player " + player.getIdentifier() + " has gone bankrupt! They are out of the game.");
        this.boardPanel.removePlayerLabel(player);
        this.sidePanel.removePlayerInfo(player);
        this.sidePanel.enableButton("Roll", true);
        this.sidePanel.enableButton("Pass", false);
    }

    /**
     * Handles what happens when a player wins.
     * @param player The Player who has won
     */
    @Override
    public void handleWinner(Player player) {
        JOptionPane.showMessageDialog(null, "Player " + player.getIdentifier() + " has won the game!");
        System.exit(0);
    }

    /**
     * Handles updates related to the player's information
     * @param playerList The list of players in the game
     */
    @Override
    public void handlePlayerUpdate(List<Player> playerList) {
        playerList.forEach((player) -> this.sidePanel.updatePlayerInfo(player));
    }

    /**
     * Handles the start of a jailed player's turn
     * @param player The player who is in jail
     */
    @Override
    public void handleJailChoice(Player player) {
        String jailString = "";
        if (player.getMoney() >= 50) {
            String[] options = {"no", "yes"};
            int choice = -1;
            if (!player.getIsAI()) {
                choice=JOptionPane.showOptionDialog(null, "Would you like to pay $50 to get out of jail this turn?", "Player " + player.getIdentifier(),
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
                }
            else if (player.getTimeInJail()==2) choice=1;

            if (choice == 1) {
                player.setJailed(false);
                jailString = "Player " + player.getIdentifier() + " has spent $50 to get out of jail!";
            }
            else {
                jailString = "Player " + player.getIdentifier() + " has not paid the fine to get out of jail!";
            }
            }
        else {
            jailString = "Player " + player.getIdentifier() + " cannot currently afford to pay the fine of $50, they must roll doubles!";
        }
        if (player.getIsAI()) {
            //Add slight delay if AI player so previous log is not erased immediately
            String finalJailString = jailString;
            Timer jailLogTimer = new Timer(1000, (e) -> this.gameLogPanel.updateGameLog(finalJailString));
            jailLogTimer.setRepeats(false);
            jailLogTimer.start();
        }
        else {
            this.gameLogPanel.updateGameLog(jailString);
        }
    }

    /**
     * Handles what happens when a player spends three turns in jail.
     * @param player The player who is in jail
     */
    @Override
    public void handleThreeTurnsInJail(Player player) {
        this.gameLogPanel.updateGameLog("Player " + player.getIdentifier() + " has spent three turns in jail and paid the $50 fine!");
    }

    /**
     * Handles what happens when a player fails to get out of jail
     * @param player The player that is stuck in jail
     */
    @Override
    public void handleStuckInJail(Player player, int[] roll) {
        this.boardPanel.updateDice(roll[0], roll[1]);
        this.sidePanel.enableButton("Pass", true);
        this.gameLogPanel.updateGameLog("Player " + player.getIdentifier() + " did not roll doubles, they are stuck in jail!");
    }

    /**
     * Handles AI players turn, Rolls and Passes turn.
     * @param player The AI player.
     */
    @Override
    public void handleAITurn(Player player) {
        this.sidePanel.enableButton("Roll", false); //disable button so cannot be clicked by user
        Timer rollTimer = new Timer(2000, (e) -> {
            this.sidePanel.enableButton("Roll", true);
            this.sidePanel.clickButton("Roll");
            this.sidePanel.enableButton("Pass", false); //disable button so cannot be clicked by user
            Timer passTimer = new Timer(3000, (e2) -> {
                this.sidePanel.enableButton("Pass", true);
                this.sidePanel.clickButton("Pass");
            });
            passTimer.setRepeats(false);
            passTimer.start();
        });
        rollTimer.setRepeats(false);
        rollTimer.start();
    }

    /**
     * Main method to run game.
     * @param args String[] input for main method
     */
    public static void main(String[] args) {
        MonopolyView mv = new MonopolyView("Monopoly");
    }
}
