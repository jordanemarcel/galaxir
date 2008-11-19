package fr.umlv.ir2.galaxir.core;

import java.util.Iterator;
import java.util.LinkedList;

import fr.umlv.ir2.galaxir.items.Planet;
import fr.umlv.remix.TimerTask;

public class ProductionTurn {
	private final LinkedList<Planet> planetList;
	private final AuthoritativeItemManager authoritativeItemManager;
	
	public ProductionTurn(AuthoritativeItemManager authoritativeItemManager) {
		this.authoritativeItemManager = authoritativeItemManager;
		this.planetList = new LinkedList<Planet>();
		Iterator<Planet> planetIterator = authoritativeItemManager.planetIterator();
		while(planetIterator.hasNext()) {
			this.planetList.add(planetIterator.next());
		}
	}
	
	public void run(TimerTask timerTask) {
		if(!authoritativeItemManager.gameInProgress()) {
			timerTask.cancel();
			return;
		}
		for(Planet planet: planetList) {
			planet.startProduction();
		}
	}
}
