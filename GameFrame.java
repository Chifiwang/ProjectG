import javax.swing.JFrame;
import java.awt.*;

public class GameFrame {
    Debug debug = new Debug();

    GameFrame() {
        JFrame frame = new JFrame("ProjectG");
        frame.add(new Board());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-650, dim.height/2-450);
        frame.setSize(1300, 900);
        frame.setVisible(true);
    }
}