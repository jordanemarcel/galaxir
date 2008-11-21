package fr.umlv.ir2.galaxir.core;

import java.util.ArrayList;
import java.util.Iterator;

import fr.umlv.ir2.galaxir.items.Background;
import fr.umlv.ir2.galaxir.items.ClickableItem;
import fr.umlv.ir2.galaxir.items.EndOfGame;
import fr.umlv.ir2.galaxir.items.Explosion;
import fr.umlv.ir2.galaxir.items.GalaxyItem;
import fr.umlv.ir2.galaxir.items.Planet;
import fr.umlv.ir2.galaxir.items.StatusBar;
import fr.umlv.ir2.galaxir.items.ship.Ship;

public class AuthoritativeItemManager {
	private ArrayList<GalaxyItem> galaxyItem;
	private ArrayList<Planet> planetList = new ArrayList<Planet>();
	private ArrayList<Ship> shipList = new ArrayList<Ship>();
	private ArrayList<Explosion> explosionList = new ArrayList<Explosion>();
	private Background background;
	private boolean gameInProgress;

	public AuthoritativeItemManager(ArrayList<GalaxyItem> galaxyItem) {
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
		ArrayList<Planet> shipItemList = new ArrayList<Planet>();
		for(Planet planet: planetList) {
			if(planet.getOwner()==player) {
				shipItemList.add(planet);
			}
		}
		return shipItemList.iterator();
	}

	public Iterator<Planet> planetIterator(ArrayList<GalaxyItem> list) {
		ArrayList<Planet> planetItemList = new ArrayList<Planet>();
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
		ArrayList<Ship> shipItemList = new ArrayList<Ship>();
		for(Ship ship: shipList) {
			if(ship.getOwner()==player) {
				shipItemList.add(ship);
			}
		}
		return shipItemList.iterator();
	}

	public Iterator<Ship> shipIterator(ArrayList<GalaxyItem> list) {
		ArrayList<Ship> shipItemList = new ArrayList<Ship>();
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

	public Iterator<ClickableItem> clickableItemIterator(ArrayList<GalaxyItem> mouseList) {
		ArrayList<ClickableItem> clickableItemList = new ArrayList<ClickableItem>();
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
