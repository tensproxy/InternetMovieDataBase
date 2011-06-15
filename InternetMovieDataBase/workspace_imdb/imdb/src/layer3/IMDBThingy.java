package layer3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class IMDBThingy {
	
	private static final String FILESER = "/home/hkh/movies.ser";
	private static final String FOLDER = "/media/one/media.video/com.imdb.www/";
	private static final String FILES = "/home/hkh/files.txt";

	private DataBase aDB = null;
	private IMDBAPIManager aIMDBAPIManger = null;		
	
	public IMDBThingy(){
		this.setDB(new DataBase());
		this.setIMDBAPIManger(IMDBAPIManager.getInstance());
	}
	
	public void calcDB() {
		// serialized LinkedList
		try {
			Object aSerializedObject = FasadeLayer3.readSerializedObject(FILESER);
			LinkedList<Entry> serializedEntries = (LinkedList<Entry>) aSerializedObject;
			for ( Entry e : serializedEntries ) {
				this.getDB().addOne(e);
			}
		} catch (Exception e) {
			FasadeLayer3.log(e.getClass() + " " + e.getMessage());
		}
				
		File[] files = FasadeLayer3.getFolderMovies(FOLDER);
		FasadeLayer3.writeTXT(FILES, FasadeLayer3.toString(files));
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
			this.aDB.serializeScrapedMovies(FILESER);
		} catch (Exception e) {
			FasadeLayer3.log(e.getMessage());
		}	
	}
	
	//TODO: more elegance!
	public void scrapeLocalDB() {
		Iterator<Entry> iterator = this.getDB().getTwoIterator();
		int m = this.getDB().sizeTwo();
		int i = 0;
		while (iterator.hasNext()){
			System.out.println(++i + "/" + m);
			Entry aEntry = iterator.next();
			Entry aScrapedEntry = this.getIMDBAPIManager().scrape(aEntry);
			this.getDB().addOne(aScrapedEntry);
//			this.getDB().removeTwo(aScrapedEntry);	
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

	public void writeTXT() throws FileNotFoundException, IOException, ClassNotFoundException {
		this.getDB().writeTXT(FILESER + "txt");
	}
}