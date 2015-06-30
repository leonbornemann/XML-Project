package xmlquestion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import logic.Question;
import logic.QuestionGenerator;
import data_representation.Country;


public class XMLQuestion {

    private Sentence questionSentence;
    private Answers questionAnswers;
    private ArrayList<XMLCountry> countryList = new ArrayList<XMLCountry>();
    
    /*------------------------------------
    * Constructors
    * ------------------------------------*/
    public XMLQuestion(Sentence questionSentence, 
            Answers questionAnswers, ArrayList<XMLCountry> countryList){
        
        this.setQuestionSentence(questionSentence);
        this.setQuestionAnswers(questionAnswers);
        this.setCountryList(countryList);
    }
    
    public XMLQuestion(Sentence questionSentence, Answers questionAnswers){
        this.setQuestionSentence(questionSentence);
        this.setQuestionAnswers(questionAnswers);
        this.setCountryList(new ArrayList<XMLCountry>());
    }

    /*------------------------------------
    * Getters and setters
    * ------------------------------------*/
    public Sentence getQuestionSentence() {
        return questionSentence;
    }

    public void setQuestionSentence(Sentence questionSentence) {
        this.questionSentence = questionSentence;
    }

    public Answers getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(Answers questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    public ArrayList<XMLCountry> getCountryList() {
        return countryList;
    }

    public void setCountryList(ArrayList<XMLCountry> countryList) {
        this.countryList = countryList;
    }
    
    /*-------------------------
     * Class methods
     --------------------------*/
    public boolean addCountry(XMLCountry c){
        if (!this.getCountryList().isEmpty()){
            this.getCountryList().add(c);
            return true;
        }
        return false;
    }
    
    @Override
    public String toString(){
        
        ArrayList<XMLCountry> countryList = this.getCountryList();        
        StringBuilder countryCL = new StringBuilder();
        
        for (XMLCountry c : countryList){
            countryCL.append(c.toString());
            countryCL.append("\n");
        }
        
        String ret = "Question: \n" + this.getQuestionSentence().toString() +
                this.getQuestionAnswers().toString() + countryCL.toString();
        
        return ret;
    }
    
    public static void main(String[] args) throws Exception{
        
        QuestionGenerator qgen = new QuestionGenerator();
        List<String> answerList;
        List<Country> countryList;
                
        Collection<Question> qCollection = qgen.getDistinctQuestions(2);
        Question[] questionArray = qCollection.toArray((new Question[qCollection.size ()]));
        
         
        for(int i = 0; i<questionArray.length; i++) {
            
            Question q = questionArray[i];
            answerList = q.getAllAnswers();
            System.out.println("Question text " + q.getQuestionText());
            System.out.println("Original " + q.getOriginal());
            System.out.println("Translation " + q.getTranslation());
            System.out.println("Answers: ");
            for (String s : answerList) {
                System.out.println(s);
            }
            System.out.println("Correct answer: " + q.getRightAnswer());
            System.out.println("Additional language info: ");
            countryList = q.getLanguageInfo().getSpokenIn();
            System.out.println("Country info: ");
            for (Country c : countryList){
                System.out.println("Name: " + c.getName());
                System.out.println("Lattitude: " + c.getLattitude());
                System.out.println("Longitude: " + c.getLongitude());
            }
            
        }
        
    }


}
