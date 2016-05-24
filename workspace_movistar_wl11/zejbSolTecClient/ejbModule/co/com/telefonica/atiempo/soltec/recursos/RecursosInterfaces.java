/*
 * Created on 23-feb-07
 */
package co.com.telefonica.atiempo.soltec.recursos;

import co.com.telefonica.atiempo.interfaces.atiempo.TR012S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR021S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR057S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR514S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR516S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR709E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR709S;
import co.com.telefonica.atiempo.soltec.dto.convertidores.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_estado_stLocal;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.comun.ComunInterfaces;

/**
 * @author TCS
 */
public interface RecursosInterfaces extends ComunInterfaces {
	
	//TOA FASE III - Se envia codigo Actividad
	public void envioPuntosRedSTB(String peticion, String idActividad, String codActividad) throws ATiempoAppEx;
	
	public void envioPuntosRedSTBGR(String peticion, String idActividad, String codActividad) throws ATiempoAppEx;
	//Fin TOA FASE III
	public void consultaPuntosRedSTB(TR012S tr012s) throws ATiempoAppEx;
	
	public void consultaPuntosRedSTBGR(TR514S tr514s) throws ATiempoAppEx;
		
	public void envioMarcaLineaDefectuosa(String peticion, String idActividad) throws ATiempoAppEx;
	
	public void envioMarcaLineaDefectuosaGR(String peticion, String idActividad) throws ATiempoAppEx;

	public void envioMarcaLineaEnServicio(String peticion, String idActividad) throws ATiempoAppEx;
	
	public void envioMarcaLineaEnServicioGR(String peticion, String idActividad) throws ATiempoAppEx;
	
	public void recibeMarcaLinea(TR021S tr021s) throws ATiempoAppEx;
	
	public void recibeMarcaLineaGR(TR516S tr516s) throws ATiempoAppEx;
	
	public InformacionTecnicaDTO obtenerDatosTecnicosLB(Long codAveOri) throws ATiempoAppEx;
	
	public boolean esMarcadaAveriaSTB(Long idPeticion ) throws ATiempoAppEx;

	/**
	 * @param idPeticion
	 * @param id_actuacion
	 * @param id
	 */
	public void enviarRefrecarDatos(Long idPeticion);
	
	public boolean isIDPC(Long idPeticion) throws ATiempoAppEx;

	public void configurarCamaraZTE(Long numPeticion) throws ATiempoAppEx;
	
	public void respuestaCamaraZTE(TR057S tr) throws ATiempoAppEx;

	public void recepcionActivarCamaraAgendaSC(TR709S tr709s) throws ATiempoAppEx;

	public void enviarActivarCamaraAgendaSC(TR709E tr) throws ATiempoAppEx;

	public void hayPGIAveria (String codigoError,String ErrorMesage,String codigoTR,String codigoAveria,String codigoActividadGeneradora,Mensaje_estado_stLocal meST);
		
}
