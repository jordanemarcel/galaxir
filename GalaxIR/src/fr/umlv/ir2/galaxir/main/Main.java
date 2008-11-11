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
		GameCore gameCore = new GameCore(640,480,galaxyItem);
		gameCore.setBackground(Color.black);
		gameCore.addPlayer("Jordane", Color.yellow, PlayerType.HUMAN);
		gameCore.addPlayer("Skynet", Color.red, PlayerType.COMPUTER);
		gameCore.addNeutralPlanets(5);
		Application.run(galaxyItem, new GalaxyItemManager(), gameCore);
	}
}
