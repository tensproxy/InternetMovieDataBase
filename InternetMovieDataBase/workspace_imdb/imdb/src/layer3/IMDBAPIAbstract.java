package layer3;

import java.net.MalformedURLException;
import java.net.URL;

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
	public URL createURL(String inID) {
		URL createURL = null;
		try {
			createURL =  new URL(this.getBaseURL() + inID + this.getEndURL());
		} catch (MalformedURLException e) {
			FL3.log(e);
		}
		return createURL;
	}
}
