package fr.umlv.ir2.galaxir;

import fr.umlv.remix.TimerRunnable;
import fr.umlv.remix.TimerTask;

public class AITimer implements TimerRunnable {
	private Brain brain;
	
	public AITimer(AuthoritativeItemManager authoritativeItemManager, Player p) {
		this.brain = new Brain(authoritativeItemManager, p);
	}
	
	public void run(TimerTask timerTask) {
		brain.run();
	}
}
