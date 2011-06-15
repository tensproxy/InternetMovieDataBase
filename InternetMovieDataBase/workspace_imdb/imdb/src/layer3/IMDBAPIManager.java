package layer3;

import java.io.IOException;
import java.net.MalformedURLException;


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
		new IMDBAPIable_com_imdbapi()
		//"http://imdbapi.poromenos.org/js/?t=",
	};

	public Entry scrape(Entry inEntry) {
		
		String APIString = null;
		Entry scrapedEntry = null;
		try {
			APIString = FasadeLayer3.getAPIEntry(IMDBAPIables[0].createURL(inEntry.getID()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		//TODO fetch Request Errors
		scrapedEntry = IMDBAPIables[0].constructEntry(inEntry, APIString);
		return scrapedEntry;
	}
}