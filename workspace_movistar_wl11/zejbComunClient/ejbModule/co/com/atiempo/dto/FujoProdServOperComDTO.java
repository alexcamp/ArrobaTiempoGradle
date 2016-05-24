/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class FujoProdServOperComDTO {

	/**
	 * 
	 */
	public FujoProdServOperComDTO() {
		super();
	}

	private Integer flpsId;
	private Long prseId;
	private Long opcoId;
	private Integer flujId;
	
	

	/**
	 * @return
	 */
	public Integer getFlpsId() {
		return flpsId;
	}

	/**
	 * @return
	 */
	public Integer getFlujId() {
		return flujId;
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
	public Long getPrseId() {
		return prseId;
	}

	/**
	 * @param integer
	 */
	public void setFlpsId(Integer integer) {
		flpsId = integer;
	}

	/**
	 * @param integer
	 */
	public void setFlujId(Integer integer) {
		flujId = integer;
	}

	/**
	 * @param long1
	 */
	public void setOpcoId(Long long1) {
		opcoId = long1;
	}

	/**
	 * @param long1
	 */
	public void setPrseId(Long long1) {
		prseId = long1;
	}

}
