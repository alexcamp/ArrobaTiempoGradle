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


public class DefinicionDeVistasDAO {


    public static final String URL_MAPPING = "url-mapping";
    public static final String DEFINICION_VISTAS = "vistas";
    public static final String TEMPLATE = "plantilla";

    public static final String AREA = "area";
    public static final String NOMBRE = "nombre";
    public static final String KEY = "key";
    public static final String VALUE= "value";
    public static final String DIRECT="direct";
    public static final String VISTA= "vista";

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

    public static HashMap loadScreenDefinitions(URL location) {
        Element root = loadDocument(location);
        if (root != null) return getScreens(root);
        else return null;
    }




/*    public static HashMap getScreenDefinitions(Element root) {
        HashMap screensDefs = new HashMap();
        NodeList list = root.getElementsByTagName(DEFINICION_VISTAS);
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
        HashMap screens = new HashMap();
        NodeList list = root.getElementsByTagName(VISTA);
        for (int loop = 0; loop < list.getLength(); loop++) {
            Node node = list.item(loop);
            if ((node != null) && node instanceof Element) {
                String templateName = ((Element)node).getAttribute(TEMPLATE);
                String screenName = ((Element)node).getAttribute(NOMBRE);
                HashMap areas = getParameters(node);
                Vista vista = new Vista(screenName,templateName, areas);
                if (!screens.containsKey(screenName)) {
                    screens.put(screenName, vista);
                } else {
                    System.err.println("La vista" + screenName +
                                       " esta definida más de una vez en el archivo de definicion");
                }
            }
        }
        return screens;
    }

    /**
     *    Load the templates into the Screens object
     */


    private static HashMap getParameters(Node node) {
        HashMap areas = new HashMap();
        if (node != null) {
            NodeList  children = node.getChildNodes();
            for (int innerLoop =0; innerLoop < children.getLength(); innerLoop++) {
                Node  child = children.item(innerLoop);
                if ((child != null) && (child.getNodeName() != null) && child.getNodeName().equals(AREA) ) {
                    if (child instanceof Element) {
                        Element childElement = ((Element)child);
                        String nombre = childElement.getAttribute(NOMBRE);
                        String url = childElement.getAttribute(URL);
                        String directString = childElement.getAttribute(DIRECT);
                        boolean direct = false;
                        if ((directString != null) && directString.equals("true")) {
                            direct = true;
                        }
                        if (!areas.containsKey(nombre)) {
                            areas.put(nombre,url);
                        } else {
                            System.err.println("DefinicionDeVistasDAO  error: " +
                                               "El parametro " + nombre + " esta definido más de una vez");

                        }
                    }
                }
            } // end inner loop
        }
        return areas;
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


