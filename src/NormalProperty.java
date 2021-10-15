public class NormalProperty extends Property{
    private String colour;

    public NormalProperty(String colour, String name, int price) {
        super(name, price);
        this.colour = colour;
    }
}
