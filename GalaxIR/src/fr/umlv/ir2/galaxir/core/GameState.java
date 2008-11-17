package fr.umlv.ir2.galaxir.core;

import java.util.Iterator;
import java.util.LinkedList;

import fr.umlv.ir2.galaxir.items.Planet;
import fr.umlv.ir2.galaxir.items.ship.Ship;
import fr.umlv.remix.TimerTask;

public class GameState {
	private final AuthoritativeItemManager authoritativeItemManager;
	private final LinkedList<Planet> planetList;

	public GameState(AuthoritativeItemManager authoritativeItemManager) {
		this.planetList = new LinkedList<Planet>();
		this.authoritativeItemManager = authoritativeItemManager;
		Iterator<Planet> planetIterator = authoritativeItemManager.planetIterator();
		while(planetIterator.hasNext()) {
			this.planetList.add(planetIterator.next());
		}
	}

	public void run(TimerTask timerTask) {
		Player currentPlayer = null;
		boolean winner = true;
		for(Planet planet: planetList) {
			Player player = planet.getOwner();
			if(player!=null) {
				if(currentPlayer==null)
					currentPlayer = player;
				if(currentPlayer!=player) {
					winner = false;
					break;
				}
			}
		}
		if(winner) {
			Iterator<Ship> shipIterator = authoritativeItemManager.shipIterator();
			while(shipIterator.hasNext()) {
				Ship ship = shipIterator.next();
				Player player = ship.getOwner();
				if(player!=currentPlayer) {
					return;
				}
			}
			System.out.println(currentPlayer+" is the winner!");
			timerTask.cancel();
		}
	}
}
