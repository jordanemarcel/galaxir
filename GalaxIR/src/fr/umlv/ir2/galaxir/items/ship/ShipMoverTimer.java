package fr.umlv.ir2.galaxir.items.ship;

import fr.umlv.ir2.galaxir.core.AuthoritativeItemManager;
import fr.umlv.remix.TimerRunnable;
import fr.umlv.remix.TimerTask;


public class ShipMoverTimer implements TimerRunnable {
	private final ShipMover ms;
	
	public ShipMoverTimer(AuthoritativeItemManager authoritativeItemManager) {
		this.ms = new ShipMover(authoritativeItemManager);
	}
	
	public void run(TimerTask timerTask) {
		ms.run(timerTask);
    }
}