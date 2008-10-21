package fr.umlv.ir2.galaxir;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class SoundEffect {
	static File f = null;
	static AudioInputStream audioInputStream = null;
	static AudioFormat format = null;
	static DataLine.Info info = null;
	static Clip clip = null; 
	
	public static void playExplosion() {
		try {
			if(audioInputStream==null) {
				f = new File("/home/jordane/Music/explo01.wav");
				audioInputStream = AudioSystem.getAudioInputStream(f);
				format = audioInputStream.getFormat();
				info = new DataLine.Info(Clip.class, format);
				clip = (Clip) AudioSystem.getLine(info);
				clip.open(audioInputStream);      
			}       
			else {
				clip.setFramePosition(0);
			}
			clip.start();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
}
