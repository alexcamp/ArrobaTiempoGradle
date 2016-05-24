package co.com.telefonica.atiempo.vpistbba.grabadores;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.dto.InstalacionDTO;

/**
 * Bean implementation class for Enterprise Bean: InstalacionServices
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class GrabaDatosPantalla
	extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter
	 {
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionServicesInterface#obtenerInfoInstalacion()
	 */
	public InstalacionDTO obtenerInfoInstalacion() throws ATiempoAppEx {
		return null;
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionServicesInterface#instalar()
	 */
	public void instalar() throws ATiempoAppEx {
	
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionServicesInterface#controlarInstalacion()
	 */
	public void controlarInstalacion() throws ATiempoAppEx {
	
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionServicesInterface#desinstalar()
	 */
	public void desinstalar() throws ATiempoAppEx {
	
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.instalacion.InstalacionServicesInterface#controlarDesinstalacion()
	 */
	public void controlarDesinstalacion() throws ATiempoAppEx {
	
	}

}
