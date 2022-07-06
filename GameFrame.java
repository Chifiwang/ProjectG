import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.*;

public class GameFrame implements MouseListener{
    Debug debug = new Debug();
    JFrame frame;
    JLabel label;
    Board board;

    GameFrame() {
        frame = new JFrame("ProjectG");
        board = new Board();

        label = new JLabel();
        label.setBounds(0, 0, 1300, 900);
        label.setBackground(Color.red);
        label.setOpaque(false);
        label.setVisible(true);
        label.addMouseListener(this);

        frame.add(label);
        frame.add(board);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((board.map.map[0].length + 2) * 100, (board.map.map.length + 2) * 100);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);
////        frame.setVisible(false); //you can't see me!
//        frame.dispose(); //Destroy the JFrame object
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        debug.print("here");

        if (board.isLose || board.isWin) {

            debug.print("remove?");
            board.setVisible(false);
            
            board = new Board();
            frame.add(board);
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