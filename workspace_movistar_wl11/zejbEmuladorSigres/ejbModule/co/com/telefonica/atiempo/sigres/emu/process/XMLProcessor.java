package co.com.telefonica.atiempo.sigres.emu.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import co.com.telefonica.atiempo.interfaces.atiempo.TR012S;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;

/**
 * XMLProcessor
 * 
 * Clase para el procesamiento de los mensajes XML.
 * 
 * @author Gonzalo Arreche
 *  
 */
public class XMLProcessor {
    private Mapping localMapping;

    public XMLProcessor() {
        init();
    }

    private void init() {
        Mapping m = new Mapping();
		m.loadMapping(new InputSource(ServiceLocatorEmulator.getRecurso("mapping.xml")));
		localMapping = m;
    }

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
        Unmarshaller u = new Unmarshaller();
        try {
            obj = u.unmarshal(new InputSource(is));
        } catch (MarshalException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public Object unmarshal(String r) {
        Object obj = null;
        Unmarshaller u =new Unmarshaller();
        try {
            obj = u.unmarshal(new StringReader(r));
        } catch (MarshalException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public String marshal(Object obj) {
        String xml = null;
        StringWriter sw = new StringWriter();
        Marshaller m =null;
        try {
            m= new Marshaller();
            m.setWriter(sw);
            m.marshal(obj);
            xml = sw.toString();
        } catch (MarshalException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
    public static void main(String[] args) {
        XMLProcessor xml = new XMLProcessor();
        TR012S tr = (TR012S)xml.unmarshal(ServiceLocatorEmulator.getRecurso("TR_012_S.xml"));
        String s = xml.marshal(tr);
        System.out.println(s);
    }
}
