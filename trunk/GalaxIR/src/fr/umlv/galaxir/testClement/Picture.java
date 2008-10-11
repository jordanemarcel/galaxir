package fr.umlv.galaxir.testClement;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Picture implements GalaxyItem{
	private final BufferedImage img;
	private final Rectangle2D shape;
	public Picture(String file) throws IOException {
		img = ImageIO.read(new File(file));
		shape = new Rectangle(new Point(0,0), new Dimension(img.getWidth(),img.getHeight()) ) ;
	}
	@Override
	public boolean contains(Point2D p) {
		return shape.contains(p);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(img,0,0,null);
	}

	@Override
	public Rectangle2D getRectangleArea() {
		
		return shape;
	}

	@Override
	public void selected() {
		// TODO Auto-generated method stub

	}
	

}
