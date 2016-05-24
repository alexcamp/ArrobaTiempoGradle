//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2015.05.20 a las 06:31:39 PM COT 
//


package co.com.telefonica.atiempo.interfaces.atiempo.TR800E;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

/**
 * <p>Clase Java para XA_TV_DATA complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="XA_TV_DATA"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Tematicos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Tipo_Decos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Cantidad_Decos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Otros_Datos_TV" type="{http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/}OTHERTYPE" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
/*@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XA_TV_DATA", propOrder = {
    "tematicos",
    "tipoDecos",
    "cantidadDecos",
    "otrosDatosTV"
})*/
public class XATVDATA {

    //@XmlElement(name = "Tematicos")
    private String Tematicos;
    //@XmlElement(name = "Tipo_Decos")
    private String Tipo_Decos;
    //@XmlElement(name = "Cantidad_Decos")
    private String Cantidad_Decos;
    //@XmlElement(name = "Otros_Datos_TV")
    private OTHERTYPE Otros_Datos_TV;

    
    
	/**
	 * @return Devuelve cantidad_Decos.
	 */
	public String getCantidad_Decos() {
		return Cantidad_Decos;
	}
	/**
	 * @param cantidad_Decos El cantidad_Decos a establecer.
	 */
	public void setCantidad_Decos(String cantidad_Decos) {
		Cantidad_Decos = cantidad_Decos;
	}
	/**
	 * @return Devuelve otros_Datos_TV.
	 */
	public OTHERTYPE getOtros_Datos_TV() {
		return Otros_Datos_TV;
	}
	/**
	 * @param otros_Datos_TV El otros_Datos_TV a establecer.
	 */
	public void setOtros_Datos_TV(OTHERTYPE otros_Datos_TV) {
		Otros_Datos_TV = otros_Datos_TV;
	}
	/**
	 * @return Devuelve tematicos.
	 */
	public String getTematicos() {
		return Tematicos;
	}
	/**
	 * @param tematicos El tematicos a establecer.
	 */
	public void setTematicos(String tematicos) {
		Tematicos = tematicos;
	}
	/**
	 * @return Devuelve tipo_Decos.
	 */
	public String getTipo_Decos() {
		return Tipo_Decos;
	}
	/**
	 * @param tipo_Decos El tipo_Decos a establecer.
	 */
	public void setTipo_Decos(String tipo_Decos) {
		Tipo_Decos = tipo_Decos;
	}
	
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
