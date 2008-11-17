package fr.umlv.ir2.galaxir.main;


import java.awt.Color;
import java.util.ArrayList;

import fr.umlv.ir2.galaxir.core.GalaxyItemManager;
import fr.umlv.ir2.galaxir.core.GameCore;
import fr.umlv.ir2.galaxir.core.Player.PlayerType;
import fr.umlv.ir2.galaxir.items.GalaxyItem;
import fr.umlv.remix.Application;


public class Main {

	public static void main(String[] args) {
		ArrayList<GalaxyItem> galaxyItem = new ArrayList<GalaxyItem>();
		GameCore gameCore = new GameCore(800,600,galaxyItem);
		gameCore.setBackground(Color.black);
		gameCore.addPlayer("Jordane", Color.yellow, PlayerType.COMPUTER);
		gameCore.addPlayer("Skynet", Color.red, PlayerType.COMPUTER);
		gameCore.addPlayer("BigBrother", Color.pink, PlayerType.COMPUTER);
		gameCore.addPlayer("Clement", Color.blue, PlayerType.COMPUTER);
		gameCore.addNeutralPlanets(20);
		Application.run(galaxyItem, new GalaxyItemManager(), gameCore);
	}
	
}
