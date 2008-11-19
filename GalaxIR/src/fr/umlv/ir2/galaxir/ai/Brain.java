package fr.umlv.ir2.galaxir.ai;

import java.util.ArrayList;
import java.util.Iterator;

import fr.umlv.ir2.galaxir.core.AuthoritativeItemManager;
import fr.umlv.ir2.galaxir.core.Player;
import fr.umlv.ir2.galaxir.items.Planet;
import fr.umlv.ir2.galaxir.items.ship.Ship;
import fr.umlv.remix.TimerTask;


public class Brain {
	private AuthoritativeItemManager authoritativeItemManager;
	private Player player;

	public Brain(AuthoritativeItemManager authoritativeItemManager, Player p) {
		this.authoritativeItemManager = authoritativeItemManager;
		this.player = p;
	}

	public int score(double growth, double distance, int attack, int defense) {
		if(defense>attack)
			return 0;
		int attackRatio = getOptimalRatio(attack, defense);
		int distanceRatio = (int)Math.round(100-(distance+1)*100/1000);
		int score = attackRatio*distanceRatio*(int)growth;
		return score;
	}

	public int getOptimalRatio(int attack, int defense) {
		return (defense+1)*100/(attack+1);
	}

	public void run(TimerTask timerTask) {
		if(!authoritativeItemManager.gameInProgress()) {
			timerTask.cancel();
			return;
		}
		
		int score = -1;
		ArrayList<Planet> source = new ArrayList<Planet>();
		Planet destination = null;
		int ratio = 50;
		boolean noMorePlanet = true;
		
		Iterator<Planet> planetListIterator = authoritativeItemManager.planetIterator();
		while(planetListIterator.hasNext()) {
			Planet planet = planetListIterator.next();
			int currentScore = -1;
			Planet bestPlanet = null;
			Iterator<Planet> playerPlanetListIterator = authoritativeItemManager.planetIterator(player);
			while(playerPlanetListIterator.hasNext()) {
				noMorePlanet = false;
				Planet playerPlanet = playerPlanetListIterator.next();
				if(planet.getOwner()!=player) {
					currentScore += score(planet.getShipRepop(), playerPlanet.getLocation().distance(planet.getLocation()), playerPlanet.getNbShip(), planet.getNbShip());
					if(bestPlanet==null || bestPlanet.getNbShip()<playerPlanet.getNbShip()) {
						bestPlanet = playerPlanet;
					}
				}
			}
			if(currentScore>score) {
				score = currentScore;
				source.add(bestPlanet);
				destination = planet;
				ratio = getOptimalRatio(bestPlanet.getNbShip(), planet.getNbShip());
			}
		}
		if(noMorePlanet) {
			Iterator<Ship> shipListIterator = authoritativeItemManager.shipIterator(player);
			if(!shipListIterator.hasNext()) {
				timerTask.cancel();
				return;
			}
		}
		if(destination!=null && source.size()!=0 && score>0) {
			for(Planet planetSource: source) {
				planetSource.moveShipTowards(destination, ratio);
			}
		}
	}
}
