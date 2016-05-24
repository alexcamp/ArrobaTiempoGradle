//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2015.05.20 a las 06:31:39 PM COT 
//


package co.com.telefonica.atiempo.interfaces.atiempo.TR800E;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

/**
 * <p>Clase Java para XA_REPAIR_INFO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="XA_REPAIR_INFO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Codigo_Apertura" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Observaciones_Apertura" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Diagnostico_Primer_Nivel" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Diagnostico_Segundo_Nivel" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Observaciones_Diagnostico_Segundo_Nivel" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Categor�a_Aver�a" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Codigo_Prioridad" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="Otros_Repair" type="{http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/}OTHERTYPE" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
/*privateXmlAccessorType(XmlAccessType.FIELD)
privateXmlType(name = "XA_REPAIR_INFO", propOrder = {
    "codigoApertura",
    "observacionesApertura",
    "diagnosticoPrimerNivel",
    "diagnosticoSegundoNivel",
    "observacionesDiagnosticoSegundoNivel",
    "categor\u00edaAver\u00eda",
    "codigoPrioridad",
    "otrosRepair"
})*/
public class XAREPAIRINFO {

    //privateXmlElement(name = "Codigo_Apertura", required = true)
    protected String Codigo_Apertura;
    //privateXmlElement(name = "Observaciones_Apertura", required = true)
    protected String Observaciones_Apertura;
    //privateXmlElement(name = "Diagnostico_Primer_Nivel", required = true)
    protected String Diagnostico_Primer_Nivel;
    //privateXmlElement(name = "Diagnostico_Segundo_Nivel", required = true)
    protected String Diagnostico_Segundo_Nivel;
    //privateXmlElement(name = "Observaciones_Diagnostico_Segundo_Nivel", required = true)
    protected String Observaciones_Diagnostico_Segundo_Nivel;
    //privateXmlElement(name = "Categor\u00eda_Aver\u00eda", required = true)
    protected String Categoria_Averia;
    //privateXmlElement(name = "Codigo_Prioridad", required = true)
    protected String Codigo_Prioridad;
    //privateXmlElement(name = "Otros_Repair")
    protected OTHERTYPE Otros_Repair;

    
	/**
	 * @return Devuelve categor�aAver�a.
	 */
	public String getCategoria_Averia() {
		return Categoria_Averia;
	}
	/**
	 * @param categor�aAver�a El categor�aAver�a a establecer.
	 */
	public void setCategoria_Averia(String categoriaAveria) {
		this.Categoria_Averia = categoriaAveria;
	}
	
	/**
	 * @return Devuelve codigo_Apertura.
	 */
	public String getCodigo_Apertura() {
		return Codigo_Apertura;
	}
	/**
	 * @param codigo_Apertura El codigo_Apertura a establecer.
	 */
	public void setCodigo_Apertura(String codigo_Apertura) {
		Codigo_Apertura = codigo_Apertura;
	}
	/**
	 * @return Devuelve codigoPrioridad.
	 */
	public String getCodigo_Prioridad() {
		return Codigo_Prioridad;
	}
	/**
	 * @param codigoPrioridad El codigoPrioridad a establecer.
	 */
	public void setCodigo_Prioridad(String codigoPrioridad) {
		this.Codigo_Prioridad = codigoPrioridad;
	}
	/**
	 * @return Devuelve diagnosticoPrimerNivel.
	 */
	public String getDiagnostico_Primer_Nivel() {
		return Diagnostico_Primer_Nivel;
	}
	/**
	 * @param diagnosticoPrimerNivel El diagnosticoPrimerNivel a establecer.
	 */
	public void setDiagnostico_Primer_Nivel(String diagnosticoPrimerNivel) {
		this.Diagnostico_Primer_Nivel = diagnosticoPrimerNivel;
	}
	/**
	 * @return Devuelve diagnosticoSegundoNivel.
	 */
	public String getDiagnostico_Segundo_Nivel() {
		return Diagnostico_Segundo_Nivel;
	}
	/**
	 * @param diagnosticoSegundoNivel El diagnosticoSegundoNivel a establecer.
	 */
	public void setDiagnostico_Segundo_Nivel(String diagnosticoSegundoNivel) {
		this.Diagnostico_Segundo_Nivel = diagnosticoSegundoNivel;
	}
	/**
	 * @return Devuelve observacionesApertura.
	 */
	public String getObservaciones_Apertura() {
		return Observaciones_Apertura;
	}
	/**
	 * @param observacionesApertura El observacionesApertura a establecer.
	 */
	public void setObservaciones_Apertura(String observacionesApertura) {
		this.Observaciones_Apertura = observacionesApertura;
	}
	/**
	 * @return Devuelve observacionesDiagnosticoSegundoNivel.
	 */
	public String getObservaciones_Diagnostico_Segundo_Nivel() {
		return Observaciones_Diagnostico_Segundo_Nivel;
	}
	/**
	 * @param observacionesDiagnosticoSegundoNivel El observacionesDiagnosticoSegundoNivel a establecer.
	 */
	public void setObservaciones_Diagnostico_Segundo_Nivel(
			String observacionesDiagnosticoSegundoNivel) {
		this.Observaciones_Diagnostico_Segundo_Nivel = observacionesDiagnosticoSegundoNivel;
	}
	/**
	 * @return Devuelve otrosRepair.
	 */
	public OTHERTYPE getOtros_Repair() {
		return Otros_Repair;
	}
	/**
	 * @param otrosRepair El otrosRepair a establecer.
	 */
	public void setOtros_Repair(OTHERTYPE otrosRepair) {
		this.Otros_Repair = otrosRepair;
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
							//itero todos los elementos a�adiendole el nombre del campo como tag y les doy espacio excepto al ultimo
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
//							A�ado el resultado a mi respuesta
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
						//A�ado el resultado a mi respuesta
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
