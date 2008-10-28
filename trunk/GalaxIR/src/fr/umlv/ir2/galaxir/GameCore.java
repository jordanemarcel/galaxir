package fr.umlv.ir2.galaxir;

import java.util.Collection;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import fr.umlv.remix.Application;
import fr.umlv.remix.ApplicationRunnable;
import fr.umlv.remix.Arena;
import fr.umlv.remix.KeyHandler;
import fr.umlv.remix.MouseHandler;

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
		
		/*
		 * We build the graphical interface by adding the graphical component
		 * corresponding to the Arena - by calling createComponent - to a
		 * JFrame.
		 */
		
		final JFrame frame = new JFrame("~ GaLaxIR ~");
		MainFrame m = new MainFrame("~ GaLaxIR ~",arena);
		
		/*
		 * This is our KeyHandler that will be called by the Arena in case of
		 * key events
		 */
		MouseHandler<GalaxyItem> mouseHandler = new MouseManager();
		final KeyHandler keyHandler = new KeyManager(frame);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(arena.createComponent(640, 500, mouseHandler, keyHandler));
		frame.pack();
		//frame.setVisible(true);
		/*
		 * We initially draw the component
		 */
		arena.refresh();

		/*
		 * We ask the Application to call the following run function every
		 * seconds. This method just refresh the component.
		 */
		Application.timer(10, new RefreshTimer(arena));
		Application.timer(40, new ShipMoverTimer(itemList));
		Application.timer(500, new ProductionTurnTimer(itemList));
	}
}
