package fr.umlv.ir2.galaxir;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import fr.umlv.remix.Application;
public abstract class Ship implements GalaxyItem{
	private final int attack;
	private final int speed;
	private final int cost;
	private final int size;
	private final Player owner;
	private Point2D.Double location;
	private Planet destinationPlanet;
	private final Color[] colors;
	private int currentColor;
	private double angleWay = 0;
	private double angleWayTime = 0;
	private double rotation = 0;
	private GalaxyItem previous = null;
	private boolean toBeDeleted = false;
	protected Squadron squadron;
	protected boolean over = false;
	protected boolean selected = false;
	
	public void setOver() {
		squadron.setOver();
		for(Ship s: squadron.getShipList())
			s.over = true;
	}
	
	public void setEndOver() {
		squadron.setEndOver();
		for(Ship s: squadron.getShipList())
			s.over = false;
	}
	
	//private final LinkedList<Point2D> trajectory;
	public Ship(int attack, int speed, int cost,int size, 
			Point2D.Double location, Planet destination,Player owner) {
		this.attack = attack;
		this.speed = speed;
		this.cost = cost; 
		this.location = location;
		this.size = size;
		this.destinationPlanet = destination;

		/*while(this.destination.distance(320, 240)<150) {
			this.destination = new Point.Double(r.nextInt(640), r.nextInt(320));
		}*/
		this.owner = owner;
		colors = new Color[2];
		colors[0] =owner.getMainColor();
		colors[1] = owner.getAuxColor();
		//trajectory = new LinkedList<Point2D>();
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
	public int getCost() {
		return cost;
	}
	
	public void delete() {
		this.toBeDeleted = true;
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
	//public void setDestination(Point2D destination){
		//this.destination = destination;
	//}
	public void move(LinkedList<Planet> planetList){
		Point2D.Double destination = new Point2D.Double(destinationPlanet.getLocation().getX(),destinationPlanet.getLocation().getY());
		if(location.distance(destination) < speed)
			location=destination;
		if (location.equals(destination))
			return;
		//System.out.println("-------------------");
		double distance = location.distance(destination);
		//System.out.println("Distance="+distance);
		double distanceX = destination.getX() - location.getX();
		double distanceY = destination.getY() - location.getY();
		//System.out.println("DisX="+distanceX+" DisY="+distanceY);
		double moveX = distanceX*speed/distance;
		double moveY = distanceY*speed/distance;
		//System.out.println("MovX="+moveX+" MovY="+moveY);
		
		double angle = 0;
		double farAngle = 0;
		/*
		int intMoveX;
		int intMoveY;
		bufferX += moveX;
		bufferY += moveY;
		if(bufferX<0)
			intMoveX = (int)Math.ceil(bufferX);
		else
			intMoveX = (int)Math.floor(bufferX);
		if(bufferY<0)
			intMoveY = (int)Math.ceil(bufferY);
		else
			intMoveY = (int)Math.floor(bufferY);
		bufferX -= (double)intMoveX;
		bufferY -= (double)intMoveY;
		*/
		//System.out.println("Old: "+location.getX()+","+location.getY());
		//System.out.println("MovX="+moveX+" MovY="+moveY);
		double nextX = location.getX() + moveX;
		double nextY = location.getY() + moveY;
		//System.out.println("New: "+nextX+","+nextY);
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
			for(Planet p: planetList) {
				//System.out.println("One"+p);
				//while(p.contains(new Point((int)nextX, (int)nextY))) {
				while(this.intersects(p)) {
					if(p==destinationPlanet) {
						this.attack(p);
						return;
					}
					//System.out.println(bug++);
					//System.out.println("Old: "+location.getX()+","+location.getY());
					//System.out.println("New: "+nextX+","+nextY);
					//System.out.println("Collision!");
					collision = true;
					farAngle += 10;
					
					
					if(angleWayTime==0) {
						//System.out.println(farAngle);
						if(angle<0)
							angle = farAngle;
						else
							angle = -farAngle;
					} else if(angleWayTime>1) {
						angle = 10 * angleWay;
					} else if(angleWayTime==1 && previous!=p) {
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
					
					previous = p;
					
					if(farAngle>=380) {
						collision = false;
						nextX = 0;
						nextY = 0;
						break;
					}
					op2D = new Point2D.Double(nextX*100, nextY*100);
					AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angle), rotateX, rotateY);
					np2D = at.transform(op2D, null);
					//System.out.println("Centre x="+rotateX+", centre y="+rotateY);
					//System.out.println("X="+nextX+", Y="+nextY);
					//at.getTranslateX();
					//at.getTranslateY();
					//newPoint = this.rotate(nextX, nextY, location.getX(), location.getY(), angle);
					//System.out.println(location.getX()*100+","+location.getY()*100);
					//System.out.println(goAroundWay);
					//System.out.println(nextX+","+nextY);
					nextX = np2D.getX()/100;
					nextY = np2D.getY()/100;
					//System.out.println(nextX+","+nextY);
					//l.add(new Cordon(new Point.Double(rotateX/100, rotateY/100), new Point.Double(nextX, nextY)));
					//System.out.println(angle);
					//l.add(new Cordon(new Point.Double(320, 240), new Point.Double(nextX, nextY)));
					this.setLocation(new Point.Double(nextX, nextY));
				}
				if(angle<0) {
					angleWay = -1;
				} else if(angle>0) {
					angleWay = 1;
				}
				//System.out.println("AngleWay: "+angleWay);
				if(collision) {
					angleWayTime = 3;
				}
				//System.out.println("OK");
				//System.out.println(farAngle*goAroundWay);
				angle = 0;
				farAngle = 0;
				//System.out.println("NEXT");
			}
		}
		angleWayTime--;
		if(angleWayTime<=0) {
			angleWayTime = 0;
			angleWay = 0;
		}
		//System.out.println("FINISH");
		//System.out.println(nextX+","+nextY);
		//System.out.println("----------");
		//l.add(new Cordon(new Point.Double(location.getX(), location.getY()), new Point.Double(nextX, nextY)));
		setLocation(new Point.Double(nextX, nextY));
		Point2D.Double top = Trigo.findUpperPoint(new Point2D.Double(centerX, centerY));
		this.rotation = Trigo.computeAngle(new Point2D.Double(centerX, centerY), top, new Point.Double(nextX, nextY));
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
		System.out.println("unselect!!!");
		squadron.setUnselected();
	}
	
	public void selected(Player player) {
		squadron.setSelected();
	}
	
	public void moveShipTowards(Planet p) {
		squadron.changeDestination(p);
	}

	public void moveShipTowards(Planet p, int percentage, ArrayList<GalaxyItem> itemList) {
		moveShipTowards(p);
	}
	
	public abstract void attack(Planet p);

}
