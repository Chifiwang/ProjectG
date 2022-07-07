import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.*;

public class GameFrame implements MouseListener {
    Debug debug = new Debug();
    JFrame frame;
    JLabel label;
    JLabel button1;
    Board board;
    LevelSelect levelSelect;
    GameFrame() {
        frame = new JFrame("ProjectG");
        levelSelect = new LevelSelect();        

        label = new JLabel();
        label.setBounds(0, 0, 1100, 900);
        label.setBackground(Color.red);
        label.setOpaque(false);
        label.setVisible(true);
        label.addMouseListener(this);

        button1 = new JLabel();
        button1.setBounds(0, 0, 100, 100);
        button1.setBackground(Color.red);
        button1.setOpaque(true);
        button1.setVisible(true);
        button1.addMouseListener(this);

        frame.getContentPane().add(label);
        frame.getContentPane().add(button1);
        frame.getContentPane().add(levelSelect);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(1300, 900);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);
////        frame.setVisible(false); //you can't see me!
//        frame.dispose(); //Destroy the JFrame object
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (levelSelect.isVisible()) {

            levelSelect.actionHandeler(e.getX(), e.getY());

        } else if (board != null && (board.isLose || board.isWin)) { 

            levelSelect.setVisible(true);

            board.setVisible(false);
            frame.getContentPane().remove(board);
            
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        debug.print("here2");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        debug.print("here3");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        debug.print("here4");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        debug.print("here5");
    }
}