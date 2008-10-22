package fr.umlv.galaxir.testClement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import fr.umlv.remix.Application;
import fr.umlv.remix.ApplicationRunnable;
import fr.umlv.remix.Arena;
import fr.umlv.remix.KeyHandler;
import fr.umlv.remix.MouseHandler;


public class GameCore implements ApplicationRunnable<GalaxyItem> {
	enum FileMenuOption{
		NEW{
			@Override
			public String getName(){
				return "new random map";
			}
			@Override
			public void performed(){
				
			}
		},
		LOAD{
			@Override
			public String getName(){
				return "load map";
			}
			@Override
			public void performed(){
				JFileChooser fileChooser = new JFileChooser();
				int ret = fileChooser.showOpenDialog(new JLabel("plop"));
				if (ret != JFileChooser.APPROVE_OPTION) {
				}
				File file = fileChooser.getSelectedFile();
				try {
					MapManager.load(file);
				} catch (IOException e) {
					JDialog popup = new JDialog();
					popup.setSize(300, 150);
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			        
			        Dimension frameSize = popup.getSize();
			        if (frameSize.height > screenSize.height) {
			            frameSize.height = screenSize.height; 
			        }
			        if (frameSize.width > screenSize.width) {
			            frameSize.width = screenSize.width;
			        }
			        popup.setLocation((screenSize.width - frameSize.width) / 2,
			                          (screenSize.height - frameSize.height) / 2);
					
					popup.add(new JLabel("This file can not be read or is not a valid file."));
					popup.setVisible(true);
					
				}
			}
		},
		SAVE{
			@Override
			public String getName(){
				return "save map";
			}
			@Override
			public void performed(){
				
			}
		},
		QUIT{
			@Override
			public String getName(){
				return "quit";
			}
			@Override
			public void performed(){
				System.exit(0);
			}
		};
		public abstract String getName();
		public abstract void performed();
		
	}
	
	private final JFrame frame; 
	
	public GameCore() {
		frame = new JFrame("~ GaLaxIR ~");	
	}
	@Override
	public void run(final Arena<GalaxyItem> arena,final Collection<? extends GalaxyItem> itemList) {
		/*
		 * This is our MouseHandler that will be called by the Arena in case of
		 * mouse events
		 */
		MouseHandler<GalaxyItem> mouseHandler = new MouseManager();
		/*
		 * Begin : Create the main frame 
		 */
		//final JFrame frame = new JFrame("~ GaLaxIR ~");	
		
		final KeyHandler keyHandler = new KeyManager(frame);
		JPanel globalePanel = new JPanel();
		globalePanel.setLayout(new BorderLayout());
		/*
		 * Begin : create the menu
		 */
		JMenuBar mainMenuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		class ItemListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent a) {
				String action = a.getActionCommand();
				for (FileMenuOption option : FileMenuOption.values()){
					if(action.equals(option.getName())){
						option.performed();
					}
				}
			}
			
		};
		ItemListener myListener = new ItemListener();
		for (FileMenuOption option : FileMenuOption.values()){
			JMenuItem item = new JMenuItem(option.getName());
			item.addActionListener(myListener);
			fileMenu.add(item);
		}

		mainMenuBar.add(fileMenu);
		globalePanel.add(mainMenuBar,BorderLayout.NORTH);
		/*
		 * End : menu done! and put into the frame
		 */
		globalePanel.add(arena.createComponent(640, 480, mouseHandler, keyHandler),BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		frame.add(globalePanel);
		frame.pack();
		frame.setVisible(true);
		/*
		 * End : frame done! and displayed on screen
		 */
		
		
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
	private void loadMap(){

	}
	private static void saveMap(){
		JFileChooser fileChooser = new JFileChooser();
		File file = fileChooser.getSelectedFile();
		MapManager.save(file, null);
	}
}
