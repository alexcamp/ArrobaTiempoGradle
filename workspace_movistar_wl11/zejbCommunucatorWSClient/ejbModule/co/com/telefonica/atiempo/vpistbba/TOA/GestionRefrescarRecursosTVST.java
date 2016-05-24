/*
 * Creado el 30/07/2015
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.TOA;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

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
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class GestionRefrescarRecursosTVST  extends GestionServiciosVariadoST{
	private transient Logger log = LoggerFactory.getLogger (getClass()) ;
	private int inboundReintentos;
	private int outboundReintentos;
	public String servicioInBound(ITRxxxBase trXXXe, Long idAplicacion , Long reintentos, String error, String des_error){
		try {
			Conexion_InterfazLocalHome conexionInterfazHome = (Conexion_InterfazLocalHome) HomeFactory.getHome(Conexion_InterfazLocalHome.JNDI_NAME);
			CommunicatorWSDelegate CommunicatorWSDelegate = new CommunicatorWSDelegate();
			String trXXXeClassName = trXXXe.getClass().getName().split("\\.")[trXXXe.getClass().getName().split("\\.").length-1]+"IN";
			Conexion_InterfazKey conexionInterfazKey  = new Conexion_InterfazKey(trXXXeClassName, "Reintento");
			Conexion_InterfazLocal conexionInterfazLocal = conexionInterfazHome.findByPrimaryKey(conexionInterfazKey);
			int reintento = Integer.parseInt(conexionInterfazLocal.getValor());
			String respuesta = null;
			while(reintentos.intValue() < reintento
					&& respuesta == null){
				String xml = "<"+trXXXeClassName+">"; 
				xml += obtenerInterfaz(trXXXeClassName, "inb:inbound_interface_request", idAplicacion);
//				xml += this.obtenerInterfaz(trXXXeClassName, "data", idAplicacion);
				xml += "</"+trXXXeClassName+">"; 
				
				//Cambio TOA dcardena
				//ya no se carga el codigo appt_numeber en el fiel,key ahora se quema la palabra appt_number 
				//String apptNumber = "<field>"+getValorMetodo(trXXXe, "getApptNumber")+"</field>";
				//xml = xml.replaceAll("</field>",apptNumber);
				//fin cambio
				
//				String error = getValorMetodo(trXXXe, "getError");
//				String des_error = getValorMetodo(trXXXe, "getDescripcionError");
				
				//se agrega el tag appt_number con el valor del appt_number
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
											"<property>"+
												"<label>"+this.buscarInformacion(ComunInterfaces.A_REFRESH_TV_RESULT_CODE)+"</label>"+
												"<value>"+this.buscarInformacion(ComunInterfaces.DELIVERED)+"</value>"+
											"</property>"+
											"<property>" +
												"<label>"+this.buscarInformacion(ComunInterfaces.A_REFRESH_TV_RESULT_DESCRIPTION)+"</label>"+
												"<value>"+this.buscarInformacion(ComunInterfaces.DELIVERED)+"</value>"+
											"</property>";
					
					xml = xml.replaceAll("<properties>",properties);
				}else{
					String properties = "<properties>"+
											"<property>"+
												"<label>"+this.buscarInformacion(ComunInterfaces.A_REFRESH_TV_RESULT_CODE)+"</label>"+
												"<value>"+this.buscarInformacion(ComunInterfaces.FAILED)+"</value>"+
											"</property>"+
											"<property>" +
												"<label>"+this.buscarInformacion(ComunInterfaces.A_REFRESH_TV_RESULT_DESCRIPTION)+"</label>"+
												"<value>"+des_error+"</value>"+
											"</property>";
					
					xml = xml.replaceAll("<properties>",properties);
				}
				respuesta = CommunicatorWSDelegate.enviarMensajeEstructurado(trXXXe,xml,trXXXeClassName );
				log.debug(respuesta);
				int reintentoAux = reintentos.intValue()+1;
				setInboundReintentos(reintentoAux);
			}
			return respuesta;
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se recibe error en la creaciòn de la respuesta: " + e);
			return null;
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se recibe error en la creaciòn de la respuesta: " + e);
			return null;
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se recibe error en la creaciòn de la respuesta: " + e);
			return null;
		}
		
	}
	
	/**
	 * @param trXXXe
	 * @return
	 *//*
	private String obtenerInterfaz(String trXXXeClassName, String arreglo, Long idAplicacion ) {
		// TODO Apéndice de método generado automáticamente
		try {
			String xml = "";
			
			Estructura_InterfazLocalHome estructuraHome = (Estructura_InterfazLocalHome) HomeFactory.getHome(Estructura_InterfazLocalHome.JNDI_NAME);
			
			xml += "<"+arreglo+">";
			Collection estructuraArray = estructuraHome.findByArray(trXXXeClassName, idAplicacion,arreglo  );
			for(Iterator iter = estructuraArray.iterator();iter.hasNext();){
				Estructura_InterfazLocal estructuraLocal = (Estructura_InterfazLocal)iter.next();
				if(estructuraLocal.getAci_nullable().intValue() == 1){
					Estructura_InterfazKey estructuraKey = (Estructura_InterfazKey)estructuraLocal.getPrimaryKey();
					if(estructuraLocal.getAci_tipo().equalsIgnoreCase("complexType")){
						xml += this.obtenerInterfaz(trXXXeClassName,estructuraKey.aci_tag,idAplicacion);
					}else{
						if(estructuraLocal.getAci_default_value() == null)
							xml += "</"+estructuraKey.aci_tag+">";
						else{
							xml += "<"+estructuraKey.aci_tag+">"+estructuraLocal.getAci_default_value()+"</"+estructuraKey.aci_tag+">";
						}
					}
				}
			}
			xml += "</"+arreglo+">";
			return xml;
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se presenta error al instanciar el metodo: "+ e);
			System.out.println("Se presenta error al instanciar el metodo: "+ e);
			return "";
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se presenta error al instanciar el metodo: "+ e);
			System.out.println("Se presenta error al instanciar el metodo: "+ e);
			return "";
		}
	}
*/
	/**
	 * @param trXXXe
	 * @return
	 *//*
	private String getValorMetodo(ITRxxxBase trXXXe, String metodo) {
		// TODO Apéndice de método generado automáticamente
		try {
			Method getMetodo = trXXXe.getClass().getMethod(metodo, new Class[] {});
			String result = (String) getMetodo.invoke(trXXXe, new Object[] {});
//			System.out.println("Mensaje Resultante= "+apptNumber);
			return result;
		} catch (SecurityException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se presenta error al instanciar el metodo: "+ e);
			return null;
		} catch (NoSuchMethodException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se presenta error al instanciar el metodo: "+ e);
			return null;
		} catch (IllegalArgumentException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se presenta error al instanciar el metodo: "+ e);
			return null;
		} catch (IllegalAccessException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se presenta error al instanciar el metodo: "+ e);
			return null;
		} catch (InvocationTargetException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se presenta error al instanciar el metodo: "+ e);
			return null;
		}
	}
*/
	public String servicioOutBound(ITRxxxBase trXXXe, Long idAplicacion, Long reintentos, String error ){
		try{
			Conexion_InterfazLocalHome conexionInterfazHome = (Conexion_InterfazLocalHome) HomeFactory.getHome(Conexion_InterfazLocalHome.JNDI_NAME);
			CommunicatorWSDelegate CommunicatorWSDelegate = new CommunicatorWSDelegate();
			String trXXXeClassName = trXXXe.getClass().getName().split("\\.")[trXXXe.getClass().getName().split("\\.").length-1]+"OUT";
			Conexion_InterfazKey conexionInterfazKey  = new Conexion_InterfazKey(trXXXeClassName, "Reintento");
			Conexion_InterfazLocal conexionInterfazLocal = conexionInterfazHome.findByPrimaryKey(conexionInterfazKey);
			int reintento = Integer.parseInt(conexionInterfazLocal.getValor());
			String respuesta = null;
			while(reintentos.intValue() < reintento
					&& respuesta == null){
				String xml = "<"+trXXXeClassName+">"; 
				xml += this.obtenerInterfaz(trXXXeClassName, "out:set_message_status", idAplicacion);
				xml += "</"+trXXXeClassName+">"; 
				log.debug(xml);
				String message_id = "<message_id>"+getValorMetodo(trXXXe, "getId")+"</message_id>";
				xml = xml.replaceAll("</message_id>",message_id);
//				String error = getValorMetodo(trXXXe, "getError");
				if(error.equals("0")){
					String status = "<status>"+this.buscarInformacion(ComunInterfaces.DELIVERED_OUT)+"</status>";
					xml = xml.replaceAll("</status>",status);
				}else{
					String status = "<status>"+this.buscarInformacion(ComunInterfaces.FAILED_OUT)+"</status>";
					xml = xml.replaceAll("</status>",status);
				}
				respuesta = CommunicatorWSDelegate.enviarMensajeEstructurado(trXXXe,xml,trXXXeClassName );
				log.debug(respuesta);
				int reintentoAux = reintentos.intValue()+1;
				setOutboundReintentos(reintentoAux);
			}
			return respuesta;
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se recibe error en la creaciòn de la respuesta: "+e);
			return null;
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se recibe error en la creaciòn de la respuesta: "+e);
			return null;
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Se recibe error en la creaciòn de la respuesta: "+e);
			return null;
		}
	}
	/*
	private String buscarInformacion( String dato) {
		try {
			// TODO Apéndice de método generado automáticamente
			Properties_BDLocalHome propertiesBDHome = (Properties_BDLocalHome) HomeFactory.getHome(Properties_BDLocalHome.JNDI_NAME);
			Properties_BDLocal propertiesBDLocal = propertiesBDHome.findByPrimaryKey(dato);
			return propertiesBDLocal.getValor();
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("no se encontrò datos para: "+dato);
			return null;
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("no se encontrò datos para: "+dato);
			return null;
		}
		
	}
*/
	/**
	 * @return Devuelve inboundReintentos.
	 */
	public int getInboundReintentos() {
		return inboundReintentos;
	}
	/**
	 * @param inboundReintentos El inboundReintentos a establecer.
	 */
	public void setInboundReintentos(int inboundReintentos) {
		this.inboundReintentos = inboundReintentos;
	}
	/**
	 * @return Devuelve outboundReintentos.
	 */
	public int getOutboundReintentos() {
		return outboundReintentos;
	}
	/**
	 * @param outboundReintentos El outboundReintentos a establecer.
	 */
	public void setOutboundReintentos(int outboundReintentos) {
		this.outboundReintentos = outboundReintentos;
	}
}