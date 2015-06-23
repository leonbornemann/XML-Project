package logic;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.basex.core.BaseXException;

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
	}
	
}
