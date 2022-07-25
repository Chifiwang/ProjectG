import java.awt.*;
import javax.swing.*;
//import java.util.*;

public class Directory extends JPanel {
	int unlocked = 1;
	JButton okButton;
	JButton[] blocks = new JButton[7];
	JLabel[] image = new JLabel[7], name = new JLabel[7], description = new JLabel[7];
	JPanel buttons = new JPanel(), display = new JPanel();
	
	public Directory() {
		super();
		this.setLayout(null);
		this.setBounds(0, 0, 800, 500);
        this.setVisible(false);
        this.setFocusable(true);
        this.requestFocus();
        
        buttons.setLayout(new GridLayout(3, 3));
        buttons.setBounds(0, 100, 800, 400);
        this.add(buttons);
        
        okButton = new JButton("Ok");
        okButton.setBounds(375, 350, 50, 40);
        okButton.addActionListener((e) -> {
        	displayButtons();
        });
        display.add(okButton);
        
        char[] lazy = {'u', 'x', '>', '^', 'm', 'l', 'C'};
        for (int i = 0; i < 7; i++) {
        	int temp = i;
        	Icon icon = new ImageIcon(Block.getImage(lazy[i]));
        	blocks[i] = new JButton();
        	blocks[i].setIcon(icon);
        	blocks[i].setSize(128, 128);
        	blocks[i].setVisible(i <= unlocked); // for testing, comment this line out
        	blocks[i].addActionListener((e) -> {
        		displayBlock(temp);
        	});
        	buttons.add(blocks[i]);
        	image[i] = new JLabel();
        	image[i].setIcon(icon);
        	image[i].setBounds(336, 36, 128, 128);
        	name[i] = new JLabel();
        	name[i].setBounds(300, 210, 200, 40);
        	description[i] = new JLabel();
        	description[i].setBounds(0, 260, 800, 40);
        }
        
        name[0].setText("Universal Block");
        name[1].setText("Immovable Block");
        name[2].setText("Left-Right Block");
        name[3].setText("Up-Down Block");
        name[4].setText("Push Minimum Block");
        name[5].setText("Push Limit Block");
        name[6].setText("Chain Block");
        
        description[0].setText("The basic block. It can be pushed in all directions.");
        description[1].setText("The obstacle block. It cannot be pushed by any block.");
        description[2].setText("The first of the directional blocks. It can be only be pushed left or right.");
        description[3].setText("The second of the directional blocks. It can only be pushed up or down.");
        description[4].setText("The bruh block. It can be pushed in all directions, but the total blocks being pushed by the player must be at least 2.");
        description[5].setText("The bruher block. It can be pushed in all directions, but the total blocks being pushed by the player must be at most 2.");
        description[6].setText("The bruhest block. It can be pushed in all directions, but only by the player or another chain block. It cannot push other block types.");
	}
	
	/** 
     * uses the last unlocked level to determine how many blocks are initially visible in the directory
     * 
     * @param level is the last level the user has unlocked
     */
	public void loadSave(int level) { // probably needs more testing
		if (level > 10) unlocked++;
		if (level > 11) unlocked++;
		if (level > 20) unlocked++;
		if (level > 30) unlocked++;
		if (level > 40) unlocked++;
	}
	
	/** 
     * uses the last unlocked level to determine how many blocks are currently visible in the directory
     * 
     * @param level is the last level the user has unlocked
     */
	public void add(int level) { // probably needs more testing
		switch (level) {
			case 11:
				unlocked++;
				break;
			case 12:
				unlocked++;
				break;
			case 21:
				unlocked++;
				break;
			case 31:
				unlocked++;
				break;
			case 41:
				unlocked++;
				break;
			default:
				break;
		}
		blocks[unlocked].setVisible(true);
	}
	
	/** 
     * displays info for the selected block
     * 
     * @param block is the block being displayed
     */
	public void displayBlock(int block) {
		buttons.setVisible(false);
		display = new JPanel();
        display.setLayout(null);
        display.setBounds(0, 100, 800, 400);
        display.add(okButton);
        display.add(image[block]);
		display.add(name[block]);
		display.add(description[block]);
		display.setVisible(true);
		this.add(display);
	}
	
	/** 
     * displays all the unlocked blocks as buttons
     * 
     */
	public void displayButtons() {
		this.remove(display);
		display.setVisible(false);
		buttons.setVisible(true);
	}
}
