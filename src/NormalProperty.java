public class NormalProperty extends Property{
    private String colour;

    public NormalProperty(String colour, String name, int price) {
        super(name, price);
        this.colour = colour;
    }

    public String getColour() {
        return this.colour;
    }
}
