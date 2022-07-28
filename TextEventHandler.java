import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics2D;

public class TextEventHandler {
    static ImageIcon textBox1 = new ImageIcon("Assets\\textBox.png");
    static JPanel panel;
    static int[] sizeX = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
    static int[] sizeY = {50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
    // static int x = 200, y = 650;
    static String tutorial1_text = "   Welcome to ProjectG. To move the character use the 'W', 'A', 'S', and 'D' keys to move the player\n   up, down, left, and right respectively. See that blue box? It's called a \"Universal Block\". Run into\n   it to push it in any direction. Your goal, as the player, is to push all the boxes off the map. Don't\n   worry, I will guide you through each of the block's mechanics as we go along.          ";
    static String tutorial2_text = "   Hello there          ";
    public static void initiateEvent(int type, JPanel p) {
        panel = p;
        AnimationHandeler.queueAnimation(type + 1, 100, 200);
    }

    // public static void gameTutorial1() {
    //     AnimationHandeler.queueAnimation(1, 100, 200);

    // }

    public static void gameTutorial(Integer[] key, Graphics2D g2D) {
        int x = 200, y = 660;
        g2D.drawImage(textBox1.getImage(), 200, 653, null);

        String[] buff;
        switch (key[0]) {
            case 1:
                buff = tutorial1_text.substring(0, AnimationHandeler.frame).split("\n");
                // Debug.print(x + ", " + y + " Frame:" + AnimationHandeler.frame);
                if (AnimationHandeler.frame >= tutorial1_text.indexOf("character")) {
                    g2D.drawImage(Player.getImage(), 5*GameFrame.scaleFactor, 3*GameFrame.scaleFactor, null);
                }
                if (AnimationHandeler.frame >= tutorial1_text.indexOf("box?")) {
                    g2D.drawImage(Block.getImage('u'), 7*GameFrame.scaleFactor, 3*GameFrame.scaleFactor, null);
                }
                for(int i = 0; i < buff.length; i++) {
                    g2D.drawString(buff[i], x, y += g2D.getFontMetrics().getHeight());
                }
                break;
            case 2:
                buff = tutorial2_text.substring(0, AnimationHandeler.frame).split("\n");
                // Debug.print(x + ", " + y + " Frame:" + AnimationHandeler.frame);
        
                for(int i = 0; i < buff.length; i++) {
                    g2D.drawString(buff[i], x, y += g2D.getFontMetrics().getHeight());
                }
            default:
                break;
        }

        
            
    }
}

