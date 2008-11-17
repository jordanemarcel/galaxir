package fr.umlv.ir2.galaxir.items.ship;

import java.awt.geom.Point2D;

import fr.umlv.ir2.galaxir.core.AuthoritativeItemManager;
import fr.umlv.ir2.galaxir.core.Player;
import fr.umlv.ir2.galaxir.items.Planet;

public class ShipFactory {
	
	public enum ShipType{
		XTWIN{
			@Override
			public Ship create(Point2D.Double loc, Planet destination, Player owner){
				return new Xtwin(loc,destination,owner);
			}
			@Override
			public String toString(){
				return "X-Twin";
			}
			@Override
			public double getSize(){
				return Xtwin.getStaticSize();
			}
			@Override
			public double getSpeed(){
				return Xtwin.getStaticSpeed();
			}
		},
		TIGHTFIGHTER{
			@Override
			public Ship create(Point2D.Double loc, Planet destination, Player owner){
				return new TightFighter(loc,destination,owner);
			}
			@Override
			public String toString(){
				return "Tight Fighter";
			}
			@Override
			public double getSize(){
				return TightFighter.getStaticSize();
			}
			@Override
			public double getSpeed(){
				return TightFighter.getStaticSpeed();
			}
		},
		FLYINGSAUCER{
			@Override
			public Ship create(Point2D.Double loc, Planet destination, Player owner){
				return new FlyingSaucer(loc,destination,owner);
			}
			@Override
			public String toString(){
				return "Flying Saucer";
			}
			@Override
			public double getSize(){
				return FlyingSaucer.getStaticSize();
			}
			@Override
			public double getSpeed(){
				return FlyingSaucer.getStaticSpeed();
			}
		}
		;
		public abstract Ship create(Point2D.Double loc, Planet destination, Player owner);
		public abstract String toString();
		public abstract double getSize();
		public abstract double getSpeed();
	}
	
	public static Ship createShip(ShipType shipType, Point2D.Double loc, Planet destination, Player owner){
		return shipType.create(loc,destination,owner);
	}
	
	public static int getBiggestShipSize() {
		int maximum = 0;
		for(ShipType shipType: ShipType.values()) {
			if(maximum<shipType.getSize()) {
				maximum = (int)shipType.getSize();
			}
		}
		return maximum;
	}
}
