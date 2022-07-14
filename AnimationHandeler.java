import java.util.ArrayList;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.awt.Image;

public class AnimationHandeler {
    static int frame = 0;
    static int numFrames = 8;
    static int scalingFactor = GameFrame.scaleFactor;

    static ArrayList<Integer[]> queue = new ArrayList<Integer[]>();
    static ArrayList<ImageIcon[]> imageLookup = new ArrayList<ImageIcon[]>();

    static Board board;
    static LevelSelect levelSelect;
    static Map map;

    static boolean canvas = false;

    public static void queueAnimation(int EventKey, int x, int y) {
        Integer[] temp = {EventKey, x, y};
        queue.add(temp);
    }

    public static void animate(Graphics2D g2D) {
        for(Integer[] key : queue) {
            switch (key[0]) {
                case 0:
                    drawGame(g2D, key);
                    canvas = true;
                    break;
            }
        }

        if (queue.size() > 0) {
            if(frame >= numFrames) {
                frame = 0;
                map.map_move = new boolean[map.map.length][map.map[0].length];
                queue.clear();
                
            }
            else if (canvas) {
                frame++;
    
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                board.repaint();
            }
        }
    }

    public static void drawGame(Graphics2D graphics, Integer[] key) {
        int c = key[1]; 
        int r = key[2]; 
        Image img;
        
        char type = map.map[r/board.scalingFactor][c/board.scalingFactor];
        
        switch (type) {
            case 'p':
                img = Player.getImage();
                break;
            default:
                img = Block.getImage(type);
        }

        c -= (scalingFactor - frame * (scalingFactor/numFrames)) * Map.dc[Map.direct];
        r -= (scalingFactor - frame * (scalingFactor/numFrames)) * Map.dr[Map.direct];

        graphics.drawImage(img, c, r, null);
        GameFrame.settingsButton.requestFocus();
        if (frame >= numFrames) {
            board.isAnimate = false;
        }
    }

    public static void setMap(Map m) { map = m; }
    public static void setBoard(Board b) { board = b; }
    public static void setLevelSelect(LevelSelect ls) { levelSelect = ls; }
}