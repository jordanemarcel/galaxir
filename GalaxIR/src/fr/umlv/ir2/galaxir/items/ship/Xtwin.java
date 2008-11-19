package fr.umlv.ir2.galaxir.items.ship;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import fr.umlv.ir2.galaxir.core.Player;
import fr.umlv.ir2.galaxir.items.Planet;

public class Xtwin extends Ship {
	private static final int attack = 1;
	private static final int speed = 3;
	private static final int size = 10;
	
	public Xtwin(Point2D.Double location,Planet destinationPlanet,Player owner) {
		super(attack, speed, size, location, destinationPlanet, owner);
	}
	
	public static double getStaticSize() {
		return size;
	}
	
	public static double getStaticSpeed() {
		return speed;
	}

	@Override
	public boolean contains(Point2D p) {
		return p.distance(this.getLocation())<this.getRadius();
	}	
	
	@Override
	public boolean intersects(Planet p) {
		double distance = p.getLocation().distance(this.getLocation());
		return (distance<(this.getRadius()+p.getRadius()));
	}
	
	@Override
	public void draw(Graphics2D g) {
		Point2D pos = getLocation();
        int x = (int)pos.getX();
        int y = (int)pos.getY();
        int size = this.getSize();
        
        if(over || this.squadron.isSelected()) {
        	g.setColor(Color.white);
        	g.drawOval(x-size/2, y-size/2, size, size);
        }
        
        Color mainColor = this.getOwner().getMainColor();
        g.setColor(mainColor);
        double rotation = this.getRotation();
        int[] tx = new int[3];
        int[] ty = new int[3];
        tx[0] = x;
        ty[0] = y-size/2;
        tx[1] = x-size/2;
        ty[1] = y+size/2;
        tx[2] = x+size/2;
        ty[2] = y+size/2;
        AffineTransform at = AffineTransform.getRotateInstance(rotation, x, y);
        for(int i=0;i<3;i++) {
        	Point2D p = new Point(tx[i], ty[i]);
        	Point2D np = at.transform(p, null);
        	tx[i] = (int)np.getX();
        	ty[i] = (int)np.getY();
        }
        g.fillPolygon(tx, ty, 3);
	}
}
