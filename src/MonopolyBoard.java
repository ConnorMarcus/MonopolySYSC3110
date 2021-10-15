import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MonopolyBoard {
    private List<Property> properties;
    private List<NormalProperty> brownProperties;
    private List<NormalProperty> lightBlueProperties;
    private List<NormalProperty> pinkProperties;
    private List<NormalProperty> orangeProperties;
    private List<NormalProperty> redProperties;
    private List<NormalProperty> yellowProperties;
    private List<NormalProperty> greenProperties;
    private List<NormalProperty> darkBlueProperties;

    public MonopolyBoard() {
        //this.brownProperties = new ArrayList<>(Arrays.asList(new NormalProperty("brown", "Mediterranean Avenue", 60), new NormalProperty("brown", "Baltic Avenue", 60)));
        //this.lightBlueProperties = new ArrayList<>(Arrays.asList(new NormalProperty("light blue", "Oriental Avenue", 100), new NormalProperty("light blue", "Vermont Avenue", 100), new NormalProperty("light blue", "Connecticut Avenue", 120)));
        this.properties = new ArrayList<>();
        this.properties.addAll(brownProperties);
    }

}
