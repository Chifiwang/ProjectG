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

    int scaleFactor = 100;
    int x, y;
    int timeDelay = 300;
    long timeStart, dt = 0;
    boolean isjump = false;
    Image pIcon;
    Map map;

    public Player(Map map) {
        pIcon = pDefault.getImage();
        this.map = map;
        x = posX();
        y = posY();
    }

    public void move() {
        // jump();
        // x += dx;
        // y += dy;

    }
    
    public int posX() {return (map.playerCoords % map.MAP_TEST_COORD_GET[0])*scaleFactor;}
    public int posY() {return (map.playerCoords / map.MAP_TEST_COORD_GET[0])*scaleFactor;}
    public int getX() {return x;}
    public int getY() {return y;}
    public Image getImage() {return pIcon;}

    /* reads key presses */

    public void keyPressed(KeyEvent e) {
        
        if (dt >= timeDelay) {
            timeStart = System.currentTimeMillis();
            char key = e.getKeyChar();
            switch(key) {
                case 'w': 
                    if (y > 0) {
                        map.map[map.playerCoords - map.MAP_TEST_COORD_GET[0]] = 'p';
                        map.map[map.playerCoords] = ' ';
                        map.playerCoords -= map.MAP_TEST_COORD_GET[0];
                        y -= scaleFactor;
                    } break;
                case 'a': 
                    if (x > 0) {
                        map.map[map.playerCoords - 1] = 'p';
                        map.map[map.playerCoords] = ' ';
                        map.playerCoords--;
                        x -= scaleFactor;
                    } break;
                case 's':
                    if (y < (map.MAP_TEST_COORD_GET[1]-1)*scaleFactor) {
                        map.map[map.playerCoords + map.MAP_TEST_COORD_GET[0]] = 'p';
                        map.map[map.playerCoords] = ' ';
                        map.playerCoords += map.MAP_TEST_COORD_GET[0];
                        y += scaleFactor;
                    } break;
                case 'd':
                    if (x < (map.MAP_TEST_COORD_GET[0]-1)*scaleFactor) {
                        map.map[map.playerCoords + 1] = 'p';
                        map.map[map.playerCoords] = ' ';
                        map.playerCoords++;
                        x += scaleFactor;
                    } break;
            }
            // debugg.print(dt + " " + timeStart);
            // return key;
        }

        dt = System.currentTimeMillis() - timeStart;
            
        // return ' ';
    }

    /* reads key releases */
    public void keyReleased(KeyEvent e) { dt = 0; }
}
