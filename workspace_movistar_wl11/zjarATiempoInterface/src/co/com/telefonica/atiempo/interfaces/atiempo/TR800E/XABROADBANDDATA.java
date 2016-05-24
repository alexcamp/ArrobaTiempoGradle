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
 * <p>Clase Java para XA_BROADBAND_DATA complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="XA_BROADBAND_DATA"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Velocidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Tipo_IP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Puerto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="POTs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ADSL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Direccion_IP_DISLAM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Direccion_IP_WAN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Direccion_IP_LAN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Mascara_LAN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Frame" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Tarjeta_Slot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RACK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SUBRACK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VPI_VCI_Cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VPI_VCI_Red" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Usuario_Acceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Otros_Datos_BA" type="{http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/}OTHERTYPE" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
/*@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XA_BROADBAND_DATA", propOrder = {
    "velocidad",
    "tipoIP",
    "puerto",
    "poTs",
    "adsl",
    "direccionIPDISLAM",
    "direccionIPWAN",
    "direccionIPLAN",
    "mascaraLAN",
    "frame",
    "tarjetaSlot",
    "rack",
    "subrack",
    "vpivciCliente",
    "vpivciRed",
    "usuarioAcceso",
    "otrosDatosBA"
})*/
public class XABROADBANDDATA {

    //@XmlElement(name = "Velocidad")
    private String Velocidad;
    //@XmlElement(name = "Tipo_IP")
    private String Tipo_IP;
    //@XmlElement(name = "Puerto")
    private String Puerto;
    //@XmlElement(name = "POTs")
    private String POTs;
    //@XmlElement(name = "ADSL")
    private String ADSL;
    //@XmlElement(name = "Direccion_IP_DISLAM")
    private String Direccion_IP_DISLAM;
    //@XmlElement(name = "Direccion_IP_WAN")
    private String Direccion_IP_WAN;
    //@XmlElement(name = "Direccion_IP_LAN")
    private String Direccion_IP_LAN;
    //@XmlElement(name = "Mascara_LAN")
    private String Mascara_LAN;
    //@XmlElement(name = "Frame")
    private String Frame;
    //@XmlElement(name = "Tarjeta_Slot")
    private String Tarjeta_Slot;
    //@XmlElement(name = "RACK")
    private String RACK;
    //@XmlElement(name = "SUBRACK")
    private String SUBRACK;
    //@XmlElement(name = "VPI_VCI_Cliente")
    private String VPI_VCI_Cliente;
    //@XmlElement(name = "VPI_VCI_Red")
    private String VPI_VCI_Red;
    //@XmlElement(name = "Usuario_Acceso")
    private String Usuario_Acceso;
    //@XmlElement(name = "Otros_Datos_BA")
    private OTHERTYPE Otros_Datos_BA;

    

	/**
	 * @return Devuelve adsl.
	 */
	public String getADSL() {
		return ADSL;
	}
	/**
	 * @param adsl El adsl a establecer.
	 */
	public void setADSL(String adsl) {
		this.ADSL = adsl;
	}
	/**
	 * @return Devuelve direccionIPDISLAM.
	 */
	public String getDireccion_IP_DISLAM() {
		return Direccion_IP_DISLAM;
	}
	/**
	 * @param direccionIPDISLAM El direccionIPDISLAM a establecer.
	 */
	public void setDireccion_IP_DISLAM(String direccionIPDISLAM) {
		this.Direccion_IP_DISLAM = direccionIPDISLAM;
	}
	/**
	 * @return Devuelve direccionIPLAN.
	 */
	public String getDireccion_IP_LAN() {
		return Direccion_IP_LAN;
	}
	/**
	 * @param direccionIPLAN El direccionIPLAN a establecer.
	 */
	public void setDireccion_IP_LAN(String direccionIPLAN) {
		this.Direccion_IP_LAN = direccionIPLAN;
	}
	/**
	 * @return Devuelve direccionIPWAN.
	 */
	public String getDireccion_IP_WAN() {
		return Direccion_IP_WAN;
	}
	/**
	 * @param direccionIPWAN El direccionIPWAN a establecer.
	 */
	public void setDireccion_IP_WAN(String direccionIPWAN) {
		this.Direccion_IP_WAN = direccionIPWAN;
	}
	/**
	 * @return Devuelve frame.
	 */
	public String getFrame() {
		return Frame;
	}
	/**
	 * @param frame El frame a establecer.
	 */
	public void setFrame(String frame) {
		this.Frame = frame;
	}
	/**
	 * @return Devuelve mascaraLAN.
	 */
	public String getMascara_LAN() {
		return Mascara_LAN;
	}
	/**
	 * @param mascaraLAN El mascaraLAN a establecer.
	 */
	public void setMascara_LAN(String mascaraLAN) {
		this.Mascara_LAN = mascaraLAN;
	}
	/**
	 * @return Devuelve otrosDatosBA.
	 */
	public OTHERTYPE getOtros_Datos_BA() {
		return Otros_Datos_BA;
	}
	/**
	 * @param otrosDatosBA El otrosDatosBA a establecer.
	 */
	public void setOtros_Datos_BA(OTHERTYPE otrosDatosBA) {
		this.Otros_Datos_BA = otrosDatosBA;
	}
	/**
	 * @return Devuelve poTs.
	 */
	public String getPOTs() {
		return POTs;
	}
	/**
	 * @param poTs El poTs a establecer.
	 */
	public void setPOTs(String poTs) {
		this.POTs = poTs;
	}
	/**
	 * @return Devuelve puerto.
	 */
	public String getPuerto() {
		return Puerto;
	}
	/**
	 * @param puerto El puerto a establecer.
	 */
	public void setPuerto(String puerto) {
		this.Puerto = puerto;
	}
	/**
	 * @return Devuelve rack.
	 */
	public String getRACK() {
		return RACK;
	}
	/**
	 * @param rack El rack a establecer.
	 */
	public void setRACK(String rack) {
		this.RACK = rack;
	}
	/**
	 * @return Devuelve subrack.
	 */
	public String getSUBRACK() {
		return SUBRACK;
	}
	/**
	 * @param subrack El subrack a establecer.
	 */
	public void setSUBRACK(String subrack) {
		this.SUBRACK = subrack;
	}
	/**
	 * @return Devuelve tarjetaSlot.
	 */
	public String getTarjeta_Slot() {
		return Tarjeta_Slot;
	}
	/**
	 * @param tarjetaSlot El tarjetaSlot a establecer.
	 */
	public void setTarjeta_Slot(String tarjetaSlot) {
		this.Tarjeta_Slot = tarjetaSlot;
	}
	/**
	 * @return Devuelve tipoIP.
	 */
	public String getTipo_IP() {
		return Tipo_IP;
	}
	/**
	 * @param tipoIP El tipoIP a establecer.
	 */
	public void setTipo_IP(String tipoIP) {
		this.Tipo_IP = tipoIP;
	}
	/**
	 * @return Devuelve usuarioAcceso.
	 */
	public String getUsuario_Acceso() {
		return Usuario_Acceso;
	}
	/**
	 * @param usuarioAcceso El usuarioAcceso a establecer.
	 */
	public void setUsuario_Acceso(String usuarioAcceso) {
		this.Usuario_Acceso = usuarioAcceso;
	}
	/**
	 * @return Devuelve velocidad.
	 */
	public String getVelocidad() {
		return Velocidad;
	}
	/**
	 * @param velocidad El velocidad a establecer.
	 */
	public void setVelocidad(String velocidad) {
		this.Velocidad = velocidad;
	}
	/**
	 * @return Devuelve vpivciCliente.
	 */
	public String getVPI_VCI_Cliente() {
		return VPI_VCI_Cliente;
	}
	/**
	 * @param vpivciCliente El vpivciCliente a establecer.
	 */
	public void setVPI_VCI_Cliente(String vpivciCliente) {
		this.VPI_VCI_Cliente = vpivciCliente;
	}
	/**
	 * @return Devuelve vpivciRed.
	 */
	public String getVPI_VCI_Red() {
		return VPI_VCI_Red;
	}
	/**
	 * @param vpivciRed El vpivciRed a establecer.
	 */
	public void setVPI_VCI_Red(String vpivciRed) {
		this.VPI_VCI_Red = vpivciRed;
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
