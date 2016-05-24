/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class CampoVariableDTO {

	/**
	 * 
	 */
	public CampoVariableDTO() {
		super();
	}
	
	private int cvId;
	private String cvNombreInt;
	private String cvNombreExt;
	
	
	/**
	 * @return
	 */
	public int getCvId() {
		return cvId;
	}

	/**
	 * @return
	 */
	public String getCvNombreExt() {
		return cvNombreExt;
	}

	/**
	 * @return
	 */
	public String getCvNombreInt() {
		return cvNombreInt;
	}

	/**
	 * @param i
	 */
	public void setCvId(int i) {
		cvId = i;
	}

	/**
	 * @param string
	 */
	public void setCvNombreExt(String string) {
		cvNombreExt = string;
	}

	/**
	 * @param string
	 */
	public void setCvNombreInt(String string) {
		cvNombreInt = string;
	}

}
