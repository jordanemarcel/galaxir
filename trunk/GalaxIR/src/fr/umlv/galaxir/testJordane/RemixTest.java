package fr.umlv.galaxir.testJordane;


import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import fr.umlv.remix.Application;


public class RemixTest {

	public static void main(String[] args) {
		/*
		File f = new File("/home/jordane/Music/Megaman_2/mm2w1rm.mid");
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(f);
			AudioFormat format = audioInputStream.getFormat();      
			DataLine.Info info = new DataLine.Info(Clip.class, format);      
			Clip clip = (Clip) AudioSystem.getLine(info);         
			clip.open(audioInputStream);      
			clip.start();
		} catch ( Exception e ) {
			e.printStackTrace();
		}*/


		
		
		Random random = new Random();
		ArrayList<GalaxyItem> testItemList = new ArrayList<GalaxyItem>();
		/*
		 * Randomly position 25 TestItems in the Arena zone (defined afterwards)
		 */
		Ship.l = testItemList;
		/*try {
			testItemList.add(new Picture("/hme/jordane/pictures/linux/Linux_XP.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Player p = new Player("Clement",Color.red,Color.pink);
		
		//testItemList.add(new Xtwin(new Point(10,10),new Point(600,400)));
		testItemList.add(new Planet(100,50,300,new Point(320,240),null ));
		testItemList.add(new Planet(100,50,150,new Point(500,240),null ));
		for (int i = 1; i <= 50; i++) {
			testItemList.add(new Xtwin(new Point.Double(random.nextInt(640),random.nextInt(480))
			,new Point.Double(random.nextInt(640),random.nextInt(480)),p));
			//testItemList.add(new Planet(100,50,i+10,new Point(random.nextInt(640),random.nextInt(480)),null ));
		}
		//testItemList.add(new DeathStar(new Point.Double(random.nextInt(640),random.nextInt(480))
		//,new Point.Double(random.nextInt(640),random.nextInt(480)),p));
		GalaxyItemManager manager = new GalaxyItemManager();
		/*
		 * Call the run method of Application providing an initial item Collection,
		 * an item manager and an ApplicationRunnable
		 */
		Application.run(testItemList, manager, new GameCore());
	}
}
