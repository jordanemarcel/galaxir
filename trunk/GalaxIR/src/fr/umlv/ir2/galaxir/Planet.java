package fr.umlv.ir2.galaxir;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import com.sun.xml.internal.bind.v2.TODO;

import fr.umlv.ir2.galaxir.ShipFactory.ShipType;
import fr.umlv.remix.Application;


public class Planet implements GalaxyItem{
	private double nbShip;
	private Player owner;
	private final double shipRepop;
	private final int width;
	private final Point2D location;
	private boolean over;
	private ShipType currentShipType;
	private boolean selected;
	private int selectedArc = 0;
	private ArrayList<SquadronUnleasher> squadronList = new ArrayList<SquadronUnleasher>();

	public Planet(ArrayList<GalaxyItem> testItemList) {
		Random random = new Random();
		int width = random.nextInt(80)+20;
		int minimumX = 0 + width/2;
		int minimumY = 0 + width/2;
		int maximumX = 640 - width;
		int maximumY = 480 - width;
		boolean intersect;
		int x;
		int y;
		do {
			x = random.nextInt(maximumX) + minimumX;
			y = random.nextInt(maximumY) + minimumY;
			intersect = false;
			for(GalaxyItem g: testItemList) {
				if(g instanceof Planet) {
					Planet p = (Planet)g;
					if(!intersect)
						intersect = p.intersects(new Point(x, y), width);
				}	
			}
		} while (intersect);
		this.location = new Point(x, y);
		this.width = width;
		this.shipRepop = random.nextInt(100);
		this.nbShip = random.nextInt(100);
		this.owner = null;
		this.currentShipType = ShipType.XTWIN;
	}
	
	public Planet(int nbShip, int shipRepop, int width, Point2D location, Player owner) {
		if (shipRepop > 100)
			throw new IllegalArgumentException("The ship repop speed is too high");
		this.nbShip = nbShip;
		this.shipRepop = shipRepop;
		this.width = width;
		this.location = location;
		this.owner = owner;
		//shape = new Rectangle((Point)(location),new Dimension(width,width) );
		over=false;
		this.currentShipType = ShipType.TIGHTFIGHTER;
	}
	public void startProduction() {
		if(owner!=null)
			this.nbShip += shipRepop/60;
	}
	
	public void setOver() {
		if(Player.getHumanPlayer()==owner) {
			if(!over)
				SoundEffect.playMouseOver();
			over = true;
		}
	}
	
	public void setEndOver() {
		over = false;
	}
	
	public void setNbShip(int nb) {
		nbShip = nb;
	}
	
	public void setNbShip(double nb) {
		nbShip = nb;
	}
	
	public int getNbShip() {
		return (int)Math.floor(nbShip);
	}
	
	public double getNbShipDouble() {
		return nbShip;
	}
	
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	public Player getOwner() {
		return this.owner;
	}
	
	public int getRadius() {
		return width/2;
	}

	public double getShipRepop() {
		return shipRepop;
	}
	public int getWidth() {
		return width;
	}
	public Point2D getLocation() {
		return location;
	}
	
    public static double circleDistance(Point2D p1, Point2D p2) {
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        return dx * dx + dy * dy;
      }
    
	public boolean contains(Point2D p){
		return circleDistance(location, p) <= getWidth()*getWidth()/4;
	}
	
	public boolean contains(Planet p){
		double distance = location.distance(p.getLocation());
		if(distance < p.getWidth()+width)
			return true;
		return false;
	}
	@Override
	public String toString() {
		return "Planet at "+location.toString()+" width ="+width+" "+over;
	}
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.lightGray);
		g.fillOval((int)location.getX()-(width+4)/2, (int)location.getY()-(width+4)/2, width+4, width+4);
		if(over){
			selectedArc++;
			if(selectedArc>360)
				selectedArc = 0;
			g.setColor(Color.white);
			g.fillArc((int)location.getX()-(width+8)/2, (int)location.getY()-(width+8)/2, width+8, width+8, 0+selectedArc, 90);
			g.fillArc((int)location.getX()-(width+8)/2, (int)location.getY()-(width+8)/2, width+8, width+8, 180+selectedArc, 90);
			//g.fillOval((int)location.getX()-(width+8)/2, (int)location.getY()-(width+8)/2, width+8, width+8);
		}
		if(owner!=null) {
			if(!selected)
				g.setColor(owner.getMainColor());
			else
				g.setColor(owner.getAuxColor());
		} else
			g.setColor(Color.DARK_GRAY);
		g.fillOval((int)location.getX()-width/2, (int)location.getY()-width/2, width, width);
		g.setColor(Color.white);
		String s = new String(""+this.getNbShip());
		g.drawString(s, (int)location.getX()-5, (int)location.getY()+5);
		
		int total = 0;
		for( int i = 0; i<squadronList.size(); i++ ) {
			SquadronUnleasher su = squadronList.get(i);
			int shipLeft = su.getNumberOfShip();
			if(shipLeft==0) {
				squadronList.remove(i);
			}
			total += shipLeft;
		}
		
		
		s = new String("+"+total);
		if(total>0)
			g.drawString(s, (int)location.getX()-5, (int)location.getY()+15);
	}

	/*@Override
	public Rectangle2D getRectangleArea() {
		return shape;
	}*/
	
	public void selectAndAdd(Player player) {
		if(player==this.getOwner()) {
			selected = true;
			player.addSelectedItem(this);
			SoundEffect.playMouseClick();
		}
	}
	
	public void unselectAndRemove(Player player) {
		if(player==this.getOwner()) {
			selected = false;
			player.removeSelectedItem(this);
			SoundEffect.playMouseClick();
		}
	}
	
	@Override
	public void selected(Player player) {
		selected = true;
	}
	
	public void unselected(Player player) {
		selected = false;
	}
	
	public boolean intersects(Point2D location, int width) {
		double distance = this.getLocation().distance(location);
		return (distance<(this.getRadius()+width/2));
	}
	
	public boolean callReinforcement() {
		if(squadronList.size()==0)
			return false;
		SquadronUnleasher su = squadronList.get(0);
		this.nbShip += su.getNumberOfShip();
		su.setNumberOfShip(0);
		return true;
	}
	
	public void moveShipTowards(Planet p, int percentage, ArrayList<GalaxyItem> itemList) {
		
		int number = (int)Math.floor(nbShip * owner.getPercentage() / 100);
		nbShip -= number;
		
		// Pensez à mesurer le timer ideal!
		
		Squadron squadron = new Squadron(this, p, owner);
		SquadronUnleasher squadronUnleasher = new SquadronUnleasher(number,currentShipType, squadron, itemList);
		squadronList.add(squadronUnleasher);
		
		Application.timer(400, new SquadronUnleasherTimer(squadronUnleasher));
	}
}
