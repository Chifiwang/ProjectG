import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener{
    // ArrayList<BasicBlock> basicBlocks = new ArrayList<BasicBlock>();
    Map mapGlobal = new Map();
    Debugg debugg = new Debugg();

    Player player;
    Block block;

    long timeStart, timeDelay = 300, dt = 300;
    boolean isValid = false;
    int scalingFactor = 100;

    Image img;
    Map map;
    Timer t;

    /* this is where most of the bg and stuff are init'd */
    public Board() {
        this.map = new Map(mapGlobal.map01);
        player = new Player(map);
        block = new Block();

        // for (int i = 0; i < map.basicCoords.size(); i++) {
        //     Blocks.add(new Block(map, type));
        // }

        addKeyListener(new AL());
        setFocusable(true);

        ImageIcon background = new ImageIcon("Assets/test.png");
        img = background.getImage();

        t = new Timer(5, this);
        t.start();
    }

    /* Player loop thing. I think that this is the main loop for interacting with things */
    @Override
    public void actionPerformed(ActionEvent e) {

        
    }
    /* paints the graphics that are displayed on the screen */
    public void paint(Graphics g) {

        super.paint(g);
            Graphics2D g2D = (Graphics2D) g;

            g2D.drawImage(img, 0, 0, null);

            for (int r = 0; r < map.map.length; r++) {
                for (int c = 0; c < map.map[r].length; c++) {
                    switch(map.map[r][c]) {
                        case 'p':
                            g2D.drawImage(player.getImage(), c*scalingFactor, r*scalingFactor, null);
                            break;
                        case ' ': break;
                        default:
                            g2D.drawImage(block.getImage(map.map[r][c]), c*scalingFactor, r*scalingFactor, null);
                    }
                }
            }
    }

    /* Calls the key listener methods defined in the player class */
    private class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            debugg.print("arrived");
            char key = e.getKeyChar();
            switch(key) {
                case 'w':
                    isValid = player.canMoveObj(Map.direct = 0);
                    break;
                case 'a': 
                    
                    isValid = player.canMoveObj(Map.direct = 1);    
                    break;
                case 's':
                    isValid = player.canMoveObj(Map.direct = 2);
                    break;
                case 'd':
                    debugg.print("d arrived");
                    isValid = player.canMoveObj(Map.direct = 3);
                    break;
                default:
                    Map.direct = 4;
                    isValid = false;
                    break;
            }
            debugg.printMap(map.map);

            if (dt >= timeDelay) {
                timeStart = System.currentTimeMillis();
            }
            
            if(isValid && dt >= timeDelay) {
                player.move();
            }
    
    
            repaint();
    
            dt = System.currentTimeMillis() - timeStart;
        }

        public void keyReleased(KeyEvent e) {
            Map.direct = 4;
            isValid = false;
        }
    }
}
