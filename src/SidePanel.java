import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
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
    private MonopolyModel model;
    private List<JLabel> playerInfoLabels;


    /**
     * Constructor for SidePanel class.
     *
     * @param model The MonopolyModel that corresponds with the object.
     */
    public SidePanel(MonopolyModel model) {
        this.setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));
        this.setBackground(new Color(211, 236, 211));
        this.model = model;
        this.playerInfoLabels = new ArrayList<>();
        this.addPlayersInfo();
        this.addButtons(model);
        this.setVisible(true);
    }

    /**
     * Adds the players info Jlabels to the JPanel.
     */
    private void addPlayersInfo() {
        int numberOfPlayer = this.model.getPlayerList().size();
        for (Player p: this.model.getPlayerList()) {
            String text = "<html><br>Money: $" + p.getMoney() + "<br>Properties: " +  p.getPropertyString() + "</html>";
            JLabel info = new JLabel(text);
            info.setName(p.getIdentifier());

            info.setFont(new Font("Aharoni", Font.BOLD, 12 ));
            info.setPreferredSize(new Dimension(PANELWIDTH - 10, (INFOAREAHEIGHT / numberOfPlayer)));

            Border line = BorderFactory.createLineBorder(Color.black);
            TitledBorder title = BorderFactory.createTitledBorder(line,"Player " + p.getIdentifier());
            title.setTitleFont(new Font("Aharoni", Font.PLAIN, 20));
            title.setTitleJustification(TitledBorder.CENTER);

            info.setVerticalAlignment(JLabel.NORTH);
            info.setBorder(title);
            this.playerInfoLabels.add(info);
            this.add(info);
        }
    }

    /**
     * Updates a players information label.
     * @param player The player whose label is changing.
     */
    public void updatePlayerInfo(Player player) {
        JLabel playerLabel = null;
        for (JLabel label : this.playerInfoLabels) {
            if (label.getName().equals(player.getIdentifier())) {
                playerLabel = label;
                break;
            }
        }
        if (playerLabel == null) {
            throw new IllegalArgumentException("The Player object that was passed does not correspond with a player info label!");
        }
        else {
            playerLabel.setText("<html><br>Money: $" + player.getMoney() + "<br>Properties: " +  player.getPropertyString() + "</html>");
        }
    }

    /**
     * Removes a player's info from the SidePanel.
     * @param player The player whose label is being removed
     */
    public void removePlayerInfo(Player player) {
        JLabel playerLabel = null;
        for (JLabel label : this.playerInfoLabels) {
            if (label.getName().equals(player.getIdentifier())) {
                playerLabel = label;
                break;
            }
        }
        if (playerLabel == null) {
            throw new IllegalArgumentException("The Player object that was passed does not correspond with a player info label!");
        }
        else {
            this.playerInfoLabels.remove(playerLabel);
            this.remove(playerLabel);
            this.revalidate();
            for (JLabel label : this.playerInfoLabels) {
                label.setPreferredSize(new Dimension(PANELWIDTH - 10, (INFOAREAHEIGHT / playerInfoLabels.size())));
            }
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

    /**
     * Enables one of the SidePanel's buttons.
     * @param buttonText The text of the button to disable
     */
    public void enableButton(String buttonText) {
        for (Component c : this.getComponents()) {
            if (c instanceof JButton && ((JButton) c).getText().equals(buttonText)) {
                c.setEnabled(true);
                break;
            }
        }
    }

}
