package sparql;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;

public class SparqlService {

	/**
	 * An examplary use case of SPARQL which retrieves all rdf:type information about Barack Obama
	 */
	private static void example() {
		String service = "http://dbpedia.org/sparql";
		String sparqlQuery = "SELECT * WHERE {<http://dbpedia.org/resource/Barack_Obama> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?val}";

		QueryExecution qe = QueryExecutionFactory.sparqlService(service,
				sparqlQuery);
		ResultSet resultSet = qe.execSelect();
		while (resultSet.hasNext()) {
			QuerySolution next = resultSet.next();
			RDFNode rdfNode = next.get("val");
			System.out.println(rdfNode.toString());
		}
	}

	public static void getSpokenIn(String language) {
		String service = "http://dbpedia.org/sparql";
		String sparqlQuery = "SELECT * WHERE {<http://dbpedia.org/resource/"
				+ language
				+ "_language> <http://dbpedia.org/ontology/spokenIn> ?country. ?country <http://dbpedia.org/property/latd> ?latitude . ?country <http://dbpedia.org/property/longd> ?longitude}";

		QueryExecution qe = QueryExecutionFactory.sparqlService(service,
				sparqlQuery);
		ResultSet resultSet = qe.execSelect();
		while (resultSet.hasNext()) {
			QuerySolution next = resultSet.next();
			RDFNode country = next.get("country");
			RDFNode longitude = next.get("longitude");
			RDFNode latitude = next.get("latitude");
			System.out.println(country.toString() + ": " + stringToInt(latitude.toString()) + ", "
					+ stringToInt(longitude.toString()));
		}
	}
	
	private static int stringToInt(String string){
		String subString = string.substring(0, string.indexOf("^"));
		return Integer.parseInt(subString);
	}

	public static void main(String[] args) {
		getSpokenIn("Turkana");
	}
}
