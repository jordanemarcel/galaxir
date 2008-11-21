package fr.umlv.ir2.galaxir.items;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import fr.umlv.ir2.galaxir.core.Player;

public class EndOfGame implements GalaxyItem {
	private final Player player;
	private final int width;
	private final int height;

	public EndOfGame(Player player, int width, int height) {
		this.player = player;
		this.width = width;
		this.height = height;
	}


	@Override
	public boolean contains(Point2D p) {
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		String s;
		if(this.player==null)
			s = "The Galaxy is freaking empty!";
		else
			s = this.player+" is the Ruler, the Master, the Emperor of the Galaxy.";
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 10));
		FontMetrics fm = g.getFontMetrics ();
		g.drawString(s, width/2-fm.stringWidth(s)/2, height/2);
		String wellDone = "Well done!";
		g.drawString(wellDone, width/2-fm.stringWidth(wellDone)/2, height/2+height/10);
	}
}
