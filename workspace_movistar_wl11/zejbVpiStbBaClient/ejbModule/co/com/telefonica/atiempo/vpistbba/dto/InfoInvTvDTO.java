package co.com.telefonica.atiempo.vpistbba.dto;

import com.telefonica_chile.atiempo.utiles.Fecha;

public class InfoInvTvDTO {

	public InfoInvTvDTO(Long idPeticion)
	{
		this.idPeticion=idPeticion;
		estado="0";
		descripcion="Act. Inv. No realizada";
	}

	private Long idPeticion;
	private String estado;
	private Fecha marca_hora;
	private String descripcion;	

	/**
	 * @return
	 */
	public String getDescripcion()
	{
		if(descripcion==null)
			return "Esperando Respuesta Actualizacion Inventario";
		return descripcion;
	}

	/**
	 * @return
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @return
	 */
	public Long getIdPeticion() {
		return idPeticion;
	}

	/**
	 * @return
	 */
	public Fecha getMarca_hora() {
		return marca_hora;
	}

	/**
	 * @param string
	 */
	public void setDescripcion(String string) {
		descripcion = string;
	}

	/**
	 * @param string
	 */
	public void setEstado(String string) {
		estado = string;
	}

	/**
	 * @param long1
	 */
	public void setIdPeticion(Long long1) {
		idPeticion = long1;
	}

	/**
	 * @param fecha
	 */
	public void setMarca_hora(Fecha fecha) {
		marca_hora = fecha;
	}

}
