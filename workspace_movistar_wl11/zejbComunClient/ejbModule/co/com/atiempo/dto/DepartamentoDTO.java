/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class DepartamentoDTO implements Comparable {

	/**
	 * 
	 */
	public DepartamentoDTO() {
		super();
	}

	private String nombreRegion;
	private String nombreDepartamento;
	private String descripcionDepartamento;
	private String codDpt;
	


	/**
	 * @return
	 */
	public String getCodDpt() {
		return codDpt;
	}

	/**
	 * @return
	 */
	public String getDescripcionDepartamento() {
		return descripcionDepartamento;
	}

	/**
	 * @return
	 */
	public String getNombreDepartamento() {
		return nombreDepartamento;
	}

	/**
	 * @return
	 */
	public String getNombreRegion() {
		return nombreRegion;
	}

	/**
	 * @param string
	 */
	public void setCodDpt(String string) {
		codDpt = string;
	}

	/**
	 * @param string
	 */
	public void setDescripcionDepartamento(String string) {
		descripcionDepartamento = string;
	}

	/**
	 * @param string
	 */
	public void setNombreDepartamento(String string) {
		nombreDepartamento = string;
	}

	/**
	 * @param string
	 */
	public void setNombreRegion(String string) {
		nombreRegion = string;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		DepartamentoDTO otro=(DepartamentoDTO) o;
		if(this.getNombreDepartamento()!=null && otro.getNombreDepartamento()!=null)
			return this.getNombreDepartamento().compareTo(otro.getNombreDepartamento());
		return 0;
	}

}
