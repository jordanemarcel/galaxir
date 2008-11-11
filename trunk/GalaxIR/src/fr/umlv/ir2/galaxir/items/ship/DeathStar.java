package fr.umlv.ir2.galaxir.items.ship;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import fr.umlv.ir2.galaxir.core.Player;
import fr.umlv.ir2.galaxir.items.Planet;

public class DeathStar extends Ship {
	private static final int attack = 10;
	private static final int speed = 1;
	private static final int size = 50;
	
	public DeathStar( Point2D.Double location,Planet destinationPlanet,Player owner) {
		super(attack, speed, size, location, destinationPlanet, owner);
	}

	@Override
	public boolean contains(Point2D p) {
		return false;
	}
	
	public int getRadius() {
		return getSize()/2;
	}
	
	@Override
	public boolean intersects(Planet p) {
		double distance = p.getLocation().distance(this.getLocation());
		return (distance<(this.getRadius()+p.getRadius()));
	}
	
	@Override
	public void draw(Graphics2D g) {
		Point2D pos = getLocation();
        int x = (int) pos.getX(), y = (int) pos.getY(), w = super.getSize();
        g.setColor(Color.gray);
        g.fillOval(x-w/2, y-w/2, w, w);
        g.setColor(Color.darkGray);
        g.fillOval(x+w/8, y-w/3, w/4, w/4);
        g.setColor(Color.black);
        g.drawArc(x-w/2, y-w/10, w, w/5, 180, 180);
        g.drawArc(x-w/2, y-w/10-1, w, w/5, 180, 180);
        g.drawArc(x-w/2, y-w/10+1, w, w/5, 180, 180);
	}

	public void attack(Planet p) {
		int lastShip = p.getNbShip() - this.getAttack();
		if(lastShip<=0) {
			lastShip = 0;
			p.setOwner(this.getOwner());
		}
		p.setNbShip(lastShip);
	}
	
}
