import javax.swing.*;

/**
 * Abstract class that represents properties that can be bought
 * @author Connor Marcus
 */
public abstract class OwnableProperty extends Property{
    private final int PRICE;
    private Player owner;

    abstract int calculateCost();

    /**
     * Constructor for this class
     * @param name The name of the property
     * @param price The price of buying the property
     */
    public OwnableProperty(String name, int price) {
        super(name);
        this.PRICE = price;
    }

    /**
     * Gets the price of the property
     * @return The price of the property
     */
    public int getPrice() {
        return this.PRICE;
    }

    /**
     * Gets the owner of this property
     * @return The Player object that owns this property, or null if there is no owner
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Removes the owner from this property
     */
    public void removeOwner() {
        owner = null;
    }

    /**
     * Handles player that landed on the property (Player can buy if available or must pay money to owner).
     *
     * @param landedPlayer the Player object that landed on the property
     */
    @Override
    public void landed(Player landedPlayer) {
        if(owner != null && landedPlayer != owner) {
            int cost = calculateCost();
            cost = landedPlayer.payRent(cost);
            owner.addMoney(cost);
            JOptionPane.showMessageDialog( null,"Player " + owner.getIdentifier() + " owns this property you must pay them $" + cost);
        }
        else if (owner != null) { //landed player owns the property
            JOptionPane.showMessageDialog(null, "You already own this property!");
        }
        else {
            buyHandler(landedPlayer);
        }
    }


    /**
     * Offers the Player to buy the property.
     *
     * @param landedPlayer Player object that landed on the property.
     */
    private void buyHandler(Player landedPlayer) {
        if (landedPlayer.getMoney() >= this.PRICE) {
            String[] options = {"no", "yes"};
            int choice = JOptionPane.showOptionDialog(null, "Would you like to buy this property for $" + this.PRICE + "?",
                    "Buy Property",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
            if (choice == 1) {
                landedPlayer.purchaseProperty(this);
                owner = landedPlayer;
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "You do not have enough money to buy this property!");
        }
    }
}
