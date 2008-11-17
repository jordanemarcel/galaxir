package fr.umlv.ir2.galaxir.items;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import fr.umlv.ir2.galaxir.core.AuthoritativeItemManager;
import fr.umlv.ir2.galaxir.core.Player;
import fr.umlv.ir2.galaxir.core.Player.PlayerType;
import fr.umlv.ir2.galaxir.items.ship.Squadron;
import fr.umlv.ir2.galaxir.items.ship.SquadronUnleasher;
import fr.umlv.ir2.galaxir.items.ship.SquadronUnleasherTimer;
import fr.umlv.ir2.galaxir.items.ship.ShipFactory.ShipType;
import fr.umlv.remix.Application;


public class Planet implements ClickableItem {
	private double nbShip;
	private Player owner;
	private final double shipRepop;
	private final int width;
	private final Point2D location;
	private boolean over = false;
	private ShipType currentShipType;
	private boolean selected;
	private int selectedArc = 0;
	private ArrayList<SquadronUnleasher> squadronList = new ArrayList<SquadronUnleasher>();
	
	public Planet(int nbShip, int shipRepop, int width, Point2D location, Player owner) {
		this.nbShip = nbShip;
		this.shipRepop = shipRepop;
		this.width = width;
		this.location = location;
		this.owner = owner;
		this.currentShipType = ShipType.XTWIN;
	}
	
	public void startProduction() {
		if(owner!=null)
			this.nbShip += shipRepop/60;
	}
	
	public ShipType getCurrentShipType() {
		return currentShipType;
	}
	
	public void nextCurrentShipType() {
		ShipType[] shipType = ShipType.values();
		for(int i=0;i<shipType.length;i++) {
			if(currentShipType.equals(shipType[i])) {
				i++;
				if(i==shipType.length)
					i = 0;
				currentShipType = shipType[i];
				break;
			}
		}
	}
	
	public void previousCurrentShipType() {
		ShipType[] shipType = ShipType.values();
		for(int i=0;i<shipType.length;i++) {
			if(currentShipType.equals(shipType[i])) {
				i--;
				if(i<0)
					i = shipType.length-1;
				currentShipType = shipType[i];
				break;
			}
		}
	}
	
	
	
	public void setOver() {
		if(this.getOwner()!=null) {
			if(this.getOwner().getPlayerType()==PlayerType.HUMAN) {
				if(!over) {
					over = true;
				}
			}
		}
	}
	
	public void setEndOver() {
		over = false;
	}
	
	public void setNbShip(int nb) {
		nbShip = nb;
	}
	
	public void setNbShipDouble(double nb) {
		nbShip = nb;
	}
	
	public int getNbShip() {
		return (int)Math.floor(nbShip);
	}
	
	public double getNbShipDouble() {
		return nbShip;
	}
	
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	public Player getOwner() {
		return this.owner;
	}
	
	public int getRadius() {
		return width/2;
	}

	public double getShipRepop() {
		return shipRepop;
	}
	public int getWidth() {
		return width;
	}
	public Point2D getLocation() {
		return location;
	}
    
	public boolean contains(Point2D p){
		return location.distance(p)<=(getWidth()/2);
	}
	
	public boolean intersectPlanet(Planet p){
		double distance = location.distance(p.getLocation());
		if(distance < (p.getRadius()+getRadius()))
			return true;
		return false;
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.lightGray);
		g.fillOval((int)location.getX()-(width+4)/2, (int)location.getY()-(width+4)/2, width+4, width+4);
		if(over){
			selectedArc++;
			if(selectedArc>360)
				selectedArc = 0;
			g.setColor(Color.white);
			g.fillArc((int)location.getX()-(width+8)/2, (int)location.getY()-(width+8)/2, width+8, width+8, 0+selectedArc, 90);
			g.fillArc((int)location.getX()-(width+8)/2, (int)location.getY()-(width+8)/2, width+8, width+8, 180+selectedArc, 90);
		}
		if(owner!=null) {
			if(!selected)
				g.setColor(owner.getMainColor());
			else
				g.setColor(owner.getAuxColor());
		} else
			g.setColor(Color.DARK_GRAY);
		g.fillOval((int)location.getX()-width/2, (int)location.getY()-width/2, width, width);
		g.setColor(Color.white);
		String s = new String(""+this.getNbShip());
		g.drawString(s, (int)location.getX()-5, (int)location.getY()+5);
		
		int total = 0;
		for( int i = 0; i<squadronList.size(); i++ ) {
			SquadronUnleasher su = squadronList.get(i);
			int shipLeft = su.getNumberOfShip();
			if(shipLeft==0) {
				squadronList.remove(i);
			}
			total += shipLeft;
		}
		s = new String("+"+total);
		if(total>0)
			g.drawString(s, (int)location.getX()-5, (int)location.getY()+15);
	}
	
	public void selectAndAdd(Player player) {
		if(player==this.getOwner()) {
			selected = true;
			player.addSelectedItem(this);
		}
	}
	
	public void unselectAndRemove(Player player) {
		if(player==this.getOwner()) {
			selected = false;
			player.removeSelectedItem(this);
		}
	}
	
	@Override
	public void selected(Player player) {
		selected = true;
	}
	
	public void unselected(Player player) {
		selected = false;
	}
	
	public boolean intersects(Point2D location, int width) {
		double distance = this.getLocation().distance(location);
		return (distance<(this.getRadius()+width/2));
	}
	
	public boolean callReinforcement() {
		if(squadronList.size()==0)
			return false;
		SquadronUnleasher su = squadronList.get(0);
		this.nbShip += su.getNumberOfShip();
		su.setNumberOfShip(0);
		return true;
	}
	
	public void moveShipTowards(Planet p, int percentage) {
		
		int number = (int)Math.floor(nbShip * owner.getPercentage() / 100);
		nbShip -= number;
		
		long timer = (long)(currentShipType.getSize()/currentShipType.getSpeed()*40);
		
		Squadron squadron = new Squadron(this, p, owner);
		SquadronUnleasher squadronUnleasher = new SquadronUnleasher(number,currentShipType, squadron);
		squadronList.add(squadronUnleasher);
		
		Application.timer(timer, new SquadronUnleasherTimer(squadronUnleasher));
	}
	
}
