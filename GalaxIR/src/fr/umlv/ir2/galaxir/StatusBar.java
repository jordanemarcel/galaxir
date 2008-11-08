package fr.umlv.ir2.galaxir;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class StatusBar implements GalaxyItem {
	private final int windowWidth;
	private final int windowHeight;
	private final int height = 20;
	Player humanPlayer;

	public StatusBar(int windowWidth, int windowHeight, Player humanPlayer) {
		this.windowHeight = windowHeight;
		this.windowWidth = windowWidth;
		this.humanPlayer = humanPlayer;
	}
	
	
	@Override
	public boolean contains(Point2D p) {
		return false;
	}
	
	

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.gray);
		g.fillRect(0, windowHeight, windowWidth, windowHeight+height);
		if(humanPlayer.getOveredPlanet()!=null) {
			String s = new String("Production: "+(int)humanPlayer.getOveredPlanet().getShipRepop());
			g.setColor(Color.black);
			g.drawString(s, 300, 495);
		}
		if(humanPlayer!=null) {
			String s = new String(humanPlayer.getPercentage()+"%");
			g.setColor(Color.black);
			g.drawString(s, 600, 495);
		}
	}

}
