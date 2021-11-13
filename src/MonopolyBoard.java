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
        PURPLE_PROPERTIES(Arrays.asList(new PropertyStreet("purple", "Mediterranean Avenue", 60), new PropertyStreet("purple", "Baltic Avenue", 60))),
        LIGHT_BLUE_PROPERTIES(Arrays.asList(new PropertyStreet("light blue", "Oriental Avenue", 100), new PropertyStreet("light blue", "Vermont Avenue", 100), new PropertyStreet("light blue", "Connecticut Avenue", 120))),
        PINK_PROPERTIES(Arrays.asList(new PropertyStreet("pink", "St. Charles Place", 140), new PropertyStreet("pink", "States Avenue", 140), new PropertyStreet("pink", "Virginia Avenue", 160))),
        ORANGE_PROPERTIES(Arrays.asList(new PropertyStreet("orange", "St. James Place", 180), new PropertyStreet("orange", "Tennessee Avenue", 180), new PropertyStreet("orange", "New York Avenue", 200))),
        RED_PROPERTIES(Arrays.asList(new PropertyStreet("red", "Kentucky Avenue", 220), new PropertyStreet("red", "Indiana Avenue", 220), new PropertyStreet("red", "Illinois Avenue", 240))),
        YELLOW_PROPERTIES(Arrays.asList(new PropertyStreet("yellow", "Atlantic Avenue", 260), new PropertyStreet("yellow", "Ventnor Avenue", 260), new PropertyStreet("yellow", "Marvin Gardens", 280))),
        GREEN_PROPERTIES(Arrays.asList(new PropertyStreet("green", "Pacific Avenue", 300), new PropertyStreet("green", "North Carolina Avenue", 300), new PropertyStreet("green", "Pennsylvania Avenue", 320))),
        DARK_BLUE_PROPERTIES(Arrays.asList(new PropertyStreet("dark blue", "Park Place", 350), new PropertyStreet("dark blue", "Boardwalk", 400)));

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
        addProperties();
    }

    /**
     * Helper method to add the properties in the correct order to the board; avoids too much code in the constructor
     */
    private void addProperties() {
        this.properties.add(new PropertyFreeSpace("GO"));
        this.properties.add(PropertyGroups.PURPLE_PROPERTIES.propertySet.get(0)); // Mediterranean Avenue
        this.properties.add(new PropertyFreeSpace("Free Space"));
        this.properties.add(PropertyGroups.PURPLE_PROPERTIES.propertySet.get(1)); // Baltic Avenue
        this.properties.add(new PropertyTax("Income Tax", 200));
        this.properties.add(new PropertyRailroad("Reading Railroad"));
        this.properties.add(PropertyGroups.LIGHT_BLUE_PROPERTIES.propertySet.get(0)); //Oriental Avenue
        this.properties.add(new PropertyFreeSpace("Free Space"));
        this.properties.addAll(PropertyGroups.LIGHT_BLUE_PROPERTIES.propertySet.subList(1, 3));
        this.properties.add(new PropertyFreeSpace("Jail"));
        this.properties.add(PropertyGroups.PINK_PROPERTIES.propertySet.get(0)); //St. Charles Place
        this.properties.add(new PropertyUtility("Electric Company"));
        this.properties.addAll(PropertyGroups.PINK_PROPERTIES.propertySet.subList(1,3));
        this.properties.add(new PropertyRailroad("Pennsylvania Railroad"));
        this.properties.add(PropertyGroups.ORANGE_PROPERTIES.propertySet.get(0)); //St. James Place
        this.properties.add(new PropertyFreeSpace("Free Space"));
        this.properties.addAll(PropertyGroups.ORANGE_PROPERTIES.propertySet.subList(1,3));
        this.properties.add(new PropertyFreeSpace("Free Parking"));
        this.properties.add(PropertyGroups.RED_PROPERTIES.propertySet.get(0)); //Kentucky Avenue
        this.properties.add(new PropertyFreeSpace("Free Space"));
        this.properties.addAll(PropertyGroups.RED_PROPERTIES.propertySet.subList(1,3));
        this.properties.add(new PropertyRailroad("B&O Railroad"));
        this.properties.addAll(PropertyGroups.YELLOW_PROPERTIES.propertySet.subList(0,2));
        this.properties.add(new PropertyUtility("Water Works"));
        this.properties.add(PropertyGroups.YELLOW_PROPERTIES.propertySet.get(2)); //Marvin Gardens
        this.properties.add(new PropertyFreeSpace("Go To Jail"));
        this.properties.addAll(PropertyGroups.GREEN_PROPERTIES.propertySet.subList(0,2));
        this.properties.add(new PropertyFreeSpace("Free Space"));
        this.properties.add(PropertyGroups.GREEN_PROPERTIES.propertySet.get(2)); //Pennsylvania Avenue
        this.properties.add(new PropertyRailroad("Short Line"));
        this.properties.add(new PropertyFreeSpace("Free Space"));
        this.properties.add(PropertyGroups.DARK_BLUE_PROPERTIES.propertySet.get(0)); //Park Place
        this.properties.add(new PropertyTax("Luxury Tax", 100));
        this.properties.add(PropertyGroups.DARK_BLUE_PROPERTIES.propertySet.get(1)); //BoardWalk
    }


    /**
     * Gets List of PropertyGroups based on colour.
     *
     * @param colour String colour of PropertyGroup
     * @return List<PropertyStreet> list of PropertyStreets in group
     */
    public List<PropertyStreet> getPropertyGroup(String colour) {
        return PropertyGroups.valueOf(colour.replaceAll("\\s+","").toUpperCase() + "_PROPERTIES").propertySet;
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
