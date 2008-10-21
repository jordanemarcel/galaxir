package fr.umlv.ir2.galaxir;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Xtwin extends Ship{

	public Xtwin( Point2D.Double location,Planet destinationPlanet,Player owner) {
		super(1, 2, 1, 5, location, destinationPlanet, owner);
	}

	@Override
	public boolean contains(Point2D p) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int getRadius() {
		return getSize();
	}
	
	public static double getStaticSize() {
		return 5;
	}
	
	public boolean intersects(Planet p) {
		double distance = p.getLocation().distance(this.getLocation());
		//if(distance<(this.getRadius()+p.getRadius()))
			//System.out.println(distance+" contre "+this.getRadius()+" et "+p.getRadius());
		return (distance<(this.getRadius()+p.getRadius()));
	}
	
	@Override
	public void draw(Graphics2D g) {
		Point2D pos = getLocation();
        int x = (int) pos.getX(), y = (int) pos.getY(), w = super.getSize();
        g.setColor(Color.blue);
        /* g.drawLine(x, y, x-w/2, y+w);
        g.drawLine(x, y, x+w/2, y+w);
        g.drawLine(x-w/2, y+w, x+w/2, y+w);*/
        double rotation = this.getRotation();
        int[] tx = new int[3];
        int[] ty = new int[3];
        tx[0] = x;
        tx[1] = x-w;
        tx[2] = x+w;
        ty[0] = y-w;
        ty[1] = y+w;
        ty[2] = y+w;
        AffineTransform at = AffineTransform.getRotateInstance(rotation, x, y);
        for(int i=0;i<3;i++) {
        	Point2D p = new Point(tx[i], ty[i]);
        	Point2D np = at.transform(p, null);
        	tx[i] = (int)np.getX();
        	ty[i] = (int)np.getY();
        }
        g.fillPolygon(tx, ty, 3);
        g.setColor(Color.black);
        //g.drawLine(x, y, (int)getDestination().getX(), (int)getDestination().getY());
	}
	/*@Override
	public Rectangle2D getRectangleArea() {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	public void attack(Planet p) {
		int lastShip;
		
		if(this.getOwner()==p.getOwner())
			lastShip = p.getNbShip() + this.getAttack();
		else
			lastShip = p.getNbShip() - this.getAttack();
		
		if(lastShip<=0) {
			lastShip = 0;
			p.setOwner(this.getOwner());
		}
		p.setNbShip(lastShip);
		//SoundEffect.playExplosion();
		this.delete();
	}
}
