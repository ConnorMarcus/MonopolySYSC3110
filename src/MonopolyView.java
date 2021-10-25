import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * MonopolyView class which handles the gui.
 *
 * @author Connor Marcus
 */
public class MonopolyView extends JFrame {
    private BoardPanel boardPanel;
    private SidePanel sidePanel;

    /**
     * Constructor for MonopolyView class.
     *
     * @param title String title of JFrame
     */
    public MonopolyView(String title) {
        super(title);
        boardPanel = new BoardPanel(new MonopolyBoard());
        Player testPlayer = new Player("1", "images/player1.png");
        Player testPlayer2 = new Player("2", "images/player2.png");
        Player testPlayer3 = new Player("3", "images/player3.png");
        Player testPlayer4 = new Player("4", "images/player4.png");
        boardPanel.addPlayerLabel(testPlayer);
        boardPanel.addPlayerLabel(testPlayer2);
        boardPanel.addPlayerLabel(testPlayer3);
        boardPanel.addPlayerLabel(testPlayer4);

        ArrayList<Player> players = new ArrayList<>();
        players.add(testPlayer);
        players.add(testPlayer2);
        players.add(testPlayer3);
        players.add(testPlayer4);
        sidePanel = new SidePanel(players);

        this.setLayout(new FlowLayout());
        this.add(boardPanel);
        this.add(sidePanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Main method to run game.
     *
     * @param args String[] input for main method
     */
    public static void main(String[] args) {
        MonopolyView mv = new MonopolyView("Monopoly");
    }
}
