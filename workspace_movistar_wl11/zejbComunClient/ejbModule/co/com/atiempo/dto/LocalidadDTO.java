/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class LocalidadDTO implements Comparable {

	/**
	 * 
	 */
	public LocalidadDTO() {
		super();
	}

	private String nombreProvincia;
	private String nombreMunicipio;
	private String nombreLocalidad;
	private String descripcionLocalidad;
	private String codLoc;
	private String indPresuscr;
	private String indSeleccMarcado;
	private String codFormatoDireccion;
	private String cod_mun;



	/**
	 * @return
	 */
	public String getCodFormatoDireccion() {
		return codFormatoDireccion;
	}

	/**
	 * @return
	 */
	public String getCodLoc() {
		return codLoc;
	}

	/**
	 * @return
	 */
	public String getDescripcionLocalidad() {
		return descripcionLocalidad;
	}

	/**
	 * @return
	 */
	public String getIndPresuscr() {
		return indPresuscr;
	}

	/**
	 * @return
	 */
	public String getIndSeleccMarcado() {
		return indSeleccMarcado;
	}

	/**
	 * @return
	 */
	public String getNombreLocalidad() {
		return nombreLocalidad;
	}

	/**
	 * @return
	 */
	public String getNombreMunicipio() {
		return nombreMunicipio;
	}

	/**
	 * @return
	 */
	public String getNombreProvincia() {
		return nombreProvincia;
	}

	/**
	 * @param string
	 */
	public void setCodFormatoDireccion(String string) {
		codFormatoDireccion = string;
	}

	/**
	 * @param string
	 */
	public void setCodLoc(String string) {
		codLoc = string;
	}

	/**
	 * @param string
	 */
	public void setDescripcionLocalidad(String string) {
		descripcionLocalidad = string;
	}

	/**
	 * @param string
	 */
	public void setIndPresuscr(String string) {
		indPresuscr = string;
	}

	/**
	 * @param string
	 */
	public void setIndSeleccMarcado(String string) {
		indSeleccMarcado = string;
	}

	/**
	 * @param string
	 */
	public void setNombreLocalidad(String string) {
		nombreLocalidad = string;
	}

	/**
	 * @param string
	 */
	public void setNombreMunicipio(String string) {
		nombreMunicipio = string;
	}

	/**
	 * @param string
	 */
	public void setNombreProvincia(String string) {
		nombreProvincia = string;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0)
	{
		LocalidadDTO localidadDTO=(LocalidadDTO) arg0;
		if(this.nombreLocalidad!=null && localidadDTO.getNombreLocalidad()!=null)
			return this.nombreLocalidad.compareTo(localidadDTO.getNombreLocalidad());
		return 0;
	}

	/**
	 * @return
	 */
	public String getCod_mun() {
		return cod_mun;
	}

	/**
	 * @param string
	 */
	public void setCod_mun(String string) {
		cod_mun = string;
	}

}
