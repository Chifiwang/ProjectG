import java.util.ArrayList;
import javax.swing.ImageIcon;

class CanvasHandeler {
    static ArrayList<Integer[]> queue = new ArrayList<Integer[]>();
    static ArrayList<ImageIcon[]> imageLookup = new ArrayList<ImageIcon[]>();

    public static void queueObj(int imageKey, int x, int y) {
        Integer[] temp = {imageKey, x, y};
        queue.add(temp);
    }

    
}
