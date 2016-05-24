/*
 * Created on 30-03-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.serviciosba;

import java.util.ArrayList;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR007S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR014S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR022S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR035S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR043S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR135S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR708S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR711S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR717S;
import co.com.telefonica.atiempo.soltec.dto.ModemSTDTO;
import co.com.telefonica.atiempo.soltec.dto.RecursosBADTO;
import co.com.telefonica.atiempo.soltec.mensajeria.ba.ejb.sb.RecursosBALocal;
import co.com.telefonica.atiempo.soltec.mensajeria.ba.ejb.sb.RecursosBALocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RecursosBADelegate implements  RecursosBAInterfaces{


	private RecursosBALocal servicios;
	private RecursosBALocalHome recursosBALocalHome;

	public RecursosBADelegate() throws ATiempoAppEx {
		try {
			
			recursosBALocalHome = (RecursosBALocalHome) HomeFactory.getHome(RecursosBALocalHome.JNDI_NAME);
			servicios = recursosBALocalHome.create();
			
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#enviarConfiguracionActualBA(java.lang.String, java.lang.String)
	 */
	public void enviarConfiguracionActualBA(String peticion, String id_actividad) throws ATiempoAppEx {
		// TODO Auto-generated method stub
		
		servicios.enviarConfiguracionActualBA(peticion, id_actividad);
		
	}


	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces#recepcionConfiguracionActualBA(co.com.telefonica.atiempo.interfaces.atiempo.TR014S)
	 */
	public void recepcionConfiguracionActualBA(TR014S tr014s) throws ATiempoAppEx {
		// TODO Auto-generated method stub
		servicios.recepcionConfiguracionActualBA(tr014s);
	}

	public void enviarActualizacionInventarioBA(ActividadEJBDTO act, String peticion, String id_actividad) throws ATiempoAppEx {
		servicios.enviarActualizacionInventarioBA(act, peticion, id_actividad);
	}

	public void recepcionActualizacionInventarioBA(TR007S tr007s) throws ATiempoAppEx {
		servicios.recepcionActualizacionInventarioBA(tr007s);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#getModemSt(java.lang.Long)
	 */
	public ArrayList getModemSt(Long peticion) throws ATiempoAppEx {
		
		return servicios.getModemSt(peticion);
		
	}
	//TCS-gquevedo May 18, 2009 CR.23338 INICIO
	public String getTipoModemSt(Long peticion) throws ATiempoAppEx {
		
		return servicios.getTipoModemSt(peticion);
		
	}
	//TCS-gquevedo May 18, 2009 CR.23338 FIN
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#grabaModemsBaST(java.lang.Long, java.lang.Long, java.util.ArrayList)
	 */
	public void grabaModemsBaST(Long nroPeticion, Long telAsignado, ArrayList modems) throws ATiempoAppEx{
		// TODO Auto-generated method stub
		
		servicios.grabaModemsBaST(nroPeticion, telAsignado, modems);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#obtenerRecursosBA(java.lang.Long)
	 */
	public RecursosBADTO obtenerRecursosBA(Long codAveOri) throws ATiempoAppEx {
		return servicios.obtenerRecursosBA(codAveOri);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#enviaModemPorUtilizar(long, java.lang.String)
	 */
	public Long enviaModemPorUtilizar(long codAveCd, String modemConsulta, long idContratista) throws ATiempoAppEx
	{
		return servicios.enviaModemPorUtilizar(codAveCd,modemConsulta, idContratista);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#actualizaModemPorUtilizar(co.com.telefonica.atiempo.interfaces.atiempo.TR022S)
	 */
	public void actualizaModemPorUtilizar(TR022S tr022s) throws ATiempoAppEx
	{
		servicios.actualizaModemPorUtilizar(tr022s);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#buscarRespuestaMensajeModem(java.lang.Long, java.lang.Long)
	 */
	public TR022S buscarRespuestaMensajeModem(Long codAveCd, Long idMensaje) throws ATiempoAppEx
	{
		return servicios.buscarRespuestaMensajeModem(codAveCd,idMensaje);
	}
	
	// Inicio SIGRES
	public void enviarConfiguracionActualBASigres(String peticion, String id_actividad) throws ATiempoAppEx{
		servicios.enviarConfiguracionActualBASigres(peticion,id_actividad);
	}
	
	public void recepcionConfiguracionActualBASigres(TR035S tr035s) throws ATiempoAppEx{
		servicios.recepcionConfiguracionActualBASigres(tr035s);
	}
	
	//fin SIGRES
	
	public void recepcionCuentaCorreoBA(TR043S tr043s) throws ATiempoAppEx{
		servicios.recepcionCuentaCorreoBA(tr043s);
	}
	
	public void enviarCuentaCorreo(String peticion,String id_actividad, String codActividad) throws ATiempoAppEx{
		servicios.enviarCuentaCorreo(peticion,id_actividad, codActividad);
	}

	
	
	public void creacionActuacionAgendaSC(Long codAve,ActividadEJBDTO act)throws ATiempoAppEx{
		servicios.creacionActuacionAgendaSC(codAve, act);
	}
	
	
	public void recepcionCreacionActuacionAgendaSC(TR701S tr701s) throws ATiempoAppEx{
		servicios.recepcionCreacionActuacionAgendaSC(tr701s);
	}
	
	public void enviaActivarDecosTarjetasAgendaSC(Long codAve,String idMensaje) throws ATiempoAppEx{
		servicios.enviaActivarDecosTarjetasAgendaSC(codAve,idMensaje);
	}
	
	public void recepcionActivarDecosTarjetasAgendaSC(TR708S tr708s) throws ATiempoAppEx {
		servicios.recepcionActivarDecosTarjetasAgendaSC(tr708s);
	}
	public boolean esAgendaSC(Long codAve) throws ATiempoAppEx{
		return servicios.esAgendaSC(codAve);
	}
	
	public void recepcionCierreActuacionAgendaSC(TR711S tr711s) throws ATiempoAppEx{
		servicios.recepcionCierreActuacionAgendaSC(tr711s);
	}
	public void cierreActividadYActuacionAgendaSC(TR711S tr711s) throws ATiempoAppEx{
		servicios.cierreActividadYActuacionAgendaSC(tr711s);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#recibirConfiguracionModemAutoinstalacion(co.com.telefonica.atiempo.interfaces.atiempo.TR135S)
	 */
	public void recibirConfiguracionModemAutoinstalacion(TR135S tr135s, boolean esCierreActuacion) throws ATiempoAppEx {
		servicios.recibirConfiguracionModemAutoinstalacion(tr135s, esCierreActuacion);
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#buscarRespuestaConfiguracionModemAutoInstalacion(java.lang.Long, java.lang.Long)
	 */
	public TR135S buscarRespuestaConfiguracionModemAutoInstalacion(Long idPeticion, Long idMensaje) throws ATiempoAppEx {
		return servicios.buscarRespuestaConfiguracionModemAutoInstalacion(idPeticion, idMensaje);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#llamadoConfModemAutoInstalacion(co.com.telefonica.atiempo.soltec.dto.ModemSTDTO, java.lang.String, java.lang.String)
	 */
	public String llamadoConfModemAutoInstalacion(ModemSTDTO modem, String codActividad, String idMensajePadre, boolean esCierreActuacion) throws ATiempoAppEx{
		 return servicios.llamadoConfModemAutoInstalacion(modem, codActividad, idMensajePadre, esCierreActuacion);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#enviarConfiguracionModemAutoinstalacion(co.com.telefonica.atiempo.soltec.dto.ModemSTDTO, java.lang.String, java.lang.String)
	 */
	public String[] enviarConfiguracionModemAutoinstalacion(ModemSTDTO modem, String codActividad, String idMensajePadre) throws ATiempoAppEx{
		return servicios.enviarConfiguracionModemAutoinstalacion(modem, codActividad, idMensajePadre);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#recepcionActivarModemsAgendaSC(co.com.telefonica.atiempo.interfaces.atiempo.TR717S)
	 */
	public void recepcionActivarModemsAgendaSC(TR717S tr717s) throws ATiempoAppEx {
		servicios.recepcionActivarModemsAgendaSC(tr717s);
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#enviarRefrecarDatos(java.lang.Long, java.lang.String, java.lang.String)
	 */
	public void enviarRefrecarDatos(Long idPeticion) {
		// TODO Apéndice de método generado automáticamente
		servicios.enviarRefrecarDatos(idPeticion);
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#isIDPC(java.lang.Long)
	 */
	public boolean isIDPC(Long idPeticion) throws ATiempoAppEx {
		return servicios.isIDPC(idPeticion);
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces#recepcionActualizaInventarioBA(co.com.telefonica.atiempo.interfaces.atiempo.TR804S, boolean)
	 */


}
