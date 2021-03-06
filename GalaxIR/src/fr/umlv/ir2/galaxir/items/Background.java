package fr.umlv.ir2.galaxir.items;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;


public class Background implements GalaxyItem {
	private Color backgroundColor;
	private int width;
	private int height;
	
	public Background(int width, int height, Color color) {
		this.width = width;
		this.height = height;
		backgroundColor = color;
	}
	
	public void setColor(Color color) {
		this.backgroundColor = color;
	}
	
	@Override
	public boolean contains(Point2D p) {
		return true;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, width, height);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	

}
