package logic;

import java.util.ArrayList;
import java.util.List;

public class QuestionData {

	private String questionText;
	private List<String> answers;
	private int correctAnswerIndex;

	public QuestionData(String questionText,List<String> answers,int correctAnswerIndex){
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

	private String getRightAnswer() {
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
