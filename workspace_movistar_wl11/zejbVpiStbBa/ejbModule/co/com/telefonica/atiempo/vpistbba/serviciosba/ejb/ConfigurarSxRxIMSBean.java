/*
 * Creado el 25/11/2014
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.vpistbba.serviciosba.ejb;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.SessionContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.actividades.IActividadEJB;
import co.com.telefonica.atiempo.ejb.eb.ConectorKey;
import co.com.telefonica.atiempo.ejb.eb.ConectorLocalHome;
import co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocal;
import co.com.telefonica.atiempo.ejb.eb.ErrorLegadoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Familia_producto_servicioKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR606E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR606S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.actividades.factory.ActividadFactoryEJB;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;
import co.com.telefonica.atiempo.vpistbba.servicioba.ConfigurarSxRxIMSInterface;
import co.com.telefonica.atiempo.vpistbba.servicios.ConfigurarSxRXIMSMQ;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: ConfigurarSxRxIMS
 */
public class ConfigurarSxRxIMSBean extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter
implements ConfigurarSxRxIMSInterface {

	protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;
	private DBManager dbSeq ;
	private SimpleDateFormat df ;
	
	public void setSessionContext (SessionContext ctx)
	throws EJBException, RemoteException
	{
		super.setSessionContext (ctx) ;
		// Creacion de DataSource
		dbSeq = new DBManager ();
		dbSeq.setDataSource(DBManager.JDBC_VPISTBBA);
		
		// TODO: revisar este formato
		df = new SimpleDateFormat ("dd/MM/yyyy") ;
	}
	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.ConfigurarSxRxIMSInterface#configurarSxRxIMS()
	 */
	public void configurarSxRxIMS(ActividadEJBDTO act) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		log.debug("Entro al mètodo de Rx y Sx de IMS para la peticiòn; " + act.getNumeroPeticion());
		try {
			ConfigurarSxRXIMSMQ configurarSxRXIMSMQ = new ConfigurarSxRXIMSMQ();
			PeticionLocalHome petiHome = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
			PeticionKey peticionkey = new PeticionKey(act.getNumeroPeticion());
			PeticionLocal peticionLocal = petiHome.findByPrimaryKey(peticionkey);
			Collection psp = peticionLocal.getProducto_servicio_peticion();
			
			dbSeq = new DBManager ();
			dbSeq.setDataSource(DBManager.JDBC_VPISTBBA);
			df = new SimpleDateFormat ("dd/MM/yyyy") ;
			
			Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome)HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
			Mensaje_estadoLocalHome mensaje_estadoLocalHome =(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Mensaje_estadoLocal mesajeEspera=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoEspera)));
			ConectorLocalHome conectorLocalHome = (ConectorLocalHome)HomeFactory.getHome(ConectorLocalHome.JNDI_NAME);
			TR606E tr606e =new TR606E();
			Familia_producto_servicioKey fampsKey = null;

			Long IdCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
			for (Iterator productosIterator = psp.iterator(); productosIterator.hasNext(); ){
				Producto_servicio_peticionLocal productoLocal =  (Producto_servicio_peticionLocal)productosIterator.next();
				fampsKey = productoLocal.getFamiliaKey();
				if(peticionLocal.getNum_ide_nu_stb() != null)
					tr606e.setPhoneNumber(peticionLocal.getNum_ide_nu_stb());
				tr606e.setAction(productoLocal.getOperacion_comercial().toString());
				tr606e.setId(IdCorrelativo.toString());
			}
			
			Mensaje_estado_baLocal mensajeEstadoBALocal= mensajeEstadoBaLocalHome.create(IdCorrelativo);
			mensajeEstadoBALocal.setPeticion(peticionLocal);
			mensajeEstadoBALocal.setCod_familia_ps(new Integer(fampsKey.faps_id.intValue()));
			mensajeEstadoBALocal.setConector(conectorLocalHome.findByPrimaryKey(new ConectorKey(new Integer(codigoConectorDos))));
			mensajeEstadoBALocal.setFecha_envio(df.format (new java.util.Date ()));
			mensajeEstadoBALocal.setMensaje_estado(mesajeEspera);
			mensajeEstadoBALocal.setCod_actividad_generadora(act.getCodigoActividad());			
			configurarSxRXIMSMQ.enviarMensaje(tr606e);

		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error producido al instanciar el proceso: " + e);
		} catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error producido al instanciar el proceso: " + e);
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error producido al instanciar el proceso: " + e);
		}
		
 	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.ConfigurarSxRxIMSInterface#respuestaConfigurarSxRxIMS(co.com.telefonica.atiempo.interfaces.atiempo.TR606S)
	 */
	public void respuestaConfigurarSxRxIMS(TR606S tr606s) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		log.debug("Recibiendo mensaje "+tr606s.getId()+ " error code " 
				+ tr606s.getErrorCode()+ " mensaje "+ tr606s.getErrorMessage());
		
		log.debug("Entro al mètodo de respuesta de autoconfiguraciòn para la peticiòn; " + tr606s.getId());
		try{
			Mensaje_estado_baLocalHome mensajeEstadoBaLocalHome = (Mensaje_estado_baLocalHome)HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
			Mensaje_estadoLocalHome mensaje_estadoLocalHome =(Mensaje_estadoLocalHome) HomeFactory.getHome(Mensaje_estadoLocalHome.JNDI_NAME);
			Mensaje_estadoLocal mensajeok=mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoCierreOk)));
			Mensaje_estadoLocal mensajeError =mensaje_estadoLocalHome.findByPrimaryKey(new Mensaje_estadoKey(new Integer(ComunInterfaces.estadoCierreError)));
			Mensaje_estado_baLocal mensajeEstadoBaLocal = mensajeEstadoBaLocalHome.findByPrimaryKey(new Mensaje_estado_baKey(new Long(tr606s.getId())) );
			PeticionLocal peticionLocal = mensajeEstadoBaLocal.getPeticion();
			PeticionKey peticionKey = (PeticionKey) peticionLocal.getPrimaryKey();
			String nombreClase = TR135S.class.getName();
			nombreClase = com.telefonica_chile.atiempo.utiles.Utiles.getClassName(nombreClase);
			Mensaje_estadoLocal estadoPeticion = mensajeEstadoBaLocal.getMensaje_estado();
			Mensaje_estadoKey estadoPeticionKey = (Mensaje_estadoKey) estadoPeticion.getPrimaryKey();
			
			ActividadFactoryEJB actividadFactoryEJB = new ActividadFactoryEJB();
			IActividadEJB actividadEJB = actividadFactoryEJB.getActividad( mensajeEstadoBaLocal.getCod_actividad_generadora());
			ActividadEJBDTO actDto = actividadEJB.getActividadEJBDTO(peticionKey.peti_numero, mensajeEstadoBaLocal.getCod_actividad_generadora());
			
			if(estadoPeticionKey.cod_estado.intValue() == ComunInterfaces.estadoEspera || estadoPeticionKey.cod_estado.intValue() == ComunInterfaces.estadoError){
				if(tr606s.getErrorCode() != 0){
					ErrorLegadoLocalHome errorHome = (ErrorLegadoLocalHome) HomeFactory.getHome(ErrorLegadoLocalHome.JNDI_NAME); 
					ErrorLegadoLocal errorLegado = null;
					String plataforma = null;
					try{
						errorLegado = errorHome.findByErrorTr(""+tr606s.getErrorCode(), nombreClase);
					}catch (FinderException e) {
						// TODO Bloque catch generado automáticamente
						log.debug("No se encuentra registro en la Base de Datos se deriva a PGI");
						plataforma = VpistbbaConfig.getVariable("IDPGI");
						actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					}
					if(errorLegado != null){
						plataforma = errorLegado.getPlataforma();
						peticionLocal.setTipoErrorId(errorLegado.getIdTipoError());
					}
					actDto.setDato(DATOS_ATVPISTBBA.DIRECTOR_DE_FLUJOS.fluj_act_sig_forza, plataforma);
					RecursosBABean recursosbaDelegate = new  RecursosBABean();
					recursosbaDelegate.insertarCausalesCnaPeticion(mensajeEstadoBaLocal.getPeticion(), mensajeEstadoBaLocal.getCod_actividad_generadora(), new Long("701"), actDto.getIdActividadFlujo());
					mensajeEstadoBaLocal.setMensaje_estado(mensajeError);
				}else{
					mensajeEstadoBaLocal.setMensaje_estado(mensajeok);
				}
				mensajeEstadoBaLocal.setFecha_cierre(df.format (new java.util.Date ()));				
				mensajeEstadoBaLocal.setCod_actividad_generadora(mensajeEstadoBaLocal.getCod_actividad_generadora());
				mensajeEstadoBaLocal.setDesc_error(mensajeEstadoBaLocal.getDesc_error());
				actividadEJB.terminar(actDto);
			}else{
				log.debug("El estado de la peticiòn es diferente para ser procesado....");
			}
		}catch (FinderException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error producido al instanciar el proceso: " + e);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error producido al instanciar el proceso: " + e);
		} catch (TnProcesoExcepcion e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error producido al instanciar el proceso: " + e);
		} catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error producido al instanciar el proceso: " + e);
		} catch (ATiempoAppEx e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error producido al instanciar el proceso: " + e);
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.debug("Error producido al instanciar el proceso: " + e);
		}
	}

}
