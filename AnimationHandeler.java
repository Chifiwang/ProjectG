import java.util.ArrayList;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class AnimationHandeler {

    // Basic driver informaiton
    static int frame = 0;
    static int numFrames = 8;
    static int scalingFactor = GameFrame.scaleFactor;

    static ArrayList<Integer[]> queue = new ArrayList<Integer[]>();
    static ArrayList<ImageIcon[]> imageLookup = new ArrayList<ImageIcon[]>();

    static int[] tutorialFrames = {TextEventHandler.tutorial1_text.length(), TextEventHandler.tutorial2_text.length(), 
                                   TextEventHandler.tutorial3_text.length(), TextEventHandler.tutorial4_text.length(),
                                   TextEventHandler.tutorial5_text.length(), TextEventHandler.tutorial6_text.length(),
                                   TextEventHandler.tutorial7_text.length()};

    static Board board;
    static LevelSelect levelSelect;
    static Map map;

    static int canvas = -1;
    static int offset = (int) (GameFrame.size.getWidth() - 1408)/2;

    /** 
     * queues an event into the event queue mechanism
     * 
     * @param EventKey provides a key to identify the event
     * @param x provides an x coordinate of the event
     * @param y provides a y coordinate of the event
     */
    public static void queueAnimation(int EventKey, int x, int y) {
        Integer[] temp = {EventKey, x, y};
        queue.add(temp);
    }

    /** 
     * Sends an animate request based on the queued events. 
     * {@code} Animate(Graphics2D g2D) also handles the management 
     * 
     * of frames and frame related events
     * @param g2D passes through the class that draws stuff
     */
    public static void animate(Graphics2D g2D) {
        for(int i = 0; i < queue.size(); i++) {
            switch (queue.get(i)[0]) {
                case 0:
                    numFrames = 8;
                    // frame = 0;
                    drawGame(g2D, queue.get(i));
                    canvas = 0;
                    break;
                case 1: case 2: case 3: case 4: case 5: case 6: case 7:
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
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                board.repaint();
            }
        }
    }

    
    /** 
     * Excecutes the animation for moving blocks during the game
     * 
     * @param graphics provides the graphics class
     * @param key provides the coordinates of the animation
     */
    public static void drawGame(Graphics2D graphics, Integer[] key) {
        int c = key[1]; 
        int r = key[2]; 
        Image img;
        
        char type = map.map[r/board.scalingFactor][(c - offset)/board.scalingFactor];
        
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
//        GameFrame.settingsButton.requestFocus();
        if (frame >= numFrames) {
            board.isAnimate = false;
        }
    }

    
    /** 
     * sends a request to animate the tutorial text in {@code} 
     * TextEventHandlre.java
     * @param key provides the tutorial number
     * @param g2D provides the graphics2D class
     */
    public static void drawTutorial(Integer[] key, Graphics2D g2D) {
        TextEventHandler.gameTutorial(key, g2D);
    }

    public static void setMap(Map m) { map = m; }
    public static void setBoard(Board b) { board = b; }
    public static void setLevelSelect(LevelSelect ls) { levelSelect = ls; }
}