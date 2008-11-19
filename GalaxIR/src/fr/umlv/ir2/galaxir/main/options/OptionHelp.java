package fr.umlv.ir2.galaxir.main.options;

import fr.umlv.ir2.galaxir.core.GameCore;

public class OptionHelp implements Options {

	@Override
	public int run(int index, String[] args, GameCore gameCore) {
		System.out.println("This is the help menu.");
		return 0;
	}

}
