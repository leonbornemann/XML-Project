package logic;

import java.util.ArrayList;
import java.util.List;

/***
 * This class contains all data and functionality needed for questions and their processing
 * @author LeonBornemann
 *
 */
public class Question {

	private String questionText;
	private List<String> answers;
	private int correctAnswerIndex;

	/***
	 * 
	 * @param questionText The text to be printed to the user
	 * @param answers A List of answers to be presented to the user
	 * @param correctAnswerIndex the index of the correct answer in {@code answers}
	 */
	public Question(String questionText,List<String> answers,int correctAnswerIndex){
		this.questionText = questionText;
		this.answers=answers;
		this.correctAnswerIndex=correctAnswerIndex;
	}
	
	public String getQuestionText() {
		return questionText;
	}

	public List<String> getAllAnswers() {
		return new ArrayList<String>(answers);
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
		System.out.println(questionText);
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
