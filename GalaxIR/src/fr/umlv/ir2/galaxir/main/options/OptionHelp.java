package fr.umlv.ir2.galaxir.main.options;

public class OptionHelp implements Options {

	@Override
	public int run(int index, String[] args, OptionSaver optionSaver) {
		System.out.println("This is the help menu.");
		return 0;
	}

}
