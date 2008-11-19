package fr.umlv.ir2.galaxir.main;

import java.util.ArrayList;
import java.util.HashMap;

import fr.umlv.ir2.galaxir.core.GalaxyItemManager;
import fr.umlv.ir2.galaxir.core.GameCore;
import fr.umlv.ir2.galaxir.items.GalaxyItem;
import fr.umlv.ir2.galaxir.main.options.OptionHelp;
import fr.umlv.ir2.galaxir.main.options.OptionPlanet;
import fr.umlv.ir2.galaxir.main.options.OptionPlayer;
import fr.umlv.ir2.galaxir.main.options.OptionScreen;
import fr.umlv.ir2.galaxir.main.options.Options;
import fr.umlv.remix.Application;

public class Menu {
	private final HashMap<String, Options> options;
	private final ArrayList<GalaxyItem> galaxyItem;
	private final GameCore gameCore;
	private final String[] args;
	
	public Menu(String[] args) {
		this.args = args;
		this.galaxyItem = new ArrayList<GalaxyItem>();
		Integer width = null;
		Integer height = null;
		for(int i=0;i<args.length;i++) {
			if(args[i].equals("--screen")) {
				if(i+2<args.length) {
					width = Integer.decode(args[i+1]);
					height = Integer.decode(args[i+2]);
				} else {
					throw new IllegalArgumentException();
				}
				
				if(width==null || height==null)
					throw new IllegalArgumentException();
				break;
			}
		}
		if(width==null)
			width = 800;
		if(height==null)
			height = 600;
		this.gameCore = new GameCore(width,height,galaxyItem);
		this.options = new HashMap<String, Options>();
		loadOptions();
		
	}
	
	private void loadOptions() {
		OptionHelp help = new OptionHelp();
		options.put("-h", help);
		options.put("--help", help);
		
		OptionScreen optionScreen = new OptionScreen();
		options.put("--screen", optionScreen);
		
		OptionPlayer optionPlayer = new OptionPlayer();
		options.put("-p", optionPlayer);
		options.put("--player", optionPlayer);
		
		OptionPlanet optionPlanet = new OptionPlanet();
		options.put("-t", optionPlanet);
		options.put("--planet", optionPlanet);
	}

	public void read() {
		for(int i=0;i<args.length;i++) {
			Options option = options.get(args[i]);
			if(option==null) {
				options.get("-h").run(i,args,null);
				System.exit(0);
			} else {
				int shift = option.run(i, args, gameCore);
				i += shift;
			}
		}
		Application.run(galaxyItem, new GalaxyItemManager(), gameCore);
	}
}
