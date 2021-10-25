import javax.swing.*;

public class MonopolyView extends JFrame {
    private BoardPanel boardPanel;

    public MonopolyView(String title) {
        super(title);
        boardPanel = new BoardPanel(new MonopolyBoard());
        Player testPlayer = new Player("1");
        boardPanel.addPlayerLabel(testPlayer);
        boardPanel.updatePlayerLabelPosition(testPlayer);
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
