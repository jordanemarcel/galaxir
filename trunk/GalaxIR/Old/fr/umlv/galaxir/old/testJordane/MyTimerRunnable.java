package fr.umlv.galaxir.old.testJordane;

import java.util.Collection;

import fr.umlv.galaxir.testJordane.ShipMover;
import fr.umlv.remix.Arena;
import fr.umlv.remix.TimerRunnable;
import fr.umlv.remix.TimerTask;

public class MyTimerRunnable implements TimerRunnable {
	final Arena<Drawable> arg0;
	private final ShipMover sm = null;
	
	public MyTimerRunnable(Arena<Drawable> arg0, Collection<? extends Drawable> arg1) {
		this.arg0 = arg0;
		//this.sm = new ShipMover(arg1.iterator());
	}
	
	public void run(TimerTask timerTask) {
		sm.run();
        arg0.refresh();
    }
}
