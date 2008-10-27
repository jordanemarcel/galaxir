package fr.umlv.ir2.galaxir;

import java.util.ArrayList;
import java.util.Collection;

import fr.umlv.remix.TimerRunnable;
import fr.umlv.remix.TimerTask;

public class AITimer implements TimerRunnable {
	private Brain brain;
	
	public AITimer(ArrayList<GalaxyItem> itemList, Player p) {
		this.brain = new Brain(itemList, p);
	}
	
	public void run(TimerTask timerTask) {
		brain.run();
	}
}
