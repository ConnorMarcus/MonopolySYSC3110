import java.util.Scanner;

/**
 * Normal Properties (houses) class
 *
 * @author Vahid Foroughi

 */

public class PropertyStreet extends Property{
    private String colour;
    private int price;
    private final double RENTRATE = 0.1;

    public PropertyStreet(String colour, String name, int price) {
        super(name);
        this.colour = colour;
        this.price = price;
    }

    public String getColour() {
        return this.colour;
    }

    public int getPrice() {
        return this.price;
    }

    public void Landed(Player owner, Player landedPlayer) {
        if(owner != null && landedPlayer != owner) {
            int rent = (int)(this.price * RENTRATE);
            rent = landedPlayer.payRent(rent);
            owner.addMoney(rent);
            System.out.println("Player " + owner.getIdentifier() + " owns this property you must pay them $" + rent);
        }
        else if (owner != null) { //landed player owns the property
            System.out.println("You already own this property!");
        }
        else {
            buyHandler(landedPlayer);
        }
    }
    private void buyHandler(Player landedPlayer) {
        String answer = "";
        Scanner in = new Scanner(System.in);
        if (landedPlayer.getMoney() >= this.price) {
            System.out.println("Would you like to buy " + this + " for $" + this.price + "? (y/n)");
            answer = in.nextLine().toLowerCase();
            while (!(answer.equals("y")) && !(answer.equals("n"))) {
                System.out.println("Please enter y or n");
                System.out.println("Would you like to buy " + this + " for $" + this.price + "? (y/n)");
                answer = in.nextLine().toLowerCase();
            }
            if (answer.equals("y")) {
                landedPlayer.purchaseProperty(this);
            }
        }
        else {
            System.out.println("You do not have enough money to buy this property!");
        }
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", super.getName(), this.colour);
    }
}
