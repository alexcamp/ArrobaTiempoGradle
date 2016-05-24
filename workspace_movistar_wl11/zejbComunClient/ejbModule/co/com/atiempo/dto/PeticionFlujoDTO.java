/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class PeticionFlujoDTO {

	/**
	 * 
	 */
	public PeticionFlujoDTO() {
		super();
	}

	private Integer peflId;
	private Long petiNumero;
	private Long opcoId;
	private Integer actiId;
	private Long prseId;
	private Integer peflOrden;
	private String peflEstado;
	
	

	/**
	 * @return
	 */
	public Integer getActiId() {
		return actiId;
	}

	/**
	 * @return
	 */
	public Long getOpcoId() {
		return opcoId;
	}

	/**
	 * @return
	 */
	public String getPeflEstado() {
		return peflEstado;
	}

	/**
	 * @return
	 */
	public Integer getPeflId() {
		return peflId;
	}

	/**
	 * @return
	 */
	public Integer getPeflOrden() {
		return peflOrden;
	}

	/**
	 * @return
	 */
	public Long getPetiNumero() {
		return petiNumero;
	}

	/**
	 * @return
	 */
	public Long getPrseId() {
		return prseId;
	}

	/**
	 * @param integer
	 */
	public void setActiId(Integer integer) {
		actiId = integer;
	}

	/**
	 * @param long1
	 */
	public void setOpcoId(Long long1) {
		opcoId = long1;
	}

	/**
	 * @param string
	 */
	public void setPeflEstado(String string) {
		peflEstado = string;
	}

	/**
	 * @param integer
	 */
	public void setPeflId(Integer integer) {
		peflId = integer;
	}

	/**
	 * @param integer
	 */
	public void setPeflOrden(Integer integer) {
		peflOrden = integer;
	}

	/**
	 * @param long1
	 */
	public void setPetiNumero(Long long1) {
		petiNumero = long1;
	}

	/**
	 * @param long1
	 */
	public void setPrseId(Long long1) {
		prseId = long1;
	}

}
