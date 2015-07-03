package database;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.Add;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.XQuery;

import data_representation.Country;
import data_representation.DBPediaLanguageInfo;
import database.exceptions.UnknownLanguageException;

/***
 * Class using the Singleton pattern to grant all classes access to the same Database-Object
 * @author LeonBornemann
 *
 */
public class DBPediaDatabase {

	public static final DBPediaDatabase INSTANCE = new DBPediaDatabase();
	private Context context;
	
	private DBPediaDatabase(){
		context = new Context();
		try {
		    InputStream stream = this.getClass().getClassLoader().getResourceAsStream("../resourcesDB/dbpedia-database/extraInformation.xml");
			new CreateDB("DBpediaDB").execute(context);
			Add addCommand = new Add("");
			addCommand.setInput(stream);
			addCommand.execute(context);
		} catch (BaseXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AssertionError("Failed to load database due to BaseXException in class " + DBPediaDatabase.class.getName());
		}
	}
	
	/***
	 * Extracts all Information for the {@code languageName} out of the DBPedia-database and returns it in an Object.
	 * @param languageName
	 * @return
	 * @throws BaseXException
	 * @throws UnknownLanguageException
	 */
	public DBPediaLanguageInfo getExtraInfoFromDBPedia(String languageName) throws BaseXException, UnknownLanguageException {
		if(!getDBpediaLanguages().contains(languageName)){
			throw new UnknownLanguageException(languageName + " not found in DBPedia database");
		}
		String xPathSpokenInExpr = "//extraInformation/language[@language='"+languageName+"']/spokenIn";
		String countryXPathExpr = "/country/text()";
		String longitudeXPathExpr = "/long/text()";
		String lattitudeXPathExpr = "/lat/text()";
		String baseQuery = "for $doc in collection('DBpediaDB') let $file-path := base-uri($doc) return doc($file-path)";		
		String countryQuery = baseQuery + xPathSpokenInExpr + countryXPathExpr;
		String longitudeQuery = baseQuery + xPathSpokenInExpr + longitudeXPathExpr;
		String lattitudeQuery = baseQuery + xPathSpokenInExpr + lattitudeXPathExpr;
		List<String> countryNames = Arrays.asList(new XQuery(countryQuery).execute(context).split("\n|\r\n"));
		List<String> longitudes = Arrays.asList(new XQuery(longitudeQuery).execute(context).split("\n|\r\n"));
		List<String> lattitudes = Arrays.asList(new XQuery(lattitudeQuery).execute(context).split("\n|\r\n"));
		assert(countryNames.size()==longitudes.size() && longitudes.size()==lattitudes.size());
		List<Country> countries = new ArrayList<>(countryNames.size());
		for(int i=0;i<countryNames.size();i++){
			String countryName = countryNames.get(i).substring(countryNames.get(i).lastIndexOf('/')+1);
			Double longitude = Double.parseDouble(longitudes.get(i).substring(0, longitudes.get(i).indexOf('^')));
			Double lattitude = Double.parseDouble(lattitudes.get(i).substring(0, lattitudes.get(i).indexOf('^')));
			countries.add(new Country(countryName, longitude, lattitude));
		}
		return new DBPediaLanguageInfo(languageName, countries );
	}
	
	public void test() throws BaseXException{
		////extraInformation/language[@language='"+languageName+"']/spokenIn
		String languageName = "Swedish";
		String xPathExpr = "//extraInformation/language[@language='"+languageName+"']/spokenIn/country/text()";
		String query = "for $doc in collection('DBpediaDB') let $file-path := base-uri($doc) return doc($file-path)"+ xPathExpr;
		String res = new XQuery(query).execute(context);
		System.out.println(res);
	}
	
	/**
	 * This methods returns all languages contained in the DBPedia database
	 * 
	 * @return List of languages
	 * @throws BaseXException
	 */
	public List<String> getDBpediaLanguages() throws BaseXException {
		String query = "distinct-values(for $doc in collection('DBpediaDB') let $file-path := base-uri($doc) return doc($file-path)//extraInformation/language/@language/string())";
		List<String> output = Arrays.asList((new XQuery(query).execute(context)).split("\n|\r\n"));
		// Closes database context
		return output;
	}
}
