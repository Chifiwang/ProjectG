import javax.swing.ImageIcon;
import java.awt.Image;

class Block {

    static ImageIcon universalBlock = new ImageIcon("Assets\\universalBlock.png");
    static ImageIcon immovableBlock = new ImageIcon("Assets\\immovableBlock.png");
    static ImageIcon destroyedBlock = new ImageIcon("Assets\\dead.png");
    static ImageIcon pushLimitBlock = new ImageIcon("Assets\\pushMaximum2Block.png");
    static ImageIcon chainedBlock = new ImageIcon("Assets\\chainBlock.png");
    static ImageIcon directLRBlock = new ImageIcon("Assets\\directionalBlockLeftRight.png");
    static ImageIcon directUDBlock = new ImageIcon("Assets\\directionalBlockUpDown.png");
    static ImageIcon pushMinimumBlock = new ImageIcon("Assets\\pushMiniumum2Block.png");

    static Image pushMinimumBlockImage = pushMinimumBlock.getImage().getScaledInstance(GameFrame.scaleFactor, GameFrame.scaleFactor, Image.SCALE_DEFAULT);
    static Image universalBlockImage = universalBlock.getImage().getScaledInstance(GameFrame.scaleFactor, GameFrame.scaleFactor, Image.SCALE_DEFAULT);
    static Image immovableBlockImage = immovableBlock.getImage().getScaledInstance(GameFrame.scaleFactor, GameFrame.scaleFactor, Image.SCALE_DEFAULT);
    static Image destroyedBlockImage = destroyedBlock.getImage().getScaledInstance(GameFrame.scaleFactor, GameFrame.scaleFactor, Image.SCALE_DEFAULT);
    static Image pushLimitBlockImage = pushLimitBlock.getImage().getScaledInstance(GameFrame.scaleFactor, GameFrame.scaleFactor, Image.SCALE_DEFAULT);
    static Image directLRBlockImage = directLRBlock.getImage().getScaledInstance(GameFrame.scaleFactor, GameFrame.scaleFactor, Image.SCALE_DEFAULT);
    static Image directUDBlockImage = directUDBlock.getImage().getScaledInstance(GameFrame.scaleFactor, GameFrame.scaleFactor, Image.SCALE_DEFAULT);
    static Image chainedBlockImage = chainedBlock.getImage().getScaledInstance(GameFrame.scaleFactor, GameFrame.scaleFactor, Image.SCALE_DEFAULT);



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
    public static boolean canMoveObj(int row, int col) {
        boolean isValid = false;
        char type = map.map[row][col];

        switch(type) {
            case 'u':
                isValid = canMoveBasic();
                break;
            case 'x':
                isValid = canMoveImmovable();
                break;
            case 'd':
                isValid = canMoveDead();
                break;
            case '>':
                isValid = canMoveDirectLR();
                break;
            case '^':
                isValid = canMoveDirectUD();
                break;
            case 'm':
            	isValid = canMovePushMin2(row, col);
            	break;
            case 'l':
                isValid = canMovePushLim2(row, col);
                break;
            case 'C':
                isValid = canMoveChain(row, col);
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
    public static boolean canMoveBasic() {
        return true;
    }
  
    /** 
     * directional movement validator for the "immoveable" block.
     * 
     * @param direct uses directional data to return its movability state
     * 
     * @return boolean returns movability of the block.
     */
    private static boolean canMoveImmovable() {
        return false;
    }
    
    /** 
     * directional movement validator for the "dead" block.
     * 
     * @param direct uses directional data to return its movability state
     * 
     * @return boolean returns movability of the block.
     */
    private static boolean canMoveDead() {
        return true;
    }

    /** 
     * directional movement validator for the "left-right" block.
     * 
     * @param direct uses directional data to return its movability state
     * 
     * @return boolean returns movability of the block.
     */
    private static boolean canMoveDirectLR() {
        return Map.direct == 1 || Map.direct == 3;
    }

    /** 
     * directional movement validator for the "up-down" block.
     * 
     * @param direct uses directional data to return its movability state
     * 
     * @return boolean returns movability of the block.
     */
    private static boolean canMoveDirectUD() {
        return Map.direct == 0 || Map.direct == 2;
    }

    /** 
     * directional movement validator for the "push minimum 2" block.
     * 
     * @param direct uses directional data to return its movability state
     * 
     * @return boolean returns movability of the block.
     */
    private static boolean canMovePushMin2(int row, int col) {
        
        if (row + Map.dr[Map.direct] > -1 && row + Map.dr[Map.direct] < map.map.length && 
                col + Map.dc[Map.direct] > -1 && col + Map.dc[Map.direct] < map.map[1].length) {

            return  map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] != 'd' && 
                    map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] != ' ' || 
                    map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] != 'p';

        } else {
            return false;
        }
    }
    
    /** 
     * directional movement validator for the "push limit 2" block.
     * 
     * @param direct uses directional data to return its movability state
     * 
     * @return boolean returns movability of the block.
     */
    private static boolean canMovePushLim2(int row, int col) {
    	if (row + Map.dr[Map.direct] > -1 && row + Map.dr[Map.direct] < map.map.length && 
                col + Map.dc[Map.direct] > -1 && col + Map.dc[Map.direct] < map.map[1].length) {

            return  (map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] == 'd' || 
            		 map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] == ' ') &&
                    (map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] != 'p' && 
                     map.map[row - 2 * Map.dr[Map.direct]][col - 2 * Map.dc[Map.direct]] == 'p' ||
                     map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] == 'p') || 
                    (row + 2 * Map.dr[Map.direct] == 0 || row + 2 * Map.dr[Map.direct] == map.map.length - 1 || 
                     col + 2 * Map.dc[Map.direct] == 0 || col + 2 * Map.dc[Map.direct] == map.map[1].length - 1 ||
                     row + 2 * Map.dr[Map.direct] > -1 && row + 2 * Map.dr[Map.direct] < map.map.length && 
                     col + 2 * Map.dc[Map.direct] > -1 && col + 2 * Map.dc[Map.direct] < map.map[1].length && 
                    map.map[row + 2 * Map.dr[Map.direct]][col + 2 * Map.dc[Map.direct]] == ' ') &&
                    map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] != ' ' &&
                    map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] == 'p';

        } else {
            return false;
        }
    }
    
    /** 
     * directional movement validator for the "chain" block.
     * 
     * @param direct uses directional data to return its movability state
     * 
     * @return boolean returns movability of the block.
     */
    private static boolean canMoveChain(int row, int col) {
        
        if (row + Map.dr[Map.direct] > -1 && row + Map.dr[Map.direct] < map.map.length && 
                col + Map.dc[Map.direct] > -1 && col + Map.dc[Map.direct] < map.map[1].length) {

            return  (map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] == 'C'|| 
                     map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] == 'd'|| 
                     map.map[row + Map.dr[Map.direct]][col + Map.dc[Map.direct]] == ' ') && 
                    (map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] == 'C'|| 
                     map.map[row - Map.dr[Map.direct]][col - Map.dc[Map.direct]] == 'p');
                    
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
                return universalBlockImage;
            case 'x':
                return immovableBlockImage;
            case 'd':
                return destroyedBlockImage;
            case '>':
                return directLRBlockImage;
            case '^':
                return directUDBlockImage;
            case 'm':
                return pushMinimumBlockImage;
            case 'l':
                return pushLimitBlockImage;
            case 'C':
                return chainedBlockImage;
            default:
                return null;
        }
    }    
}
