package fr.umlv.ir2.galaxir.ai;

import java.util.Iterator;
import java.util.Random;

import fr.umlv.ir2.galaxir.core.AuthoritativeItemManager;
import fr.umlv.ir2.galaxir.core.Player;
import fr.umlv.ir2.galaxir.items.Planet;
import fr.umlv.ir2.galaxir.items.ship.Ship;
import fr.umlv.remix.TimerTask;

public class Brain {
	private AuthoritativeItemManager authoritativeItemManager;
	private Player player;

	public Brain(AuthoritativeItemManager authoritativeItemManager, Player player) {
		this.authoritativeItemManager = authoritativeItemManager;
		this.player = player;
	}

	public int getOptimalRatio(int attack, int defense) {
		return (defense+1)*100/(attack+1);
	}

	public int guessDefense(Planet planet) {
		if(planet.getOwner()==null)
			return planet.getNbShip();
		Random rand = new Random();
		return rand.nextInt(100)+10;
	}

	public void run(TimerTask timerTask) {
		if(!this.authoritativeItemManager.gameInProgress()) {
			timerTask.cancel();
			return;
		}

		Planet source = null;
		Planet destination = null;
		int minRatio = 100;
		double minDistance = Double.MAX_VALUE;
		boolean noMorePlanet = true;

		Iterator<Planet> planetListIterator = this.authoritativeItemManager.planetIterator();
		while(planetListIterator.hasNext()) {
			Planet planet = planetListIterator.next();
			Iterator<Planet> playerPlanetListIterator = this.authoritativeItemManager.planetIterator(this.player);
			while(playerPlanetListIterator.hasNext()) {
				noMorePlanet = false;
				Planet playerPlanet = playerPlanetListIterator.next();
				if(planet.getOwner()!=this.player) {
					double distance = planet.getLocation().distance(playerPlanet.getLocation());
					if(distance<minDistance) {
						int defense = this.guessDefense(planet);
						int ratio = this.getOptimalRatio(playerPlanet.getNbShip(), defense);
						if(ratio<minRatio) {
							minDistance = distance;
							minRatio = ratio;
							source = playerPlanet;
							destination = planet;
						}
					}
				}
			}
		}
		if(noMorePlanet) {
			Iterator<Ship> shipListIterator = this.authoritativeItemManager.shipIterator(this.player);
			if(!shipListIterator.hasNext()) {
				timerTask.cancel();
				return;
			}
		}
		if(destination!=null) {
			source.moveShipTowards(destination, minRatio);
		}
	}
}
