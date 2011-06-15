package layer3;

public class IMDBAPIable_com_imdbapi extends IMDBAPIAbstract implements IMDBAPIable {

	public IMDBAPIable_com_imdbapi() {
		super("http://www.imdbapi.com/?i=", "");
	}

	@Override
	public Entry constructEntry (Entry inEntry, String scrapedString) {

		scrapedString = scrapedString.replace("{", "");
		scrapedString = scrapedString.replace("}", "");
		String[] key_value = scrapedString.split("\",\"");
		
		for ( int j = 0 ; j  < key_value.length ; j++ ) {
			int lastDoubleQuote = key_value[j].lastIndexOf('"');
			key_value[j] = key_value[j].substring(lastDoubleQuote+1);
		}
		String temp = key_value[13];
		key_value[13] = key_value[14];
		key_value[14] = temp;
				
		return Entry.constructEntry(inEntry, key_value, 13);
	}
}
