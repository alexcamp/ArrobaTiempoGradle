package co.com.telefonica.atiempo.sigres.emu.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
/**
 * XMLProcessor
 * 
 * Clase para el procesamiento de los mensajes XML.
 * 
 * @author Gonzalo Arreche
 *  
 */
public class XMLProcessor {
	private Logger log = Logger.getLogger(getClass());
    /**
     * Extrae el tipo o nombre de la interfaz (TR) que representa el mensaje
     * recibido por parámtero.
     * 
     * @param xml
     *            es el mensaje
     * @return es el tipo de interfaz (TR)
     */
	public String getMessageType(String xml) {
		String type = "";
		SAXBuilder sb = new SAXBuilder();
		try {
			Document d = sb.build(new StringReader(xml));
			Element root = d.getRootElement();
			type = root.getName();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return type;
	}

    public Object unmarshal(InputStream is) {
        Object obj = null;
        try {
            obj = XMLUtilities.unmarshall(is);
        } catch (ATiempoAppEx e) {
			log.error("Error al realizar el unmarshal: " + e.getMessage());
		}
        return obj;
    }
    public Object unmarshal(String r) {
        Object obj = null;
        try {
            obj = XMLUtilities.unmarshall(r);
        } catch(ATiempoAppEx e){
        	log.error("Error al realizar el unmarshal: " + e.getMessage());
        }
        return obj;
    }
    public String marshal(Object obj) {
        String xml = null;
        try {           
          	xml = XMLUtilities.marshall(obj);
        }catch (ATiempoAppEx e) {
			log.error("Error al realizar el marshal: " + e.getMessage());
		}
        return xml;
    }
    
    public String getXML(InputStream stream){
        StringBuffer msj = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        String line = "";
        try {
            while((line=br.readLine())!=null){
                msj.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msj.toString();
    }  
}
