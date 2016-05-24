/*
 * Creado el 3/06/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.interfaces.atiempo.TR800S;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TR800SParser {
	
	//	No generics
	private TR800S tr800s;
	private String tr800sXML;
	private Document dom;

	public TR800S parse(String tr800sXML) {
		
		this.tr800sXML = tr800sXML;
		
		//parse the xml file and get the dom object
		parseXmlFile();
		
		//get each employee element and create a Employee object
		return parseDocument();
		
	}
	
	private void parseXmlFile(){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			InputSource is = new InputSource();
		    is.setCharacterStream(new StringReader(tr800sXML));
			
			//parse using builder to get DOM representation of the XML file
			dom = db.parse(is);
			
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	
	private TR800S parseDocument(){
		//get the root elememt
		Element docEle = dom.getDocumentElement();
		
		//get a nodelist of <TR800S> elements, by default is 1 element
		NodeList nl = docEle.getElementsByTagName("man:createResponseWorkOrder");
		
//		for (int i = 0; i < nl.getLength(); i++) {
//		      Element element = (Element) nl.item(i);
//
//		      NodeList name = element.getElementsByTagName("XA_SOURCE_SYSTEMR");
//		      Element line = (Element) name.item(0);
//		      System.out.println("XA_SOURCE_SYSTEMR: " + getCharacterDataFromElement(line));
//
//		      NodeList title = element.getElementsByTagName("id_actuacion");
//		      line = (Element) title.item(0);
//		      System.out.println("id_actuacion: " + getCharacterDataFromElement(line));
//		}
		
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {
				
				//get the Interfaz element
				Element el = (Element)nl.item(0);
				
				//get the Interfaz object
				tr800s = getTR800S(el);
				
			}
		}
		return tr800s;
	}

	  public static String getCharacterDataFromElement(Element e) {
	    Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	      CharacterData cd = (CharacterData) child;
	      return cd.getData();
	    }
	    return "";
	  }

	/**
	 * I take an employee element and read the values in, create
	 * an Employee object and return it
	 * @param tr800sElement
	 * @return
	 */
	private TR800S getTR800S(Element tr800sElement) {
		
		tr800s = new TR800S();
		tr800s.setXASOURCESYSTEMR(getTextValue(tr800sElement,"XA_SOURCE_SYSTEMR"));
		tr800s.setIdActuacion(getTextValue(tr800sElement,"id_actuacion"));
		tr800s.setXAORDERATIEMPO(getTextValue(tr800sElement,"XA_ORDER_ATIEMPO"));
		tr800s.setXAORDERATIS(getTextValue(tr800sElement,"XA_ORDER_ATIS"));
		tr800s.setXAORDERCODERR(getTextValue(tr800sElement,"XA_ORDER_COD_ERR"));
		tr800s.setXAORDERDESCERR(getTextValue(tr800sElement,"XA_ORDER_DESC_ERR"));
		//forma de leer los atributos de un tag, se deja en el codigo por si algun dia se necesita
		//pero estas linea no son funcionales.
		String soapEnv = tr800sElement.getAttribute("xmlns:soapenv");
		String namespace = tr800sElement.getAttribute("xmlns:man");
		
		return tr800s;
	}


	/**
	 * I take a xml element and the tag name, look for the tag and get
	 * the text content 
	 * i.e for <employee><name>John</name></employee> xml snippet if
	 * the Element points to employee node and tagName is name I will return John  
	 * @param ele
	 * @param tagName
	 * @return
	 */
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}

	
	/**
	 * Calls getTextValue and returns a int value
	 * @param ele
	 * @param tagName
	 * @return
	 */
	private int getIntValue(Element ele, String tagName) {
		//in production application you would catch the exception
		return Integer.parseInt(getTextValue(ele,tagName));
	}

	public static void main(String[] args){
		//create an instance
		TR800SParser dpe = new TR800SParser();
		String respuesta = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:man=\"http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/\">   <soapenv:Header/>   <soapenv:Body>      <man:CreacionServicioResponse> 	    <XA_SOURCE_SYSTEMR>XA_SOURCE_SYSTEMR</XA_SOURCE_SYSTEMR>         <id_actuacion>id_actuacion</id_actuacion>         <XA_ORDER_ATIEMPO>XA_ORDER_ATIEMPO</XA_ORDER_ATIEMPO>         <XA_ORDER_ATIS>XA_ORDER_ATIS</XA_ORDER_ATIS>         <XA_ORDER_COD_ERR>XA_ORDER_COD_ERR</XA_ORDER_COD_ERR>         <!--Optional:-->         <XA_ORDER_DESC_ERR>XA_ORDER_DESC_ERR</XA_ORDER_DESC_ERR>      </man:CreacionServicioResponse>   </soapenv:Body></soapenv:Envelope>";
		String respuesta2 = "<Personnel><Employee type=\"permanent\"><Name>Seagull</Name><Id>3674</Id><Age>34</Age></Employee><Employee type=\"contract\"><Name>Robin</Name><Id>3675</Id><Age>25</Age></Employee><Employee type=\"contract\"><Name>Crow</Name><Id>3676</Id><Age>28</Age></Employee></Personnel>";
		try {
			String respuestafinal = java.net.URLEncoder.encode(respuesta2, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		//call run example
		TR800S res = dpe.parse(respuesta);
		System.out.println(res);
	}


	
}
