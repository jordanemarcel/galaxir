package fr.umlv.galaxir.testJordane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import fr.umlv.remix.Application;

public class Main {

	public static void main(String[] args) throws IOException {
		Random random = new Random();
		final ArrayList<Drawable> itemList = new ArrayList<Drawable>();
		/*
		 * Randomly position 25 TestItems in the Arena zone (defined afterwards)
		 */
		for (int i = 0; i < 3; i++) {
			itemList.add(new TestItem(random.nextInt(640), random.nextInt(480),
					100));
		}
		itemList.add(new TriangleItem(random.nextInt(640), random.nextInt(480),
				30));


		TestManager manager = new TestManager();

		/*
		 * Call the run method of Application providing an initial item Collection,
		 * an item manager and an ApplicationRunnable
		 */
		Application.run(itemList, manager, new MyApplicationRunnable());
		//Runnable runnable = new MovingShip((TriangleItem)testItemList.get(3));
		//new Thread(runnable).start();

	}
}
