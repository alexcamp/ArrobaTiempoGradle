/*
 * Creado el 17/06/2009
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.atiempo.dto;

/**
 * @author db2admin
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class CausaDemoraDTO {

	public CausaDemoraDTO() {
		super();
	}
	
	private Long codCaudem;
	private String descripcionCaudem;
	
	
	
	/**
	 * @return Devuelve codCaudem.
	 */
	public Long getCodCaudem() {
		return codCaudem;
	}
	/**
	 * @param codCaudem El codCaudem a establecer.
	 */
	public void setCodCaudem(Long long1) {
		codCaudem = long1;
	}
	/**
	 * @return Devuelve descripcionCaudem.
	 */
	public String getDescripcionCaudem() {
		return descripcionCaudem;
	}
	/**
	 * @param descripcionCaudem El descripcionCaudem a establecer.
	 */
	public void setDescripcionCaudem(String string) {
		descripcionCaudem = string;
	}
}
