/*
 * Created on 26-feb-07
 */
package co.com.telefonica.atiempo.soltec.dto;

import co.com.telefonica.atiempo.utiles.DataTransferObject;

/**
 * @author TCS
 */
public class EquipoStDTO implements DataTransferObject {

	private String tipoEquipo = "";
	private String idDeco = "";
	private String idTarjeta = "";
	private String estado = "";
	private Long idContratista;
	private String contratista = "";
	private String observacion = "";
	private Integer accion;
	private Integer codError; 
	private Integer idEstado;
	private String fechaMarcaHora;
//	TODO PVR se agrego reference al DTO validar donde se  carga este DTO
	private String decoReference;
	private String ddtvSerial="";
			
	private boolean original;
	

	/*
	 * CR 00026144 - 16/06/2009
	 * 		Se agrega campo para marca - Germán P. 
	 */
	private String marca;

	
	public EquipoStDTO()
	{
	}

	/**
	 * @return
	 */
	public String getTipoEquipo() {
		return tipoEquipo;
	}



	/**
	 * @param string
	 */
	public void setTipoEquipo(String string) {
		tipoEquipo = string;
	}

	/**
	 * @return
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param string
	 */
	public void setEstado(String string) {
		estado = string;
	}

	/**
	 * @return
	 */
	public String getContratista() {
		return contratista;
	}

	/**
	 * @return
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param string
	 */
	public void setContratista(String string) {
		contratista = string;
	}

	/**
	 * @param string
	 */
	public void setObservacion(String string) {
		observacion = string;
	}

	/**
	 * @return
	 */


	/**
	 * @return
	 */
	public Long getIdContratista() {
		return idContratista;
	}

	/**
	 * @param long1
	 */
	public void setIdContratista(Long long1) {
		idContratista = long1;
	}
	/**
	 * @return
	 */
	public String getIdDeco() {
		return idDeco;
	}

	/**
	 * @return
	 */
	public String getIdTarjeta() {
		return idTarjeta;
	}

	/**
	 * @param string
	 */
	public void setIdDeco(String string) {
		idDeco = string;
	}

	/**
	 * @param string
	 */
	public void setIdTarjeta(String string) {
		idTarjeta = string;
	}

	/**
	 * @return
	 */
	public Integer getCodError() {
		return codError;
	}

	/**
	 * @return
	 */
	public Integer getIdEstado() {
		return idEstado;
	}

	/**
	 * @param integer
	 */
	public void setCodError(Integer integer) {
		codError = integer;
	}

	/**
	 * @param integer
	 */
	public void setIdEstado(Integer integer) {
		idEstado = integer;
	}

	/**
	 * @return
	 */
	public Integer getAccion() {
		return accion;
	}

	/**
	 * @param long1
	 */
	public void setAccion(Integer int1) {
		accion = int1;
	}

	/**
	 * @return
	 */
	public boolean isOriginal() {
		return original;
	}

	/**
	 * @param b
	 */
	public void setOriginal(boolean b) {
		original = b;
	}

	/**
	 * @return
	 */
	public String getFechaMarcaHora() {
		return fechaMarcaHora;
	}

	/**
	 * @param string
	 */
	public void setFechaMarcaHora(String string) {
		fechaMarcaHora = string;
	}

	/**
	 * @return
	 */
	public String getDecoReference() {
		return decoReference;
	}
	
	/**
	 * @param string
	 */
	public void setDecoReference(String string) {
		decoReference = string;
	}
	
	/**
	 * @return Returns the marca.
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * @param marca The marca to set.
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * @return Devuelve ddtvSerial.
	 */
	public String getDdtvSerial() {
		return ddtvSerial;
	}
	/**
	 * @param ddtvSerial El ddtvSerial a establecer.
	 */
	public void setDdtvSerial(String ddtvSerial) {
		this.ddtvSerial = ddtvSerial;
	}
}
