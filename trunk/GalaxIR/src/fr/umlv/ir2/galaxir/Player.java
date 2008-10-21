package fr.umlv.ir2.galaxir;
import java.awt.Color;
import java.util.ArrayList;

public class Player{
	private static int serialId;
	private final int playerId = serialId++;
	private final Color mainColor;
	private final Color auxColor;
	private final String name;
	private static Player humanPlayer;
	private ArrayList<GalaxyItem> galaxyItem;
	private ArrayList<Planet> selectedPlanetList = new ArrayList<Planet>();
	
	public Player(String name, Color mainColor, Color auxColor, ArrayList<GalaxyItem> galaxyItem) {
		this.name = name;
		this.mainColor = mainColor;
		this.auxColor = auxColor;
		this.galaxyItem = galaxyItem;
	}
	public Color getAuxColor() {
		return auxColor;
	}
	public Color getMainColor() {
		return mainColor;
	}
	public int getPlayerId(){
		return playerId;
	}
	
	public void addSelectedPlanet(Planet p) {
		selectedPlanetList.add(p);
	}
	
	public void removeSelectedPlanet(Planet p) {
		selectedPlanetList.remove(p);
	}
	
	public boolean containsSelectedPlanet(Planet p) {
		return selectedPlanetList.contains(p);
	}
	
	public void clearSelectedPlanet() {
		for(Planet p: selectedPlanetList) {
			p.unselected(this);
		}
		selectedPlanetList.clear();
	}
	
	public void launchShip(Planet p) {
		if(selectedPlanetList.size()==0) {
			return;
		}
		if(selectedPlanetList.contains(p)) {
			selectedPlanetList.remove(p);
			p.unselected(this);
		}
		
		//ArrayList<Ship> escadron;
		for(Planet currentPlanet: selectedPlanetList) {
			//escadron = currentPlanet.moveShipTowards(p, 50);
			currentPlanet.moveShipTowards(p, 50, galaxyItem);
			//for(Ship s: escadron) {
			//	this.galaxyItem.add(s);
			//}
		}
		this.clearSelectedPlanet();
	}
	
	public static Player getHumanPlayer() {
		return humanPlayer;
	}
	
	public static void setHumanPlayer(Player player) {
		Player.humanPlayer = player;
	}
}
