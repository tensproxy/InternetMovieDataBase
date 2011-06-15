package layer3;

import java.io.Serializable;

/**

 * ID 		0 
 * ---
 * Language 1 
 * LanguageV2
 * ---
 * Title 	3  
 * Year 	4 
 * Rated 	5  
 * Released 6 
 * Genre 	7  
 * Director 8 
 * Writer 	9  
 * Actors 	10 
 * Plot 	11 
 * Poster 	12
 * Runtime 	13  
 * Rating 	14 
 * Votes 	15 
 * ID		16
 * Response 17
 * 
 * @author hkh
 * 
 */
public class Entry implements Serializable{
	
	public static final int LENGTH = 18;
	private static final int OFFSET = 3;
	private static final long serialVersionUID = -7045975487325119763L;
		
	private String[] parameters = new String[LENGTH];
	
	public Entry(String inID, String inLanguage) {
		this.setID(inID);
		this.setLanguage(inLanguage);
	}
	
	public static Entry constructEntry(Entry inEntry, String[] inParameters, int inIID) {
		if ( ! inEntry.getID().equals(inParameters[inIID]) ) {
			for ( int i = Entry.OFFSET; i < Entry.LENGTH ; i++ ) {
				inEntry.parameters[i] = inParameters[ i - Entry.OFFSET];
			}				
		} else { // TODO
			System.err.println("BÄM!");
		}
		return inEntry;
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
		return this.parameters[10];
	}
	
	public String getDirector() {
		return this.parameters[8];
	}
	
	public String getGenre() {
		return this.parameters[7];
	}
	
	public final String getID() {
		return this.parameters[0];
	}
	
	public String getLanguage() {
		return this.parameters[1];
	}
	
	public String getLanguageV() {
		return this.parameters[2];	
	}

	public String getPlot() {
		return this.parameters[11];
	}
	
	public String getPoster() {
		return this.parameters[12];
	}
	
	public String getRated() {
		return this.parameters[5];
	}
	
	public String getRating() {
		return this.parameters[14];
	}

	public String getReleased() {
		return this.parameters[7];
	}

	public String getResponse() {		
		return this.parameters[16];
	}

	public String getRuntime() {
		return this.parameters[13];
	}

	public String getTitle() {
		return this.parameters[3];
	}

	public String getVotes() {		
		return this.parameters[15];
	}
	
	public String getWriter() {
		return this.parameters[9];
	}

	public String getYear() {
		return this.parameters[4];	
	}

	@Override
	public final int hashCode(){
		return this.getID().hashCode();
	}

	public String setActors(String string) {
		return this.parameters[10] = string;
	}

	public String setDirector(String string) {
		return this.parameters[8] = string;
	}

	public String setGenre(String string) {
		return this.parameters[7] = string;
	}

	private final String setID(String string) {
		return this.parameters[0] = string;
	}

	private String setLanguage(String string) {
		this.setLanguageV(string);
		return this.parameters[1] = string;
	}

	private String setLanguageV(String string) {
		return this.parameters[2] = FasadeLayer3.isValidLocale(string) + "";
	}

	public String setPlot(String string) {
		return this.parameters[11] = string;
	}
	
	public String setPoster(String string) {
		return this.parameters[12] = string;
	}

	public String setRated(String string) {
		return this.parameters[5] = string;
	}

	public String setRating(String string) {
		return this.parameters[14] = string;
	}

	public String setReleased(String string) {
		return this.parameters[6] = string;
	}

	public String setResponse(String string) {
		return this.parameters[16] = string;
	}

	public String setRuntime(String string) {
		return this.parameters[13] = string;
	}

	public String setTitle(String string) {
		return this.parameters[3] = string;
	}

	public String setVotes(String string) {
		return this.parameters[15] = string;
	}
	
	public String setWriter(String string) {
		return this.parameters[9] = string;
	}
	
	public String setYear(String string) {
		return this.parameters[4] = string;
	}

	public String toString(){
		return FasadeLayer3.toString(this);
	}
	
	public String getName(){
		return this.getID() + "." + this.getLanguage();
	}
}