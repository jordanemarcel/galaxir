package fr.umlv.ir2.galaxir.items.ship;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.Random;

import fr.umlv.ir2.galaxir.core.Player;
import fr.umlv.ir2.galaxir.core.Player.PlayerType;
import fr.umlv.ir2.galaxir.items.ClickableItem;
import fr.umlv.ir2.galaxir.items.GalaxyItem;
import fr.umlv.ir2.galaxir.items.Planet;
import fr.umlv.ir2.galaxir.utils.Trigonometry;

public abstract class Ship implements ClickableItem {
	private final int attack;
	private final int speed;
	private final int size;
	private final Player owner;
	private Point2D.Double location;
	private Planet destinationPlanet;
	private double angleWay = 0;
	private double angleWayTime = 0;
	private double rotation = 0;
	private GalaxyItem previous = null;
	protected Squadron squadron;
	protected boolean over = false;
	protected boolean selected = false;
	private boolean toBeDeleted = false;
	
	public void setOver() {
		if(this.getOwner()!=null) {
			if(this.getOwner().getPlayerType()==PlayerType.HUMAN) {
				squadron.setOver();
				for(Ship s: squadron.getShipList())
					s.over = true;
			}
		}
	}
	
	public void setEndOver() {
		squadron.setEndOver();
		for(Ship s: squadron.getShipList())
			s.over = false;
	}
	
	public int getRadius() {
		return getSize()/2;
	}
	
	public Ship(int attack, int speed,int size, 
			Point2D.Double location, Planet destination,Player owner) {
		this.attack = attack;
		this.speed = speed;
		this.location = location;
		this.size = size;
		this.destinationPlanet = destination;
		this.owner = owner;
	}
	
	public Planet getDestination() {
		return destinationPlanet;
	}
	
	public void setDestination(Planet p) {
		destinationPlanet = p;
	}
	
	public void setSquadron(Squadron squadron) {
		this.squadron = squadron;
		if(squadron.isOver())
			this.over = true;
	}
	
	public double getRotation() {
		return this.rotation;
	}
	
	public int getNextLocation(){
		return 0;
	}
	public int getAttack() {
		return attack;
	}
	
	public boolean toBeDeleted() {
		return this.toBeDeleted;
	}
	public int getSpeed() {
		return speed;
	}
	public int getSize() {
		return size;
	}
	public Player getOwner() {
		return owner;
	}
	public Point2D getLocation() {
		return location;
	}
	
	public abstract boolean intersects(Planet p);

	public void setLocation(Point2D.Double location){
		this.location = location;
	}

	public void move(Iterator<Planet> planetIterator){
		Point2D.Double destination = new Point2D.Double(destinationPlanet.getLocation().getX(),destinationPlanet.getLocation().getY());
		double distance = location.distance(destination);
		if(distance < speed)
			location=destination;
		if (location.equals(destination))
			return;
		
		double distanceX = destination.getX() - location.getX();
		double distanceY = destination.getY() - location.getY();

		double moveX = distanceX*speed/distance;
		double moveY = distanceY*speed/distance;
		
		double angle = 0;
		double farAngle = 0;

		double nextX = location.getX() + moveX;
		double nextY = location.getY() + moveY;

		double centerX = location.getX();
		double centerY = location.getY();
		
		double rotateX = location.getX()*100;
		double rotateY = location.getY()*100;
		this.setLocation(new Point.Double(nextX, nextY));
		Point2D.Double op2D = null;
		Point2D np2D = null;
		boolean collision = true;
		while(collision) {
			collision = false;
			while(planetIterator.hasNext()) {
				Planet planet = planetIterator.next();
				while(this.intersects(planet)) {
					if(planet==destinationPlanet) {
						this.attack(planet);
						return;
					}
					collision = true;
					farAngle += 10;
					
					if(angleWayTime==0) {
						if(angle<0)
							angle = farAngle;
						else
							angle = -farAngle;
					} else if(angleWayTime>1) {
						angle = 10 * angleWay;
					} else if(angleWayTime==1 && previous!=planet) {
						Random r = new Random();
						boolean randomDirection = r.nextBoolean();
						if(randomDirection)
							angleWay = -1;
						else
							angleWay = 1;
						angle = 10 * angleWay;
						angleWayTime = 3;
					} else {
						if(angle<0)
							angle = farAngle;
						else
							angle = -farAngle;
					}
					
					previous = planet;
					
					if(farAngle>=380) {
						collision = false;
						nextX = 0;
						nextY = 0;
						break;
					}
					op2D = new Point2D.Double(nextX*100, nextY*100);
					AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angle), rotateX, rotateY);
					np2D = at.transform(op2D, null);
					
					nextX = np2D.getX()/100;
					nextY = np2D.getY()/100;

					this.setLocation(new Point.Double(nextX, nextY));
				}
				if(angle<0) {
					angleWay = -1;
				} else if(angle>0) {
					angleWay = 1;
				}
				if(collision) {
					angleWayTime = 3;
				}
				angle = 0;
				farAngle = 0;
			}
		}
		angleWayTime--;
		if(angleWayTime<=0) {
			angleWayTime = 0;
			angleWay = 0;
		}
		setLocation(new Point.Double(nextX, nextY));
		Point2D.Double top = Trigonometry.findUpperPoint(new Point2D.Double(centerX, centerY));
		this.rotation = Trigonometry.findAngle(new Point2D.Double(centerX, centerY), top, new Point.Double(nextX, nextY));
		if(nextX<centerX)
			this.rotation = -this.rotation;
	}
	
	public void selectAndAdd(Player player) {
		if(player==this.getOwner()) {
			squadron.setSelected();
			player.addSelectedItem(this);
		}
	}
	
	public void unselectAndRemove(Player player) {
		if(player==this.getOwner()) {
			squadron.setUnselected();
			player.removeSelectedItem(this);
		}
	}
	
	public void unselected(Player player) {
		squadron.setUnselected();
	}
	
	public void selected(Player player) {
		squadron.setSelected();
	}
	
	public void moveShipTowards(Planet p) {
		squadron.changeDestination(p);
	}
	
	@Override
	public void moveShipTowards(Planet p, int percentage) {
		moveShipTowards(p);
	}
	
	public void attack(Planet p) {
		int lastShip;
		if(this.getOwner()==p.getOwner())
			lastShip = p.getNbShip() + this.getAttack();
		else {
			lastShip = p.getNbShip() - this.getAttack();
		}
		
		if(lastShip<=0) {
			if(!p.callReinforcement()) {
				lastShip = 0;
				p.setOwner(this.getOwner());
			} else {
				lastShip = p.getNbShip();
			}
		}
		p.setNbShip(lastShip);
		this.toBeDeleted = true;
	}

}