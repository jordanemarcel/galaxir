package fr.umlv.galaxir.testJordane;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
public abstract class Ship implements GalaxyItem{
	private final int attack;
	private final int speed;
	private final int cost;
	private final int size;
	private final Player owner;
	private Point2D.Double location;
	private Point2D.Double destination;
	private final Color[] colors;
	private int currentColor;
	private int goAroundWay = 0;
	private double angle;
	private double rotation = 0;
	
	public static ArrayList<GalaxyItem> l;
	
	//private final LinkedList<Point2D> trajectory;
	public Ship(int attack, int speed, int cost,int size, 
			Point2D.Double location, Point2D.Double destination,Player owner) {
		this.attack = attack;
		this.speed = speed;
		this.cost = cost; 
		this.location = location;
		this.size = size;
		this.destination = destination;
		Random r = new Random();
		while(this.destination.distance(320, 240)<150) {
			this.destination = new Point.Double(r.nextInt(640), r.nextInt(320));
		}
		this.owner = owner;
		colors = new Color[2];
		colors[0] =owner.getMainColor();
		colors[1] = owner.getAuxColor();
		//trajectory = new LinkedList<Point2D>();
	}
	
	public Point2D getDestination() {
		return destination;
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
	public int getSpeed() {
		return speed;
	}
	public int getSize() {
		return size;
	}
	public Point2D getLocation() {
		return location;
	}
	
	public abstract boolean intersects(Planet p);
	@Override
	public void selected() {
		currentColor = (currentColor == 0 ? 1 : 0);
	}
	public void setLocation(Point2D.Double location){
		this.location = location;
	}
	//public void setDestination(Point2D destination){
		//this.destination = destination;
	//}
	public void move(LinkedList<Planet> planetList){
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
					//System.out.println("Old: "+location.getX()+","+location.getY());
					//System.out.println("New: "+nextX+","+nextY);
					//System.out.println("Collision!");
					collision = true;
					farAngle += 10;
					//System.out.println(farAngle);
					if(angle<0)
						angle = farAngle;
					else
						angle = -farAngle;
					if(farAngle>=380) {
						collision = false;
						nextX = 0;
						nextY = 0;
						break;
					}
					AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angle), rotateX, rotateY);
					op2D = new Point2D.Double(nextX*100, nextY*100);
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
					//l.add(new Cordon(new Point.Double(320, 240), new Point.Double(nextX, nextY)));
					this.setLocation(new Point.Double(nextX, nextY));
				}
				//System.out.println(farAngle*goAroundWay);
				angle = 0;
				farAngle = 0;
				//System.out.println("NEXT");
			}
		}
		//System.out.println(nextX+","+nextY);
		//System.out.println("----------");
		//l.add(new Cordon(new Point.Double(location.getX(), location.getY()), new Point.Double(nextX, nextY)));
		setLocation(new Point.Double(nextX, nextY));
		Point2D.Double top = Ship.findUpperPoint(new Point2D.Double(centerX, centerY));
		this.rotation = computeAngle(new Point2D.Double(centerX, centerY), top, new Point.Double(nextX, nextY));
		if(nextX<centerX)
			this.rotation = -this.rotation;
	}
	
	public static Point2D.Double findUpperPoint(Point2D.Double p) {
		double x = p.getX();
		double y = 0;
		return new Point2D.Double(x, y);
	}

	public static double length (double[] v)
	{
		return Math.sqrt(v[0]*v[0] + v[1]*v[1]);
	}
	
	public static double scalarProduct(double[] v0, double[] v1) {
		return v0[0] * v1[0] + v0[1] * v1[1];
	}
	
	public static double[] createVector(Point2D.Double p1, Point2D.Double p2) {
		double v[] = {(p2.getX() - p1.getX()), (p2.getY() - p1.getY())};
		return v;
	}

	public static double computeAngle (Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
		double[] v0 = Ship.createVector(p1, p2);
		double[] v1 = Ship.createVector(p1, p3);

		double dotProduct = Ship.scalarProduct(v0, v1);

		double length1 = Ship.length(v0);
		double length2 = Ship.length(v1);

		double denominator = length1 * length2;

		double product = denominator != 0.0 ? dotProduct / denominator : 0.0;

		double angle = Math.acos(product);

		return angle;
	}

}
