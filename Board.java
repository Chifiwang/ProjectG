import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Board extends JPanel{

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

    /* objects */
    JLabel score;
    Player player;
    Block block;

    /* variables */
    Graphics2D g2D;

    boolean isAnimate = false;
    boolean moveFlag = false;
    boolean isValid = false;
    boolean isLose = false;
    boolean isWin = false;
    boolean init = true;

    int scalingFactor = GameFrame.scaleFactor;
    int moveCount = 0;
    int numFrames = 10;
    int frameNum = 0;
    int starCount = 3;

    long timeDelay = 200;
    long timeStart; 
    long dt = 200;

    Image board;
    Image end;
    Image bg;

    Map map;
    Timer t;

    public Board(int level) {
        this.map = new Map(level);
        
        player = new Player(map, map.loadPlayer(map.map));
        block = new Block(map);
        score = new JLabel();

        this.add(player);
        
        AnimationHandeler.setBoard(this);
        AnimationHandeler.setMap(this.map);
        
        this.setLayout(null);
        this.setFocusable(true);
        this.requestFocus();

        ImageIcon board = new ImageIcon("Assets\\test.png");
        ImageIcon background = new ImageIcon("Assets\\background.png");
        ImageIcon end = new ImageIcon("Assets\\end.png");
        this.board = board.getImage().getScaledInstance(900, 500, Image.SCALE_DEFAULT);
        this.bg = background.getImage().getScaledInstance(1300, 900, Image.SCALE_DEFAULT);
        this.end = end.getImage().getScaledInstance(1300, 900, Image.SCALE_DEFAULT);

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
        g2D = (Graphics2D) g;
        g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 

        for (int r = 0; r < map.map.length; r++) {
            for (int c = 0; c < map.map[r].length; c++) {
                if (!(r == 0 || c == 0 || r == map.map.length - 1 || c == map.map[0].length - 1))
                    g2D.drawImage(map.tileImg, c*scalingFactor, r*scalingFactor, null);
                switch(map.map[r][c]) {
                    case 'p':
                        if (map.map_move[r][c])
                            AnimationHandeler.queueAnimation(0, c*scalingFactor, r*scalingFactor);
                        else
                            g2D.drawImage(Player.getImage(), c*scalingFactor, r*scalingFactor, null);
                        break;

                    case ' ':
                        break;

                    default:
                        if (map.map_move[r][c])
                            AnimationHandeler.queueAnimation(0, c*scalingFactor, r*scalingFactor);
                        else
                            g2D.drawImage(Block.getImage(map.map[r][c]), c*scalingFactor, r*scalingFactor, null);
                        break;
                }
            }           
        }
        
        AnimationHandeler.animate(g2D);

        starCount = getStarCount();

        if (starCount == 0) {
            isLose = true;
        }

        if (Map.blockCount == 0) {
            isWin = true;
        }

        if (isWin) {
            float alpha = 1 * 0.5f;
            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, alpha);

            g2D.setComposite(alcom);
            g2D.drawImage(end, 0, 0, null);

        } else if (isLose) {

            float alpha = 1 * 0.5f;
            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, alpha);

            g2D.setComposite(alcom);
            g2D.drawImage(end, 0, 0, null);
            
        } else {
            g2D.drawString("Num moves: " + moveCount + " Num stars: " + starCount, 7 * GameFrame.scaleFactor, 13 * GameFrame.scaleFactor/2);
        }

        if(init) {
            init = false;
            repaint();
        }
    }

    /** 
     * returns how many stars you have according to the number of moves you've made
     * 
     */
    public int getStarCount() {
        if (moveCount < Map.starBounds[0])
            return 3;
        else if (moveCount < Map.starBounds[1] ) 
            return 2;
        else if (moveCount < Map.starBounds[2]) 
            return 1;
        else return 0;
        
    }

    /** 
     * uses keybinding to determine when a player is using WASD keys to move
     * 
     */
    private class MoveDecoder extends AbstractAction{
        int direct;
        boolean isRelease;

        public MoveDecoder(int direct, boolean isRelease) {
            this.direct = direct;
            this.isRelease = isRelease;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isLose && !isWin) {
                if (isRelease && direct == Map.direct) {
                    if (!isAnimate) Map.direct = 4;
                    dt = 200;
                }
                else if (!isRelease && !isAnimate) {
                    Map.direct = direct;
                    dt = 200;
                }

                moveFlag = !isRelease;
                
                if (dt >= timeDelay && moveFlag)
                    timeStart = System.currentTimeMillis();
                
                if(moveFlag && dt >= timeDelay && player.canMoveObj() && !isAnimate) {
                    player.move();
                    moveCount++;

                    isAnimate = true;
                }
                if (isAnimate) {
                    repaint();
                    
                }
                
                dt = System.currentTimeMillis() - timeStart;
            }
        }

    }
}