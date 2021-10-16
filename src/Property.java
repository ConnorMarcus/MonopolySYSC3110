import java.util.Scanner;

/**
 * Property class
 *
 * @author Vahid Foroughi
 */
public class Property {
    private String name;
    private int price;

    public Property(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {return name;}
    public int getPrice() {return price;}

    @Override
    public String toString() {
        return this.name;
    }

    public void Landed(Player owner, Player renter) {
        if(owner !=null) {
            int rent = (int)(this.price * 0.1);
            rent = renter.payRent(rent);
            owner.addMoney(rent);
            System.out.println(owner.getIdentifier() + " owns this property you must pay them $" + rent);
        }else{
            buyHandler(renter);
        }
    }
    private void buyHandler(Player turnPlayer) {
        String answer = "";
        Scanner in = new Scanner(System.in);
        System.out.println("Would you like to buy " + this.getName() + " for $" + this.getPrice() + "? (y/n)");
        answer = in.nextLine().toLowerCase();
        while (!(answer.equals("y")) && !(answer.equals("n"))) {
            System.out.println("Please enter y or n");
            System.out.println("Would you like to buy " + this.getName() + " for $" + this.getPrice() + "? (y/n)");
            answer = in.nextLine().toLowerCase();
        }
        if (answer.equals("y")) {
            turnPlayer.purchaseProperty(this, this.getPrice());
        }
    }


}
