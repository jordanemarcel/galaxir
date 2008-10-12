package fr.umlv.galaxir.testClement;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Xtwin extends Ship{

	public Xtwin( Point2D location,Point2D destination,Player owner) {
		super(1, 1, 1, 10, location, destination, owner);
	}

	@Override
	public boolean contains(Point2D p) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void draw(Graphics2D g) {
		Point2D pos = getLocation();
        int x = (int) pos.getX(), y = (int) pos.getY(), w = super.getSize();
        g.setColor(Color.red);
        g.drawLine(x, y, x-w/2, y+w);
        g.drawLine(x, y, x+w/2, y+w);
        g.drawLine(x-w/2, y+w, x+w/2, y+w);
        g.setColor(Color.black);
        g.drawLine((int)getSource().getX(), (int)getSource().getY(), (int)getDestination().getX(), (int)getDestination().getY());
	}
	@Override
	public Rectangle2D getRectangleArea() {
		// TODO Auto-generated method stub
		return null;
	}
}
