package fr.umlv.galaxir.testJordane;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class SoundEffect {
	public static void playExplosion() {
		File f = new File("/home/jordane/Music/explo1.wav");
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f);
			AudioFormat format = audioInputStream.getFormat();      
			DataLine.Info info = new DataLine.Info(Clip.class, format);      
			Clip clip = (Clip) AudioSystem.getLine(info);         
			clip.open(audioInputStream);      
			clip.start();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
}
