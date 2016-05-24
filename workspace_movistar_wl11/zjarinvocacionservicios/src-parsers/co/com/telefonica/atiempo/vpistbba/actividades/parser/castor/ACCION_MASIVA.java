/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.5</a>, using an XML
 * Schema.
 * $Id: ACCION_MASIVA.java,v 1.1.2.1 2011/03/30 22:45:08 lfmartinez Exp $
 */

package co.com.telefonica.atiempo.vpistbba.actividades.parser.castor;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * Class ACCION_MASIVA.
 * 
 * @version $Revision: 1.1.2.1 $ $Date: 2011/03/30 22:45:08 $
 */
public class ACCION_MASIVA implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _CODIGO_ACCION
     */
    private java.lang.String _CODIGO_ACCION;

    /**
     * Field _NUMERO_PETICION
     */
    private java.lang.String _NUMERO_PETICION;

    /**
     * Field _CODIGO_ACTIVIDAD
     */
    private java.lang.String _CODIGO_ACTIVIDAD;

    /**
     * Field _INSTANCIA_ACTIVIDAD
     */
    private java.lang.String _INSTANCIA_ACTIVIDAD;


      //----------------/
     //- Constructors -/
    //----------------/

    public ACCION_MASIVA() 
     {
        super();
    } //-- cl.telefonica.dth.javagenerado.accionmasiva.ACCION_MASIVA()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'CODIGO_ACCION'.
     * 
     * @return the value of field 'CODIGO_ACCION'.
     */
    public java.lang.String getCODIGO_ACCION()
    {
        return this._CODIGO_ACCION;
    } //-- java.lang.String getCODIGO_ACCION() 

    /**
     * Returns the value of field 'CODIGO_ACTIVIDAD'.
     * 
     * @return the value of field 'CODIGO_ACTIVIDAD'.
     */
    public java.lang.String getCODIGO_ACTIVIDAD()
    {
        return this._CODIGO_ACTIVIDAD;
    } //-- java.lang.String getCODIGO_ACTIVIDAD() 

    /**
     * Returns the value of field 'INSTANCIA_ACTIVIDAD'.
     * 
     * @return the value of field 'INSTANCIA_ACTIVIDAD'.
     */
    public java.lang.String getINSTANCIA_ACTIVIDAD()
    {
        return this._INSTANCIA_ACTIVIDAD;
    } //-- java.lang.String getINSTANCIA_ACTIVIDAD() 

    /**
     * Returns the value of field 'NUMERO_PETICION'.
     * 
     * @return the value of field 'NUMERO_PETICION'.
     */
    public java.lang.String getNUMERO_PETICION()
    {
        return this._NUMERO_PETICION;
    } //-- java.lang.String getNUMERO_PETICION() 

    /**
     * Method isValid
     * 
     * 
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Sets the value of field 'CODIGO_ACCION'.
     * 
     * @param CODIGO_ACCION the value of field 'CODIGO_ACCION'.
     */
    public void setCODIGO_ACCION(java.lang.String CODIGO_ACCION)
    {
        this._CODIGO_ACCION = CODIGO_ACCION;
    } //-- void setCODIGO_ACCION(java.lang.String) 

    /**
     * Sets the value of field 'CODIGO_ACTIVIDAD'.
     * 
     * @param CODIGO_ACTIVIDAD the value of field 'CODIGO_ACTIVIDAD'
     */
    public void setCODIGO_ACTIVIDAD(java.lang.String CODIGO_ACTIVIDAD)
    {
        this._CODIGO_ACTIVIDAD = CODIGO_ACTIVIDAD;
    } //-- void setCODIGO_ACTIVIDAD(java.lang.String) 

    /**
     * Sets the value of field 'INSTANCIA_ACTIVIDAD'.
     * 
     * @param INSTANCIA_ACTIVIDAD the value of field
     * 'INSTANCIA_ACTIVIDAD'.
     */
    public void setINSTANCIA_ACTIVIDAD(java.lang.String INSTANCIA_ACTIVIDAD)
    {
        this._INSTANCIA_ACTIVIDAD = INSTANCIA_ACTIVIDAD;
    } //-- void setINSTANCIA_ACTIVIDAD(java.lang.String) 

    /**
     * Sets the value of field 'NUMERO_PETICION'.
     * 
     * @param NUMERO_PETICION the value of field 'NUMERO_PETICION'.
     */
    public void setNUMERO_PETICION(java.lang.String NUMERO_PETICION)
    {
        this._NUMERO_PETICION = NUMERO_PETICION;
    } //-- void setNUMERO_PETICION(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * cl.telefonica.dth.javagenerado.accionmasiva.ACCION_MASIVA
     */
    public static co.com.telefonica.atiempo.vpistbba.actividades.parser.castor.ACCION_MASIVA unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (co.com.telefonica.atiempo.vpistbba.actividades.parser.castor.ACCION_MASIVA) Unmarshaller.unmarshal(co.com.telefonica.atiempo.vpistbba.actividades.parser.castor.ACCION_MASIVA.class, reader);
    } //-- cl.telefonica.dth.javagenerado.accionmasiva.ACCION_MASIVA unmarshal(java.io.Reader) 

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
