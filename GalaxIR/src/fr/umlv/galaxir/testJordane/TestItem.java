package fr.umlv.galaxir.testJordane;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;

public class TestItem {
    /*
     * Our TestItem will be represented by a square centered at a given position
     */
    private final Point2D center;
    private final int width;
    private final Color[] colors = { Color.pink, Color.blue };
    private int currentColor;

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
  }
