import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.*;

public class Player {
    Debugg debugg = new Debugg();
    int x, dx, y, dy;
    Image pIcon;

    public Player() {
        ImageIcon iStart = new ImageIcon("Assets/player_default.png");
        pIcon = iStart.getImage();
        x = 10;
        y = 172;

    }

    public void move() {
        x += dx;
        y += dy;
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public Image getImage() {return pIcon;}

    public void keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        debugg.print(key);

        switch(key) {
            case 'w': dy = -1; break;
            case 'a': dx = -1; break;
            case 's': dy = 1; break;
            case 'd': dx = 1; break;
        }

    }

    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        debugg.print(key);

        switch(key) {
            case 'w': dy = 0; break;
            case 'a': dx = 0; break;
            case 's': dy = 0; break;
            case 'd': dx = 0; break;
        }

    }
}
