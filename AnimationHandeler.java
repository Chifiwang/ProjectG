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

    static int[] frameTime1 = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 0, 0, 0, 0, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
    static int[] tutorialFrames = {TextEventHandler.tutorial1_text.length(), TextEventHandler.tutorial2_text.length()};

    static Board board;
    static LevelSelect levelSelect;
    static Map map;

    static int canvas = -1;

    public static void queueAnimation(int EventKey, int x, int y) {
        // queue.clear();
        Integer[] temp = {EventKey, x, y};
        // frame = 0;
        // if (!queue.contains(temp))
        queue.add(temp);
    }

    public static void animate(Graphics2D g2D) {
        for(int i = 0; i < queue.size(); i++) {
            switch (queue.get(i)[0]) {
                case 0:
                    numFrames = 8;
                    // frame = 0;
                    drawGame(g2D, queue.get(i));
                    canvas = 0;
                    break;
                case 1: case 2:
                    numFrames = tutorialFrames[queue.get(i)[0] - 1];
                    // frame = 0;
                    drawTutorial(queue.get(i), g2D);
                    // queue.clear();
                    canvas = 1;
                    break;
            }
        }

        if (queue.size() > 0) {
            if(frame >= numFrames) {
                frame = 0;
                map.map_move = new boolean[map.map.length][map.map[0].length];
                queue.clear();
                canvas = -1;
                board.isTutorial = false;
                Map.firstGame = false;
                
            }
            else if (canvas == 0) {
                frame++;
    
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                board.repaint();
            }
            else if (canvas == 1){
                frame++;
                // queue.clear();
                // if(frame > 24) {
                //     board.isTutorial = false;
                // }
                try {
                    // Debug.print(frameTime1[frame]);
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

    public static void drawTutorial(Integer[] key, Graphics2D g2D) {
        TextEventHandler.gameTutorial(key, g2D);
    }

    public static void setMap(Map m) { map = m; }
    public static void setBoard(Board b) { board = b; }
    public static void setLevelSelect(LevelSelect ls) { levelSelect = ls; }
}