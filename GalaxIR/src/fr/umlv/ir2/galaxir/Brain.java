package fr.umlv.ir2.galaxir;

import java.util.ArrayList;
import java.util.Random;

public class Brain {
	private ArrayList<GalaxyItem> itemList;
	private Player player;
	
	public Brain(ArrayList<GalaxyItem> itemList, Player p) {
		this.itemList = itemList;
		this.player = p;
	}
	
	public void run() {
		int min = Integer.MAX_VALUE;
		Planet thisOne = null;
			for(GalaxyItem g: itemList) {
				if(g instanceof Planet) {
					Planet p = (Planet)g;
					if(p.getNbShip()<min && p.getOwner()!=player) {
						min = p.getNbShip();
						thisOne = p;
					}
				}
			}
		for(GalaxyItem g: itemList) {
			if(g instanceof Planet) {
				Planet p = (Planet)g;
				if(p.getOwner()==player) {
					p.moveShipTowards(thisOne, 50, itemList);
				}
			}
			if(g instanceof Ship) {
				Ship s = (Ship)g;
				if(s.getOwner()==player) {
					s.moveShipTowards(thisOne);
				}
			}
		}
	}
}
