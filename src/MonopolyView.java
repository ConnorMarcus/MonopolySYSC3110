import javax.swing.*;

public class MonopolyView extends JFrame {
    private BoardPanel boardPanel;

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

        this.add(boardPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        MonopolyView mv = new MonopolyView("Monopoly");
    }
}
