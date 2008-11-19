package fr.umlv.ir2.galaxir.main.options;

import java.awt.Color;
import java.util.HashMap;
import java.util.Random;

import fr.umlv.ir2.galaxir.core.GameCore;
import fr.umlv.ir2.galaxir.core.Player.PlayerType;

public class OptionPlayer implements Options {
	private final HashMap<String, Color> colors = new HashMap<String, Color>();
	private final HashMap<String, PlayerType> playerType = new HashMap<String, PlayerType>();

	public OptionPlayer() {
		colors.put("black", Color.black);
		colors.put("yellow", Color.yellow);
		colors.put("blue", Color.blue);
		colors.put("cyan", Color.cyan);
		colors.put("darkGray", Color.darkGray);
		colors.put("gray", Color.gray);
		colors.put("green", Color.green);
		colors.put("lightGray", Color.lightGray);
		colors.put("magenta", Color.magenta);
		colors.put("orange", Color.orange);
		colors.put("pink", Color.pink);
		colors.put("red", Color.red);
		colors.put("white", Color.white);
		
		playerType.put("human", PlayerType.HUMAN);
		playerType.put("computer", PlayerType.COMPUTER);
	}

	@Override
	public int run(int index, String[] args, GameCore gameCore) {
		if(!(index+3<args.length))
			throw new IllegalArgumentException();

		String name = args[index+1];
		
		PlayerType type = getPlayerType(args[index+3]);
		if(type==null) {
			throw new IllegalArgumentException();
		}
		
		Color color = getColor(args[index+2]);
		if(color==null) {
			try {
				color = Color.decode(args[index+2]);
			} catch(NumberFormatException nfe) {
				if(!args[index+2].equals("random"))
					throw new IllegalArgumentException();
				Random rand = new Random();
				String randomColor = ""+rand.nextInt(255*255*255);
				color = Color.decode(randomColor);
			}
		}
		
		gameCore.addPlayer(name, color, type);
		return 3;
	}

	private PlayerType getPlayerType(String type) {
		return playerType.get(type);
	}

	private Color getColor(String color) {
		return colors.get(color);
	}

}
