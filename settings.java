import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Settings extends JPanel {
    boolean isOpen = false;

	public Settings() {
        super();
		this.setBounds(0, 0, 800, 500);
		this.setLayout(null);
		this.setBackground(new Color(255, 0, 0, 0));

        setVisible(false);
        setFocusable(true);
        requestFocus();
		
	}

}
