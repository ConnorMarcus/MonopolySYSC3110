//Written by: Noah Hammoud

import java.util.*;

/**
 * The Player object for monopoly
 */
public class Player {
    private final String identifier;
    private int money;
    private int position;
    private Set<Property> properties;
    private boolean tookTurn;


    /**
     * The constructor for Player.
     * @param identifier, String identifier of the Player.
     */
    public Player(String identifier) {
        this.identifier = identifier;
        this.money = 1500;
        this.position = 0;
        this.properties = new HashSet<>();
        this.tookTurn = false;
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

    public Set<String> getPropertyGroups(MonopolyBoard board) {
        Set<String> propertyGroups = new HashSet<>();
        for (Property p : this.properties) {
            if (p instanceof NormalProperty && !propertyGroups.contains(((NormalProperty) p).getColour())) {
                String colour = ((NormalProperty) p).getColour();
                propertyGroups.add(colour);
                for (NormalProperty p2 : board.getPropertyGroup(colour)) {
                    if (!this.properties.contains(p2)) {
                        propertyGroups.remove(colour);
                        break;
                    }
                }
            }
        }
        return propertyGroups;
    }

    /**
     * Gets a string of all the properties that the player owns
     * @return a String containing the players properties separated by a comma
     */
    public String getPropertyString() {
        String propertyString = "";
        for (Property p : this.properties) {
            propertyString += p + ", ";
        }
        return propertyString.replaceAll(", $", ""); //removes trailing comma
    }

    /**
     * Checks if a player took their turn
     * @return true if a player took their turn, and false otherwise
     */
    public boolean isTookTurn() {
        return this.tookTurn;
    }

    /**
     * Sets whether a player took their turn
     * @param tookTurn the boolean value to be set
     */
    public void setTookTurn(boolean tookTurn) {
        this.tookTurn = tookTurn;
    }
}
