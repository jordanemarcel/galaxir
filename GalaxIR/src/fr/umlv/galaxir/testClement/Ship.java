package fr.umlv.galaxir.testClement;


import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
public abstract class Ship implements GalaxyItem{
	private final int attack;
	private final int speed;
	private final int cost;
	private final int size;
	private final Player owner;
	private Point2D location;
	private final Point2D destination;
	private final Color[] colors;
	private int currentColor;
	private double bufferX = 0;
	private double bufferY = 0;
	//private final LinkedList<Point2D> trajectory;
	public Ship(int attack, int speed, int cost,int size, 
			Point2D location, Point2D destination,Player owner) {
		this.attack = attack;
		this.speed = speed;
		this.cost = cost; 
		this.location = location;
		this.size = size;
		this.destination = destination;
		this.owner = owner;
		colors = new Color[2];
		colors[0] =owner.getMainColor();
		colors[1] = owner.getAuxColor();
		//trajectory = new LinkedList<Point2D>();
	}
	
	public Point2D getDestination() {
		return destination;
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
	public int getSpeed() {
		return speed;
	}
	public int getSize() {
		return size;
	}
	public Point2D getLocation() {
		return location;
	}
	@Override
	public void selected() {
		currentColor = (currentColor == 0 ? 1 : 0);
	}
	public void setLocation(Point2D location){
		this.location = location;
	}
	//public void setDestination(Point2D destination){
		//this.destination = destination;
	//}
	public void move(){
		/*
		if (location.equals(destination))
			return;
		double adj = location.getX() - destination.getX();

		double opp = location.getY() - destination.getY() ;
		boolean adjPos=false;
		boolean oppPos=false;
		if (adj>0)
			adjPos=true;
		if(opp>0)
			oppPos=true;
		adj = Math.abs(adj);
		opp = Math.abs(opp);
		double angle = Math.atan(opp/adj);
		double dx = (double)speed * Math.cos(angle);
		double dy = (double)speed * Math.sin(angle);
		if(adjPos)
			dx= -dx;
		if(oppPos)
			dy = -dy;
		double x =  dx + location.getX();
		double y = dy + location.getY();
		System.out.print("loc"+location+"GOTO="+destination);
		this.setLocation(new Point((int)x,(int)y));
		System.out.println(" new=[x="+x+"y="+y+"]");
		if(location.distance(destination) < speed)
			location=destination;
		*/
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
		bufferX += moveX;
		bufferY += moveY;
		int intMoveX = (int)Math.floor(bufferX);
		int intMoveY = (int)Math.floor(bufferY);
		bufferX -= (double)intMoveX;
		bufferY -= (double)intMoveY;
		double nextX = location.getX() + intMoveX;
		double nextY = location.getY() + intMoveY;
		setLocation(new Point((int)nextX, (int)nextY));
	}

}
