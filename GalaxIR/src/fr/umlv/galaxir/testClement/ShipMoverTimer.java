package fr.umlv.galaxir.testClement;

import java.util.Collection;

import fr.umlv.remix.TimerRunnable;
import fr.umlv.remix.TimerTask;


public class ShipMoverTimer implements TimerRunnable {
	private final ShipMover ms;
	
	public ShipMoverTimer(Collection<? extends GalaxyItem> itemList) {
		this.ms = new ShipMover(itemList);
	}
	
	public void run(TimerTask timerTask) {
		ms.run();
    }
}