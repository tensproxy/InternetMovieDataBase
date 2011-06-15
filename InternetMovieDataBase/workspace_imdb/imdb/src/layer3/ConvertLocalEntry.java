package layer3;

public class ConvertLocalEntry {

	public static String toCSV(Entry inLocalEntry) {
		return inLocalEntry.getTitle() + "\",\"" + inLocalEntry.getYear()
				+ "\",\"" + inLocalEntry.getRated() + "\",\""
				+ inLocalEntry.getReleased() + "\",\""
				+ inLocalEntry.getGenre() + "\",\""
				+ inLocalEntry.getDirector() + "\",\""
				+ inLocalEntry.getActors() + "\",\"" + inLocalEntry.getPlot()
				+ "\",\"" + inLocalEntry.getPoster() + "\",\""
				+ inLocalEntry.getRuntime() + "\",\""
				+ inLocalEntry.getRating() + "\",\"" + inLocalEntry.getVotes()
				+ "\",\"" + inLocalEntry.getID() + "\",\""
				+ inLocalEntry.getResponse();
	}

	public static String toTXT(Entry inLocalEntry) {
		return "tt" + inLocalEntry.getID() + "." + inLocalEntry.getLanguage()
				+ ": " + inLocalEntry.getTitle() + "\n" + "Genre: "
				+ inLocalEntry.getGenre() + ", " + inLocalEntry.getYear()
				+ "\n" + "Plot: " + inLocalEntry.getPlot() + "\n"
				+ "Director: " + inLocalEntry.getDirector() + "\n" + "Actor: "
				+ inLocalEntry.getActors() + "\n";
	}
}