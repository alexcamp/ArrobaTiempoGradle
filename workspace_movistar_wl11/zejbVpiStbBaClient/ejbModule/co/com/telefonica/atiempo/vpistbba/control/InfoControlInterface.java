/*
 * Created on 16-02-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.control;

import co.com.telefonica.atiempo.vpistbba.dto.ControlInstalacionDTO;

/**
 * @author admin
 */
public interface InfoControlInterface {
	public ControlInstalacionDTO obtenerInfoControlInstalacion() throws Exception;
	public ControlInstalacionDTO obtenerInfoControlInstalacionAdsl() throws Exception;
}
