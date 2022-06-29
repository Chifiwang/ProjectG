import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.*;

public class BasicBlock {
    ImageIcon bDefault = new ImageIcon("Assets/basicBlock_default.png");
    Debugg debugg = new Debugg();
    boolean isDead = false;

    int scaleFactor = 100;
    int x, y, i, coordInd;
    int width, height;
    int isMove = -1;
    Image bIcon;
    Map map;

    public BasicBlock(Map map, int i) {
        debugg.print(i);
        bIcon = bDefault.getImage();
        this.map = map;

        coordInd = map.basicCoords.get(i);
        x = posX(i);
        y = posY(i);
        this.i = i;
    }

    public void move() {
    	if (isDead) return;

        if (Board.dt >= Board.timeDelay) {

            switch (isMove) {
                case 1:
                    coordInd -= map.MAP_TEST_COORD_GET[0];
                    map.basicCoords.set(i, coordInd);
                    y -= scaleFactor;
                    break;

                case 2: 
                    coordInd--;
                    map.basicCoords.set(i, coordInd);
                    x -= scaleFactor;
                    break;

                case 3:
                    coordInd += map.MAP_TEST_COORD_GET[0];
                    map.basicCoords.set(i, coordInd);
                    y += scaleFactor;
                    break;

                case 4:
                    map.basicCoords.set(i, coordInd + 1);
                    x += scaleFactor;
                    break;
            }

            if (x < 0 || x >= (map.MAP_TEST_COORD_GET[0])*scaleFactor ||
                y < 0 || y >= (map.MAP_TEST_COORD_GET[1])*scaleFactor) {

            	isDead = true;
            	map.basicCoords.set(i, -1);
            }

            debugg.print(isMove);
            debugg.printMap(map.map);
        }
    }
    
    /* reads key presses */

    public void keyPressed(KeyEvent e) {
        if (isDead) return;
        char key = e.getKeyChar();
        // debugg.print( map.map[coordInd - 1]);
        switch(key) {
            case 'w': 
            if (y >= 0 && coordInd + map.MAP_TEST_COORD_GET[0] < map.map.length 
                && (map.map[coordInd + map.MAP_TEST_COORD_GET[0]] == 'p')) {

                    map.map[coordInd] = 'p';
                    isMove = 1;
                } 
                
                else {isMove = -1;} 
                break;

            case 'a': 
                if (x >= 0 && coordInd + 1 < map.map.length && (map.map[coordInd + 1] == 'p')) {
                    
                    map.map[coordInd] = 'p';
                    isMove = 2;
                } 
                
                else {isMove = -1;} 
                break;

            case 's':
                if (y < (map.MAP_TEST_COORD_GET[1])*scaleFactor && coordInd - map.MAP_TEST_COORD_GET[0] >= 0 
                   && (map.map[coordInd - map.MAP_TEST_COORD_GET[0]] == 'p' || coordInd == map.playerCoords)) {

                    map.map[coordInd] = 'p';
                    isMove = 3;
                } 
                
                else {isMove = -1;} 
                break;

            case 'd':
                if (x < (map.MAP_TEST_COORD_GET[0])*scaleFactor && coordInd - 1 >= 0 
                   && (map.map[coordInd - 1] == 'p' || coordInd == map.playerCoords)) {

                    map.map[coordInd] = 'p';
                    isMove = 4;
                }
                
                else {isMove = -1;} 
                break;
        }
        
        if (x < 0 || x >= (map.MAP_TEST_COORD_GET[0])*scaleFactor || 
            y < 0 || y >= (map.MAP_TEST_COORD_GET[1])*scaleFactor) {

        	isDead = true;
        	map.basicCoords.set(i, -1);
        }
        
        //bedug
        debugg.print(isMove);
    }

    /* reads key releases */
    public void keyReleased(KeyEvent e) { isMove = -1; }

    public int posX(int i) {return (coordInd % map.MAP_TEST_COORD_GET[0])*scaleFactor;}
    public int posY(int i) {return (coordInd / map.MAP_TEST_COORD_GET[0])*scaleFactor;}

    public int getX() {return x;}
    public int getY() {return y;}

    public Image getImage() {return bIcon;}
}
