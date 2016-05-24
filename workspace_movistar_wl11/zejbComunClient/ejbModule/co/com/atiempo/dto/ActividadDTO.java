/*
 * Created on 08-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class ActividadDTO {

	/**
	 * 
	 */
	public ActividadDTO() {
		super();
	}

	private Long actId;
	private Long apId;
	private Long rolId;
	private String actCodigo;
	private String actDescripcion;
	private Integer actOrdenTc;
	private String actNombreReversa;
	private int actManual;
	private Long actOla;
    private String actNombreFlujo;

	/**
	 * @return
	 */
	public String getActCodigo() {
		return actCodigo;
	}

	/**
	 * @return
	 */
	public String getActDescripcion() {
		return actDescripcion;
	}

	/**
	 * @return
	 */
	public Long getActId() {
		return actId;
	}

	/**
	 * @return
	 */
	public int getActManual() {
		return actManual;
	}

	/**
	 * @return
	 */
	public String getActNombreFlujo() {
		return actNombreFlujo;
	}

	/**
	 * @return
	 */
	public String getActNombreReversa() {
		return actNombreReversa;
	}

	/**
	 * @return
	 */
	public Long getActOla() {
		return actOla;
	}

	/**
	 * @return
	 */
	public Integer getActOrdenTc() {
		return actOrdenTc;
	}

	/**
	 * @return
	 */
	public Long getApId() {
		return apId;
	}

	/**
	 * @return
	 */
	public Long getRolId() {
		return rolId;
	}

	/**
	 * @param string
	 */
	public void setActCodigo(String string) {
		actCodigo = string;
	}

	/**
	 * @param string
	 */
	public void setActDescripcion(String string) {
		actDescripcion = string;
	}

	/**
	 * @param long1
	 */
	public void setActId(Long long1) {
		actId = long1;
	}

	/**
	 * @param i
	 */
	public void setActManual(int i) {
		actManual = i;
	}

	/**
	 * @param string
	 */
	public void setActNombreFlujo(String string) {
		actNombreFlujo = string;
	}

	/**
	 * @param string
	 */
	public void setActNombreReversa(String string) {
		actNombreReversa = string;
	}

	/**
	 * @param long1
	 */
	public void setActOla(Long long1) {
		actOla = long1;
	}

	/**
	 * @param integer
	 */
	public void setActOrdenTc(Integer integer) {
		actOrdenTc = integer;
	}

	/**
	 * @param long1
	 */
	public void setApId(Long long1) {
		apId = long1;
	}

	/**
	 * @param long1
	 */
	public void setRolId(Long long1) {
		rolId = long1;
	}

}
