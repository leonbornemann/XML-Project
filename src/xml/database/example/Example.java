package xml.database.example;

import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.Add;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.DropDB;
import org.basex.core.cmd.InfoDB;
import org.basex.core.cmd.List;
import org.basex.core.cmd.XQuery;

public class Example {

	private static Context context;

	/**
	 * This class shows the basic interaction with the basex xml database java api. Sources:
	 * http://docs.basex.org/wiki/Java_Examples - many examples considering database creation and access from java
	 * http://docs.basex.org/wiki/Startup - provides the download link of the .jar
	 * 
	 * TODO: Licence checken ob wir in unserem Git-Projekt darauf hinweisen müssen, dass wir basex verwenden!
	 * 
	 * @param args
	 * @throws BaseXException
	 */
	public static void main(String[] args) throws BaseXException {
		context = new Context();
		String exampleFilePath = "example xml files/";
		String dbName = "DBExample";
		//creating a new database from a file:
		System.out.println("--------------------------Creating database, adding files and printing info-------------------------");
	    new CreateDB(dbName).execute(context);
	    new Add("",exampleFilePath).execute(context);
	    System.out.print(new InfoDB().execute(context));
	    System.out.println("-----------------------------------------------------------------------");
	    System.out.println("--------------------------Executing querys-----------------------------"); 
	    System.out.println("-----------------------------------------------------------------------");
	    System.out.println();
	    // List all documents in the database
	    System.out.println("-------------------------Files in database:----------------------------");
	    executeAndPrintResult("for $doc in collection('"+dbName+"') return base-uri($doc)");
	    // print all text contents of 'child'-nodes in any document:
	    System.out.println("-------------------------Text stored in 'child' nodes:------------------");
	    executeAndPrintResult("for $doc in collection('"+dbName+"') let $file-path := base-uri($doc) return doc($file-path)//child/text()");
	    // This drops the database TODO: rausfinden ob das nötig ist?
	    System.out.println("-----------------------------------------------------------------------");
	    System.out.println("-------------------------Finished Executing Queries--------------------");
	    System.out.println("-----------------------------------------------------------------------");
	    System.out.println();
	    System.out.println("--------------------------Dropping the database--------------------------------------------------------");
	    new DropDB("DBExample").execute(context);
	    // Show all existing databases
	    System.out.println("Show existing databases:");
	    System.out.print(new List().execute(context));
	    // Closes database context
	    context.close();
	}
	
	/**
	   * This method evaluates a query by using the database command.
	   * The results are automatically serialized and printed.
	   * @param query query to be evaluated
	   * @throws BaseXException if a database command fails
	   */
	  static void executeAndPrintResult(final String query) throws BaseXException {
	    System.out.println(new XQuery(query).execute(context));
	  }

}
