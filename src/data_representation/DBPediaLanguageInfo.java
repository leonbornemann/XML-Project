package data_representation;

import java.util.List;

/***
 * Straightforward data class to contain infos from the DBPedia-database
 * @author LeonBornemann
 *
 */
public class DBPediaLanguageInfo {

	private String languageName;
	private List<Country> spokenIn;
	
	public DBPediaLanguageInfo(String languageName, List<Country> spokenIn) {
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
