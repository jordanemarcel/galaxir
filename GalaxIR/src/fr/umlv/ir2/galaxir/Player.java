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
	private static Player ordiPlayer;
	private ArrayList<GalaxyItem> galaxyItem;
	private ArrayList<GalaxyItem> selectedItem = new ArrayList<GalaxyItem>();
	private int percentage = 50;
	
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
	
	
	
	public Player(String name, Color mainColor, Color auxColor, ArrayList<GalaxyItem> galaxyItem) {
		this.name = name;
		this.mainColor = mainColor;
		this.auxColor = auxColor;
		this.galaxyItem = galaxyItem;
	}
	
	public void addAnExplosion(Ship s) {
		if(s.getOwner()!=s.getDestination().getOwner()) {
			Explosion e = new Explosion((int)s.getLocation().getX(),(int)s.getLocation().getY());
			galaxyItem.add(e);
		}
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
		SoundEffect.playGogogo();
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
