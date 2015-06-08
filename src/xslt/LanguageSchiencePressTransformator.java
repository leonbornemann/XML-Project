package xslt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class LanguageSchiencePressTransformator {

	public static void main(String[] args) throws TransformerException, IOException{
		TransformerFactory fac = TransformerFactory.newInstance();
		String xsltPath = "resources/xslt stylesheets/languageSciencePress.xslt";
		Source source = new StreamSource(new File(xsltPath));
		Transformer transformer = fac.newTransformer(source);
		String xmlSrcRootPath = "resources/language science press data/";
		File srcRoot = new File(xmlSrcRootPath);
		for(File srcFile : srcRoot.listFiles()){
			String xmlSrcPath = srcFile.getAbsolutePath();
			String xmlDstPath = "resources/example xslt output/"+srcFile.getName();
			StreamResult res = new StreamResult(new FileWriter(new File(xmlDstPath)));
			transformer.transform(new StreamSource(new File(xmlSrcPath)), res);
		}
	}
}
