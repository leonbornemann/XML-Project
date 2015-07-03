package restservice;

import java.io.File;
import java.io.InputStream;
//import java.nio.file.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import xmlquestion.XMLQuestionList;
import xmlquestion.XMLQuestionListGenerator;
import xmlquestion.XMLUtilities;


// http://localhost:8080/XML-Project/quiz/hello/question
@Path("/hello")
public class Hello {

   
//This method is called if XML is requesting
 @GET
 @Produces(MediaType.TEXT_XML)
 @Path("question")
 public String sayXMLHello()  {
   
     ClassLoader cl = this.getClass().getClassLoader();
     InputStream is = cl.getResourceAsStream("../resourcesDB/questionSet.txt");
     System.out.println(cl.getResource("../resourcesDB/questionSet.txt").getPath());
     String questionList = XMLUtilities.getStringFromInputStream(is);
         
     return questionList;
   
 }

//This method is called if XML is requesting
@GET
@Produces(MediaType.TEXT_XML)
@Path("questionAll")
public String allQuestions()  {
  
    ClassLoader cl = this.getClass().getClassLoader();
    InputStream is = cl.getResourceAsStream("../resourcesDB/questionAll.txt");
    System.out.println(cl.getResource("../resourcesDB/questionAll.txt").getPath());
    String questionList = XMLUtilities.getStringFromInputStream(is);
        
    return questionList;
  
}
 
 
 @GET
 @Produces(MediaType.TEXT_XML)
 @Path("question2")
 public String makeQuestionSet2() throws Exception {
          
     ClassLoader cl = this.getClass().getClassLoader();
     InputStream is = cl.getResourceAsStream("../resourcesDB/languageSciencePress-database/dahl.xml");
               
     System.out.println(cl.getResource("../resourcesDB/languageSciencePress-database/dahl.xml").getPath());
     
     String dbFilepath = this.getClass().getClassLoader().getResource("../resourcesDB/languageSciencePress-database/dahl.xml").getFile();
     File file = new File(dbFilepath);
     System.out.println("The path is: " + dbFilepath);
     System.out.println("Exists: " + file.exists());
     System.out.println("Is directory: " + file.isDirectory());
     
     String questionList = XMLUtilities.getStringFromInputStream(is);
     
     
     return questionList;
     
     /*XMLQuestionList qlist = XMLQuestionListGenerator.generateNewQuestionList(2);
     
     String result = XMLUtilities.questionListToXML(qlist);
     return result;*/
   
 }

 @GET
 @Produces(MediaType.TEXT_XML)
 @Path("question5")
 public String makeQuestionSet5() throws Exception {
        
     XMLQuestionList qlist = XMLQuestionListGenerator.generateNewQuestionList(2);
     
     String result = XMLUtilities.questionListToXML(qlist);
     return result;
   
 }
 
 @GET
 @Produces(MediaType.TEXT_XML)
 @Path("question10")
 public String makeQuestionSet10() throws Exception {
        
     XMLQuestionList qlist = XMLQuestionListGenerator.generateNewQuestionList(10);
     
     String result = XMLUtilities.questionListToXML(qlist);
     return result;
   
 }

 
  // This method is called if HTML is requesting
  @GET
  @Produces(MediaType.TEXT_XML)
  @Path("welcome")
  public String sayWelcomeGeek() {
    return "<html> " + "<title>" + "Hello Jersey" + "</title>"
        + "<body><h1>" + "Hello Geek" + "</h1></body>" + "</html> ";
  }

} 