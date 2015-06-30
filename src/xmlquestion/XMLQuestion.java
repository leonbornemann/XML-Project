package xmlquestion;

import java.util.ArrayList;


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
}
