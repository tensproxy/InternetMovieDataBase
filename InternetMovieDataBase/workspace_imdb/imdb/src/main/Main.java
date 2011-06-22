package main;

import layer3.IMDBThingy;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IMDBThingy aT = IMDBThingy.getInstance();
		aT.calcDB();		
		aT.checkDB();
		aT.scrapeDB();		
		aT.writeSER();
		aT.writeTXT();		
		aT.writeCSV();
		aT.writeHTML();
	}
}