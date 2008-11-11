package fr.umlv.ir2.galaxir.core;

import java.util.Collection;

import fr.umlv.ir2.galaxir.items.GalaxyItem;
import fr.umlv.remix.TimerRunnable;
import fr.umlv.remix.TimerTask;

public class ProductionTurnTimer implements TimerRunnable {
	private final ProductionTurn pt;
	
	
	public ProductionTurnTimer(Collection<? extends GalaxyItem> itemList) {
		this.pt = new ProductionTurn(itemList);
	}
	
	public void run(TimerTask timerTask) {
		pt.run(timerTask);
    }
}
