/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class CierreActividadDTO {

	/**
	 * 
	 */
	public CierreActividadDTO() {
		super();
	}

	private Long ciacId;
	private Long ActId;
	private String ciacNombre;
	private String ciacCodigo;
	private String ciacReversa;
	private String ciacValor;
	private String ciacTermino;
	
	

	/**
	 * @return
	 */
	public Long getActId() {
		return ActId;
	}

	/**
	 * @return
	 */
	public String getCiacCodigo() {
		return ciacCodigo;
	}

	/**
	 * @return
	 */
	public Long getCiacId() {
		return ciacId;
	}

	/**
	 * @return
	 */
	public String getCiacNombre() {
		return ciacNombre;
	}

	/**
	 * @return
	 */
	public String getCiacReversa() {
		return ciacReversa;
	}

	/**
	 * @return
	 */
	public String getCiacTermino() {
		return ciacTermino;
	}

	/**
	 * @return
	 */
	public String getCiacValor() {
		return ciacValor;
	}

	/**
	 * @param long1
	 */
	public void setActId(Long long1) {
		ActId = long1;
	}

	/**
	 * @param string
	 */
	public void setCiacCodigo(String string) {
		ciacCodigo = string;
	}

	/**
	 * @param long1
	 */
	public void setCiacId(Long long1) {
		ciacId = long1;
	}

	/**
	 * @param string
	 */
	public void setCiacNombre(String string) {
		ciacNombre = string;
	}

	/**
	 * @param string
	 */
	public void setCiacReversa(String string) {
		ciacReversa = string;
	}

	/**
	 * @param string
	 */
	public void setCiacTermino(String string) {
		ciacTermino = string;
	}

	/**
	 * @param string
	 */
	public void setCiacValor(String string) {
		ciacValor = string;
	}

}
