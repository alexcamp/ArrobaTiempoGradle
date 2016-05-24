/*
 * Creado el 28/05/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.servicios.web.comun.test;

import co.com.telefonica.atiempo.vpistbba.servicios.web.comun.CommunicatorWS;
import co.com.telefonica.atiempo.vpistbba.servicios.web.comun.CommunicatorWSBasic;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class CommunucatorWSBasicTest {
	
	

	public static void main(String[] args) {
		
		String xmlInput1 =  "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:man=\"http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/\">" +
							"<soapenv:Header/>" +
							"<soapenv:Body>" +
							"<man:CreacionServicio>" +
							"<XA_FAMILIA>XA_FAMILIA</XA_FAMILIA>" +
							"<XA_SOURCE_SYSTEM>XA_SOURCE_SYSTEM</XA_SOURCE_SYSTEM>" +
							"<cname>cname</cname>" +
							"<XA_CUSTOMER_ID_TYPE>XA_CUSTOMER_ID_TYPE</XA_CUSTOMER_ID_TYPE>" +
							"<customer_number>customer_number</customer_number>" +
							"<XA_CUSTOMER_SEGMENT>XA_CUSTOMER_SEGMENT</XA_CUSTOMER_SEGMENT>" +
							"<XA_CUSTOMER_SUBSEGMENT>XA_CUSTOMER_SUBSEGMENT</XA_CUSTOMER_SUBSEGMENT>" +
							"<XA_DAT_AGDMTO_1>XA_DAT_AGDMTO_1</XA_DAT_AGDMTO_1>" +
							"<XA_DAT_AGDMTO_2>XA_DAT_AGDMTO_2</XA_DAT_AGDMTO_2>" +
							"<XA_CUSTOMER_VIP>XA_CUSTOMER_VIP</XA_CUSTOMER_VIP>" +
							"<XA_CONTACT_NAME>XA_CONTACT_NAME</XA_CONTACT_NAME>" +
							"<address>address</address>" +
							"<XA_CITY_CODE>XA_CITY_CODE</XA_CITY_CODE>" +
							"<city>city</city>" +
							"<XA_STATE_CODE>XA_STATE_CODE</XA_STATE_CODE>" +
							"<state>state</state>" +
							"<XA_NEIGHBORHOOD>XA_NEIGHBORHOOD</XA_NEIGHBORHOOD>" +
							"<acoord_x>10.2</acoord_x>" +
							"<acoord_y>10.1</acoord_y>" +
							"<XA_QUADRANT>XA_QUADRANT</XA_QUADRANT>" +
							"<XA_WORK_ZONE_KEY>XA_WORK_ZONE_KEY</XA_WORK_ZONE_KEY>" +
							"<aworktype_label>aworktype_label</aworktype_label>" +
							"<XA_WORK_TYPE>XA_WORK_TYPE</XA_WORK_TYPE>" +
							"<id_actuacion>id_actuacion</id_actuacion>" +
							"<XA_ORDER_ATIS>XA_ORDER_ATIS</XA_ORDER_ATIS>" +
							"<XA_ORDER_NUMBER>XA_ORDER_NUMBER</XA_ORDER_NUMBER>" +
							"<XA_CREATION_DATE>XA_CREATION_DATE</XA_CREATION_DATE>" +
							"<date>date</date><!--1 or more repetitions:-->" +
							"<XA_NUMBER_DECODERS>XA_NUMBER_DECODERS</XA_NUMBER_DECODERS>" +
							"</man:CreacionServicio>" +
							"</soapenv:Body>" +
							"</soapenv:Envelope>";
		
		String xmlInput2 =  "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:man=\"http://www.telefonica.com/ManageWorkOrderLifecydeScheduler_PS/\">" +
							"<soapenv:Header/>" +
						  "<soapenv:Body>" +
						     "<man:CreacionServicio>" +
						        "<XA_FAMILIA>XA_FAMILIA</XA_FAMILIA>" +
						        "<XA_SOURCE_SYSTEM>XA_SOURCE_SYSTEM</XA_SOURCE_SYSTEM>" +
						        "<cname>cname</cname>" +
						        "<XA_CUSTOMER_ID_TYPE>XA_CUSTOMER_ID_TYPE</XA_CUSTOMER_ID_TYPE>" +
						        "<customer_number>customer_number</customer_number>" +
						        "<XA_CUSTOMER_SEGMENT>XA_CUSTOMER_SEGMENT</XA_CUSTOMER_SEGMENT>" +
						        "<XA_CUSTOMER_SUBSEGMENT>XA_CUSTOMER_SUBSEGMENT</XA_CUSTOMER_SUBSEGMENT>" +
						        "<XA_DAT_AGDMTO_1>XA_DAT_AGDMTO_1</XA_DAT_AGDMTO_1>" +
						        "<XA_DAT_AGDMTO_2>XA_DAT_AGDMTO_2</XA_DAT_AGDMTO_2>" +
						        "<XA_CUSTOMER_VIP>XA_CUSTOMER_VIP</XA_CUSTOMER_VIP>" +
						        "<XA_DAT_OP_CLIENT>" +
						           "<Propiedad>" +
						              "<Key>Key</Key>" +
						              "<Value>Value</Value>" +
						           "</Propiedad>" +
						        "</XA_DAT_OP_CLIENT>" +
						        "<XA_CONTACT_NAME>XA_CONTACT_NAME</XA_CONTACT_NAME>" +
						        "<cell>cell</cell>" +
						        "<XA_CONTACT_PHONE_NUMBER_2>XA_CONTACT_PHONE_NUMBER_2</XA_CONTACT_PHONE_NUMBER_2>" +
						        "<XA_CONTACT_PHONE_NUMBER_3>XA_CONTACT_PHONE_NUMBER_3</XA_CONTACT_PHONE_NUMBER_3>" +
						        "<XA_CONTACT_PHONE_NUMBER_4>XA_CONTACT_PHONE_NUMBER_4</XA_CONTACT_PHONE_NUMBER_4>" +
						        "<XA_CONTACT_PHONE_NUMBER_5>XA_CONTACT_PHONE_NUMBER_5</XA_CONTACT_PHONE_NUMBER_5>" +
						        "<XA_CONTACT_PHONE_NUMBER_6>XA_CONTACT_PHONE_NUMBER_6</XA_CONTACT_PHONE_NUMBER_6>" +
						        "<email>email</email>" +
						        "<phone>phone</phone>" +
						        "<XA_DAT_OP_CONTACT>" +
						           "<Propiedad>" +
						              "<Key>Key</Key>" +
						              "<Value>Value</Value>" +
						           "</Propiedad>" +
						        "</XA_DAT_OP_CONTACT>" +
						        "<address>address</address>" +
						        "<XA_CITY_CODE>XA_CITY_CODE</XA_CITY_CODE>" +
						        "<city>city</city>" +
						        "<XA_STATE_CODE>XA_STATE_CODE</XA_STATE_CODE>" +
						        "<state>state</state>" +
						        "<XA_NEIGHBORHOOD>XA_NEIGHBORHOOD</XA_NEIGHBORHOOD>" +
						        "<acoord_x>2.1</acoord_x>" +
						        "<acoord_y>3.2</acoord_y>" +
						        "<XA_QUADRANT>XA_QUADRANT</XA_QUADRANT>" +
						        "<XA_WORK_ZONE_KEY>XA_WORK_ZONE_KEY</XA_WORK_ZONE_KEY>" +
						        "<aworktype_label>aworktype_label</aworktype_label>" +
						        "<XA_WORK_TYPE>XA_WORK_TYPE</XA_WORK_TYPE>" +
						        "<XA_DESCRIPTION>XA_DESCRIPTION</XA_DESCRIPTION>" +
						        "<XA_DAT_OP_TPACTIVIDAD>" +
						           "<Propiedad>" +
						              "<Key>Key</Key>" +
						              "<Value>Value</Value>" +
						           "</Propiedad>" +
						        "</XA_DAT_OP_TPACTIVIDAD>" +
						        "<id_actuacion>id_actuacion</id_actuacion>" +
						        "<XA_ORDER_ATIS>XA_ORDER_ATIS</XA_ORDER_ATIS>" +
						        "<XA_ORDER_NUMBER>XA_ORDER_NUMBER</XA_ORDER_NUMBER>" +
						        "<XA_CREATION_DATE>XA_CREATION_DATE</XA_CREATION_DATE>" +
						        "<date>date</date>" +
						        "<XA_DAT_OP_DATO_CITA>" +
						           "<Propiedad>" +
						              "<Key>Key</Key>" +
						              "<Value>Value</Value>" +
						           "</Propiedad>" +
						        "</XA_DAT_OP_DATO_CITA>" +
						        "<XA_PROJECT_CODE>XA_PROJECT_CODE</XA_PROJECT_CODE>" +
						        "<XA_REINJECTED>XA_REINJECTED</XA_REINJECTED>" +
						        "<XA_DAT_OP_DATO_INST>" +
						           "<Propiedad>" +
						              "<Key>Key</Key>" +
						              "<Value>Value</Value>" +
						           "</Propiedad>" +
						        "</XA_DAT_OP_DATO_INST>" +
						        "<XA_MASSIVE_TYPE>XA_MASSIVE_TYPE</XA_MASSIVE_TYPE>" +
						        "<XA_REITERATION>XA_REITERATION</XA_REITERATION>" +
						        "<XA_YOUNG_REPAIR>XA_YOUNG_REPAIR</XA_YOUNG_REPAIR>" +
						        "<XA_DAT_OP_DATO_AVER>" +
						           "<Propiedad>" +
						              "<Key>Key</Key>" +
						              "<Value>Value</Value>" +
						           "</Propiedad>" +
						        "</XA_DAT_OP_DATO_AVER>" +
						        "<XA_POST_SALES_CODE>XA_POST_SALES_CODE</XA_POST_SALES_CODE>" +
						        "<XA_REPAIR_INFO>" +
						           "<Codigo_Apertura>Codigo_Apertura</Codigo_Apertura>" +
						           "<Observaciones_Apertura>Observaciones_Apertura</Observaciones_Apertura>" +
						           "<Diagnostico_Primer_Nivel>Diagnostico_Primer_Nivel</Diagnostico_Primer_Nivel>" +
						           "<Diagnostico_Segundo_Nivel>Diagnostico_Segundo_Nivel</Diagnostico_Segundo_Nivel>" +
						           "<Observaciones_Diagnostico_Segundo_Nivel>Observaciones_Diagnostico_Segundo_Nivel</Observaciones_Diagnostico_Segundo_Nivel>" +
						           //"<Categoría_Avería>Categoría_Avería</Categoría_Avería>" +
						           "<Codigo_Prioridad>Codigo_Prioridad</Codigo_Prioridad>" +
						           "<Otros_Repair>" +
						              "<Propiedad>" +
						                 "<Key>Key</Key>" +
						                 "<Value>Value</Value>" +
						              "</Propiedad>" +
						           "</Otros_Repair>" +
						        "</XA_REPAIR_INFO>" +
						        "<XA_CENTRAL>XA_CENTRAL</XA_CENTRAL>" +
						        "<XA_BOX_TYPE>XA_BOX_TYPE</XA_BOX_TYPE>" +
						        "<XA_TELEPHONE_DATA>" +
						           "<Central>Central</Central>" +
						           "<Telefono>Telefono</Telefono>" +
						           "<Len>Len</Len>" +
						           "<Posicion_Horizontal>Posicion_Horizontal</Posicion_Horizontal>" +
						           "<Distribuidor>Distribuidor</Distribuidor>" +
						           "<Descripcion_Distribuidor>Descripcion_Distribuidor</Descripcion_Distribuidor>" +
						           "<Direccion_Distribuidor>Direccion_Distribuidor</Direccion_Distribuidor>" +
						           "<Liston>Liston</Liston>" +
						           "<Par_liston>Par_liston</Par_liston>" +
						           "<Cable>Cable</Cable>" +
						           "<Par_Cable>Par_Cable</Par_Cable>" +
						           "<Armario>Armario</Armario>" +
						           "<Direccion_Armario>Direccion_Armario</Direccion_Armario>" +
						           "<Caja>Caja</Caja>" +
						           "<Par_caja>Par_caja</Par_caja>" +
						           "<Direccion_caja>Direccion_caja</Direccion_caja>" +
						           "<Tipo_caja>Tipo_caja</Tipo_caja>" +
						           "<Distancia_caja>Distancia_caja</Distancia_caja>" +
						           "<Latitud>Latitud</Latitud>" +
						           "<Longitud>Longitud</Longitud>" +
						           "<Zona_cobertura>Zona_cobertura</Zona_cobertura>" +
						           "<Otros_Telephon_Data>" +
						              "<Propiedad>" +
						                 "<Key>Key</Key>" +
						                 "<Value>Value</Value>" +
						              "</Propiedad>" +
						           "</Otros_Telephon_Data>" +
						        "</XA_TELEPHONE_DATA>" +
						        "<XA_BROADBAND_DATA>" +
						           "<Velocidad>Velocidad</Velocidad>" +
						           "<Tipo_IP>Tipo_IP</Tipo_IP>" +
						           "<Puerto>Puerto</Puerto>" +
						           "<POTs>POTs</POTs>" +
						           "<ADSL>ADSL</ADSL>" +
						           "<Direccion_IP_DISLAM>Direccion_IP_DISLAM</Direccion_IP_DISLAM>" +
						           "<Direccion_IP_WAN>Direccion_IP_WAN</Direccion_IP_WAN>" +
						           "<Direccion_IP_LAN>Direccion_IP_LAN</Direccion_IP_LAN>" +
						           "<Mascara_LAN>Mascara_LAN</Mascara_LAN>" +
						           "<Frame>Frame</Frame>" +
						           "<Tarjeta_Slot>Tarjeta_Slot</Tarjeta_Slot>" +
						           "<RACK>RACK</RACK>" +
						           "<SUBRACK>SUBRACK</SUBRACK>" +
						           "<VPI_VCI_Cliente>VPI_VCI_Cliente</VPI_VCI_Cliente>" +
						           "<VPI_VCI_Red>VPI_VCI_Red</VPI_VCI_Red>" +
						           "<Usuario_Acceso>Usuario_Acceso</Usuario_Acceso>" +
						           "<Otros_Datos_BA>" +
						              "<Propiedad>" +
						                 "<Key>Key</Key>" +
						                 "<Value>Value</Value>" +
						              "</Propiedad>" +
						           "</Otros_Datos_BA>" +
						        "</XA_BROADBAND_DATA>" +
						        "<!--1 or more repetitions:-->" +
						        "<XA_NUMBER_DECODERS>XA_NUMBER_DECODERS</XA_NUMBER_DECODERS>" +
						        "<XA_ID_PCTV>XA_NUMBER_DECODERS</XA_ID_PCTV>" +
						        "<XA_TV_DATA>" +
						           "<Tematicos>Tematicos</Tematicos>" +
						           "<Tipo_Decos>Tipo_Decos</Tipo_Decos>" +
						           "<Cantidad_Decos>Cantidad_Decos</Cantidad_Decos>" +
						           "<Otros_Datos_TV>" +
						              "<Propiedad>" +
						                 "<Key>Key</Key>" +
						                 "<Value>Value</Value>" +
						              "</Propiedad>" +
						           "</Otros_Datos_TV>" +
						        "</XA_TV_DATA>" +
						        "<XA_NOTES>XA_NOTES</XA_NOTES>" +
						        "<XA_EQUIPMENT>" +
						           "<Tipo_Equipo>Tipo_Equipo</Tipo_Equipo>" +
						           "<Numero>Numero</Numero>" +
						           "<Accion>Accion</Accion>" +
						           "<Familia>Familia</Familia>" +
						           "<Otros_Equipos>" +
						              "<Propiedad>" +
						                "<Key>Key</Key>" +
						                 "<Value>Value</Value>" +
						              "</Propiedad>" +
						           "</Otros_Equipos>" +
						        "</XA_EQUIPMENT>" +
						        "<EQUIPOS_CLIENTE>" +
						           "<inv_type>inv_type</inv_type>" +
						           "<invsn>invsn</invsn>" +
						           "<XI_BRAND>XI_BRAND</XI_BRAND>" +
						           "<XI_MODEL>XI_MODEL</XI_MODEL>" +
						           "<XI_SAP_DOCUMENT_NUMBER>XI_SAP_DOCUMENT_NUMBER</XI_SAP_DOCUMENT_NUMBER>" +
						           "<XI_SAP_DOCUMENT_YEAR>XI_SAP_DOCUMENT_YEAR</XI_SAP_DOCUMENT_YEAR>" +
						           "<XI_SAP_CODE>XI_SAP_CODE</XI_SAP_CODE>" +
						           "<XI_SAP_CODE_DESCRIPTION>XI_SAP_CODE_DESCRIPTION</XI_SAP_CODE_DESCRIPTION>" +
						           "<XI_SAP_CODE_TJ>XI_SAP_CODE_TJ</XI_SAP_CODE_TJ>" +
						           "<XI_CARD_SERIAL_NUMBER>XI_CARD_SERIAL_NUMBER</XI_CARD_SERIAL_NUMBER>" +
						           "<XI_CAS_ID>XI_CAS_ID</XI_CAS_ID>" +
						           "<XI_BULK_SAP>XI_BULK_SAP</XI_BULK_SAP>" +
						           "<XI_SAP_DISTRIBUTION_CENTER>XI_SAP_DISTRIBUTION_CENTER</XI_SAP_DISTRIBUTION_CENTER>" +
						           "<XI_SAP_MOVIMIENTO>XI_SAP_MOVIMIENTO</XI_SAP_MOVIMIENTO>" +
						           "<XI_SAP_BOD_CTISTA>XI_SAP_BOD_CTISTA</XI_SAP_BOD_CTISTA>" +
						           "<XI_SAP_PEP>XI_SAP_PEP</XI_SAP_PEP>" +
						           "<XI_SAP>XI_SAP</XI_SAP>" +
						           "<XI_SAP_OTROS>" +
						              "<Propiedad>" +
						                 "<Key>Key</Key>" +
						                 "<Value>Value</Value>" +
						              "</Propiedad>" +
						           "</XI_SAP_OTROS>" +
						           "<I_ACTIVATION_NUMBER>I_ACTIVATION_NUMBER</I_ACTIVATION_NUMBER>" +
						           "<XI_MATERIAL_CODE>XI_MATERIAL_CODE</XI_MATERIAL_CODE>" +
						        "</EQUIPOS_CLIENTE>" +
						        "<OTHER>" +
						           "<Propiedad>" +
						              "<Key>Key</Key>" +
						              "<Value>Value</Value>" +
						           "</Propiedad>" +
						        "</OTHER>" +
						     "</man:CreacionServicio>" +
						  "</soapenv:Body>" +
						"</soapenv:Envelope>";
		
		String endpoint = "http://BOG-PF04QJNM:8088/mockCreacionServicioSOAP?wsdl";
		CommunicatorWSBasic comWS = new CommunicatorWSBasic();
		
		String arreglo = comWS.sendMessageByTRxxxE(endpoint, xmlInput1, 20000);
		
		System.out.println("Res " + arreglo);
	}
}
