package fr.umlv.galaxir.testClement;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import fr.umlv.remix.Application;
import fr.umlv.remix.ApplicationRunnable;
import fr.umlv.remix.Arena;
import fr.umlv.remix.KeyHandler;
import fr.umlv.remix.MouseHandler;
import fr.umlv.remix.TimerRunnable;
import fr.umlv.remix.TimerTask;


public class GameCore implements ApplicationRunnable<GalaxyItem> {
	/*
	 * This will be the instructions launch by the run method of Application
	 * after creating the Arena arg0
	 */
	@Override
	public void run(final Arena<GalaxyItem> arena,final Collection<? extends GalaxyItem> itemList) {
		/*
		 * This is our MouseHandler that will be called by the Arena in case of
		 * mouse events
		 */
		MouseHandler<GalaxyItem> mouseHandler = new MouseManager();
		/*
		 * We build the graphical interface by adding the graphical component
		 * corresponding to the Arena - by calling createComponent - to a
		 * JFrame.
		 */
		final JFrame frame = new JFrame("Test Arena");
		/*
		 * This is our KeyHandler that will be called by the Arena in case of
		 * key events
		 */
		final KeyHandler keyHandler = new KeyManager(frame);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(arena.createComponent(640, 480, mouseHandler, keyHandler));
		frame.pack();
		frame.setVisible(true);
		/*
		 * We initially draw the component
		 */
		arena.refresh();

		/*
		 * We ask the Application to call the following run function every
		 * seconds. This method just refresh the component.
		 */
		Application.timer(50, new TimerRunnable() {
			public void run(TimerTask timerTask) {
				arena.refresh();
			}
		});
	}
}
