package xmlquestion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XMLUtilities {

    /**
     * Generates the xml representation of a question list
     * @param questionList
     * @return String containing the xml document
     */
    public static String questionListToXML(XMLQuestionList questionList){
        
        XStream xstream = new XStream(new DomDriver("UTF-8"));
        xstream.setMode(XStream.ID_REFERENCES);
        xstream.alias("sentence", Sentence.class);
        xstream.alias("answers", Answers.class);
        xstream.alias("country",XMLCountry.class);
        xstream.alias("xmlquestion", XMLQuestion.class);
        xstream.alias("xmlquestionlist",XMLQuestionList.class);
                
        return xstream.toXML(questionList);
        
    }
    
    /**
     * Makes a question list given its xml representation
     * @param XMLString
     * @return question list as XMLQuestionList
     */
    public static XMLQuestionList convertFromXML(String XMLString) {
        XMLQuestionList ql = null;
        XStream xstream = new XStream(new DomDriver("UTF-8"));
        xstream.setMode(XStream.ID_REFERENCES);
        xstream.alias("sentence", Sentence.class);
        xstream.alias("answers", Answers.class);
        xstream.alias("country",XMLCountry.class);
        xstream.alias("xmlquestion", XMLQuestion.class);
        xstream.alias("xmlquestionlist",XMLQuestionList.class);
        
        
        Object obj = xstream.fromXML(XMLString);
        
        if (obj instanceof XMLQuestionList) {
            ql = (XMLQuestionList) obj;                        
        }
        
        return ql;                
    }
    
    public static boolean saveStringToFile(String fileName, String saveString) {

        boolean saved = false;
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(
                    new FileWriter(fileName));

            try {
                bw.write(saveString);
                saved = true;
            }
            finally {
                bw.close();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        return saved;
    }
    
    public static String getStringFromFile(String fileName) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        try {
            br = new BufferedReader(new FileReader(fileName));

            try {
                String s;
                while((s = br.readLine()) != null) {

                    // add linefeed back since stripped by readline()
                    sb.append(s);
                    sb.append("\n");
                }
            }
            finally {
                br.close();
            }        
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

        return sb.toString();
    }

    
    public static String getStringFromInputStream(InputStream is){
        
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
 
        String line;
        try {
 
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
 
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
 
        return sb.toString();
  
    }
    
    public static boolean inputStreamToFile(InputStream inputStream, String path) {
        
        OutputStream outputStream = null;
        
        //System.out.println(path);
        
        try {
            // write the inputStream to a FileOutputStream
            outputStream = 
                        new FileOutputStream(new File(path));
     
            int read = 0;
            byte[] bytes = new byte[1024];
     
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }                 
            System.out.println("Done!");
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {            
            if (outputStream != null) {
                try {
                    // outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
     
            }            
        }
        return true;
    }
    
    
    
    public static void main(String[] args) throws Exception{
                        
        XMLQuestionList ql = XMLQuestionListGenerator.generateNewQuestionList(2);
        
        //Test xml methods        
        String questionXMLString = XMLUtilities.questionListToXML(ql);        
        XMLQuestionList rebuiltQuestion = XMLUtilities.convertFromXML(questionXMLString);
        
        
        if (ql.toString().equals(rebuiltQuestion.toString())) {
            System.out.println("Conversion to and from XML succesful.");
        } 
        else {
            System.out.println("Error when converting to or from XML");
        }
        
        //Create files
        if (saveStringToFile("questionSet.txt", questionXMLString)){
            System.out.println("XML File saved successfully");
        }
        
        String qset = XMLUtilities.getStringFromFile("questionSet.txt");
        System.out.println(qset);
        
    }

}
