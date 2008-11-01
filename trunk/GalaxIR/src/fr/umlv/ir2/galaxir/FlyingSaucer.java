package fr.umlv.ir2.galaxir;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class FlyingSaucer extends Ship {
private static final int size = 10;
	
	public FlyingSaucer( Point2D.Double location,Planet destinationPlanet,Player owner) {
		super(1, 2, 1, size, location, destinationPlanet, owner);
	}

	@Override
	public boolean contains(Point2D p) {
		return p.distance(this.getLocation())<this.getRadius();
	}	
	
	public static double getStaticSize() {
		return size;
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
        g.setColor(this.getOwner().getMainColor());
        if(over)
        	g.setColor(Color.white);
        if(this.squadron.isSelected())
        	g.setColor(this.getOwner().getAuxColor());
        double rotation = this.getRotation();
        
        g.setColor(Color.white);
        g.fillOval(x-w/2, y-w/2, w, w);
        g.setColor(Color.red);
        g.fillOval(x-w/4, y-w/4, w/2, w/2);
        
        /*int[] tx = new int[3];
        int[] ty = new int[3];
        tx[0] = x;
        ty[0] = y-w/2;
        tx[1] = x-w/2;
        ty[1] = y+w/2;
        tx[2] = x+w/2;
        ty[2] = y+w/2;
        AffineTransform at = AffineTransform.getRotateInstance(rotation, x, y);
        for(int i=0;i<3;i++) {
        	Point2D p = new Point(tx[i], ty[i]);
        	Point2D np = at.transform(p, null);
        	tx[i] = (int)np.getX();
        	ty[i] = (int)np.getY();
        }
        g.fillPolygon(tx, ty, 3);*/
	}
}
