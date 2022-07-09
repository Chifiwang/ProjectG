import java.awt.*;
import javax.swing.*;

public class LevelSelect extends JPanel {
    Debug debug = new Debug();

    Image board;
    Image bg;

    Graphics2D g2D;
    JButton button;
    int mapButton_Type = 0;

    int activeButton = -1;

    boolean isTest = false;
    int map = 0;

    // private final int[][] objects = {
    //     {}
    // };

    public LevelSelect() {

        


        ImageIcon board = new ImageIcon("Assets\\dead.png");
        ImageIcon background = new ImageIcon("Assets\\levelSelect_background.png");
        this.board = board.getImage();
        bg = background.getImage();

        setVisible(false);
        setFocusable(true);
        requestFocus();
    }

    public void paint(Graphics g) {
        
        super.paint(g);
        g2D = (Graphics2D) g;
        g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 

        g2D.drawImage(bg, 0, 0, null);
        g2D.drawImage(board, 500, 200, null);

        if(isTest) {
        }

    }

    public void actionHandeler(int x, int y) {

        // getButton(x, y);

        // if(activeButton == 0) {
        isTest = true;
        setOutput();
            
        // }

        repaint();

    }

    public void getButton(int x, int y) {
        if (x > 500 && x < 600 && y > 200 && y < 300) {
            activeButton = 0;
            board = new ImageIcon("Assets\\chainE.png").getImage();
        } else {
            activeButton = -1;
            board = new ImageIcon("Assets\\dead.png").getImage();
        }
    }

    public void setOutput() {
        if(isTest)
            map = 0;
    }


}
