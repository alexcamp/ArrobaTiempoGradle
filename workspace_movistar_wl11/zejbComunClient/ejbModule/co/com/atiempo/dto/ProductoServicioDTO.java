/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class ProductoServicioDTO {

	/**
	 * 
	 */
	public ProductoServicioDTO() {
		super();
	}

	private Long psId;
	private Long emprId;
	private Integer ambiId;
	private Long fapsId;
	private Long grpsId;
	private String psNombre;
	private int psEsFacturable;
	private int psPermiteGestionTecnico;
	private String psObservacion;
	private int psVigente;
	private int psPcoObligatorio;
	private String psComandoActivacion;
	private Integer habiId;
	
	


	/**
	 * @return
	 */
	public Integer getAmbiId() {
		return ambiId;
	}

	/**
	 * @return
	 */
	public Long getEmprId() {
		return emprId;
	}

	/**
	 * @return
	 */
	public Long getFapsId() {
		return fapsId;
	}

	/**
	 * @return
	 */
	public Long getGrpsId() {
		return grpsId;
	}

	/**
	 * @return
	 */
	public Integer getHabiId() {
		return habiId;
	}

	/**
	 * @return
	 */
	public String getPsComandoActivacion() {
		return psComandoActivacion;
	}

	/**
	 * @return
	 */
	public int getPsEsFacturable() {
		return psEsFacturable;
	}

	/**
	 * @return
	 */
	public Long getPsId() {
		return psId;
	}

	/**
	 * @return
	 */
	public String getPsNombre() {
		return psNombre;
	}

	/**
	 * @return
	 */
	public String getPsObservacion() {
		return psObservacion;
	}

	/**
	 * @return
	 */
	public int getPsPcoObligatorio() {
		return psPcoObligatorio;
	}

	/**
	 * @return
	 */
	public int getPsPermiteGestionTecnico() {
		return psPermiteGestionTecnico;
	}

	/**
	 * @return
	 */
	public int getPsVigente() {
		return psVigente;
	}

	/**
	 * @param integer
	 */
	public void setAmbiId(Integer integer) {
		ambiId = integer;
	}

	/**
	 * @param long1
	 */
	public void setEmprId(Long long1) {
		emprId = long1;
	}

	/**
	 * @param long1
	 */
	public void setFapsId(Long long1) {
		fapsId = long1;
	}

	/**
	 * @param long1
	 */
	public void setGrpsId(Long long1) {
		grpsId = long1;
	}

	/**
	 * @param integer
	 */
	public void setHabiId(Integer integer) {
		habiId = integer;
	}

	/**
	 * @param string
	 */
	public void setPsComandoActivacion(String string) {
		psComandoActivacion = string;
	}

	/**
	 * @param i
	 */
	public void setPsEsFacturable(int i) {
		psEsFacturable = i;
	}

	/**
	 * @param long1
	 */
	public void setPsId(Long long1) {
		psId = long1;
	}

	/**
	 * @param string
	 */
	public void setPsNombre(String string) {
		psNombre = string;
	}

	/**
	 * @param string
	 */
	public void setPsObservacion(String string) {
		psObservacion = string;
	}

	/**
	 * @param i
	 */
	public void setPsPcoObligatorio(int i) {
		psPcoObligatorio = i;
	}

	/**
	 * @param i
	 */
	public void setPsPermiteGestionTecnico(int i) {
		psPermiteGestionTecnico = i;
	}

	/**
	 * @param i
	 */
	public void setPsVigente(int i) {
		psVigente = i;
	}

}
