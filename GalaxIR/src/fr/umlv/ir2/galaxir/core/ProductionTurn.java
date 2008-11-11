package fr.umlv.ir2.galaxir.core;

import java.util.Collection;
import java.util.LinkedList;

import fr.umlv.ir2.galaxir.items.GalaxyItem;
import fr.umlv.ir2.galaxir.items.Planet;
import fr.umlv.remix.TimerTask;

public class ProductionTurn {
	private LinkedList<Planet> planetList;
	
	public ProductionTurn(Collection<? extends GalaxyItem> itemList) {
		planetList = new LinkedList<Planet>();
		for (GalaxyItem item : itemList) {
			if(item instanceof Planet)
				planetList.add((Planet)item);
		}
	}
	
	public void run(TimerTask timerTask) {
		for (Planet p : planetList){
			p.startProduction();
		}
	}
}
