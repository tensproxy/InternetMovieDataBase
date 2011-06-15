package layer3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class DataBase {
	
	private LinkedList<Entry> one = null;
	private LinkedList<Entry> two = null;
	private LinkedList<Entry> three = null;
	private LinkedList<Entry> four = null;
	
	public DataBase(){
		this.setOne(new LinkedList<Entry>());
		this.setTwo(new LinkedList<Entry>());
		this.setThree(new LinkedList<Entry>());
		this.setFour(new LinkedList<Entry>());
	}
	
	private LinkedList<Entry> getOne() {
		return this.one;
	}
	
	public Iterator<Entry> getOneIterator(){
		return this.getOne().iterator();
	}

	private LinkedList<Entry> getTwo() {
		return this.two;
	}
	
	private LinkedList<Entry> getThree() {
		return this.three;
	}
	
	private LinkedList<Entry> getFour() {
		return this.four;
	}

	private LinkedList<Entry> setOne (LinkedList<Entry> aSerializedObject) {
		this.one = aSerializedObject;
		return this.getOne();
	}
	
	private LinkedList<Entry> setTwo(LinkedList<Entry> inTemp) {
		this.two = inTemp;
		return this.getTwo();
	}
	
	private LinkedList<Entry> setThree(LinkedList<Entry> inTemp) {
		this.three = inTemp;
		return this.getThree();
	}
	
	private LinkedList<Entry> setFour(LinkedList<Entry> inTemp) {
		this.four = inTemp;
		return this.getFour();
	}
	
	@Override
	public String toString(){
		return 
		FasadeLayer3.toString("one", this.getOne()) + 
		FasadeLayer3.toString("two", this.getTwo()) + 
		FasadeLayer3.toString("three", this.getThree()) +
		FasadeLayer3.toString("three", this.getThree()) +
		"" ;
	}

	public boolean addOne(Entry e) {
		return this.getOne().add(e);
	}
	
	public boolean addTwo(Entry e) {
		return this.getTwo().add(e);
	}
	
	public boolean addThree(Entry e) {
		return this.getThree().add(e);
	}
	
	public boolean addFour(Entry e) {
		return this.getFour().add(e);
	}

	public void removeMoviesNotExitantAnymore () {
		final LinkedList<Entry> removeEntries = new LinkedList<Entry>();
		for ( Entry e : this.getOne() ) {
			if ( ! this.getTwo().contains(e) ) {
				removeEntries.add(e);
			}
		} 
		for (Entry e : removeEntries ) { 
			this.getOne().remove(e);
			this.addFour(e);
		}
	}

	public void removeAlreadySerializedMovies() {
		final LinkedList<Entry> removeEntries = new LinkedList<Entry>();
		for ( Entry e : this.getTwo() ) {
			if ( ! this.getOne().contains(e) ) {
				removeEntries.add(e);
			}
		} 
		for (Entry e : removeEntries ) { 
			this.getTwo().remove(e);
		}
	}

	public void serializeScrapedMovies(String inFileLocation) throws FileNotFoundException, IOException, ClassNotFoundException {
		FasadeLayer3.setSerializedDB(inFileLocation, this.getOne());
	}

	public int sizeOne() {
		return this.getOne().size();
	}
}