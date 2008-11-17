package fr.umlv.ir2.galaxir.core;

import java.util.Iterator;
import java.util.LinkedList;

import fr.umlv.ir2.galaxir.items.Planet;
import fr.umlv.remix.TimerTask;

public class ProductionTurn {
	private final LinkedList<Planet> planetList;
	
	public ProductionTurn(AuthoritativeItemManager authoritativeItemManager) {
		this.planetList = new LinkedList<Planet>();
		Iterator<Planet> planetIterator = authoritativeItemManager.planetIterator();
		while(planetIterator.hasNext()) {
			this.planetList.add(planetIterator.next());
		}
	}
	
	public void run(TimerTask timerTask) {
		for(Planet planet: planetList) {
			planet.startProduction();
		}
	}
}
