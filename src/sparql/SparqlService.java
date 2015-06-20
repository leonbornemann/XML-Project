package sparql;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.basex.core.BaseXException;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.RDFNode;

import data_exploration.DataExploration;

public class SparqlService {

	/**
	 * An examplary use case of SPARQL which retrieves all rdf:type information
	 * about Barack Obama
	 */

	private static Set<String> unsupportedLanguages;
	private static StringBuilder sb;

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

	public static void getSpokenIn(String language) throws IOException {
		// BufferedWriter bw = new BufferedWriter(new
		// FileWriter("resources/xml-database/extraInformation.xml"));
		Set<String> languagesAlreadyInData = new HashSet<String>();
		Set<String> countriesAlreadyInData = new HashSet<String>();
		String service = "http://dbpedia.org/sparql";
		String sparqlQuery = "SELECT * WHERE {<http://dbpedia.org/resource/"
				+ language
				+ "_language> <http://dbpedia.org/ontology/spokenIn> ?country. "
				+ "OPTIONAL {?country <http://dbpedia.org/property/latd> ?latd. } "
				+ "OPTIONAL {?country <http://dbpedia.org/property/longd> ?longd. }"
				+ "OPTIONAL {?country <http://dbpedia.org/property/latitude> ?latitude. }"
				+ "OPTIONAL {?country <http://dbpedia.org/property/longitude> ?longitude} "
				+ "OPTIONAL {?country <http://www.w3.org/2003/01/geo/wgs84_pos#lat> ?geoLat}"
				+ "OPTIONAL {?country <http://www.w3.org/2003/01/geo/wgs84_pos#long> ?geoLong}}";

		QueryExecution qe = QueryExecutionFactory.sparqlService(service,
				sparqlQuery);
		ResultSet resultSet = qe.execSelect();
		if (resultSet.hasNext()) {
			// System.out.println(language);
		} else {
			unsupportedLanguages.add(language);
		}

		while (resultSet.hasNext()) {
			//nur ein language Tag pro Sprache
			if (!languagesAlreadyInData.contains(language)) {
				sb.append("<language language='" + language + "'>");
				languagesAlreadyInData.add(language);
			}
			QuerySolution next = resultSet.next();
			RDFNode country = next.get("country");
			RDFNode longitude = next.get("longd");
			RDFNode latitude = next.get("latd");
			
			// keine doppelten country tags
			if (!countriesAlreadyInData.contains(country.toString())) {

				/*
				 * Es gibt unterschiedliche Präfixe für longitude und latitude:
				 * dbpprop:longitude dbpprop:longd geo:long Von daher ist die
				 * Überprüfung, ob mindestens eine davon nicht leer ist, etwas
				 * unschön.
				 */
				if (longitude == null) {
					longitude = next.get("longitude");
					if (longitude == null) {
						longitude = next.get("geoLong");
					}
				}
				if (latitude == null) {
					latitude = next.get("latitude");
					if (latitude == null) {
						latitude = next.get("geoLat");
					}
				}
				if (country != null && latitude != null && longitude != null) {
					// System.out.println(country.toString() + ": (" +
					// stringToNumber(latitude.toString()) + ", "
					// + stringToNumber(longitude.toString())+")");
					sb.append("<spokenIn><country>" + country.toString()
							+ "</country><long>" + longitude.toString()
							+ "</long><lat>" + latitude.toString()
							+ "</lat></spokenIn>");
				}
				countriesAlreadyInData.add(country.toString());
			}

		}
		sb.append("</language>");

	}

	private static double stringToNumber(String string) {
		String subString = string.substring(0, string.indexOf("^"));
		return Double.parseDouble(subString);
	}

	public static void main(String[] args) throws IOException {
		// getSpokenIn("Turkana");
		sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<extraInformation xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:noNamespaceSchemaLocation='extraInformation.xsd'>");
		unsupportedLanguages = new HashSet<String>();
		Set<String> languages = DataExploration.getLanguages("DBExample");
		for (String language : languages) {
			if (!language.contains(" ")) {
				getSpokenIn(language);
				// System.out.println();
			} else {
				unsupportedLanguages.add(language);
			}
		}
		sb.append("</extraInformation>");
		System.out.println(sb.toString());
		// System.out.println("The following languages are not available:\n");
		// for (String unsupportedLanguage : unsupportedLanguages) {
		// System.out.println(unsupportedLanguage);
		// }
		// System.out.println("Number of SupportedLanguages: "+(languages.size()-unsupportedLanguages.size()));
		// System.out.println("Number of UnsupportedLanguages: "+unsupportedLanguages.size());
	}
}
