import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.Image;
// import java.awt.event.KeyEvent;
// import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Toolkit;

public class GameFrame {
    Debug debug = new Debug();
    JFrame frame;
    JLabel label;

    static int scaleFactor = 128;
    
    //exit as in exit settings
    static JButton settingsButton, backButton, nextButton, enterButton, exitButton;

    Board board;
    LevelSelect levelSelect;
    Settings settings;
    
    boolean isGame = true;

    GameFrame() {
        frame = new JFrame("ProjectG");
        levelSelect = new LevelSelect(); 
        settings = new Settings();

//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        if(screenSize.getWidth() < 128*11 || screenSize.getHeight() < 128*9) {
//                scaleFactor = 64;
//        }
        
        //settingsbutton
        ImageIcon icon = new ImageIcon("Assets\\settings.png");
        Image scaleImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        icon = new ImageIcon(scaleImage); // dw
        
        settingsButton = new JButton();
        settingsButton.setIcon(icon);
        settingsButton.setBounds(1250, 10, 40, 40);
        settingsButton.setVisible(true);
        settingsButton.setFocusable(true);
        settingsButton.addActionListener((e) -> {
        	if (e.getSource() == settingsButton) {
        		if (isGame) board.setVisible(false); 
        		else levelSelect.setVisible(false);
        		settingsButton.setVisible(false);
        		settings.setVisible(true);
        		exitButton.setVisible(true);
        	}
        });
        
        backButton = new JButton("Back");
        backButton.setBounds(200, 430, 80, 80);
        backButton.setVisible(false);
        backButton.addActionListener((e) -> {
        	if (e.getSource() == backButton) {
        		levelSelect.map--;
        		if (levelSelect.map == 1) backButton.setVisible(false);
        		nextButton.setVisible(true);
        		enterButton.setText(String.valueOf(levelSelect.map));
        	}
        });
        
        nextButton = new JButton("Next");
        nextButton.setBounds(1020, 430, 80, 80);
        nextButton.setVisible(false);
        nextButton.addActionListener((e) -> {
        	if (e.getSource() == nextButton) {
        		levelSelect.map++;
        		if (levelSelect.map == levelSelect.unlocked) nextButton.setVisible(false);
        		backButton.setVisible(true);
        		enterButton.setText(String.valueOf(levelSelect.map));
        	}
        });
        
        enterButton = new JButton("0");
        enterButton.setBounds(300, 250, 700, 400);
        enterButton.setVisible(true);
        enterButton.addActionListener((e) -> {
        	if (e.getSource() == enterButton) {
        		newGame(levelSelect.map);
        	}
        });
        
        exitButton = new JButton("Exit");
        exitButton.setBounds(10, 10, 80, 40);
        exitButton.setVisible(false);
        exitButton.addActionListener((e) -> {
        	if (e.getSource() == exitButton) {
        		if (isGame) board.setVisible(true); 
        		else levelSelect.setVisible(true);
        		settingsButton.setVisible(true);
        		settings.setVisible(false);
        		exitButton.setVisible(false);
        	}
        });
        
        //tutorial
        newGame(0);


        AnimationHandeler.setLevelSelect(levelSelect);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(scaleFactor*11, scaleFactor*9); // apparently we need to make the frame bigger than the desired size
        frame.setVisible(true);

        levelSelect.add(backButton);
        levelSelect.add(nextButton);
        levelSelect.add(enterButton);
        levelSelect.add(settingsButton);
        settings.add(exitButton);
        board.add(settingsButton);
        
        frame.getContentPane().add(settings);
        frame.getContentPane().add(levelSelect);
    }
    
    /** 
     * creates a new instance of board whenever the player enters a level.
     * 
     * @param level selected
     */
    public void newGame(int map) {
    	isGame = true;
    	levelSelect.setVisible(false);
    	board = new Board(map);
    	board.setVisible(true);
        board.add(settingsButton);
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
    				isGame = false;
	                board.setVisible(false);
	   				frame.remove(board);
	                levelSelect.add(settingsButton);
	    			levelSelect.setVisible(true);
    			}
    			if (board.isWin && levelSelect.map == levelSelect.unlocked) {
    				levelSelect.unlocked++;
    				levelSelect.map++;
    				if (map > 0) backButton.setVisible(true);
    				enterButton.setText(String.valueOf(levelSelect.map));
    			}
    		}
    	});
    }
}