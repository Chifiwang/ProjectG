import javax.swing.JFrame;

public class GameFrame {
    Debugg debugg = new Debugg();

    GameFrame() {
        JFrame frame = new JFrame("ProjectG");
        frame.add(new Board());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(1000, 600);
        frame.setVisible(true);
    }
}