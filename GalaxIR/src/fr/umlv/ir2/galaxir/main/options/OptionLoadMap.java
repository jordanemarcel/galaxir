package fr.umlv.ir2.galaxir.main.options;

import java.io.File;
import java.io.IOException;

import fr.umlv.ir2.galaxir.core.MapManager;

public class OptionLoadMap implements Options {

	@Override
	public int run(int index, String[] args, OptionSaver optionSaver) {
		if(!(index+1<args.length))
			throw new IllegalArgumentException();
		
		File file = new File(args[index+1]);
		MapManager mapManager = new MapManager();
		try {
			mapManager.load(file);
			optionSaver.setLoadingMap(mapManager);
		} catch(IOException ioe) {
			
		}
		return 1;
	}

}
