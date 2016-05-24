package co.com.telefonica.atiempo.vpistbba.control.ejb;

import co.com.telefonica.atiempo.utiles.SessionBeanAdapter;
import co.com.telefonica.atiempo.vpistbba.control.InfoControlInterface;
import co.com.telefonica.atiempo.vpistbba.dto.ControlInstalacionDTO;

/**
 * Bean implementation class for Enterprise Bean: InfoControl
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class InfoControlBean
	extends SessionBeanAdapter
	implements InfoControlInterface {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.comun.business.InfoControlInterface#obtenerInfoControlInstalacion()
	 */
	public ControlInstalacionDTO obtenerInfoControlInstalacion() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.control.InfoControlInterface#obtenerInfoControlInstalacionAdsl()
	 */
	public ControlInstalacionDTO obtenerInfoControlInstalacionAdsl() throws Exception {
		return null;
	}

}
