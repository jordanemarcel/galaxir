package fr.umlv.ir2.galaxir.main.options;

import java.awt.Color;
import java.util.HashMap;

import fr.umlv.ir2.galaxir.core.Player.PlayerType;
import fr.umlv.ir2.galaxir.utils.ColorTools;

public class OptionPlayer implements Options {
	private final HashMap<String, Color> colors = new HashMap<String, Color>();
	private final HashMap<String, PlayerType> playerType = new HashMap<String, PlayerType>();

	public OptionPlayer() {
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
		colors.put("random", ColorTools.generateNotBlackRandomColor());
		
		playerType.put("human", PlayerType.HUMAN);
		playerType.put("computer", PlayerType.COMPUTER);
	}

	@Override
	public int run(int index, String[] args, OptionSaver optionSaver) {
		if(!(index+3<args.length))
			throw new IllegalArgumentException();

		String name = args[index+1];
		
		PlayerType type = getPlayerType(args[index+3]);
		if(type==null)
			throw new IllegalArgumentException();
		
		Color color = getColor(args[index+2]);
		if(color==null) {
			try {
				color = Color.decode(args[index+2]);
				if(ColorTools.getAverageRGB(color)<20) {
					System.out.println("Color "+args[index+2]+" too dark.");
					color = ColorTools.generateNotBlackRandomColor();
					System.out.println("A random color has been generated.");
				}
			} catch(NumberFormatException nfe) {
				throw new IllegalArgumentException();
			}
		}
		optionSaver.addPlayer(name, color, type);
		return 3;
	}

	private PlayerType getPlayerType(String type) {
		return playerType.get(type);
	}

	private Color getColor(String color) {
		Color c = colors.get(color);
		colors.put("random", ColorTools.generateNotBlackRandomColor());
		return c;
	}

}
