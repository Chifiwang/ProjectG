import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;

import legacy.Debug;

import java.awt.Graphics2D;

public class TextEventHandler {

    static ImageIcon textBox1 = new ImageIcon("Assets\\textBox.png");
    static JPanel panel;

    // Tutorial text strings
    static String tutorial1_text = "   Welcome to ProjectG. To move the character use the 'W', 'A', 'S', and 'D' keys to move the player\n   up, down, left, and right respectively. See that blue box? It's called a \"Universal Block\". Run into\n   it to push it in any direction. Your goal, as the player, is to push all the boxes off the map. Don't\n   worry, I will guide you through each of the block's mechanics as we go along.          ";
    static String tutorial2_text = "   Hello again player. You seem to be getting the hang of the controls now. Ok, this is your first new block.\n   This block is called the \"Immoveable Block.\" You cant move it; that's it. Try to work around\n   this obstacle when encountering it. Good Luck player!           ";

    /** 
     * Queues a text event through the animator's event handeler
     * 
     * @param type provides the specific code for the tutorial that needs to be played
     * @param p sets the specific Jpannel that needs to be drawn on for future use
     */
    public static void initiateEvent(int type, JPanel p) {
        panel = p;
        AnimationHandeler.queueAnimation(type + 1, 100, 200);
    }

    
    /** 
     * uses the tutorial key to display the correct tutorial message
     * 
     * @param key provides some key details about the tutorial
     * @param g2D provides the class to display the images with
     */
    public static void gameTutorial(Integer[] key, Graphics2D g2D) {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int offset = (int) (size.getWidth() - 1408)/2;
        int x = 200, y = 660;
        g2D.drawImage(textBox1.getImage(), 200, 653, null);

        String[] buff;
        switch (key[0]) {
            case 1:
                buff = tutorial1_text.substring(0, AnimationHandeler.frame).split("\n");
                if (AnimationHandeler.frame >= tutorial1_text.indexOf("character")) {
                    g2D.drawImage(Player.getImage(), 5*GameFrame.scaleFactor + offset, 3*GameFrame.scaleFactor, null);
                }
                if (AnimationHandeler.frame >= tutorial1_text.indexOf("box?")) {
                    g2D.drawImage(Block.getImage('u'), 7*GameFrame.scaleFactor + offset, 3*GameFrame.scaleFactor, null);
                }
                for(int i = 0; i < buff.length; i++) {
                    g2D.drawString(buff[i], x, y += g2D.getFontMetrics().getHeight());
                }
                break;
            case 2:
                buff = tutorial2_text.substring(0, AnimationHandeler.frame).split("\n");
                if (AnimationHandeler.frame >= tutorial1_text.indexOf("block")) {
                    g2D.drawImage(Block.getImage('x'), 7*GameFrame.scaleFactor + offset, 3*GameFrame.scaleFactor, null);
                }
                for(int i = 0; i < buff.length; i++) {
                    g2D.drawString(buff[i], x, y += g2D.getFontMetrics().getHeight());
                }
            default:
                break;
        }   
    }
}

