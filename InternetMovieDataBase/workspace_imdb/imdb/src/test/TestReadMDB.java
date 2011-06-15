package test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import layer3.IMDBThingy;

public class TestReadMDB {
	
	@Test
	public void testing() throws FileNotFoundException, IOException, ClassNotFoundException{
		IMDBThingy aT = new IMDBThingy();
		System.out.println("calcDB");
		aT.calcDB();
		System.out.println("checkDB");
		aT.checkDB();
		System.out.println("scrapeLocalDB");
		aT.scrapeLocalDB();
		System.out.println("saveSerializedDB");
		aT.saveSerializedDB();
		System.out.println("writeTXT");
		aT.writeTXT();

	}
}