package fr.umlv.ir2.galaxir.input;


import java.util.ArrayList;
import java.util.Iterator;

import fr.umlv.ir2.galaxir.core.AuthoritativeItemManager;
import fr.umlv.ir2.galaxir.core.Player;
import fr.umlv.ir2.galaxir.items.ClickableItem;
import fr.umlv.ir2.galaxir.items.GalaxyItem;
import fr.umlv.ir2.galaxir.items.Planet;
import fr.umlv.ir2.galaxir.items.ship.Ship;
import fr.umlv.remix.KeyPress;

public class MouseManager implements fr.umlv.remix.MouseHandler<GalaxyItem> {
	private AuthoritativeItemManager authoritativeItemManager;
	ArrayList<GalaxyItem> overList = new ArrayList<GalaxyItem>();
	private Player humanPlayer;

	public MouseManager(AuthoritativeItemManager authoritativeItemManager, Player humanPlayer) {
		this.authoritativeItemManager = authoritativeItemManager;
		this.humanPlayer = humanPlayer;
	}

	@Override
	public void mouseClicked(ArrayList<GalaxyItem> arg0,KeyPress arg1) {
		if(humanPlayer==null)
			return;
		Iterator<ClickableItem> iterator = authoritativeItemManager.clickableItemIterator(arg0);
		while(iterator.hasNext()) {
			ClickableItem item = iterator.next();
			if(arg1.equals(KeyPress.CRTL)) {
				if(humanPlayer.containsSelectedItem(item)) {
					item.unselectAndRemove(humanPlayer);
				}
				else {
					item.selectAndAdd(humanPlayer);
				}
			} else if(arg1.equals(KeyPress.SHIFT)) {
				humanPlayer.launchShip(item);
			}
			else {
				humanPlayer.clearSelectedItem();
				item.selectAndAdd(humanPlayer);
			}
		}
	}        

	@Override
	public void mouseWheelMoved(ArrayList<GalaxyItem> arg0,	KeyPress arg1, int arg2) {
		if(humanPlayer==null)
			return;
		if(arg1 == KeyPress.SHIFT) {
			Iterator<Planet> planetIterator = authoritativeItemManager.planetIterator(arg0);
			while(planetIterator.hasNext()) {
				Planet planet = planetIterator.next();
				if(planet.getOwner()==humanPlayer) {
					if(arg2<0)
						planet.nextCurrentShipType();
					else if(arg2>0)
						planet.previousCurrentShipType();
				}
			}
		} else {
			humanPlayer.setPercentage(humanPlayer.getPercentage() - arg2*5);
		}
	}

	@Override
	public void mouseDrag(ArrayList<GalaxyItem> itemsDrag, KeyPress key) {
		if(humanPlayer==null)
			return;
		Iterator<ClickableItem> iterator = authoritativeItemManager.clickableItemIterator(itemsDrag);
		while(iterator.hasNext()) {
			ClickableItem clickableItem = iterator.next();
			if(!humanPlayer.containsSelectedItem(clickableItem)) {
				clickableItem.selectAndAdd(humanPlayer);
			}
		}
	}

	@Override
	public void mouseDragging(ArrayList<GalaxyItem> itemsDragging, KeyPress key) {

	}

	@Override
	public void mouseDrop(ArrayList<GalaxyItem> itemsDrop, KeyPress key) {
		if(humanPlayer==null)
			return;
		Iterator<Planet> iterator = authoritativeItemManager.planetIterator(itemsDrop);
		while(iterator.hasNext()) {
			Planet planet = iterator.next();
			humanPlayer.launchShip(planet);
		}    
	}

	@Override
	public void mouseOver(ArrayList<GalaxyItem> itemsOver, KeyPress key) {
		if(humanPlayer==null)
			return;
		Iterator<Planet> planetIterator = authoritativeItemManager.planetIterator(overList);
		Iterator<Ship> shipIterator = authoritativeItemManager.shipIterator(overList);
		while(shipIterator.hasNext()) {
			Ship ship = shipIterator.next();
			if(!itemsOver.contains(ship)) {
				ship.setEndOver();
			}
		}
		while(planetIterator.hasNext()) {
			Planet planet = planetIterator.next();
			if(!itemsOver.contains(planet)) {
				planet.setEndOver();
				humanPlayer.setOveredPlanet(null);
			}
		}
		overList.clear();
		if(!itemsOver.isEmpty()){
			shipIterator = authoritativeItemManager.shipIterator(itemsOver);
			while(shipIterator.hasNext()) {
				Ship ship = shipIterator.next();
				overList.add(ship);
				ship.setOver();
			}
			planetIterator = authoritativeItemManager.planetIterator(itemsOver);
			while(planetIterator.hasNext()) {
				Planet planet = planetIterator.next();
				overList.add(planet);
				planet.setOver();
				humanPlayer.setOveredPlanet(planet);
			}
		}
	}
}
