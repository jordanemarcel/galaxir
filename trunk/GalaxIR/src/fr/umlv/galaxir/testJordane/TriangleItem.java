package fr.umlv.galaxir.testJordane;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

public class TriangleItem implements Drawable {

    private final Point2D center;
    private final int width;
    private final Color[] colors = { Color.pink, Color.blue };
    private int currentColor;
    private Drawable destination;
    
    public void setDestination(Drawable d) {
    	this.destination = d;
    }
    
    public Point2D getDestination() {
    	return destination.getLocation();
    }

    public Color getColor() {
      return colors[currentColor];
    }
    
    public void editCenter(double x, double y) {
    	center.setLocation(x, y);
    }

    public TriangleItem(int x, int y, int w) {
      center = new Point(x, y);
      width = w;
    }

    public int getWidth() {
      return width;
    }

    public Point2D getLocation() {
      return center;
    }

    public void swap() {
      currentColor = (currentColor == 0 ? 1 : 0);
    }
    
    public void draw(Graphics2D g) {
        Point2D pos = getLocation();
        int x = (int) pos.getX(), y = (int) pos.getY(), w = getWidth();
        /*g.setColor(item.getColor());
        g.fillRect(x - w / 2, y - w / 2, w, w);
        g.setColor(Color.white);
        g.drawLine(x - w / 2, y - w / 2, x + w / 2, y + w / 2);*/
        g.setColor(Color.blue);
        g.drawLine(x, y, x-w/2, y+w);
        g.drawLine(x, y, x+w/2, y+w);
        g.drawLine(x-w/2, y+w, x+w/2, y+w);
        /*g.setColor(Color.red);
        g.fillOval((int)(x-w/2), (int)(y-w/2), w, w);
        g.setColor(Color.black);
        int[] table = rotateTriangle(x, y, w);
        g.drawLine(table[0], table[1], table[2], table[3]);*/
      }
    
    public boolean contains(Point2D location) {
        return TestManager.circleDistance(location, getLocation()) <= getWidth()*getWidth()/4;
      }
    
    public void setOver(boolean b) {
    	
    }
}
