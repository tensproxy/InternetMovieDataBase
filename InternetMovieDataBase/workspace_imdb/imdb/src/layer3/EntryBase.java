package layer3;

import java.util.Iterator;
import java.util.LinkedList;

public class EntryBase {
	
	private LinkedList<EntryList<Entry>> lists = null;
	
	public EntryBase(){
		this.setLists(new LinkedList<EntryList<Entry>>());
	}
	
	public boolean addEntry(int index, Entry inEntry) {
		return this.getLists().get(index).add(inEntry);
	}

	public int newList(EntryList<Entry> inEntryList) {
		int index = -1;
		if (inEntryList != null) {
			if (this.getLists().add(inEntryList)) {
				index = index + this.getLists().size();	
			}			
		}
		return index;
	}
	
	@SuppressWarnings("unchecked")
	public EntryList<Entry> clone(int inIndex) {
		return (EntryList<Entry>) this.getLists().get(inIndex).clone();
	}
	
	/*
	 * same
	 * diffA
	 * diffB
	 */
	
	private LinkedList<EntryList<Entry>> getLists() {
		return this.lists;
	}
	
	public Iterator<Entry> iterator(int inIndex) {
		return this.getLists().get(inIndex).iterator();
	}

	public int newList() {
		int index = -1;
		if (this.getLists().add(new EntryList<Entry>())) {
			index = index + this.getLists().size();
		}
		return index;
	}
	
	private LinkedList<EntryList<Entry>> setLists(LinkedList<EntryList<Entry>> inLinkedList) {
		this.lists = inLinkedList; 
		return this.getLists();
	}

	public int size(int inIndex) {
		return this.getLists().get(inIndex).size();
	}

	@Override
	public String toString(){
		StringBuilder aSB = new StringBuilder();
//		for ( int i = 0 ; i < 1 this.getLists().size() ; i++ ){
//			aSB.append(this.toString(i) + System.getProperty("line.separator"));
//		}
		aSB.append(this.toString(6));
		return aSB.toString();
	}

	public String toString(int a) {
		return this.getLists().get(a).toString();
	}

	/**
	 * Entries in A that are not in B are removed from A.
	 * Removed entries are returned.
	 * Example: Remove from A containing serialized movies, movies that 
	 * are not local anymore, contained in B.
	 */
	public EntryList<Entry> trimAFromDiffToBReturnTrimmedEntries (int indexA, int indexB) {
		final EntryList<Entry> trimEntries = new EntryList<Entry>();
		for ( Entry e : this.getLists().get(indexA) ) {
			if ( ! this.getLists().get(indexB).contains(e) ) {
				trimEntries.add(e);
			}
		} 
		for (Entry e : trimEntries ) { 
			this.getLists().get(indexA).remove(e);
		}
		return trimEntries;
	}

	/**
	 * Entries in A that are in B are removed from A.
	 * Removed entries are returned.
	 */
	public EntryList<Entry> trimAFromSameToBReturnTrimmedEntries(int indexA, int indexB) {
		final EntryList<Entry> trimEntries = new EntryList<Entry>();
		for ( Entry e : this.getLists().get(indexA) ) {
			if ( this.getLists().get(indexB).contains(e) ) {
				trimEntries.add(e);
			}
		} 
		for ( Entry e : trimEntries ) { 
			this.getLists().get(indexA).remove(e);
		}
		return trimEntries;
	}

	public int size() {
		return this.getLists().size();
	}

	public boolean addList(int index, EntryList<Entry> inAddList) {
		return this.getLists().get(index).addAll(inAddList);
	}
}