package fr.umlv.galaxir.testJordane;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;

import fr.umlv.remix.ItemManager;

class TestManager implements ItemManager<Drawable> {
    @Override
    public void draw(Graphics2D g, Drawable item) {
    	item.draw(g);
    }

    @Override
    public boolean intersects(Shape selection, Drawable item) {
      return selection.contains(item.getLocation());
    }

    @Override
    public boolean contains(Point2D location, Drawable item) {
      return item.contains(location);
    }

    private static double squareDistance(Point2D p1, Point2D p2) {
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        return dx * dx + dy * dy;
    }
    
    public static double circleDistance(Point2D p1, Point2D p2) {
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        return dx * dx + dy * dy;
      }

    @Override
    public boolean isContained(Shape arg0, Drawable arg1) {
      Point2D pos = arg1.getLocation();
      int x = (int) pos.getX(), y = (int) pos.getY(), w = arg1.getWidth();
      return arg0.contains(new Rectangle(x - w / 2, y - w / 2, w, w));
    }
  }
