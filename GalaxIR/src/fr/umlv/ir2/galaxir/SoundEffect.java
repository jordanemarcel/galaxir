package fr.umlv.ir2.galaxir;

import java.io.File;
import java.sql.Time;
import java.util.Calendar;
import java.util.Random;

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
	static Calendar previousCalendar = Calendar.getInstance();
	
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
	
	public static void playGogogo() {
		try {
			//Random r = new Random();
			//String filename = "/home/jordane/music/galaxir/gogogo/gogogo"+r.nextInt(3)+".wav";
			String filename = "/home/jordane/music/galaxir/gogogo/attack.wav";
			File f = new File(filename);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f);
			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioInputStream);        
			clip.start();
		} catch ( Exception e ) {
			//e.printStackTrace();
		}
	}
	
	public static void playMouseOver() {
		try {
			String filename = "/home/jordane/music/galaxir/mouseover.wav";
			System.out.println(filename);
			File f = new File(filename);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f);
			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioInputStream);        
			clip.start();
		} catch ( Exception e ) {
			//e.printStackTrace();
		}
	}
	
	public static void playMouseClick() {
		try {
			String filename = "/home/jordane/music/galaxir/mousedown.wav";
			System.out.println(filename);
			File f = new File(filename);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f);
			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioInputStream);        
			clip.start();
		} catch ( Exception e ) {
			//e.printStackTrace();
		}
	}
	
	public static void playAttack() {
		Calendar now = Calendar.getInstance();
		long previousMilli = previousCalendar.getTimeInMillis();
		long nowMilli = now.getTimeInMillis();
		
		if((nowMilli - previousMilli)<100)
			return;
			
		previousCalendar = now;
		
		try {
			Random r = new Random();
			String filename = "/home/jordane/music/galaxir/laser/laser"+r.nextInt(2)+".wav";
			File f = new File(filename);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f);
			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioInputStream);        
			clip.start();
		} catch ( Exception e ) {
			//e.printStackTrace();
		}
	}
}
