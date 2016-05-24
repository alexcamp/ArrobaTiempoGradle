/*
 * Created on 01-10-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.accion_masiva;

import java.util.ArrayList;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.soltec.accion_masiva.ejb.sb.AccionMasivaSTServicesLocal;
import co.com.telefonica.atiempo.soltec.accion_masiva.ejb.sb.AccionMasivaSTServicesLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva.AccionMasivaMSGDTO;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AccionMasivaSTDelegate implements AccionMasivaSTInterface {

	private AccionMasivaSTServicesLocal servicios=null;
	public AccionMasivaSTDelegate() throws ATiempoAppEx {
		try {
			servicios =((AccionMasivaSTServicesLocalHome) HomeFactory.getHome(AccionMasivaSTServicesLocalHome.JNDI_NAME)).create();
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.accion_masiva.AccionMasivaSTInterface#ejecutaAccion(co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva.AccionMasivaMSGDTO)
	 */
	public void ejecutaAccion(AccionMasivaMSGDTO aMDTO) throws ATiempoAppEx {
		servicios.ejecutaAccion(aMDTO);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.accion_masiva.AccionMasivaSTInterface#enviaAccion(java.util.ArrayList)
	 */
	public void enviaAccion(ArrayList peticiones) throws ATiempoAppEx {
		servicios.enviaAccion(peticiones);
	}

}
