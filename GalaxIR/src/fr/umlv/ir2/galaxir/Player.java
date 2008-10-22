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
	private ArrayList<GalaxyItem> selectedItem = new ArrayList<GalaxyItem>();
	
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
	
	public void addSelectedItem(GalaxyItem gi) {
		selectedItem.add(gi);
	}
	
	public void removeSelectedItem(GalaxyItem gi) {
		selectedItem.remove(gi);
	}
	
	public boolean containsSelectedItem(GalaxyItem gi) {
		return selectedItem.contains(gi);
	}
	
	public void clearSelectedItem() {
		for(GalaxyItem gi: selectedItem) {
			gi.unselected(this);
		}
		selectedItem.clear();
	}
	
	public void launchShip(Planet p) {
		if(selectedItem.size()==0) {
			return;
		}
		if(selectedItem.contains(p)) {
			selectedItem.remove(p);
			p.unselected(this);
		}
		
		//ArrayList<Ship> escadron;
		for(GalaxyItem currentItem: selectedItem) {
			//escadron = currentPlanet.moveShipTowards(p, 50);
			currentItem.moveShipTowards(p, 50, galaxyItem);
			//for(Ship s: escadron) {
			//	this.galaxyItem.add(s);
			//}
		}
		this.clearSelectedItem();
	}
	
	public static Player getHumanPlayer() {
		return humanPlayer;
	}
	
	public static void setHumanPlayer(Player player) {
		Player.humanPlayer = player;
	}
}
