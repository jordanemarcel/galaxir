package fr.umlv.ir2.galaxir.main.options;

import fr.umlv.ir2.galaxir.core.GameCore;

public class OptionPlanet implements Options {

	@Override
	public int run(int index, String[] args, GameCore gameCore) {
		if(!(index+1<args.length))
			throw new IllegalArgumentException();
		
		Integer number = Integer.decode(args[index+1]);
		if(number==null)
			throw new IllegalArgumentException();
		
		if(number<0)
			throw new IllegalArgumentException();
		gameCore.setNeutralPlanet(number);
		return 1;
	}

}
