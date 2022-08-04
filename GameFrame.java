import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GameFrame {
    Debug debug = new Debug();
    static JFrame frame;

    static int scaleFactor = 128;
    
    //exit as in exit settings
    static JButton menuButton, settingsButton, backButton, nextButton, enterButton, exitButton, returnButton, leaveButton, restartButton, directoryButton, exitDirectoryButton, editorButton, optionsButton, saveButton, discardButton, exitEditorButton, playButton, returnButtonEditor, namingButton, exitNamerButton, nameSaveButton;
    static JTextField customNamer;

    static Board board;
    static LevelSelect levelSelect;
    static Settings settings;
    static Directory directory;
    static Editor editor;
    static JPanel menu = new JPanel(), options = new JPanel(), namer = new JPanel();
    
    static int level = -1;
    
    static boolean isGame = false, isEdit = false, isName = false;

    GameFrame() throws Exception {
    	
        frame = new JFrame("ProjectG");
        levelSelect = new LevelSelect(); 
        settings = new Settings();
        directory = new Directory();
        
        menu.setLayout(null);
        menu.setBounds(0, 0, 1300, 900);
        menu.setVisible(false);
        
        options.setLayout(null);
        options.setBounds(0, 0, 1300, 900);
        options.setVisible(false);

        namer.setLayout(null);
        namer.setBounds(0, 0, 1300, 900);
        namer.setVisible(false);
        
        menuButton = new JButton("Menu");
        menuButton.setBounds(10, 10, 80, 40);
        menuButton.setFocusable(true);
        menuButton.addActionListener((e) -> {
          board.setVisible(false);
          menu.setVisible(true);
          Sound.playSfx(0);
        });
        
        editorButton = new JButton("Editor");
        editorButton.setBounds(1170, 810, 100, 40);
        editorButton.setFocusable(true);
        editorButton.addActionListener((e) -> {
          levelSelect.setVisible(false);
          newEditor();
          Sound.playSfx(0);
        });
        
        optionsButton = new JButton("Options");
        optionsButton.setBounds(10, 10, 80, 40);
        optionsButton.setFocusable(true);
        optionsButton.addActionListener((e) -> {
          editor.setVisible(false);
          options.setVisible(true);
          Sound.playSfx(0);
        });
        
        returnButtonEditor = new JButton("Return");
        returnButtonEditor.setBounds(600, 300, 100, 40);
        returnButtonEditor.setFocusable(true);
        returnButtonEditor.addActionListener((e) -> {
          options.setVisible(false);
          editor.setVisible(true);
          Sound.playSfx(0);
        });
        
        exitEditorButton = new JButton("Exit");
        exitEditorButton.setBounds(600, 350, 100, 40);
        exitEditorButton.setFocusable(true);
        exitEditorButton.addActionListener((e) -> {
          options.setVisible(false);
          exitEditor();
          Sound.playSfx(0);
        });
        
        saveButton = new JButton("Save");
        saveButton.setBounds(600, 400, 100, 40);
        saveButton.setFocusable(true);
        saveButton.setEnabled(false);
        saveButton.addActionListener((e) -> {
          // save function in bson, add later
          // Debug.print("called");

          editor.save();
          Sound.playSfx(0);
        });
        
        discardButton = new JButton("Discard");
        discardButton.setBounds(600, 450, 100, 40);
        discardButton.setFocusable(true);
        discardButton.addActionListener((e) -> {
    		// discard function in bson or smth, add later
        	options.setVisible(false);
        	exitEditor();
    		Sound.playSfx(0);
        });
        
        playButton = new JButton("Play");
        playButton.setBounds(600, 500, 100, 40);
        playButton.setFocusable(true);
        playButton.addActionListener((e) -> {
    		// save first
        	// newgame() for custom levels or smth
        	options.setVisible(false);
        	// just exits for now, might keep
        	exitEditor();
        	newGame(editor.map.map);
    		Sound.playSfx(0);
        });

        namingButton = new JButton("Name");
        namingButton.setBounds(200, 550, 100, 40);
        namingButton.setFocusable(true);
        namingButton.addActionListener((e) -> {
          options.setVisible(false);
          namer.setVisible(true);
    		  Sound.playSfx(0);
        });
        
        customNamer = new JTextField("choose a file name");
        customNamer.setFont(new Font("ocr a extended", Font.PLAIN, 15));
        customNamer.setBounds(400, 200, 600, 20);
        // customNamer.setVisible(true);
        customNamer.setFocusable(true);

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
          else if (isEdit) editor.setVisible(false);
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
        exitButton.setFocusable(true);
        exitButton.addActionListener((e) -> {
          if (isGame) board.setVisible(true);
          else if (isEdit) editor.setVisible(true);
          else levelSelect.setVisible(true);
          settings.setVisible(false);
    		  Sound.playSfx(0);
        });

        exitNamerButton = new JButton("Exit");
        exitNamerButton.setBounds(10, 10, 80, 40);
        exitNamerButton.setFocusable(true);
        exitNamerButton.addActionListener((e) -> {
          namer.setVisible(false);
          options.setVisible(true);
    		  Sound.playSfx(0);
        });

        nameSaveButton = new JButton("Save");
        nameSaveButton.setBounds(600, 350, 100, 40);
        nameSaveButton.setFocusable(true);
        nameSaveButton.addActionListener((e) -> {
            editor.updateName();
            saveButton.setEnabled(true);
            namingButton.setText("Rename");
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
          if (level != -1) newGame(level);
          else newGame(board.map.map);
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
        levelSelect.add(editorButton);
        
        settings.add(exitButton);
        
        directory.add(exitDirectoryButton);
        
        menu.add(returnButton);
        menu.add(leaveButton);
        menu.add(restartButton);
        
        options.add(saveButton);
        options.add(discardButton);
        options.add(exitEditorButton);
        options.add(playButton);
        options.add(returnButtonEditor);
        options.add(namingButton);
        
        namer.add(customNamer);
        namer.add(exitNamerButton);
        namer.add(nameSaveButton);
        // namer.add(saveButton);
        
        frame.getContentPane().add(settings);
        frame.getContentPane().add(levelSelect);
        frame.getContentPane().add(menu);
        frame.getContentPane().add(directory);
        frame.getContentPane().add(options);
        frame.getContentPane().add(namer);

        Sound.init();
        Sound.setMusicVolume(settings.musicVolumeLevel);
        Sound.setSfxVolume(settings.sfxVolumeLevel);
    }
    
    public void newGame(char[][] map) {
    	board = new Board(map);
    	newGame();
    }
    
    public void newGame(int map) {
    	level = map;
    	board = new Board(map);
    	newGame();
    }
    
    /** 
     * creates a new instance of board whenever the player enters a level.
     * 
     * @param map is the level selected
     */
    public void newGame() {
    	Sound.playMusic(1);
//    	level = map;
    	isGame = true;
    	levelSelect.setVisible(false);
//    	board = new Board(map);
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
    			if (board.isWin || board.isLose) exitGame(level);
    			if (board.isWin && levelSelect.map == levelSelect.unlocked) {
    				levelSelect.unlocked++;
    				levelSelect.map++;
    				if (levelSelect.map > 0) backButton.setVisible(true);
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
	    if (map != -1) Bson.rewrite("Map"+Integer.toString(map), Map.rewriteMapData(board.starCount, Integer.toString(map)));
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
//        board.setVisible(false);
	    frame.remove(board);
        levelSelect.add(settingsButton);
	    levelSelect.setVisible(true);
    }
    
    public void newEditor() {
    	isEdit = true;
    	editor = new Editor();
    	editor.add(settingsButton);
    	editor.add(optionsButton);
    	frame.getContentPane().add(editor);
    }
    
    public static void exitEditor() {
    	isEdit = false;
//        editor.setVisible(false);
	    frame.remove(editor);
        levelSelect.add(settingsButton);
	    levelSelect.setVisible(true);
    }
    
    public static void setPlayButton(boolean visible) {
    	playButton.setVisible(visible);
    }
}