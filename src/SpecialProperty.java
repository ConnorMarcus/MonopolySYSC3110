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

    /**
     * User must pay an income tax when they land on this property
     *
     * @return int, 200 dollars the user must pay
     */
    public int incomeTax(){
        return 200;
    }
    /**
     * User must pay a super tax when they land on this property
     *
     * @return int, 100 dollars the user must pay
     */
    public int superTax(){
        return 100;
    }
}
