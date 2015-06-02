package data_exploration;

import java.util.HashMap;
import java.util.Map;

import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.Add;
import org.basex.core.cmd.CreateDB;
import org.basex.core.cmd.InfoDB;
import org.basex.core.cmd.XQuery;

public class DataExploration {

	private static Context context;
	
	public static void main(String[] args) throws BaseXException {
		context = new Context();
		String exampleFilePath = "resources/language science press data/";
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
	    executeAndPrintResult("for $doc in collection('"+dbName+"') let $file-path := base-uri($doc) return doc($file-path)//language/text()");

	    // Closes database context
	    context.close();
	}
	
	static void executeAndPrintResult(final String query) throws BaseXException {
	    String a = new XQuery(query).execute(context);
	    String[] languages = a.split("\n|\r\n");
	    Map<String,Integer> languageCount = new HashMap<>();
	    for(int i =0;i<languages.length;i++){
	    	if(languageCount.containsKey(languages[i])){
	    		languageCount.put(languages[i], languageCount.get(languages[i])+1);
	    	} else{
	    		languageCount.put(languages[i], 1);
	    	}
	    }
		for(String language:languageCount.keySet()){
			if(languageCount.get(language)>=10){
				System.out.println(language + " - " + languageCount.get(language));
			}
		}
	}
}
