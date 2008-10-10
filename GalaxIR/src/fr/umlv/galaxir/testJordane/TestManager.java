package fr.umlv.galaxir.testJordane;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Point2D;

import fr.umlv.remix.ItemManager;

class TestManager implements ItemManager<TestItem> {
    @Override
    public void draw(Graphics2D g, TestItem item) {
      Point2D pos = item.getLocation();
      int x = (int) pos.getX(), y = (int) pos.getY(), w = item.getWidth();
      g.setColor(item.getColor());
      g.fillRect(x - w / 2, y - w / 2, w, w);
      g.setColor(Color.white);
      g.drawLine(x - w / 2, y - w / 2, x + w / 2, y + w / 2);     
      /*g.setColor(Color.black);
      int[] table = rotateTriangle(x, y, w);
      g.drawLine(table[0], table[1], table[2], table[3]);*/
    }
    /*
    public static int[] rotateTriangle(int x, int y, int height) {
    	int[] table = new int[6];
    	table[0] = x;
    	table[1] = y;
    	Random random = new Random();
    	int x2 = random.nextInt(300);
    	int y2 = random.nextInt(200);
    	int adj = x2 - x;
    	int opp = y2 - y;
    	double angle = Math.atan(opp/adj);
    	table[2] = x + (int)(height * Math.cos(angle));
    	table[3] = y + (int)(height * Math.sin(angle));
    	return table;
    }*/

    @Override
    public boolean intersects(Shape selection, TestItem item) {
      return selection.contains(item.getLocation());
    }

    @Override
    public boolean contains(Point2D location, TestItem item) {
      return squareDistance(location, item.getLocation()) <= item.getWidth()
          * item.getWidth();
    }

    private static double squareDistance(Point2D p1, Point2D p2) {
      double dx = p1.getX() - p2.getX();
      double dy = p1.getY() - p2.getY();
      return dx * dx + dy * dy;
    }

    @Override
    public boolean isContained(Shape arg0, TestItem arg1) {
      Point2D pos = arg1.getLocation();
      int x = (int) pos.getX(), y = (int) pos.getY(), w = arg1.getWidth();
      return arg0.contains(new Rectangle(x - w / 2, y - w / 2, w, w));
    }
  }
