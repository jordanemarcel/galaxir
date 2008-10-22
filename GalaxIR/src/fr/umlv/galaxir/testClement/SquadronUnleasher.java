package fr.umlv.galaxir.testClement;


import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import fr.umlv.remix.TimerTask;

public class SquadronUnleasher {
	private int numberOfShip;
	private Squadron squadron;
	private ArrayList<GalaxyItem> itemList;
	double angleOfRotation;
	Point2D calibratedPoint;
	Point2D.Double locationDouble;
	
	public SquadronUnleasher(int numberOfShip, Squadron squadron, ArrayList<GalaxyItem> itemList) {
		this.numberOfShip = numberOfShip;
		this.squadron = squadron;
		this.itemList = itemList;
		this.computeWhatINeed();
	}
	
	public void computeWhatINeed() {
		locationDouble = new Point2D.Double(squadron.getSourcePlanet().getLocation().getX(), squadron.getSourcePlanet().getLocation().getY());
		Point2D.Double top = Trigo.findUpperPoint(locationDouble);
		Point2D.Double destinationDouble = new Point2D.Double(squadron.getDestinationPlanet().getLocation().getX(), squadron.getDestinationPlanet().getLocation().getY());
		double startAngle = Trigo.computeAngle(locationDouble, top, destinationDouble);
		if(locationDouble.getX()>destinationDouble.getX())
			startAngle = -startAngle;
		
		double creationX = locationDouble.getX();
		double creationY = locationDouble.getY() - squadron.getSourcePlanet().getRadius() - Xtwin.getStaticSize();
		
		Point2D.Double leftPoint = new Point2D.Double(creationX - Xtwin.getStaticSize()/2 - 10, creationY);
		Point2D.Double rightPoint = new Point2D.Double(creationX + Xtwin.getStaticSize()/2 + 10, creationY);
		
		angleOfRotation = Trigo.computeAngle(locationDouble, leftPoint, rightPoint);
		
		calibratedPoint = new Point((int)(creationX*100), (int)(creationY*100));
		AffineTransform at = AffineTransform.getRotateInstance(startAngle, locationDouble.getX()*100, locationDouble.getY()*100);
		Point2D currentPoint = at.transform(calibratedPoint, null);
		calibratedPoint = new Point((int)currentPoint.getX(), (int)currentPoint.getY());
	}
	
	public int shipPerSubSquadron() {
		return (int)(Math.floor(180/(Math.toDegrees(angleOfRotation)))+1);
	}
	
	public void run(TimerTask timerTask) {
		int numberToCreate = shipPerSubSquadron();
		if(numberToCreate>numberOfShip)
			numberToCreate = numberOfShip;
		
		Point2D currentPoint = new Point((int)calibratedPoint.getX(),(int)calibratedPoint.getY());
		double startAngle = 0;
		
		for(int i=0;i<numberToCreate;i++) {
			Ship currentShip = new Xtwin(new Point2D.Double(currentPoint.getX()/100, currentPoint.getY()/100), squadron.getDestinationPlanet(), squadron.getOwner());
			AffineTransform at = AffineTransform.getRotateInstance(startAngle, locationDouble.getX()*100, locationDouble.getY()*100);
			currentPoint = at.transform(calibratedPoint, null);
			if(startAngle>0)
				startAngle = -startAngle;
			else {
				startAngle = -startAngle;
				startAngle += angleOfRotation;
			}
			squadron.add(currentShip);
			itemList.add(currentShip);
		}
		
		numberOfShip -= numberToCreate;
		if(numberOfShip==numberToCreate)
			timerTask.cancel();
	}

}
