package com.tecnonautica.mvc;

import java.net.URL;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;



public class FiltrosDAO {


    public static final String URL_MAPPING = "url-mapping";
    public static final String DEFINICION_VISTAS = "definicion-vistas";
    public static final String LANGUAGE = "language";
    public static final String TEMPLATE = "template";
    public static final String DEFAULT_TEMPLATE = "default-template";
    public static final String RESULT = "result";
    public static final String NEXT_SCREEN = "screen";
    public static final String USE_REQUEST_HANDLER = "useRequestHandler";
    public static final String USE_FLOW_HANDLER = "useFlowHandler";
    public static final String FLOW_HANDLER_CLASS = "class";
    public static final String REQUEST_HANDLER_CLASS = "request-handler-class";
    public static final String HANDLER_RESULT = "handler-result";
    public static final String FLOW_HANDLER = "flow-handler";

    public static final String FILTRO = "filtro";
    public static final String CLASE = "clase";
    public static final String NOMBRE = "nombre";
    public static final String KEY = "key";
    public static final String VALUE= "value";
    public static final String DIRECT="direct";
    public static final String VISTA= "vista";
    public static final String NAME = "name";
    public static final String URL = "url";
    public static final String PARAMETER = "parameter";

    public static Element loadDocument(URL url) {
        Document doc = null;
        try {
            InputSource xmlInp = new InputSource(url.openStream());

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = docBuilderFactory.newDocumentBuilder();
            doc = parser.parse(xmlInp);
            Element root = doc.getDocumentElement();
            root.normalize();
            return root;
        } catch (SAXParseException err) {
            System.err.println ("DefinicionDeVistasDAO ** Parsing error" + ", line " +
                        err.getLineNumber () + ", uri " + err.getSystemId ());
            System.err.println("DefinicionDeVistasDAO error: " + err.getMessage ());
        } catch (SAXException e) {
            System.err.println("DefinicionDeVistasDAO error: " + e);
        } catch (java.net.MalformedURLException mfx) {
            System.err.println("DefinicionDeVistasDAO error: " + mfx);
        } catch (java.io.IOException e) {
            System.err.println("DefinicionDeVistasDAO error: " + e);
        } catch (Exception pce) {
            System.err.println("DefinicionDeVistasDAO error: " + pce);
        }
        return null;
    }

   
    public static Vector CargarFiltros(URL location) {
        Element root = loadDocument(location);
        if (root != null) return getFiltros(root);
        else return null;
    }



  
    public static Vector getFiltros(Element root) {

        Vector filtros = new Vector();

        NodeList list = root.getElementsByTagName(FILTRO);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if ((node != null) && node instanceof Element) {
				String nombreDeClase = ((Element)node).getAttribute(CLASE);
                if (nombreDeClase != null){
                	filtros.add(nombreDeClase);
                }
                
                
            }
        }
        return filtros;
    }
    



}


