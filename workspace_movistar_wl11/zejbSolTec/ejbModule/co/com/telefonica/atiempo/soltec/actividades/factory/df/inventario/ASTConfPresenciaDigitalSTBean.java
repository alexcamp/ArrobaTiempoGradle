package co.com.telefonica.atiempo.soltec.actividades.factory.df.inventario;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.actividades.ejb.sb.ASTDefaultActividadBean;
import co.com.telefonica.atiempo.soltec.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;

/**
 * Bean implementation class for Enterprise Bean: ASTConfPresenciaDigitalST
 */
public class ASTConfPresenciaDigitalSTBean 
extends 	ASTDefaultActividadBean {



/* (non-Javadoc)
* @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onInicioActividadST(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
*/
protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
super.onInicioActividadST(act);
log.debug("--Pasa por metodo onInicioActividadST de ASTVistaGlobalBean, en la actividad "+ act.getCodigoActividad()+", para la petición: "+act.getNumeroPeticion());

try {
	RecursosDelegate delegateEquipos = new RecursosDelegate();			
	delegateEquipos.ConfigurarPresenciaDigital(act, act.getNumeroPeticion(),act.getCodigoActividad());			
} catch (ATiempoAppEx e) {
	log.error("ASTVistaGlobalBean: onInicioActividadST: Error intentando enviar la solicitud de la Vista Global. "+e);
} 
}



}