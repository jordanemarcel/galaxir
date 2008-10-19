package fr.umlv.ir2.galaxir;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;


public class Planet implements GalaxyItem{
	private int nbShip;
	private int currentColor;
	private Player owner;
	private final int shipRepop;
	private final int width;
	private final Point2D location;
	private final Color[] colors = { Color.blue, Color.cyan };
	//private final Rectangle2D shape;
	private boolean over;
	private boolean selected;

	public Planet(ArrayList<GalaxyItem> testItemList) {
		Random random = new Random();
		int width = random.nextInt(80)+20;
		int minimumX = 0 + width/2;
		int minimumY = 0 + width/2;
		int maximumX = 640 - width;
		int maximumY = 480 - width;
		boolean intersect;
		int x;
		int y;
		do {
			x = random.nextInt(maximumX) + minimumX;
			y = random.nextInt(maximumY) + minimumY;
			intersect = false;
			for(GalaxyItem g: testItemList) {
				if(g instanceof Planet) {
					Planet p = (Planet)g;
					if(!intersect)
						intersect = p.intersects(new Point(x, y), width);
				}	
			}
		} while (intersect);
		this.location = new Point(x, y);
		this.width = width;
		this.shipRepop = random.nextInt(100);
		this.nbShip = random.nextInt(100);
		this.owner = null;
	}
	
	public Planet(int nbShip, int shipRepop, int width, Point2D location, Player owner) {
		if (shipRepop > 100)
			throw new IllegalArgumentException("The ship repop speed is too high");
		this.nbShip = nbShip;
		this.shipRepop = shipRepop;
		this.width = width;
		this.location = location;
		this.owner = owner;
		//shape = new Rectangle((Point)(location),new Dimension(width,width) );
		over=false;
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
	
    public static double circleDistance(Point2D p1, Point2D p2) {
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        return dx * dx + dy * dy;
      }
    
	public boolean contains(Point2D p){
		return circleDistance(location, p) <= getWidth()*getWidth()/4;
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
		if(owner!=null) {
			if(!selected)
				g.setColor(owner.getMainColor());
			else
				g.setColor(owner.getAuxColor());
		} else
			g.setColor(Color.DARK_GRAY);
		g.fillOval((int)location.getX()-width/2, (int)location.getY()-width/2, width, width);
		g.setColor(Color.white);
		String s = new String(""+nbShip);
		g.drawString(s, (int)location.getX()-5, (int)location.getY()+5);
	}

	/*@Override
	public Rectangle2D getRectangleArea() {
		return shape;
	}*/
	@Override
	public void selected(Player player) {
		selected = (selected==true ? false : true);
		player.addSelectedPlanet(this);
	}
	
	public boolean intersects(Point2D location, int width) {
		double distance = this.getLocation().distance(location);
		return (distance<(this.getRadius()+width/2));
	}
}
