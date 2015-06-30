package xmlquestion;

import java.util.ArrayList;

public class XMLQuestionList {
    
    public ArrayList<XMLQuestion> questionList;
    
    public XMLQuestionList(){
        this.questionList = new ArrayList<XMLQuestion>();
    }
    
    public boolean addQuestion(XMLQuestion q) {
        this.questionList.add(q);
        return true;
    }
    
    /**
     * Removes a question from the list
     * @param index
     * @return
     */
    public boolean removeQuestion(int index){
        if (this.questionList.isEmpty() || (this.questionList.size() <= index))
            return false;
        else{
            this.questionList.remove(index);
            return true;
        }
    }
    
    
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for (XMLQuestion q : this.questionList){
            sb.append(q.toString());
            sb.append("\n");
        }
        
        return sb.toString();
    }
}
