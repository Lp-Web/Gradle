package fr.iut;

public class Convertion {

	public double unit_Convertion(String s) {
		if(s.equals("EUR-USD")) {
			return 1.29;
		}
		if(s.equals("USD-EUR")) {
			return 1/1.29;
		}
		return 0.0;
	}
	
}
