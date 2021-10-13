public class Dice {
    private int dice1;
    private int dice2;
    private int doubleCount = 0;
    private int totalRoll = 0;
    private int rollSum = 0;
    private boolean turnOver = false;

    public Dice() {
    }

    public int rollDice() {
        while(this.dice1 == 0) {
            this.dice1 = (int)(Math.random() * 7.0D);
        }

        while(this.dice2 == 0) {
            this.dice2 = (int)(Math.random() * 7.0D);
        }

        this.totalRoll += this.dice1 + this.dice2;
        if (this.dice1 == this.dice2) {
            System.out.println("You rolled a " + this.dice1 + " and a " + this.dice2 + "!");
            ++this.doubleCount;
            if (this.doubleCount == 3) {
                System.out.println("You rolled doubles 3 times in a row!");
                System.out.println("Go straight to jail!");
                this.doubleCount = 0;
                this.totalRoll = 0;
                return 0;
            }

            System.out.println("Doubles! Roll again");
            this.dice1 = 0;
            this.dice2 = 0;
            this.rollDice();
        } else {
            System.out.println("You rolled a " + this.dice1 + " and a " + this.dice2 + "!");
            System.out.println("Your total roll is: " + this.totalRoll);
            this.turnOver = true;
        }

        if (this.turnOver) {
            this.rollSum = this.totalRoll;
            this.totalRoll = 0;
        }

        return this.rollSum;
    }
}
