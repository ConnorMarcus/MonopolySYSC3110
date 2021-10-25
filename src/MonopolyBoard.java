import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class to represent a class Monopoly Board with all the colour groups.
 *
 * @author Connor Marcus
 * @author George Pantazopoulos
 */
public class MonopolyBoard {
    private List<Property> properties;


    /**
     * Enum for each different PropertyGroup.
     */
    private enum PropertyGroups {
        PURPLEPROPERTIES(Arrays.asList(new PropertyStreet("purple", "Mediterranean Avenue", 60), new PropertyStreet("purple", "Baltic Avenue", 60))),
        LIGHTBLUEPROPERTIES(Arrays.asList(new PropertyStreet("light blue", "Oriental Avenue", 100), new PropertyStreet("light blue", "Vermont Avenue", 100), new PropertyStreet("light blue", "Connecticut Avenue", 120))),
        PINKPROPERTIES(Arrays.asList(new PropertyStreet("pink", "St. Charles Place", 140), new PropertyStreet("pink", "States Avenue", 140), new PropertyStreet("pink", "Virginia Avenue", 160))),
        ORANGEPROPERTIES(Arrays.asList(new PropertyStreet("orange", "St. James Place", 180), new PropertyStreet("orange", "Tennessee Avenue", 180), new PropertyStreet("orange", "New York Avenue", 200))),
        REDPROPERTIES(Arrays.asList(new PropertyStreet("red", "Kentucky Avenue", 220), new PropertyStreet("red", "Indiana Avenue", 220), new PropertyStreet("red", "Illinois Avenue", 240))),
        YELLOWPROPERTIES(Arrays.asList(new PropertyStreet("yellow", "Atlantic Avenue", 260), new PropertyStreet("yellow", "Ventnor Avenue", 260), new PropertyStreet("yellow", "Marvin Gardens", 280))),
        GREENPROPERTIES(Arrays.asList(new PropertyStreet("green", "Pacific Avenue", 300), new PropertyStreet("green", "North Carolina Avenue", 300), new PropertyStreet("green", "Pennsylvania Avenue", 320))),
        DARKBLUEPROPERTIES(Arrays.asList(new PropertyStreet("dark blue", "Park Place", 350), new PropertyStreet("dark blue", "Boardwalk", 400)));

        private List<PropertyStreet> propertySet;


        /**
         * Constructor for PropertyGroups.
         *
         * @param propertySet
         */
        PropertyGroups(List<PropertyStreet> propertySet) {
            this.propertySet = propertySet;
        }
    }


    /**
     * Constructor for MonopolyBoard.
     */
    public MonopolyBoard() {
        this.properties = new ArrayList<>();
        this.properties.add(new PropertyFreeSpace("GO"));
        this.properties.add(PropertyGroups.PURPLEPROPERTIES.propertySet.get(0)); // Mediterranean Avenue
        this.properties.add(new PropertyFreeSpace("Free"));
        this.properties.add(PropertyGroups.PURPLEPROPERTIES.propertySet.get(1)); // Baltic Avenue
        this.properties.add(new PropertyFreeSpace("Income Tax"));
        this.properties.add(new PropertyFreeSpace("Reading Railroad")); //price constant among all railroads so don't need to pass the price (use a constant for the price in the class)
        this.properties.add(PropertyGroups.LIGHTBLUEPROPERTIES.propertySet.get(0)); //Oriental Avenue
        this.properties.add(new PropertyFreeSpace("Free"));
        this.properties.addAll(PropertyGroups.LIGHTBLUEPROPERTIES.propertySet.subList(1, 3));
        this.properties.add(new PropertyFreeSpace("Jail"));
        this.properties.add(PropertyGroups.PINKPROPERTIES.propertySet.get(0)); //St. Charles Place
        this.properties.add(new PropertyFreeSpace("Electric Company"));
        this.properties.addAll(PropertyGroups.PINKPROPERTIES.propertySet.subList(1,3));
        this.properties.add(new PropertyFreeSpace("Pennsylvania Railroad"));
        this.properties.add(PropertyGroups.ORANGEPROPERTIES.propertySet.get(0)); //St. James Place
        this.properties.add(new PropertyFreeSpace("Free"));
        this.properties.addAll(PropertyGroups.ORANGEPROPERTIES.propertySet.subList(1,3));
        this.properties.add(new PropertyFreeSpace("Free Parking"));
        this.properties.add(PropertyGroups.REDPROPERTIES.propertySet.get(0)); //Kentucky Avenue
        this.properties.add(new PropertyFreeSpace("Free"));
        this.properties.addAll(PropertyGroups.REDPROPERTIES.propertySet.subList(1,3));
        this.properties.add(new PropertyFreeSpace("B&O Railroad"));
        this.properties.addAll(PropertyGroups.YELLOWPROPERTIES.propertySet.subList(0,2));
        this.properties.add(new PropertyFreeSpace("Water Works"));
        this.properties.add(PropertyGroups.YELLOWPROPERTIES.propertySet.get(2)); //Marvin Gardens
        this.properties.add(new PropertyFreeSpace("Go To Jail"));
        this.properties.addAll(PropertyGroups.GREENPROPERTIES.propertySet.subList(0,2));
        this.properties.add(new PropertyFreeSpace("Free"));
        this.properties.add(PropertyGroups.GREENPROPERTIES.propertySet.get(2)); //Pennsylvania Avenue
        this.properties.add(new PropertyFreeSpace("Short Line"));
        this.properties.add(new PropertyFreeSpace("Free"));
        this.properties.add(PropertyGroups.DARKBLUEPROPERTIES.propertySet.get(0)); //Park Place
        this.properties.add(new PropertyFreeSpace("Luxury Tax"));
        this.properties.add(PropertyGroups.DARKBLUEPROPERTIES.propertySet.get(1)); //BoardWalk
    }


    /**
     * Gets List of PropertyGroups based on colour.
     *
     * @param colour String colour of PropertyGroup
     * @return List<PropertyStreet> list of PropertyStreets in group
     */
    public List<PropertyStreet> getPropertyGroup(String colour) {
        return PropertyGroups.valueOf(colour.replaceAll("\\s+","").toUpperCase() + "PROPERTIES").propertySet;
    }


    /**
     * Gets Property at index of List.
     *
     * @param index int index of Property
     * @return Property at index
     */
    public Property getProperty(int index) {
        return this.properties.get(index);
    }


    /**
     * Gets the number of Properties.
     *
     * @return int number of Properties
     */
    public int getNumProperties() {
        return this.properties.size();
    }

}
