import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * BoardPanel class which handles the game board.
 *
 * @author Connor Marcus
 */
public class BoardPanel extends JPanel {
    private final int PANEL_WIDTH = 693;
    private final int PANEL_HEIGHT = 693;
    private MonopolyBoard board;
    private Map<String, JLabel> playerLabelMap;
    private JLabel[] spaces;
    private JLabel dice1;
    private JLabel dice2;


    public BoardPanel(MonopolyBoard board, List<Player> players) {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(new Color(211, 236, 211));
        this.board = board;
        this.playerLabelMap = new HashMap<>();
        this.spaces = new JLabel[board.getNumProperties()];
        this.dice1 = new JLabel();
        this.dice2 = new JLabel();
        initializeSpaces();
        players.forEach(this::addPlayerLabel);
        addLabels();
    }

    /**
     * Initializes JPanel game board.
     */
    private void initializeSpaces() {
        for (int i=0; i<spaces.length; i++) {
            spaces[i] = new JLabel();
        }

        int x = 0;
        int y = 0;
        int WIDTH = PANEL_WIDTH /11;
        int HEIGHT = PANEL_HEIGHT /11;

        //Initialize top and bottom spaces
        for (int i=20; i<31; i++) {
            try {
                //All the top images are rotated 180 degrees
                BufferedImage image1 = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResource(String.format("images/%s.png", board.getProperty(i).getName()))));
                AffineTransform transform = new AffineTransform();
                transform.rotate(Math.PI, image1.getWidth() / 2, image1.getHeight() / 2);
                AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
                image1 = op.filter(image1, null);
                spaces[i].setIcon(new ImageIcon(image1));
                spaces[i].setBounds(x, y, spaces[i].getPreferredSize().width, spaces[i].getPreferredSize().height);
                this.add(spaces[i]);

                //All the bottom images do not need to be rotated
                BufferedImage image2 = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResource(String.format("images/%s.png", board.getProperty(30-i).getName()))));
                spaces[30-i].setIcon(new ImageIcon(image2));
                spaces[30-i].setBounds(x, y+ PANEL_HEIGHT -HEIGHT, spaces[30-i].getPreferredSize().width, spaces[30-i].getPreferredSize().height);
                this.add(spaces[30-i]);
            }
            catch (Exception e) {
                System.err.println("Error loading image! " + e.getMessage());
                System.exit(1);
            }
            x += WIDTH;
        }

        x -= WIDTH;
        y += HEIGHT;

        //Initialize right and left spaces
        for (int i=31; i<40; i++) {
            try {
                //All the right images are rotated 270 degrees
                BufferedImage image1 = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResource(String.format("images/%s.png", board.getProperty(i).getName()))));
                AffineTransform transform = new AffineTransform();
                transform.rotate(1.5*Math.PI, image1.getWidth() / 2, image1.getHeight() / 2);
                AffineTransformOp transformOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
                image1 = transformOp.filter(image1, null);
                spaces[i].setIcon(new ImageIcon(image1));
                spaces[i].setBounds(x, y, spaces[i].getPreferredSize().width, spaces[i].getPreferredSize().height);
                this.add(spaces[i]);

                //All the left images are rotated by 90 degrees
                BufferedImage image2 = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResource(String.format("images/%s.png", board.getProperty(50-i).getName()))));
                AffineTransform transform2 = new AffineTransform();
                transform2.rotate(Math.PI/2, image2.getWidth() / 2, image2.getHeight() / 2);
                AffineTransformOp transformOp2 = new AffineTransformOp(transform2, AffineTransformOp.TYPE_BILINEAR);
                image2 = transformOp2.filter(image2, null);
                spaces[50-i].setIcon(new ImageIcon(image2));
                spaces[50-i].setBounds(x- PANEL_WIDTH +WIDTH, y, spaces[50-i].getPreferredSize().width, spaces[50-i].getPreferredSize().height);
                this.add(spaces[50-i]);
            }
            catch (Exception e) {
                System.err.println("Error loading image!");
                System.exit(1);
            }
            y += WIDTH;
        }
    }

    /**
     * Adds the dice and Monopoly labels to the JPanel
     */
    private void addLabels() {
        //Adds dice logos to board
        JLabel logo = new JLabel();
        logo.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("images/logo.png"))));

        logo.setBounds(PANEL_WIDTH /5, PANEL_HEIGHT /5, 395, 395);
        this.add(logo);

        //Sets bounds and adds dice Jlabels
        this.dice1.setBounds(PANEL_WIDTH /2+100, PANEL_HEIGHT /2+100, 55, 55);
        this.add(this.dice1);

        this.dice2.setBounds(PANEL_WIDTH /2+150, PANEL_WIDTH /2+150, 55, 55);
        this.add(this.dice2);
    }

    /**
     * Moves the Player icons position on the board.
     *
     * @param player the player object being moved.
     */
    public void updatePlayerLabelPosition(Player player) {
        JLabel playerLabel = this.playerLabelMap.get(player.getIdentifier());
        if (playerLabel == null) {
            throw new IllegalArgumentException("The Player object that was passed does not correspond with a player label!");
        }
        else {
            int xPos = spaces[player.getPosition()].getX();
            int yPos = spaces[player.getPosition()].getY();
            playerLabel.setBounds(xPos, yPos, playerLabel.getPreferredSize().width, playerLabel.getPreferredSize().height);
        }
    }

    /**
     * Adds player label to Map of playerLabels.
     *
     * @param player the Player object getting added.
     */
    private void addPlayerLabel(Player player) {
        if (player != null) {
            JLabel playerLabel = new JLabel();
            playerLabel.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource(player.getPlayerImageFile()))));
            this.playerLabelMap.put(player.getIdentifier(), playerLabel);
            this.add(playerLabel);
            this.setComponentZOrder(playerLabel, 0);
            updatePlayerLabelPosition(player);
        }
        else {
            throw new IllegalArgumentException("Cannot pass null Player!");
        }
    }

    /**
     * Removes player label from list of playerLabels.
     *
     * @param player the Player object getting removed.
     */
    public void removePlayerLabel(Player player) {
        JLabel playerLabel = this.playerLabelMap.remove(player.getIdentifier());
        if (playerLabel != null) {
            this.remove(playerLabel);
            this.repaint();
        }
        else {
            throw new IllegalArgumentException("The Player object that was passed does not correspond with a player label!");
        }
    }

    /**
     * Updates the dice Jlabel image icon.
     *
     * @param d1 int roll of dice 1
     * @param d2 int roll of dice 2
     */
    public void updateDice(int d1, int d2) {
        this.dice1.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("images/" + d1 + ".png"))));
        this.dice2.setIcon(new ImageIcon(Objects.requireNonNull(this.getClass().getClassLoader().getResource("images/" + d2 + ".png"))));
    }

}
