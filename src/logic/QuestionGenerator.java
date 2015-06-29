package logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.basex.core.BaseXException;

import data_representation.DBPediaLanguageInfo;
import data_representation.Example;
import data_representation.LanguageContent;
import database.DBPediaDatabase;
import database.LanguageSciencePressDatabase;
import database.exceptions.UnknownLanguageException;

/***
 * The main class in the logic. Its methods generate questions from the data found in the databases.
 * The generation of Questions is very basic for now.
 * @author LeonBornemann
 *
 */
public class QuestionGenerator {

	private Random random;

	public QuestionGenerator(){
		this.random = new Random();
	}
	
	/***
	 * 
	 * @return A List of Languages for which Questions can be generated (i.e. languages in both databases)
	 * @throws BaseXException 
	 */
	public List<String> getValidLanguageNames() throws BaseXException{
		Set<String> languageSciencePress = new HashSet<>(LanguageSciencePressDatabase.INSTANCE.getAllLanguageNames());
		Set<String> dbPedia = new HashSet<>(DBPediaDatabase.INSTANCE.getDBpediaLanguages());
		Set<String> languagesInBoth = new HashSet<>(languageSciencePress);
		languagesInBoth.retainAll(dbPedia);
		return new ArrayList<>(languagesInBoth);
	}
	
	/***
	 * Randomly Generates a new Question.
	 * @return A new Question
	 * @throws BaseXException
	 * @throws UnknownLanguageException
	 */
	public Question getNewQuestion() throws BaseXException, UnknownLanguageException{
		List<String> languageList = getValidLanguageNames();
		String languageName = languageList.get(random.nextInt(languageList.size()));
		return buildQuestionData(languageName);
	}

	private Question buildQuestionData(String languageName) throws UnknownLanguageException {
		LanguageContent languageContent;
		try {
			languageContent = LanguageSciencePressDatabase.INSTANCE.getLanguage(languageName);
			//extra info is not yet used
			DBPediaLanguageInfo info = DBPediaDatabase.INSTANCE.getExtraInfoFromDBPedia(languageName);
			List<String> answers = getOtherLanguages(languageName);
			answers.add(languageName);
			Collections.shuffle(answers);
			int correctAnswerIndex = answers.indexOf(languageName);
			Example example = languageContent.getRandomExample();
			String original = example.getOriginal();
			String translation = example.getTranslation();
			return new Question(original, translation, answers, correctAnswerIndex, info);
		} catch (BaseXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("question could not be generated due to an exception in the database module");
			return null;
		}
	}
	
	private List<String> getOtherLanguages(String languageName) throws BaseXException {
		List<String> allLanguageNames = new ArrayList<>(LanguageSciencePressDatabase.INSTANCE.getAllLanguageNames());
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
		List<String> allLanguageNames = getValidLanguageNames();
		Collections.shuffle(allLanguageNames);
		List<Question> questionData = new ArrayList<>(count);
		for(String languageName : allLanguageNames.subList(0, count)){
			questionData.add(buildQuestionData(languageName));
		}
		return questionData;
	}

	/***
	 * Returns a Question for a specific language.
	 * @param languageName
	 * @return
	 * @throws UnknownLanguageException if languageName is not a valid Language
	 */
	public Question getQuestion(String languageName) throws UnknownLanguageException {
		return buildQuestionData(languageName);
	}
}
