package fr.umlv.ir2.galaxir.core;

import fr.umlv.remix.TimerRunnable;
import fr.umlv.remix.TimerTask;

public class ProductionTurnTimer implements TimerRunnable {
	private final ProductionTurn pt;
	
	
	public ProductionTurnTimer(AuthoritativeItemManager authoritativeItemManager) {
		this.pt = new ProductionTurn(authoritativeItemManager);
	}
	
	public void run(TimerTask timerTask) {
		pt.run(timerTask);
    }
}
