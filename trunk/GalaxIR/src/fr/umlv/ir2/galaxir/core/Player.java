package fr.umlv.ir2.galaxir.core;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import fr.umlv.ir2.galaxir.items.ClickableItem;
import fr.umlv.ir2.galaxir.items.Explosion;
import fr.umlv.ir2.galaxir.items.Planet;
import fr.umlv.ir2.galaxir.items.ship.Ship;

public class Player{
	
	public enum PlayerType {
		HUMAN,COMPUTER;
	}
	
	private static int serialId;
	private final int playerId = serialId++;
	private final Color mainColor;
	private final String name;
	private final PlayerType playerType;
	private AuthoritativeItemManager authoritativeItemManager;
	private ArrayList<ClickableItem> selectedItem = new ArrayList<ClickableItem>();
	private ArrayList<Integer> scoreList = new ArrayList<Integer>();
	private int percentage = 50;
	private Planet overedPlanet;
	
	public Player(String name, Color mainColor, PlayerType playerType, AuthoritativeItemManager authoritativeItemManager) {
		this.name = name;
		this.mainColor = mainColor;
		this.playerType = playerType;
		this.authoritativeItemManager = authoritativeItemManager;
	}
	
	public PlayerType getPlayerType() {
		return playerType;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public Planet getOveredPlanet() {
		return overedPlanet;
	}
	
	public void setOveredPlanet(Planet planet) {
		overedPlanet = planet;
	}
	
	public ArrayList<Integer> getScore() {
		return scoreList;
	}
	
	public void setScore(int index) {
		int score = 0;
		Iterator<Planet> planetIterator = authoritativeItemManager.planetIterator(this);
		while(planetIterator.hasNext()) {
			score += planetIterator.next().getNbShip();
		}
		scoreList.add(index, score);
	}
	
	public void setPercentage(int percentage) {
		if(percentage>100)
			percentage = 100;
		if(percentage<5)
			percentage = 5;
		this.percentage = percentage;
	}
	
	public int getPercentage() {
		return percentage;
	}
	
	public AuthoritativeItemManager getAuthoritativeItemManager() {
		return authoritativeItemManager;
	}
	
	public void addAnExplosion(Ship s) {
		if(s.getOwner()!=s.getDestination().getOwner()) {
			Explosion e = new Explosion((int)s.getLocation().getX(),(int)s.getLocation().getY());
			authoritativeItemManager.addExplosion(e);
		}
	}
	
	public Color getMainColor() {
		return mainColor;
	}
	public int getPlayerId(){
		return playerId;
	}
	
	public void addSelectedItem(ClickableItem item) {
		selectedItem.add(item);
	}
	
	public void removeSelectedItem(ClickableItem item) {
		selectedItem.remove(item);
	}
	
	public boolean containsSelectedItem(ClickableItem item) {
		return selectedItem.contains(item);
	}
	
	public void clearSelectedItem() {
		for(ClickableItem item: selectedItem) {
			item.unselected(this);
		}
		selectedItem.clear();
	}
	
	public void launchShip(ClickableItem item) {
		if(selectedItem.size()==0) {
			return;
		}
		Planet planet = authoritativeItemManager.getPlanetFromClickableItem(item);
		if(planet==null)
			return;
		
		if(selectedItem.contains(planet)) {
			selectedItem.remove(planet);
			planet.unselected(this);
		}
		
		for(ClickableItem currentItem: selectedItem) {
			currentItem.moveShipTowards(planet, 50);
		}
		
		this.clearSelectedItem();
	}
}
