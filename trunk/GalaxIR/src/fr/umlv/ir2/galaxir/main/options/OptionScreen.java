package fr.umlv.ir2.galaxir.main.options;

public class OptionScreen implements Options {

	@Override
	public int run(int index, String[] args, OptionSaver optionSaver) {
		if(!(index+2<args.length))
			throw new IllegalArgumentException();
		
		Integer width = Integer.decode(args[index+1]);
		if(width==null)
			throw new IllegalArgumentException();
		Integer height = Integer.decode(args[index+2]);
		if(height==null)
			throw new IllegalArgumentException();
		
		optionSaver.setScreenDimension(width, height);
		return 2;
	}

}
