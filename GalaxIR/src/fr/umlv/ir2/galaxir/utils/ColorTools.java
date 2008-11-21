package fr.umlv.ir2.galaxir.utils;

import java.awt.Color;
import java.util.Random;

public class ColorTools {
	
	public static Color generateNotBlackRandomColor() {
		Random rand = new Random();
		Color color;
		do {
			int randomRGB = rand.nextInt(255*255*255);
			String randomColor = ""+randomRGB;
			color = Color.decode(randomColor);
		} while(getAverageRGB(color)<20);
		return color;
	}
	
	public static int getAverageRGB(Color color) {
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		int average = (red+green+blue)/3;
		return average;
	}
	
}
