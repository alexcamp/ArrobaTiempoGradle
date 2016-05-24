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
 * <p>Clase Java para XA_TELEPHONE_DATA complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="XA_TELEPHONE_DATA"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Central" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Telefono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Len" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Posicion_Horizontal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Distribuidor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Descripcion_Distribuidor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Direccion_Distribuidor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Liston" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Par_liston" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Cable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Par_Cable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Armario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Direccion_Armario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Caja" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Par_caja" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Direccion_caja" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Tipo_caja" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Distancia_caja" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Latitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Longitud" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Zona_cobertura" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Otros_Telephon_Data" type="{http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/}OTHERTYPE" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
/*@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XA_TELEPHONE_DATA", propOrder = {
    "central",
    "telefono",
    "len",
    "posicionHorizontal",
    "distribuidor",
    "descripcionDistribuidor",
    "direccionDistribuidor",
    "liston",
    "parListon",
    "cable",
    "parCable",
    "armario",
    "direccionArmario",
    "caja",
    "parCaja",
    "direccionCaja",
    "tipoCaja",
    "distanciaCaja",
    "latitud",
    "longitud",
    "zonaCobertura",
    "otrosTelephonData"
})*/
public class XATELEPHONEDATA {

    //@XmlElement(name = "Central")
    private String Central;
    //@XmlElement(name = "Telefono")
    private String Telefono;
    //@XmlElement(name = "Len")
    private String Len;
    //@XmlElement(name = "Posicion_Horizontal")
    private String Posicion_Horizontal;
    //@XmlElement(name = "Distribuidor")
    private String Distribuidor;
    //@XmlElement(name = "Descripcion_Distribuidor")
    private String Descripcion_Distribuidor;
    //@XmlElement(name = "Direccion_Distribuidor")
    private String Direccion_Distribuidor;
    //@XmlElement(name = "Liston")
    private String Liston;
    //@XmlElement(name = "Par_liston")
    private String Par_liston;
    //@XmlElement(name = "Cable")
    private String Cable;
    //@XmlElement(name = "Par_Cable")
    private String Par_Cable;
    //@XmlElement(name = "Armario")
    private String Armario;
    //@XmlElement(name = "Direccion_Armario")
    private String Direccion_Armario;
    //@XmlElement(name = "Caja")
    private String Caja;
    //@XmlElement(name = "Par_caja")
    private String Par_caja;
    //@XmlElement(name = "Direccion_caja")
    private String Direccion_caja;
    //@XmlElement(name = "Tipo_caja")
    private String Tipo_caja;
    //@XmlElement(name = "Distancia_caja")
    private String Distancia_caja;
    //@XmlElement(name = "Latitud")
    private String Latitud;
    //@XmlElement(name = "Longitud")
    private String Longitud;
    //@XmlElement(name = "Zona_cobertura")
    private String Zona_cobertura;
    //@XmlElement(name = "Otros_Telephon_Data")
    private OTHERTYPE Otros_Telephon_Data;

    
    
	/**
	 * @return Devuelve armario.
	 */
	public String getArmario() {
		return Armario;
	}
	/**
	 * @param armario El armario a establecer.
	 */
	public void setArmario(String armario) {
		Armario = armario;
	}
	/**
	 * @return Devuelve cable.
	 */
	public String getCable() {
		return Cable;
	}
	/**
	 * @param cable El cable a establecer.
	 */
	public void setCable(String cable) {
		Cable = cable;
	}
	/**
	 * @return Devuelve caja.
	 */
	public String getCaja() {
		return Caja;
	}
	/**
	 * @param caja El caja a establecer.
	 */
	public void setCaja(String caja) {
		Caja = caja;
	}
	/**
	 * @return Devuelve central.
	 */
	public String getCentral() {
		return Central;
	}
	/**
	 * @param central El central a establecer.
	 */
	public void setCentral(String central) {
		Central = central;
	}
	/**
	 * @return Devuelve descripcion_Distribuidor.
	 */
	public String getDescripcion_Distribuidor() {
		return Descripcion_Distribuidor;
	}
	/**
	 * @param descripcion_Distribuidor El descripcion_Distribuidor a establecer.
	 */
	public void setDescripcion_Distribuidor(String descripcion_Distribuidor) {
		Descripcion_Distribuidor = descripcion_Distribuidor;
	}
	/**
	 * @return Devuelve direccion_Armario.
	 */
	public String getDireccion_Armario() {
		return Direccion_Armario;
	}
	/**
	 * @param direccion_Armario El direccion_Armario a establecer.
	 */
	public void setDireccion_Armario(String direccion_Armario) {
		Direccion_Armario = direccion_Armario;
	}
	/**
	 * @return Devuelve direccion_caja.
	 */
	public String getDireccion_caja() {
		return Direccion_caja;
	}
	/**
	 * @param direccion_caja El direccion_caja a establecer.
	 */
	public void setDireccion_caja(String direccion_caja) {
		Direccion_caja = direccion_caja;
	}
	/**
	 * @return Devuelve direccion_Distribuidor.
	 */
	public String getDireccion_Distribuidor() {
		return Direccion_Distribuidor;
	}
	/**
	 * @param direccion_Distribuidor El direccion_Distribuidor a establecer.
	 */
	public void setDireccion_Distribuidor(String direccion_Distribuidor) {
		Direccion_Distribuidor = direccion_Distribuidor;
	}
	/**
	 * @return Devuelve distancia_caja.
	 */
	public String getDistancia_caja() {
		return Distancia_caja;
	}
	/**
	 * @param distancia_caja El distancia_caja a establecer.
	 */
	public void setDistancia_caja(String distancia_caja) {
		Distancia_caja = distancia_caja;
	}
	/**
	 * @return Devuelve distribuidor.
	 */
	public String getDistribuidor() {
		return Distribuidor;
	}
	/**
	 * @param distribuidor El distribuidor a establecer.
	 */
	public void setDistribuidor(String distribuidor) {
		Distribuidor = distribuidor;
	}
	/**
	 * @return Devuelve latitud.
	 */
	public String getLatitud() {
		return Latitud;
	}
	/**
	 * @param latitud El latitud a establecer.
	 */
	public void setLatitud(String latitud) {
		Latitud = latitud;
	}
	/**
	 * @return Devuelve len.
	 */
	public String getLen() {
		return Len;
	}
	/**
	 * @param len El len a establecer.
	 */
	public void setLen(String len) {
		Len = len;
	}
	/**
	 * @return Devuelve liston.
	 */
	public String getListon() {
		return Liston;
	}
	/**
	 * @param liston El liston a establecer.
	 */
	public void setListon(String liston) {
		Liston = liston;
	}
	/**
	 * @return Devuelve longitud.
	 */
	public String getLongitud() {
		return Longitud;
	}
	/**
	 * @param longitud El longitud a establecer.
	 */
	public void setLongitud(String longitud) {
		Longitud = longitud;
	}
	/**
	 * @return Devuelve otros_Telephon_Data.
	 */
	public OTHERTYPE getOtros_Telephon_Data() {
		return Otros_Telephon_Data;
	}
	/**
	 * @param otros_Telephon_Data El otros_Telephon_Data a establecer.
	 */
	public void setOtros_Telephon_Data(OTHERTYPE otros_Telephon_Data) {
		Otros_Telephon_Data = otros_Telephon_Data;
	}
	/**
	 * @return Devuelve par_Cable.
	 */
	public String getPar_Cable() {
		return Par_Cable;
	}
	/**
	 * @param par_Cable El par_Cable a establecer.
	 */
	public void setPar_Cable(String par_Cable) {
		Par_Cable = par_Cable;
	}
	/**
	 * @return Devuelve par_caja.
	 */
	public String getPar_caja() {
		return Par_caja;
	}
	/**
	 * @param par_caja El par_caja a establecer.
	 */
	public void setPar_caja(String par_caja) {
		Par_caja = par_caja;
	}
	/**
	 * @return Devuelve par_liston.
	 */
	public String getPar_liston() {
		return Par_liston;
	}
	/**
	 * @param par_liston El par_liston a establecer.
	 */
	public void setPar_liston(String par_liston) {
		Par_liston = par_liston;
	}
	/**
	 * @return Devuelve posicion_Horizontal.
	 */
	public String getPosicion_Horizontal() {
		return Posicion_Horizontal;
	}
	/**
	 * @param posicion_Horizontal El posicion_Horizontal a establecer.
	 */
	public void setPosicion_Horizontal(String posicion_Horizontal) {
		Posicion_Horizontal = posicion_Horizontal;
	}
	/**
	 * @return Devuelve telefono.
	 */
	public String getTelefono() {
		return Telefono;
	}
	/**
	 * @param telefono El telefono a establecer.
	 */
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	/**
	 * @return Devuelve tipo_caja.
	 */
	public String getTipo_caja() {
		return Tipo_caja;
	}
	/**
	 * @param tipo_caja El tipo_caja a establecer.
	 */
	public void setTipo_caja(String tipo_caja) {
		Tipo_caja = tipo_caja;
	}
	/**
	 * @return Devuelve zona_cobertura.
	 */
	public String getZona_cobertura() {
		return Zona_cobertura;
	}
	/**
	 * @param zona_cobertura El zona_cobertura a establecer.
	 */
	public void setZona_cobertura(String zona_cobertura) {
		Zona_cobertura = zona_cobertura;
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
