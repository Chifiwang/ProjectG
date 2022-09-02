// package legacy;
// import java.awt.*;
// import javax.swing.*;

// import Block;
// import GameFrame;
// import Map;
// import Player;
// import Sound;

// import java.awt.event.*;
// import java.util.Arrays;
// import java.util.stream.Stream;

// public class Editor extends JPanel {
	
// 	JButton settingsButton, optionsButton, saveButton, discardButton, exitEditorButton, playButton, returnButtonEditor, namingButton, exitNamerButton, nameSaveButton, yesButton, noButton;
// 	JTextField customNamer;
// 	JPanel options = new JPanel(), namer = new JPanel(), savePrompt = new JPanel();
	
// 	boolean init = true;

//     /* objects */
//     JLabel score;
//     Block block;
    
// 	JButton[] blocks = new JButton[9];

//     char[] lazy = {'u', 'x', '>', '^', 'm', 'l', 'C', ' ', 'p'};
//     int selected = -1, count = 0;
    
//     boolean saved = false;

//     /* variables */
//     Graphics2D g2D;

//     int scalingFactor = GameFrame.scaleFactor;
//     String name = "";

//     Image board;
//     Image bg;

//     Map map;
//     Timer t;

//     public Editor() {
    	
//         options.setLayout(null);
//         options.setBounds(0, 0, 1300, 900);
//         options.setVisible(false);
        
//         savePrompt.setLayout(null);
//         savePrompt.setBounds(550, 375, 200, 150);
//         savePrompt.setBorder(BorderFactory.createRaisedBevelBorder());
//         savePrompt.setVisible(false);

//         namer.setLayout(null);
//         namer.setBounds(0, 0, 1300, 900);
//         namer.setVisible(false);
        
//         JLabel saveMessage = new JLabel("Save changes?");
//         saveMessage.setBounds(55, 10, 90, 40);
        
//         yesButton = new JButton("Yes");
//         yesButton.setBounds(15, 80, 80, 40);
//         yesButton.setFocusable(true);
//         yesButton.addActionListener((e) -> {
//           this.save();
//           savePrompt.setVisible(false);
//           GameFrame.exitEditor();
//           if (GameFrame.isGame) GameFrame.newGame(this.map.map);
//           Sound.playSfx(0);
//         });
        
//         noButton = new JButton("No");
//         noButton.setBounds(105, 80, 80, 40);
//         noButton.setFocusable(true);
//         noButton.addActionListener((e) -> {
//         	savePrompt.setVisible(false);
//         	// set editor map to last saved version of map
//         	GameFrame.exitEditor();
//             if (GameFrame.isGame) GameFrame.newGame(this.map.map);
//           Sound.playSfx(0);
//         });
        
//         savePrompt.add(saveMessage);
//         savePrompt.add(yesButton);
//         savePrompt.add(noButton);
        
//         optionsButton = new JButton("Options");
//         optionsButton.setBounds(10, 10, 80, 40);
//         optionsButton.setFocusable(true);
//         optionsButton.addActionListener((e) -> {
//           this.setVisible(false);
//           options.setVisible(true);
//           Sound.playSfx(0);
//         });
        
//         returnButtonEditor = new JButton("Return");
//         returnButtonEditor.setBounds(600, 300, 100, 40);
//         returnButtonEditor.setFocusable(true);
//         returnButtonEditor.addActionListener((e) -> {
//           options.setVisible(false);
//           this.setVisible(true);
//           Sound.playSfx(0);
//         });
        
//         exitEditorButton = new JButton("Exit");
//         exitEditorButton.setBounds(600, 350, 100, 40);
//         exitEditorButton.setFocusable(true);
//         exitEditorButton.addActionListener((e) -> {
//           options.setVisible(false);
//           if (!this.saved) savePrompt.setVisible(true);
//           else GameFrame.exitEditor();
//           Sound.playSfx(0);
//         });
        
//         saveButton = new JButton("Save");
//         saveButton.setBounds(600, 400, 100, 40);
//         saveButton.setFocusable(true);
//         saveButton.setEnabled(false);
//         saveButton.addActionListener((e) -> {
//           // save function in bson, add later
//           // Debug.print("called");
//         	saveButton.setEnabled(false);
//           this.save();
//           Sound.playSfx(0);
//         });
        
//         discardButton = new JButton("Discard");
//         discardButton.setBounds(600, 450, 100, 40);
//         discardButton.setFocusable(true);
//         discardButton.addActionListener((e) -> {
//     		// discard function in bson or smth, add later
//         	options.setVisible(false);
//         	GameFrame.exitEditor();
//     		Sound.playSfx(0);
//         });
        
//         playButton = new JButton("Play");
//         playButton.setBounds(600, 500, 100, 40);
//         playButton.setFocusable(true);
//         playButton.addActionListener((e) -> {
//     		// save first
//         	// newgame() for custom levels or smth
//         	options.setVisible(false);
//         	if (!this.saved) {
//         		savePrompt.setVisible(true);
//         		GameFrame.isGame = true;
//         	}
//         	else {
// 	        	// just exits for now, might keep
// 	        	GameFrame.exitEditor();
// 	        	GameFrame.newGame(this.map.map);
//         	}
//     		Sound.playSfx(0);
//         });

//         namingButton = new JButton("Name");
//         namingButton.setBounds(200, 550, 100, 40);
//         namingButton.setFocusable(true);
//         namingButton.addActionListener((e) -> {
//           options.setVisible(false);
//           namer.setVisible(true);
//     		  Sound.playSfx(0);
//         });
        
//         customNamer = new JTextField("choose a file name");
//         customNamer.setFont(new Font("ocr a extended", Font.PLAIN, 15));
//         customNamer.setBounds(400, 200, 600, 20);
//         // customNamer.setVisible(true);
//         customNamer.setFocusable(true);
        
//       //settingsbutton
//         ImageIcon bruh = new ImageIcon("Assets\\settings.png");
//         Image scaleImage = bruh.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
//         bruh = new ImageIcon(scaleImage); // dw
        
//         settingsButton = new JButton();
//         settingsButton.setIcon(bruh);
//         settingsButton.setBounds(1250, 10, 40, 40);
//         settingsButton.setFocusable(true);
//         settingsButton.addActionListener((e) -> {
//         	this.setVisible(false);
//             GameFrame.settings.setVisible(true);
//             Sound.playSfx(0);
//         });
        
//         exitNamerButton = new JButton("Cancel");
//         exitNamerButton.setBounds(400, 250, 80, 40);
//         exitNamerButton.setFocusable(true);
//         exitNamerButton.addActionListener((e) -> {
//           namer.setVisible(false);
//           options.setVisible(true);
//     		  Sound.playSfx(0);
//         });

//         nameSaveButton = new JButton("Ok");
//         nameSaveButton.setBounds(500, 250, 80, 40);
//         nameSaveButton.setFocusable(true);
//         nameSaveButton.addActionListener((e) -> {
//             this.updateName();
// //            saveButton.setEnabled(true);
//             namingButton.setText("Rename");
//             namer.setVisible(false);
//             options.setVisible(true);
//     		  Sound.playSfx(0);
//         });
        
//         options.add(saveButton);
//         options.add(discardButton);
//         options.add(exitEditorButton);
//         options.add(playButton);
//         options.add(returnButtonEditor);
//         options.add(namingButton);
        
//         namer.add(customNamer);
//         namer.add(exitNamerButton);
//         namer.add(nameSaveButton);
        
// //        this.add(options);
// //        this.add(namer);
// //        this.add(savePrompt);

//         GameFrame.frame.getContentPane().add(options);
//         GameFrame.frame.getContentPane().add(namer);
//         GameFrame.frame.getContentPane().add(savePrompt);
        
//         this.add(settingsButton);
//         this.add(optionsButton);
    	
//     	this.setBackground(Color.CYAN);
//         this.setLayout(null);
//         this.setFocusable(true);
//         this.requestFocus();
//         this.addMouseListener(new MouseListener() {
//         	public void mouseClicked(MouseEvent e) {

//             }
        	
//             public void mousePressed(MouseEvent e) {
// //            	System.out.println("here");
// //            	System.out.printf("(%d, %d)\n", e.getX(), e.getY());
//             	putBlock(e);
//             		// x = 128, y = 128
//             		// x = 1278, y = 768
//             }

//             public void mouseReleased(MouseEvent e) {

//             }

//             public void mouseEntered(MouseEvent e) {
            	
//             }

//             public void mouseExited(MouseEvent e) {
            	
//             }
//         });
//         this.addMouseMotionListener(new MouseMotionListener() {
//         	public void mouseMoved(MouseEvent e) {
        		
//         	}
//         	public void mouseDragged(MouseEvent e) {
// //            	System.out.printf("(%d, %d)\n", e.getX(), e.getY());
//             	putBlock(e);
//         	}
//         });
        
//         this.map = new Map();
//         this.map.map = new char[7][11];
//         for (int i = 0; i < 7; i++) Arrays.fill(map.map[i], ' ');

//         ImageIcon board = new ImageIcon("Assets\\test.png");
//         ImageIcon background = new ImageIcon("Assets\\background.png");
//         this.board = board.getImage().getScaledInstance(900, 500, Image.SCALE_DEFAULT);
//         this.bg = background.getImage().getScaledInstance(1300, 900, Image.SCALE_DEFAULT);
        
//         for (int i = 0; i < 9; i++) {
//         	int temp = i;
//         	Icon icon = null;
//         	if (i < 7) icon = new ImageIcon(Block.getImage(lazy[i]));
//         	else if (i == 7) icon = new ImageIcon(map.tileImg);
//         	else icon = new ImageIcon(Player.getImage());
//         	blocks[i] = new JButton();
//         	blocks[i].setIcon(icon);
// //        	blocks[i].setSize(128, 128);
//         	blocks[i].setBounds(95 + i * 128, 5, 128, 128);
//         	blocks[i].addActionListener((e) -> {
//         		if (selected != -1) blocks[selected].setBorder(null);
//         		selected = temp;
// //        		blocks[temp].setBorder(BorderFactory.createRaisedBevelBorder());
//         		blocks[temp].setBorder(BorderFactory.createEtchedBorder());
//         	});
//         	this.add(blocks[i]);
//         }
//     }
    
//     /** 
//      * loops through the internal map and paints sprites according to block type.
//      * paints the graphics that are displayed on the screen 
//      * 
//      * @param g
//      */
//     public void paint(Graphics g) {

//         super.paint(g);
//         g2D = (Graphics2D) g;
//         count = 0;

//         for (int r = 0; r < map.map.length; r++) {
//             for (int c = 0; c < map.map[r].length; c++) {
//                 if (!(r == 0 || c == 0 || r == map.map.length - 1 || c == map.map[0].length - 1))
//                     g2D.drawImage(map.tileImg, c*scalingFactor, r*scalingFactor, null);
//                 switch(map.map[r][c]) {
//                     case 'p':
//                         g2D.drawImage(Player.getImage(), c*scalingFactor, r*scalingFactor, null);
//                         break;

//                     case ' ':
//                         break;

//                     default:
//                     	if (map.map[r][c] != 'x') count++;
//                         g2D.drawImage(Block.getImage(map.map[r][c]), c*scalingFactor, r*scalingFactor, null);
//                         break;
//                 }
//             }           

//         }

//         if(init) {
//             init = false;
//             repaint();
//         }
        
//         // checks if map is valid, meaning it has the player and at least 1 block placed
//     	GameFrame.setPlayButton(!blocks[8].isVisible() && count > 0);
//     }
    
//     public void putBlock(MouseEvent e) {
//     	int x = e.getX(), y = e.getY();
//     	if (x >= 128 && y >= 128 && x < 1278 && y < 768 && selected != -1) {
//     		char temp = map.map[y/128][x/128];
//     		if (selected == 7 && map.map[y/128][x/128] == 'p') blocks[8].setVisible(true);
    		
//         	if (map.map[y/128][x/128] != 'p' || selected == 7) map.map[y/128][x/128] = lazy[selected];
        	
//     		if (selected == 8) {
//     			blocks[8].setBorder(null);
//     			blocks[8].setVisible(false);
//     			selected = -1;
//     		}
//     		if (temp != map.map[y/128][x/128]) {
//     			saved = false;
//     			this.saveButton.setEnabled(true);
//     		}
//     	}
//     	repaint();
    	
// //    	Debug.printMap(map.map);
//     }

//     public void updateName() {
//         name = this.customNamer.getText();
//         save();
//     }

//     public void save() {
//     	saved = true;
    	
// //    	String[] save = Bson.extractClassInfo(Bson.getClass("MapC0", "Saves\\CustomLevels.txt"));
// //    	String[] temp = Bson.writeContent("MapC0", save[1], true, 0, Integer.parseInt(save[4]), Integer.parseInt(save[5]), save[6], Stream.of(save[7].split(", ")).mapToInt(Integer::parseInt).toArray(), Integer.parseInt(save[8]));
// //    	Bson.writeClass("MapC0", temp, "Saves\\CustomLevels.txt");
    	
// //        String template = name + " {\n	\"firstClear\" : true\n	\"starsAchived\" : 0\n	\"col\" : 11\n	\"row\" : 7\n	\"map\" : \"";
// //        String __mapBuff__ = "";
// //        for(int i = 0; i < map.map.length; i++) {
// //            for (int j = 0; j < map.map[0].length; j++) {
// //                __mapBuff__ += map.map[i][j];
// //            }
// //        }
// //        template += __mapBuff__ + "\"\n	\"starBounds\" = -1, -1, -1\n    \"blockCount\" = 1\n	\"isPlayable\" : false\n}";
// //        
// //        Bson.createCustomClass(template, name);
//     }
// }