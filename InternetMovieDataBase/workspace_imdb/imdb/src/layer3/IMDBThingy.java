package layer3;

import java.util.Iterator;

public class IMDBThingy {
	
	private static IMDBThingy SINGLETON = null;

	public static IMDBThingy getInstance(){
		if ( IMDBThingy.GETSINGLETON() == null ) {
			IMDBThingy.SETSINGLETON(new IMDBThingy());				
		}
		return IMDBThingy.GETSINGLETON();
	}
	
	private static IMDBThingy GETSINGLETON() {
		return IMDBThingy.SINGLETON;
	}

	public static boolean isFunctional() {
		boolean allGood = false;
		try {
			FL3.inf("Checking if writable", 0);
			if ( ! FL3.isWritable() ) {
				throw new Exception ("Not removable!");
			}
			FL3.inf("Checking if removable", 0);
			if ( ! FL3.isDeletable() ) {
//				throw new Exception ("Not removable!");
			}
			FL3.inf("Checking if online", 0);
			if ( ! FL3.isOnline() ) {
				throw new Exception ("Not online!");
			}		
			FL3.inf("All checks passed successfully", 0);
			allGood = true;
		} catch (Exception e) {
			FL3.log(e);
		}
		return allGood;
	}
	
	private static boolean isOnline() {
		if (FL3.isOnline()) return true; 
		else return false; 
	}
	
	private static IMDBThingy SETSINGLETON(IMDBThingy inIMDBThingy) {
		IMDBThingy.SINGLETON = inIMDBThingy;
		return IMDBThingy.GETSINGLETON();
	}
	
	private DataBase aDB = null;
	private IMDBAPIManager aIMDBAPIManger = null;
	private RepConManager aRepConManager = null;
	private int delDB = 0;
	private int filDB = 0;
	private int invDB = 0;
	private int serDB = 0;
	
	private IMDBThingy(){
		this.setDB(new DataBase());
		this.setIMDBAPIManger(IMDBAPIManager.getInstance());
		this.setRepConManager(RepConManager.getInstance());
	}

	public void calcDB() {
		FL3.inf("calcDB", 0);

		// add entries from serialized file
		serDB = this.getDB().newList(FL3.getSerializedEntries());
		FL3.inf("Amount of serialized movies: " + this.getDB().size(serDB), 1);
		
		// add entries from locale proxy file or local movie folder 
		filDB = this.getDB().newList();
		String[] aID_aLanguage = null;
		for ( String m : FL3.getMovieTuples() ) {
			aID_aLanguage = m.substring(m.lastIndexOf(System.getProperty("file.separator"))+1).split("\\.");
			Entry aEntry = new Entry(aID_aLanguage[0], aID_aLanguage[1]);
			this.getDB().addEntry(filDB, aEntry);
		}
		FL3.inf("Amount of filed IMDB-IDs: " + this.getDB().size(filDB), 1);
	}

	public void checkDB() {
		FL3.inf("checkDB", 0);
		
		invDB = this.getDB().newList();
		Iterator<Entry> iterator = null;
		// check valids in serDB
		iterator = this.getDB().iterator(serDB);
		while(iterator.hasNext()){
			Entry e = iterator.next();
			if (! e.isValid()){
				this.getDB().addEntry(invDB, e);
			}
		}
		this.getDB().trimAFromSameToBReturnTrimmedEntries(serDB, invDB);	// remove from list with serialized movies, movies that not valid
		// check valids in filDB
		iterator = this.getDB().iterator(filDB);
		while(iterator.hasNext()){
			Entry e = iterator.next();
			if (! e.isValid()){
				this.getDB().addEntry(invDB, e);
			}
		}
		this.getDB().trimAFromSameToBReturnTrimmedEntries(filDB, invDB);	// remove from list with filed movies, movies that not valid
		// trim serDB
		delDB = this.getDB().newList();
		this.getDB().addList(delDB, this.getDB().trimAFromDiffToBReturnTrimmedEntries(serDB, filDB));		// remove from list with serialized movies, movies that are not local anymore
		// trim filDB
		this.getDB().addList(delDB, this.getDB().trimAFromSameToBReturnTrimmedEntries(filDB, serDB));		// remove from list with local movies, movies that are already serialized

		FL3.inf("this.getDB().size(invDB): " + this.getDB().size(invDB), 1);
		FL3.inf("this.getDB().size(serDB): " + this.getDB().size(serDB), 1);
		FL3.inf("this.getDB().size(valDB): " + this.getDB().size(filDB), 1);
		FL3.inf("this.getDB().size(delDB): " + this.getDB().size(delDB), 1);
	}

	private DataBase getDB() {
		return this.aDB;
	}
	
	private IMDBAPIManager getIMDBAPIManager() {
		return this.aIMDBAPIManger;
	}
	
	private RepConManager getRepConManager() {
		return this.aRepConManager;
	}

	public void scrapeDB() {
		FL3.inf("scrapeDB", 0);

		if (IMDBThingy.isOnline()) {
			FL3.inf("Scrapping...", 1);
			Iterator<Entry> iterator = this.getDB().iterator(filDB);
			int max = this.getDB().size(filDB);
			int i = 0;
			while (iterator.hasNext()){
				FL3.inf( i + "/" + max, 1 );
				Entry aEntry = iterator.next();
				Entry aScrapedEntry = null;
				try {
					aScrapedEntry = this.getIMDBAPIManager().scrape(aEntry);
					this.getDB().addEntry(serDB, aScrapedEntry);
					++i;
				} catch (Exception e) {
					this.getDB().addEntry(invDB, aScrapedEntry);
				}
//				if (i > 0) {break;}
			}
			FL3.inf("Scrapped " + i + " IMDB-IDs", 1);
			// remove serialized, valid entries
			this.aDB.trimAFromSameToBReturnTrimmedEntries(filDB, serDB);			
		} else {
			FL3.inf("Not online. Scrapping not possible", 2);
		}
	}

	private DataBase setDB(DataBase inDataBase) {
		this.aDB = inDataBase;
		return this.getDB();
	}

	private IMDBAPIManager setIMDBAPIManger(IMDBAPIManager inIMDBAPIManager) {
		this.aIMDBAPIManger = inIMDBAPIManager;
		return this.getIMDBAPIManager();
	}
	
	private RepConManager setRepConManager(RepConManager inRepConManager){
		this.aRepConManager = inRepConManager;
		return this.getRepConManager();
	}
	
	@Override
	public String toString(){
		return this.getDB().toString();
	} 
	
	public void writeCSV() {
		FL3.inf("writeCSV", 0);
		FL3.writeFile(this.aDB.clone(serDB), this.aRepConManager.getRepCon(RepConManager.rep.csv));
	}

	public void writeHTML() {
		FL3.inf("writeHTML", 0);
		FL3.writeFile(this.aDB.clone(serDB), this.aRepConManager.getRepCon(RepConManager.rep.html));
	}

	public void writeSER() {
		FL3.inf("writeSER", 0);
		FL3.writeFile(this.aDB.clone(serDB), this.aRepConManager.getRepCon(RepConManager.rep.ser));
	}

	public void writeTXT() {
		FL3.inf("writeTXT", 0);
		FL3.writeFile(this.aDB.clone(serDB), this.aRepConManager.getRepCon(RepConManager.rep.txt));
	}
}