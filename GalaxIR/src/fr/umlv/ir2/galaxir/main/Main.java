package fr.umlv.ir2.galaxir.main;

public class Main {

	public static void main(String[] args) {
		Menu menu = new Menu();
		menu.readArguments(args);
		menu.run();
	}
}
