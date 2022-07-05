import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Board extends JPanel {

    /* Action key commands */
    final String up = "move up";
    final String down = "move down";
    final String left = "move left";
    final String right = "move right";

    final String up_release = "release up";
    final String down_release = "release down";
    final String left_release = "release left";
    final String right_release = "release right";

    /* Innit statements */
    Map mapGlobal = new Map();
    Debug debug = new Debug();

    /* objects */
    Player player;
    Block block;

    /* variables */
    boolean isAnimate = false;
    boolean moveFlag = false;
    boolean isValid = false;

    int scalingFactor = 100;
    int numFrames = 10;
    int frameNum = 0;

    long timeDelay = 200;
    long timeStart; 
    long dt = 200;

    Image board;
    Image bg;
    Map map;
    Timer t;

    public Board() {
        this.map = new Map(mapGlobal.levelSelect[0], mapGlobal.moveSelect[0]);
        player = new Player(map, map.loadPlayer(map.map));
        block = new Block(map);

        this.add(player);

        setFocusable(true);
        requestFocus();

        ImageIcon board = new ImageIcon("Assets/test.png");
        ImageIcon background = new ImageIcon("Assets/background.png");
        this.board = board.getImage();
        bg = background.getImage();

        // t = new Timer(5, this);
        // t.start();

        player.putInputMap(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), up);
        player.putInputMap(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), down);
        player.putInputMap(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), left);
        player.putInputMap(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), right);

        player.putInputMap(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), up_release);
        player.putInputMap(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), down_release);
        player.putInputMap(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), left_release);
        player.putInputMap(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), right_release);

        player.putActionMap(up, new MoveDecoder(0, false));
        player.putActionMap(down, new MoveDecoder(2, false));
        player.putActionMap(left, new MoveDecoder(1, false));
        player.putActionMap(right, new MoveDecoder(3, false));

        player.putActionMap(up_release, new MoveDecoder(0, true));
        player.putActionMap(down_release, new MoveDecoder(2, true));
        player.putActionMap(left_release, new MoveDecoder(1, true));
        player.putActionMap(right_release, new MoveDecoder(3, true));
    }
    
    /** 
     * loops through the internal map and paints sprites according to block type.
     * paints the graphics that are displayed on the screen 
     * 
     * @param g
     */
    public void paint(Graphics g) {

        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        
        g2D.drawImage(bg, 0, 0, null);
        g2D.drawImage(board, 100, 100, null);

        for (int r = 0; r < map.map.length; r++) {
            for (int c = 0; c < map.map[r].length; c++) {
                // debug.print(r + " " + c);
                switch(map.map[r][c]) {
                    case 'p':
                        if (map.map_move[r][c])
//                            try {
                                animate(player.getImage(), c*scalingFactor, r*scalingFactor, g2D);
//                            } catch (InterruptedException e1) {
//                                e1.printStackTrace();
//                            }
                        else
                            g2D.drawImage(player.getImage(), c*scalingFactor, r*scalingFactor, null);
                        break;

                    case ' ':
                        break;

                    default:
                        if (map.map_move[r][c])
//                            try {
                                animate(block.getImage(map.map[r][c]), c*scalingFactor, r*scalingFactor, g2D);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
                        else
                            g2D.drawImage(block.getImage(map.map[r][c]), c*scalingFactor, r*scalingFactor, null);
                        break;
                }
            }
        }  

        if (isAnimate) {
            frameNum++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        
            repaint();
        }      
    }

    public void animate(Image img, int c, int r, Graphics2D graphics) {
        c -= (scalingFactor - frameNum * (scalingFactor/numFrames)) * Map.dc[Map.direct];
        r -= (scalingFactor - frameNum * (scalingFactor/numFrames)) * Map.dr[Map.direct];

        graphics.drawImage(img, c, r, null);

        if (frameNum >= numFrames) {
            map.map_move = new boolean[map.map.length][map.map[0].length];

            Map.__mvntCache__ = 4;
            isAnimate = false;
            frameNum = 0;
            
        }
    }

    private class MoveDecoder extends AbstractAction{
        int direct;
        boolean isRelease;

        public MoveDecoder(int direct, boolean isRelease) {
            this.direct = direct;
            this.isRelease = isRelease;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isRelease && direct == Map.direct) {
            	if (!isAnimate) Map.direct = 4;
            	dt = 200;
            }
            else if (!isRelease && !isAnimate) {
            	Map.direct = direct;
            	dt = 200;
            }
            
//            if (!isAnimate)
//            	Map.direct = this.direct;
            moveFlag = !isRelease;
            
            if (dt >= timeDelay && moveFlag)
                timeStart = System.currentTimeMillis();
            
            if(moveFlag && dt >= timeDelay && player.canMoveObj(Map.direct) && !isAnimate) {
                debug.print("here");
                player.move();
                // debug.printMap(map.map);

                isAnimate = true;
            }
            if (isAnimate) {
                repaint();
                
            }
            
            dt = System.currentTimeMillis() - timeStart;
        }

    }
}