import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener{
    Debugg debugg = new Debugg();
    Player player;
    Image img;
    Timer t;

    public Board() {
        player = new Player();
        addKeyListener(new AL());
        setFocusable(true);
        ImageIcon icon = new ImageIcon("Assets/test.jpg");
        img = icon.getImage();
        t = new Timer(5, this);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player.move();
        repaint();
        
    }

    public void paint(Graphics g) {
        super.paint(g);
            Graphics2D g2D = (Graphics2D) g;
            g2D.drawImage(img, 0, 0, null);
            g2D.drawImage(player.getImage(), player.getX(), player.getY(), null);
    }

    private class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }
    }
}
