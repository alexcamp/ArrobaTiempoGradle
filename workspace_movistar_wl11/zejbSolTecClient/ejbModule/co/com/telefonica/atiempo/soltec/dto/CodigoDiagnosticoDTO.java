/*
 * Created on 28-jun-07
 */
package co.com.telefonica.atiempo.soltec.dto;

/**
 * @author rodrigo
 */
public class CodigoDiagnosticoDTO {
	
	private String idDefAper;
	private String codTipoDef;
	private String descTipoDef;
	private String descDefAper;
	
	

	/**
	 * @return
	 */
	public String getCodTipoDef() {
		return codTipoDef;
	}

	/**
	 * @return
	 */
	public String getDescDefAper() {
		return descDefAper;
	}

	/**
	 * @return
	 */
	public String getDescTipoDef() {
		return descTipoDef;
	}

	/**
	 * @return
	 */
	public String getIdDefAper() {
		return idDefAper;
	}

	/**
	 * @param string
	 */
	public void setCodTipoDef(String string) {
		codTipoDef = string;
	}

	/**
	 * @param string
	 */
	public void setDescDefAper(String string) {
		descDefAper = string;
	}

	/**
	 * @param string
	 */
	public void setDescTipoDef(String string) {
		descTipoDef = string;
	}

	/**
	 * @param string
	 */
	public void setIdDefAper(String string) {
		idDefAper = string;
	}

}
