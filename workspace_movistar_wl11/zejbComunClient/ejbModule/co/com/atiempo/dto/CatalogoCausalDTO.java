/*
 * Created on 15-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class CatalogoCausalDTO {

	public CatalogoCausalDTO() {
		super();
	}
	
	private Long codCausal;
	private Integer quiebre;
	private String descripcionCausal;
	private Integer actividadQuiebre;
	
	

	/**
	 * @return
	 */
	public Integer getActividadQuiebre() {
		return actividadQuiebre;
	}

	/**
	 * @return
	 */
	public Long getCodCausal() {
		return codCausal;
	}

	/**
	 * @return
	 */
	public String getDescripcionCausal() {
		return descripcionCausal;
	}

	/**
	 * @return
	 */
	public Integer getQuiebre() {
		return quiebre;
	}

	/**
	 * @param integer
	 */
	public void setActividadQuiebre(Integer integer) {
		actividadQuiebre = integer;
	}

	/**
	 * @param long1
	 */
	public void setCodCausal(Long long1) {
		codCausal = long1;
	}

	/**
	 * @param string
	 */
	public void setDescripcionCausal(String string) {
		descripcionCausal = string;
	}

	/**
	 * @param integer
	 */
	public void setQuiebre(Integer integer) {
		quiebre = integer;
	}

}
