/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class FlujoDefinicionDTO {

	/**
	 * 
	 */
	public FlujoDefinicionDTO() {
		super();
	}
	
	private Integer fldeId;
	private Integer flujId;
	private Integer actiId;
	private Integer actActiId;
	
	

	/**
	 * @return
	 */
	public Integer getActActiId() {
		return actActiId;
	}

	/**
	 * @return
	 */
	public Integer getActiId() {
		return actiId;
	}

	/**
	 * @return
	 */
	public Integer getFldeId() {
		return fldeId;
	}

	/**
	 * @return
	 */
	public Integer getFlujId() {
		return flujId;
	}

	/**
	 * @param integer
	 */
	public void setActActiId(Integer integer) {
		actActiId = integer;
	}

	/**
	 * @param integer
	 */
	public void setActiId(Integer integer) {
		actiId = integer;
	}

	/**
	 * @param integer
	 */
	public void setFldeId(Integer integer) {
		fldeId = integer;
	}

	/**
	 * @param integer
	 */
	public void setFlujId(Integer integer) {
		flujId = integer;
	}

}
