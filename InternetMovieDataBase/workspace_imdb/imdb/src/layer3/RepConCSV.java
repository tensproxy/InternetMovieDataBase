package layer3;

public class RepConCSV extends RepConAbstract {

	public RepConCSV() {
		super(".csv");
	}

	@Override
	public Object convert(EntryList<Entry> inEntries) {
		StringBuilder aSB = new StringBuilder();
		for ( Entry e : inEntries ) {
			aSB.append(this.toString(e));
		}
		return aSB.toString();
	}

	@Override
	public StringBuilder toString(Entry e) {
		StringBuilder aSB = new StringBuilder();
		String[] values = e.values().toArray(new String[e.values().size()]);
		String[] labels = Entry.labels().toArray(new String[Entry.labels().size()]);
		for ( int i = 0 ; i < labels.length ; i++ ){
			aSB.append(labels[i] + "," + values[i] + ";");
			
		}
		aSB.append(System.getProperty("line.separator"));
		return aSB;
	}
}