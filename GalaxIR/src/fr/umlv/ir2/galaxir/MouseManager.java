package fr.umlv.ir2.galaxir;


import java.util.ArrayList;
import fr.umlv.remix.KeyPress;

public class MouseManager implements fr.umlv.remix.MouseHandler<GalaxyItem> {
	ArrayList<GalaxyItem> dragList;
	
	@Override
	public void mouseClicked(ArrayList<GalaxyItem> arg0,KeyPress arg1) {
		//System.out.println("Select " + arg0);
		for (GalaxyItem testItem : arg0) {
			testItem.selected(Player.getHumanPlayer());
		}
		//SoundEffect.playExplosion();
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
	public void mouseDrag(ArrayList<GalaxyItem> itemsDrag,
			KeyPress key) {
		dragList=itemsDrag;
		System.out.println("Drag :"+dragList);

	}
	@Override
	public void mouseDragging(ArrayList<GalaxyItem> itemsDragging,
			KeyPress key) {
		if(!itemsDragging.isEmpty())
			System.out.println("Dragging :"+itemsDragging);

	}

	@Override
	public void mouseDrop(ArrayList<GalaxyItem> itemsDrop, KeyPress key) {
		System.out.println("Drag& Drop :"+dragList+" => "+itemsDrop + " using "+key.toString());            
	}

	@Override
	public void mouseOver(ArrayList<GalaxyItem> itemsOver, KeyPress key) {
		if(!itemsOver.isEmpty()){
			//System.out.println("Over :"+itemsOver);
		}
	}
}
