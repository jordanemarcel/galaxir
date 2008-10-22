package fr.umlv.ir2.galaxir;


import java.util.ArrayList;
import fr.umlv.remix.KeyPress;

public class MouseManager implements fr.umlv.remix.MouseHandler<GalaxyItem> {
	ArrayList<GalaxyItem> overList = new ArrayList<GalaxyItem>();
	
	@Override
	public void mouseClicked(ArrayList<GalaxyItem> arg0,KeyPress arg1) {
		//System.out.println("Select " + arg0);
		for(GalaxyItem testItem : arg0) {
			//if(testItem instanceof Planet) {
				if(arg1.equals(KeyPress.CRTL)) {
					if(Player.getHumanPlayer().containsSelectedItem(testItem)) {
						testItem.unselectAndRemove(Player.getHumanPlayer());
					}
					else {
						testItem.selectAndAdd(Player.getHumanPlayer());
					}
				} else if(arg1.equals(KeyPress.SHIFT)) {
					if(testItem instanceof Planet) {
						Planet p = (Planet)testItem;
						Player.getHumanPlayer().launchShip(p);
					}
				}
				else {
					Player.getHumanPlayer().clearSelectedItem();
					testItem.selectAndAdd(Player.getHumanPlayer());
				}
		//	}
		}
	}        

	/*
	 * in case of mouse wheel move, we just print the set of TestItems
	 * covered by the mouse when it appears, the key that was eventually
	 * pressed among CTL, SHIFT, ALT-GR and the direction of the wheel
	 * move (-1 or +1).
	 */
	@Override
	public void mouseWheelMoved(ArrayList<GalaxyItem> arg0,	KeyPress arg1, int arg2) {
		System.out.println(arg0 + " using " + arg1.toString()
				+ " wheel rotate " + arg2);
	}

	@Override
	public void mouseDrag(ArrayList<GalaxyItem> itemsDrag, KeyPress key) {
		for(GalaxyItem testItem : itemsDrag) {
			if(testItem instanceof Planet) {
				if(!Player.getHumanPlayer().containsSelectedItem(testItem)) {
					testItem.selectAndAdd(Player.getHumanPlayer());
				}
			}
		}
	}
	
	@Override
	public void mouseDragging(ArrayList<GalaxyItem> itemsDragging,
			KeyPress key) {
		if(!itemsDragging.isEmpty())
			System.out.println("Dragging :"+itemsDragging);

	}

	@Override
	public void mouseDrop(ArrayList<GalaxyItem> itemsDrop, KeyPress key) {
		for(GalaxyItem testItem : itemsDrop) {
			if(testItem instanceof Planet) {
				Planet p = (Planet)testItem;
				Player.getHumanPlayer().launchShip(p);
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
		}
		overList.clear();
		if(!itemsOver.isEmpty()){
			for(GalaxyItem gi: itemsOver) {
				if(gi instanceof Ship) {
					overList.add(gi);
					Ship s = (Ship)gi;
					s.setOver();
				}
			}
		}
		
	}
}
