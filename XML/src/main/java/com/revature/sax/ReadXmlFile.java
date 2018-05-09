package com.revature.sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadXmlFile {

	public static void main(String[] args) {
		final String fileName = "src/main/resources/pokemon.xml";
		try {
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			
			DefaultHandler handler = new DefaultHandler() {
				
				boolean beforeName = false;
				boolean beforeType = false;
				boolean beforeEvolves = false;
				
				public void startElement(String uri, String localName, String qName, Attributes attributes) {
					System.out.println("Start Element :" + qName);

					if (qName.equalsIgnoreCase("NAME")) {
						beforeName = true;
					}

					if (qName.equalsIgnoreCase("TYPE")) {
						beforeType = true;
					}

					if (qName.equalsIgnoreCase("EVOLVES")) {
						beforeEvolves = true;
					}
				}
				
				public void endElement(String uri, String localName,
						String qName) throws SAXException {

						System.out.println("End Element :" + qName);

					}
				
				public void characters(char ch[], int start, int length) throws SAXException {

					if (beforeName) {
						System.out.println("Name: " + new String(ch, start, length));
						beforeName = false;
					}

					if (beforeType) {
						System.out.println("Type: " + new String(ch, start, length));
						beforeType = false;
					}

					if (beforeEvolves) {
						System.out.println("Evolves: " + new String(ch, start, length));
						beforeEvolves = false;
					}

				}

			     };
			     parser.parse("src/main/resources/pokemon.xml", handler);
			} catch (Exception e) {
				e.printStackTrace();
		}
}}
