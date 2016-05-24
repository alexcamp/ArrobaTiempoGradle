package co.com.atiempo.dto;


public class MunicipioDTO implements Comparable {

	private String cod_mun;
	private String nombre_municipio;
	private String descripcion_municipio;
	private String cod_dpt;
	
	/**
	 * @return
	 */
	public String getCod_dpt() {
		return cod_dpt;
	}

	/**
	 * @return
	 */
	public String getCod_mun() {
		return cod_mun;
	}

	/**
	 * @return
	 */
	public String getDescripcion_municipio() {
		return descripcion_municipio;
	}

	/**
	 * @return
	 */
	public String getNombre_municipio() {
		return nombre_municipio;
	}

	/**
	 * @param string
	 */
	public void setCod_dpt(String string) {
		cod_dpt = string;
	}

	/**
	 * @param string
	 */
	public void setCod_mun(String string) {
		cod_mun = string;
	}

	/**
	 * @param string
	 */
	public void setDescripcion_municipio(String string) {
		descripcion_municipio = string;
	}

	/**
	 * @param string
	 */
	public void setNombre_municipio(String string) {
		nombre_municipio = string;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		MunicipioDTO otro=(MunicipioDTO) o;
		if(this.nombre_municipio!=null && otro.getNombre_municipio()!=null)
			return this.nombre_municipio.compareTo(otro.getNombre_municipio());
		return 0;
	}

}
