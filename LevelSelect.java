import java.awt.*;
import javax.swing.*;

import legacy.Debug;

public class LevelSelect extends JPanel {
	
	JButton settingsButton, backButton, nextButton, enterButton, directoryButton/* , editorButton */;

    Image board;
    Image bg;

    Graphics2D g2D;
    JButton button;
    int mapButton_Type = 0;

    int activeButton = -1;

    boolean isTest = false;
    int map = 0, unlocked = 0;

    public LevelSelect() {
    	
    	// editorButton = new JButton("Editor");
      //   editorButton.setBounds(1170, 810, 100, 40);
      //   editorButton.setFocusable(true);
      //   editorButton.addActionListener((e) -> {
      //     this.setVisible(false);
      //     GameFrame.newEditor();
      //     Sound.playSfx(0);
      //   });
        
        this.setBackground(Color.BLACK);

        directoryButton = new JButton("Directory");
        directoryButton.setBounds(1140, 10, 100, 40);
        directoryButton.setFocusable(true);
        directoryButton.addActionListener((e) -> {
          this.setVisible(false);
          GameFrame.directory.setVisible(true);
          GameFrame.directory.displayButtons();
          Sound.playSfx(0);
        });
        
      //settingsbutton
        ImageIcon icon = new ImageIcon("Assets\\settings.png");
        Image scaleImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        icon = new ImageIcon(scaleImage); // dw
        
        settingsButton = new JButton();
        settingsButton.setIcon(icon);
        settingsButton.setBounds(1250, 10, 40, 40);
        settingsButton.setFocusable(true);
        settingsButton.addActionListener((e) -> {
        	this.setVisible(false);
            GameFrame.settings.setVisible(true);
            Sound.playSfx(0);
        });
        
        backButton = new JButton();
        backButton.setIcon((new ImageIcon("Assets\\leftScrollButton.png")));
        backButton.setBackground(Color.BLACK);
        // backButton.setBorder(BorderFactory.createEmptyBorder(4, 4, 2, 20));
        backButton.setBounds(200, 350, 80, 240);
        backButton.setVisible(false);
        backButton.addActionListener((e) -> {
          this.map--;
          if (this.map == 1) backButton.setVisible(false);
            nextButton.setVisible(true);
            enterButton.setText(String.valueOf(this.map));
            Sound.playSfx(0);
        });
        
        nextButton = new JButton();
        nextButton.setIcon((new ImageIcon("Assets\\rightScrollButton.png")));
        nextButton.setBackground(Color.BLACK);

        nextButton.setBounds(1020, 350, 80, 240);
        nextButton.setVisible(false);
        nextButton.addActionListener((e) -> {
          this.map++;
          if (this.map == this.unlocked) nextButton.setVisible(false);
            backButton.setVisible(true);
            enterButton.setText(String.valueOf(this.map));
            Sound.playSfx(0);
        });
        
        enterButton = new JButton("0");
        enterButton.setBounds(300, 250, 700, 400);
        enterButton.addActionListener((e) -> {
          GameFrame.newGame(this.map);
          Sound.playSfx(0);
        });
    	
    	  this.setLayout(null);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(0, 0, (int) size.getWidth(), (int) size.getHeight());
//        Debug.print("hallo");

        ImageIcon board = new ImageIcon("Assets\\dead.png");
        ImageIcon background = new ImageIcon("Assets\\levelSelect_background.png");
        this.board = board.getImage();
        bg = background.getImage();

        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocus();

        this.add(backButton);
        this.add(nextButton);
        this.add(enterButton);
        this.add(settingsButton);
        this.add(directoryButton);
        // this.add(editorButton);
    }
}
