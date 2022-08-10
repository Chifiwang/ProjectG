import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.stream.Stream;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class CustomLevelSelect extends JPanel{
    JComboBox<String> select;
    String[] saves;
    String currentMap;
    char[][] map;
    boolean firstGame;
    boolean[][] map_move;
    int starCount, blockCount;
    int[] starBounds;

    public CustomLevelSelect() {
        super();
		this.setBounds(0, 0, 800, 500);
		this.setLayout(null);
        this.setVisible(false);
        this.setFocusable(true);
        this.requestFocus();

        saves = Bson.getAllClassNames("Saves\\CustomLevels.txt");
        select = new JComboBox<>(saves);
        select.setBounds(80, 50, 500, 20);
        select.addActionListener((e) -> {
            updateSelection();
        });

        this.add(select);

        // updateSaves();
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;

        
        drawMap(g2D);



    }



    public void drawMap(Graphics2D g2D) {
        int scalingFactor = 128;
        ImageIcon tile = new ImageIcon("Assets\\tileBlockNormal.png");
        Image tileImg = tile.getImage().getScaledInstance(GameFrame.scaleFactor, GameFrame.scaleFactor, Image.SCALE_DEFAULT);

        for(int r = 0; r < map.length; r++) {
            for(int c = 0; c < map[0].length; c++) {
                if (!(r == 0 || c == 0 || r == map.length - 1 || c == map[0].length - 1))
                    g2D.drawImage(tileImg, c*scalingFactor, r*scalingFactor, null);
                switch(map[r][c]) {
                    case 'p':
                        g2D.drawImage(Player.getImage(), c*scalingFactor, r*scalingFactor, null);
                        break;

                    case ' ':
                        break;

                    default:
                        g2D.drawImage(Block.getImage(map[r][c]), c*scalingFactor, r*scalingFactor, null);
                        break;
                }
            }
        }
    }

    public void updateSaves() {
        
        select.removeAllItems();
        for (String map : Bson.getAllClassNames("Saves\\CustomLevels.txt")) {
            select.addItem(map);
        }

        updateSelection();
    }

    public void updateSelection() {
        String[] save = Bson.extractClassInfo(Bson.getClass("MapC" + String.valueOf(select.getSelectedIndex()), "Saves\\CustomLevels.txt"));

        if(save.length > 0) {
            int col = Integer.parseInt(save[4]);
            int row = Integer.parseInt(save[5]);
            String mapString = save[6];

            map = new char[row][col];
            for(int i = 0; i < col*row; i++)
                map[i / col][i % col] = mapString.charAt(i);

            map_move = new boolean[row][col];

            starBounds = Stream.of(save[7].split(", ")).mapToInt(Integer::parseInt).toArray();

            blockCount = Integer.parseInt(save[8]);

            firstGame = save[2].contains("true");

        }
    }
}
