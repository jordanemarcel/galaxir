package fr.umlv.ir2.galaxir;

import java.util.ArrayList;

public class Squadron {
	private Planet source;
	private Planet destination;
	private Player owner;
	private ArrayList<Ship> squadron = new ArrayList<Ship>();
	private boolean over = false;
	private boolean selected = false;

	public void changeDestination(Planet p) {
		destination = p;
		for(Ship s: squadron) {
			s.setDestination(p);
		}
	}
	
	public void setOver() {
			over = true;
	}

	public void setEndOver() {
			over = false;
	}
	
	public boolean isOver() {
		return over;
	}
	
	public void setSelected() {
		selected = true;
	}

	public void setUnselected() {
		selected = false;
	}

	public boolean isSelected() {
		return selected;
	}

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
	
	public ArrayList<Ship> getShipList() {
		return squadron;
	}

}
