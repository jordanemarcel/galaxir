package fr.umlv.galaxir.testJordane;


import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import fr.umlv.remix.Application;


public class RemixTest {

	public static void main(String[] args) {
		Random random = new Random();
		ArrayList<GalaxyItem> testItemList = new ArrayList<GalaxyItem>();
		/*
		 * Randomly position 25 TestItems in the Arena zone (defined afterwards)
		 */
		Ship.l = testItemList;
		try {
			testItemList.add(new Picture("/hme/jordane/pictures/linux/Linux_XP.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Player p = new Player("Clement",Color.red,Color.pink);
		
		//testItemList.add(new Xtwin(new Point(10,10),new Point(600,400)));
		testItemList.add(new Planet(100,50,300,new Point(320,240),null ));
		for (int i = 1; i <= 1000; i++) {
			testItemList.add(new Xtwin(new Point.Double(random.nextInt(640),random.nextInt(480))
			,new Point.Double(random.nextInt(640),random.nextInt(480)),p));
			//testItemList.add(new Planet(100,50,i*20,new Point(random.nextInt(640),random.nextInt(480)),null ));
		}
		testItemList.add(new DeathStar(new Point.Double(random.nextInt(640),random.nextInt(480))
		,new Point.Double(random.nextInt(640),random.nextInt(480)),p));
		GalaxyItemManager manager = new GalaxyItemManager();
		/*
		 * Call the run method of Application providing an initial item Collection,
		 * an item manager and an ApplicationRunnable
		 */
		Application.run(testItemList, manager, new GameCore());
	}
}
