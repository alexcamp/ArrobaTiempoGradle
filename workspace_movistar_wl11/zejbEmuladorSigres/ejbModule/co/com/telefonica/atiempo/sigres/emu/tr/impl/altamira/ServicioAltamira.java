/*
 * Created on May 8, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.sigres.emu.tr.impl.altamira;

/**
 * @author 801936
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public abstract class ServicioAltamira {

	public String nombreServicio;
	
	protected abstract String getEstadoRespuestaSincrona();
	protected abstract String getEstadoRespuestaAsincrona();
	protected abstract boolean generarRespuestaAsincrona();
	
	public String getNombreServicio() {
		return nombreServicio;
	}
	
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
		
}
