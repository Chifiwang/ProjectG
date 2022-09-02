package legacy;
// import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.awt.Image;
// import java.util.stream.Stream;

// import javax.swing.ImageIcon;
// import javax.swing.JComboBox;
// import javax.swing.JPanel;
// import javax.swing.JButton;

// public class CustomLevelSelect extends JPanel{
//     JComboBox<String> select;

//     JButton cLevelSelectButton, 
//             exitButton, test;

//     String[] saves;
//     String currentMap;
//     char[][] map;
//     boolean firstGame;
//     boolean[][] map_move;
//     int starCount, blockCount;
//     int[] starBounds;

//     public CustomLevelSelect() {

//         super();

// 		this.setBounds(0, 0, 800, 500);
// 		this.setLayout(null);
//         this.setVisible(false);
//         this.setFocusable(true);
//         this.requestFocus();

//         cLevelSelectButton = new JButton("Custom");
//             cLevelSelectButton.setBounds(1140, 60, 100, 40);
//             cLevelSelectButton.setFocusable(true);
//             cLevelSelectButton.addActionListener((e) -> {
//                 // updateSaves();

//                 this.setVisible(true);
//                 GameFrame.levelSelect.setVisible(false);
//                 Sound.playSfx(0);
//             });

//         exitButton = new JButton("exit");
//             exitButton.setBounds(1140, 60, 100, 40);
//             exitButton.setFocusable(true);
//             exitButton.addActionListener((e) -> {
//                 this.setVisible(false);
//                 GameFrame.levelSelect.setVisible(true);
//                 Sound.playSfx(0);
//             });

//         test = new JButton("test");
//             test.setBounds(1140, 10, 100, 40);
//             test.setFocusable(true);
//             test.addActionListener((e) -> {
//                 updateSaves();
//                 Sound.playSfx(0);
//             });

//         GameFrame.levelSelect.add(cLevelSelectButton);
//         this.add(exitButton);
//         this.add(test);

//         saves = Bson.getAllClassNames("Saves\\CustomLevels.txt");
//         select = new JComboBox<>(saves);
//         select.setBounds(80, 50, 500, 20);
//         select.addActionListener((e) -> {
//             updateSelection();
//         });

//         this.add(select);

//         // updateSaves();
//     }

//     public void paint(Graphics g) {
//         super.paint(g);

//         Graphics2D g2D = (Graphics2D) g;

        
//         // drawMap(g2D);



//     }



//     public void drawMap(Graphics2D g2D) {
//         int scalingFactor = 128;
//         ImageIcon tile = new ImageIcon("Assets\\tileBlockNormal.png");
//         Image tileImg = tile.getImage().getScaledInstance(GameFrame.scaleFactor, GameFrame.scaleFactor, Image.SCALE_DEFAULT);

//         for(int r = 0; r < map.length; r++) {
//             for(int c = 0; c < map[0].length; c++) {
//                 if (!(r == 0 || c == 0 || r == map.length - 1 || c == map[0].length - 1))
//                     g2D.drawImage(tileImg, c*scalingFactor, r*scalingFactor, null);
//                 switch(map[r][c]) {
//                     case 'p':
//                         g2D.drawImage(Player.getImage(), c*scalingFactor, r*scalingFactor, null);
//                         break;

//                     case ' ':
//                         break;

//                     default:
//                         g2D.drawImage(Block.getImage(map[r][c]), c*scalingFactor, r*scalingFactor, null);
//                         break;
//                 }
//             }
//         }
//     }

//     public void updateSaves() {
        
//         select.removeAllItems();
//         String[] temp = Bson.getAllClassNames("Saves\\CustomLevels.txt");
//         for (String map : temp) {
//             select.addItem(map);
//         }
//     }

//     public void updateSelection() {
//         String[] saveInfo = Bson.extractClassInfo(Bson.getClass(Integer.toString(select.getSelectedIndex()), "Saves\\CustomLevels.txt"));

//         for (String info : saveInfo) {
//             Debug.print(info);
//         }
//     }
// }
