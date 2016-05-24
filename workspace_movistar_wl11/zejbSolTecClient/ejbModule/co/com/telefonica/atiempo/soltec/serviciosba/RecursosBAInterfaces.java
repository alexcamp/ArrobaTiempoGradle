/*
 * Created on 30-03-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.serviciosba;

import java.util.ArrayList;

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
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.comun.ComunInterfaces;

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface RecursosBAInterfaces extends ComunInterfaces {
	
	public void enviarConfiguracionActualBA(String peticion, String id_actividad) throws ATiempoAppEx;
	public void recepcionConfiguracionActualBA(TR014S tr014s) throws ATiempoAppEx;

	public void enviarActualizacionInventarioBA(ActividadEJBDTO act, String peticion, String id_actividad) throws ATiempoAppEx;
	public void recepcionActualizacionInventarioBA(TR007S tr007s) throws ATiempoAppEx;
	public ArrayList getModemSt(Long peticion) throws ATiempoAppEx;
	//TCS-gquevedo May 18, 2009 CR.23338 INICIO
	public String getTipoModemSt(Long peticion) throws ATiempoAppEx;
	//TCS-gquevedo May 18, 2009 CR.23338 FIN
	public void grabaModemsBaST(Long nroPeticion,Long telAsignado,ArrayList modems)throws ATiempoAppEx;
	public RecursosBADTO obtenerRecursosBA(Long codAveOri) throws ATiempoAppEx;
	public Long enviaModemPorUtilizar(long codAveCd, String modemConsulta, long idContratista) throws ATiempoAppEx;
	public void actualizaModemPorUtilizar(TR022S tr022s) throws ATiempoAppEx;
	public TR022S buscarRespuestaMensajeModem(Long codAveCd, Long idMensaje) throws ATiempoAppEx;
	// Inicio SIGRES
	public void enviarConfiguracionActualBASigres(String peticion, String id_actividad) throws ATiempoAppEx;
	public void recepcionConfiguracionActualBASigres(TR035S tr035s) throws ATiempoAppEx;
	public void recepcionCuentaCorreoBA(TR043S tr043s) throws ATiempoAppEx;
	public void enviarCuentaCorreo(String peticion, String id_actividad, String codActividad) throws ATiempoAppEx;
	// fin SIGRES
	//DAVID: Inicio métodos Agenda SC.
	public void creacionActuacionAgendaSC(Long codAve, ActividadEJBDTO act)throws ATiempoAppEx;
	public void recepcionCreacionActuacionAgendaSC(TR701S tr701s) throws ATiempoAppEx;	
	public void enviaActivarDecosTarjetasAgendaSC(Long codAve,String idMensaje) throws ATiempoAppEx;	
	public void recepcionActivarDecosTarjetasAgendaSC(TR708S tr708s) throws ATiempoAppEx;
	public boolean esAgendaSC(Long codAve) throws ATiempoAppEx;
	public void recepcionCierreActuacionAgendaSC(TR711S tr711s) throws ATiempoAppEx;
	public void cierreActividadYActuacionAgendaSC(TR711S tr711s) throws ATiempoAppEx;
	
	public String llamadoConfModemAutoInstalacion(ModemSTDTO modem, String codActividad, String idMensajePadre, boolean esCierreActuacion) throws ATiempoAppEx;
	public String[] enviarConfiguracionModemAutoinstalacion(ModemSTDTO modem, String codActividad, String idMensajePadre) throws ATiempoAppEx;
	public void recibirConfiguracionModemAutoinstalacion(TR135S tr135s, boolean esCierreActuacion) throws ATiempoAppEx;
	public TR135S buscarRespuestaConfiguracionModemAutoInstalacion(Long idPeticion, Long idMensaje) throws ATiempoAppEx;
	
	public void recepcionActivarModemsAgendaSC(TR717S tr717s) throws ATiempoAppEx;
	/**
	 * @param idPeticion
	 * @param id_actuacion
	 * @param id
	 */
	public void enviarRefrecarDatos(Long idPeticion);
	
	public boolean isIDPC(Long idPeticion) throws ATiempoAppEx;
	
}
