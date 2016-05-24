/*
 * Created on 01-10-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.servicios;

import co.com.telefonica.atiempo.soltec.accion_masiva.AccionMasivaSTDelegate;
import co.com.telefonica.atiempo.soltec.accion_masiva.AccionMasivaSTInterface;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva.AccionMasivaMSGDTO;
import co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva.AccionMasivaMSGService;

/**
 * @author VicorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AccionMasivaSTServicio extends ServicioBasico {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		AccionMasivaMSGDTO aMDTO = (AccionMasivaMSGDTO) obj[0];
		AccionMasivaSTInterface aMI = new AccionMasivaSTDelegate();
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
