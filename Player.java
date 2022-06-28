import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.*;

public class Player/*  implements event */{
/*     private class CException extends Exception {
        public CException(String s) {
            super(s);
        }
    } */

    ImageIcon pDefault = new ImageIcon("Assets/player_default.png");
    ImageIcon pJump = new ImageIcon("Assets/player_jump.jpg");
    Debugg debugg = new Debugg();
    
    int x, dx, y, dy;
    boolean isjump = false;
    Image pIcon;

    public Player() {
        pIcon = pDefault.getImage();
        x = 10;
        y = 172;

    }

    public void move() {
        // jump();
        x += dx;
        y += dy;
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public Image getImage() {return pIcon;}

    /* reads key presses */
    public char keyPressed(KeyEvent e) {
        char key = e.getKeyChar();
        debugg.print(key + " " + isjump);

        switch(key) {
            case 'w': dy = -1; break;
            case 'a': dx = -1; break;
            case 's': dy = 1; break;
            case 'd': dx = 1; break;
            case ' ': isjump = true; break;
        }
        return key;
    }

    /* reads key releases */
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        debugg.print(key + " " + isjump);

        switch(key) {
            case 'w': dy = 0; break;
            case 'a': dx = 0; break;
            case 's': dy = 0; break;
            case 'd': dx = 0; break;
            case ' ': isjump = false;
                      dy = 0; break;
        }

    }

    // public void jump() {
    //     long tInit, dt, sleep;
    //     pIcon = pJump.getImage();

    //     tInit = System.currentTimeMillis();

    //     do {
    //         dt = System.currentTimeMillis() - tInit;
    //         sleep = 10 - dt;
    //         if (sleep < 0) {sleep = 2;}
    //         try {
    //             Thread.sleep(sleep);
    //         } catch(Exception e) { }

    //         dy = (int) jump(dt);
    //         debugg.print(dt);
    //     } while (isjump && dt < 0);
    // }

    // public long jump (long t) {
    //     return -2*t + 3;
    // }
}
