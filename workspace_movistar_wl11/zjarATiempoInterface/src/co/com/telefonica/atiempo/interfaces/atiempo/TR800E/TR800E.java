package co.com.telefonica.atiempo.interfaces.atiempo.TR800E;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import co.com.telefonica.atiempo.interfaces.atiempo.base.ITRxxxBase;
import co.com.telefonica.atiempo.interfaces.atiempo.base.TRxxxBase;

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
 *         &lt;element name="XA_FAMILIA" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_SOURCE_SYSTEM" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="cname" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_CUSTOMER_ID_TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="customer_number" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_CUSTOMER_SEGMENT" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_CUSTOMER_SUBSEGMENT" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_DAT_AGDMTO_1" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_DAT_AGDMTO_2" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_CUSTOMER_VIP" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_DAT_OP_CLIENT" type="{http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/}OTHERTYPE" minOccurs="0"/&gt;
 *         &lt;element name="XA_CONTACT_NAME" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="cell" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XA_CONTACT_PHONE_NUMBER_2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XA_CONTACT_PHONE_NUMBER_3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XA_CONTACT_PHONE_NUMBER_4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XA_CONTACT_PHONE_NUMBER_5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XA_CONTACT_PHONE_NUMBER_6" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XA_DAT_OP_CONTACT" type="{http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/}OTHERTYPE" minOccurs="0"/&gt;
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_CITY_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_STATE_CODE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_NEIGHBORHOOD" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="acoord_x" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="acoord_y" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="XA_QUADRANT" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_WORK_ZONE_KEY" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="aworktype_label" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_WORK_TYPE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_DESCRIPTION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XA_DAT_OP_TPACTIVIDAD" type="{http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/}OTHERTYPE" minOccurs="0"/&gt;
 *         &lt;element name="id_actuacion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_ORDER_ATIS" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_ORDER_NUMBER" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_CREATION_DATE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="XA_DAT_OP_DATO_CITA" type="{http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/}OTHERTYPE" minOccurs="0"/&gt;
 *         &lt;element name="XA_PROJECT_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XA_REINJECTED" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XA_DAT_OP_DATO_INST" type="{http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/}OTHERTYPE" minOccurs="0"/&gt;
 *         &lt;element name="XA_MASSIVE_TYPE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XA_REITERATION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XA_YOUNG_REPAIR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XA_DAT_OP_DATO_AVER" type="{http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/}OTHERTYPE" minOccurs="0"/&gt;
 *         &lt;element name="XA_POST_SALES_CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XA_REPAIR_INFO" type="{http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/}XA_REPAIR_INFO" minOccurs="0"/&gt;
 *         &lt;element name="XA_CENTRAL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XA_BOX_TYPE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XA_TELEPHONE_DATA" type="{http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/}XA_TELEPHONE_DATA" minOccurs="0"/&gt;
 *         &lt;element name="XA_BROADBAND_DATA" type="{http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/}XA_BROADBAND_DATA" minOccurs="0"/&gt;
 *         &lt;element name="XA_NUMBER_DECODERS" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/&gt;
 *         &lt;element name="XA_ID_PCTV" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="XA_TV_DATA" type="{http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/}XA_TV_DATA" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="XA_NOTES" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XA_EQUIPMENT" type="{http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/}XA_EQUIPMENT" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="EQUIPOS_CLIENTE" type="{http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/}EQUIPOS_CLIENTE" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="OTHER" type="{http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/}OTHERTYPE" minOccurs="0"/&gt;
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
    "xafamilia",
    "xasourcesystem",
    "cname",
    "xacustomeridtype",
    "customerNumber",
    "xacustomersegment",
    "xacustomersubsegment",
    "xadatagdmto1",
    "xadatagdmto2",
    "xacustomervip",
    "xadatopclient",
    "xacontactname",
    "cell",
    "xacontactphonenumber2",
    "xacontactphonenumber3",
    "xacontactphonenumber4",
    "xacontactphonenumber5",
    "xacontactphonenumber6",
    "email",
    "phone",
    "xadatopcontact",
    "address",
    "xacitycode",
    "city",
    "xastatecode",
    "state",
    "xaneighborhood",
    "acoordX",
    "acoordY",
    "xaquadrant",
    "xaworkzonekey",
    "aworktypeLabel",
    "xaworktype",
    "xadescription",
    "xadatoptpactividad",
    "idActuacion",
    "xaorderatis",
    "xaordernumber",
    "xacreationdate",
    "date",
    "xadatopdatocita",
    "xaprojectcode",
    "xareinjected",
    "xadatopdatoinst",
    "xamassivetype",
    "xareiteration",
    "xayoungrepair",
    "xadatopdatoaver",
    "xapostsalescode",
    "xarepairinfo",
    "xacentral",
    "xaboxtype",
    "xatelephonedata",
    "xabroadbanddata",
    "xanumberdecoders",
    "xaidpctv",
    "xatvdata",
    "xanotes",
    "xaequipment",
    "equiposcliente",
    "other"
})
@XmlRootElement(name = "CreacionServicio")*/
public class TR800E extends TRxxxBase implements ITRxxxBase{
	
	public static void main (String[] args) {
		String tr800sString = "";
		TR800E tr800e = initializeTR();
//		CommunicatorWS communicatorWS = new CommunicatorWS();
//		tr800sString = communicatorWS.sendMessageByTRxxxE(tr800e);
//		System.out.println("TR800S: " + tr800sString);
//		TR800SParser dpe = new TR800SParser();
//		TR800S res = dpe.parse(tr800sString);
//		System.out.println(res);
		System.out.println(tr800e.toString());
	}
	
	public static TR800E initializeTR (){
		TR800E tr800e = new TR800E();
		
		tr800e.setAcoord_x(20.0);
		tr800e.setAcoord_y(15.2);
		tr800e.setAddress("Direccion");
		tr800e.setAworktype_label("Work Type Label");
		ArrayList equipos_Cli = new ArrayList();
		EQUIPOSCLIENTE equiposcliente = new EQUIPOSCLIENTE();
		EQUIPOSCLIENTE equiposcliente2 = new EQUIPOSCLIENTE();
		equiposcliente.setInvsn("INVAN");
		equiposcliente2.setInvsn("INVAN2");
		equipos_Cli.add(equiposcliente);
		equipos_Cli.add(equiposcliente2);
		tr800e.setEQUIPOS_CLIENTE(equipos_Cli);
		
		return tr800e;
	}
	
	

//    @XmlElement(name = "XA_FAMILIA", required = true)
    private String XA_FAMILIA;
//    @XmlElement(name = "XA_SOURCE_SYSTEM", required = true)
    private String XA_SOURCE_SYSTEM;
//    @XmlElement(required = true)
    private String cname;
//    @XmlElement(name = "XA_CUSTOMER_ID_TYPE", required = true)
    private String XA_CUSTOMER_ID_TYPE;
//    @XmlElement(name = "customer_number", required = true)
    private String customer_number;
//    @XmlElement(name = "XA_CUSTOMER_SEGMENT", required = true)
    private String XA_CUSTOMER_SEGMENT;
//    @XmlElement(name = "XA_CUSTOMER_SUBSEGMENT", required = true)
    private String XA_CUSTOMER_SUBSEGMENT;
//    @XmlElement(name = "XA_DAT_AGDMTO_1", required = true)
    private String XA_DAT_AGDMTO_1;
//    @XmlElement(name = "XA_DAT_AGDMTO_2", required = true)
    private String XA_DAT_AGDMTO_2;
//    @XmlElement(name = "XA_CUSTOMER_VIP", required = true)
    private String XA_CUSTOMER_VIP;
//    @XmlElement(name = "XA_DAT_OP_CLIENT")
    private OTHERTYPE XA_DAT_OP_CLIENT;
//    @XmlElement(name = "XA_CONTACT_NAME", required = true)
    private String XA_CONTACT_NAME;
    
    private String cell;
//    @XmlElement(name = "XA_CONTACT_PHONE_NUMBER_2")
    private String XA_CONTACT_PHONE_NUMBER_2;
//    @XmlElement(name = "XA_CONTACT_PHONE_NUMBER_3")
    private String XA_CONTACT_PHONE_NUMBER_3;
//    @XmlElement(name = "XA_CONTACT_PHONE_NUMBER_4")
    private String XA_CONTACT_PHONE_NUMBER_4;
//    @XmlElement(name = "XA_CONTACT_PHONE_NUMBER_5")
    private String XA_CONTACT_PHONE_NUMBER_5;
//    @XmlElement(name = "XA_CONTACT_PHONE_NUMBER_6")
    private String XA_CONTACT_PHONE_NUMBER_6;
    private String email;
    private String phone;
//    @XmlElement(name = "XA_DAT_OP_CONTACT")
    private OTHERTYPE XA_DAT_OP_CONTACT;
//    @XmlElement(required = true)
    private String address;
//    @XmlElement(name = "XA_CITY_CODE", required = true)
    private String XA_CITY_CODE;
//    @XmlElement(required = true)
    private String city;
//    @XmlElement(name = "XA_STATE_CODE", required = true)
    private String XA_STATE_CODE;
//    @XmlElement(required = true)
    private String state;
//    @XmlElement(name = "XA_NEIGHBORHOOD", required = true)
    private String XA_NEIGHBORHOOD;
//    @XmlElement(name = "acoord_x", required = true)
    private double acoord_x;
//    @XmlElement(name = "acoord_y", required = true)
    private double acoord_y;
//    @XmlElement(name = "XA_QUADRANT", required = true)
    private String XA_QUADRANT;
//    @XmlElement(name = "XA_WORK_ZONE_KEY", required = true)
    private String XA_WORK_ZONE_KEY;
//    @XmlElement(name = "aworktype_label", required = true)
    private String aworktype_label;
//    @XmlElement(name = "XA_WORK_TYPE", required = true)
    private String XA_WORK_TYPE;
//    @XmlElement(name = "XA_DESCRIPTION")
    private String XA_DESCRIPTION;
//    @XmlElement(name = "XA_DAT_OP_TPACTIVIDAD")
    private OTHERTYPE XA_DAT_OP_TPACTIVIDAD;
//    @XmlElement(name = "id_actuacion", required = true)
    private String id_actuacion;
//    @XmlElement(name = "XA_ORDER_ATIS", required = true)
    private String XA_ORDER_ATIS;
//    @XmlElement(name = "XA_ORDER_NUMBER", required = true)
    private String XA_ORDER_NUMBER;
//    @XmlElement(name = "XA_CREATION_DATE", required = true)
    private String XA_CREATION_DATE;
//    @XmlElement(required = true)
    private String date;
//    @XmlElement(name = "XA_DAT_OP_DATO_CITA")
    private OTHERTYPE XA_DAT_OP_DATO_CITA;
//    @XmlElement(name = "XA_PROJECT_CODE")
    private String XA_PROJECT_CODE;
//    @XmlElement(name = "XA_REINJECTED")
    private String XA_REINJECTED;
//    @XmlElement(name = "XA_DAT_OP_DATO_INST")
    private OTHERTYPE XA_DAT_OP_DATO_INST;
//    @XmlElement(name = "XA_MASSIVE_TYPE")
//    private String XA_MASSIVE_TYPE;
//    @XmlElement(name = "XA_REITERATION")
    private String XA_REITERATION;
//    @XmlElement(name = "XA_YOUNG_REPAIR")
    private String XA_YOUNG_REPAIR;
//    @XmlElement(name = "XA_DAT_OP_DATO_AVER")
    private OTHERTYPE XA_DAT_OP_DATO_AVER;
//    @XmlElement(name = "XA_POST_SALES_CODE")
    private String XA_POST_SALES_CODE;
//    @XmlElement(name = "XA_REPAIR_INFO")
    private XAREPAIRINFO XA_REPAIR_INFO;
//    @XmlElement(name = "XA_CENTRAL")
    private String XA_CENTRAL;
//    @XmlElement(name = "XA_BOX_TYPE")
    private String XA_BOX_TYPE;
//    @XmlElement(name = "XA_TELEPHONE_DATA")
    private XATELEPHONEDATA XA_TELEPHONE_DATA;
//    @XmlElement(name = "XA_BROADBAND_DATA")
    private XABROADBANDDATA XA_BROADBAND_DATA;
//    @XmlElement(name = "XA_NUMBER_DECODERS", required = true)
    private List/*String*/ XA_NUMBER_DECODERS;
//    @XmlElement(name = "XA_ID_PCTV")
    private List/*String*/ XA_ID_PCTV;
//    @XmlElement(name = "XA_TV_DATA")
    private List/*XATVDATA*/ XA_TV_DATA;
//    @XmlElement(name = "XA_NOTES")
    private String XA_NOTES;
//    @XmlElement(name = "XA_EQUIPMENT")
    private List/*XAEQUIPMENT*/ XA_EQUIPMENT;
//    @XmlElement(name = "EQUIPOS_CLIENTE")
    private List/*EQUIPOSCLIENTE*/ EQUIPOS_CLIENTE;
//    @XmlElement(name = "OTHER")
    private OTHERTYPE OTHER;

    

/**
 * @return Devuelve a_CONTACT_PHONE_NUMBER_4.
 */
public String getXA_CONTACT_PHONE_NUMBER_4() {
	return XA_CONTACT_PHONE_NUMBER_4;
}
/**
 * @param xa_contact_phone_number_4 El a_CONTACT_PHONE_NUMBER_4 a establecer.
 */
public void setXA_CONTACT_PHONE_NUMBER_4(String xa_contact_phone_number_4) {
	XA_CONTACT_PHONE_NUMBER_4 = xa_contact_phone_number_4;
}
/**
 * @return Devuelve acoord_x.
 */
public double getAcoord_x() {
	return acoord_x;
}
/**
 * @param acoord_x El acoord_x a establecer.
 */
public void setAcoord_x(double acoord_x) {
	this.acoord_x = acoord_x;
}
/**
 * @return Devuelve acoord_y.
 */
public double getAcoord_y() {
	return acoord_y;
}
/**
 * @param acoord_y El acoord_y a establecer.
 */
public void setAcoord_y(double acoord_y) {
	this.acoord_y = acoord_y;
}
/**
 * @return Devuelve address.
 */
public String getAddress() {
	return address;
}
/**
 * @param address El address a establecer.
 */
public void setAddress(String address) {
	this.address = address;
}
/**
 * @return Devuelve aworktype_label.
 */
public String getAworktype_label() {
	return aworktype_label;
}
/**
 * @param aworktype_label El aworktype_label a establecer.
 */
public void setAworktype_label(String aworktype_label) {
	this.aworktype_label = aworktype_label;
}
	/**
	 * @return Devuelve cell.
	 */
	public String getCell() {
		return cell;
	}
	/**
	 * @param cell El cell a establecer.
	 */
	public void setCell(String cell) {
		this.cell = cell;
	}
/**
 * @return Devuelve city.
 */
public String getCity() {
	return city;
}
/**
 * @param city El city a establecer.
 */
public void setCity(String city) {
	this.city = city;
}
/**
 * @return Devuelve cname.
 */
public String getCname() {
	return cname;
}
/**
 * @param cname El cname a establecer.
 */
public void setCname(String cname) {
	this.cname = cname;
}
/**
 * @return Devuelve customer_number.
 */
public String getCustomer_number() {
	return customer_number;
}
/**
 * @param customer_number El customer_number a establecer.
 */
public void setCustomer_number(String customer_number) {
	this.customer_number = customer_number;
}
/**
 * @return Devuelve date.
 */
public String getDate() {
	return date;
}
/**
 * @param date El date a establecer.
 */
public void setDate(String date) {
	this.date = date;
}
	/**
	 * @return Devuelve email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email El email a establecer.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
/**
 * @return Devuelve eQUIPOS_CLIENTE.
 */
public List getEQUIPOS_CLIENTE() {
	return EQUIPOS_CLIENTE;
}
/**
 * @param equipos_cliente El eQUIPOS_CLIENTE a establecer.
 */
public void setEQUIPOS_CLIENTE(List equipos_cliente) {
	EQUIPOS_CLIENTE = equipos_cliente;
}
/**
 * @return Devuelve id_actuacion.
 */
public String getId_actuacion() {
	return id_actuacion;
}
/**
 * @param id_actuacion El id_actuacion a establecer.
 */
public void setId_actuacion(String id_actuacion) {
	this.id_actuacion = id_actuacion;
}
/**
 * @return Devuelve oTHER.
 */
public OTHERTYPE getOTHER() {
	return OTHER;
}
/**
 * @param other El oTHER a establecer.
 */
public void setOTHER(OTHERTYPE other) {
	OTHER = other;
}
	/**
	 * @return Devuelve phone.
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone El phone a establecer.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return Devuelve state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state El state a establecer.
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return Devuelve xA_BOX_TYPE.
	 */
	public String getXA_BOX_TYPE() {
		return XA_BOX_TYPE;
	}
	/**
	 * @param xa_box_type El xA_BOX_TYPE a establecer.
	 */
	public void setXA_BOX_TYPE(String xa_box_type) {
		XA_BOX_TYPE = xa_box_type;
	}
	/**
	 * @return Devuelve xA_BROADBAND_DATA.
	 */
	public XABROADBANDDATA getXA_BROADBAND_DATA() {
		return XA_BROADBAND_DATA;
	}
	/**
	 * @param xa_broadband_data El xA_BROADBAND_DATA a establecer.
	 */
	public void setXA_BROADBAND_DATA(XABROADBANDDATA xa_broadband_data) {
		XA_BROADBAND_DATA = xa_broadband_data;
	}
	/**
	 * @return Devuelve xA_CENTRAL.
	 */
	public String getXA_CENTRAL() {
		return XA_CENTRAL;
	}
	/**
	 * @param xa_central El xA_CENTRAL a establecer.
	 */
	public void setXA_CENTRAL(String xa_central) {
		XA_CENTRAL = xa_central;
	}
	/**
	 * @return Devuelve xA_CITY_CODE.
	 */
	public String getXA_CITY_CODE() {
		return XA_CITY_CODE;
	}
	/**
	 * @param xa_city_code El xA_CITY_CODE a establecer.
	 */
	public void setXA_CITY_CODE(String xa_city_code) {
		XA_CITY_CODE = xa_city_code;
	}
	/**
	 * @return Devuelve xA_CONTACT_NAME.
	 */
	public String getXA_CONTACT_NAME() {
		return XA_CONTACT_NAME;
	}
	/**
	 * @param xa_contact_name El xA_CONTACT_NAME a establecer.
	 */
	public void setXA_CONTACT_NAME(String xa_contact_name) {
		XA_CONTACT_NAME = xa_contact_name;
	}
	/**
	 * @return Devuelve xA_CONTACT_PHONE_NUMBER_2.
	 */
	public String getXA_CONTACT_PHONE_NUMBER_2() {
		return XA_CONTACT_PHONE_NUMBER_2;
	}
	/**
	 * @param xa_contact_phone_number_2 El xA_CONTACT_PHONE_NUMBER_2 a establecer.
	 */
	public void setXA_CONTACT_PHONE_NUMBER_2(String xa_contact_phone_number_2) {
		XA_CONTACT_PHONE_NUMBER_2 = xa_contact_phone_number_2;
	}
	/**
	 * @return Devuelve xA_CONTACT_PHONE_NUMBER_3.
	 */
	public String getXA_CONTACT_PHONE_NUMBER_3() {
		return XA_CONTACT_PHONE_NUMBER_3;
	}
	/**
	 * @param xa_contact_phone_number_3 El xA_CONTACT_PHONE_NUMBER_3 a establecer.
	 */
	public void setXA_CONTACT_PHONE_NUMBER_3(String xa_contact_phone_number_3) {
		XA_CONTACT_PHONE_NUMBER_3 = xa_contact_phone_number_3;
	}
	/**
	 * @return Devuelve xA_CONTACT_PHONE_NUMBER_5.
	 */
	public String getXA_CONTACT_PHONE_NUMBER_5() {
		return XA_CONTACT_PHONE_NUMBER_5;
	}
	/**
	 * @param xa_contact_phone_number_5 El xA_CONTACT_PHONE_NUMBER_5 a establecer.
	 */
	public void setXA_CONTACT_PHONE_NUMBER_5(String xa_contact_phone_number_5) {
		XA_CONTACT_PHONE_NUMBER_5 = xa_contact_phone_number_5;
	}
	/**
	 * @return Devuelve xA_CONTACT_PHONE_NUMBER_6.
	 */
	public String getXA_CONTACT_PHONE_NUMBER_6() {
		return XA_CONTACT_PHONE_NUMBER_6;
	}
	/**
	 * @param xa_contact_phone_number_6 El xA_CONTACT_PHONE_NUMBER_6 a establecer.
	 */
	public void setXA_CONTACT_PHONE_NUMBER_6(String xa_contact_phone_number_6) {
		XA_CONTACT_PHONE_NUMBER_6 = xa_contact_phone_number_6;
	}
	/**
	 * @return Devuelve xA_CREATION_DATE.
	 */
	public String getXA_CREATION_DATE() {
		return XA_CREATION_DATE;
	}
	/**
	 * @param xa_creation_date El xA_CREATION_DATE a establecer.
	 */
	public void setXA_CREATION_DATE(String xa_creation_date) {
		XA_CREATION_DATE = xa_creation_date;
	}
	/**
	 * @return Devuelve xA_CUSTOMER_ID_TYPE.
	 */
	public String getXA_CUSTOMER_ID_TYPE() {
		return XA_CUSTOMER_ID_TYPE;
	}
	/**
	 * @param xa_customer_id_type El xA_CUSTOMER_ID_TYPE a establecer.
	 */
	public void setXA_CUSTOMER_ID_TYPE(String xa_customer_id_type) {
		XA_CUSTOMER_ID_TYPE = xa_customer_id_type;
	}
	/**
	 * @return Devuelve xA_CUSTOMER_SEGMENT.
	 */
	public String getXA_CUSTOMER_SEGMENT() {
		return XA_CUSTOMER_SEGMENT;
	}
	/**
	 * @param xa_customer_segment El xA_CUSTOMER_SEGMENT a establecer.
	 */
	public void setXA_CUSTOMER_SEGMENT(String xa_customer_segment) {
		XA_CUSTOMER_SEGMENT = xa_customer_segment;
	}
	/**
	 * @return Devuelve xA_CUSTOMER_SUBSEGMENT.
	 */
	public String getXA_CUSTOMER_SUBSEGMENT() {
		return XA_CUSTOMER_SUBSEGMENT;
	}
	/**
	 * @param xa_customer_subsegment El xA_CUSTOMER_SUBSEGMENT a establecer.
	 */
	public void setXA_CUSTOMER_SUBSEGMENT(String xa_customer_subsegment) {
		XA_CUSTOMER_SUBSEGMENT = xa_customer_subsegment;
	}
	/**
	 * @return Devuelve xA_CUSTOMER_VIP.
	 */
	public String getXA_CUSTOMER_VIP() {
		return XA_CUSTOMER_VIP;
	}
	/**
	 * @param xa_customer_vip El xA_CUSTOMER_VIP a establecer.
	 */
	public void setXA_CUSTOMER_VIP(String xa_customer_vip) {
		XA_CUSTOMER_VIP = xa_customer_vip;
	}
	/**
	 * @return Devuelve xA_DAT_AGDMTO_1.
	 */
	public String getXA_DAT_AGDMTO_1() {
		return XA_DAT_AGDMTO_1;
	}
	/**
	 * @param xa_dat_agdmto_1 El xA_DAT_AGDMTO_1 a establecer.
	 */
	public void setXA_DAT_AGDMTO_1(String xa_dat_agdmto_1) {
		XA_DAT_AGDMTO_1 = xa_dat_agdmto_1;
	}
	/**
	 * @return Devuelve xA_DAT_AGDMTO_2.
	 */
	public String getXA_DAT_AGDMTO_2() {
		return XA_DAT_AGDMTO_2;
	}
	/**
	 * @param xa_dat_agdmto_2 El xA_DAT_AGDMTO_2 a establecer.
	 */
	public void setXA_DAT_AGDMTO_2(String xa_dat_agdmto_2) {
		XA_DAT_AGDMTO_2 = xa_dat_agdmto_2;
	}
	/**
	 * @return Devuelve xA_DAT_OP_CLIENT.
	 */
	public OTHERTYPE getXA_DAT_OP_CLIENT() {
		return XA_DAT_OP_CLIENT;
	}
	/**
	 * @param xa_dat_op_client El xA_DAT_OP_CLIENT a establecer.
	 */
	public void setXA_DAT_OP_CLIENT(OTHERTYPE xa_dat_op_client) {
		XA_DAT_OP_CLIENT = xa_dat_op_client;
	}
	/**
	 * @return Devuelve xA_DAT_OP_CONTACT.
	 */
	public OTHERTYPE getXA_DAT_OP_CONTACT() {
		return XA_DAT_OP_CONTACT;
	}
	/**
	 * @param xa_dat_op_contact El xA_DAT_OP_CONTACT a establecer.
	 */
	public void setXA_DAT_OP_CONTACT(OTHERTYPE xa_dat_op_contact) {
		XA_DAT_OP_CONTACT = xa_dat_op_contact;
	}
	/**
	 * @return Devuelve xA_DAT_OP_DATO_AVER.
	 */
	public OTHERTYPE getXA_DAT_OP_DATO_AVER() {
		return XA_DAT_OP_DATO_AVER;
	}
	/**
	 * @param xa_dat_op_dato_aver El xA_DAT_OP_DATO_AVER a establecer.
	 */
	public void setXA_DAT_OP_DATO_AVER(OTHERTYPE xa_dat_op_dato_aver) {
		XA_DAT_OP_DATO_AVER = xa_dat_op_dato_aver;
	}
	/**
	 * @return Devuelve xA_DAT_OP_DATO_CITA.
	 */
	public OTHERTYPE getXA_DAT_OP_DATO_CITA() {
		return XA_DAT_OP_DATO_CITA;
	}
	/**
	 * @param xa_dat_op_dato_cita El xA_DAT_OP_DATO_CITA a establecer.
	 */
	public void setXA_DAT_OP_DATO_CITA(OTHERTYPE xa_dat_op_dato_cita) {
		XA_DAT_OP_DATO_CITA = xa_dat_op_dato_cita;
	}
	/**
	 * @return Devuelve xA_DAT_OP_DATO_INST.
	 */
	public OTHERTYPE getXA_DAT_OP_DATO_INST() {
		return XA_DAT_OP_DATO_INST;
	}
	/**
	 * @param xa_dat_op_dato_inst El xA_DAT_OP_DATO_INST a establecer.
	 */
	public void setXA_DAT_OP_DATO_INST(OTHERTYPE xa_dat_op_dato_inst) {
		XA_DAT_OP_DATO_INST = xa_dat_op_dato_inst;
	}
	/**
	 * @return Devuelve xA_DAT_OP_TPACTIVIDAD.
	 */
	public OTHERTYPE getXA_DAT_OP_TPACTIVIDAD() {
		return XA_DAT_OP_TPACTIVIDAD;
	}
	/**
	 * @param xa_dat_op_tpactividad El xA_DAT_OP_TPACTIVIDAD a establecer.
	 */
	public void setXA_DAT_OP_TPACTIVIDAD(OTHERTYPE xa_dat_op_tpactividad) {
		XA_DAT_OP_TPACTIVIDAD = xa_dat_op_tpactividad;
	}
	/**
	 * @return Devuelve xA_DESCRIPTION.
	 */
	public String getXA_DESCRIPTION() {
		return XA_DESCRIPTION;
	}
	/**
	 * @param xa_description El xA_DESCRIPTION a establecer.
	 */
	public void setXA_DESCRIPTION(String xa_description) {
		XA_DESCRIPTION = xa_description;
	}
	/**
	 * @return Devuelve xA_EQUIPMENT.
	 */
	public List getXA_EQUIPMENT() {
		return XA_EQUIPMENT;
	}
	/**
	 * @param xa_equipment El xA_EQUIPMENT a establecer.
	 */
	public void setXA_EQUIPMENT(List xa_equipment) {
		XA_EQUIPMENT = xa_equipment;
	}
	/**
	 * @return Devuelve xA_FAMILIA.
	 */
	public String getXA_FAMILIA() {
		return XA_FAMILIA;
	}
	/**
	 * @param xa_familia El xA_FAMILIA a establecer.
	 */
	public void setXA_FAMILIA(String xa_familia) {
		XA_FAMILIA = xa_familia;
	}
	/**
	 * @return Devuelve xA_ID_PCTV.
	 */
	public List getXA_ID_PCTV() {
		return XA_ID_PCTV;
	}
	/**
	 * @param xa_id_pctv El xA_ID_PCTV a establecer.
	 */
	public void setXA_ID_PCTV(List xa_id_pctv) {
		XA_ID_PCTV = xa_id_pctv;
	}
	/**
	 * @return Devuelve xA_MASSIVE_TYPE.
	 */
//	public String getXA_MASSIVE_TYPE() {
//		return XA_MASSIVE_TYPE;
//	}
//	/**
//	 * @param xa_massive_type El xA_MASSIVE_TYPE a establecer.
//	 */
//	public void setXA_MASSIVE_TYPE(String xa_massive_type) {
//		XA_MASSIVE_TYPE = xa_massive_type;
//	}
	/**
	 * @return Devuelve xA_NEIGHBORHOOD.
	 */
	public String getXA_NEIGHBORHOOD() {
		return XA_NEIGHBORHOOD;
	}
	/**
	 * @param xa_neighborhood El xA_NEIGHBORHOOD a establecer.
	 */
	public void setXA_NEIGHBORHOOD(String xa_neighborhood) {
		XA_NEIGHBORHOOD = xa_neighborhood;
	}
	/**
	 * @return Devuelve xA_NOTES.
	 */
	public String getXA_NOTES() {
		return XA_NOTES;
	}
	/**
	 * @param xa_notes El xA_NOTES a establecer.
	 */
	public void setXA_NOTES(String xa_notes) {
		XA_NOTES = xa_notes;
	}
	/**
	 * @return Devuelve xA_NUMBER_DECODERS.
	 */
	public List getXA_NUMBER_DECODERS() {
		return XA_NUMBER_DECODERS;
	}
	/**
	 * @param xa_number_decoders El xA_NUMBER_DECODERS a establecer.
	 */
	public void setXA_NUMBER_DECODERS(List xa_number_decoders) {
		XA_NUMBER_DECODERS = xa_number_decoders;
	}
	/**
	 * @return Devuelve xA_ORDER_ATIS.
	 */
	public String getXA_ORDER_ATIS() {
		return XA_ORDER_ATIS;
	}
	/**
	 * @param xa_order_atis El xA_ORDER_ATIS a establecer.
	 */
	public void setXA_ORDER_ATIS(String xa_order_atis) {
		XA_ORDER_ATIS = xa_order_atis;
	}
	/**
	 * @return Devuelve xA_ORDER_NUMBER.
	 */
	public String getXA_ORDER_NUMBER() {
		return XA_ORDER_NUMBER;
	}
	/**
	 * @param xa_order_number El xA_ORDER_NUMBER a establecer.
	 */
	public void setXA_ORDER_NUMBER(String xa_order_number) {
		XA_ORDER_NUMBER = xa_order_number;
	}
	/**
	 * @return Devuelve xA_POST_SALES_CODE.
	 */
	public String getXA_POST_SALES_CODE() {
		return XA_POST_SALES_CODE;
	}
	/**
	 * @param xa_post_sales_code El xA_POST_SALES_CODE a establecer.
	 */
	public void setXA_POST_SALES_CODE(String xa_post_sales_code) {
		XA_POST_SALES_CODE = xa_post_sales_code;
	}
	/**
	 * @return Devuelve xA_PROJECT_CODE.
	 */
	public String getXA_PROJECT_CODE() {
		return XA_PROJECT_CODE;
	}
	/**
	 * @param xa_project_code El xA_PROJECT_CODE a establecer.
	 */
	public void setXA_PROJECT_CODE(String xa_project_code) {
		XA_PROJECT_CODE = xa_project_code;
	}
	/**
	 * @return Devuelve xA_QUADRANT.
	 */
	public String getXA_QUADRANT() {
		return XA_QUADRANT;
	}
	/**
	 * @param xa_quadrant El xA_QUADRANT a establecer.
	 */
	public void setXA_QUADRANT(String xa_quadrant) {
		XA_QUADRANT = xa_quadrant;
	}
	/**
	 * @return Devuelve xA_REINJECTED.
	 */
	public String getXA_REINJECTED() {
		return XA_REINJECTED;
	}
	/**
	 * @param xa_reinjected El xA_REINJECTED a establecer.
	 */
	public void setXA_REINJECTED(String xa_reinjected) {
		XA_REINJECTED = xa_reinjected;
	}
	/**
	 * @return Devuelve xA_REITERATION.
	 */
	public String getXA_REITERATION() {
		return XA_REITERATION;
	}
	/**
	 * @param xa_reiteration El xA_REITERATION a establecer.
	 */
	public void setXA_REITERATION(String xa_reiteration) {
		XA_REITERATION = xa_reiteration;
	}
	/**
	 * @return Devuelve xA_REPAIR_INFO.
	 */
	public XAREPAIRINFO getXA_REPAIR_INFO() {
		return XA_REPAIR_INFO;
	}
	/**
	 * @param xa_repair_info El xA_REPAIR_INFO a establecer.
	 */
	public void setXA_REPAIR_INFO(XAREPAIRINFO xa_repair_info) {
		XA_REPAIR_INFO = xa_repair_info;
	}
	/**
	 * @return Devuelve xA_SOURCE_SYSTEM.
	 */
	public String getXA_SOURCE_SYSTEM() {
		return XA_SOURCE_SYSTEM;
	}
	/**
	 * @param xa_source_system El xA_SOURCE_SYSTEM a establecer.
	 */
	public void setXA_SOURCE_SYSTEM(String xa_source_system) {
		XA_SOURCE_SYSTEM = xa_source_system;
	}
	/**
	 * @return Devuelve xA_STATE_CODE.
	 */
	public String getXA_STATE_CODE() {
		return XA_STATE_CODE;
	}
	/**
	 * @param xa_state_code El xA_STATE_CODE a establecer.
	 */
	public void setXA_STATE_CODE(String xa_state_code) {
		XA_STATE_CODE = xa_state_code;
	}
	/**
	 * @return Devuelve xA_TELEPHONE_DATA.
	 */
	public XATELEPHONEDATA getXA_TELEPHONE_DATA() {
		return XA_TELEPHONE_DATA;
	}
	/**
	 * @param xa_telephone_data El xA_TELEPHONE_DATA a establecer.
	 */
	public void setXA_TELEPHONE_DATA(XATELEPHONEDATA xa_telephone_data) {
		XA_TELEPHONE_DATA = xa_telephone_data;
	}
	/**
	 * @return Devuelve xA_TV_DATA.
	 */
	public List getXA_TV_DATA() {
		return XA_TV_DATA;
	}
	/**
	 * @param xa_tv_data El xA_TV_DATA a establecer.
	 */
	public void setXA_TV_DATA(List xa_tv_data) {
		XA_TV_DATA = xa_tv_data;
	}
	/**
	 * @return Devuelve xA_WORK_TYPE.
	 */
	public String getXA_WORK_TYPE() {
		return XA_WORK_TYPE;
	}
	/**
	 * @param xa_work_type El xA_WORK_TYPE a establecer.
	 */
	public void setXA_WORK_TYPE(String xa_work_type) {
		XA_WORK_TYPE = xa_work_type;
	}
	/**
	 * @return Devuelve xA_WORK_ZONE_KEY.
	 */
	public String getXA_WORK_ZONE_KEY() {
		return XA_WORK_ZONE_KEY;
	}
	/**
	 * @param xa_work_zone_key El xA_WORK_ZONE_KEY a establecer.
	 */
	public void setXA_WORK_ZONE_KEY(String xa_work_zone_key) {
		XA_WORK_ZONE_KEY = xa_work_zone_key;
	}
	/**
	 * @return Devuelve xA_YOUNG_REPAIR.
	 */
	public String getXA_YOUNG_REPAIR() {
		return XA_YOUNG_REPAIR;
	}
	/**
	 * @param xa_young_repair El xA_YOUNG_REPAIR a establecer.
	 */
	public void setXA_YOUNG_REPAIR(String xa_young_repair) {
		XA_YOUNG_REPAIR = xa_young_repair;
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
