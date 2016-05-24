/*
 * Created on 11-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.ejb.RemoveException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.Agenda_scKey;
import co.com.telefonica.atiempo.ejb.eb.Agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Agenda_scLocalHome;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_tvKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_tvLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_tvLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.ErrorAux;
import co.com.telefonica.atiempo.interfaces.atiempo.TR017S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.actividades.factory.ActividadFactoryEJB;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.instalacion.TOADelegate;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVInterfaces;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ConfiguracionServiciosTVSevicio extends ServicioBasico {
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	
	protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;
	
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		TR017S tr017s = (TR017S) obj[0];
		boolean esAgendaSC = false;
		
		RecursosBAInterfaces recursosBA = new RecursosBADelegate();
		esAgendaSC = recursosBA.esAgendaSC(new Long(tr017s.getAtisRequestNumber()));
		
		
		try{
			RecursosTVInterfaces recursosTV = new RecursosTVDelegate();
			recursosTV.actualizaConfiguracionServiciosTV(tr017s, esAgendaSC);
		
			if (esAgendaSC){
				Mensaje_estado_tvLocalHome mensajeEstadoTVLocalHome = (Mensaje_estado_tvLocalHome) HomeFactory.getHome(Mensaje_estado_tvLocalHome.JNDI_NAME);
				Mensaje_estado_tvKey mensajeEstadoTVKey = new Mensaje_estado_tvKey(new Long(tr017s.getId()));
				Mensaje_estado_tvLocal mensajeEstadoTV = mensajeEstadoTVLocalHome.findByPrimaryKey(mensajeEstadoTVKey);
				
				if (mensajeEstadoTV.getDesc_error() != null && mensajeEstadoTV.getDesc_error().length() > 0){
					String cadenaAux = mensajeEstadoTV.getDesc_error();
					String valoresAux [] = cadenaAux.split("@");
					
					if (cadenaAux.indexOf("tr708") != -1 || cadenaAux.indexOf("801") != -1 ){
						
						if(cadenaAux.indexOf("801") != -1){
							TOADelegate toaDelegate = new TOADelegate();
							String tmpAux [] = valoresAux[0].split("-");
							Long idtmpAgenda = new Long (tmpAux[1]);
							Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome) HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
							Agenda_scKey agendaSCKey = new Agenda_scKey(valoresAux[0]);
							Agenda_scLocal agendaSCLocal =  agendaSCLocalHome.findByPrimaryKey(agendaSCKey);
							Tmp_agenda_scLocalHome tmpAgendaSChome = (Tmp_agenda_scLocalHome) HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
							
							//Varios Refresh - sfandino
							//dcardena solucion varias activaciones
							boolean noHayRegistro=false;
							try{
								
								Tmp_agenda_scLocal tmpAgendaLocal= tmpAgendaSChome.findID(idtmpAgenda);
								log.debug("Ya existe un registro con peti_num "+idtmpAgenda+" se procede a eliminarlo y crear uno nuevo");
								tmpAgendaLocal.remove();
								noHayRegistro=true;
							}
							catch (RemoveException e) {
								
								log.debug("Error al eliminar el registro de la tabla tmp_agenda_sc: "+e);
								e.printStackTrace();
							}catch (FinderException e) {
								noHayRegistro=true;
								log.debug("No existe registro en la base de datos... ");
								e.printStackTrace();
							}
							finally{
								if(noHayRegistro){
									Tmp_agenda_scLocal tmpAgendaSC = tmpAgendaSChome.create(idtmpAgenda);
									PeticionLocal petiLocal = mensajeEstadoTV.getPeticion();
									PeticionKey peticionKey = (PeticionKey) petiLocal.getPrimaryKey();
									tmpAgendaSC.setPeti_numero(peticionKey.peti_numero);
									tmpAgendaSC.setAgenda_sc(agendaSCLocal);
									tmpAgendaSC.setXml(XMLUtilities.marshall (tr017s));
									toaDelegate.enviaActivarDecosTarjetasTOA(valoresAux[0], valoresAux[1]);
								}
								else{
								log.debug("Ocurrio un error al intentar eliminar el registro ya existente con ID = "+idtmpAgenda+" de la tabla tmp_agenda_sc");	
								}
							}
							//fin arreglo varias activaciones
						}else{
							recursosBA.enviaActivarDecosTarjetasAgendaSC(valoresAux[0], valoresAux[1]);
						}
						
						for (Iterator erroresIterator = tr017s.getErrors().iterator(); erroresIterator.hasNext();){
							ErrorAux errorDecoTarjeta = (ErrorAux) erroresIterator.next();
							if (errorDecoTarjeta.getCode()!= 0 ){
								mensajeEstadoTV.setDesc_error("");
							}
						}
					}else if (cadenaAux.indexOf("711") != -1){
						boolean estaTodasPeticionesCerradas = true;
						Agenda_scLocalHome agendaSCLocalHome = (Agenda_scLocalHome) HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
						Agenda_scKey agendaSCKey = new Agenda_scKey(valoresAux[0]);
						Agenda_scLocal agendaSCLocal =  agendaSCLocalHome.findByPrimaryKey(agendaSCKey);
								
						BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
													
						Long idAplicacion=new Long(ApplicationConfig.getVariable(ApplicationConfig.APP_VPISTBBA_ID));
						BintegradaLocal bintegradaLocal=bintegradaLocalHome.findByVisiblePetApl(agendaSCLocal.getPeti_numero(),idAplicacion);
							
						String idCorrelativo=bintegradaLocal.getBi_url_detalle();
						int idInicio=idCorrelativo.indexOf("instanciaActividad");
						
						if(idInicio!=-1){
							idCorrelativo=idCorrelativo.substring(idInicio,idCorrelativo.length());
							int idFin=idCorrelativo.indexOf("&");
							if(idFin!=-1){
								idCorrelativo=idCorrelativo.substring(19,idFin);
							}
						}
							
						String codActividad = bintegradaLocal.getBi_url_detalle();
						idInicio = codActividad.indexOf("actividad");
							
						if(idInicio!=-1){
							codActividad=codActividad.substring(idInicio,codActividad.length());
							int idFin=codActividad.indexOf("&");
							if(idFin!=-1){
								codActividad=codActividad.substring(10,idFin);
								codActividad = codActividad.replace('+', ' ');
							}
						}
											
						agendaSCLocal.setEstado(new Integer(ComunInterfaces.ACTUACION_CERRADA));
						
						if (codActividad.equals(ComunInterfaces.ACT_CTRL_INSTALACION)){
							ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
							IActividadEJB actividadEJB = actividadFactoryEJB.getActividad(codActividad);
								
							ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(agendaSCLocal.getPeti_numero(), codActividad,idCorrelativo,null);
							
							if (valoresAux[3].equals("false")){
								log.debug("Se cierra la actuación y se deriva a PGC porque los modems que vienen en el cierre no coinciden con los instalados o no quedaron " +
									"bien configurados, por favor revise");
							
								actDto.setObservacion("Se cierra la actuación y se deriva a PGC porque los modems que vienen en el cierre no coinciden con los instalados o no quedaron " +
									"bien configurados, por favor revise");
							
								actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, VpistbbaConfig.getVariable("IDPGC"));
							}
							
							actividadEJB.terminar(actDto);
						}
					}
				}
			}
		}catch(NamingException ex){
			ex.printStackTrace();
		}catch(FinderException ex){
			ex.printStackTrace();
		}catch(TnProcesoExcepcion ex){
			log.debug("Error en el momento de cerrar la actividad para Agenda SC	 " + ex);
			ex.printStackTrace();
		} catch (CreateException ex) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error en el momento de cerrar la actividad para Agenda SC	 " + ex);
			ex.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		Object[] obj = new Object[1];
		TR017S tr017s = (TR017S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr017s;
		
		return obj;
	}
}
