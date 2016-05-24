package co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva;



/**
* A sample program to show how to use the generated Java beans to:
*  - create and save an XML document
*  - load the XML document and print its content
*/
public class Sample
{
  ACCION_MASIVAFactory iACCION_MASIVAFactory;
  ACCION_MASIVA iACCION_MASIVA;
  
  public Sample()
  {
  }
  
  /**
  * Create the root element in the document
  */
  void createACCION_MASIVA()
  {
    iACCION_MASIVA.setCODIGO_ACCION("CODIGO_ACCION");
    iACCION_MASIVA.setNUMERO_PETICION("NUMERO_PETICION");
    iACCION_MASIVA.setCODIGO_ACTIVIDAD("CODIGO_ACTIVIDAD");
    iACCION_MASIVA.setINSTANCIA_ACTIVIDAD("INSTANCIA_ACTIVIDAD");
  }
  
  /**
  * Print the content of the root element
  */
  void printACCION_MASIVA()
  {
    System.out.println(iACCION_MASIVA.getCODIGO_ACCION());
    System.out.println(iACCION_MASIVA.getNUMERO_PETICION());
    System.out.println(iACCION_MASIVA.getCODIGO_ACTIVIDAD());
    System.out.println(iACCION_MASIVA.getINSTANCIA_ACTIVIDAD());
  }
  
  /**
  * Create a new XML document using the generated ACCION_MASIVAFactory class
  *   @param filename The XML file name
  */
  void createNewInstance(String filename)
  {
    iACCION_MASIVAFactory = new ACCION_MASIVAFactory();
    iACCION_MASIVAFactory.setPackageName("co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva");
    
    // include schemaLocation hint for validation
    iACCION_MASIVAFactory.setXSDFileName("AccionMasiva.xsd");
    
    // encoding for output document
    iACCION_MASIVAFactory.setEncoding("Cp1252");
    
    // encoding tag for xml declaration
    iACCION_MASIVAFactory.setEncodingTag("ISO-8859-1");
    
    // Create the root element in the document using the specified root element name
    iACCION_MASIVA = (ACCION_MASIVA) iACCION_MASIVAFactory.createRoot("ACCION_MASIVA");
    createACCION_MASIVA();
    
    iACCION_MASIVAFactory.save(filename);
  }
  
  /**
  * Load an XML document using the generated ACCION_MASIVAFactory class
  *   @param filename An existing XML file name
  */
  void loadExistingInstance(String filename)
  {
    iACCION_MASIVAFactory = new ACCION_MASIVAFactory();
    iACCION_MASIVAFactory.setPackageName("co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva");
    
    // Load the document
    iACCION_MASIVA = (ACCION_MASIVA) iACCION_MASIVAFactory.loadDocument(filename);
    printACCION_MASIVA();
  }
  
  /**
  * The main program.
  * Creates an example XML document and then loads it
  */

  public static void main(String args[]){
//	  String hola="RUEAAAABAwRx0AAAAAAAAAAAAAAAGAAAAAEAWwAIAAAAAAAAAAAAAAAYQQAAAAEDCo+AAAAAAAAAAABF";
//	  System.out.println(hola);
//	  String holaEncode = URLEncoder.encode(hola);
//	  System.out.println(holaEncode);
//	  String holaDecode = URLDecoder.decode(holaEncode);
//	  System.out.println(holaDecode);
//	  hola="RUEAAAABAwRx0AAAAAAAAAAAAAAAGAAAAAEAWwAIAAAAAAAAAAAAAAAYQQAAAAEDCo AAAAAAAAAAABF";
//	  System.out.println(hola);
//   	  holaEncode = URLEncoder.encode(hola);
//	  System.out.println(holaEncode);

  }
}

