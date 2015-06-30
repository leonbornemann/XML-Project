package xmlquestion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import logic.Question;
import logic.QuestionGenerator;
import data_representation.Country;

/**
 * Class that contains all the necessary methods to 
 * handle the list of questions
 */
public class XMLQuestionListGenerator {
    
    /*
     * Empty constructor
     */
    public XMLQuestionListGenerator(){
        
    }

    /**
     * Creates a new question list
     * @param nQuestions
     * @return new question list
     * @throws Exception
     */
    public static XMLQuestionList generateNewQuestionList(int nQuestions) throws Exception {

        // Variables to store question elements
        QuestionGenerator qgen = new QuestionGenerator();
        List<String> answerList;
        List<Country> countryList;        
        XMLQuestionList xmlQl = new XMLQuestionList();        
        Collection<Question> qCollection = qgen.getDistinctQuestions(nQuestions);
        Question[] questionArray = qCollection.toArray((new Question[qCollection.size ()]));
        
         
        for(int i = 0; i<questionArray.length; i++) {
            
            Question q = questionArray[i];
            answerList = q.getWrongAnswers();
            countryList = q.getLanguageInfo().getSpokenIn();
            ArrayList<XMLCountry> xmlCountryList = new ArrayList<XMLCountry>();
            
            //Make new XMLQuestion
            Sentence s = new Sentence(q.getQuestionText(), q.getOriginal(),q.getTranslation());            
            Answers ans = new Answers(q.getRightAnswer(),answerList);
            
            for (Country c : countryList){
                XMLCountry xmlcountry = new XMLCountry(c.getName(),String.valueOf(c.getLattitude()),
                        String.valueOf(c.getLongitude()));
                
                xmlCountryList.add(xmlcountry);
            }
            
            //Create question and add it to XMLQuestionList
            XMLQuestion xmlq = new XMLQuestion(s,ans,xmlCountryList);                        
            xmlQl.addQuestion(xmlq);         
        }
                
        return xmlQl;
    }
}
