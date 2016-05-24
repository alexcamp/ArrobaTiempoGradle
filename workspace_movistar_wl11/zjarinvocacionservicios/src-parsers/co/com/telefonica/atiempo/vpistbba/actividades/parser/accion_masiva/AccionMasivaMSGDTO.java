/*
 * Created on 07-07-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.actividades.parser.accion_masiva;

/**
 * @author VictorMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AccionMasivaMSGDTO {
	private Integer codigoAccion;
	private Long numeroPeticion;
	private String codigoActividad;
	private String instanciaActividad;
	

	/**
	 * @return
	 */
	public Integer getCodigoAccion() {
		return codigoAccion;
	}

	/**
	 * @return
	 */
	public String getCodigoActividad() {
		return codigoActividad;
	}

	/**
	 * @return
	 */
	public Long getNumeroPeticion() {
		return numeroPeticion;
	}

	/**
	 * @param integer
	 */
	public void setCodigoAccion(Integer integer) {
		codigoAccion = integer;
	}

	/**
	 * @param string
	 */
	public void setCodigoActividad(String string) {
		codigoActividad = string;
	}

	/**
	 * @param long1
	 */
	public void setNumeroPeticion(Long long1) {
		numeroPeticion = long1;
	}

	/**
	 * @return
	 */
	public String getInstanciaActividad() {
		return instanciaActividad;
	}

	/**
	 * @param string
	 */
	public void setInstanciaActividad(String string) {
		instanciaActividad = string;
	}

}
