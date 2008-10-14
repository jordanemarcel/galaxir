package fr.umlv.galaxir.testJordane;
import java.util.Collection;

import fr.umlv.remix.Arena;
import fr.umlv.remix.TimerRunnable;
import fr.umlv.remix.TimerTask;


public class MainTimerThread implements TimerRunnable {
	final Arena<GalaxyItem> arena;
	private final ShipMover ms;
	
	public MainTimerThread(Arena<GalaxyItem> arena, Collection<? extends GalaxyItem> itemList) {
		this.arena = arena;
		this.ms = new ShipMover(itemList);
	}
	
	public void run(TimerTask timerTask) {
		ms.run();
        arena.refresh();
    }
}