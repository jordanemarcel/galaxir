package fr.umlv.ir2.galaxir.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import fr.umlv.ir2.galaxir.ai.AITimer;
import fr.umlv.ir2.galaxir.core.Player.PlayerType;
import fr.umlv.ir2.galaxir.input.KeyManager;
import fr.umlv.ir2.galaxir.input.MouseManager;
import fr.umlv.ir2.galaxir.items.Background;
import fr.umlv.ir2.galaxir.items.GalaxyItem;
import fr.umlv.ir2.galaxir.items.Planet;
import fr.umlv.ir2.galaxir.items.StatusBar;
import fr.umlv.ir2.galaxir.items.ship.ShipFactory;
import fr.umlv.ir2.galaxir.items.ship.ShipMoverTimer;
import fr.umlv.remix.Application;
import fr.umlv.remix.ApplicationRunnable;
import fr.umlv.remix.Arena;
import fr.umlv.remix.KeyHandler;
import fr.umlv.remix.MouseHandler;

public class GameCore implements ApplicationRunnable<GalaxyItem> {
	private int width;
	private int height;
	private int minimumPlanetSize = 30;
	private int maximumPlanetSize = 90;
	private int playerPlanetShipNumber = 100;
	private int playerPlanetSize = 100;
	private int playerPlanetGrowth = 100;
	private AuthoritativeItemManager authoritativeItemManager;
	private int neutralPlanet = 20;
	ArrayList<Player> playerList = new ArrayList<Player>();
	
	public GameCore(ArrayList<GalaxyItem> galaxyItem) {
		createGameCore(640,480,galaxyItem);
	}
	
	public GameCore(int width, int height, ArrayList<GalaxyItem> galaxyItem) {
		createGameCore(width,height,galaxyItem);
	}
	
	private void createGameCore(int width, int height, ArrayList<GalaxyItem> galaxyItem) {
		this.width = width;
		this.height = height;
		this.authoritativeItemManager = new AuthoritativeItemManager(galaxyItem);
		this.authoritativeItemManager.addBackground(new Background(width, height, Color.black));
	}
	
	public void setPlanetSizeLimits(int minimum, int maximum) {
		if(minimum>maximum)
			throw new IllegalArgumentException("minimum can't be superior than maximum.");
		this.minimumPlanetSize = minimum;
		this.maximumPlanetSize = maximum;
	}
	
	public void setBackground(Color color) {
		authoritativeItemManager.setBackground(color);
	}
	
	public void setPlayerPlanetShipNumber(int shipNumber) {
		this.playerPlanetShipNumber = shipNumber;
	}
	
	public void setPlayerPlanetGrowth(int planetGrowth) {
		this.playerPlanetGrowth = planetGrowth;
	}
	
	public void setPlayerPlanetSize(int planetSize) {
		this.playerPlanetSize = planetSize;
	}
	
	public void setNeutralPlanet(int number) {
		this.neutralPlanet = number;
	}
	
	private void addStatusBar(Player humanPlayer) {
		StatusBar statusBar = new StatusBar(width, height, humanPlayer);
		authoritativeItemManager.addStatusBar(statusBar);
	}
	
	public void addPlayer(String playerName, Color playerColor, PlayerType playerType) {
		Player player = new Player(playerName,playerColor,playerType,authoritativeItemManager);
		try {
			Point2D planetLocation = getEmptyPlanetLocation(playerPlanetSize);
			Planet planet = new Planet(playerPlanetShipNumber, playerPlanetGrowth, playerPlanetSize, planetLocation, player);
			authoritativeItemManager.addPlanet(planet);
			playerList.add(player);
		} catch (IllegalStateException ise) {
			
		}
	}
	
	private void addNeutralPlanets(int planetNumber) {
		Random r = new Random();
		for(int i=0;i<planetNumber;i++) {
			try {
				int width = minimumPlanetSize + r.nextInt(maximumPlanetSize-minimumPlanetSize);
				Point2D planetLocation = getEmptyPlanetLocation(width);
				int startingPopulation = r.nextInt(100);
				int planetGrowth = width + r.nextInt(40) - 20;
				Planet planet = new Planet(startingPopulation, planetGrowth, width, planetLocation, null);
				authoritativeItemManager.addPlanet(planet);
			} catch (IllegalStateException ise) {
				
			}
		}
	}
	
	public Point2D getEmptyPlanetLocation(int planetSize) {
		Random random = new Random();
		int minimumX = planetSize/2;
		int minimumY = planetSize/2;
		int maximumX = width - planetSize;
		int maximumY = height - planetSize;
		boolean intersect;
		int x;
		int y;
		int maximumTry = 100;
		int biggestShipSize = ShipFactory.getBiggestShipSize();
		do {
			x = random.nextInt(maximumX) + minimumX;
			y = random.nextInt(maximumY) + minimumY;
			intersect = false;
			Iterator<Planet> planetIterator = authoritativeItemManager.planetIterator();
			while(planetIterator.hasNext()) {
				Planet planet = planetIterator.next();
				if(!intersect)
					intersect = planet.intersects(new Point(x, y), planetSize+biggestShipSize*3);
				else
					break;
			}
			maximumTry--;
		} while (intersect && maximumTry >0);
		if(maximumTry==0)
			throw new IllegalStateException("Not enough space on the map to put a planet.");
		return new Point(x, y);
	}
	
	/*
	 * This will be the instructions launch by the run method of Application
	 * after creating the Arena arg0
	 */
	@Override
	public void run(final Arena<GalaxyItem> arena,final Collection<? extends GalaxyItem> itemList) {
		Player humanPlayer = null;
		for(Player player: playerList) {
			if(player.getPlayerType()==PlayerType.HUMAN) {
				humanPlayer = player;
				addStatusBar(humanPlayer);
				break;
			}
		}

		final JFrame frame = new JFrame("~ GaLaxIR ~");
	
		MouseHandler<GalaxyItem> mouseHandler = new MouseManager(authoritativeItemManager, humanPlayer);
		final KeyHandler keyHandler = new KeyManager(frame);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(arena.createComponent(width, height+20, mouseHandler, keyHandler), BorderLayout.SOUTH);
		frame.setResizable(false);
		
		addNeutralPlanets(neutralPlanet);
		
		frame.pack();
		arena.refresh();
		Application.timer(10, new RefreshTimer(arena));
		Application.timer(40, new ShipMoverTimer(authoritativeItemManager));
		Application.timer(500, new ProductionTurnTimer(authoritativeItemManager));
		Application.timer(1000, new GameStateTimer(authoritativeItemManager));
		for(Player player: playerList) {
			if(player.getPlayerType()==PlayerType.COMPUTER)
				Application.timer(1000, new AITimer(authoritativeItemManager, player));
		}
		frame.setVisible(true);
	}
}
