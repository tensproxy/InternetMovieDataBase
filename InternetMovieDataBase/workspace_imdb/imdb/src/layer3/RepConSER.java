package layer3;

public class RepConSER extends RepConAbstract {

	public RepConSER() {
		super(".ser");
	}

	@Override
	public Object convert(EntryList<Entry> inEntries) {
		return inEntries;		
	}

	@Override
	public StringBuilder toString(Entry e) {
		return new StringBuilder(e.toString());
	}
}