package fr.umlv.galaxir.testClement;


import java.util.ArrayList;

public class Squadron {
	private Planet source;
	private Planet destination;
	private Player owner;
	private ArrayList<Ship> squadron = new ArrayList<Ship>();
	
	public Squadron(Planet source, Planet destination, Player owner) {
		this.source = source;
		this.destination = destination;
		this.owner = owner;
	}
	
	public void add(Ship s) {
		squadron.add(s);
	}
	
	public Planet getSourcePlanet() {
		return source;
	}
	
	public Planet getDestinationPlanet() {
		return destination;
	}
	
	public Player getOwner() {
		return owner;
	}

}
