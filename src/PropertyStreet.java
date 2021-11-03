import javax.swing.*;

/**
 * Normal Properties (houses) class
 *
 * @author Vahid Foroughi

 */
public class PropertyStreet extends Property{
    private String colour;
    private int price;
    private final double RENT_RATE = 0.1;
    private Player owner;


    /**
     * Constructor for PropertyStreet object.
     *
     * @param colour String colour of PropertyStreet
     * @param name String name of PropertyStreet
     * @param price int price of PropertyStreet
     */
    public PropertyStreet(String colour, String name, int price) {
        super(name);
        this.colour = colour;
        this.price = price;
    }


    /**
     * Gets PropertyStreets colour.
     *
     * @return String colour of property
     */
    public String getColour() {
        return this.colour;
    }


    /**
     * Gets price of PropertyStreet.
     *
     * @return int price of PropertyStreet
     */
    public int getPrice() {
        return this.price;
    }


    /**
     * Handlers player that landed on PropertyStreet (Player can buy if available or must pay rent to owner).
     *
     * @param landedPlayer the Player object that landed on the property
     */
    @Override
    public void landed(Player landedPlayer) {
        if(owner != null && landedPlayer != owner) {
            int rent = (int)(this.price * RENT_RATE);
            rent = landedPlayer.payRent(rent);
            owner.addMoney(rent);
            JOptionPane.showMessageDialog( null,"Player " + owner.getIdentifier() + " owns this property you must pay them $" + rent);
        }
        else if (owner != null) { //landed player owns the property
            JOptionPane.showMessageDialog(null, "You already own this property!");
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
        if (landedPlayer.getMoney() >= this.price) {
            String[] options = {"no", "yes"};
            int choice = JOptionPane.showOptionDialog(null, "Would you like to buy this property for $" + this.price + "?",
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

    /**
     * Removes the owner from this Property.
     */
    public void removeOwner() {
        this.owner = null;
    }


    /**
     * String representation of PropertyStreet object.
     *
     * @return String representation of PropertyStreet.
     */
    @Override
    public String toString() {
        return String.format("%s (%s)", super.getName(), this.colour);
    }
}
