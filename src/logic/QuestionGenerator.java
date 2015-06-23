package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.basex.core.BaseXException;

import database.DBPediaDatabase;
import database.DataExtraction;
import database.LanguageContent;
import database.exceptions.UnknownLanguageException;

/***
 * The main class in the logic. Its methods generate questions from the data found in the databases.
 * The generation of Questions is very basic for now.
 * @author LeonBornemann
 *
 */
public class QuestionGenerator {

	/***
	 * Randomly Generates a new Question.
	 * @return A new Question
	 * @throws BaseXException
	 * @throws UnknownLanguageException
	 */
	public Question getNewQuestion() throws BaseXException, UnknownLanguageException{
		String languageName = DataExtraction.getRandomLanguageName();
		return buildQuestionData(languageName);
	}

	private Question buildQuestionData(String languageName) throws UnknownLanguageException {
		LanguageContent languageContent;
		try {
			languageContent = DataExtraction.getLanguage(languageName);
			//extra info is not yet used
			//DBPediaLanguageInfo info = DBPediaDatabase.INSTANCE.getExtraInfoFromDBPedia(languageName);
			List<String> answers = getOtherLanguages(languageName);
			answers.add(languageName);
			Collections.shuffle(answers);
			int correctAnswerIndex = answers.indexOf(languageName);
			return new Question("The sentence \""+ languageContent.getRandomExample().getOriginal() + "\" is in which language?", answers, correctAnswerIndex);
		} catch (BaseXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("question could not be generated due to an exception in the database module");
			return null;
		}
	}
	
	private List<String> getOtherLanguages(String languageName) throws BaseXException {
		List<String> allLanguageNames = new ArrayList<>(DataExtraction.getAllLanguageNames());
		allLanguageNames.remove(languageName);
		Collections.shuffle(allLanguageNames);
		return allLanguageNames.subList(0, 3);
	}

	/***
	 * Returns randomly generated, but distinct questions
	 * @param count the number of questions to be generated
	 * @return A collection of Questions
	 * @throws BaseXException
	 * @throws UnknownLanguageException
	 */
	public Collection<Question> getDistinctQuestions(int count) throws BaseXException, UnknownLanguageException{
		List<String> allLanguageNames = DataExtraction.getAllLanguageNames();
		Collections.shuffle(allLanguageNames);
		List<Question> questionData = new ArrayList<>(count);
		for(String languageName : allLanguageNames.subList(0, count)){
			questionData.add(buildQuestionData(languageName));
		}
		return questionData;
	}

	public Question getQuestion(String languageName) throws UnknownLanguageException {
		return buildQuestionData(languageName);
	}
}
