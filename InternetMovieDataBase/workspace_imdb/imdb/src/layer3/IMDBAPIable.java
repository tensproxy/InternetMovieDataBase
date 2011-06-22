package layer3;

import java.net.URL;

public interface IMDBAPIable {
	
	public URL createURL(String inID);
	
	public Entry constructEntry (Entry inEntry, String ScrapedString) throws Exception;
}
