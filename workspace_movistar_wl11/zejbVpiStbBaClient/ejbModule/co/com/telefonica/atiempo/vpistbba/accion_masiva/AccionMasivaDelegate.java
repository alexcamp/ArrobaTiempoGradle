/*
 * Created on 07-07-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.accion_masiva;

import java.util.ArrayList;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.accion_masiva.ejb.sb.AccionMasivaServicesLocal;
import co.com.telefonica.atiempo.vpistbba.accion_masiva.ejb.sb.AccionMasivaServicesLocalHome;
import co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva.AccionMasivaMSGDTO;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AccionMasivaDelegate implements AccionMasivaInterface {
	private AccionMasivaServicesLocal servicios;

	public AccionMasivaDelegate() throws ATiempoAppEx {
		try {
			servicios =((AccionMasivaServicesLocalHome) HomeFactory.getHome(AccionMasivaServicesLocalHome.JNDI_NAME)).create();
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.accion_masiva.AccionMasivaInterface#ejecutaAccion(co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva.AccionMasivaMSGDTO)
	 */
	public void ejecutaAccion(AccionMasivaMSGDTO aMDTO) throws ATiempoAppEx {
		servicios.ejecutaAccion(aMDTO);
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.accion_masiva.AccionMasivaInterface#enviaAccion(java.util.ArrayList)
	 */
	public void enviaAccion(ArrayList peticiones) throws ATiempoAppEx {
		servicios.enviaAccion(peticiones);
		
	}

}
