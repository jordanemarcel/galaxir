package fr.umlv.galaxir.testJordane;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Xtwin extends Ship{

	public Xtwin( Point2D.Double location,Point2D.Double destination,Player owner) {
		super(1, 1, 1, 5, location, destination, owner);
	}

	@Override
	public boolean contains(Point2D p) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int getRadius() {
		return getSize();
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
        g.setColor(Color.red);
        /* g.drawLine(x, y, x-w/2, y+w);
        g.drawLine(x, y, x+w/2, y+w);
        g.drawLine(x-w/2, y+w, x+w/2, y+w);*/
        int[] tx = new int[3];
        int[] ty = new int[3];
        tx[0] = x;
        tx[1] = x-w;
        tx[2] = x+w;
        ty[0] = y-w;
        ty[1] = y+w;
        ty[2] = y+w;
        
        g.fillPolygon(tx, ty, 3);
        g.setColor(Color.black);
        g.drawLine(x, y, (int)getDestination().getX(), (int)getDestination().getY());
	}
	@Override
	public Rectangle2D getRectangleArea() {
		// TODO Auto-generated method stub
		return null;
	}
}
