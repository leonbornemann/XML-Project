package restservice;

import java.io.InputStream;

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

  public String questionListXML;
  
  
//This method is called if XML is requesting
 @GET
 @Path("question")
 public String sayXMLHello()  {
   
     ClassLoader cl = this.getClass().getClassLoader();
     InputStream is = cl.getResourceAsStream("questionSet.txt");
     
     String questionList = XMLUtilities.getStringFromInputStream(is);
         
     return questionList;
   
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