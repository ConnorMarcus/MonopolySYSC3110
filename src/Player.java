//Written by: Noah Hammoud

import java.util.HashSet;
import java.util.Set;

/**
 * The Player object for monopoly
 */
public class Player {
    private final String identifier;
    private int money;
    private int position;
    private Set<Property> properties;


    /**
     * The constructor for Player.
     * @param identifier, String identifier of the Player.
     */
    public Player(String identifier) {
        this.identifier = identifier;
        this.money = 1500;
        this.position = 0;
        this.properties = new HashSet<>();
    }


    /**
     * Gets the identifier of the Player.
     * @return the String identifier.
     */
    public String getIdentifier() {
        return this.identifier;
    }


    /**
     * Gets the Players money.
     * @return the int amount.
     */
    public int getMoney() {
        return this.money;
    }


    /**
     * Gets the Players current position.
     * @return the int position.
     */
    public int getPosition() {
        return this.position;
    }


    /**
     * Sets Players Position.
     * @param pos, int position.
     */
    public void setPosition(int pos) {
        this.position = pos;
    }


    /**
     * Adds money to Player.
     * @param money, int amount of money being added.
     */
    public void addMoney(int money) {
        this.money += money;
    }


    /**
     * Removes money from Player.
     * @param money, int amount of money being removed.
     * @return boolean, true if player is not bankrupt.
     */
    public boolean removeMoney(int money) {
        if (this.money - money <= 0) {
            this.money = 0;
            System.out.println(this.identifier + " has gone bankrupt, thanks for playing!");
            return false;
        }
        this.money -= money;
        return true;
    }


    /**
     * purchases property.
     * @param p, the Property object that the Player wants to purchase.
     * @param cost, the int cost of the Property.
     */
    public void purchaseProperty(Property p, int cost) {
        if (this.removeMoney(cost)) this.properties.add(p);
        else System.out.println(this.identifier + " failed to purchase the property");
    }


    /**
     * Checks if Player owns property.
     * @param p, the Property object that is being checked.
     * @return boolean, true if Player owns property.
     */
    public boolean ownsProperty(Property p) {
        return this.properties.contains(p);
    }
}
