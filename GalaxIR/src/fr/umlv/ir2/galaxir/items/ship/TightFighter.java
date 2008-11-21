package fr.umlv.ir2.galaxir.items.ship;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import fr.umlv.ir2.galaxir.core.Player;
import fr.umlv.ir2.galaxir.items.Planet;
import fr.umlv.ir2.galaxir.items.ship.ShipFactory.ShipType;

public class TightFighter extends Ship{
	private static final int attack = 2;
	private static final int speed = 2;
	private static final int size = 15;
	private final ShipType shipType = ShipType.TIGHTFIGHTER;
	
	public TightFighter( Point2D.Double location,Planet destinationPlanet,Player owner) {
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
	
	public ShipType getShipType() {
		return shipType;
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
        double rotation = this.getRotation();
        int[] tx = new int[8];
        int[] ty = new int[8];
        tx[0] = x-w/4;
        ty[0] = y-w/2;    
        tx[1] = x+w/4;
        ty[1] = y-w/2;   
        tx[2] = x+w/4;
        ty[2] = y;     
        tx[3] = x+w/2;
        ty[3] = y;
        tx[4] = x+w/2;
        ty[4] = y+w/2;
        tx[5] = x-w/2;
        ty[5] = y+w/2;
        tx[6] = x-w/2;
        ty[6] = y;    
        tx[7] = x-w/4;
        ty[7] = y;        
        AffineTransform at = AffineTransform.getRotateInstance(rotation, x, y);
        for(int i=0;i<tx.length;i++) {
        	Point2D p = new Point(tx[i], ty[i]);
        	Point2D np = at.transform(p, null);
        	tx[i] = (int)np.getX();
        	ty[i] = (int)np.getY();
        }
        g.fillPolygon(tx, ty, tx.length);
	}
}
