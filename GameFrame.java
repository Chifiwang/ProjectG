import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.event.*;

public class GameFrame extends JFrame implements KeyListener{

    JLabel player;

    GameFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 450);
        this.setLayout(null);
        this.addKeyListener(this);

        player = new JLabel();
        player.setBounds(0, 0, 100, 100);
        player.setBackground(Color.red);
        player.setOpaque(true);

        this.add(player);
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyChar()) {
            case 'w': player.setLocation(player.getX(), player.getY() - 1);
                break;
            case 'a': player.setLocation(player.getX() - 1, player.getY());
                break;
            case 's': player.setLocation(player.getX(), player.getY() + 1);
                break;
            case 'd': player.setLocation(player.getX() + 1, player.getY());
                break;
        }
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case 38: player.setLocation(player.getX(), player.getY() - 1);
                break;
            case 37: player.setLocation(player.getX() - 1, player.getY());
                break;
            case 40: player.setLocation(player.getX(), player.getY() + 1);
                break;
            case 39: player.setLocation(player.getX() + 1, player.getY());
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("released " + e.getKeyCode() + " " + e.getKeyChar());
    }
}
