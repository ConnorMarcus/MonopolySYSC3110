import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
    public MonopolyModel(String type, int numPlayers) {
        this.board = new MonopolyBoard();
        this.dice = new Dice(2);
        this.turn = 0;
        this.observers = new ArrayList<>();
        this.playerList = new ArrayList<>();
        this.playerList.add(new Player(String.valueOf(1), String.format("images/player%s.png", 1), false));
        for (int i=1; i<numPlayers; i++) {
            this.playerList.add(new Player(String.valueOf(i+1), String.format("images/player%s.png", i+1), type.equals(MonopolyView.AI_STRING)));
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
     * Gets the value of the turn variable used to keep
     * of the current turn.
     * @return the current value of the turn variable
     */
    public int getTurn(){
        return this.turn;
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

        boolean isDouble = roll[0] == roll[1];
        boolean value = isDouble ? true : false;
        turnPlayer.setRolledDoubles(value);

        if (turnPlayer.isJailed() && !(isDouble)) {
            for (MonopolyObserver o : this.observers) {
                o.handleStuckInJail(turnPlayer, roll);
            }
        }
        else {
            int rollSum = IntStream.of(roll).sum();
            if ((turnPlayer.getPosition() + rollSum) > (this.board.getNumProperties()-1)) {
                turnPlayer.setPassingGo(true);
                turnPlayer.addMoney(200);
                for (MonopolyObserver o : this.observers) {
                    o.handlePlayerUpdate(this.playerList);
                }
            }
            int newPosition = (turnPlayer.getPosition() + rollSum) % this.board.getNumProperties();
            turnPlayer.setPosition(newPosition);
            Property propertyLandedOn = this.board.getProperty(newPosition);
            for (MonopolyObserver o : this.observers) {
                o.handleTakeTurn(turnPlayer, roll, propertyLandedOn);
                o.handlePlayerUpdate(this.playerList);
            }
            turnPlayer.setPassingGo(false);
            if (turnPlayer.getIsBankrupt()) bankrupt(turnPlayer); // Checks if player is bankrupt after paying rent
        }
    }

    /**
     * Passes Player's turn.
     */
    public void passTurn() {
        Player turnPlayer = this.playerList.get(this.turn);
        this.turn = (this.turn + 1) % this.playerList.size();
        for (MonopolyObserver o : this.observers) {
            o.handlePassTurn(turnPlayer);
        }
        Player nextPlayer = this.playerList.get(this.turn);
        if (nextPlayer.isJailed()) {
            nextPlayer.incrementTimeInJail();
            if (nextPlayer.getTimeInJail() == 3) {
                nextPlayer.payMoney(50);
                nextPlayer.setJailed(false);
                nextPlayer.resetTimeInJail();
                for (MonopolyObserver o : this.observers) {
                    o.handleThreeTurnsInJail(nextPlayer);
                    o.handlePlayerUpdate(this.playerList);
                }
                if (nextPlayer.getMoney() < 0) {
                    nextPlayer.setBankrupt(true);
                    bankrupt(nextPlayer);
                }
            }
            else {
                for (MonopolyObserver o : this.observers) {
                    o.handleJailChoice(nextPlayer);
                    o.handlePlayerUpdate(this.playerList);
                }
                if (!nextPlayer.isJailed())  {
                    nextPlayer.payMoney(50);
                    nextPlayer.resetTimeInJail();
                }
            }
        }
        if (nextPlayer.getIsAI()) {
            for (MonopolyObserver o : this.observers) {
                o.handleAITurn(nextPlayer);
            }
        }
    }

    /**
     * Removes bankrupted Player from game and checks if there is a winner (One player remaining).
     * @param p Player that has gone Bankrupt
     */
    public void bankrupt(Player p) {
        for (MonopolyObserver o : this.observers) {
            o.handleBankrupt(p);
        }
        for (OwnableProperty property : p.getProperties()) {
            property.removeOwner();
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
