import javax.swing.*;
import java.awt.*;

/**
 * The GameLogPanel class used to display text to the users.
 * @author Noah Hammoud
 */
public class GameLogPanel extends JPanel {
    private final int PANEL_WIDTH = 1043;
    private final int PANEL_HEIGHT = 40;
    private JLabel gameLog;

    /**
     * Constructor for GameLogPanel class.
     */
    public GameLogPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(new Color(211, 236, 211));
        gameLog = new JLabel("Welcome to Monopoly!  Please roll to start the game");
        gameLog.setHorizontalAlignment(SwingConstants.CENTER);
        gameLog.setPreferredSize(new Dimension(1025, 30));
        gameLog.setOpaque(true);
        gameLog.setBackground(Color.WHITE);
        this.add(gameLog);

    }

    /**
     * Updates the GameLog Jlabel text.
     *
     * @param text the string that will be displayed
     */
    public void updateGameLog(String text) {
        gameLog.setText(text);
    }
}
