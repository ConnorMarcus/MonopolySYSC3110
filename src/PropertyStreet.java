/**
 * Normal Properties (houses) class
 *
 * @author Vahid Foroughi

 */

public class PropertyStreet extends Property{
    private String colour;

    public PropertyStreet(String colour, String name, int price) {
        super(name, price);
        this.colour = colour;
    }

    public String getColour() {
        return this.colour;
    }

}
