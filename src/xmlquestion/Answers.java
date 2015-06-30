package xmlquestion;

import java.util.List;

public class Answers {
    
    private String right;
    private String wrong1;
    private String wrong2;
    private String wrong3;
    
    public Answers(String right, String wrong1, String wrong2, String wrong3){
        this.setRight(right);
        this.setWrong1(wrong1);
        this.setWrong2(wrong2);
        this.setWrong3(wrong3);
    }
    
    public Answers(String right, List<String> wrongAnswers){
        this.setRight(right); 
        this.setWrong1(wrongAnswers.get(0));
        this.setWrong2(wrongAnswers.get(1));
        this.setWrong3(wrongAnswers.get(2));
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public String getWrong1() {
        return wrong1;
    }

    public void setWrong1(String wrong1) {
        this.wrong1 = wrong1;
    }

    public String getWrong2() {
        return wrong2;
    }

    public void setWrong2(String wrong2) {
        this.wrong2 = wrong2;
    }

    public String getWrong3() {
        return wrong3;
    }

    public void setWrong3(String wrong3) {
        this.wrong3 = wrong3;
    }

    @Override
    public String toString(){
        String ret = "Right answer: " + this.getRight() + "\n"
                + "Wrong answer 1: " + this.getWrong1() + "\n"
                + "Wrong answer 2: " + this.getWrong2() + "\n"
                + "Wrong answer 3: " + this.getWrong3() + "\n";
        
        return ret;
    }
}
