package fr.umlv.ir2.galaxir.core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import fr.umlv.ir2.galaxir.items.Background;
import fr.umlv.ir2.galaxir.items.ClickableItem;
import fr.umlv.ir2.galaxir.items.EndOfGame;
import fr.umlv.ir2.galaxir.items.Explosion;
import fr.umlv.ir2.galaxir.items.GalaxyItem;
import fr.umlv.ir2.galaxir.items.Planet;
import fr.umlv.ir2.galaxir.items.StatusBar;
import fr.umlv.ir2.galaxir.items.ship.Ship;

public class AuthoritativeItemManager {
	private LinkedList<GalaxyItem> galaxyItem;
	private LinkedList<Planet> planetList = new LinkedList<Planet>();
	private LinkedList<Ship> shipList = new LinkedList<Ship>();
	private LinkedList<Explosion> explosionList = new LinkedList<Explosion>();
	private Background background;
	private boolean gameInProgress;

	public AuthoritativeItemManager(LinkedList<GalaxyItem> galaxyItem) {
		this.galaxyItem = galaxyItem;
		this.gameInProgress = true;
	}
	
	public boolean gameInProgress() {
		return gameInProgress;
	}

	public void addPlanet(Planet planet) {
		planetList.add(planet);
		galaxyItem.add(planet);
	}

	public void addShip(Ship ship) {
		shipList.add(ship);
		galaxyItem.add(ship);
	}

	public void removePlanet(Planet planet) {
		planetList.remove(planet);
		galaxyItem.remove(planet);
	}

	public void removeShip(Ship ship) {
		shipList.remove(ship);
		galaxyItem.remove(ship);
	}
	
	public void removeExplosion(Explosion explosion) {
		galaxyItem.remove(explosion);
		explosionList.remove(explosion);
	}

	public void addBackground(Background background) {
		this.background = background;
		galaxyItem.add(0, background);
	}

	public void addStatusBar(StatusBar statusBar) {
		galaxyItem.add(statusBar);
	}

	public void addExplosion(Explosion explosion) {
		galaxyItem.add(explosion);
		explosionList.add(explosion);
	}

	public Planet getPlanetFromClickableItem(ClickableItem item) {
		for(Planet planet: planetList) {
			if(planet.equals(item)) {
				return planet;
			}
		}
		return null;
	}
	
	public Iterator<Planet> planetIterator() {
		//GalaxyItemIterator<Planet> iterator = new GalaxyItemIterator<Planet>(planetList);
		return planetList.iterator();
	}

	public Iterator<Explosion> explosionIterator() {
		//GalaxyItemIterator<Planet> iterator = new GalaxyItemIterator<Planet>(planetList);
		return explosionList.iterator();
	}
	
	public Iterator<Planet> planetIterator(Player player) {
		LinkedList<Planet> shipItemList = new LinkedList<Planet>();
		for(Planet planet: planetList) {
			if(planet.getOwner()==player) {
				shipItemList.add(planet);
			}
		}
		return shipItemList.iterator();
	}

	public Iterator<Planet> planetIterator(List<GalaxyItem> list) {
		LinkedList<Planet> planetItemList = new LinkedList<Planet>();
		for(GalaxyItem galaxyItem: list) {
			for(Planet planet: planetList) {
				if(planet.equals(galaxyItem)) {
					planetItemList.add(planet);
					break;
				}
			}
		}
		return planetItemList.iterator();
	}

	public Iterator<Ship> shipIterator() {
		//GalaxyItemIterator<Ship> iterator = new GalaxyItemIterator<Ship>(shipList);
		return shipList.iterator();
	}

	public Iterator<Ship> shipIterator(Player player) {
		LinkedList<Ship> shipItemList = new LinkedList<Ship>();
		for(Ship ship: shipList) {
			if(ship.getOwner()==player) {
				shipItemList.add(ship);
			}
		}
		return shipItemList.iterator();
	}

	public Iterator<Ship> shipIterator(List<GalaxyItem> list) {
		LinkedList<Ship> shipItemList = new LinkedList<Ship>();
		for(GalaxyItem galaxyItem: list) {
			for(Ship ship: shipList) {
				if(ship.equals(galaxyItem)) {
					shipItemList.add(ship);
					break;
				}
			}
		}
		return shipItemList.iterator();
	}

	public Iterator<ClickableItem> clickableItemIterator(List<GalaxyItem> mouseList) {
		LinkedList<ClickableItem> clickableItemList = new LinkedList<ClickableItem>();
		for(GalaxyItem galaxyItem: mouseList) {
			for(Planet planet: planetList) {
				if(planet.equals(galaxyItem)) {
					clickableItemList.add(planet);
					break;
				} else {
					for(Ship ship: shipList) {
						if(ship.equals(galaxyItem)) {
							clickableItemList.add(ship);
							break;
						}
					}
				}
			}
		}
		return clickableItemList.iterator();
	}
	
	public void endGame(Player player) {
		gameInProgress = false;
		galaxyItem.clear();
		planetList.clear();
		shipList.clear();
		explosionList.clear();
		galaxyItem.add(background);
		galaxyItem.add(new EndOfGame(player, background.getWidth(), background.getHeight()));
	}
	
	public void newGame() {
		gameInProgress = true;
		galaxyItem.clear();
		planetList.clear();
		shipList.clear();
		explosionList.clear();
		galaxyItem.add(background);
	}
}
