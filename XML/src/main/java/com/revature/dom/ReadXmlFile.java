package com.revature.dom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXmlFile {

	public static void main(String[] args) {
		final String fileName = "src/main/resources/pokemon.xml";
		try {
			// Get a reference to the file
			File xmlFile = new File(fileName);
			
			// Get a reference to a DocumentBuilderFactory
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			
			// Instantiate a DocumentBuilder from the dbFactory
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			// Create the Document object
			Document doc = dBuilder.parse(xmlFile);
			
			// Optional, but recommended
			doc.getDocumentElement().normalize();
			
			System.out.println("Root Element: " + doc.getDocumentElement().getNodeName());
			
			System.out.println("-------------------------");
			
			NodeList list = doc.getElementsByTagName("pokemon");
			
			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				System.out.println("\nCurrent Element: " + node.getNodeName());
				
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					
					System.out.println("Id: " + element.getAttribute("id"));
					System.out.println("Name: " + element.getElementsByTagName("name").item(0).getTextContent());
					System.out.println("Evolves: " + element.getElementsByTagName("evolves").item(0).getTextContent());
					if (node.hasChildNodes()) {
						System.out.println("Primary Type: " + element.getElementsByTagName("primary").item(0).getTextContent());
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
