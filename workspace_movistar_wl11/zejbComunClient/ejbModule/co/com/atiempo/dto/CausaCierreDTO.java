/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class CausaCierreDTO {

	/**
	 * 
	 */
	public CausaCierreDTO() {
		super();
	}

	private Long caciId;
	private Long causId;
	private Integer ambiId;
	private Long ciacId;
	


	/**
	 * @return
	 */
	public Integer getAmbiId() {
		return ambiId;
	}

	/**
	 * @return
	 */
	public Long getCaciId() {
		return caciId;
	}

	/**
	 * @return
	 */
	public Long getCausId() {
		return causId;
	}

	/**
	 * @return
	 */
	public Long getCiacId() {
		return ciacId;
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
	public void setCaciId(Long long1) {
		caciId = long1;
	}

	/**
	 * @param long1
	 */
	public void setCausId(Long long1) {
		causId = long1;
	}

	/**
	 * @param long1
	 */
	public void setCiacId(Long long1) {
		ciacId = long1;
	}

}
