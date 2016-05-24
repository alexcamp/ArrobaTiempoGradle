package co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva;

import com.ibm.etools.xmlschema.beans.Factory;

/**
* Provides convenience methods for creating Java beans for elements
* in this XML document
*/
public class ACCION_MASIVAFactory extends Factory
{
  public ACCION_MASIVAFactory()
  {
    super();
  }
  
  /**
  * Create the Java bean ACCION_MASIVA for the root element
  *   @param rootElementname The tag for the root element
  *   @return ACCION_MASIVA The Java bean representing this element
  */
  public ACCION_MASIVA createRoot(String rootElementname)
  {
    return (ACCION_MASIVA) createRootDOMFromComplexType("ACCION_MASIVA",rootElementname);
  }
  
  /**
  * Create the Java bean ACCION_MASIVA by loading the XML file
  *   @param filename The XML file name
  *   @return ACCION_MASIVA The Java bean representing the root element
  */
  public ACCION_MASIVA loadDocument(String filename)
  {
    return (ACCION_MASIVA) loadDocument("ACCION_MASIVA",filename);
  }
  
  /**
  * Create the Java bean ACCION_MASIVA for the element
  *   @param elementName The tag for the element
  *   @return ACCION_MASIVA The Java bean representing this element
  */
  public ACCION_MASIVA createACCION_MASIVA(String elementName)
  {
    return (ACCION_MASIVA) createDOMElementFromComplexType("ACCION_MASIVA",elementName);
  }
  
}

