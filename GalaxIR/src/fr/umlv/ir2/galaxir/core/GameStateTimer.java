package fr.umlv.ir2.galaxir.core;

import java.util.ArrayList;

import fr.umlv.remix.TimerRunnable;
import fr.umlv.remix.TimerTask;

public class GameStateTimer implements TimerRunnable {
	
		private final GameState gameState;
		
		public GameStateTimer(AuthoritativeItemManager authoritativeItemManager) {
			this.gameState= new GameState(authoritativeItemManager);
		}
		
		public void run(TimerTask timerTask) {
			gameState.run(timerTask);
	    }
}
