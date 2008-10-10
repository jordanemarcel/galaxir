package fr.umlv.galaxir.testJordane;

import fr.umlv.remix.Arena;
import fr.umlv.remix.TimerRunnable;
import fr.umlv.remix.TimerTask;

public class MyTimerRunnable implements TimerRunnable {
	final Arena<TestItem> arg0;
	
	public MyTimerRunnable(Arena<TestItem> arg0) {
		this.arg0 = arg0;
	}
	
	public void run(TimerTask timerTask) {
        arg0.refresh();
    }
}
