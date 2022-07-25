import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics2D;

public class TextEventHandler {
    static JPanel panel;
    static int[] sizeX = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
    static int[] sizeY = {50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
    static String tutorial1_text = "Welcome to ProjectG.\n To move the character use the 'W', 'A', 'S', and 'D' keys\n to move the player up, down, left, and right respectively.\n See that blue box? Run into it to push it.\n Your goal, as the player, is to push all the boxes off the map.\n Don't worry, I will guide you through each of the block's mechanics as we go along.          ";
    public static void initiateEvent(int type, JPanel p) {
        panel = p;
        switch (type) {
            case 0:
                gameTutorial1();
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default:
                break;
        }
    }

    public static void gameTutorial1() {
        AnimationHandeler.queueAnimation(1, 100, 200);

    }

    public static void gameTutorial1_text(Integer[] key, Graphics2D g2D) {
        int x = 1350, y = 400;
        g2D.drawRect(x, y, 200, 100);
        
        for (String line : tutorial1_text.substring(0, AnimationHandeler.frame).split("\n"))
            g2D.drawString(line, x, y += g2D.getFontMetrics().getHeight());
    }
}
