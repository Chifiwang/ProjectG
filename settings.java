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

        this.setVisible(false);
        this.setFocusable(true);
        this.requestFocus();
		
        // add more stuff later
	}

}
