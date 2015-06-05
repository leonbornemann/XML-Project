package xslt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Example {

	public static void main(String[] args) throws TransformerException, IOException{
		TransformerFactory fac = TransformerFactory.newInstance();
		String xsltPath = "resources/xslt stylesheets/test.xslt";
		Source source = new StreamSource(new File(xsltPath));
		Transformer transformer = fac.newTransformer(source);
		String xmlSrcPath = "resources/example xml files/input1.xml";
		String xmlDstPath = "resources/example xml files/output1.xml";
		StreamResult res = new StreamResult(new FileWriter(new File(xmlDstPath)));
		transformer.transform(new StreamSource(new File(xmlSrcPath)), res);
	}
}
