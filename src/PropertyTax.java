import javax.swing.*;

/**
 * Class that corresponds to the Tax property in a Monopoly game (income tax and luxury tax)
 */
public class PropertyTax extends Property {
    private final int TAX_PRICE;

    /**
     * Constructor of the class
     * @param name The name of the property
     * @param taxPrice The tax price of landing on the property
     */
    public PropertyTax(String name, int taxPrice) {
        super(name);
        this.TAX_PRICE = taxPrice;
    }

    /**
     * Handles what happens when you land on the property
     * @param landedPlayer the Player object that landed on the property
     */
    @Override
    public void landed(Player landedPlayer) {
        int cost = landedPlayer.payMoney(TAX_PRICE);
        JOptionPane.showMessageDialog(null, "You must pay $" + cost + " in tax!");
    }
}
