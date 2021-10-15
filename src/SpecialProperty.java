/**
 * Special Property Class
 * This includes Railroads, Jail, JailVisit, Taxes, Go,
 * Free parking, Water works, Electric Company
 */
public class SpecialProperty extends Property {
    private String action;

    public SpecialProperty(String action, String name, int price){
        super(name, price);
        this.action = action;
    }

    public void railroad(){

    }
}
