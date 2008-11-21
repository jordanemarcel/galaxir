package fr.umlv.ir2.galaxir.ai;

import fr.umlv.ir2.galaxir.core.AuthoritativeItemManager;
import fr.umlv.ir2.galaxir.core.Player;
import fr.umlv.remix.TimerRunnable;
import fr.umlv.remix.TimerTask;

/**
 * The AITimer class is a timer implementing {@link TimerRunnable}.
 * It asks periodically to a {@link Brain} object to play as a player.
 * @author Clément LEBRETON, Jordane MARCEL
 *
 */
public class AITimer implements TimerRunnable {
	private Brain brain;
	
	/**
	 * Unique constructor for {@link AITimer}.
	 * Construct a {@link Brain} object.
	 * @param authoritativeItemManager (Item Handler)
	 * @param player (Player played by the AI)
	 */
	public AITimer(AuthoritativeItemManager authoritativeItemManager, Player player) {
		this.brain = new Brain(authoritativeItemManager, player);
	}
	
	/**
	 * Call the run method on a {@link Brain} object periodically.
	 */
	@Override
	public void run(TimerTask timerTask) {
		this.brain.run(timerTask);
	}
}
