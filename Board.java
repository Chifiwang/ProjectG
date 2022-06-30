import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener{

    Map mapGlobal = new Map();
    Debugg debugg = new Debugg();

    Player player;
    Block block;

    long timeStart, timeDelay = 200, dt = 200;
    boolean isValid = false;
    int scalingFactor = 100;

    Image img;
    Map map;
    Timer t;

    public Board() {
        this.map = new Map(mapGlobal.levelSelect[0]);
        player = new Player(map, map.loadPlayer(map.map));
        block = new Block();

        addKeyListener(new AL());
        setFocusable(true);

        ImageIcon background = new ImageIcon("Assets/test.png");
        img = background.getImage();

        t = new Timer(5, this);
        t.start();
    }

    
    /** 
     * does nothing lmao
     * 
     * @param e a yes code...
     */
    @Override
    public void actionPerformed(ActionEvent e) { }

    
    
    /** 
     * loops through the internal map and paints sprites according to block type.
     * paints the graphics that are displayed on the screen 
     * 
     * @param g
     */
    public void paint(Graphics g) {

        super.paint(g);
            Graphics2D g2D = (Graphics2D) g;

            g2D.drawImage(img, 0, 0, null);

            for (int r = 0; r < map.map.length; r++) {
                for (int c = 0; c < map.map[r].length; c++) {

                    switch(map.map[r][c]) {
                        case 'p':
                            g2D.drawImage(player.getImage(), c*scalingFactor, 
                                          r*scalingFactor, null);
                            break;

                        case ' ': 
                            break;

                        default:
                            g2D.drawImage(block.getImage(map.map[r][c]), c*scalingFactor, 
                                          r*scalingFactor, null);
                            break;
                    }
                }
            }
    }

    /* calls different validation methods from player.java and block.java */
    private class AL extends KeyAdapter {

        /** 
         * on event, call the {@cod player.canMoveObj(dircet);} method with the appropriate
         * encoded direction values, up = 0, left = 1, down = 2, right = 3, none = 4.
         * 
         * the method then calls the move function if the move is valid.
         * 
         * @param e keyboard event
         */
        public void keyPressed(KeyEvent e) {

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
                    isValid = player.canMoveObj(Map.direct = 3);
                    break;

                default:
                    Map.direct = 4;
                    isValid = false;
                    break;
            }
            debugg.printMap(map.map);

            if (dt >= timeDelay) { // jank time gate, needs revamping.
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
