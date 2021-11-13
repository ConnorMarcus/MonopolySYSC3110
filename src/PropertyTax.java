import javax.swing.*;

/**
 * Class that corresponds to the Tax property in a Monopoly game (income tax and luxury tax)
 */
public class PropertyTax extends Property {
    private final int TAX_PRICE;

    public PropertyTax(String name, int taxPrice) {
        super(name);
        this.TAX_PRICE = taxPrice;
    }

    @Override
    public void landed(Player landedPlayer) {
        int cost = landedPlayer.payMoney(TAX_PRICE);
        JOptionPane.showMessageDialog(null, "You must pay $" + cost + " in tax!");
    }
}
