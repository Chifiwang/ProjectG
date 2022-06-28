import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.event.*;

public class GameFrame {
    Debugg debugg = new Debugg();

    GameFrame() {
        JFrame frame = new JFrame("ProjectG");
        frame.add(new Board());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 450);
        frame.setVisible(true);
    }
}