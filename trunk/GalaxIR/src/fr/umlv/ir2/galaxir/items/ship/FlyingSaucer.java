package fr.umlv.ir2.galaxir.items.ship;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import fr.umlv.ir2.galaxir.core.Player;
import fr.umlv.ir2.galaxir.items.Planet;

public class FlyingSaucer extends Ship {
	private static final int attack = 3;
	private static final int speed = 1;
	private static final int size = 20;
	
	public FlyingSaucer( Point2D.Double location,Planet destinationPlanet,Player owner) {
		super(attack, speed, size, location, destinationPlanet, owner);
	}

	@Override
	public boolean contains(Point2D p) {
		return p.distance(this.getLocation())<this.getRadius();
	}	
	
	public static double getStaticSize() {
		return size;
	}
	
	public static double getStaticSpeed() {
		return speed;
	}
	
	@Override
	public boolean intersects(Planet p) {
		double distance = p.getLocation().distance(this.getLocation());
		return (distance<(this.getRadius()+p.getRadius()));
	}
	
	@Override
	public void draw(Graphics2D g) {
		Point2D pos = getLocation();
        int x = (int)pos.getX(), y = (int)pos.getY(), w = this.getSize();
        
        if(over || this.squadron.isSelected()) {
        	g.setColor(Color.white);
        	g.drawOval(x-size/2, y-size/2, size, size);
        }
        g.setColor(this.getOwner().getMainColor());
        g.fillOval(x-w/2, y-w/2, w, w);
        g.setColor(Color.gray);
        g.fillOval(x-w/4, y-w/4, w/2, w/2);
	}
}
