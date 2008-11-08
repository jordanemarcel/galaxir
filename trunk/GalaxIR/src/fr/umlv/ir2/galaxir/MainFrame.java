package fr.umlv.ir2.galaxir;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import fr.umlv.remix.Arena;
import fr.umlv.remix.KeyHandler;

public class MainFrame{
	
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
	
	private String name;
	private Arena<GalaxyItem> arena;
	private final JPanel globalePanel;
	private final JFrame frame;
	
	
	public MainFrame(String name, Arena<GalaxyItem> arena, Player humanPlayer){
		globalePanel = new JPanel();
		globalePanel.setLayout(new BorderLayout());
		frame = new JFrame(name);
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
		//MouseHandler<GalaxyItem> mouseHandler = new MouseManager(humanPlayer);
		final KeyHandler keyHandler = new KeyManager(frame);
		//globalePanel.add(arena.createComponent(640, 500, mouseHandler, keyHandler));
		/*
		 * End : menu done! and put into the frame
		 */
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(640,500);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height; 
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2,
                          (screenSize.height - frameSize.height) / 2);
        frame.add(globalePanel);
        frame.pack();
       
        frame.setVisible(true);
        //frame.setResizable(false);
	}
}
