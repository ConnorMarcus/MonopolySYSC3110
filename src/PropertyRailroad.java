import javax.swing.*;

/**
 * Railroad property class
 * @author Connor Marcus
 */
public class PropertyRailroad extends Property implements OwnableProperty {
    private final int PRICE = 200;
    private final double RENT_RATE = 0.125;
    private Player owner;

    /**
     * Constructor for the class
     * @param name The name of the property
     */
    public PropertyRailroad(String name) {
        super(name);

    }
    /**
     * Handlers player that landed on PropertyStreet (Player can buy if available or must pay money to owner).
     *
     * @param landedPlayer the Player object that landed on the property
     */
    @Override
    public void landed(Player landedPlayer) {
        if(owner != null && landedPlayer != owner) {
            int rent = (int)(this.PRICE * RENT_RATE * Math.pow(2, owner.getNumRailroads()-1));
            rent = landedPlayer.payRent(rent);
            owner.addMoney(rent);
            JOptionPane.showMessageDialog( null,"Player " + owner.getIdentifier() + " owns this railroad you must pay them $" + rent + " since they own " + owner.getNumRailroads() + " railroads!");
        }
        else if (owner != null) { //landed player owns the property
            JOptionPane.showMessageDialog(null, "You already own this railroad!");
        }
        else {
            buyHandler(landedPlayer);
        }
    }


    /**
     * Offers the Player to buy the PropertyStreet.
     *
     * @param landedPlayer Player object that landed on PropertyStreet
     */
    private void buyHandler(Player landedPlayer) {
        if (landedPlayer.getMoney() >= this.PRICE) {
            String[] options = {"no", "yes"};
            int choice = JOptionPane.showOptionDialog(null, "Would you like to buy this railroad for $" + this.PRICE + "?",
                    "Buy Property",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);
            if (choice == 1) {
                landedPlayer.purchaseProperty(this);
                owner = landedPlayer;
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "You do not have enough money to buy this railroad!");
        }
    }

    /**
     * Removes the owner from this Property.
     */
    public void removeOwner() {
        this.owner = null;
    }

    /**
     * Gets the price of the property
     * @return the price of the property
     */
    public int getPrice() {
        return PRICE;
    }
}

