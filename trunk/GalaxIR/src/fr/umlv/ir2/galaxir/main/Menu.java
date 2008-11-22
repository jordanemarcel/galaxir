package fr.umlv.ir2.galaxir.main;

import java.util.HashMap;
import java.util.LinkedList;

import fr.umlv.ir2.galaxir.core.GalaxyItemManager;
import fr.umlv.ir2.galaxir.core.GameCore;
import fr.umlv.ir2.galaxir.items.GalaxyItem;
import fr.umlv.ir2.galaxir.main.options.OptionComputerNumber;
import fr.umlv.ir2.galaxir.main.options.OptionHelp;
import fr.umlv.ir2.galaxir.main.options.OptionLoadMap;
import fr.umlv.ir2.galaxir.main.options.OptionPlanet;
import fr.umlv.ir2.galaxir.main.options.OptionPlayer;
import fr.umlv.ir2.galaxir.main.options.OptionSaver;
import fr.umlv.ir2.galaxir.main.options.OptionScreen;
import fr.umlv.ir2.galaxir.main.options.Options;
import fr.umlv.remix.Application;

public class Menu {
	private final HashMap<String, Options> options;
	private final GameCore gameCore;
	private final LinkedList<GalaxyItem> itemList;
	private final OptionSaver optionsSaver;

	public Menu() {
		this.itemList = new LinkedList<GalaxyItem>();
		this.gameCore = new GameCore(this.itemList);
		this.options = new HashMap<String, Options>();
		this.optionsSaver = new OptionSaver();
		loadOptions();
	}
	
	private void loadOptions() {
		OptionHelp help = new OptionHelp();
		options.put("-h", help);
		options.put("--help", help);
		
		OptionScreen optionScreen = new OptionScreen();
		options.put("-s", optionScreen);
		options.put("--screen", optionScreen);
		
		OptionPlayer optionPlayer = new OptionPlayer();
		options.put("-p", optionPlayer);
		options.put("--player", optionPlayer);
		
		OptionPlanet optionPlanet = new OptionPlanet();
		options.put("-t", optionPlanet);
		options.put("--planet", optionPlanet);
		
		OptionComputerNumber optionComputerNumber = new OptionComputerNumber();
		options.put("-c", optionComputerNumber);
		options.put("--computer", optionComputerNumber);
		
		OptionLoadMap optionLoadMap = new OptionLoadMap();
		options.put("-l", optionLoadMap);
		options.put("--load", optionLoadMap);
	}
	
	public void readArguments(String[] args) {
		for(int i=0;i<args.length;i++) {
			Options option = options.get(args[i]);
			if(option==null) {
				options.get("-h").run(i,args,null);
				throw new RuntimeException();
			} else {
				int shift = option.run(i, args, optionsSaver);
				i += shift;
			}
		}
	}
	
	public void run() {
		optionsSaver.update(gameCore);
		Application.run(this.itemList, new GalaxyItemManager(), this.gameCore);
	}
}
