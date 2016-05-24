
package co.com.telefonica.atiempo.interfaces.atiempo.TR800S;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.base.ITRxxxBase;

/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="XA_SOURCE_SYSTEMR" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="id_actuacion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_ORDER_ATIEMPO" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_ORDER_ATIS" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_ORDER_COD_ERR" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_ORDER_DESC_ERR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
/*@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "xasourcesystemr",
    "idActuacion",
    "xaorderatiempo",
    "xaorderatis",
    "xaordercoderr",
    "xaorderdescerr"
})
@XmlRootElement(name = "CreacionServicioResponse")*/
public class TR800S implements ITRxxxBase {

//	 @XmlElement(name = "XA_SOURCE_SYSTEMR", required = true)
	private String xasourcesystemr;
//    @XmlElement(name = "id_actuacion", required = true)
    private String idActuacion;
//    @XmlElement(name = "XA_ORDER_ATIEMPO", required = true)
    private String xaorderatiempo;
//    @XmlElement(name = "XA_ORDER_ATIS", required = true)
    private String xaorderatis;
//    @XmlElement(name = "XA_ORDER_COD_ERR", required = true)
    private String xaordercoderr;
//    @XmlElement(name = "XA_ORDER_DESC_ERR")
    private String xaorderdescerr;
    
    /**
	 * 
	 */
	public TR800S() {

		// TODO Apéndice de constructor generado automáticamente
	}

    /**
     * Obtiene el valor de la propiedad xasourcesystemr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXASOURCESYSTEMR() {
        return xasourcesystemr;
    }

    /**
     * Define el valor de la propiedad xasourcesystemr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXASOURCESYSTEMR(String value) {
        this.xasourcesystemr = value;
    }

    /**
     * Obtiene el valor de la propiedad idActuacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdActuacion() {
        return idActuacion;
    }

    /**
     * Define el valor de la propiedad idActuacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdActuacion(String value) {
        this.idActuacion = value;
    }

    /**
     * Obtiene el valor de la propiedad xaorderatiempo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXAORDERATIEMPO() {
        return xaorderatiempo;
    }

    /**
     * Define el valor de la propiedad xaorderatiempo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXAORDERATIEMPO(String value) {
        this.xaorderatiempo = value;
    }

    /**
     * Obtiene el valor de la propiedad xaorderatis.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXAORDERATIS() {
        return xaorderatis;
    }

    /**
     * Define el valor de la propiedad xaorderatis.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXAORDERATIS(String value) {
        this.xaorderatis = value;
    }

    /**
     * Obtiene el valor de la propiedad xaordercoderr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXAORDERCODERR() {
        return xaordercoderr;
    }

    /**
     * Define el valor de la propiedad xaordercoderr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXAORDERCODERR(String value) {
        this.xaordercoderr = value;
    }

    /**
     * Obtiene el valor de la propiedad xaorderdescerr.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXAORDERDESCERR() {
        return xaorderdescerr;
    }

    /**
     * Define el valor de la propiedad xaorderdescerr.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXAORDERDESCERR(String value) {
        this.xaorderdescerr = value;
    }
    
    /**
     * Intended only for debugging.
     *
     * <P>Here, a generic implementation uses reflection to print
     * names and values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     *
     * <p>The format of the presentation could be standardized by using
     * a MessageFormat object with a standard pattern.
     */
    public String toString() {
		StringBuffer result = new StringBuffer();
		String newLine = System.getProperty("line.separator");
		String tab = "\t";
	
		result.append("<").append(this.getClass().getName().split("\\.")[this.getClass().getName().split("\\.").length - 1]).append(">");
		result.append(newLine);
	
		//determine fields declared in this class only (no fields of
		// superclass)
		Field[] fields = this.getClass().getDeclaredFields();
		int cantFields = this.getClass().getDeclaredFields().length;
	
		//Method[] methods = this.getClass().getDeclaredMethods();
	
		//print field names paired with their values
		for (int pos = 0; pos < cantFields; pos++) {
			try {
				if (fields[pos].get(this) != null) {
					//requires access to private field:
					if (fields[pos].getType().isPrimitive()
							|| fields[pos].getType().toString().equals("class java.lang.String")) {
						result.append("  ");
						result.append("<").append(fields[pos].getName()).append(">");
						result.append(fields[pos].get(this));
						result.append("</").append(fields[pos].getName()).append(">");
					} else if (fields[pos].getType().getName().equals("java.util.List")){
						List specificFieldList = (List) fields[pos].get(this);
						String instanceOfFieldList = "";
						String fieldName = fields[pos].getName();
						
						//Encuentro el objeto especifico que contiene la lista
						for(Iterator objectFieldIterator = specificFieldList.iterator(); objectFieldIterator.hasNext(); ) {
						    Object fieldObject = objectFieldIterator.next();
						    instanceOfFieldList = fieldObject.getClass().getName().split("\\.")[fieldObject.getClass().getName().split("\\.").length - 1];
						    //System.out.println("CLASE: "+ fieldList);
						    break;
						}
						
						//Si es una lista de String
						if (instanceOfFieldList.equals("String")) {
							//Obtengo todos los strings de la lista y les remplazo las llaves del inicio y del final
							String stringObjects = (fields[pos].get(this).toString().replace('[', ' ')).replace(']', ' ');
							//obtengo el substring sin los espacios del principio y del final, adicional le hago split por ", "
							String[] cadenas = stringObjects.substring(1, stringObjects.length()-1).split(", ");
							//itero todos los elementos añadiendole el nombre del campo como tag y les doy espacio excepto al ultimo
							for (int numCadena = 0; numCadena < cadenas.length; numCadena++) {
								result.append("<").append(fieldName).append(">").append(cadenas[numCadena]).append("</").append(fieldName).append(">");
								if (numCadena < cadenas.length-1) result.append(newLine);
							}
						} else { //si es una lista de Objetos
//							Imprimo la lista, quitandole {", ", "[", "]")
							String object = ((fields[pos].get(this).toString().replaceAll(", ", newLine)).replace('[', ' ')).replace(']', ' ');
							//Intercambio el nombre de la instancia por el nombre del campo
							object = object.replaceAll("<"+instanceOfFieldList+">","<"+fieldName+">");
							object = object.replaceAll("</"+instanceOfFieldList+">","</"+fieldName+">");
//							Añado el resultado a mi respuesta
							result.append(object);
						}
					} else {
						String fieldClass = fields[pos].get(this).getClass().getName().split("\\.")[fields[pos].get(this).getClass().getName().split("\\.").length - 1];
						String fieldName = fields[pos].getName();
						//Obtengo el String del objeto						
						String object = fields[pos].get(this).toString();
						//Intercambio el nombre de la instancia por el nombre del campo
						object = object.replaceAll("<"+fieldClass+">","<"+fieldName+">");
						object = object.replaceAll("</"+fieldClass+">","</"+fieldName+">");
						//Añado el resultado a mi respuesta
						result.append(object);
					}
					result.append(newLine);
				}
			} catch (IllegalAccessException ex) {
				System.out.println(ex);
			}
		}
		result.append("</").append(this.getClass().getName().split("\\.")[this.getClass().getName().split("\\.").length - 1]).append(">");
	
		return result.toString();
	}

    
}
