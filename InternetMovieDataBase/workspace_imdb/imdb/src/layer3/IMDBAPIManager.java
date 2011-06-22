package layer3;

import java.net.URL;

public class IMDBAPIManager {
	
	private static IMDBAPIManager SINGLETON = null;
	
	public static IMDBAPIManager getInstance() {
		if ( IMDBAPIManager.GETSINGLETON() == null ) {
			IMDBAPIManager.SETSINGLETON(new IMDBAPIManager());
		}
		return IMDBAPIManager.GETSINGLETON();
	}
	
	private static IMDBAPIManager SETSINGLETON(IMDBAPIManager inIMDBAPIManager) {
		IMDBAPIManager.SINGLETON = inIMDBAPIManager;
		return IMDBAPIManager.GETSINGLETON();
	}

	private static IMDBAPIManager GETSINGLETON() {
		return IMDBAPIManager.SINGLETON;
	}

	private static final IMDBAPIable[] IMDBAPIables = {
		new IMDBAP_com_imdbapi()
		//new IMDBAPIable_com_poromenos(); //"http://imdbapi.poromenos.org/js/?t=",
	};

	public Entry scrape(Entry inEntry) throws Exception {
		URL scrapingURL = IMDBAPIables[0].createURL(inEntry.getID()); 
		String APIString = FL3.readScrapedString(scrapingURL);
		Entry scrapedEntry = IMDBAPIables[0].constructEntry(inEntry, APIString);
		return scrapedEntry;
	}
}