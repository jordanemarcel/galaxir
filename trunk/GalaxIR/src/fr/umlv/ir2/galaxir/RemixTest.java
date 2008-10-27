package fr.umlv.ir2.galaxir;


import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import fr.umlv.remix.Application;


public class RemixTest {

	public static void main(String[] args) {

		ArrayList<GalaxyItem> testItemList = new ArrayList<GalaxyItem>();
		testItemList.add(new Background(640, 480, Color.black));
		testItemList.add(new StatusBar());
		Player p = new Player("Clement",Color.red,Color.pink,testItemList);
		Player p2 = new Player("Jordane",Color.blue,Color.cyan,testItemList);
		Player.setHumanPlayer(p);
		
		Random random = new Random();
		testItemList.add(new Planet(100,50,100,new Point(random.nextInt(640),random.nextInt(480)), p));
		testItemList.add(new Planet(100,50,100,new Point(random.nextInt(640),random.nextInt(480)), p2));
		for (int i = 1; i <= 10; i++) {
			testItemList.add(new Planet(testItemList));
		}

		GalaxyItemManager manager = new GalaxyItemManager();
		Application.run(testItemList, manager, new GameCore());
		Application.timer(100, new AITimer(testItemList, p2));
	}
}
