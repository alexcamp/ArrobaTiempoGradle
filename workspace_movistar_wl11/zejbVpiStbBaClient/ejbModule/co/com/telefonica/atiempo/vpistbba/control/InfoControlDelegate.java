/*
 * Created on 16-02-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.control;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.control.ejb.InfoControlLocal;
import co.com.telefonica.atiempo.vpistbba.control.ejb.InfoControlLocalHome;
import co.com.telefonica.atiempo.vpistbba.dto.ControlInstalacionDTO;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author admin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InfoControlDelegate implements InfoControlInterface {

	private InfoControlLocal ejbInfoControl;

	public InfoControlDelegate() throws ATiempoAppEx {
		try {
			ejbInfoControl =
				((InfoControlLocalHome) HomeFactory
					.getHome(InfoControlLocalHome.JNDI_NAME))
					.create();
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}

	}
	public ControlInstalacionDTO obtenerInfoControlInstalacionAdsl()
		throws Exception {
		return ejbInfoControl.obtenerInfoControlInstalacionAdsl();
	}
	public ControlInstalacionDTO obtenerInfoControlInstalacion()
		throws Exception {
		return ejbInfoControl.obtenerInfoControlInstalacion();
	}

}
