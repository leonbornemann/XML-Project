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

import data_representation.Example;
import data_representation.LanguageContent;

public class LanguageSciencePressDatabase {

	public static LanguageSciencePressDatabase INSTANCE = new LanguageSciencePressDatabase();
	
	private Context context;
	private final String dbName = "DBExample";
	private String dbFilepath = "resources/languageSciencePress-database/";

	public LanguageSciencePressDatabase(){
		this.context = new Context();
		try {
			new CreateDB(dbName).execute(context);
			new Add("", dbFilepath).execute(context);
		} catch (BaseXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AssertionError("Failed to load database due to BaseXException in class " + LanguageSciencePressDatabase.class.getName());
		}
	}
	
	// for testing
	public static void main(String[] args) throws BaseXException {

		List<LanguageContent> languageContentList = LanguageSciencePressDatabase.INSTANCE.getAllLanguages();
		System.out.println(languageContentList.get(1).getLanguageName());
		System.out.println(languageContentList.get(1).getRandomExample()
				.getOriginal());
		LanguageContent exampleLanguageContent = LanguageSciencePressDatabase.INSTANCE.getRandomLanguage();
		System.out.println(exampleLanguageContent.getLanguageName());
		System.out.println(exampleLanguageContent.getRandomExample()
				.getTranslation());
		System.out.println(LanguageSciencePressDatabase.INSTANCE.getRandomLanguageName());

		// create DBpedia Database Example
		DBPediaDatabase.INSTANCE.getDBpediaLanguages();
	}

	public List<String> getAllLanguageNames() throws BaseXException {
		String query = "distinct-values(for $doc in collection('"
				+ dbName
				+ "') let $file-path := base-uri($doc) return doc($file-path)//languageExamples/languageExample/@language/string())";
		List<String> output = Arrays
				.asList((new XQuery(query).execute(context)).split("\n|\r\n"));
		return (output);

	}

	public List<LanguageContent> getAllLanguages() throws BaseXException {
		// get List with all language names to iterate through
		List<String> languageList = getAllLanguageNames();
		List<LanguageContent> languageContentObjects = new ArrayList<LanguageContent>(
				Collections.emptyList());
		for (String element : languageList) {
			// create a languageContentObject for every language
			LanguageContent languageContentObject = new LanguageContent();
			languageContentObject.setLanguageName(element);

			List<Example> examples = new ArrayList<Example>(
					Collections.emptyList());
			String query;
			List<String> originals;
			List<String> translations;

			// Catching the only language that contains a single quote
			if (element.contains("'")) {
				query = "for $doc in collection('"
						+ dbName
						+ "')//languageExamples/languageExample let $file-path := base-uri($doc) where $doc/@language='K&#39;abeena' return ($doc/example/original/string())";
				originals = (Arrays.asList((new XQuery(query).execute(context))
						.split("\n")));
				query = "for $doc in collection('"
						+ dbName
						+ "')//languageExamples/languageExample let $file-path := base-uri($doc) where $doc/@language='K&#39;abeena' return ($doc/example/translation/string())";
				translations = (Arrays.asList((new XQuery(query)
						.execute(context)).split("\n")));

			}

			else {
				query = "for $doc in collection('"
						+ dbName
						+ "')//languageExamples/languageExample let $file-path := base-uri($doc) where $doc/@language='"
						+ element.trim()
						+ "' return ($doc/example/original/string())";
				originals = (Arrays.asList((new XQuery(query).execute(context))
						.split("\n")));
				query = "for $doc in collection('"
						+ dbName
						+ "')//languageExamples/languageExample let $file-path := base-uri($doc) where $doc/@language='"
						+ element.trim()
						+ "' return ($doc/example/translation/string())";
				translations = (Arrays.asList((new XQuery(query)
						.execute(context)).split("\n")));
			}

			// put every pair of original and translation together
			for (int i = 0; i < translations.size(); i++) {
				if (!(translations.get(i).trim().equals("") || originals.get(i)
						.trim().equals(""))) {
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
		return languageContentObjects;
	}

	// calling all language names and choosing one random
	public String getRandomLanguageName() throws BaseXException {
		List<String> languageNames = getAllLanguageNames();
		Random rnd = new Random();
		int randomRange = languageNames.size();
		int rand = rnd.nextInt(randomRange);
		return (languageNames.get(rand));
	}

	// calling all language contents and choosing one random
	public LanguageContent getRandomLanguage() throws BaseXException {
		List<LanguageContent> languageNames = getAllLanguages();
		Random rnd = new Random();
		int randomRange = languageNames.size();
		int rand = rnd.nextInt(randomRange);
		return (languageNames.get(rand));

	}

	public LanguageContent getLanguage(String languageName)
			throws BaseXException {
		LanguageContent languageContentObject = new LanguageContent();
		languageContentObject.setLanguageName(languageName);

		List<Example> examples = new ArrayList<Example>(Collections.emptyList());
		String query;
		List<String> originals;
		List<String> translations;

		if (languageName.contains("'")) {
			query = "for $doc in collection('"
					+ dbName
					+ "')//languageExamples/languageExample let $file-path := base-uri($doc) where $doc/@language='K&#39;abeena' return ($doc/example/original/string())";
			originals = (Arrays.asList((new XQuery(query).execute(context))
					.split("\n")));
			query = "for $doc in collection('"
					+ dbName
					+ "')//languageExamples/languageExample let $file-path := base-uri($doc) where $doc/@language='K&#39;abeena' return ($doc/example/translation/string())";
			translations = (Arrays.asList((new XQuery(query).execute(context))
					.split("\n")));

		}

		else {
			query = "for $doc in collection('"
					+ dbName
					+ "')//languageExamples/languageExample let $file-path := base-uri($doc) where $doc/@language='"
					+ languageName.trim()
					+ "' return ($doc/example/original/string())";
			originals = (Arrays.asList((new XQuery(query).execute(context))
					.split("\n")));
			query = "for $doc in collection('"
					+ dbName
					+ "')//languageExamples/languageExample let $file-path := base-uri($doc) where $doc/@language='"
					+ languageName.trim()
					+ "' return ($doc/example/translation/string())";
			translations = (Arrays.asList((new XQuery(query).execute(context))
					.split("\n")));
		}

		for (int i = 0; i < translations.size(); i++) {
			if (!(translations.get(i).trim().equals("") || originals.get(i)
					.trim().equals(""))) {
				Example example = new Example();
				example.setOriginal(originals.get(i).trim());
				example.setTranslation(translations.get(i).trim());
				examples.add(example);
			}
		}

		// Closes database context

		languageContentObject.setExaples(examples);
		return languageContentObject;

	}
}