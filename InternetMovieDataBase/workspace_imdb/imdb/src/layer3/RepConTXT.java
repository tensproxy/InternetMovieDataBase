package layer3;

public class RepConTXT extends RepConAbstract {

	public RepConTXT() {
		super(".txt");
	}

	@Override
	public String convert(EntryList<Entry> inEntries) {
		StringBuilder aSB = new StringBuilder();
		for (Entry e : inEntries) {
			aSB.append(this.toString(e));
		}
		return aSB.toString();
	}
	
	public StringBuilder toString(Entry e) {
		StringBuilder aSB = new StringBuilder();
		aSB.append(e.getID() + "." + e.getLanguage() + System.getProperty("line.separator"));
		aSB.append(e.getTitle() + " (" + e.getYear() + ") " + System.getProperty("line.separator"));
		aSB.append(e.getGenre() + System.getProperty("line.separator"));
		aSB.append(e.getPlot() + System.getProperty("line.separator"));
		aSB.append(Entry.getDirectorL() + ": " + e.getDirector() + System.getProperty("line.separator"));
		aSB.append(Entry.getWriterL() + ": " + e.getWriter() + System.getProperty("line.separator"));
		aSB.append(Entry.getActorsL() + ": " + e.getActors() + System.getProperty("line.separator"));
		aSB.append(System.getProperty("line.separator"));
		aSB.append(System.getProperty("line.separator"));
		return aSB;
	}
}
