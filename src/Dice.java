import java.util.Date;
import java.util.Random;

// Written by George Pantazopoulos
public class Dice {
//    private int dice1;
//    private int dice2;
//    private int doubleCount = 0;
//    private int totalRoll = 0;
//    private int rollSum = 0;
//    private boolean turnOver = false;
    private int numDice;

    public Dice(int numDice) {
        if (numDice <= 0) {
            throw new IllegalArgumentException("Number of dice must be > 0");
        }
        this.numDice = numDice;
    }

    /**
     * Rolls all of the dice
     * @return the sum of all the dice rolls
     */
    public int rollDice() {
//        this.dice1 = 1 + (int)(Math.random() * 6);
//        this.dice2 = 1 + (int)(Math.random() * 6);
//        this.totalRoll += this.dice1 + this.dice2;
//        if (this.dice1 == this.dice2) {
//            System.out.println("You rolled a " + this.dice1 + " and a " + this.dice2 + "!");
//            ++this.doubleCount;
//            if (this.doubleCount == 3) {
//                System.out.println("You rolled doubles 3 times in a row!");
//                System.out.println("Go straight to jail!");
//                this.doubleCount = 0;
//                this.totalRoll = 0;
//                return 0;
//            }
//            System.out.println("Doubles! Roll again");
//            this.doubleCount += 1;
//            this.rollDice();
//        }
//        else {
//            System.out.println("You rolled a " + this.dice1 + " and a " + this.dice2 + "!");
//            System.out.println("Your total roll is: " + this.totalRoll);
//            this.turnOver = true;
//        }
//        if (this.turnOver) {
//            this.rollSum = this.totalRoll;
//            this.totalRoll = 0;
//            this.doubleCount = 0;
//        }
//        return this.rollSum;
        int sum = 0;
        Random r = new Random();
        for (int i=0; i<this.numDice; i++) {
            sum += r.nextInt(6) + 1;
        }
        return sum;
    }
}
