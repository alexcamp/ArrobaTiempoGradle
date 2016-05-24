package co.com.telefonica.atiempo.interfaces.atiempo.TR800E;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;


/**
 * <p>Clase Java para EQUIPOS_CLIENTE complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="EQUIPOS_CLIENTE"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="inv_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="invsn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XI_BRAND" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XI_MODEL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XI_SAP_DOCUMENT_NUMBER" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="XI_SAP_DOCUMENT_YEAR" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="XI_SAP_CODE" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="XI_SAP_CODE_DESCRIPTION" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="XI_SAP_CODE_TJ" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="XI_CARD_SERIAL_NUMBER" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="XI_CAS_ID" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="XI_BULK_SAP" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="XI_SAP_DISTRIBUTION_CENTER" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="XI_SAP_MOVIMIENTO" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="XI_SAP_BOD_CTISTA" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="XI_SAP_PEP" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="XI_SAP" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="XI_SAP_OTROS" type="{http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/}OTHERTYPE" minOccurs="0"/&gt;
 *         &lt;element name="I_ACTIVATION_NUMBER" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="XI_MATERIAL_CODE" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
/*@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EQUIPOS_CLIENTE", propOrder = {
    "invType",
    "invsn",
    "xibrand",
    "ximodel",
    "xisapdocumentnumber",
    "xisapdocumentyear",
    "xisapcode",
    "xisapcodedescription",
    "xisapcodetj",
    "xicardserialnumber",
    "xicasid",
    "xibulksap",
    "xisapdistributioncenter",
    "xisapmovimiento",
    "xisapbodctista",
    "xisappep",
    "xisap",
    "xisapotros",
    "iactivationnumber",
    "ximaterialcode"
})*/
public class EQUIPOSCLIENTE {

//    @XmlElement(name = "inv_type")
    protected String inv_type;
    
    protected String invsn;
//    @XmlElement(name = "XI_BRAND")
    protected String XI_BRAND;
//    @XmlElement(name = "XI_MODEL")
    protected String XI_MODEL;
//    @XmlElement(name = "XI_SAP_DOCUMENT_NUMBER")
    protected List/*String*/ XI_SAP_DOCUMENT_NUMBER;
//    @XmlElement(name = "XI_SAP_DOCUMENT_YEAR")
    protected List/*String*/ XI_SAP_DOCUMENT_YEAR;
    //@XmlElement(name = "XI_SAP_CODE")
    protected List/*String*/ XI_SAP_CODE;
    //@XmlElement(name = "XI_SAP_CODE_DESCRIPTION")
    protected List/*String*/ XI_SAP_CODE_DESCRIPTION;
    //@XmlElement(name = "XI_SAP_CODE_TJ")
    protected List/*String*/ XI_SAP_CODE_TJ;
    //@XmlElement(name = "XI_CARD_SERIAL_NUMBER")
    protected List/*String*/ XI_CARD_SERIAL_NUMBER;
    //@XmlElement(name = "XI_CAS_ID")
    protected List/*String*/ XI_CAS_ID;
    //@XmlElement(name = "XI_BULK_SAP")
    protected List/*String*/ XI_BULK_SAP;
    //@XmlElement(name = "XI_SAP_DISTRIBUTION_CENTER")
    protected List/*String*/ XI_SAP_DISTRIBUTION_CENTER;
    //@XmlElement(name = "XI_SAP_MOVIMIENTO")
    protected List/*String*/ XI_SAP_MOVIMIENTO;
    //@XmlElement(name = "XI_SAP_BOD_CTISTA")
    protected List/*String*/ XI_SAP_BOD_CTISTA;
    //@XmlElement(name = "XI_SAP_PEP")
    protected List/*String*/ XI_SAP_PEP;
    //@XmlElement(name = "XI_SAP")
    protected List/*String*/ XI_SAP;
    //@XmlElement(name = "XI_SAP_OTROS")
    protected OTHERTYPE XI_SAP_OTROS;
    //@XmlElement(name = "I_ACTIVATION_NUMBER")
    protected List/*String*/ I_ACTIVATION_NUMBER;
    //@XmlElement(name = "XI_MATERIAL_CODE")
    protected List/*String*/ XI_MATERIAL_CODE;
    
    

	/**
	 * @return Devuelve i_ACTIVATION_NUMBER.
	 */
	public List getI_ACTIVATION_NUMBER() {
		return I_ACTIVATION_NUMBER;
	}
	/**
	 * @param i_activation_number El i_ACTIVATION_NUMBER a establecer.
	 */
	public void setI_ACTIVATION_NUMBER(List i_activation_number) {
		I_ACTIVATION_NUMBER = i_activation_number;
	}
	/**
	 * @return Devuelve inv_type.
	 */
	public String getInv_type() {
		return inv_type;
	}
	/**
	 * @param inv_type El inv_type a establecer.
	 */
	public void setInv_type(String inv_type) {
		this.inv_type = inv_type;
	}
	/**
	 * @return Devuelve invsn.
	 */
	public String getInvsn() {
		return invsn;
	}
	/**
	 * @param invsn El invsn a establecer.
	 */
	public void setInvsn(String invsn) {
		this.invsn = invsn;
	}
	/**
	 * @return Devuelve xI_BRAND.
	 */
	public String getXI_BRAND() {
		return XI_BRAND;
	}
	/**
	 * @param xi_brand El xI_BRAND a establecer.
	 */
	public void setXI_BRAND(String xi_brand) {
		XI_BRAND = xi_brand;
	}
	/**
	 * @return Devuelve xI_BULK_SAP.
	 */
	public List getXI_BULK_SAP() {
		return XI_BULK_SAP;
	}
	/**
	 * @param xi_bulk_sap El xI_BULK_SAP a establecer.
	 */
	public void setXI_BULK_SAP(List xi_bulk_sap) {
		XI_BULK_SAP = xi_bulk_sap;
	}
	/**
	 * @return Devuelve xI_CARD_SERIAL_NUMBER.
	 */
	public List getXI_CARD_SERIAL_NUMBER() {
		return XI_CARD_SERIAL_NUMBER;
	}
	/**
	 * @param xi_card_serial_number El xI_CARD_SERIAL_NUMBER a establecer.
	 */
	public void setXI_CARD_SERIAL_NUMBER(List xi_card_serial_number) {
		XI_CARD_SERIAL_NUMBER = xi_card_serial_number;
	}
	/**
	 * @return Devuelve xI_CAS_ID.
	 */
	public List getXI_CAS_ID() {
		return XI_CAS_ID;
	}
	/**
	 * @param xi_cas_id El xI_CAS_ID a establecer.
	 */
	public void setXI_CAS_ID(List xi_cas_id) {
		XI_CAS_ID = xi_cas_id;
	}
	/**
	 * @return Devuelve xI_MATERIAL_CODE.
	 */
	public List getXI_MATERIAL_CODE() {
		return XI_MATERIAL_CODE;
	}
	/**
	 * @param xi_material_code El xI_MATERIAL_CODE a establecer.
	 */
	public void setXI_MATERIAL_CODE(List xi_material_code) {
		XI_MATERIAL_CODE = xi_material_code;
	}
	/**
	 * @return Devuelve xI_MODEL.
	 */
	public String getXI_MODEL() {
		return XI_MODEL;
	}
	/**
	 * @param xi_model El xI_MODEL a establecer.
	 */
	public void setXI_MODEL(String xi_model) {
		XI_MODEL = xi_model;
	}
	/**
	 * @return Devuelve xI_SAP.
	 */
	public List getXI_SAP() {
		return XI_SAP;
	}
	/**
	 * @param xi_sap El xI_SAP a establecer.
	 */
	public void setXI_SAP(List xi_sap) {
		XI_SAP = xi_sap;
	}
	/**
	 * @return Devuelve xI_SAP_BOD_CTISTA.
	 */
	public List getXI_SAP_BOD_CTISTA() {
		return XI_SAP_BOD_CTISTA;
	}
	/**
	 * @param xi_sap_bod_ctista El xI_SAP_BOD_CTISTA a establecer.
	 */
	public void setXI_SAP_BOD_CTISTA(List xi_sap_bod_ctista) {
		XI_SAP_BOD_CTISTA = xi_sap_bod_ctista;
	}
	/**
	 * @return Devuelve xI_SAP_CODE.
	 */
	public List getXI_SAP_CODE() {
		return XI_SAP_CODE;
	}
	/**
	 * @param xi_sap_code El xI_SAP_CODE a establecer.
	 */
	public void setXI_SAP_CODE(List xi_sap_code) {
		XI_SAP_CODE = xi_sap_code;
	}
	/**
	 * @return Devuelve xI_SAP_CODE_DESCRIPTION.
	 */
	public List getXI_SAP_CODE_DESCRIPTION() {
		return XI_SAP_CODE_DESCRIPTION;
	}
	/**
	 * @param xi_sap_code_description El xI_SAP_CODE_DESCRIPTION a establecer.
	 */
	public void setXI_SAP_CODE_DESCRIPTION(List xi_sap_code_description) {
		XI_SAP_CODE_DESCRIPTION = xi_sap_code_description;
	}
	/**
	 * @return Devuelve xI_SAP_CODE_TJ.
	 */
	public List getXI_SAP_CODE_TJ() {
		return XI_SAP_CODE_TJ;
	}
	/**
	 * @param xi_sap_code_tj El xI_SAP_CODE_TJ a establecer.
	 */
	public void setXI_SAP_CODE_TJ(List xi_sap_code_tj) {
		XI_SAP_CODE_TJ = xi_sap_code_tj;
	}
	/**
	 * @return Devuelve xI_SAP_DISTRIBUTION_CENTER.
	 */
	public List getXI_SAP_DISTRIBUTION_CENTER() {
		return XI_SAP_DISTRIBUTION_CENTER;
	}
	/**
	 * @param xi_sap_distribution_center El xI_SAP_DISTRIBUTION_CENTER a establecer.
	 */
	public void setXI_SAP_DISTRIBUTION_CENTER(List xi_sap_distribution_center) {
		XI_SAP_DISTRIBUTION_CENTER = xi_sap_distribution_center;
	}
	/**
	 * @return Devuelve xI_SAP_DOCUMENT_NUMBER.
	 */
	public List getXI_SAP_DOCUMENT_NUMBER() {
		return XI_SAP_DOCUMENT_NUMBER;
	}
	/**
	 * @param xi_sap_document_number El xI_SAP_DOCUMENT_NUMBER a establecer.
	 */
	public void setXI_SAP_DOCUMENT_NUMBER(List xi_sap_document_number) {
		XI_SAP_DOCUMENT_NUMBER = xi_sap_document_number;
	}
	/**
	 * @return Devuelve xI_SAP_DOCUMENT_YEAR.
	 */
	public List getXI_SAP_DOCUMENT_YEAR() {
		return XI_SAP_DOCUMENT_YEAR;
	}
	/**
	 * @param xi_sap_document_year El xI_SAP_DOCUMENT_YEAR a establecer.
	 */
	public void setXI_SAP_DOCUMENT_YEAR(List xi_sap_document_year) {
		XI_SAP_DOCUMENT_YEAR = xi_sap_document_year;
	}
	/**
	 * @return Devuelve xI_SAP_MOVIMIENTO.
	 */
	public List getXI_SAP_MOVIMIENTO() {
		return XI_SAP_MOVIMIENTO;
	}
	/**
	 * @param xi_sap_movimiento El xI_SAP_MOVIMIENTO a establecer.
	 */
	public void setXI_SAP_MOVIMIENTO(List xi_sap_movimiento) {
		XI_SAP_MOVIMIENTO = xi_sap_movimiento;
	}
	/**
	 * @return Devuelve xI_SAP_OTROS.
	 */
	public OTHERTYPE getXI_SAP_OTROS() {
		return XI_SAP_OTROS;
	}
	/**
	 * @param xi_sap_otros El xI_SAP_OTROS a establecer.
	 */
	public void setXI_SAP_OTROS(OTHERTYPE xi_sap_otros) {
		XI_SAP_OTROS = xi_sap_otros;
	}
	/**
	 * @return Devuelve xI_SAP_PEP.
	 */
	public List getXI_SAP_PEP() {
		return XI_SAP_PEP;
	}
	/**
	 * @param xi_sap_pep El xI_SAP_PEP a establecer.
	 */
	public void setXI_SAP_PEP(List xi_sap_pep) {
		XI_SAP_PEP = xi_sap_pep;
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
