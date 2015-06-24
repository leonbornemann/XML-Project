package logic;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.basex.core.BaseXException;

import database.DBPediaDatabase;
import database.DataExtraction;
import database.exceptions.UnknownLanguageException;

/***
 * Example class to show the functionality of the server logic
 * @author LeonBornemann
 *
 */
public class Main {
	
	public static void main(String[] args) throws BaseXException, UnknownLanguageException{
		//throws exceptions!
		/*QuestionGenerator gen = new QuestionGenerator();
		Collection<Question> questions = gen.getDistinctQuestions(2);*/
		QuestionGenerator gen = new QuestionGenerator();
		List<Question> questions = Arrays.asList(gen.getQuestion("Teiwa"),gen.getQuestion("Swedish"));
		questions.forEach(e -> {e.printQuestion(); System.out.println("-----------------------------");});
		//DataExtraction.getExtraInfoFromDBPedia("Mangarayi");
		//Get all Languages present in both databases:
		Set<String> languageSciencePress = cleanup(DataExtraction.getAllLanguageNames());
		Set<String> dbPedia = cleanup(DBPediaDatabase.INSTANCE.getDBpediaLanguages());
		Set<String> languagesInBoth = new HashSet<>(languageSciencePress);
		languagesInBoth.retainAll(dbPedia);
		System.out.println("-------------------------------------");
		System.out.println("languages in language Science press count: " + languageSciencePress.size());
		System.out.println("languages in dbpedia count: " + dbPedia.size());
		System.out.println("valid languages count(after name cleanup): " + languagesInBoth.size());
		System.out.println(DataExtraction.getAllLanguageNames().size());
		//languagesInBoth.forEach(e -> System.out.println(e));
		//note:
	}

	private static Set<String> cleanup(Collection<String> stringSet) {
		Set<String> cleaned = new HashSet<>();
		stringSet.forEach(e -> cleaned.add(e.trim().replaceAll("\n|\r\n", "")) );
		return cleaned;
	}
}
