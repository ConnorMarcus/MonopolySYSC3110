import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BoardPanel extends JPanel {
    private final int PANELWIDTH = 693;
    private final int PANELHEIGHT = 693;
    private MonopolyBoard board;
    private List<JLabel> playerLabels;
    private JLabel[] spaces;

    public BoardPanel(MonopolyBoard board) {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(PANELWIDTH, PANELHEIGHT));
        this.board = board;
        this.playerLabels = new ArrayList<>();
        this.spaces = new JLabel[board.getNumProperties()];
        initializeLabels();
    }

    private void initializeLabels() {
        for (int i=0; i<spaces.length; i++) {
            spaces[i] = new JLabel();
        }
        int x = 0;
        int y = 0;
        int WIDTH = PANELWIDTH/11;
        int HEIGHT = PANELHEIGHT/11;

        //Initialize top and bottom spaces
        for (int i=20; i<31; i++) {
            try {
                //All the top images are rotated 180 degrees
                BufferedImage image1 = ImageIO.read(new File(String.format("images/%s.png", board.getProperty(i).getName())));
                AffineTransform transform = new AffineTransform();
                transform.rotate(Math.PI, image1.getWidth() / 2, image1.getHeight() / 2);
                AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
                image1 = op.filter(image1, null);
                spaces[i].setIcon(new ImageIcon(image1));
                spaces[i].setBounds(x, y, spaces[i].getPreferredSize().width, spaces[i].getPreferredSize().height);
                this.add(spaces[i]);

                BufferedImage image2 = ImageIO.read(new File(String.format("images/%s.png", board.getProperty(30-i).getName())));
                spaces[30-i].setIcon(new ImageIcon(image2));
                spaces[30-i].setBounds(x, y+PANELHEIGHT-HEIGHT, spaces[30-i].getPreferredSize().width, spaces[30-i].getPreferredSize().height);
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
                BufferedImage image1 = ImageIO.read(new File(String.format("images/%s.png", board.getProperty(i).getName())));
                AffineTransform transform = new AffineTransform();
                transform.rotate(1.5*Math.PI, image1.getWidth() / 2, image1.getHeight() / 2);
                AffineTransformOp transformOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
                image1 = transformOp.filter(image1, null);
                spaces[i].setIcon(new ImageIcon(image1));
                spaces[i].setBounds(x, y, spaces[i].getPreferredSize().width, spaces[i].getPreferredSize().height);
                this.add(spaces[i]);

                //All the left images are rotated by 90 degrees
                BufferedImage image2 = ImageIO.read(new File(String.format("images/%s.png", board.getProperty(50-i).getName())));
                AffineTransform transform2 = new AffineTransform();
                transform2.rotate(Math.PI/2, image2.getWidth() / 2, image2.getHeight() / 2);
                AffineTransformOp transformOp2 = new AffineTransformOp(transform2, AffineTransformOp.TYPE_BILINEAR);
                image2 = transformOp2.filter(image2, null);
                spaces[50-i].setIcon(new ImageIcon(image2));
                spaces[50-i].setBounds(x-PANELWIDTH+WIDTH, y, spaces[50-i].getPreferredSize().width, spaces[50-i].getPreferredSize().height);
                this.add(spaces[50-i]);
            }
            catch (Exception e) {
                System.err.println("Error loading image!");
                System.exit(1);
            }
            y += WIDTH;
        }
    }

    public void updatePlayerLabelPosition(Player player) {
        JLabel playerLabel = null;
        for (JLabel label : this.playerLabels) {
            if (label.getName().equals(player.getIdentifier())) {
                playerLabel = label;
            }
        }
        if (playerLabel == null) {
            throw new IllegalArgumentException("The Player object that was passed does not correspond with a player label!");
        }
        else {
            int xPos = spaces[player.getPosition()].getX();
            int yPos = spaces[player.getPosition()].getY();
            playerLabel.setBounds(xPos, yPos, playerLabel.getPreferredSize().width, playerLabel.getPreferredSize().height);
        }
    }

    public void addPlayerLabel(Player player) {
        JLabel playerLabel = new JLabel();
        playerLabel.setName(player.getIdentifier());
        playerLabel.setIcon(new ImageIcon(player.getPlayerImageFile()));
        this.playerLabels.add(playerLabel);
        this.add(playerLabel);
        this.setComponentZOrder(playerLabel, 0);
        updatePlayerLabelPosition(player);
    }

}
