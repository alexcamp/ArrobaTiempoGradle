/*
 * Created on 23-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.instalacion;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Recursos_baLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.interfaces.atiempo.TR801S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR802S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR803S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR804S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR805E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR805S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR811S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.comun.ComunInterfaces;

/**
 * @author TCS
 */
	
public interface TOAInterfaces extends ComunInterfaces {
	public void crearActuacionTOA(ActividadEJBDTO act)throws ATiempoAppEx;
	
	public void recepcionCrearActuacionTOA(String respuesta, ActividadEJBDTO act, String idActuacion)throws ATiempoAppEx;

	public void recepcionActivarDecosTarjetasTOA(TR801S tr801s)throws ATiempoAppEx;
	
	public void recepcionActivarModemTOA(TR802S tr802s)throws ATiempoAppEx;
	
	public void enviaActivarDecosTarjetasTOA(String idActuacion, String idMensajePeticion)throws ATiempoAppEx;
	
	public void enviaActivarModemTOA(String idActuacion, String idMensajePeticion)throws ATiempoAppEx;
	
	public void recepcionCierreActuacionTOA (TR811S tr811s) throws ATiempoAppEx;
	
	public void refrescarRecursosSTB(TR803S tr803s)  throws ATiempoAppEx;
	
	public void recepcionActualizaInventarioBA(TR804S tr804s)throws ATiempoAppEx;
	
	public void recibeActualizacionInventarioTV (TR805E tr805e) throws ATiempoAppEx;
	
	public void enviaRefrescarRecursosTV(TR805S tr805s) throws ATiempoAppEx;
	
	public void enviaRefrescarRecursosSTB(Recursos_linea_basicaLocal recursos_linea_basicaLocal) throws ATiempoAppEx;
	
	public void enviaRefrescarRecursosBA(Recursos_baLocal recursos_baLocal) throws ATiempoAppEx;
}
