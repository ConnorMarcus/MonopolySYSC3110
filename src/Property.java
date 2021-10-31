/**
 * The Property object for monopoly.
 *
 * @author Vahid Foroughi
 */
public abstract class Property {
    private String name;

    /**
     * Abstract method to handler player landing on property.
     *
     * @param landedPlayer the Player object that landed on the property
     */
    public abstract void landed(Player landedPlayer);


    /**
     * Constructor for Property.
     *
     * @param name String name of Property
     */
    public Property(String name) {
        this.name = name;
    }


    /**
     * Get's name of Property.
     *
     * @return String name of Property
     */
    public String getName() {
        return name;
    }


    /**
     * String representation of Property object.
     *
     * @return String representation of Property
     */
    @Override
    public String toString() {
        return String.format("%s", this.name);
    }

}
