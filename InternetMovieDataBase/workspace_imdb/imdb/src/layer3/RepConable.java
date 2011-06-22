package layer3;

public interface RepConable {
	
	public Object convert(EntryList<Entry> inEntries);

	public Object getFileEnding();
	
	public Object toString(Entry e);

}
