package xmlquestion;

public class Sentence {
    
    private String text;
    private String original;
    private String translation;
    
    public Sentence(String text, String original, String translation) {
        this.setText(text);
        this.setOriginal(original);
        this.setTranslation(translation);
    }
    
    public Sentence(String original, String translation) {
        this.setText("");
        this.setOriginal(original);
        this.setTranslation(translation);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
    
    @Override
    public String toString(){
        String ret = "Question sentence: " + this.getText() +"\n"
                + "Original: " + this.getOriginal() + "\n"
                + "Translation: "+ this.getTranslation() + "\n";
         
        return ret;       
    
    }
}
