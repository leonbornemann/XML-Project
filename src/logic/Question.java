package logic;

import java.util.ArrayList;
import java.util.List;

import data_representation.DBPediaLanguageInfo;

/***
 * This class contains all data and functionality needed for questions and their processing
 * @author LeonBornemann
 *
 */
public class Question {

	private List<String> answers;
	private int correctAnswerIndex;
	private DBPediaLanguageInfo languageInfo;
	private String original;
	private String translation;

	/***
	 * 
	 * @param original The sentence in the foreign language
	 * @param translation The english translation
	 * @param answers A List of answers to be presented to the user
	 * @param correctAnswerIndex the index of the correct answer in {@code answers}
	 * @param languageInfo All Information concerning the language, that this question is about. Note: languageInfo.getLanguageName() should return the same string as this.getRightAnswer()
	 */
	public Question(String original,String translation,List<String> answers,int correctAnswerIndex,DBPediaLanguageInfo languageInfo){
		this.original = original;
		this.translation = translation;
		this.answers=answers;
		this.correctAnswerIndex=correctAnswerIndex;
		this.languageInfo = languageInfo;
		assert(languageInfo.getLanguageName().equals(getRightAnswer()));
	}
	
	public String getQuestionText() {
		return "The sentence \""+ original + "\" is in which language?";
	}
	
	public String getOriginal() {
		return original;
	}

	public String getTranslation() {
		return translation;
	}

	public List<String> getAllAnswers() {
		return new ArrayList<String>(answers);
	}
	
	public DBPediaLanguageInfo getLanguageInfo(){
		return languageInfo;
	}
	
	public List<String> getWrongAnswers(){
		ArrayList<String> wrongAnswers = new ArrayList<String>(answers);
		wrongAnswers.remove(getRightAnswer());
		return wrongAnswers;
	}

	public String getRightAnswer() {
		return answers.get(correctAnswerIndex);
	}

	public void printQuestion() {
		System.out.println(getQuestionText());
		System.out.println("possibleAnswers: ");
		for(int i=0;i<answers.size();i++){
			String toPrint = i+") " + answers.get(i);
			if(i==correctAnswerIndex){
				toPrint = toPrint + "[correct answer]";
			}
			System.out.println(toPrint );
		}
	}
	
}
