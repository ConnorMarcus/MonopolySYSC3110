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

    /**
     * Constructor for MonopolyView class.
     * @param title String title of JFrame
     */
    public MonopolyView(String title) {
        super(title);
        String[] options = {"4", "3", "2"};
        int choice = JOptionPane.showOptionDialog(null, "How many players would like to play:",
                "Choose Players",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[2]);
        if (choice == -1) {
            System.exit(0);
        }
        this.model = new MonopolyModel(Integer.parseInt(options[choice]));
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
        this.gameLogPanel.updateGameLog("Player " + player.getIdentifier() + " has rolled a " + rollSum + ". They are now on " + propertyLandedOn + ".");
        this.sidePanel.enableButton("Pass", true);
        if (player.isPassingGo()) {
            JOptionPane.showMessageDialog(null, "Player " + player.getIdentifier() + " has received $200 for passing GO!");
            player.addMoney(200);
            handlePlayerUpdate(model.getPlayerList());
        }
        if (propertyLandedOn instanceof PropertyUtility) ((PropertyUtility) propertyLandedOn).setDiceRoll(rollSum);
        propertyLandedOn.landed(player);
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
    public void handleJailedPlayer(Player player) {
        player.incrementTimeInJail();
        if (player.getTimeInJail() == 3) {
            JOptionPane.showMessageDialog(null, "Player " + player.getIdentifier() + " has spent 3 turns in jail! They must pay the $50 fine to get out!");
            player.payMoney(50);
            player.setJailed(false);
            player.resetTimeInJail();
            if (player.getMoney() < 0) {
                model.getPlayerList().remove(player);
                handleBankrupt(player);
                if (model.getPlayerList().size() == 1) handleWinner(model.getPlayerList().get(0));
            }
            handlePlayerUpdate(model.getPlayerList());
        }
        else {
            if (player.getMoney() >= 50) {
                String[] options = {"no", "yes"};
                int choice = JOptionPane.showOptionDialog(null, "Would you like to pay $50 to get out of jail this turn?", "Player " + player.getIdentifier(),
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
                if (choice == 1) {
                    player.payMoney(50);
                    player.setJailed(false);
                    player.resetTimeInJail();
                    handlePlayerUpdate(model.getPlayerList());
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Player " + player.getIdentifier() + " cannot currently afford to pay the fine of $50, they must roll doubles!");
            }
        }
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
     * Main method to run game.
     * @param args String[] input for main method
     */
    public static void main(String[] args) {
        MonopolyView mv = new MonopolyView("Monopoly");
    }
}
