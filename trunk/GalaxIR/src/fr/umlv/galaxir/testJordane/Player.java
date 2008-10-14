package fr.umlv.galaxir.testJordane;
import java.awt.Color;

public class Player{
	private static int serialId;
	private final int playerId = serialId++;
	private final Color mainColor;
	private final Color auxColor;
	private final String name;
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
}
