import javax.swing.ImageIcon;
import java.awt.Image;

public class Block {

    static ImageIcon bBasic = new ImageIcon("Assets\\basicBlock_default.png");
    static ImageIcon bImmovable = new ImageIcon("Assets\\Immovable.default.png");
    static ImageIcon bDead = new ImageIcon("Assets\\dead.png");
    static ImageIcon bChainE = new ImageIcon("Assets\\chainE.png");
    static ImageIcon bChainH = new ImageIcon("Assets\\chainH.png");
    static ImageIcon bDirectLR = new ImageIcon("Assets\\directLR.png");
    static ImageIcon bDirectUD = new ImageIcon("Assets\\directUD.png");
    static ImageIcon bMoveLim2 = new ImageIcon("Assets\\moveLim2.png");

    static Map map;

    Debug debug = new Debug();
    
    public Block(Map loadedMap) { map = loadedMap; }

    
    /** 
     * takes in {@value tpye} data to allocate the correct movement conditions onto
     * the box that is being scanned in {@code player.canMoveObj(dircet)}. The method
     * then returns the data that is returned from the repective move conditions back
     * to the player instance.
     * 
     * @param direct parses directional data to the movement condition validators
     * @param type uses (char) type data to correctly determine block type
     * 
     * @return boolean returns output from the movement condition validators
     */
    public static boolean canMoveObj(int direct, int row, int col) {
        boolean isValid = false;
        char type = map.map[row][col];

        switch(type) {
            case 'u':
                isValid = canMoveBasic(direct);
                break;
            case 'x':
                isValid = canMoveImmovable(direct);
                break;
            case 'd':
                isValid = canMoveDead(direct);
                break;
            case '>':
                isValid = canMoveDirectLR(direct);
                break;
            case '^':
                isValid = canMoveDirectUD(direct);
                break;
            case '2':
                isValid = canMovePushLim2(direct, row, col);
                break;
            case 'c':
                isValid = canMoveChainE(direct, row, col);
                break;
            case 'C':
                isValid = canMoveChainH(direct, row, col);
                break;
            
        }

        return isValid;
    }
    
    /** 
     * directional movement validator for the "basic" block.
     * 
     * @param direct uses directional data to return its movability state
     * 
     * @return boolean returns movability of the block.
     */
    public static boolean canMoveBasic(int direct) {
        return true;
    }
  
    /** 
     * directional movement validator for the "immoveable" block.
     * 
     * @param direct uses directional data to return its movability state
     * 
     * @return boolean returns movability of the block.
     */
    private static boolean canMoveImmovable(int direct) {
        return false;
    }

    private static boolean canMoveDead(int direct) {
        return true;
    }

    private static boolean canMoveDirectLR(int direct) {
        return direct == 1 || direct == 3;
    }

    private static boolean canMoveDirectUD(int direct) {
        return direct == 0 || direct == 2;
    }

    private static boolean canMovePushLim2(int direct, int row, int col) {
        
        if (row + Map.dr[Map.direct] > -1 && row + Map.dr[Map.direct] < map.map.length && 
                col + Map.dc[Map.direct] > -1 && col + Map.dc[Map.direct] < map.map[1].length) {

            return  map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] != ' ' || 
                   (map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] != ' ' &&
                    map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] != 'p');

        } else {
            return false;
        }
    }

    private static boolean canMoveChainE(int direct, int row, int col) {
        
        if (row + Map.dr[Map.direct] > -1 && row + Map.dr[Map.direct] < map.map.length && 
                col + Map.dc[Map.direct] > -1 && col + Map.dc[Map.direct] < map.map[1].length) {

            return  map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] == 'c'|| 
                    map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] == 'd'|| 
                    map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] == 'c'|| 
                    map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] == 'd';
                    
        } else {
            return false;
        }
    }

    private static boolean canMoveChainH(int direct, int row, int col) {
        
        if (row + Map.dr[Map.direct] > -1 && row + Map.dr[Map.direct] < map.map.length && 
                col + Map.dc[Map.direct] > -1 && col + Map.dc[Map.direct] < map.map[1].length) {

            return  (map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] == 'C'|| 
                     map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] == 'd'|| 
                     map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] == ' ') && 
                    (map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] == 'C'|| 
                     map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] == 'd') 
                                                     ||
                    (map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] == 'C'|| 
                     map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] == 'd') && 
                    (map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] == 'C'|| 
                     map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] == 'd'||
                     map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] == 'p')
                                                     ||
                    (map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] == 'C'|| 
                     map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] == 'd') && 
                    (map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] == 'C'|| 
                     map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] == 'd'|| 
                     map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] == ' ')
                                                     ||
                    (map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] == 'C'|| 
                     map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] == 'd'|| 
                     map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] == 'p') && 
                    (map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] == 'C'|| 
                     map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] == 'd');
                    
        } else {
            return false;
        }
    }
    
    /** 
     * uses type data to correctly assign assets to the correct blocks
     * 
     * @param type uses (char) type data to correctly determine block type
     * 
     * @return Image returns correct sprites for the block type.
     */
    static public Image getImage(char type) {
        switch(type) {
            case 'u':
                return bBasic.getImage();
            case 'x':
                return bImmovable.getImage();
            case 'd':
                return bDead.getImage();
            case '>':
                return bDirectLR.getImage();
            case '^':
                return bDirectUD.getImage();
            case '2':
                return bMoveLim2.getImage();
            case 'c':
                return bChainE.getImage();
            case 'C':
                return bChainH.getImage();
            default:
                return null;
        }
    }    
}
