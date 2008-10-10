package fr.umlv.galaxir.testJordane;

import java.util.ArrayList;
import java.util.Random;

import fr.umlv.remix.*;

public class Main {

  public static void main(String[] args) {
    Random random = new Random();
    ArrayList<TestItem> testItemList = new ArrayList<TestItem>();
    /*
     * Randomly position 25 TestItems in the Arena zone (defined afterwards)
     */
    for (int i = 0; i < 25; i++) {
      testItemList.add(new TestItem(random.nextInt(300), random.nextInt(200),
          10));
    }

    TestManager manager = new TestManager();

    /*
     * Call the run method of Application providing an initial item Collection,
     * an item manager and an ApplicationRunnable
     */
    Application.run(testItemList, manager, new MyApplicationRunnable());
  }
}
