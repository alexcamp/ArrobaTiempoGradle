/*
 * Created on 12-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.serviciostv;

import java.util.ArrayList;
import java.util.Collection;

import co.com.telefonica.atiempo.interfaces.atiempo.TR016S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR017S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR018S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR019S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.comun.ComunInterfaces;

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface RecursosTVInterfaces extends ComunInterfaces{
	

	/**
	 * solicita a HC una lista de decos y tarjetas cuyos numeo de series terminan con 4 digitos
	 * 
	 * esta rutina es llamada directamente desde la interfaz al usuario
	 * 
	 * @param idPeticion el id de la peticion
	 * @param ult4digitosTarjeta ultimos 4 digitos del numero de la tarjeta
	 * @param ult4digitosDeco ultimos 4 digitos del numero de serie del deco
	 * @param idContratista id del contratista
	 * @param tipoDecodificador id del contratista
	 */
	// PVR se modifico para que el buscar tenga el tipo Deco
	public Long enviaDecoTarjetaSTPorUtilizar (long idPeticion, String ult4digitosTarjeta, String ult4digitosDeco, long idContratista, String tipoDeco) throws ATiempoAppEx;

	public void actualizaDecoTarjetaSTPorUtilizar (TR016S tr016s) throws ATiempoAppEx;
	/**
	 * Busca la Respuesta al Mensaje Enviado
	 * 
	 * esta rutina es llamada directamente desde la interfaz al usuario
	 * 
	 * @param idPeticion el id de la peticion
	 * @param idMensaje id del mensaje
	 */

	public TR016S buscarRespuestaMensaje(Long idPeticion, Long idMensaje) throws ATiempoAppEx;
		
	public void solicitudInformacionTecnica(String peticion, String id_actividad) throws ATiempoAppEx;
	
	public void recepcionInformacionTecnica(TR019S tr019s) throws ATiempoAppEx;
	
	public boolean enviaActualizacionInventarioTV (long idPeticion, String idActividad) throws ATiempoAppEx;

	public void recibeActualizacionInventarioTV (TR018S tr018s) throws ATiempoAppEx;
	
	public Collection getListaDecoTarjetas( Long idPeticion ) throws ATiempoAppEx;
	
	public Long enviarActivacionCI(Long idPeticion, ArrayList listaActivar,ArrayList listaEliminar) throws ATiempoAppEx;
	
	public void actualizaConfiguracionServiciosTV (TR017S tr017s) throws ATiempoAppEx;

	public void enviaRefrescoPares(Long nroPeticion) throws ATiempoAppEx;
	
	public Long enviaConfiguracionServiciosTVSoloEq(long idPeticion) throws ATiempoAppEx;
	
	//	DAVID: Se adiciona el siguiente método para agenda SC
	public Long enviaConfiguracionServiciosTVSoloEqAgendaSC(long idPeticion) throws ATiempoAppEx;
	public Long enviaConfiguracionServiciosTVSoloEqAgendaSC(long idPeticion, ArrayList decosAInstalar) throws ATiempoAppEx;
	
	public boolean isIdpcTV(Long idPeticion) throws ATiempoAppEx;
}
