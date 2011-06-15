package layer3;

public abstract class IMDBAPIAbstract implements IMDBAPIable {
	
	private String aBaseURL = null;
	private String aEndURL = null;

	public IMDBAPIAbstract(String inBaseURL, String inEndURL) {
		this.setBaseURL(inBaseURL);
		this.setEndURL(inEndURL);
	}
	
	private String setEndURL(String inEndURL) {
		this.aEndURL = inEndURL;
		return this.getEndURL();
	}

	private String getEndURL() {
		return this.aEndURL;
	}

	private String setBaseURL(String inBaseURL) {
		this.aBaseURL = inBaseURL;
		return this.getBaseURL();
	}

	private String getBaseURL() {
		return this.aBaseURL;
	}

	@Override
	public String createURL(String inID) {
		return this.getBaseURL() + inID + this.getEndURL();
	}
}
