/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class SegmentoDTO {

	/**
	 * 
	 */
	public SegmentoDTO() {
		super();
	}

	private Long segmId;
	private Long faseId;
	private String segmDescripcion;
	private String segmCodigo;
	private String segmNegocia;
	private String segmTipo;
	private String segmAutoprueba;
	
	
	/**
	 * @return
	 */
	public Long getFaseId() {
		return faseId;
	}

	/**
	 * @return
	 */
	public String getSegmAutoprueba() {
		return segmAutoprueba;
	}

	/**
	 * @return
	 */
	public String getSegmCodigo() {
		return segmCodigo;
	}

	/**
	 * @return
	 */
	public String getSegmDescripcion() {
		return segmDescripcion;
	}

	/**
	 * @return
	 */
	public Long getSegmId() {
		return segmId;
	}

	/**
	 * @return
	 */
	public String getSegmNegocia() {
		return segmNegocia;
	}

	/**
	 * @return
	 */
	public String getSegmTipo() {
		return segmTipo;
	}

	/**
	 * @param long1
	 */
	public void setFaseId(Long long1) {
		faseId = long1;
	}

	/**
	 * @param string
	 */
	public void setSegmAutoprueba(String string) {
		segmAutoprueba = string;
	}

	/**
	 * @param string
	 */
	public void setSegmCodigo(String string) {
		segmCodigo = string;
	}

	/**
	 * @param string
	 */
	public void setSegmDescripcion(String string) {
		segmDescripcion = string;
	}

	/**
	 * @param long1
	 */
	public void setSegmId(Long long1) {
		segmId = long1;
	}

	/**
	 * @param string
	 */
	public void setSegmNegocia(String string) {
		segmNegocia = string;
	}

	/**
	 * @param string
	 */
	public void setSegmTipo(String string) {
		segmTipo = string;
	}

}
