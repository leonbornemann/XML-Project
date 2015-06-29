import static org.junit.Assert.*;

import java.util.List;

import logic.Question;
import logic.QuestionGenerator;

import org.basex.core.BaseXException;
import org.junit.Test;

import database.exceptions.UnknownLanguageException;


public class ServerLogic {

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
				System.out.println(question.getOriginal());
				System.out.println(question.getTranslation());
			} catch (UnknownLanguageException e) {
				assertTrue(false);
			}
		}
	}

}
