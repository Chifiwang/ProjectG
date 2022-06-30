import javax.swing.ImageIcon;
import java.awt.Image;

public class Block {

    ImageIcon bBasic = new ImageIcon("Assets\\basicBlock_default.png");
    ImageIcon bImmovable = new ImageIcon("Assets\\Immovable.default.png");
    Debugg debugg = new Debugg();
    
    public Block() { }

    
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
    public static boolean canMoveObj(int direct, char type) {
        boolean isValid = false;
        switch(type) {
            case 'u':
                isValid = canMoveBasic(direct);
                break;
            case 'x':
                isValid = canMoveImmovable(direct);
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
    
    /** 
     * uses type data to correctly assign assets to the correct blocks
     * 
     * @param type uses (char) type data to correctly determine block type
     * 
     * @return Image returns correct sprites for the block type.
     */
    public Image getImage(char type) {
        switch(type) {
            case 'u':
                return bBasic.getImage();
            case 'x':
                return bImmovable.getImage();
            default:
                return null;
        }
    }    
}
