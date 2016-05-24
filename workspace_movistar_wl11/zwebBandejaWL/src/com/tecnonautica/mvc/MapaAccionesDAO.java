package com.tecnonautica.mvc;

import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;




/**
 * This class provides the data bindings for the screendefinitions.xml
 * and the requestmappings.xml file.
 * The data obtained is maintained by the ScreenFlowManager
 */

public class MapaAccionesDAO {

    // event - flow constants
	public static final String ACCION = "accion";
    public static final String NOMBRE = "nombre";
    public static final String CLASE = "clase";
    public static final String VISTA = "vista";
    public static final String FLUJO = "flujo";
	public static final String MAPA = "mapa";
	public static final String RESULTADO = "resultado";
	

    public static final String URL_MAPPING = "url-mapping";
    public static final String SCREEN_DEFINITION = "screen-definition";
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

    // screendefinitions.xml contansts
    public static final String KEY = "key";
    public static final String VALUE= "value";
    public static final String DIRECT="direct";
    public static final String SCREEN= "screen";
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
            System.err.println ("ScreenFlowXmlDAO ** Parsing error" + ", line " +
                        err.getLineNumber () + ", uri " + err.getSystemId ());
            System.err.println("ScreenFlowXmlDAO error: " + err.getMessage ());
        } catch (SAXException e) {
            System.err.println("ScreenFlowXmlDAO error: " + e);
        } catch (java.net.MalformedURLException mfx) {
            System.err.println("ScreenFlowXmlDAO error: " + mfx);
        } catch (java.io.IOException e) {
            System.err.println("ScreenFlowXmlDAO error: " + e);
        } catch (Exception pce) {
            System.err.println("ScreenFlowXmlDAO error: " + pce);
        }
        return null;
    }

    public static HashMap cargarMapaAcciones(URL location) {
        Element root = loadDocument(location);
        if (root != null) return getScreens(root);
        else return null;
    }


/*

    public static HashMap getScreenDefinitions(Element root) {
        HashMap screensDefs = new HashMap();
        NodeList list = root.getElementsByTagName(SCREEN_DEFINITION);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if (node != null) {
                String language = null;
                String url = null;
                if (node instanceof Element) {
                    language = ((Element)node).getAttribute(LANGUAGE);
                    url = ((Element)node).getAttribute(URL);
                }
                if ((language != null) && (url != null) && !screensDefs.containsKey(language)) {
                    screensDefs.put(language, url);
                } else {
                    System.err.println("*** Non Fatal errror: ScreenDefinitions for language " + language +
                                       " defined more than once in screen definitions file");
                }
            }
        }
        return screensDefs;
    }

*/

    public static HashMap getScreens(Element root) {

        HashMap acciones = new HashMap();

        // cargar acciones
        NodeList list = root.getElementsByTagName(ACCION);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if ((node != null) && node instanceof Element) {
                String nombreAccion = ((Element)node).getAttribute(NOMBRE);
                String clase = ((Element)node).getAttribute(CLASE);
                String vista = ((Element)node).getAttribute(VISTA);
                String flujo = ((Element)node).getAttribute(FLUJO);
				MapaAccion mapaAccion=new MapaAccion(nombreAccion,clase,vista,flujo);
				
				HashMap mapas = getParameters(node);
				mapaAccion.setMapas(mapas); 
				
                if (!acciones.containsKey(nombreAccion)) {
                    acciones.put(nombreAccion, mapaAccion);
                } else {
                    System.err.println("*** Non Fatal errror: Screen " + nombreAccion +
                                       " defined more than once in screen definitions file");
                }
            }
        }
        return acciones;
    }

    /**
     *    Load the templates into the Screens object
     */


    private static HashMap getParameters(Node node) {
        HashMap mapas = new HashMap();
        
        if (node != null) {
            NodeList  children = node.getChildNodes();
            for (int innerLoop =0; innerLoop < children.getLength(); innerLoop++) {
                Node  child = children.item(innerLoop);
                if ((child != null) && (child.getNodeName() != null) && child.getNodeName().equals(MAPA) ) {
                    if (child instanceof Element) {
                        Element childElement = ((Element)child);
                        String resultado = childElement.getAttribute(RESULTADO);
                        String accion = childElement.getAttribute(ACCION);
                        String vista = childElement.getAttribute(VISTA);
                        
                        Mapa mapa= new Mapa();
                        mapa.setResultado(resultado);
                        mapa.setAccion(accion);
                        mapa.setVista(vista);
                        
                        
                        if (!mapas.containsKey(resultado)) {
                            mapas.put(resultado,mapa);
                        } else {
                            System.err.println("error: " +
                                               "El Parametro " + resultado + " está definido más de una vez");

                        }
                    }
                }
            } // end inner loop
        }
        return mapas;
    }



    public static String getSubTagValue(Node node, String subTagName) {
        String returnString = "";
        if (node != null) {
            NodeList  children = node.getChildNodes();
            for (int innerLoop =0; innerLoop < children.getLength(); innerLoop++) {
                Node  child = children.item(innerLoop);
                if ((child != null) && (child.getNodeName() != null) && child.getNodeName().equals(subTagName) ) {
                    Node grandChild = child.getFirstChild();
                    if (grandChild.getNodeValue() != null) return grandChild.getNodeValue();
                }
            } // end inner loop
        }
        return returnString;
    }

    private String getSubTagAttribute(Element root, String tagName, String subTagName, String attribute) {
        String returnString = "";
        NodeList list = root.getElementsByTagName(tagName);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if (node != null) {
                NodeList  children = node.getChildNodes();
                for (int innerLoop =0; innerLoop < children.getLength(); innerLoop++) {
                    Node  child = children.item(innerLoop);
                    if ((child != null) && (child.getNodeName() != null) && child.getNodeName().equals(subTagName) ) {
                        if (child instanceof Element) {
                            return ((Element)child).getAttribute(attribute);
                        }
                    }
                } // end inner loop
            }
        }
        return returnString;
    }

    public static String getSubTagValue(Element root, String tagName, String subTagName) {
        String returnString = "";
        NodeList list = root.getElementsByTagName(tagName);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if (node != null) {
                NodeList  children = node.getChildNodes();
                for (int innerLoop =0; innerLoop < children.getLength(); innerLoop++) {
                    Node  child = children.item(innerLoop);
                    if ((child != null) && (child.getNodeName() != null) && child.getNodeName().equals(subTagName) ) {
                        Node grandChild = child.getFirstChild();
                        if (grandChild.getNodeValue() != null) return grandChild.getNodeValue();
                    }
                } // end inner loop
            }
        }
        return returnString;
    }

    public static String getTagValue(Element root, String tagName) {
        String returnString = "";
        NodeList list = root.getElementsByTagName(tagName);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if (node != null) {
                Node child = node.getFirstChild();
                if ((child != null) && child.getNodeValue() != null) return child.getNodeValue();
            }
        }
        return returnString;
    }
}



