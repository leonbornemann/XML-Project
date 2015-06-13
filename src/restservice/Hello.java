package restservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


// http://localhost:8080/XML-Project/quiz/hello/question
@Path("/hello")
public class Hello {

  // This method is called if XML is requesting
  @GET
  @Path("question")
  public String sayXMLHello() {
    return "<?xml version=\"1.0\"?>" + "<block><question> What is this language? </question>"
    		+ "<sentence>Bla Bla Blu</sentence> <translation>Bli Bli Ble</translation></block>";
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