import javax.swing.ImageIcon;
import java.awt.Image;

public class Player {

    ImageIcon pDefault = new ImageIcon("Assets\\player_default.png");
    Debugg debugg = new Debugg();

    int row, col;

    Image pIcon;
    Map map;

    public Player(Map map, int[] coords) {
        pIcon = pDefault.getImage();
        this.map = map;
        this.row = coords[0];
        this.col = coords[1];
    }

    /** 
     * uses the internal coordinates {@value this.row} and {@value this.col}
     * and previously logged pointer data from {@code this.canMoveObj(direct)}
     * to reverse through the scanned data in {@code this.canMoveObj(direct)}
     * and overwrite the data from the last action to the current action, 
     * starting at the logged pointer.
     */
    public void move() {
        
        for (int r = Map.r, c = Map.c; r > -1 && r < map.map.length && c > -1 && 
            c < map.map[1].length; r -= Map.dr[Map.direct], c -= Map.dc[Map.direct]) {
                
            if (map.map[r][c] == 'p') {
                map.map[r][c] = ' ';

                col = c + Map.dc[Map.direct];
                row = r + Map.dr[Map.direct];
                break;
            }

            map.map[r][c] = map.map[r - Map.dr[Map.direct]][c - Map.dc[Map.direct]];
        }
    }
    
    
    /** 
     * this method returns {@code true} if player is within the bounds of 
     * {@code map.map}.
     *  
     * @param direct takes directional data
     * @param r takes row coordinate 'y coord'
     * @param c takes column coordinate 'x coord'
     * @return boolean returns if a valid move is available
     */
    public boolean canMove(int direct, int r, int c) { // checks all sides of map
        return r + Map.dr[direct] > -1 && r + Map.dr[direct] < map.map.length && 
               c + Map.dc[direct] > -1 && c + Map.dc[direct] < map.map[1].length ;
    }

    
    /** 
     * given a direction {@value direct}, the program loops through from the player's
     * position {@value row, col} to the boarder or next empty space {@value ' '}.
     * it then calls the nescessary methods to validate their movement conditions individually.
     * Through the ternary operator, the method accumulates these method call outputs in a
     * logical "and" operation. Furthermore, on termination of the {@code for loop} the
     * method logs the pointer data in {@value Map.r} and {@value Map.c} to use in the 
     * {@code this.move()} method.
     * 
     * @param direct provides directional data
     * @return boolean outputs whether a valid move was made or not
     */
    public boolean canMoveObj(int direct) {
        boolean isValid = true;
        
        for(int r =  row, c = col; r > -1 && r < map.map.length && 
            c > -1 && c < map.map[1].length; r += Map.dr[direct], c += Map.dc[direct]) {

            if (map.map[r][c] == ' ') { 
                Map.r = r;
                Map.c = c;
                break; 
            } 

            else if (map.map[r][c] == 'p') {
                isValid = (canMove(direct, r, c)) ? isValid : false;
            }

            else if (map.map[r][c] != ' ') {
                isValid = (Block.canMoveObj(direct, map.map[r][c])) ? isValid : false;
            }
            
            Map.r = r;
            Map.c = c;
        }
        return isValid;
    }

    public Image getImage() {return pIcon;}
}
