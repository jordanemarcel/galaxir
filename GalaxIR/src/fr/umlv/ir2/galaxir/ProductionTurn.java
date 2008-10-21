package fr.umlv.ir2.galaxir;

import java.util.Collection;
import java.util.LinkedList;

import fr.umlv.remix.TimerTask;

public class ProductionTurn implements Runnable {
	private Collection<? extends GalaxyItem> itemList;
	private LinkedList<Planet> planetList;
	
	public ProductionTurn(Collection<? extends GalaxyItem> itemList) {
		this.itemList = itemList;
		planetList = new LinkedList<Planet>();
		for (GalaxyItem item : itemList) {
			if(item instanceof Planet)
				planetList.add((Planet)item);
		}
	}
	
	
	@Override
	public void run() {
		for (Planet p : planetList){
			p.startProduction();
		}
	}
}
