import javax.swing.*;

/**
 * Free space/Starting Property Object.
 *
 * @author Vahid Foroughi
 */
public class PropertyFreeSpace extends Property {

    /**
     * Constructor for PropertyFreeSpace object.
     *
     * @param name String name for PropertyFreeSpace object.
     */
    public PropertyFreeSpace(String name) {
        super(name);
    }


    /**
     * Outputs free space message.
     *
     * @param landedPlayer the Player object that landed on the property.
     */
    @Override
    public void landed(Player landedPlayer) {
        JOptionPane.showMessageDialog(null, "This is a free space!");
    }
}
