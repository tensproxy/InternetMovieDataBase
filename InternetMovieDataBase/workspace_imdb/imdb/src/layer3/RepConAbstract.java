package layer3;

public abstract class RepConAbstract implements RepConable{

	private String aFileEnding = "imdbthingy";	
	
	public RepConAbstract(String inFileEnding) {
		this.setFileEnding(inFileEnding);
	}

	private String setFileEnding(String inFileEnding) {
		this.aFileEnding = inFileEnding;
		return this.getFileEnding();
	}
	
	public abstract Object convert(EntryList<Entry> inEntries);

	@Override	
	public String getFileEnding(){
		return this.aFileEnding;
	}
	
	@Override	
	public abstract StringBuilder toString(Entry e);
}
