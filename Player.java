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
    int isMove = -1;
    Image pIcon;
    Map map;

    public Player(Map map) {
        pIcon = pDefault.getImage();
        this.map = map;
        x = posX();
        y = posY();
    }

    public void move() {
        if (dt >= timeDelay) {
            timeStart = System.currentTimeMillis();
            switch (isMove) {
                case 1:
//                    map.map[map.playerCoords - map.MAP_TEST_COORD_GET[0]] = 'p';
//                    map.map[map.playerCoords] = ' ';
                	if (y - scaleFactor >= 0) {
	                    map.playerCoords -= map.MAP_TEST_COORD_GET[0];
	                    y -= scaleFactor;
                	}
                    break;
                case 2: 
//                    map.map[map.playerCoords - 1] = 'p';
//                    map.map[map.playerCoords] = ' ';
                	if (x - scaleFactor >= 0) {
	                    map.playerCoords--;
	                    x -= scaleFactor;
                	}
                    break;
                case 3:
//                    map.map[map.playerCoords + map.MAP_TEST_COORD_GET[0]] = 'p';
//                    map.map[map.playerCoords] = ' ';
                	if (y + scaleFactor < map.MAP_TEST_COORD_GET[1]*scaleFactor) {
	                    map.playerCoords += map.MAP_TEST_COORD_GET[0];
	                    y += scaleFactor;
                	}
                    break;
                case 4:
//                    map.map[map.playerCoords + 1] = 'p';
//                    map.map[map.playerCoords] = ' ';
                	if (x + scaleFactor < map.MAP_TEST_COORD_GET[0]*scaleFactor) {
	                    map.playerCoords++;
	                    x += scaleFactor;
                	}
                    break;
            }
            // debugg.printMap(map.map);
        }

        dt = System.currentTimeMillis() - timeStart;
    }
    
    public int posX() {return (map.playerCoords % map.MAP_TEST_COORD_GET[0])*scaleFactor;}
    public int posY() {return (map.playerCoords / map.MAP_TEST_COORD_GET[0])*scaleFactor;}
    public int getX() {return x;}
    public int getY() {return y;}
    public Image getImage() {return pIcon;}

    /* reads key presses */

    public void keyPressed(KeyEvent e) {
        
        char key = e.getKeyChar();
        switch(key) {
            case 'w': 
                if (y > 0) {
                    isMove = 1;
                } else {
                    isMove = -1;
                } break;
            case 'a': 
                if (x > 0) {
                    isMove = 2;
                } else {
                    isMove = -1;
                } break;
            case 's':
                if (y < (map.MAP_TEST_COORD_GET[1]-1)*scaleFactor) {
                    isMove = 3;
                } else {
                    isMove = -1;
                } break;
            case 'd':
                if (x < (map.MAP_TEST_COORD_GET[0]-1)*scaleFactor) {
                    isMove = 4;
                } else {
                    isMove = -1;
                } break;
        }
            // debugg.print(dt + " " + timeStart);
            // return key;

            
        // return ' ';
    }

    /* reads key releases */
    public void keyReleased(KeyEvent e) { isMove = -1; }
}
