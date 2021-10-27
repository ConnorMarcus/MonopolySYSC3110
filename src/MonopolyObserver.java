/**
 * Interface that all the observers of the MonopolyModel must implement.
 */
public interface MonopolyObserver {
    void handleTakeTurn(Player player, int roll, Property propertyLandedOn);
    void handlePassTurn(Player player);
    void handleBankrupt(Player player);
    void handleWinner(Player player);
    void handlePlayerUpdate(Player player);
}
