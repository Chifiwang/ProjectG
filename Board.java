import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener{

    Map mapGlobal = new Map();
    Debug debug = new Debug();

    Player player;
    Block block;

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
        block = new Block();

        addKeyListener(new AL());
        setFocusable(true);
        requestFocus();

        ImageIcon board = new ImageIcon("Assets/test.png");
        ImageIcon background = new ImageIcon("Assets/background.png");
        this.board = board.getImage();
        bg = background.getImage();

        t = new Timer(5, this);
        t.start();
    }

    
    /** 
     * does nothing lmao
     * 
     * @param e a yes code...
     */
    @Override
    public void actionPerformed(ActionEvent e) { 
        if (dt > timeDelay && moveFlag)
            timeStart = System.currentTimeMillis();
        
        
        if(moveFlag && dt > timeDelay && player.canMoveObj(Map.direct) && !isAnimate) {
            
            player.move();
            // debug.printMap(map.map);

            isAnimate = true;
        }
        if (isAnimate && dt > timeDelay / (2 * numFrames)) {
            repaint();

        }
        
        dt = System.currentTimeMillis() - timeStart;
        
        
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
                            animate(player.getImage(), c*scalingFactor, r*scalingFactor, g2D);
                        else
                            g2D.drawImage(player.getImage(), c*scalingFactor, r*scalingFactor, null);
                        break;

                    case ' ':
                        break;

                    default:
                        if (map.map_move[r][c])
                            animate(block.getImage(map.map[r][c]), c*scalingFactor, r*scalingFactor, g2D);
                        else
                            g2D.drawImage(block.getImage(map.map[r][c]), c*scalingFactor, r*scalingFactor, null);
                        break;
                }
            }
        }

        if(isAnimate)
            frameNum++;
        
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

    /* calls different validation methods from player.java and block.java */
    private class AL extends KeyAdapter {

        /** 
         * on event, call the {@code player.canMoveObj(dircet);} method with the appropriate
         * encoded direction values, up = 0, left = 1, down = 2, right = 3, none = 4.
         * 
         * the method then calls the move function if the move is valid.
         * 
         * @param e keyboard event
         */
        public void keyTyped(KeyEvent e) {
            debug.print(debug.timeDifference(System.currentTimeMillis()));
            char key = e.getKeyChar();
            debug.print(key);
            moveFlag = true;
            if (dt > timeDelay / 4) {
                switch(key) {
                    case 'w':
                        Map.direct = 0;
                        break;
    
                    case 'a': 
                        Map.direct = 1;
                        break;
    
                    case 's':
                        Map.direct = 2;
                        break;
    
                    case 'd':
                        Map.direct = 3;
                        break;
    
                    default:
                        Map.direct = 4;
                        break;
                }
            }
            debug.logTime(System.currentTimeMillis());
            // debug.print(Map.direct);
            
        }

        public void keyReleased(KeyEvent e) {
            char key = e.getKeyChar();
            int newDirect;

            switch(key) {
                case 'w':
                    newDirect = 0;
                    break;

                case 'a': 
                    newDirect = 1;    
                    break;

                case 's':
                    newDirect = 2;
                    break;

                case 'd':
                    newDirect = 3;
                    break;

                default:
                    newDirect = -1;
                    break;
            }
            if (newDirect == Map.direct) {
                if (!isAnimate) Map.direct = 4;
                moveFlag = false;
                dt = 0;
            }
        }
    }
}
