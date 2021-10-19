import java.util.Random;

// Written by George Pantazopoulos
public class Dice {
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
        int sum = 0;
        Random r = new Random();
        for (int i=0; i<this.numDice; i++) {
            sum += r.nextInt(6) + 1;
        }
        return sum;
    }
}
