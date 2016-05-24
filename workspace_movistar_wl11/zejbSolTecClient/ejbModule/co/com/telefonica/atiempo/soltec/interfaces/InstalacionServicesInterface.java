/*
 * Created on 16-02-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.interfaces;

import co.com.telefonica.atiempo.soltec.dto.InstalacionDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
/**
 * @author admin
 */
public interface InstalacionServicesInterface {
	public InstalacionDTO obtenerInfoInstalacion() throws ATiempoAppEx;
	public void instalar() throws ATiempoAppEx;
	public void controlarInstalacion() throws ATiempoAppEx;
	public void desinstalar() throws ATiempoAppEx;
	public void controlarDesinstalacion() throws ATiempoAppEx;


}
