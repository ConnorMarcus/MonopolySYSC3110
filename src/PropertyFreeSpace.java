public class PropertyFreeSpace extends Property {

    public PropertyFreeSpace(String name) {
        super(name);
    }

    @Override
    public void Landed(Player landedPlayer) {
        System.out.println("This is a free space in Milestone 1!");
    }
}
