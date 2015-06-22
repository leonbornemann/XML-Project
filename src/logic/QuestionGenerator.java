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

public class QuestionGenerator {

	public QuestionData getNewQuestion() throws BaseXException, UnknownLanguageException{
		String languageName = DataExtraction.getRandomLanguageName();
		return buildQuestionData(languageName);
	}

	private QuestionData buildQuestionData(String languageName) throws UnknownLanguageException {
		LanguageContent languageContent;
		try {
			languageContent = DataExtraction.getLanguage(languageName);
			//extra info is not yet used
			ExtraInfo info = DBPediaDatabase.INSTANCE.getExtraInfoFromDBPedia(languageName);
			List<String> answers = getOtherLanguages(languageName);
			answers.add(languageName);
			Collections.shuffle(answers);
			int correctAnswerIndex = answers.indexOf(languageName);
			return new QuestionData("The sentence \""+ languageContent.getRandomExample().getOriginal() + "\" is in which language?", answers, correctAnswerIndex);
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

	public Collection<QuestionData> getDistinctQuestions(int count) throws BaseXException, UnknownLanguageException{
		List<String> allLanguageNames = DataExtraction.getAllLanguageNames();
		Collections.shuffle(allLanguageNames);
		List<QuestionData> questionData = new ArrayList<>(count);
		for(String languageName : allLanguageNames.subList(0, count)){
			questionData.add(buildQuestionData(languageName));
		}
		return questionData;
	}
}
