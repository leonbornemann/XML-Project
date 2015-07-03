package database;

import logic.QuestionGenerator;

import org.basex.core.BaseXException;

import xmlquestion.XMLQuestionList;
import xmlquestion.XMLQuestionListGenerator;
import xmlquestion.XMLUtilities;

public class Main {

	public static void main(String[] args) throws BaseXException, Exception{
		XMLQuestionList questionList = XMLQuestionListGenerator.generateNewQuestionList(new QuestionGenerator().getValidLanguageNames().size());
		String asXML = XMLUtilities.questionListToXML(questionList);
		XMLUtilities.saveStringToFile("WebContent/WEB-INF/resourcesDB/questionAll.txt", asXML);
	}
}
