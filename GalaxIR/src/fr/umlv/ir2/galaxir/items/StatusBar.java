package fr.umlv.ir2.galaxir.items;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import fr.umlv.ir2.galaxir.core.Player;

public class StatusBar implements GalaxyItem {
	private final int windowWidth;
	private final int windowHeight;
	private static final int height = 20;
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
		if(humanPlayer!=null) {
			if(humanPlayer.getOveredPlanet()!=null) {
				if(humanPlayer.getOveredPlanet().getOwner()==humanPlayer) {
					String type = "Ship Type: "+humanPlayer.getOveredPlanet().getCurrentShipType();
					g.setColor(Color.black);
					g.drawString(type, windowWidth/10, windowHeight+15);
				}
				String production = "Production: "+(int)humanPlayer.getOveredPlanet().getShipRepop();
				g.setColor(Color.black);
				g.drawString(production, windowWidth/2, windowHeight+15);

			}
			String s = humanPlayer.getPercentage()+"%";
			g.setColor(Color.black);
			g.drawString(s, windowWidth-40, windowHeight+15);
		}
	}

}
