package fr.umlv.ir2.galaxir;


import java.util.ArrayList;
import java.util.Iterator;

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
		humanPlayer.setPercentage(humanPlayer.getPercentage() - arg2*5);
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
	public void mouseDragging(ArrayList<GalaxyItem> itemsDragging,
			KeyPress key) {

	}

	@Override
	public void mouseDrop(ArrayList<GalaxyItem> itemsDrop, KeyPress key) {
		if(humanPlayer==null)
			return;
		for(GalaxyItem testItem : itemsDrop) {
			if(testItem instanceof Planet) {
				Planet p = (Planet)testItem;
				humanPlayer.launchShip(p);
			}
		}    
	}

	@Override
	public void mouseOver(ArrayList<GalaxyItem> itemsOver, KeyPress key) {
		for(GalaxyItem gi: overList) {
			if(gi instanceof Ship) {
				if(!itemsOver.contains(gi)) {
					Ship s = (Ship)gi;
					s.setEndOver();
				}
			}
			if(gi instanceof Planet) {
				if(!itemsOver.contains(gi)) {
					Planet p = (Planet)gi;
					p.setEndOver();
					humanPlayer.setOveredPlanet(null);
				}
			}
		}
		overList.clear();
		if(!itemsOver.isEmpty()){
			for(GalaxyItem gi: itemsOver) {
				if(gi instanceof Ship) {
					overList.add(gi);
					Ship s = (Ship)gi;
					s.setOver();
				}
				if(gi instanceof Planet) {
					overList.add(gi);
					Planet p = (Planet)gi;
					p.setOver();
					humanPlayer.setOveredPlanet(p);
				}
			}
		}

	}
}
