/*
 * Creado el 30/07/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
package co.com.telefonica.atiempo.vpistbba.TOA;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.Mensaje_agenda_scLocal;
import co.com.telefonica.atiempo.interfaces.atiempo.base.ITRxxxBase;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.CommunicatorWSDelegate;
import co.com.telefonica.atiempo.vpistbba.servicios.Conexion_InterfazKey;
import co.com.telefonica.atiempo.vpistbba.servicios.Conexion_InterfazLocal;
import co.com.telefonica.atiempo.vpistbba.servicios.Conexion_InterfazLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
public class GestionServiciosSTBBA extends GestionServiciosVariado{
	private transient Logger log = LoggerFactory.getLogger (getClass()) ;

	public String servicioInBound(ITRxxxBase trXXXe, Long idAplicacion, Mensaje_agenda_scLocal mensaje_agenda_local, String xaTelephoneData, String producto, String error  ){
		try {
			Conexion_InterfazLocalHome conexionInterfazHome = (Conexion_InterfazLocalHome) HomeFactory.getHome(Conexion_InterfazLocalHome.JNDI_NAME);
			CommunicatorWSDelegate CommunicatorWSDelegate = new CommunicatorWSDelegate();
			String trXXXeClassName = trXXXe.getClass().getName().split("\\.")[trXXXe.getClass().getName().split("\\.").length-1]+"IN";
			Conexion_InterfazKey conexionInterfazKey  = new Conexion_InterfazKey(trXXXeClassName, "Reintento");
			Conexion_InterfazLocal conexionInterfazLocal = conexionInterfazHome.findByPrimaryKey(conexionInterfazKey);
			int reintento = Integer.parseInt(conexionInterfazLocal.getValor());
			String respuesta = null;
			String result_description = "";
			String result_code = "";
			String label =  "";
			if(producto.equals("BA")){
				result_description = this.buscarInformacion(ComunInterfaces.A_REFRESH_BA_RESULT_DESCRIPTION);
				result_code = this.buscarInformacion(ComunInterfaces.A_REFRESH_BA_RESULT_CODE);
				label = "XA_BROADBAND_DATA";
			}else if (producto.equals("STB")){
				result_description = this.buscarInformacion(ComunInterfaces.A_REFRESH_LB_RESULT_DESCRIPTION);
				result_code = this.buscarInformacion(ComunInterfaces.A_REFRESH_LB_RESULT_CODE);
				label = "XA_TELEPHONE_DATA";
			}
			while(mensaje_agenda_local.getReintentos().intValue() < reintento
					&& respuesta == null){
				String xml = "<"+trXXXeClassName+">"; 
				xml += obtenerInterfaz(trXXXeClassName, "inb:inbound_interface_request", idAplicacion);
//				xml += this.obtenerInterfaz(trXXXeClassName, "data", idAplicacion);
				xml += "</"+trXXXeClassName+">"; 
				
				//Cambio TOA dcardena
				//ya no se carga el codigo appt_numeber en el fiel,key ahora se quema la palabra appt_number 
				//String apptNumber = "<field>"+getValorMetodo(trXXXe, "getApptNumber")+"</field>";
				//xml = xml.replaceAll("</field>",apptNumber);
				//Fin Cambio
				
//				se agrega el tag appt_number con el valor del appt_number
				String apptNumber = "<appt_number>"+getValorMetodo(trXXXe, "getApptNumber")+"</appt_number>";
				xml = xml.replaceAll("</appt_number>",apptNumber);
				//fin 
				//se elimina <properties> </properties> ya que este aparece en el head y en el body dentro del arreglo padre appoinment solo se necesita en el body
				//por eso se elimina del head
				xml = xml.replaceFirst("<properties>","");
				xml = xml.replaceFirst("</properties>","");
				//el appt_number tambien aparece en el head y en el body dentro del appoinment se elimina del apoimment del head
				xml = xml.replaceFirst(apptNumber,"");
				//para el tag field se necesitan dos uno en el head con appt_number y otro en el body con invsn este replace solo cambia el primero por appt_number
				//y el segundo queda con el invs sin modificacion, esto por que el field esta dentro del padre keys y el keys se necesita en el body y en el head
				xml = xml.replaceFirst("<field>invsn</field>","<field>appt_number</field>");
				//se elimina este arreglo de keys y field junto con acttion complete del head no debe aparece y este esta dentro del apoinment que se usa en el body 
				//solo se necestan estos tag en el body
				xml = xml.replaceFirst("<keys><field>invsn</field></keys><action_if_completed>update</action_if_completed>","");
				
				if(error.equals("0")){
					String properties = "<properties>"+
											"<property>" +
												"<label>"+result_code+"</label>"+
												"<value>"+this.buscarInformacion(ComunInterfaces.DELIVERED)+"</value>"+
											"</property>"+
											"<property>" +
												"<label>"+result_description+"</label>"+
												"<value>"+this.buscarInformacion(ComunInterfaces.DELIVERED)+"</value>"+
											"</property>"+
											"<property>" +
												"<label>"+label+"</label>"+
												"<value>"+"<![CDATA["+xaTelephoneData+"]]>"+"</value>"+
											"</property>";
					
					xml = xml.replaceAll("<properties>",properties);
				}else{
					String properties = "<properties>"+
											"<property>" +
												"<label>"+result_code+"</label>"+
												"<value>"+this.buscarInformacion(ComunInterfaces.FAILED)+"</value>"+
											"</property>"+
											"<property>" +
												"<label>"+result_description+"</label>"+
												"<value>"+mensaje_agenda_local.getDesc_error()+"</value>"+
											"</property>"+
											"<property>" +
												"<label>"+label+"</label>"+
												"<value>"+"<![CDATA["+xaTelephoneData+"]]>"+"</value>"+
											"</property>";
					
					xml = xml.replaceAll("<properties>",properties);
				}
				
				respuesta = CommunicatorWSDelegate.enviarMensajeEstructurado(trXXXe,xml,trXXXeClassName );
				log.debug(respuesta);
				int reintentoAux = mensaje_agenda_local.getReintentos().intValue()+1;
				mensaje_agenda_local.setReintentos(new Long(reintentoAux));
			}
			return respuesta;
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado autom�ticamente
			log.debug("Se recibe error en la creaci�n de la respuesta: "+e);
			return null;
		} catch (NamingException e) {
			// TODO Bloque catch generado autom�ticamente
			log.debug("Se recibe error en la creaci�n de la respuesta: "+e);
			return null;
		} catch (FinderException e) {
			// TODO Bloque catch generado autom�ticamente
			log.debug("Se recibe error en la creaci�n de la respuesta: "+e);
			return null;
		}
		
	}

	public String servicioOutBound(ITRxxxBase trXXXe, Long idAplicacion, Mensaje_agenda_scLocal mensaje_agenda_local, String producto, String error ){
		try{
			Conexion_InterfazLocalHome conexionInterfazHome = (Conexion_InterfazLocalHome) HomeFactory.getHome(Conexion_InterfazLocalHome.JNDI_NAME);
			CommunicatorWSDelegate CommunicatorWSDelegate = new CommunicatorWSDelegate();
			String trXXXeClassName = trXXXe.getClass().getName().split("\\.")[trXXXe.getClass().getName().split("\\.").length-1]+"OUT";
			Conexion_InterfazKey conexionInterfazKey  = new Conexion_InterfazKey(trXXXeClassName, "Reintento");
			Conexion_InterfazLocal conexionInterfazLocal = conexionInterfazHome.findByPrimaryKey(conexionInterfazKey);
			int reintento = Integer.parseInt(conexionInterfazLocal.getValor());
			String respuesta = null;
			while(mensaje_agenda_local.getReintentos().intValue() < reintento
					&& respuesta == null){
				String xml = "<"+trXXXeClassName+">"; 
				xml += this.obtenerInterfaz(trXXXeClassName, "out:set_message_status", idAplicacion);
				xml += "</"+trXXXeClassName+">"; 
				log.debug(xml);
				String message_id = "<message_id>"+getValorMetodo(trXXXe, "getId")+"</message_id>";
				xml = xml.replaceAll("</message_id>",message_id);
				if(error.equals("0")){
					String status = "<status>"+this.buscarInformacion(ComunInterfaces.DELIVERED_OUT)+"</status>";
					xml = xml.replaceAll("</status>",status);
				}else{
					String status = "<status>"+this.buscarInformacion(ComunInterfaces.FAILED_OUT)+"</status>";
					xml = xml.replaceAll("</status>",status);
				}
				respuesta = CommunicatorWSDelegate.enviarMensajeEstructurado(trXXXe,xml,trXXXeClassName );
				log.debug(respuesta);
				int reintentoAux = mensaje_agenda_local.getReintentos().intValue()+1;
				mensaje_agenda_local.setReintentos(new Long(reintentoAux));
			}
			return respuesta;
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado autom�ticamente
			log.debug("Se recibe error en la creaci�n de la respuesta: "+e);
			return null;
		} catch (NamingException e) {
			// TODO Bloque catch generado autom�ticamente
			log.debug("Se recibe error en la creaci�n de la respuesta: "+e);
			return null;
		} catch (FinderException e) {
			// TODO Bloque catch generado autom�ticamente
			log.debug("Se recibe error en la creaci�n de la respuesta: "+e);
			return null;
		}
	}

}