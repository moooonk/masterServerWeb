package com.masterserver.xml;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlHelper {
	private static DocumentBuilder builder;
	private static Transformer transformer;
	
	static {
		try {
			builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			transformer = TransformerFactory.newInstance().newTransformer();
//			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF8");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}
	}
	
	public static Document newDocment() {
		return builder.newDocument();
	}
	
	public static String trans(Element ele){
		StringWriter writer = new StringWriter();
		try {
			transformer.transform(new DOMSource(ele), new StreamResult(writer));
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}
}
