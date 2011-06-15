package layer3;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

public class IMDBThingy {
	
	private static final String FILESER = "movies.ser";
	private static final String FOLDER = "/home/hkh/test/";

	private DataBase aDB = null;
	private IMDBAPIManager aIMDBAPIManger = null;		
	
	public IMDBThingy(){
		this.setDB(new DataBase());
		this.setIMDBAPIManger(IMDBAPIManager.getInstance());
	}
	
	public void calcDB() {
		// serialized LinkedList
		try {
			Object aSerializedObject = FasadeLayer3.readSerializedObject(FOLDER + FILESER + "");
			LinkedList<Entry> serializedEntries = (LinkedList<Entry>) aSerializedObject;
			for ( Entry e : serializedEntries ) {
				this.getDB().addOne(e);
			}
		} catch (Exception e) {
			FasadeLayer3.log(e.getClass() + " " + e.getMessage());
		}
		
		File[] files = FasadeLayer3.getFolderMovies(FOLDER);
		for ( File file : files ) {
			// TODO: Log if no files found, sanity check, maybe wrong folder
			String[] aID_aLanguage = file.getName().split("\\.");
			String aID = aID_aLanguage[0];
			String aLanguage = aID_aLanguage[1];
			Entry aEntry = new Entry(aID, aLanguage);
			// TODO: more elegancy, puh-lease!
			if (aEntry.getLanguageV().equals(Boolean.toString(Boolean.TRUE))){
				this.getDB().addTwo(aEntry);
			} else {
				this.getDB().addThree(aEntry);
			}
		}
	}

	public void checkDB() {
		// remove all videos, that have been ser, but do not exist anymore
		this.getDB().removeMoviesNotExitantAnymore();
		// remove all videos, that are already ser
		this.getDB().removeAlreadySerializedMovies();
	}

	private DataBase getDB() {
		return this.aDB;
	}
	
	private IMDBAPIManager getIMDBAPIManager() {
		return this.aIMDBAPIManger;
	}

	public void saveSerializedDB() {
		try {
			this.aDB.serializeScrapedMovies(FOLDER + FILESER + "");
		} catch (Exception e) {
			FasadeLayer3.log(e.getMessage());
		}	
	}
	
	public void scrapeLocalDB() {
		Iterator<Entry> iterator = this.getDB().getOneIterator();
		while (iterator.hasNext()){
			Entry aAbstractEntry = iterator.next();
			Entry aLocalEntry = this.getIMDBAPIManager().scrape(aAbstractEntry);
			this.getDB().addOne(aLocalEntry);
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

	@Override
	public String toString(){
		return this.getDB().toString();
	}
}