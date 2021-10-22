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
     * Sets owner of PropertyStreet after it is purchased.
     *
     * @param owner Player who owns PropertyStreet
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }


    /**
     * Handlers player that landed on PropertyStreet (Player can buy if available or must pay rent to owner).
     *
     * @param landedPlayer the Player object that landed on the property
     */
    @Override
    public void Landed(Player landedPlayer) {
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


    /**
     * Offers the Player to buy the PropertyStreet.
     *
     * @param landedPlayer Player object that landed on PropertyStreet
     */
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
                owner = landedPlayer;
            }
        }
        else {
            System.out.println("You do not have enough money to buy this property!");
        }
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
