import java.util.ArrayList;
import java.util.List;

/**
 * The model part of the MVC; handles the game logic.
 * @author Connor Marcus
 */
public class MonopolyModel {
    private MonopolyBoard board;
    private List<Player> playerList;
    private Dice dice;
    private int turn;
    private List<MonopolyObserver> observers;

    /**
     * Constructor of the class; initializes the class attributes.
     * @param numPlayers The number of players playing the Monopoly game
     */
    public MonopolyModel(int numPlayers) {
        this.board = new MonopolyBoard();
        this.dice = new Dice(2);
        this.turn = 0;
        this.observers = new ArrayList<>();
        this.playerList = new ArrayList<>();
        for (int i=0; i<numPlayers; i++) {
            this.playerList.add(new Player(String.valueOf(i+1), String.format("images/player%s.png", i+1)));
        }
    }

    /**
     * Gets the MonopolyBoard object used in the game.
     * @return The MonopolyBoard object used in the game
     */
    public MonopolyBoard getBoard() {
        return this.board;
    }

    /**
     * Adds a new MonopolyObserver to the list of observers.
     * @param observer The new MonopolyObserver
     */
    public void addMonopolyObserver(MonopolyObserver observer) {
        if (observer != null && !this.observers.contains(observer)) {
            this.observers.add(observer);
        }
        else {
            throw new IllegalArgumentException("MonopolyObserver cannot be null!");
        }
    }

    /**
     * Takes the turn of the turn player.
     */
    public void takeTurn() {
        Player turnPlayer = this.playerList.get(this.turn);
        int[] roll = dice.rollDice();
        int rollSum = 0;
        for (int i = 0; i < roll.length; i++) {
            rollSum += roll[i];
        }
        int newPosition = (turnPlayer.getPosition() + rollSum) % this.board.getNumProperties();
        turnPlayer.setPosition(newPosition);
        Property propertyLandedOn = this.board.getProperty(newPosition);
        for (MonopolyObserver o : this.observers) {
            o.handleTakeTurn(turnPlayer, roll, propertyLandedOn);
        }
        propertyLandedOn.landed(turnPlayer);
        for (MonopolyObserver o : this.observers) {
            o.handlePlayerUpdate(this.playerList);
        }
        if (turnPlayer.getIsBankrupt()) bankrupt(turnPlayer); // Checks if player is bankrupt after paying rent
    }

    /**
     * Passes Player's turn.
     *
     * @return boolean use to change turn.
     */
    public void passTurn() {
        Player turnPlayer = this.playerList.get(this.turn);
        this.turn = (this.turn + 1) % this.playerList.size();
        for (MonopolyObserver o : this.observers) {
            o.handlePassTurn(turnPlayer);
        }

    }

    /**
     * Removes bankrupted Player from game and checks if their is a winner (One player remaining).
     *
     * @param p Player that has gone Bankrupt
     */
    private void bankrupt(Player p) {
        for (MonopolyObserver o : this.observers) {
            o.handleBankrupt(p);
        }
        for (Property property : p.getProperties()) {
            //Right now all Properties that the player owns are PropertyStreets
            if (property instanceof PropertyStreet) {
                ((PropertyStreet) property).removeOwner();
            }
        }
        this.playerList.remove(p);

        if (this.playerList.size() == 1) {
            Player winner = this.playerList.get(0);
            for (MonopolyObserver o : this.observers) {
                o.handleWinner(winner);
            }
        }
    }

    /**
     * Getter for the list of players.
     * @return The list of Player objects playing the game
     */
    public List<Player> getPlayerList() {
        return this.playerList;
    }
}
