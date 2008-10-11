package fr.umlv.galaxir.testClement;
import java.awt.Color;
import java.awt.Graphics2D;
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
	public void draw(Graphics2D g){
		Point2D pos = this.getLocation();
		g.setColor(this.getColor());
		int x = (int) pos.getX(), y = (int) pos.getY(), w = this.getWidth();
		//this.getNextLocation();
		g.drawLine(x - w / 2, y - w / 2, x + w / 2, y + w / 2);
	}

}
