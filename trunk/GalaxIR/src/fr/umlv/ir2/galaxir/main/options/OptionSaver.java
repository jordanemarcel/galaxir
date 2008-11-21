package fr.umlv.ir2.galaxir.main.options;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.umlv.ir2.galaxir.core.GameCore;
import fr.umlv.ir2.galaxir.core.MapManager;
import fr.umlv.ir2.galaxir.core.Player.PlayerType;
import fr.umlv.ir2.galaxir.items.Planet;
import fr.umlv.ir2.galaxir.utils.ColorTools;

public class OptionSaver {

	private static class PlayerInfo {
		private final String name;
		private final Color color;
		private final PlayerType playerType;

		public PlayerInfo(String name, Color color, PlayerType playerType) {
			this.name = name;
			this.color = color;
			this.playerType = playerType;
		}

		public String getName() {
			return name;
		}

		public Color getColor() {
			return color;
		}

		public PlayerType getPlayerType() {
			return playerType;
		}
	}

	private int width;
	private int height;
	private int neutralPlanet;
	private int computerPlayerNumber;
	private int computerPlayerID;
	private ArrayList<PlayerInfo> playerList;
	private MapManager mapManager;

	public OptionSaver() {
		this.width = 640;
		this.height = 480;
		this.neutralPlanet = 10;
		this.computerPlayerNumber = 0;
		this.computerPlayerID = 1;
		this.playerList = new ArrayList<PlayerInfo>();
		this.mapManager = null;
	}

	public void setNeutralPlanet(int number) {
		if(number<0)
			throw new IllegalArgumentException();
		neutralPlanet = number;
	}

	public void setScreenDimension(int width, int height) {
		if(width<0)
			throw new IllegalArgumentException();
		if(height<0)
			throw new IllegalArgumentException();
		this.width = width;
		this.height = height;
	}

	public void addPlayer(String name, Color color, PlayerType playerType) {
		playerList.add(new PlayerInfo(name, color, playerType));
	}

	public void setComputerPlayerNumber(int number) {
		this.computerPlayerNumber = number;
	}

	public void setLoadingMap(MapManager mapManager) {
		this.mapManager = mapManager;
	}

	public void update(GameCore gameCore) {
		if(mapManager!=null) {
			width = mapManager.getWidth();
			height = mapManager.getHeight();
		}
		gameCore.setWidth(width);
		gameCore.setHeight(height);
		
		for(int i=0;i<computerPlayerNumber;i++) {
			playerList.add(new PlayerInfo("Player"+computerPlayerID, ColorTools.generateNotBlackRandomColor(),PlayerType.COMPUTER));
			computerPlayerID++;
		}
		
		if(mapManager==null) {
			gameCore.setNeutralPlanet(neutralPlanet);
			for(PlayerInfo playerInfo: playerList) {
				gameCore.addPlayer(playerInfo.getName(), playerInfo.getColor(), playerInfo.getPlayerType());
			}
		} else {
			gameCore.setNeutralPlanet(0);
			List<Planet> planetList = mapManager.getPlanetList();
			Iterator<PlayerInfo> playerInfoIterator = playerList.iterator();
			for(Planet planet: planetList) {
				if(planet.getOwner()!=null) {
					if(playerInfoIterator.hasNext()) {
						PlayerInfo pi = playerInfoIterator.next();
						gameCore.addPlayer(pi.getName(), pi.getColor(), pi.getPlayerType(), planet);
					} else {
						gameCore.addPlayer("Player"+computerPlayerID, ColorTools.generateNotBlackRandomColor(),PlayerType.COMPUTER, planet);
						computerPlayerID++;
					}
				} else
					gameCore.addPlanet(planet);
			}
		}
	}	
}
