/*
 * Creado el 4/06/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.interfaces.atiempo.TR800S;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class PARSER {

	public static void main(String arg[]) throws Exception{
	    String xmlRecords = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:man=\"http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/\">   <soapenv:Header/>   <soapenv:Body>      <man:CreacionServicioResponse> 	    <XA_SOURCE_SYSTEMR>XA_SOURCE_SYSTEMR</XA_SOURCE_SYSTEMR>         <id_actuacion>id_actuacion</id_actuacion>         <XA_ORDER_ATIEMPO>XA_ORDER_ATIEMPO</XA_ORDER_ATIEMPO>         <XA_ORDER_ATIS>XA_ORDER_ATIS</XA_ORDER_ATIS>         <XA_ORDER_COD_ERR>XA_ORDER_COD_ERR</XA_ORDER_COD_ERR>         <!--Optional:-->         <XA_ORDER_DESC_ERR>XA_ORDER_DESC_ERR</XA_ORDER_DESC_ERR>      </man:CreacionServicioResponse>   </soapenv:Body></soapenv:Envelope>";

	    DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
	    InputSource is = new InputSource();
	    is.setCharacterStream(new StringReader(xmlRecords));

	    Document doc = db.parse(is);
	    NodeList nodes = doc.getElementsByTagName("man:CreacionServicioResponse");

	    for (int i = 0; i < nodes.getLength(); i++) {
	      Element element = (Element) nodes.item(i);

	      NodeList name = element.getElementsByTagName("XA_SOURCE_SYSTEMR");
	      Element line = (Element) name.item(0);
	      System.out.println("XA_SOURCE_SYSTEMR: " + getCharacterDataFromElement(line));

	      NodeList title = element.getElementsByTagName("id_actuacion");
	      line = (Element) title.item(0);
	      System.out.println("id_actuacion: " + getCharacterDataFromElement(line));
	    }

	  }

	  public static String getCharacterDataFromElement(Element e) {
	    Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	      CharacterData cd = (CharacterData) child;
	      return cd.getData();
	    }
	    return "";
	  }
}
