package xslt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class LanguageSchiencePressTransformator {

	/***
	 * Applies the language Science press xslt-transformation to all files in inputDir and saves the result (under the same filename) in outputDir.
	 * Will fail if there is a file in the inputDir, that is not an xml-file
	 * @param inputDir
	 * @param outputDir
	 * @throws TransformerException
	 * @throws IOException
	 */
	public void transform(File inputDir, File outputDir) throws TransformerException, IOException{
		TransformerFactory fac = TransformerFactory.newInstance();
		String xsltPath = "resources/xslt stylesheets/languageSciencePress.xslt";
		Source source = new StreamSource(new File(xsltPath));
		Transformer transformer = fac.newTransformer(source);
		for(File srcFile : inputDir.listFiles()){
			String xmlSrcPath = srcFile.getAbsolutePath();
			String xmlDstPath = outputDir.getAbsolutePath()+File.pathSeparator+srcFile.getName();
			StreamResult res = new StreamResult(new FileWriter(new File(xmlDstPath)));
			transformer.transform(new StreamSource(new File(xmlSrcPath)), res);
		}
	}
	
	public static void main(String[] args) throws TransformerException, IOException{
		LanguageSchiencePressTransformator transformer = new LanguageSchiencePressTransformator();
		File inputDir = new File("resources"+File.separator+"language science press data");
		File outputDir = new File("resources"+File.separator+"example xslt output");
		transformer.transform(inputDir, outputDir);
	}
}
