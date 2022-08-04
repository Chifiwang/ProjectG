import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class Editor extends JPanel {
	
	boolean init = true;

    /* objects */
    JLabel score;
    Block block;
    
	JButton[] blocks = new JButton[9];

    char[] lazy = {'u', 'x', '>', '^', 'm', 'l', 'C', ' ', 'p'};
    int selected = -1, count = 0;

    /* variables */
    Graphics2D g2D;

    int scalingFactor = GameFrame.scaleFactor;
    String name = "";

    Image board;
    Image bg;

    Map map;
    Timer t;

    public Editor() {
    	
    	this.setBackground(Color.CYAN);
        this.setLayout(null);
        this.setFocusable(true);
        this.requestFocus();
        this.addMouseListener(new MouseListener() {
        	public void mouseClicked(MouseEvent e) {

            }
        	
            public void mousePressed(MouseEvent e) {
//            	System.out.println("here");
//            	System.out.printf("(%d, %d)\n", e.getX(), e.getY());
            	putBlock(e);
            		// x = 128, y = 128
            		// x = 1278, y = 768
            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {
            	
            }

            public void mouseExited(MouseEvent e) {
            	
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
        	public void mouseMoved(MouseEvent e) {
        		
        	}
        	public void mouseDragged(MouseEvent e) {
//            	System.out.printf("(%d, %d)\n", e.getX(), e.getY());
            	putBlock(e);
        	}
        });
        
        this.map = new Map();
        this.map.map = new char[7][11];
        for (int i = 0; i < 7; i++) Arrays.fill(map.map[i], ' ');

        ImageIcon board = new ImageIcon("Assets\\test.png");
        ImageIcon background = new ImageIcon("Assets\\background.png");
        this.board = board.getImage().getScaledInstance(900, 500, Image.SCALE_DEFAULT);
        this.bg = background.getImage().getScaledInstance(1300, 900, Image.SCALE_DEFAULT);
        
        for (int i = 0; i < 9; i++) {
        	int temp = i;
        	Icon icon = null;
        	if (i < 7) icon = new ImageIcon(Block.getImage(lazy[i]));
        	else if (i == 7) icon = new ImageIcon(map.tileImg);
        	else icon = new ImageIcon(Player.getImage());
        	blocks[i] = new JButton();
        	blocks[i].setIcon(icon);
//        	blocks[i].setSize(128, 128);
        	blocks[i].setBounds(95 + i * 128, 5, 128, 128);
        	blocks[i].addActionListener((e) -> {
        		if (selected != -1) blocks[selected].setBorder(null);
        		selected = temp;
//        		blocks[temp].setBorder(BorderFactory.createRaisedBevelBorder());
        		blocks[temp].setBorder(BorderFactory.createEtchedBorder());
        	});
        	this.add(blocks[i]);
        }
    }
    
    /** 
     * loops through the internal map and paints sprites according to block type.
     * paints the graphics that are displayed on the screen 
     * 
     * @param g
     */
    public void paint(Graphics g) {

        super.paint(g);
        g2D = (Graphics2D) g;
        count = 0;

        for (int r = 0; r < map.map.length; r++) {
            for (int c = 0; c < map.map[r].length; c++) {
                if (!(r == 0 || c == 0 || r == map.map.length - 1 || c == map.map[0].length - 1))
                    g2D.drawImage(map.tileImg, c*scalingFactor, r*scalingFactor, null);
                switch(map.map[r][c]) {
                    case 'p':
                        g2D.drawImage(Player.getImage(), c*scalingFactor, r*scalingFactor, null);
                        break;

                    case ' ':
                        break;

                    default:
                    	if (map.map[r][c] != 'x') count++;
                        g2D.drawImage(Block.getImage(map.map[r][c]), c*scalingFactor, r*scalingFactor, null);
                        break;
                }
            }           

        }

        if(init) {
            init = false;
            repaint();
        }
        
        // checks if map is valid, meaning it has the player and at least 1 block placed
    	GameFrame.setPlayButton(!blocks[8].isVisible() && count > 0);
    }
    
    public void putBlock(MouseEvent e) {
    	int x = e.getX(), y = e.getY();
    	if (x >= 128 && y >= 128 && x < 1278 && y < 768 && selected != -1) {
    		if (selected == 7 && map.map[y/128][x/128] == 'p') blocks[8].setVisible(true);
    		
        	if (map.map[y/128][x/128] != 'p' || selected == 7) map.map[y/128][x/128] = lazy[selected];
        	
    		if (selected == 8) {
    			blocks[8].setBorder(null);
    			blocks[8].setVisible(false);
    			selected = -1;
    		}
    	}
    	repaint();
    	
//    	Debug.printMap(map.map);
    }

    public void updateName() {
        name = GameFrame.customNamer.getText();
    }

    public void save() {
        String template = name + " {\n	\"firstClear\" : true\n	\"starsAchived\" : 0\n	\"col\" : 11\n	\"row\" : 7\n	\"map\" : \"";
        String __mapBuff__ = "";
        for(int i = 0; i < map.map.length; i++) {
            for (int j = 0; j < map.map[0].length; j++) {
                __mapBuff__ += map.map[i][j];
            }
        }
        template += __mapBuff__ + "\"\n	\"starBounds\" = -1, -1, -1\n      \"blockCount\" = 1\n	\"isPlayable\" : false\n}";
        
        Bson.createCustomClass(template, name);
    }
}