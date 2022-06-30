import javax.swing.ImageIcon;
import java.awt.Image;

public class Block {
    ImageIcon bBasic = new ImageIcon("Assets/basicBlock_default.png");
    Debugg debugg = new Debugg();

    Image bIcon;
    
    public Block() {
        bIcon = bBasic.getImage();
    }

    public Image getImage() {return bIcon;}

    public static boolean canMoveObj(int direct, char type) {
        boolean isValid = false;
        switch(type) {
            case 'u':
                isValid = canMoveBasic(direct);
                break;
            // case 'e':
            //     isValid = canMoveHori(direct);
        }

        return isValid;
    }

    public static boolean canMoveBasic(int direct) {
        return true;
    }

    public Image getImage(char type) {
        switch(type) {
            case 'u':
                return bBasic.getImage();
            default:
                return null;
        }
    }    
}
