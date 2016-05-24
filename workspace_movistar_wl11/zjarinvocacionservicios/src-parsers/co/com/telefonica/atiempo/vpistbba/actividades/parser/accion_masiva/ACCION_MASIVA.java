package co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva;

import com.ibm.etools.xmlschema.beans.ComplexType;

/**
* This class represents the complex type <ACCION_MASIVA>
*/
public class ACCION_MASIVA extends ComplexType
{
  public ACCION_MASIVA()
  {
  }
  
  public void setCODIGO_ACCION(String CODIGO_ACCION)
  {
    setElementValue("CODIGO_ACCION", CODIGO_ACCION);
  }
  
  public String getCODIGO_ACCION()
  {
    return getElementValue("CODIGO_ACCION");
  }
  
  public boolean removeCODIGO_ACCION()
  {
    return removeElement("CODIGO_ACCION");
  }
  
  public void setNUMERO_PETICION(String NUMERO_PETICION)
  {
    setElementValue("NUMERO_PETICION", NUMERO_PETICION);
  }
  
  public String getNUMERO_PETICION()
  {
    return getElementValue("NUMERO_PETICION");
  }
  
  public boolean removeNUMERO_PETICION()
  {
    return removeElement("NUMERO_PETICION");
  }
  
  public void setCODIGO_ACTIVIDAD(String CODIGO_ACTIVIDAD)
  {
    setElementValue("CODIGO_ACTIVIDAD", CODIGO_ACTIVIDAD);
  }
  
  public String getCODIGO_ACTIVIDAD()
  {
    return getElementValue("CODIGO_ACTIVIDAD");
  }
  
  public boolean removeCODIGO_ACTIVIDAD()
  {
    return removeElement("CODIGO_ACTIVIDAD");
  }
  
  public void setINSTANCIA_ACTIVIDAD(String INSTANCIA_ACTIVIDAD)
  {
    setElementValue("INSTANCIA_ACTIVIDAD", INSTANCIA_ACTIVIDAD);
  }
  
  public String getINSTANCIA_ACTIVIDAD()
  {
    return getElementValue("INSTANCIA_ACTIVIDAD");
  }
  
  public boolean removeINSTANCIA_ACTIVIDAD()
  {
    return removeElement("INSTANCIA_ACTIVIDAD");
  }
  
}

