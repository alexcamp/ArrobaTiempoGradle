package com.tecnonautica.utiles.basicos;

import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParseadorXML extends DefaultHandler {
	
	private HashMap mensaje = new HashMap();
	private String llaveMensaje;

	public HashMap parse(String texto) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		//La siguiente línea fue modificada por el ídolo pop por antonomasia, 
		//pues estaba utilizándose una clase obsoleta, y no permitía el manejo
		//de caracteres Unicode
		saxParser.parse(new InputSource(new StringReader(texto)), this);
		return mensaje;
	}

	public void startElement(
		String uri,
		String localName,
		String qName,
		Attributes attributes)
		throws SAXException {
			
			llaveMensaje = qName;
	}
	
	public void characters(char[] ch, int start, int length) {
		
		String valor = new String(ch, start, length);
		if (!mensaje.containsKey(llaveMensaje)){
			mensaje.put(llaveMensaje, valor);
		}
		
	}
	
}
