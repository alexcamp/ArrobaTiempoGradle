/*
 * Created on 27-feb-07
 */
package co.com.telefonica.atiempo.soltec.servicios;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.interfaces.atiempo.TR005S;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator_ST;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.incidentes.IncidentesDelegate;
import co.com.telefonica.atiempo.soltec.incidentes.IncidentesInterfaces;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * @author TCS
 * 
 * Mensaje: TR_001_S
 */
public class RecepcionIncidenteATISServicio extends ServicioBasicoWF {

	private Logger log=LoggerFactory.getLogger(RecepcionIncidenteATISServicio.class);	
	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasicoWF#obtenerVariableWF()
	 */
	protected String obtenerVariableWF() {
		return "WF-INICIAR-VARIABLE";
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		TR005S incidenteAtis = (TR005S) obj[0];
		IncidentesInterfaces incidentesServices = new IncidentesDelegate();

		boolean cancelacion=false;
		boolean reiteracion=false;
		boolean reapertura=false;
		boolean terminada=false;
			
		boolean masiva=incidenteAtis.getMassiveBreakdownCode()>0;
		
		//Datos de Cancelacion
		String bcc=incidenteAtis.getBreakdownCancelationCode();
		char bsc=incidenteAtis.getBreakdownStatusCode();
		
		//Datos de Reiteracion
		char bri = incidenteAtis.getBreakdownReiterateIndicator();
		
		//Datos de Reapertura
		long boc = incidenteAtis.getBreakdownOriginalCode();
		
		cancelacion = ((bcc!=null  && bcc.length()>0) && (bsc == ComunInterfaces.codigoEstadoAveriaCancelada.charAt(0)));
		terminada = (bsc == ComunInterfaces.codigoEstadoAveriaTerminada.charAt(0));
		reiteracion = (bri == 'S');
		reapertura = (boc>0);
			
		long codavecd=-1;
		
		boolean modificarCategoria = incidentesServices.modificarCategoria(incidenteAtis);
		
		if (terminada || cancelacion || reiteracion || reapertura) {	
			codavecd=incidenteAtis.getBreakdownNumber();
			if (terminada){
				log.debug("Se Inyecta una Peticion de Termino");
				Long nroInc=incidentesServices.terminarIncidente(incidenteAtis,masiva);
				if (nroInc.longValue()>0){
					this.iniciarFlujo(nroInc);	
				}
			}else if (cancelacion) {
				log.debug("Se Inyecta una Peticion de Cancelacion");
				incidentesServices.cancelarIncidente(new Long(codavecd));
			} else if (reiteracion){
				log.debug("Se Inyecta una Peticion de Reiteracion");
				int resp = incidentesServices.reiterarIncidente(incidenteAtis,masiva);
				if(resp == ComunInterfaces.reiteracionGrabaNuevoIncidente){
					this.iniciarFlujo(new Long(incidenteAtis.getBreakdownNumber()));
				}
			}else if (reapertura) {
				log.debug("Se Inyecta una Peticion de Reapertura");
				Peticion_stLocal inciLocal=incidentesServices.salvarIncidenteATIS(incidenteAtis,masiva);
				Long nroInc= ((Peticion_stKey)inciLocal.getPrimaryKey()).cod_ave_cd;		
				this.iniciarFlujo(nroInc);	
			}
		}else{
//			CR17031 - adocarmo - 10/10/2008 - inicio
			//boolean modificarCategoria = incidentesServices.modificarCategoria(incidenteAtis);
			if (!modificarCategoria) {
				log.debug("Se Inyecta una Peticion Normal");
				Peticion_stLocal inciLocal=incidentesServices.salvarIncidenteATIS(incidenteAtis,masiva);
				Long nroInc= ((Peticion_stKey)inciLocal.getPrimaryKey()).cod_ave_cd;		
				this.iniciarFlujo(nroInc);	
			}
//			CR17031 - adocarmo - 10/10/2008 - fin			
		}
		

	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		TR005S tr005s = (TR005S) XMLUtilities.unmarshall(mensaje);
		// si el emulador está activo genero el id de la peticion de atis
		
		//if (Despachador.getEmuProps().getProperty("emulador.activado").equals("si")) {
		if (ServiceLocatorEmulator_ST.emuladorActivado()) {
			log.debug("Estoy viendo el emulador ACTIVOOOO!!!!");
		
			DBManager dbSeq = new DBManager ();
			dbSeq.setDataSource(DBManager.JDBC_SOLTEC); // verificar que es el datasource correcto
				
			long proximo = dbSeq.seqNextValLong("CORRELATIVO_MENSAJE");	
			tr005s.setId(String.valueOf(proximo));	
			//tr001s.setRequestNumber(proximo);
		}

		Object[] obj = new Object[1];
		obj[0] = tr005s;
		
		return obj;
	}

}
