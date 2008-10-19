package fr.umlv.ir2.galaxir;
import java.util.ArrayList;





public class Trigo {
	private static final ArrayList<Double> cos = new ArrayList<Double>();
	private static final ArrayList<Double> sin = new ArrayList<Double>();
	static {
		for (int i = 0; i <= 360; i++){
			cos.add(i, Math.cos(i));
			sin.add(i, Math.sin(i));
		}
	}
	public static double cos(int angle){
		return cos.get(angle);
	}
	public static double sin(int angle){
		return sin.get(angle);
	}
}
