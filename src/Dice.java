import java.util.Random;

/**
 * Dice object for monopoly.
 *
 * @author George Pantazopoulos
 */
public class Dice {
    private int numDice;


    /**
     * Constructor for Dice.
     *
     * @param numDice int number of dice
     */
    public Dice(int numDice) {
        if (numDice <= 0) {
            throw new IllegalArgumentException("Number of dice must be > 0");
        }
        else {
            this.numDice = numDice;
        }
    }


    /**
     * Rolls all of the dice.
     *
     * @return int[] the roll of each dice
     */
    public int[] rollDice() {
        int[] dice = new int[numDice];
        Random r = new Random();
        for (int i=0; i<this.numDice; i++) {
            int roll = r.nextInt(6) + 1;
            dice[i] = roll;
        }
        return dice;
    }
}
