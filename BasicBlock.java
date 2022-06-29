import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.*;

public class BasicBlock {
    ImageIcon bDefault = new ImageIcon("Assets/basicBlock_default.png");
    Debugg debugg = new Debugg();
    boolean isDead = false;

    int scaleFactor = 100;
    int x, y, i;
    int isMove = -1;
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
        if (dt >= timeDelay) {
            timeStart = System.currentTimeMillis();
            switch (isMove) {
                case 1:
                    map.map[map.basicCoords.get(i) - map.MAP_TEST_COORD_GET[0]] = 'u';
                    map.map[map.basicCoords.get(i)] = ' ';
                    map.basicCoords.set(i, map.basicCoords.get(i) - map.MAP_TEST_COORD_GET[0]);
                    y -= scaleFactor;
                    break;
                case 2: 
                    map.map[map.basicCoords.get(i) - 1] = 'u';
                    map.map[map.basicCoords.get(i)] = ' ';
                    map.basicCoords.set(i, map.basicCoords.get(i) - 1);
                    x -= scaleFactor;
                    break;
                case 3:
                    map.map[map.basicCoords.get(i) + map.MAP_TEST_COORD_GET[0]] = 'u';
                    map.map[map.basicCoords.get(i)] = ' ';
                    map.basicCoords.set(i, map.basicCoords.get(i) + map.MAP_TEST_COORD_GET[0]);
                    y += scaleFactor;
                    break;
                case 4:
                    map.map[map.basicCoords.get(i) + 1] = 'u';
                    map.map[map.basicCoords.get(i)] = ' ';
                    map.basicCoords.set(i, map.basicCoords.get(i) + 1);
                    x += scaleFactor;
                    break;
            }
            debugg.print(isMove);
            debugg.printMap(map.map);
        }

        dt = System.currentTimeMillis() - timeStart;
    }
    
    public int posX(int i) {return (map.basicCoords.get(i) % map.MAP_TEST_COORD_GET[0])*scaleFactor;}
    public int posY(int i) {return (map.basicCoords.get(i) / map.MAP_TEST_COORD_GET[0])*scaleFactor;}
    public int getX() {return x;}
    public int getY() {return y;}
    public Image getImage() {return bIcon;}

    /* reads key presses */

    public void keyPressed(KeyEvent e) {
        
        char key = e.getKeyChar();
        // debugg.print( map.map[map.basicCoords.get(i) - 1]);
        switch(key) {
            case 'w': 
            if (y >= 0 && map.basicCoords.get(i) + map.MAP_TEST_COORD_GET[0] < map.map.length && (map.map[map.basicCoords.get(i) + map.MAP_TEST_COORD_GET[0]] == 'p')) {
                    map.map[map.basicCoords.get(i)] = 'p';
                    isMove = 1;
                } else {
                    isMove = -1;
                } break;
            case 'a': 
                if (x >= 0 && map.basicCoords.get(i) + 1 < map.map.length && (map.map[map.basicCoords.get(i) + 1] == 'p')) {
                    map.map[map.basicCoords.get(i)] = 'p';
                    isMove = 2;
                } else {
                    isMove = -1;
                } break;
            case 's':
                if (y < (map.MAP_TEST_COORD_GET[1])*scaleFactor && map.basicCoords.get(i) - map.MAP_TEST_COORD_GET[0] >= 0 && (map.map[map.basicCoords.get(i) - map.MAP_TEST_COORD_GET[0]] == 'p')) {
                    map.map[map.basicCoords.get(i)] = 'p';
                    isMove = 3;
                } else {
                    isMove = -1;
                } break;
            case 'd':
                if (x < (map.MAP_TEST_COORD_GET[0])*scaleFactor && map.basicCoords.get(i) - 1 >= 0 && (map.map[map.basicCoords.get(i) - 1] == 'p')) {
                    map.map[map.basicCoords.get(i)] = 'p';
                    isMove = 4;
                } else {
                    isMove = -1;
                } break;
        }

    }

    /* reads key releases */
    public void keyReleased(KeyEvent e) { dt = 0; }
}
