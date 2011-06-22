package layer3;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

public class Entry implements Serializable{
	
	public static enum parameter {
		Actors,
		Director,
		Genre,
		ID,
		Language,
		Plot,
		Poster,
		Rated,
		Rating,
		Released,
		Response,
		Runtime,
		Title,
		Votes,
		Writer,
		Year
	}
	private static HashMap<Entry.parameter, String> parameter_Label = null;
	private static LinkedList<String> knownValidLanguages = null;
	private static final long serialVersionUID = -7045975487325119763L;
	private static String[] ISOLanguages = null;
	
	public static String getActorsL() {
		return Entry.getParameter_Label().get(Entry.parameter.Actors);	
	}
		
	public static String getDirectorL() {
		return Entry.getParameter_Label().get(Entry.parameter.Director);
	}
	
	public static String getGenreL() {
		return Entry.getParameter_Label().get(Entry.parameter.Genre);
	}
	
	public static final String getIDL() {
		return Entry.getParameter_Label().get(Entry.parameter.ID);
	}
	
	public static String getLanguageL() {		
		return Entry.getParameter_Label().get(Entry.parameter.Language);
	}
	
	public static String getLReleased() {
		return Entry.getParameter_Label().get(Entry.parameter.Released);
	}
	
	public static HashMap<parameter, String> getParameter_Label() {
		if (Entry.parameter_Label == null ) {
			HashMap<parameter, String> initParameter_Label = new HashMap<parameter, String>();
			initParameter_Label.put(Entry.parameter.Actors, "Actors");
			initParameter_Label.put(Entry.parameter.Director, "Director");
			initParameter_Label.put(Entry.parameter.Genre, "Genre");
			initParameter_Label.put(Entry.parameter.ID, "ID");
			initParameter_Label.put(Entry.parameter.Language, "Language");
			initParameter_Label.put(Entry.parameter.Plot, "Plot");
			initParameter_Label.put(Entry.parameter.Poster, "Poster");
			initParameter_Label.put(Entry.parameter.Rated, "Rated");
			initParameter_Label.put(Entry.parameter.Rating, "Rating");
			initParameter_Label.put(Entry.parameter.Released, "Released");
			initParameter_Label.put(Entry.parameter.Response, "Response");
			initParameter_Label.put(Entry.parameter.Runtime, "Runtime");
			initParameter_Label.put(Entry.parameter.Released, "Released");
			initParameter_Label.put(Entry.parameter.Runtime, "Runtime");	
			initParameter_Label.put(Entry.parameter.Writer, "Writer");	
			Entry.setParameter_Label(initParameter_Label);
		}
		return Entry.parameter_Label;
	}
	
	public static String getPlotL() {
		return Entry.getParameter_Label().get(Entry.parameter.Plot);
	}
	
	public static String getPosterL() {
		return Entry.getParameter_Label().get(Entry.parameter.Poster);
	}
	
	public static String getRatedL() {
		return Entry.getParameter_Label().get(Entry.parameter.Rated);

	}
	
	public static String getRatingL() {
		return Entry.getParameter_Label().get(Entry.parameter.Rating);
	}
	
	public static String getResponseL() {
		return Entry.getParameter_Label().get(Entry.parameter.Response);
	}
	
	public static String getRuntimeL() {
		return Entry.getParameter_Label().get(Entry.parameter.Runtime);
	}
	
	public static String getTitleL() {
		return Entry.getParameter_Label().get(Entry.parameter.Title);
	}
	
	public static String getVotesL() {		
		return Entry.getParameter_Label().get(Entry.parameter.Votes);
	}
	
	public static String getWriterL() {
		return Entry.getParameter_Label().get(Entry.parameter.Writer);
	}
	
	public static String getYearL() {		
		return Entry.getParameter_Label().get(Entry.parameter.Year);
	}	
	
	private static HashMap<parameter, String> setParameter_Label(HashMap<parameter, String> inParameter_Label) {
		Entry.parameter_Label = inParameter_Label;
		return Entry.getParameter_Label();
	}
	
	private HashMap<Entry.parameter, String> parameter_Value = null;
	
	public Entry(String inID, String inLanguage) {
		HashMap<Entry.parameter, String> temp = new HashMap<Entry.parameter, String>();
		temp.put(Entry.parameter.ID, inID);
		temp.put(Entry.parameter.Language, inLanguage);
		this.setParameter_Value(temp);
	}
	
	public Entry fill(HashMap<parameter, String> inParameter_Value) {
		inParameter_Value.put(Entry.parameter.Language, this.getParameter_Value().get(Entry.parameter.Language));
		this.setParameter_Value(inParameter_Value);
		return this;
	}

	@Override
	public final boolean equals(Object inObject) {
		boolean isEqual = false;
		if ( inObject instanceof Entry ) {
			Entry inEntry = (Entry) inObject;
			if ( inEntry.hashCode() == this.hashCode() ) {
				isEqual = true;
			}
		}
		return isEqual;
	}
	
	public String getActors() {
		return this.getParameter_Value().get(Entry.parameter.Actors);
	}
	
	public String getDirector() {
		return this.getParameter_Value().get(Entry.parameter.Director);
	}
	
	public String getGenre() {
		return this.getParameter_Value().get(Entry.parameter.Genre);
	}

	public final String getID() {
		return this.getParameter_Value().get(Entry.parameter.ID);
	}

	public String getLanguage() {
		return this.getParameter_Value().get(Entry.parameter.Language);
	}

	public String getName(){
		return this.getParameter_Value().get(Entry.parameter.ID) + "." + this.getParameter_Value().get(Entry.parameter.Language);
	}
	
	private HashMap<parameter, String> getParameter_Value() {
		return this.parameter_Value;
	}

	public String getPlot() {
		return this.getParameter_Value().get(Entry.parameter.Plot);
	}
	
	public String getPoster() {
		return this.getParameter_Value().get(Entry.parameter.Poster);
	}

	public String getRated() {
		return this.getParameter_Value().get(Entry.parameter.Rated);
	}
	
	public String getRating() {
		return this.getParameter_Value().get(Entry.parameter.Rating);
	}

	public String getReleased() {
		return this.getParameter_Value().get(Entry.parameter.Released);
	}
	
	public String getResponse() {		
		return this.getParameter_Value().get(Entry.parameter.Response);
	}

	public String getRuntime() {
		return this.getParameter_Value().get(Entry.parameter.Runtime);
	}
	
	public String getTitle() {
		return this.getParameter_Value().get(Entry.parameter.Title);
	}
	
	public Collection<String> values() {
		return this.getParameter_Value().values();
	}
	
	public String getVotes() {		
		return this.getParameter_Value().get(Entry.parameter.Votes);
	}

	public String getWriter() {
		return this.getParameter_Value().get(Entry.parameter.Writer);
	}
	
	public String getYear() {
		return this.getParameter_Value().get(Entry.parameter.Year);	
	}

	@Override
	public final int hashCode(){
		return this.getID().hashCode();
	}

	public boolean isScraped() {
		boolean isScraped = true;
		Collection<String> strings = this.getParameter_Value().values();
		for ( String s : strings ) {
			if( s == null ) {
				isScraped = false;
			}
		}
		return isScraped;
	}

	public String setActors(String string) {
		return this.getParameter_Value().put(Entry.parameter.Actors, string);
	}

	public String setDirector(String string) {
		return this.getParameter_Value().put(Entry.parameter.Director, string);
	}

	public String setGenre(String string) {
		return this.getParameter_Value().put(Entry.parameter.Genre, string);
	}
	
	private HashMap<parameter, String> setParameter_Value(HashMap<parameter, String> inParameter_Value) {
		this.parameter_Value = inParameter_Value;
		return this.getParameter_Value();
	}

	public String setPlot(String string) {
		return this.getParameter_Value().put(Entry.parameter.Plot, string);
	}

	public String setPoster(String string) {
		return this.getParameter_Value().put(Entry.parameter.Poster, string);
	}

	public String setRated(String string) {
		return this.getParameter_Value().put(Entry.parameter.Rated, string);
	}

	public String setRating(String string) {
		return this.getParameter_Value().put(Entry.parameter.Rating, string);
	}

	public String setReleased(String string) {
		return this.getParameter_Value().put(Entry.parameter.Released, string);
	}

	public String setResponse(String string) {
		return this.getParameter_Value().put(Entry.parameter.Actors, string);
	}

	public String setRuntime(String string) {
		return this.getParameter_Value().put(Entry.parameter.Runtime, string);
	}
	
	public String setTitle(String string) {
		return this.getParameter_Value().put(Entry.parameter.Title, string);
	}
	
	public String setVotes(String string) {
		return this.getParameter_Value().put(Entry.parameter.Votes, string);
	}

	public String setWriter(String string) {
		return this.getParameter_Value().put(Entry.parameter.Writer, string);
	}
	
	public String setYear(String string) {
		return this.getParameter_Value().put(Entry.parameter.Year, string);
	}
	
	public String toString(){
		return FL3.toString(this);
	}

	public static Collection<String> labels() {
		return Entry.getParameter_Label().values();
	}
	
	public boolean isValid(){
		boolean isValid = false;
		//check language
		if ( ! Entry.getKnownValidLanguages().contains(this.getLanguage())){
			for ( int i = 0 ; i < Entry.getISOLanguages().length ; i++ ) {
				if ( this.getLanguage().equals(Entry.getISOLanguages()[i])){
					isValid = true;
					break;
				}
			}
		} else {
			isValid = true;
		}
		return isValid;
	}

	private static String[] getISOLanguages() {
		if (Entry.ISOLanguages == null){
			Entry.setISOLanguages(Locale.getISOLanguages());
		}
		return Entry.ISOLanguages;
	}

	private static String[] setISOLanguages(String[] inISOLanguages) {
		Entry.ISOLanguages = inISOLanguages;
		return Entry.getISOLanguages();		
	}

	private static LinkedList<String> getKnownValidLanguages() {
		if (Entry.knownValidLanguages == null ) {
			Entry.setKnownValidLanguages(new LinkedList<String>());
		}
		return Entry.knownValidLanguages ;
	}

	private static void setKnownValidLanguages(LinkedList<String> inKnownLanguages) {
		Entry.knownValidLanguages = inKnownLanguages;
	}
}