/*
 * Created on 30-03-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.dto;

/**
 * @author QA
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DatosTecnicosDTO {

	private String planta;
	private String codigoPlantaCCN;
	private String localidad;
	/**
	 * @return
	 */
	public String getCodigoPlantaCCN() {
		return codigoPlantaCCN;
	}

	/**
	 * @return
	 */
	public String getLocalidad() {
		return localidad;
	}

	/**
	 * @return
	 */
	public String getPlanta() {
		return planta;
	}

	/**
	 * @param string
	 */
	public void setCodigoPlantaCCN(String string) {
		codigoPlantaCCN = string;
	}

	/**
	 * @param string
	 */
	public void setLocalidad(String string) {
		localidad = string;
	}

	/**
	 * @param string
	 */
	public void setPlanta(String string) {
		planta = string;
	}

}
