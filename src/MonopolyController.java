import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller for the MVC
 * @author Connor Marcus
 */
public class MonopolyController implements ActionListener {
    private MonopolyModel model;

    /**
     * Constructor for the class
     * @param model The MonopolyModel that this class acts as the controller for.
     */
    public MonopolyController(MonopolyModel model) {
        this.model = model;
    }

    /**
     * Overrides the actionPerformed method from the ActionListener interface.
     * @param e The ActionEvent that triggered this method.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            if (((JButton)e.getSource()).getText().equals("Roll")) {
                ((JButton)e.getSource()).setEnabled(false);
                this.model.takeTurn();
            }
            else if (((JButton)e.getSource()).getText().equals("Pass")) {
                ((JButton)e.getSource()).setEnabled(false);
                this.model.passTurn();
            }
        }

    }
}
