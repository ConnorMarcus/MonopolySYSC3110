import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MonopolyBoard {
    private List<Property> properties;

    private enum PropertyGroups {
        BROWNPROPERTIES(Arrays.asList(new NormalProperty("brown", "Mediterranean Avenue", 60), new NormalProperty("brown", "Baltic Avenue", 60))),
        LIGHTBLUEPROPERTIES(Arrays.asList(new NormalProperty("light blue", "Oriental Avenue", 100), new NormalProperty("light blue", "Vermont Avenue", 100), new NormalProperty("light blue", "Connecticut Avenue", 120))),
        PINKPROPERTIES(Arrays.asList(new NormalProperty("pink", "St. Charles Place", 140), new NormalProperty("pink", "States Avenue", 140), new NormalProperty("pink", "Virginia Avenue", 160))),
        ORANGEPROPERTIES(Arrays.asList(new NormalProperty("orange", "St. James Place", 180), new NormalProperty("orange", "Tennessee Avenue", 180), new NormalProperty("orange", "New York Avenue", 200))),
        REDPROPERTIES(Arrays.asList(new NormalProperty("red", "Kentucky Avenue", 220), new NormalProperty("red", "Indiana Avenue", 220), new NormalProperty("red", "Illinois Avenue", 240))),
        YELLOWPROPERTIES(Arrays.asList(new NormalProperty("yellow", "Atlantic Avenue", 260), new NormalProperty("yellow", "Ventnor Avenue", 260), new NormalProperty("yellow", "Marvin Gardens", 280))),
        GREENPROPERTIES(Arrays.asList(new NormalProperty("green", "Pacific Avenue", 300), new NormalProperty("green", "North Carolina Avenue", 300), new NormalProperty("green", "Pennsylvania Avenue", 320))),
        DARKBLUEPROPERTIES(Arrays.asList(new NormalProperty("dark blue", "Park Place", 350), new NormalProperty("dark blue", "Boardwalk", 400)));

        private List<NormalProperty> propertySet;

        PropertyGroups(List<NormalProperty> propertySet) {
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

    public List<NormalProperty> getPropertyGroup(String colour) {
        return PropertyGroups.valueOf(colour.replaceAll("\\s+","").toUpperCase() + "PROPERTIES").propertySet;
    }

    public Property getProperty(int index) {
        return this.properties.get(index);
    }

}
