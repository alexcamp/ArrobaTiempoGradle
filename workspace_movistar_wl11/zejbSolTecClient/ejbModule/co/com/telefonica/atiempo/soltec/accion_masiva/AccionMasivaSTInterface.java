/*
 * Created on 01-10-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.accion_masiva;

import java.util.ArrayList;

import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva.AccionMasivaMSGDTO;

import com.telefonica_chile.comun.ComunInterfaces;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface AccionMasivaSTInterface extends ComunInterfaces {
	
	public void ejecutaAccion(AccionMasivaMSGDTO aMDTO) throws ATiempoAppEx;
	public void enviaAccion(ArrayList peticiones) throws ATiempoAppEx;

}
