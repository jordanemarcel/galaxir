package fr.umlv.ir2.galaxir.core;

import fr.umlv.ir2.galaxir.items.GalaxyItem;
import fr.umlv.remix.Arena;
import fr.umlv.remix.TimerRunnable;
import fr.umlv.remix.TimerTask;

public class RefreshTimer implements TimerRunnable {
	final Arena<GalaxyItem> arena;
	
	public RefreshTimer(Arena<GalaxyItem> arena) {
		this.arena = arena;
	}
	
	public void run(TimerTask timerTask) {
        arena.refresh();
    }
}