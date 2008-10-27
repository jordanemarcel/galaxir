package fr.umlv.ir2.galaxir;

import fr.umlv.remix.TimerRunnable;
import fr.umlv.remix.TimerTask;

public class SquadronUnleasherTimer implements TimerRunnable {
	private SquadronUnleasher su;
	
	public SquadronUnleasherTimer(SquadronUnleasher su) {
		this.su = su;
	}
	
	public void run(TimerTask timerTask) {
		su.run(timerTask);
	}

}
