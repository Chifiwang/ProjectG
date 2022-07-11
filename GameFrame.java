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

// import java.awt.event.MouseEvent;
// import java.awt.event.MouseListener;

// import java.awt.*;

public class GameFrame/*  implements MouseListener */ {
    Debug debug = new Debug();
    JFrame frame;
    JLabel label;
    
    //exit as in exit settings
    static JButton settingsButton, backButton, nextButton, enterButton, exitButton;
    static JPanel settingsPanel = new JPanel();
    JLayeredPane current = new JLayeredPane();

    Board board;
    LevelSelect levelSelect;
    Settings settings;

    GameFrame() {
        frame = new JFrame("ProjectG");
//        frame.setLayout(null);
        levelSelect = new LevelSelect(); 
        settings = new Settings();
        
//        current.setLayout(null);
        current.setBounds(0, 0, 1300, 900);
        current.setVisible(true);
//        current.setFocusable(true);
//        current.requestFocus();

//        settingsPanel.setBounds(1250, 10, 40, 40);
//        current.setBounds(0, 0, 1300, 900);
//        current.setVisible(true);
        
        //settingsbutton
        ImageIcon icon = new ImageIcon("Assets\\settings.png");
        Image scaleImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        icon = new ImageIcon(scaleImage); // dw
        
        settingsButton = new JButton();
        settingsButton.setIcon(icon);
        settingsButton.setBounds(1250, 10, 40, 40);
//        settingsButton.setSize(40, 40);
        settingsButton.setVisible(true);
        settingsButton.setFocusable(true);
        settingsButton.addActionListener((e) -> {
        	if (e.getSource() == settingsButton) {
//        		levelSelect.setVisible(false);
        		current.setVisible(false);
        		settings.setVisible(true);
        		exitButton.setVisible(true);
        	}
        });
        
//        settingsPanel.add(settingsButton);
//        settingsPanel.setVisible(true);
        
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
//        		levelSelect.setVisible(true);
        		current.setVisible(true);
        		settings.setVisible(false);
        		exitButton.setVisible(false);
        	}
        });
        
        //tutorial
        newGame(0);


        AnimationHandeler.setLevelSelect(levelSelect);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1310, 937); // apparently we need to make the frame bigger than the desired size
        frame.setVisible(true);

        levelSelect.add(backButton);
        levelSelect.add(nextButton);
        levelSelect.add(enterButton);
        levelSelect.add(settingsButton);
        settings.add(exitButton);
        board.add(settingsButton);
        
        // current.add(settingsButton, new Integer(2));
        // current.add(levelSelect, new Integer(1));

        // frame.getContentPane().add(current);
        frame.getContentPane().add(settings);
        frame.getContentPane().add(levelSelect);
        
//        newGame(0);
// idk why it doesnt display board when i put it here
    }
    
    public void newGame(int map) {
//    	levelSelect.remove(settingsButton);
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
    		public void mouseReleased(MouseEvent e) {
    			if (board.isLose || board.isWin) {
//    				levelSelect.add(settingsButton);
                                debug.print("A");
                                board.setVisible(false);
   				frame.remove(board);
    				// current.remove(board);
                                levelSelect.add(settingsButton);
    				levelSelect.setVisible(true);
   				levelSelect.repaint();
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