package fr.umlv.galaxir.testClement;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class Planet implements GalaxyItem{
	private int nbShip;
	private int currentColor;
	private Player owner;
	private final int shipRepop;
	private final int width;
	private final Point2D location;
	private final Color[] colors = { Color.blue, Color.cyan };
	private final Rectangle2D shape;
	private boolean over;
	
	public Planet(int nbShip, int shipRepop, int width, Point2D location, Player owner) {
		if (shipRepop > 100)
			throw new IllegalArgumentException("The ship repop speed is too high");
		this.nbShip = nbShip;
		this.shipRepop = shipRepop;
		this.width = width;
		this.location = location;
		this.owner = owner;
		shape = new Rectangle((Point)(location),new Dimension(width,width) );
		over=false;
	}
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	public int getNbShip() {
		return nbShip;
	}
	public int getShipRepop() {
		return shipRepop;
	}
	public int getWidth() {
		return width;
	}
	public Point2D getLocation() {
		return location;
	}
	public Color getColor() {
		return colors[currentColor];
	}
	public boolean contains(Point2D p){
		return shape.contains(p);
	}
	public boolean contains(Planet p){
		double distance = location.distance(p.getLocation());
		if(distance < p.getWidth()+width)
			return true;
		return false;
	}
	@Override
	public String toString() {
		return "Planet at "+location.toString()+" width ="+width+" "+over;
	}
	@Override
	public void draw(Graphics2D g) {
		if(over){
			g.setColor(Color.green);
			g.fillOval((int)location.getX()-3, (int)location.getY()-3, width+6, width+6);
			
		}
		g.setColor(this.getColor());
		g.fillOval((int)location.getX(), (int)location.getY(), width, width);
		g.setColor(Color.white);
		String s = new String(""+nbShip);
		g.drawString(s, (int)location.getX()+width/2-s.length()*4, (int)location.getY()+width/2+4);
	}
	
	@Override
	public Rectangle2D getRectangleArea() {
		return shape;
	}
	@Override
	public void selected() {
		currentColor = (currentColor == 0 ? 1 : 0);
	}
}
