package fr.umlv.ir2.galaxir.items.ship;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import fr.umlv.ir2.galaxir.core.AuthoritativeItemManager;
import fr.umlv.ir2.galaxir.items.ship.ShipFactory.ShipType;
import fr.umlv.ir2.galaxir.utils.Trigo;
import fr.umlv.remix.TimerTask;

public class SquadronUnleasher {
	private int numberOfShip;
	private Squadron squadron;
	double angleOfRotation;
	Point2D calibratedPoint;
	Point2D.Double locationDouble;
	private final ShipType shipType;
	private AuthoritativeItemManager authoritativeItemManager;
	
	public int getNumberOfShip() {
		return numberOfShip;
	}
	
	public SquadronUnleasher(int numberOfShip,ShipType shipType, Squadron squadron) {
		this.numberOfShip = numberOfShip;
		this.squadron = squadron;
		squadron.setSquadronUnleasher(this);
		this.computeWhatINeed();
		this.shipType = shipType;
		this.authoritativeItemManager = squadron.getOwner().getAuthoritativeItemManager();
	}
	
	public void setNumberOfShip(int number) {
		numberOfShip = number;
	}
	
	public void computeWhatINeed() {
		locationDouble = new Point2D.Double(squadron.getSourcePlanet().getLocation().getX(), squadron.getSourcePlanet().getLocation().getY());
		Point2D.Double top = Trigo.findUpperPoint(locationDouble);
		Point2D.Double destinationDouble = new Point2D.Double(squadron.getDestinationPlanet().getLocation().getX(), squadron.getDestinationPlanet().getLocation().getY());
		double startAngle = Trigo.findAngle(locationDouble, top, destinationDouble);
		if(locationDouble.getX()>destinationDouble.getX())
			startAngle = -startAngle;
		
		double creationX = locationDouble.getX();
		double creationY = locationDouble.getY() - squadron.getSourcePlanet().getRadius() - Xtwin.getStaticSize();
		
		Point2D.Double leftPoint = new Point2D.Double(creationX - Xtwin.getStaticSize()/2, creationY);
		Point2D.Double rightPoint = new Point2D.Double(creationX + Xtwin.getStaticSize()/2, creationY);
		
		angleOfRotation = Trigo.findAngle(locationDouble, leftPoint, rightPoint);
		
		calibratedPoint = new Point((int)(creationX*100), (int)(creationY*100));
		AffineTransform at = AffineTransform.getRotateInstance(startAngle, locationDouble.getX()*100, locationDouble.getY()*100);
		Point2D currentPoint = at.transform(calibratedPoint, null);
		calibratedPoint = new Point((int)currentPoint.getX(), (int)currentPoint.getY());
	}
	
	public int shipPerSubSquadron() {
		return (int)(Math.floor(180/(Math.toDegrees(angleOfRotation)))+1);
	}
	
	public void run(TimerTask timerTask) {
		if(squadron.getSourcePlanet()==squadron.getDestinationPlanet()) {
			timerTask.cancel();
			double nbShip = squadron.getSourcePlanet().getNbShipDouble();
			squadron.getSourcePlanet().setNbShipDouble(nbShip + numberOfShip);
			numberOfShip = 0;
			return;
		}
		int numberToCreate = shipPerSubSquadron();
		if(numberToCreate>numberOfShip)
			numberToCreate = numberOfShip;
		
		Point2D currentPoint = new Point((int)calibratedPoint.getX(),(int)calibratedPoint.getY());
		double startAngle = 0;
		
		for(int i=0;i<numberToCreate;i++) {
			Ship currentShip = shipType.create(new Point2D.Double(currentPoint.getX()/100, currentPoint.getY()/100), squadron.getDestinationPlanet(), squadron.getOwner());
			currentShip.setSquadron(squadron);
			AffineTransform at = AffineTransform.getRotateInstance(startAngle, locationDouble.getX()*100, locationDouble.getY()*100);
			currentPoint = at.transform(calibratedPoint, null);
			if(startAngle>0)
				startAngle = -startAngle;
			else {
				startAngle = -startAngle;
				startAngle += angleOfRotation;
			}
			squadron.add(currentShip);
			authoritativeItemManager.addShip(currentShip);
		}
		
		numberOfShip -= numberToCreate;
		if(numberOfShip==numberToCreate)
			timerTask.cancel();
	}

}
