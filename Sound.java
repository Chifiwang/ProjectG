import java.io.*;
import javax.sound.sampled.*;

public class Sound {
	static Clip levelMusic, gameMusic;
	static int volume = 50;
	
	public Sound() {}
    
    
	/** 
	 * initializes the sound player
	 * 
	 * @throws Exception
	 */
	public static void init() throws Exception {
    	AudioInputStream level = AudioSystem.getAudioInputStream(new File("Sound\\kahoot.wav")), game = AudioSystem.getAudioInputStream(new File("Sound\\misstherage.wav"));
    	levelMusic = AudioSystem.getClip();
    	levelMusic.open(level);
    	gameMusic = AudioSystem.getClip();
    	gameMusic.open(game);
    	playMusic(0);
    }
	
    
	/** 
	 * Play's the background music
	 * 
	 * @param music provides an id for which music needs to be played
	 */
	public static void playMusic(int music) {
    	switch (music) {
    		case 1:
    			levelMusic.stop();
    			levelMusic.setMicrosecondPosition(0);
    			gameMusic.loop(Clip.LOOP_CONTINUOUSLY);
    			break;
    		default:
    			gameMusic.stop();
    			gameMusic.setMicrosecondPosition(0);
    	    	levelMusic.loop(Clip.LOOP_CONTINUOUSLY);
    			break;
    	}
    }
    
    
	/** 
	 * plays a sound effect
	 * 
	 * @param sfx sends query to which sound effect needs to be played
	 */
	public static void playSfx(int sfx) {
    	try {
    		sfx(sfx);
    	} catch (Exception e) {
    		Debug.print("Sound effect could not play");
    		e.printStackTrace();
    	}
    }
    
    
	/** 
	 * determines and plays the correct sound effect
	 * 
	 * @param sfx provides a key for which sound effect needs to be played
	 * @throws Exception
	 */
	public static void sfx(int sfx) throws Exception {
    	AudioInputStream stream = null;
    	switch (sfx) {
    		case 1:
    			break;
    		default:
    			stream = AudioSystem.getAudioInputStream(new File("Sound\\click.wav"));
    			break;
    	}
    	Clip clip = AudioSystem.getClip();
    	clip.open(stream);
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);       
        gainControl.setValue(20f * (float) Math.log10(Sound.volume/100.0));
    	clip.start();
    }

    
    
	/** 
	 * Sets the music volume
	 * 
	 * @param volume provides the volume in %
	 */
	public static void setMusicVolume(int volume) {
		FloatControl gainControl = (FloatControl) levelMusic.getControl(FloatControl.Type.MASTER_GAIN);       
        gainControl.setValue(20f * (float) Math.log10(volume/100.0));
        gainControl = (FloatControl) gameMusic.getControl(FloatControl.Type.MASTER_GAIN);       
        gainControl.setValue(20f * (float) Math.log10(volume/100.0));
    }
    
    
	/** 
	 * Sets the sound effect's volume
	 * 
	 * @param volume provides the volume in %
	 */
	public static void setSfxVolume(int volume) {
    	Sound.volume = volume;
    }
}
