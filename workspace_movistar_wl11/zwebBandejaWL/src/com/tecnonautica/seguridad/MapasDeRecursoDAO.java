package com.tecnonautica.seguridad;

import java.net.URL;
import java.util.HashMap;
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



/**
 * This class provides the data bindings for the screendefinitions.xml
 * and the requestmappings.xml file.
 * The data obtained is maintained by the ScreenFlowManager
 */

public class MapasDeRecursoDAO {

    public static final String RECURSO = "recurso";
    public static final String PATRON = "patron";
    public static final String IDRECURSO = "id";
    public static final String CONTROLADOR = "controlador";
    

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
            System.err.println ("MapaDeRecursosXmlDAO ** Parsing error" + ", line " +
                        err.getLineNumber () + ", uri " + err.getSystemId ());
            System.err.println("MapaDeRecursosXmlDAO error: " + err.getMessage ());
        } catch (SAXException e) {
            System.err.println("MapaDeRecursosXmlDAO error: " + e);
        } catch (java.net.MalformedURLException mfx) {
            System.err.println("MapaDeRecursosXmlDAO error: " + mfx);
        } catch (java.io.IOException e) {
            System.err.println("MapaDeRecursosXmlDAO error: " + e);
        } catch (Exception pce) {
            System.err.println("MapaDeRecursosXmlDAO error: " + pce);
        }
        return null;
    }


    public static HashMap cargarMapaDeRecursos(URL location) {
        Element root = loadDocument(location);
        if (root != null) return getControladores(root);
        else return null;
    }

    public static Vector cargarMapaDePatrones(URL location) {
        Element root = loadDocument(location);
        if (root != null) return getPatrones(root);
        else return null;
    }



    public static HashMap getControladores(Element root) {
 
        
        // cargar controladores        
        HashMap recursos = new HashMap();
		        
        NodeList list = root.getElementsByTagName(RECURSO);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if ((node != null) && node instanceof Element) {
                String idRecurso = ((Element)node).getAttribute(IDRECURSO);
                String controlador = ((Element)node).getAttribute(CONTROLADOR);
				MapaDeRecurso mapaDeRecurso=new MapaDeRecurso(idRecurso,controlador);
                if (!recursos.containsKey(idRecurso)) {
                    recursos.put(idRecurso, mapaDeRecurso);
                } else {
                    System.err.println("Error: El recurso " + idRecurso +
                                       " está definido más de una vez");
                }
            }
        }
        return recursos;
    }
    
    public static Vector getPatrones(Element root) {
 
        
        // cargar controladores        
        Vector patrones = new Vector();
		        
        NodeList list = root.getElementsByTagName(PATRON);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if ((node != null) && node instanceof Element) {
                String idRecurso = ((Element)node).getAttribute(IDRECURSO);
                String controlador = ((Element)node).getAttribute(CONTROLADOR);
				MapaDeRecurso mapaDeRecurso=new MapaDeRecurso(idRecurso,controlador);
                patrones.add(mapaDeRecurso);
            }
        }
        return patrones;
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



