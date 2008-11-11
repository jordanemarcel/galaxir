package fr.umlv.ir2.galaxir.core;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import fr.umlv.ir2.galaxir.items.Background;
import fr.umlv.ir2.galaxir.items.ClickableItem;
import fr.umlv.ir2.galaxir.items.Explosion;
import fr.umlv.ir2.galaxir.items.GalaxyItem;
import fr.umlv.ir2.galaxir.items.Planet;
import fr.umlv.ir2.galaxir.items.StatusBar;
import fr.umlv.ir2.galaxir.items.ship.Ship;

public class AuthoritativeItemManager {
	private ArrayList<GalaxyItem> galaxyItem;
	private ArrayList<Planet> planetList = new ArrayList<Planet>();
	private ArrayList<Ship> shipList = new ArrayList<Ship>();
	private Background background;
	
	public AuthoritativeItemManager(ArrayList<GalaxyItem> galaxyItem) {
		this.galaxyItem = galaxyItem;
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
	
	public void addBackground(Background background) {
		this.background = background;
		galaxyItem.add(0, background);
	}
	
	public void setBackground(Color color) {
		this.background.setColor(color);
	}
	
	public void addStatusBar(StatusBar statusBar) {
		galaxyItem.add(statusBar);
	}
	
	public void addExplosion(Explosion explosion) {
		galaxyItem.add(explosion);
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
	
	public Iterator<Ship> shipIterator() {
		//GalaxyItemIterator<Ship> iterator = new GalaxyItemIterator<Ship>(shipList);
		return shipList.iterator();
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
}
