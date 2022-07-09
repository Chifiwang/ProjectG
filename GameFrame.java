import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

// import java.awt.event.MouseEvent;
// import java.awt.event.MouseListener;

// import java.awt.*;

public class GameFrame/*  implements MouseListener */ {
    Debug debug = new Debug();
    JFrame frame;
    JLabel label;
    static JButton testButton;
    static JButton testButton1;
    static JButton testButton2;
    static JButton testButton3;
    static JButton testButton4;
    static JButton testButton5;


    Board board;
    LevelSelect levelSelect;
    Settings settings;
    static JButton[] buttons = {testButton, testButton1, testButton2, testButton3, testButton4, testButton5};

    GameFrame() {
        frame = new JFrame("ProjectG");
        levelSelect = new LevelSelect(); 
        settings = new Settings();       

        buttons[0] = new JButton();
        buttons[0].setBounds(900, 50, 50, 400);
        buttons[0].setVisible(false);
        buttons[0].addActionListener( (e) -> {if (e.getSource() == buttons[0]) {
            
            debug.print("will do something");
        }});

        
        buttons[1] = new JButton();
        buttons[1].setBounds(300, 50, 500, 400);
        buttons[1].setVisible(false);
        buttons[1].addActionListener( (e) -> {if (e.getSource() == buttons[1]) {

            for (JButton b : buttons) {
                b.setVisible(false);
            }

            board = new Board(levelSelect.map);
            levelSelect.setVisible(false);
            frame.getContentPane().add(board);
        }});

        buttons[2] = new JButton();
        buttons[2].setBounds(150, 50, 50, 400);
        buttons[2].setVisible(false);
        buttons[2].addActionListener( (e) -> {if (e.getSource() == buttons[2]) {
            debug.print("will do something");
        }});

        buttons[3] = new JButton();
        buttons[3].setBounds(0, 0, 1300, 900);
        buttons[3].setVisible(true);
        buttons[3].addActionListener( (e) -> {if (e.getSource() == buttons[3]) {

            buttons[3].setVisible(false);

            buttons[0].setVisible(true);
            buttons[1].setVisible(true);
            buttons[2].setVisible(true);

            levelSelect.setVisible(true);
        }});

        buttons[4] = new JButton();
        buttons[4].setBounds(0, 0, 1300, 900);
        buttons[4].setVisible(false);
        buttons[4].addActionListener( (e) -> {if (e.getSource() == buttons[4]) {

            buttons[4].setVisible(false);

            buttons[0].setVisible(true);
            buttons[1].setVisible(true);
            buttons[2].setVisible(true);

            levelSelect.setVisible(true);
            
            board.setVisible(false);
            frame.getContentPane().remove(board);
        }});

        buttons[5] = new JButton();
        buttons[5].setBounds(1200, 10, 75, 50);
        buttons[5].setVisible(true);
        buttons[5].addActionListener( (e) -> {if (e.getSource() == buttons[5]) {
            debug.print("here");
            if(!settings.isOpen)
                settings.setVisible(true);
            else
                settings.setVisible(false);
        }});

        AnimationHandeler.setLevelSelect(levelSelect);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 900);
        frame.setVisible(true);

        for(JButton b : buttons) {
            frame.getContentPane().add(b);
        }

        frame.getContentPane().add(levelSelect);
        frame.getContentPane().add(settings);
    }
}