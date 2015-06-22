package logic;

import java.util.List;

public class ExtraInfo {

	private String languageName;
	private List<Country> spokenIn;
	
	public ExtraInfo(String languageName, List<Country> spokenIn) {
		super();
		this.languageName = languageName;
		this.spokenIn = spokenIn;
	}

	public String getLanguageName() {
		return languageName;
	}

	public List<Country> getSpokenIn() {
		return spokenIn;
	}
}
