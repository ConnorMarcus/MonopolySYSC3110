import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MonopolyBoard {
    private List<Property> properties;

    private enum PropertyGroups {
        BROWNPROPERTIES(Arrays.asList(new PropertyStreet("brown", "Mediterranean Avenue", 60), new PropertyStreet("brown", "Baltic Avenue", 60))),
        LIGHTBLUEPROPERTIES(Arrays.asList(new PropertyStreet("light blue", "Oriental Avenue", 100), new PropertyStreet("light blue", "Vermont Avenue", 100), new PropertyStreet("light blue", "Connecticut Avenue", 120))),
        PINKPROPERTIES(Arrays.asList(new PropertyStreet("pink", "St. Charles Place", 140), new PropertyStreet("pink", "States Avenue", 140), new PropertyStreet("pink", "Virginia Avenue", 160))),
        ORANGEPROPERTIES(Arrays.asList(new PropertyStreet("orange", "St. James Place", 180), new PropertyStreet("orange", "Tennessee Avenue", 180), new PropertyStreet("orange", "New York Avenue", 200))),
        REDPROPERTIES(Arrays.asList(new PropertyStreet("red", "Kentucky Avenue", 220), new PropertyStreet("red", "Indiana Avenue", 220), new PropertyStreet("red", "Illinois Avenue", 240))),
        YELLOWPROPERTIES(Arrays.asList(new PropertyStreet("yellow", "Atlantic Avenue", 260), new PropertyStreet("yellow", "Ventnor Avenue", 260), new PropertyStreet("yellow", "Marvin Gardens", 280))),
        GREENPROPERTIES(Arrays.asList(new PropertyStreet("green", "Pacific Avenue", 300), new PropertyStreet("green", "North Carolina Avenue", 300), new PropertyStreet("green", "Pennsylvania Avenue", 320))),
        DARKBLUEPROPERTIES(Arrays.asList(new PropertyStreet("dark blue", "Park Place", 350), new PropertyStreet("dark blue", "Boardwalk", 400)));

        private List<PropertyStreet> propertySet;

        PropertyGroups(List<PropertyStreet> propertySet) {
            this.propertySet = propertySet;
        }
    }

    public MonopolyBoard() {
        //for now there are no utility properties to keep the board as a square
        this.properties = new ArrayList<>();
        //this.properties.add(GO Property (+$200, free space for now));
        this.properties.addAll(PropertyGroups.BROWNPROPERTIES.propertySet);
        //this.properties.add(Income Tax Property (-$200));
        //this.properties.add(new RailRoadProperty("Reading Railroad")); //price constant among all railroads so don't need to pass the price (use a constant for the price in the class)
        this.properties.addAll(PropertyGroups.LIGHTBLUEPROPERTIES.propertySet);
        //this.properties.add(Jail (Free space for now));
        this.properties.addAll(PropertyGroups.PINKPROPERTIES.propertySet);
        //this.properties.add(new RailRoadProperty("Pennsylvania Railroad"));
        this.properties.addAll(PropertyGroups.ORANGEPROPERTIES.propertySet);
        //this.properties.add(Free Parking (Free space for now));
        this.properties.addAll(PropertyGroups.REDPROPERTIES.propertySet);
        //this.properties.add(new RailRoadProperty("B&O Railroad"));
        this.properties.addAll(PropertyGroups.YELLOWPROPERTIES.propertySet);
        //this.properties.add(Go To Jail (Free space for now));
        this.properties.addAll(PropertyGroups.GREENPROPERTIES.propertySet);
        //this.properties.add(new RailRoadProperty("Short Line"));
        this.properties.add(PropertyGroups.DARKBLUEPROPERTIES.propertySet.get(0)); //Park Place
        //this.properties.add(Luxury Tax Property (-$100));
        this.properties.add(PropertyGroups.DARKBLUEPROPERTIES.propertySet.get(1)); //BoardWalk
    }

    public List<PropertyStreet> getPropertyGroup(String colour) {
        return PropertyGroups.valueOf(colour.replaceAll("\\s+","").toUpperCase() + "PROPERTIES").propertySet;
    }

    public Property getProperty(int index) {
        return this.properties.get(index);
    }

}