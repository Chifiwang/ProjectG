import java.awt.*;
import javax.swing.*;

import legacy.Debug;

import java.awt.event.*;

public class Board extends JPanel{
	
	JButton menuButton, settingsButton, returnButton, leaveButton, restartButton;
	JPanel menu = new JPanel();

    /* Action key commands */
    final String up = "move up";
    final String down = "move down";
    final String left = "move left";
    final String right = "move right";

    final String up_release = "release up";
    final String down_release = "release down";
    final String left_release = "release left";
    final String right_release = "release right";

    /* objects */
    JLabel score;
    Player player;
    Block block;

    /* variables */
    Graphics2D g2D;

    /* Boolean flags */
    boolean isTutorial = false;
    boolean isAnimate = false;
    boolean isCustom = false;
    boolean moveFlag = false;
    boolean isValid = false;
    boolean isLose = false;
    boolean isWin = false;
    boolean init = true;

    /* game counters and storage values */
    int scalingFactor = GameFrame.scaleFactor;
    int moveCount = 0;
    int starCount = 3;
    int offset;
    int level;

    /* Timer values and logs */
    long timeDelay = 200;
    long timeStart; 
    long dt = 200;

    /* Image types */
    Image board;
    Image end;
    Image bg;

    Map map;

    public Board(int level) {
    	this.level = level;
        this.map = new Map(level);
        init();
    }
    
    public Board(char[][] map) {
    	this.level = -1;
    	this.map = new Map(map);
		isCustom = true;
		init();
    }
    
    public void init() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        offset = (int) (size.getWidth() - 1408)/2;
        this.setBackground(Color.BLACK);

    	menu.setLayout(null);
        
        this.setBounds(0, 0, (int) size.getWidth(), (int) size.getHeight());
        menu.setVisible(false);
        
        menuButton = new JButton("Menu");
        menuButton.setBounds(10, 10, 80, 40);
        menuButton.setFocusable(true);
        menuButton.addActionListener((e) -> {
          this.setVisible(false);
          menu.setVisible(true);
          Sound.playSfx(0);
          menu.repaint();
        });
        
      //settingsbutton
        ImageIcon icon = new ImageIcon("Assets\\settings.png");
        Image scaleImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        icon = new ImageIcon(scaleImage); // dw
        
        settingsButton = new JButton();
        settingsButton.setIcon(icon);
        settingsButton.setBounds((int) size.getWidth() - 100, 10, 40, 40);
        settingsButton.setFocusable(true);
        settingsButton.addActionListener((e) -> {
        	this.setVisible(false);
            GameFrame.settings.setVisible(true);
            Sound.playSfx(0);
        });
        
        returnButton = new JButton("Return");
        returnButton.setVisible(true);
        returnButton.setBounds(600, 380, 100, 40);
        returnButton.setFocusable(true);
        returnButton.addActionListener((e) -> {
          menu.setVisible(false);
          this.setVisible(true);
          Sound.playSfx(0);
        });
        
        leaveButton = new JButton("Levels");
        leaveButton.setVisible(true);
        leaveButton.setBounds(600, 430, 100, 40);
        leaveButton.setFocusable(true);
        leaveButton.addActionListener((e) -> {
          menu.setVisible(false);
          GameFrame.exitGame();
          Sound.playSfx(0);
        });
        
        restartButton = new JButton("Restart");
        restartButton.setVisible(true);
        restartButton.setBounds(600, 480, 100, 40);
        restartButton.setFocusable(true);
        restartButton.addActionListener((e) -> {
          GameFrame.frame.remove(this);
          menu.setVisible(false);
          if (level != -1) GameFrame.newGame(level);
          else GameFrame.newGame(this.map.map);
          Sound.playSfx(0);
        });
        
        //test restart
        
        menu.add(returnButton);
        menu.add(leaveButton);
        menu.add(restartButton);
        
//        this.add(menu);
        GameFrame.frame.getContentPane().add(menu);
        this.add(menuButton);
        this.add(settingsButton);
        
        player = new Player(map, map.loadPlayer(map.map));
        block = new Block(map);
        
        if (!isCustom) {
	        score = new JLabel();
	        isTutorial = (level == 0 || level % 10 == 1 || level == 12) && Map.firstGame;
        }

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
        this.end = end.getImage().getScaledInstance((int)size.getWidth(), (int)size.getHeight(), Image.SCALE_DEFAULT);

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
        
        player.putInputMap(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), up);
        player.putInputMap(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), down);
        player.putInputMap(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), left);
        player.putInputMap(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), right);

        player.putInputMap(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), up_release);
        player.putInputMap(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), down_release);
        player.putInputMap(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), left_release);
        player.putInputMap(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), right_release);

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
        
        if (init && isTutorial) {
            // Debug.print(level);
            TextEventHandler.initiateEvent(level, this);
            
            // Debug.print("called");
        }

        super.paint(g);
        g2D = (Graphics2D) g;
        g2D.setFont(new Font("ocr a extended", Font.PLAIN, 15));
        g2D.setColor(Color.WHITE);

        /* This is the main loop that prints and queues the game board's items and objects visuals and animations */
        for (int r = 0; r < map.map.length; r++) {
            for (int c = 0; c < map.map[r].length; c++) {
                if (!(r == 0 || c == 0 || r == map.map.length - 1 || c == map.map[0].length - 1))
                    g2D.drawImage(map.tileImg, c*scalingFactor + offset, r*scalingFactor, null);
                switch(map.map[r][c]) {
                    case 'p':
                        if (map.map_move[r][c])
                            AnimationHandeler.queueAnimation(0, c*scalingFactor + offset, r*scalingFactor);
                        else
                            g2D.drawImage(Player.getImage(), c*scalingFactor + offset, r*scalingFactor, null);
                        break;

                    case ' ':
                        break;

                    default:
                        if (map.map_move[r][c])
                            AnimationHandeler.queueAnimation(0, c*scalingFactor + offset, r*scalingFactor);
                        else
                            g2D.drawImage(Block.getImage(map.map[r][c]), c*scalingFactor + offset, r*scalingFactor, null);
                        break;
                }
            }           

        }

        /* does some tutorial logic checks */
        if (isTutorial && AnimationHandeler.frame < AnimationHandeler.numFrames) {
            float alpha = 1 * 0.8f;
            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, alpha);

            g2D.setComposite(alcom);
            g2D.drawImage(end, 0, 0, null);
        }

        if (AnimationHandeler.frame > AnimationHandeler.numFrames && Map.firstGame) {
            Map.firstGame = false;
            repaint();
        }

        AnimationHandeler.animate(g2D);

        if (!isCustom) starCount = getStarCount();

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
            
        } else if (!isCustom) {
            g2D.drawString("Num moves: " + moveCount + " Num stars: " + starCount, 9 * GameFrame.scaleFactor, GameFrame.scaleFactor/2 - 35);
            g2D.drawString("Num moves to keep " + starCount + " stars: " + (Map.starBounds[3 - starCount] - moveCount - 1), 9 * GameFrame.scaleFactor, GameFrame.scaleFactor/2 - 20);
        }

        /* initialization repaint to play tutorial */
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
        /** 
         * listens for keyboard inputs and interprets them according to
         * the key pressed. It also handles the movement timing logic.
         * 
         * @param e keyboard event
         */
        public void actionPerformed(ActionEvent e) {
            if (!isLose && !isWin && !isTutorial) {
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