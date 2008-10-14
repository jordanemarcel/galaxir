package fr.umlv.galaxir.old.testJordane;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;

public class TestItem implements Drawable {
    /*
     * Our TestItem will be represented by a square centered at a given position
     */
    private final Point2D center;
    private final int width;
    private final Color[] colors = { Color.yellow, Color.black };
    private int currentColor;
    private boolean over;

    public Color getColor() {
      return colors[currentColor];
    }

    public TestItem(int x, int y, int w) {
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
        if(over) {
        	g.setColor(Color.blue);
    		g.fillOval(x-(w+20)/2, y-(w+20)/2, w+20, w+20);
    		setOver(false);
        }
        g.setColor(getColor());
        g.fillOval(x-w/2, y-w/2, w, w);
        swap();
        g.setColor(getColor());
        g.fillOval(x-(w-10)/2, y-(w-10)/2, w-10, w-10);
        swap();
        /*g.setColor(Color.red);
        g.fillOval((int)(x-w/2), (int)(y-w/2), w, w);
        g.setColor(Color.black);
        int[] table = rotateTriangle(x, y, w);
        g.drawLine(table[0], table[1], table[2], table[3]);*/
      }
    
    public void setOver(boolean b) {
    	over = b;
    }
    
    public boolean contains(Point2D location) {
        return TestManager.circleDistance(location, getLocation()) <= getWidth()*getWidth()/4;
      }
  }
