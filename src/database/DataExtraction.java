package database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.Add;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.XQuery;

public class DataExtraction {

	private static Context context;
	
	private static String dbName = "DBExample";
	
	private static String exampleFilePath = "resources/xml-database/";
	
	//for testing
	public static void main(String[] args) throws BaseXException {
		
	    List<LanguageContent> languageContentList = getAllLanguages();
	    System.out.println(languageContentList.get(1).getLanguageName());
	    System.out.println(languageContentList.get(1).getRandomExample().getOriginal());
	    LanguageContent exampleLanguageContent = getRandomLanguage();
	    System.out.println(exampleLanguageContent.getLanguageName());
	    System.out.println(exampleLanguageContent.getRandomExample().getTranslation());
	    System.out.println(getRandomLanguageName());

	}
	
	public static List<String> getAllLanguageNames() throws BaseXException
	{
		context = new Context();
	    new CreateDB(dbName).execute(context);
	    new Add("",exampleFilePath).execute(context);

	    
		String query = "distinct-values(for $doc in collection('"+dbName+"') let $file-path := base-uri($doc) return doc($file-path)//languageExamples/languageExample/@language/string())";
		List<String> output = Arrays.asList((new XQuery(query).execute(context)).split("\n"));
	    // Closes database context
	    context.close();
		return(output);
		
	}
	
	public static List<LanguageContent> getAllLanguages() throws BaseXException
	{
		//get List with all language names to iterate through
		List<String> languageList = getAllLanguageNames();
		
		context = new Context();
	    new CreateDB(dbName).execute(context);
	    new Add("",exampleFilePath).execute(context);

	    
		
		List<LanguageContent> languageContentObjects = new ArrayList<LanguageContent>(Collections.emptyList());
		for (String element : languageList)
		{
			//create a languageContentObject for every language
			LanguageContent languageContentObject = new LanguageContent();
			languageContentObject.setLanguageName(element);
			
			List<Example> examples = new ArrayList<Example>(Collections.emptyList());
			String query;
			List<String> originals;
			List<String> translations;
			
			//Catching the only language that contains a single quote
			if (element.contains("'")) 
			{
				query = "for $doc in collection('"+dbName+"')//languageExamples/languageExample let $file-path := base-uri($doc) where $doc/@language='K&#39;abeena' return ($doc/example/original/string())";
				originals = (Arrays.asList((new XQuery(query).execute(context)).split("\n")));
				query = "for $doc in collection('"+dbName+"')//languageExamples/languageExample let $file-path := base-uri($doc) where $doc/@language='K&#39;abeena' return ($doc/example/translation/string())";
				translations = (Arrays.asList((new XQuery(query).execute(context)).split("\n")));

			}
			
			else 
			{
				query = "for $doc in collection('"+dbName+"')//languageExamples/languageExample let $file-path := base-uri($doc) where $doc/@language='"   +element.trim()+   "' return ($doc/example/original/string())";
				originals = (Arrays.asList((new XQuery(query).execute(context)).split("\n")));
				query = "for $doc in collection('"+dbName+"')//languageExamples/languageExample let $file-path := base-uri($doc) where $doc/@language='"   +element.trim()+   "' return ($doc/example/translation/string())";
				translations = (Arrays.asList((new XQuery(query).execute(context)).split("\n")));
			}
			
			//put every pair of original and translation together
			for (int i=0; i<translations.size(); i++)
			{
				if (!(translations.get(i).trim().equals("") || originals.get(i).trim().equals(""))) 
				{
					Example example = new Example();
					example.setOriginal(originals.get(i).trim());
					example.setTranslation(translations.get(i).trim());
					examples.add(example);
				}
			}
			
			languageContentObject.setExaples(examples);
			languageContentObjects.add(languageContentObject);
			
		}
	    // Closes database context
	    context.close();
		
		return languageContentObjects;
	}
	
	//calling all language names and choosing one random
	public static String getRandomLanguageName() throws BaseXException
	{
		List<String> languageNames = getAllLanguageNames();
		Random rnd = new Random();
		int randomRange=languageNames.size();
		int rand = rnd.nextInt(randomRange);
		return(languageNames.get(rand));		
	}
	
	//calling all language contents and choosing one random
	public static LanguageContent getRandomLanguage() throws BaseXException
	{
		List<LanguageContent> languageNames = getAllLanguages();
		Random rnd = new Random();
		int randomRange=languageNames.size();
		int rand = rnd.nextInt(randomRange);
		return(languageNames.get(rand));		
		
	}
	
	public static LanguageContent getLanguage(String languageName) throws BaseXException
	{
		context = new Context();
	    new CreateDB(dbName).execute(context);
	    new Add("",exampleFilePath).execute(context);

	    
		LanguageContent languageContentObject = new LanguageContent();
		languageContentObject.setLanguageName(languageName);
		
		List<Example> examples = new ArrayList<Example>(Collections.emptyList());
		String query;
		List<String> originals;
		List<String> translations;
		
		if (languageName.contains("'")) 
		{
			query = "for $doc in collection('"+dbName+"')//languageExamples/languageExample let $file-path := base-uri($doc) where $doc/@language='K&#39;abeena' return ($doc/example/original/string())";
			originals = (Arrays.asList((new XQuery(query).execute(context)).split("\n")));
			query = "for $doc in collection('"+dbName+"')//languageExamples/languageExample let $file-path := base-uri($doc) where $doc/@language='K&#39;abeena' return ($doc/example/translation/string())";
			translations = (Arrays.asList((new XQuery(query).execute(context)).split("\n")));

		}
		
		else 
		{
			query = "for $doc in collection('"+dbName+"')//languageExamples/languageExample let $file-path := base-uri($doc) where $doc/@language='"   +languageName.trim()+   "' return ($doc/example/original/string())";
			originals = (Arrays.asList((new XQuery(query).execute(context)).split("\n")));
			query = "for $doc in collection('"+dbName+"')//languageExamples/languageExample let $file-path := base-uri($doc) where $doc/@language='"   +languageName.trim()+   "' return ($doc/example/translation/string())";
			translations = (Arrays.asList((new XQuery(query).execute(context)).split("\n")));
		}
		
		for (int i=0; i<translations.size(); i++)
		{
			if (!(translations.get(i).trim().equals("") || originals.get(i).trim().equals(""))) 
			{
				Example example = new Example();
				example.setOriginal(originals.get(i).trim());
				example.setTranslation(translations.get(i).trim());
				examples.add(example);
			}
		}
		
	    // Closes database context
	    context.close();
		
		languageContentObject.setExaples(examples);
		return languageContentObject;
		
	}
	
}