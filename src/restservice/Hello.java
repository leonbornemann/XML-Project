package restservice;

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
     InputStream is = cl.getResourceAsStream("../resourcesDB/questionAll.txt");
     System.out.println(cl.getResource("../resourcesDB/questionAll.txt").getPath());
     String questionList = XMLUtilities.getStringFromInputStream(is);
     XMLQuestionList xmlQL = XMLUtilities.convertFromXML(questionList);
     xmlQL = XMLQuestionListGenerator.cleanQuestionList(xmlQL);
     XMLQuestionList finalQuestionList = XMLQuestionListGenerator.generateRandomQuestions(xmlQL, 2);
     
     return XMLUtilities.questionListToXML(finalQuestionList);

   
 }

 @GET
 @Produces(MediaType.TEXT_XML)
 @Path("question5")
 public String makeQuestionSet5() throws Exception {
        
     ClassLoader cl = this.getClass().getClassLoader();
     InputStream is = cl.getResourceAsStream("../resourcesDB/questionAll.txt");
     System.out.println(cl.getResource("../resourcesDB/questionAll.txt").getPath());
     String questionList = XMLUtilities.getStringFromInputStream(is);
     XMLQuestionList xmlQL = XMLUtilities.convertFromXML(questionList);
     xmlQL = XMLQuestionListGenerator.cleanQuestionList(xmlQL);
     XMLQuestionList finalQuestionList = XMLQuestionListGenerator.generateRandomQuestions(xmlQL, 5);
     
     return XMLUtilities.questionListToXML(finalQuestionList);
 }
 
 @GET
 @Produces(MediaType.TEXT_XML)
 @Path("question10")
 public String makeQuestionSet10() throws Exception {
        
     ClassLoader cl = this.getClass().getClassLoader();
     InputStream is = cl.getResourceAsStream("../resourcesDB/questionAll.txt");
     System.out.println(cl.getResource("../resourcesDB/questionAll.txt").getPath());
     String questionList = XMLUtilities.getStringFromInputStream(is);
     XMLQuestionList xmlQL = XMLUtilities.convertFromXML(questionList);
     xmlQL = XMLQuestionListGenerator.cleanQuestionList(xmlQL);
     XMLQuestionList finalQuestionList = XMLQuestionListGenerator.generateRandomQuestions(xmlQL, 10);
     
     return XMLUtilities.questionListToXML(finalQuestionList);
   
 }

 @GET
 @Produces(MediaType.TEXT_XML)
 @Path("questionset")
 public String makeQuestionSetParam(String nquestions) throws Exception {

     //Retrieve parameter and store as integer
     Integer i = Integer.parseInt(nquestions);

     ClassLoader cl = this.getClass().getClassLoader();
     InputStream is = cl.getResourceAsStream("../resourcesDB/questionAll.txt");
     System.out.println(cl.getResource("../resourcesDB/questionAll.txt").getPath());
     String questionList = XMLUtilities.getStringFromInputStream(is);
     XMLQuestionList xmlQL = XMLUtilities.convertFromXML(questionList);
     XMLQuestionList finalQuestionList = XMLQuestionListGenerator.generateRandomQuestions(xmlQL, i.intValue());
     
     return XMLUtilities.questionListToXML(finalQuestionList);
   
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