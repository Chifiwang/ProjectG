import java.awt.*;
import javax.swing.*;
import java.awt.Graphics2D;

public class LevelSelect extends JPanel {

    Image board;
    Image bg;

    Graphics2D g2D;
    JButton button;
    int mapButton_Type = 0;

    int activeButton = -1;

    boolean isTest = false;
    int map = 0, unlocked = 0;

    public LevelSelect() {
    	this.setLayout(null);
        this.setBounds(0, 0, 1300, 900);
        Debug.print("hallo");

        ImageIcon board = new ImageIcon("Assets\\dead.png");
        ImageIcon background = new ImageIcon("Assets\\levelSelect_background.png");
        this.board = board.getImage();
        bg = background.getImage();

        this.setVisible(true);
        this.setFocusable(true);
        this.requestFocus();
    }
}
