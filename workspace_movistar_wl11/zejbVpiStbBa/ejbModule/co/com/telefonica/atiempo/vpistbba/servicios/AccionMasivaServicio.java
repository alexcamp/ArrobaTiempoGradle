/*
 * Created on 07-07-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.vpistbba.accion_masiva.AccionMasivaDelegate;
import co.com.telefonica.atiempo.vpistbba.accion_masiva.AccionMasivaInterface;
import co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva.AccionMasivaMSGDTO;
import co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva.AccionMasivaMSGService;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AccionMasivaServicio extends ServicioBasico {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		AccionMasivaMSGDTO aMDTO = (AccionMasivaMSGDTO) obj[0];
		AccionMasivaInterface aMI = new AccionMasivaDelegate();
		aMI.ejecutaAccion(aMDTO);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		Object[] obj = new Object[1];
		AccionMasivaMSGService aMS = new AccionMasivaMSGService();
		AccionMasivaMSGDTO aMDTO = (AccionMasivaMSGDTO) aMS.unmarshall(mensaje);
		obj[0] = aMDTO;
			
		return obj;
	}

}
