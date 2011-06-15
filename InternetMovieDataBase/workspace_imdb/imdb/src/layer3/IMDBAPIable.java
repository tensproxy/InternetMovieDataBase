package layer3;

public interface IMDBAPIable {
	
	public String createURL(String inID);

	public Entry builtUpLocalEntry(Entry inAbstractEntry, String scrapedString);

}
