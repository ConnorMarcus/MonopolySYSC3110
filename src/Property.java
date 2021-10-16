/**
 * Property class
 *
 * @author Vahid Foroughi
 */
public abstract class Property {
    private String name;
    public abstract void Landed(Player owner, Player landedPlayer);

    public Property(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s", this.name);
    }

}
