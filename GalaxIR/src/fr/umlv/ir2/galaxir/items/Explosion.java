package fr.umlv.ir2.galaxir.items;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import fr.umlv.ir2.galaxir.core.AuthoritativeItemManager;
import fr.umlv.ir2.galaxir.core.Player;

public class Explosion implements GalaxyItem {
	private Point2D location;
	private int animationFrame;
	private boolean toBeDeleted;

	public Explosion(int x, int y) {
		location = new Point(x,y);
		animationFrame = 0;
		toBeDeleted = false;
	}
	
	@Override
	public boolean contains(Point2D p) {
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		Random r = new Random();
		int width = r.nextInt(30)+10;
		g.setColor(Color.yellow);
		if(animationFrame<3) {
			g.fillOval((int)location.getX()-width/2, (int)location.getY()-width/2, width, width);
			animationFrame++;
		} else {
			toBeDeleted = true;
		}
	}
	
	public boolean toBeDeleted() {
		return toBeDeleted;
	}
	
}
