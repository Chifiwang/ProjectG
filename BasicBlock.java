import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.*;

public class BasicBlock {
    ImageIcon bDefault = new ImageIcon("Assets/basicBlock_default.png");
    Debugg debugg = new Debugg();
    boolean isDead = false;

    int scaleFactor = 100;
    int x, y, i;
    int timeDelay = 300;
    long timeStart, dt = 0;
    Image bIcon;
    Map map;

    public BasicBlock(Map map, int i) {
        debugg.print(i);
        bIcon = bDefault.getImage();
        this.map = map;
        x = posX(i);
        y = posY(i);
        this.i = i;
    }

    public void move() {
        // jump();
        // x += dx;
        // y += dy;

    }
    
    public int posX(int i) {return (map.basicCoords.get(i) % map.MAP_TEST_COORD_GET[0])*scaleFactor;}
    public int posY(int i) {return (map.basicCoords.get(i) / map.MAP_TEST_COORD_GET[0])*scaleFactor;}
    public int getX() {return x;}
    public int getY() {return y;}
    public Image getImage() {return bIcon;}

    /* reads key presses */

    public void keyPressed(KeyEvent e) {
        
        if (dt >= timeDelay) {
            timeStart = System.currentTimeMillis();
            char key = e.getKeyChar();
            // debugg.print( map.map[map.basicCoords.get(i) - 1]);
            switch(key) {
                case 'w': 
                if (y >= 0 && map.basicCoords.get(i) + map.MAP_TEST_COORD_GET[0] < map.map.length && map.map[map.basicCoords.get(i) + map.MAP_TEST_COORD_GET[0]] == 'p') {
                        map.map[map.playerCoords - map.MAP_TEST_COORD_GET[0]] = 'u';
                        map.basicCoords.set(i, map.basicCoords.get(i) - map.MAP_TEST_COORD_GET[0]);
                        map.map[map.playerCoords] = ' ';
                        y -= scaleFactor;
                    } break;
                case 'a': 
                    if (x >= 0 && map.basicCoords.get(i) + 1 < map.map.length && map.map[map.basicCoords.get(i) + 1] == 'p') {
                        map.map[map.playerCoords - 1] = 'u';
                        map.basicCoords.set(i, map.basicCoords.get(i) - 1);
                        map.map[map.playerCoords] = ' ';
                        x -= scaleFactor;
                    } break;
                case 's':
                    if (y < (map.MAP_TEST_COORD_GET[1])*scaleFactor && map.basicCoords.get(i) - map.MAP_TEST_COORD_GET[0] >= 0 && map.map[map.basicCoords.get(i) - map.MAP_TEST_COORD_GET[0]] == 'p') {
                        map.map[map.playerCoords + map.MAP_TEST_COORD_GET[0]] = 'u';
                        map.basicCoords.set(i, map.basicCoords.get(i) + map.MAP_TEST_COORD_GET[0]);
                        map.map[map.playerCoords] = ' ';
                        y += scaleFactor;
                    } break;
                case 'd':
                    if (x < (map.MAP_TEST_COORD_GET[0])*scaleFactor && map.basicCoords.get(i) - 1 >= 0 && map.map[map.basicCoords.get(i) - 1] == 'p') {
                        map.map[map.basicCoords.get(i) + 1] = 'u';
                        map.basicCoords.set(i, map.basicCoords.get(i) + 1);
                        map.map[map.basicCoords.get(i)] = ' ';
                        x += scaleFactor;
                    } break;
            }
            // debugg.print(map.map[map.basicCoords.get(i) - 1]);
        }

        dt = System.currentTimeMillis() - timeStart;
            
        // return ' ';
    }

    /* reads key releases */
    public void keyReleased(KeyEvent e) { dt = 0; }
}
