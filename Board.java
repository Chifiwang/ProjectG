import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener{

    Debugg debugg = new Debugg();
    Player player;
    Image img;
    Timer t;

    /* this is where most of the bg and stuff are init'd */
    public Board() {
        player = new Player();

        addKeyListener(new AL());
        setFocusable(true);

        ImageIcon background = new ImageIcon("Assets/test.jpg");
        img = background.getImage();

        t = new Timer(5, this);
        t.start();
    }

    /* Player loop thing. I think that this is the main loop for interacting with things */
    @Override
    public void actionPerformed(ActionEvent e) {
        // player.jump();
        player.move();
        repaint();
        
    }
    /* paints the graphics that are displayed on the screen */
    public void paint(Graphics g) {

        super.paint(g);
            Graphics2D g2D = (Graphics2D) g;

            g2D.drawImage(img, 0, 0, null);
            g2D.drawImage(player.getImage(), player.getX(), player.getY(), null);
    }

    /* Calls the key listener methods defined in the player class */
    private class AL extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }
}