/**
 * Normal Properties (houses) class
 *
 * @author Vahid Foroughi

 */
public class PropertyStreet extends OwnableProperty {
    private final String COLOUR;
    private final double RENT_RATE = 0.1;

    /**
     * Constructor for PropertyStreet object.
     *
     * @param colour String colour of PropertyStreet
     * @param name String name of PropertyStreet
     * @param price int price of PropertyStreet
     */
    public PropertyStreet(String colour, String name, int price) {
        super(name, price);
        this.COLOUR = colour;
    }

    /**
     * Calculates the cost of landing on this property
     * @return The price of the property times the rent rate.
     */
    @Override
    public int calculateCost() {
        return (int)(getPrice() * RENT_RATE);
    }

    /**
     * Gets PropertyStreets colour.
     *
     * @return String colour of property
     */
    public String getColour() {
        return this.COLOUR;
    }

    /**
     * String representation of PropertyStreet object.
     *
     * @return String representation of PropertyStreet.
     */
    @Override
    public String toString() {
        return String.format("%s (%s)", super.getName(), this.COLOUR);
    }
}
