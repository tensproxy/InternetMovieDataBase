package test;

import org.junit.Test;

import layer3.IMDBThingy;

public class TestIMDBThingy {
	
	@Test
	public void testing() {
		if (IMDBThingy.isFunctional()) {
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
}