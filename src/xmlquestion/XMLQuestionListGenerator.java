package xmlquestion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

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
    
    public static XMLQuestionList generateRandomQuestions(XMLQuestionList questionSet, int nQuestions){
        
        ArrayList<XMLQuestion> qList = questionSet.questionList;
        ArrayList<XMLQuestion> retList = new ArrayList<XMLQuestion>();
        
        int[] selectedQuestions = new int[nQuestions];
        
        //Array init
        for(int i = 0; i<nQuestions; i++)
            selectedQuestions[i] = -1;
        
        //Create as many random indexes as nQuestions
        Random rand = new Random();
        
        for (int i = 0; i<nQuestions; i++){
            
            int randomNum = rand.nextInt(44);
            
            //Make sure the question was not already selected
            while(alreadySelected(randomNum,selectedQuestions)){
                randomNum = rand.nextInt(44);
            }
            
            //Add selected question to question list
            selectedQuestions[i] = randomNum;
            
            XMLQuestion newQuestion = qList.get(randomNum);
            retList.add(newQuestion);                                    
        }
        
        
        return new XMLQuestionList(retList);
    }
    
    
    public static XMLQuestionList cleanQuestionList(XMLQuestionList ql){
        
        for(XMLQuestion q : ql.questionList) {
            
            Sentence questionSentence = q.getQuestionSentence();
            String original = questionSentence.getOriginal();
            String newOriginal = original.replace('?', ' ');
            newOriginal = newOriginal.replace('=', ' ');
            questionSentence.setOriginal(newOriginal);
            q.setQuestionSentence(questionSentence);
        }
                        
        return ql;
        
    }
    
    private static boolean alreadySelected(int value, int[] questionArray){
        
        for (int i=0; i<questionArray.length; i++){
            if (questionArray[i] == value)
                return true;
        }
        return false;
    }
    
}
