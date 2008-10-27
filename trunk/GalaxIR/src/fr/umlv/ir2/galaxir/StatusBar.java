package fr.umlv.ir2.galaxir;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class StatusBar implements GalaxyItem {

	@Override
	public boolean contains(Point2D p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.yellow);
		g.fillRect(0, 480, 640, 500);
		String s = new String(Player.getHumanPlayer().getPercentage()+"%");
		g.setColor(Color.black);
		g.drawString(s, 600, 495);
	}

	@Override
	public void moveShipTowards(Planet p, int percentage,
			ArrayList<GalaxyItem> itemList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectAndAdd(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selected(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unselectAndRemove(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unselected(Player player) {
		// TODO Auto-generated method stub
		
	}

}
