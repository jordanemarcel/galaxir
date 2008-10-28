package fr.umlv.ir2.galaxir;

import java.awt.geom.Point2D;

public class ShipFactory {
	public enum ShipType{
		XTWIN{
			@Override
			public Ship create(Point2D.Double loc, Planet destination, Player owner){
				return new Xtwin(loc,destination,owner);
			}
			@Override
			public String getName(){
				return "X-Twin";
			}
		},
		TIGHTFIGHTER{
			@Override
			public Ship create(Point2D.Double loc, Planet destination, Player owner){
				return new TightFighter(loc,destination,owner);
			}
			@Override
			public String getName(){
				return "Tight Fighter";
			}
		}
		;
		public abstract Ship create(Point2D.Double loc, Planet destination, Player owner);
		public abstract String getName();
	}
	
	public static Ship createShip(ShipType shipType, Point2D.Double loc, Planet destination, Player owner){
		return shipType.create(loc,destination,owner);
	}
}
