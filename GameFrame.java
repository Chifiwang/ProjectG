import javax.swing.JFrame;

import legacy.Debug;
// import legacy.Editor;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Dimension;
import java.awt.Toolkit;

public class GameFrame {
    Debug debug = new Debug();

    static JFrame frame;
    static Board board;
    static LevelSelect levelSelect;
    static Settings settings;
    static Directory directory;
    // static Editor editor;
    // static CustomLevelSelect cLevelSelect;

    static int level = -1;
    static int scaleFactor = 128;
    static boolean isGame = false, isEdit = false, isName = false;
    static Dimension size;

    GameFrame() throws Exception {
    	Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

        frame = new JFrame("ProjectG");
        levelSelect = new LevelSelect(); 
        settings = new Settings();
        directory = new Directory();
        // cLevelSelect = new CustomLevelSelect();
        
        frame.setBackground(Color.BLACK);

        Sound.init();
        Sound.setMusicVolume(settings.musicVolumeLevel);
        Sound.setSfxVolume(settings.sfxVolumeLevel);
        
        levelSelect.unlocked = Bson.getUnlocked();
        
        if (levelSelect.unlocked == 0) newGame(0);
        else {
            directory.loadSave(levelSelect.unlocked);
        
            levelSelect.map = 1;
            levelSelect.enterButton.setText("1");
        }

    	if (levelSelect.unlocked > 1) levelSelect.nextButton.setVisible(true);

        AnimationHandeler.setLevelSelect(levelSelect);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((int) size.getWidth(), (int) size.getHeight()); // apparently we need to make the frame bigger than the desired size
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        frame.getContentPane().add(settings);
        frame.getContentPane().add(levelSelect);
        frame.getContentPane().add(directory);
        // frame.getContentPane().add(cLevelSelect);
    }
    
    // public static void newGame(char[][] map) {
    // 	board = new Board(map);
    // 	newGame();
    // }
    
    // public static void newGame(int map) {
    // 	level = map;
    // 	board = new Board(map);
    // 	newGame();
    // }
    
    /** 
     * creates a new instance of board whenever the player enters a level.
     * 
     * @param map is the level selected
     */
    public static void newGame(int map) {
        level = map;
    	board = new Board(map);
    	Sound.playMusic(1);

    	isGame = true;

    	levelSelect.setVisible(false);
        frame.getContentPane().add(board);

    	board.addMouseListener(new MouseListener() {
    		public void mouseClicked(MouseEvent e) { /* do nothing */ }
    		public void mouseEntered(MouseEvent e) { /* do nothing */ }
    		public void mousePressed(MouseEvent e) { /* do nothing */ }
            public void mouseExited(MouseEvent e)  { /* do nothing */ }

    		/** 
    	     * once the game is over, removes instance of board from frame.
    	     * if the player wins in the most recently unlocked level, unlocks the next level
    	     * 
    	     * @param e
    	     */
    		public void mouseReleased(MouseEvent e) {
                // Debug.print((levelSelect.unlocked + 1 < Bson.numClasses("Saves\\Levels.txt") - 2) ? 1 : 0);
                // Debug.print(board.isWin);
                // Debug.print(levelSelect.map + " " + levelSelect.unlocked);
                // Debug.print("-----------------------");
    			if (board.isWin || board.isLose) exitGame(level);
    			if (board.isWin && levelSelect.map == levelSelect.unlocked) {
                    // Debug.print(Bson.numClasses("Saves\\Levels.txt") - 2 + " " + levelSelect.unlocked + 1);
    				levelSelect.unlocked += (levelSelect.unlocked + 1 < Bson.numClasses("Saves\\Levels.txt") - 1) ? 1 : 0;
    				levelSelect.map += (levelSelect.map + 1 < Bson.numClasses("Saves\\Levels.txt") - 1) ? 1 : 0;

    				if (levelSelect.map > 1) levelSelect.backButton.setVisible(true);
                    levelSelect.info.setText("Level " + levelSelect.map);
    				levelSelect.enterButton.setText(String.valueOf(levelSelect.map));
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
	    if (map != -1) Bson.writeClass("Map"+Integer.toString(map), Map.rewriteMapData(board.isWin, board.starCount, Integer.toString(map)), "Saves\\Levels.txt");
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

	    frame.remove(board);
	    levelSelect.setVisible(true);
    }
    
    // public static void newEditor() {
    // 	isEdit = true;

    // 	editor = new Editor();
    // 	frame.getContentPane().add(editor);
    // }
    
    // public static void exitEditor() {
    // 	isEdit = false;
        
	//     frame.remove(editor);
	//     levelSelect.setVisible(true);
    // }
    
    // public static void setPlayButton(boolean visible) {
    // 	editor.playButton.setVisible(visible);
    // }
}