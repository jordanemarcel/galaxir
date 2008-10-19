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
	
	ArrayList<Planet> selectedPlanetList = new ArrayList<Planet>();
	
	public Player(String name, Color mainColor, Color auxColor) {
		this.name = name;
		this.mainColor = mainColor;
		this.auxColor = auxColor;
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
		if(selectedPlanetList.size()>0) {
			
		}
	}
	
	public static Player getHumanPlayer() {
		return humanPlayer;
	}
	
	public static void setHumanPlayer(Player player) {
		Player.humanPlayer = player;
	}
}
