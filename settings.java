import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Settings extends JPanel {
    boolean isOpen = false, isClick = false, isHover = false;
	JPanel musicVolumeDisplay = new JPanel(), sfxVolumeDisplay = new JPanel();
	JSlider musicVolume, sfxVolume;
	JLabel musicLevel = new JLabel("50"), sfxLevel = new JLabel("50"), music = new JLabel("Music Volume"), sfx = new JLabel("SFX Volume");
	int musicVolumeLevel = 50, sfxVolumeLevel = 50;

	public Settings() {
        super();
		this.setBounds(0, 0, 800, 500);
		this.setLayout(null);
//		this.setBackground(new Color(255, 0, 0, 255));

        this.setVisible(false);
        this.setFocusable(true);
        this.requestFocus();
		
        // add more stuff later
		music.setBounds(100, 200, 100, 40);
		music.setVisible(true);
		this.add(music);
        
		musicVolume = new JSlider();
		musicVolume.setBounds(100, 240, 600, 20);
		musicVolume.setPaintTicks(true);
		musicVolume.setMajorTickSpacing(10);
		this.add(musicVolume);
		musicVolume.setVisible(true);
		musicVolume.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				musicVolumeLevel = musicVolume.getValue();
		        displayMusicVolume(musicVolumeLevel);
		        Sound.setMusicVolume(musicVolumeLevel);
			}
		});
		musicVolume.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {

		    }
			
		    public void mousePressed(MouseEvent e) {
		    	isClick = true;
		    }

		    public void mouseReleased(MouseEvent e) {
		    	isClick = false;
		    	if (!isHover) musicVolumeDisplay.setVisible(false);
		    }

		    public void mouseEntered(MouseEvent e) {
		    	isHover = true;
		    	musicVolumeLevel = musicVolume.getValue();
		        displayMusicVolume(musicVolumeLevel);
		    }

		    public void mouseExited(MouseEvent e) {
		    	isHover = false;
		    	if (!isClick) musicVolumeDisplay.setVisible(false);
		    }
		});
		
		musicVolumeDisplay.setVisible(false);
		musicVolumeDisplay.setBackground(Color.white);
		musicVolumeDisplay.add(musicLevel);
		this.add(musicVolumeDisplay);
		
		//sfx
		sfx.setBounds(100, 270, 100, 40);
		sfx.setVisible(true);
		this.add(sfx);
        
		sfxVolume = new JSlider();
		sfxVolume.setBounds(100, 310, 600, 20);
		sfxVolume.setPaintTicks(true);
		sfxVolume.setMajorTickSpacing(10);
		this.add(sfxVolume);
		sfxVolume.setVisible(true);
		sfxVolume.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				sfxVolumeLevel = sfxVolume.getValue();
		        displaySfxVolume(sfxVolumeLevel);
		        Sound.setSfxVolume(sfxVolumeLevel);
			}
		});
		sfxVolume.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {

		    }
			
		    public void mousePressed(MouseEvent e) {
		    	isClick = true;
		    }

		    public void mouseReleased(MouseEvent e) {
		    	isClick = false;
		    	if (!isHover) sfxVolumeDisplay.setVisible(false);
		    }

		    public void mouseEntered(MouseEvent e) {
		    	isHover = true;
		    	sfxVolumeLevel = sfxVolume.getValue();
		        displaySfxVolume(sfxVolumeLevel);
		    }

		    public void mouseExited(MouseEvent e) {
		    	isHover = false;
		    	if (!isClick) sfxVolumeDisplay.setVisible(false);
		    }
		});
		
		sfxVolumeDisplay.setVisible(false);
		sfxVolumeDisplay.setBackground(Color.white);
		sfxVolumeDisplay.add(sfxLevel);
		this.add(sfxVolumeDisplay);
	}
	
	public void displayMusicVolume(int i) {
		musicLevel.setText(String.valueOf(i));
		//starts at 98
		//ends at 682
		musicVolumeDisplay.setBounds(98 + (int)Math.round(5.84 * i), 220, 20, 20);
		musicVolumeDisplay.setVisible(true);
	}
	
	public void displaySfxVolume(int i) {
		sfxLevel.setText(String.valueOf(i));
		//starts at 98
		//ends at 682
		sfxVolumeDisplay.setBounds(98 + (int)Math.round(5.84 * i), 290, 20, 20);
		sfxVolumeDisplay.setVisible(true);
	}
}
