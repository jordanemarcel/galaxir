package fr.umlv.galaxir.testClement;


import java.util.ArrayList;

import fr.umlv.remix.TimerRunnable;
import fr.umlv.remix.TimerTask;

public class SquadronUnleasherTimer implements TimerRunnable {
	private SquadronUnleasher su;
	
	public SquadronUnleasherTimer(ArrayList<GalaxyItem> itemList, Squadron squadron, int numberOfShip) {
		this.su = new SquadronUnleasher(numberOfShip, squadron, itemList);
	}
	
	public void run(TimerTask timerTask) {
		su.run(timerTask);
	}

}
