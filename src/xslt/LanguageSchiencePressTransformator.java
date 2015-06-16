package xslt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
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
		//String xsltPath = "resources/xslt stylesheets/languageSciencePress.xslt";
		String xsltPath = "resources/xslt stylesheets/test.xslt";
		File tempDir = new File("resources"+File.separator+"example xslt output");
		applyTransformation(inputDir, tempDir, xsltPath);
		//apply the cleanup:
		//String xsltCleanupPath = "resources/xslt stylesheets/languageSciencePress cleanup.xslt";
		//applyTransformation(tempDir, outputDir, xsltCleanupPath);
	}

	private void applyTransformation(File inputDir, File outputDir,String xsltPath) throws IOException,TransformerException {
		TransformerFactory fac = TransformerFactory.newInstance();
		Source source = new StreamSource(new File(xsltPath));
		Transformer transformer = fac.newTransformer(source);
		for(File srcFile : inputDir.listFiles()){
			String xmlSrcPath = srcFile.getAbsolutePath();
			String xmlDstPath = outputDir.getAbsolutePath()+File.separator+srcFile.getName();
			StreamResult res = new StreamResult(new FileWriter(new File(xmlDstPath)));
			transformer.transform(new StreamSource(new File(xmlSrcPath)), res);
		}
	}
	
	public static void main(String[] args) throws TransformerException, IOException{
		LanguageSchiencePressTransformator transformer = new LanguageSchiencePressTransformator();
		//File inputDir = new File("resources"+File.separator+"language science press data");
		File inputDir = new File("resources"+File.separator+"example xml files");
		File outputDir = new File("resources"+File.separator+"xml-database");
		transformer.transform(inputDir, outputDir);
	}
}
