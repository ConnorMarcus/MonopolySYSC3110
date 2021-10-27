import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

/**
 * SidePanel class used to display Players info and buttons.
 *
 * @author Noah Hammoud
 */
public class SidePanel extends JPanel {
    private final int PANELWIDTH = 350;
    private final int PANELHEIGHT = 693;
    private final int INFOAREAHEIGHT = 620;
    private List<Player> players;


    /**
     * Constructor for SidePanel class.
     *
     * @param model The MonopolyModel that corresponds with the object.
     */
    public SidePanel(MonopolyModel model) {
        this.setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));
        this.setBackground(new Color(211, 236, 211));
        this.players = model.getPlayerList();
        this.addPlayersInfo();
        this.addButtons(model);
        this.setVisible(true);
    }

    /**
     * Adds the players info Jlabels to the JPanel.
     */
    private void addPlayersInfo() {
        int numberOfPlayer = this.players.size();
        for (Player p: this.players) {
            String text = "<html><br>Money: $" + p.getMoney() + "<br>Properties: " +  p.getPropertyString() + "</html>";
            JLabel info = new JLabel(text);

            info.setFont(new Font("Aharoni", Font.BOLD, 12 ));
            info.setPreferredSize(new Dimension(PANELWIDTH - 10, (INFOAREAHEIGHT / numberOfPlayer)));

            Border line = BorderFactory.createLineBorder(Color.black);
            TitledBorder title = BorderFactory.createTitledBorder(line,"Player " + p.getIdentifier());
            title.setTitleFont(new Font("Aharoni", Font.PLAIN, 20));
            title.setTitleJustification(TitledBorder.CENTER);

            info.setVerticalAlignment(JLabel.NORTH);
            info.setBorder(title);
            this.add(info);
        }

    }

    /**
     * Adds the buttons to the JPanel.
     */
    private void addButtons(MonopolyModel model) {
        Font buttonFont = new Font("arial",Font.BOLD, 25);
        Dimension buttonSize = new Dimension(100, 40);
        MonopolyController controller = new MonopolyController(model);

        JButton roll = new JButton("Roll");
        roll.setFont(buttonFont);
        roll.setFocusPainted(false);
        roll.setPreferredSize(buttonSize);
        roll.addActionListener(controller);

        JButton pass = new JButton("Pass");
        pass.setFont(buttonFont);
        pass.setFocusPainted(false);
        pass.setPreferredSize(buttonSize);
        pass.setEnabled(false);
        pass.addActionListener(controller);

        JButton buy = new JButton("Buy");
        buy.setFont(buttonFont);
        buy.setFocusPainted(false);
        buy.setPreferredSize(buttonSize);
        buy.setEnabled(false);

        this.add(roll);
        this.add(pass);
        this.add(buy);
    }

    public void enableButton(String buttonText) {
        for (Component c : this.getComponents()) {
            if (c instanceof JButton && ((JButton) c).getText().equals(buttonText)) {
                ((JButton) c).setEnabled(true);
            }
        }
    }

}
