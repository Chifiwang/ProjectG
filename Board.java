import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener{
    ArrayList<BasicBlock> basicBlocks = new ArrayList<BasicBlock>();
    Map mapGlobal = new Map();
    Debugg debugg = new Debugg();
    Player player;
    Image img;
    Map map;
    Timer t;

    /* this is where most of the bg and stuff are init'd */
    public Board() {
        this.map = new Map(mapGlobal.map_test);
        player = new Player(map);

        for (int i = 0; i < map.basicCoords.size(); i++) {
            basicBlocks.add(new BasicBlock(map, i));
        }

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
        // player.jump();
        for (BasicBlock b : basicBlocks) {
            b.move();
        }
        player.move();
        map.redraw(map.map);
        repaint();
        
    }
    /* paints the graphics that are displayed on the screen */
    public void paint(Graphics g) {

        super.paint(g);
            Graphics2D g2D = (Graphics2D) g;

            g2D.drawImage(img, 0, 0, null);
            g2D.drawImage(player.getImage(), player.getX(), player.getY(), null);
            for(BasicBlock b : basicBlocks) {
                g2D.drawImage(b.getImage(), b.getX(), b.getY(), null);
            }
    }

    /* Calls the key listener methods defined in the player class */
    private class AL extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
        	char key = e.getKeyChar();
        	if (key == 's' || key == 'd') {
	            for(int i = 0; i < basicBlocks.size(); i++) {
	            	basicBlocks.get(i).keyPressed(e);
	            }
            }
        	else if (key == 'w' || key == 'a') {
	            for(int i = basicBlocks.size() - 1; i >= 0; i--) {
	                basicBlocks.get(i).keyPressed(e);
	            }
            }
            player.keyPressed(e);
            // debugg.printMap(map.map);
        }

        public void keyReleased(KeyEvent e) {
//        	char key = e.getKeyChar();
//        	if (key == 's' || key == 'd') {
	            for(BasicBlock b : basicBlocks) {
	                b.keyReleased(e);
	            }
//            }
//        	else if (key == 'w' || key == 'a') {
//	            for(int i = basicBlocks.size() - 1; i >= 0; i--) {
//	                basicBlocks.get(i).keyReleased(e);
//	            }
//            }
            player.keyReleased(e);
        }
    }
}
