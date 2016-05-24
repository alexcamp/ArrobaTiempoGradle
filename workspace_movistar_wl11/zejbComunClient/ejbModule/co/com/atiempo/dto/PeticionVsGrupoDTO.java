package co.com.atiempo.dto;

public class PeticionVsGrupoDTO {

	private Long nroPeticion;
	private Integer idGrupo; 

	/**
	 * @return
	 */
	public Integer getIdGrupo()
	{
		return idGrupo;
	}

	/**
	 * @return
	 */
	public Long getNroPeticion() {
		return nroPeticion;
	}

	/**
	 * @param integer
	 */
	public void setIdGrupo(Integer integer) {
		idGrupo = integer;
	}

	/**
	 * @param long1
	 */
	public void setNroPeticion(Long long1) {
		nroPeticion = long1;
	}

}
