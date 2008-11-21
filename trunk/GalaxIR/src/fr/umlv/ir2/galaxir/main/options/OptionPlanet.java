package fr.umlv.ir2.galaxir.main.options;

public class OptionPlanet implements Options {

	@Override
	public int run(int index, String[] args, OptionSaver optionSaver) {
		if(!(index+1<args.length))
			throw new IllegalArgumentException();
		
		Integer number = Integer.decode(args[index+1]);
		if(number==null)
			throw new IllegalArgumentException();
		
		optionSaver.setNeutralPlanet(number);
		return 1;
	}

}
