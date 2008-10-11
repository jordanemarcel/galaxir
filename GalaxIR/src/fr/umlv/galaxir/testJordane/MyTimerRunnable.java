package fr.umlv.galaxir.testJordane;

import java.util.Collection;

import fr.umlv.remix.Arena;
import fr.umlv.remix.TimerRunnable;
import fr.umlv.remix.TimerTask;

public class MyTimerRunnable implements TimerRunnable {
	final Arena<Drawable> arg0;
	private final ShipMover ms;
	
	public MyTimerRunnable(Arena<Drawable> arg0, Collection<? extends Drawable> arg1) {
		this.arg0 = arg0;
		this.ms = new ShipMover(arg1.iterator());
	}
	
	public void run(TimerTask timerTask) {
		ms.run();
        arg0.refresh();
    }
}
