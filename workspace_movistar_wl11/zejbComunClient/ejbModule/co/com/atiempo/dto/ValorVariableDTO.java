/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class ValorVariableDTO {

	/**
	 * 
	 */
	public ValorVariableDTO() {
		super();
	}

	private int cvId;
	private Long biId;
	private String valor; 
	

	/**
	 * @return
	 */
	public Long getBiId() {
		return biId;
	}

	/**
	 * @return
	 */
	public int getCvId() {
		return cvId;
	}

	/**
	 * @return
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param long1
	 */
	public void setBiId(Long long1) {
		biId = long1;
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
	public void setValor(String string) {
		valor = string;
	}

}
