package logic;

import java.util.Collection;

import org.basex.core.BaseXException;

import database.exceptions.UnknownLanguageException;

/***
 * Example class to show the functionality of the server logic
 * @author LeonBornemann
 *
 */
public class Main {
	
	public static void main(String[] args) throws BaseXException, UnknownLanguageException{
		QuestionGenerator gen = new QuestionGenerator();
		Collection<QuestionData> questions = gen.getDistinctQuestions(10);
		questions.forEach(e -> {e.printQuestion(); System.out.println("-----------------------------");});
		//DataExtraction.getExtraInfoFromDBPedia("Mangarayi");
	}
	
}
