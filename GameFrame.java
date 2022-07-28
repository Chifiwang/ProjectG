import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Image;
// import java.awt.event.KeyEvent;
// import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GameFrame {
    Debug debug = new Debug();

    static JFrame frame;
    static int scaleFactor = 128;
    
    //exit as in exit settings
    static JButton menuButton, settingsButton, backButton, nextButton, enterButton, exitButton, returnButton, leaveButton, restartButton, directoryButton, exitDirectoryButton;

    static Board board;
    static LevelSelect levelSelect;
    static Settings settings;
    static Directory directory;
    static JPanel menu = new JPanel();
    
    static int level = -1;
    
    static boolean isGame = false;

    GameFrame() throws Exception {
    	Sound.init();
    	Sound.setMusicVolume(50);
    	Sound.setSfxVolume(50);
    	
        frame = new JFrame("ProjectG");
        levelSelect = new LevelSelect(); 
        settings = new Settings();
        directory = new Directory();
        
        menu.setLayout(null);
        menu.setBounds(0, 0, 1300, 900);
        menu.setVisible(false);
        
        menuButton = new JButton("Menu");
        menuButton.setBounds(10, 10, 80, 40);
        menuButton.setFocusable(true);
        menuButton.addActionListener((e) -> {
    		board.setVisible(false);
    		menu.setVisible(true);
    		Sound.playSfx(0);
        });
        
        //settingsbutton
        ImageIcon icon = new ImageIcon("Assets\\settings.png");
        Image scaleImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        icon = new ImageIcon(scaleImage); // dw
        
        directoryButton = new JButton("Directory");
        directoryButton.setBounds(1140, 10, 100, 40);
        directoryButton.setFocusable(true);
        directoryButton.addActionListener((e) -> {
    		levelSelect.setVisible(false);
    		directory.setVisible(true);
    		directory.displayButtons();
    		Sound.playSfx(0);
        });
        
        exitDirectoryButton = new JButton("Exit");
        exitDirectoryButton.setBounds(10, 10, 80, 40);
        exitDirectoryButton.addActionListener((e) -> {
    		levelSelect.setVisible(true);
    		directory.setVisible(false);
    		Sound.playSfx(0);
        });
        
        settingsButton = new JButton();
        settingsButton.setIcon(icon);
        settingsButton.setBounds(1250, 10, 40, 40);
        settingsButton.setFocusable(true);
        settingsButton.addActionListener((e) -> {
    		if (isGame) board.setVisible(false);
    		else levelSelect.setVisible(false);
    		settings.setVisible(true);
    		Sound.playSfx(0);
        });
        
        backButton = new JButton("Back");
        backButton.setBounds(200, 430, 80, 80);
        backButton.setVisible(false);
        backButton.addActionListener((e) -> {
    		levelSelect.map--;
    		if (levelSelect.map == 1) backButton.setVisible(false);
    		nextButton.setVisible(true);
    		enterButton.setText(String.valueOf(levelSelect.map));
    		Sound.playSfx(0);
        });
        
        nextButton = new JButton("Next");
        nextButton.setBounds(1020, 430, 80, 80);
        nextButton.setVisible(false);
        nextButton.addActionListener((e) -> {
    		levelSelect.map++;
    		if (levelSelect.map == levelSelect.unlocked) nextButton.setVisible(false);
    		backButton.setVisible(true);
    		enterButton.setText(String.valueOf(levelSelect.map));
    		Sound.playSfx(0);
        });
        
        enterButton = new JButton("0");
        enterButton.setBounds(300, 250, 700, 400);
        enterButton.addActionListener((e) -> {
    		newGame(levelSelect.map);
    		Sound.playSfx(0);
        });
        
        exitButton = new JButton("Exit");
        exitButton.setBounds(10, 10, 80, 40);
        exitButton.setVisible(true);
        exitButton.addActionListener((e) -> {
    		if (isGame) board.setVisible(true);
    		else levelSelect.setVisible(true);
    		settings.setVisible(false);
    		Sound.playSfx(0);
        });
        
        returnButton = new JButton("Return");
        returnButton.setBounds(600, 380, 100, 40);
        returnButton.setFocusable(true);
        returnButton.addActionListener((e) -> {
    		menu.setVisible(false);
    		board.setVisible(true);
    		Sound.playSfx(0);
        });
        
        leaveButton = new JButton("Levels");
        leaveButton.setBounds(600, 430, 100, 40);
        leaveButton.setFocusable(true);
        leaveButton.addActionListener((e) -> {
    		menu.setVisible(false);
    		exitGame();
    		Sound.playSfx(0);
        });
        
        restartButton = new JButton("Restart");
        restartButton.setBounds(600, 480, 100, 40);
        restartButton.setFocusable(true);
        restartButton.addActionListener((e) -> {
    		frame.remove(board);
    		menu.setVisible(false);
    		newGame(level);
    		Sound.playSfx(0);
        });
        
        levelSelect.unlocked = Bson.getUnlocked();
        //tutorial
        if (levelSelect.unlocked == 0) newGame(0);
        directory.loadSave(levelSelect.unlocked);
        
    	levelSelect.map = 1;
    	enterButton.setText("1");
    	if (levelSelect.unlocked > 1) nextButton.setVisible(true);

        AnimationHandeler.setLevelSelect(levelSelect);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(scaleFactor*11, scaleFactor*7); // apparently we need to make the frame bigger than the desired size
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        levelSelect.add(backButton);
        levelSelect.add(nextButton);
        levelSelect.add(enterButton);
        levelSelect.add(settingsButton);
        levelSelect.add(directoryButton);
        
        settings.add(exitButton);
        
        directory.add(exitDirectoryButton);
        
        menu.add(returnButton);
        menu.add(leaveButton);
        menu.add(restartButton);
        
        frame.getContentPane().add(settings);
        frame.getContentPane().add(levelSelect);
        frame.getContentPane().add(menu);
        frame.getContentPane().add(directory);
    }
    
    /** 
     * creates a new instance of board whenever the player enters a level.
     * 
     * @param map is the level selected
     */
    public void newGame(int map) {
    	Sound.playMusic(1);
    	level = map;
    	isGame = true;
    	levelSelect.setVisible(false);
    	board = new Board(map);
        board.add(settingsButton);
        board.add(menuButton);
        frame.getContentPane().add(board);
    	board.addMouseListener(new MouseListener() {
    		public void mouseClicked(MouseEvent e) {
    			// do nothing
    		}
    		public void mouseEntered(MouseEvent e) {
    			// do nothing
    		}
    		public void mouseExited(MouseEvent e) {
    			// do nothing
    		}
    		public void mousePressed(MouseEvent e) {
    			// do nothing
    		}
        
    		/** 
    	     * once the game is over, removes instance of board from frame.
    	     * if the player wins in the most recently unlocked level, unlocks the next level
    	     * 
    	     * @param e
    	     */
    		public void mouseReleased(MouseEvent e) {
    			if (board.isLose || board.isWin) {
    				exitGame(map);
    			}
    			if (board.isWin && levelSelect.map == levelSelect.unlocked) {
    				levelSelect.unlocked++;
    				levelSelect.map++;
    				if (map > 0) backButton.setVisible(true);
    				enterButton.setText(String.valueOf(levelSelect.map));
    				directory.add(levelSelect.unlocked);
    			}
				if(board.isTutorial) {
					AnimationHandeler.frame = AnimationHandeler.numFrames - 1;
				}
    		}
    	});
    }
    
    /** 
     * exits the current level due to technical difficulties
     * 
     */
    public static void exitGame(int map) {
	    Bson.rewrite("Map"+Integer.toString(map), Map.rewriteMapData(board.starCount, Integer.toString(map)));
        exitGame();
    }
    
    /** 
     * exits the current level
     * 
     */
    public static void exitGame() {
    	Sound.playMusic(0);
    	level = -1;
    	isGame = false;
        board.setVisible(false);
	    frame.remove(board);
        levelSelect.add(settingsButton);
	    levelSelect.setVisible(true);
    }
}