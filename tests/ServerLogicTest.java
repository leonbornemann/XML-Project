import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import logic.Question;
import logic.QuestionGenerator;

import org.basex.core.BaseXException;
import org.junit.Test;

import database.exceptions.UnknownLanguageException;


public class ServerLogicTest {

	@Test
	public void testAllLanguages() throws BaseXException {
		QuestionGenerator gen = new QuestionGenerator();
		List<String> allowedLanguages = gen.getValidLanguageNames();
		for(String lang : allowedLanguages){
			try {
				Question question = gen.getQuestion(lang);
				assertEquals(lang,question.getLanguageInfo().getLanguageName());
				assertEquals(lang,question.getRightAnswer());
				assertTrue(question.getLanguageInfo().getSpokenIn().size()>=1);
			} catch (UnknownLanguageException e) {
				assertTrue(false);
			}
		}
	}
	
	@Test
	public void testDistinctQuestions() throws BaseXException, UnknownLanguageException{
		QuestionGenerator gen = new QuestionGenerator();
		Collection<Question> questions = gen.getDistinctQuestions(10);
		Set<String> questionTexts = new HashSet<>();
		for(Question question  :questions){
			assert(!questionTexts.contains(question.getQuestionText()));
			questionTexts.add(question.getQuestionText());
			assertTrue(question.getLanguageInfo().getSpokenIn().size()>=1);
		}
	}

}
