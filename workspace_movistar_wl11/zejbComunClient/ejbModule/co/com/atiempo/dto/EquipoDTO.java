package co.com.atiempo.dto;


public class EquipoDTO {

	private String tipoEquipo = "";
	private String idDeco = "";
	private String idTarjeta = "";
	private String estado = "";
	private Long idContratista;
	private String contratista = "";
	private String observacion = "";
	private String idOpCo ="";
	private String descOperacionComer;
	private Integer codError; 
	private Integer idEstado;
	private String fechaMarcaHora;
	private String ddtvSerial="";

	private String decoReference;
	//TODO PVR se agrego reference al DTO validar donde se  carga este DTO

//	 TODO: AT-1035 - PVR - Inicio - guido
	private Integer accion;
//	 guido AT-1035 - Fin
	
	private boolean marcaPrint;

	/*
	 * CR 00026144 - 16/06/2009
	 * 		Se agrega atributo marca - Germán P. 
	 */
	private String marca;
	
	public EquipoDTO()
	{
		marcaPrint=false;
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
	 * @return
	 */
	public String getTipoEquipo() {
		return tipoEquipo;
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
	public String getIdOpCo() {
		return idOpCo;
	}

	/**
	 * @param string
	 */
	public void setIdOpCo(String string) {
		idOpCo = string;
	}

	/**
	 * @return
	 */
	public String getDescOperacionComer() {
		return descOperacionComer;
	}

	/**
	 * @param string
	 */
	public void setDescOperacionComer(String string) {
		descOperacionComer = string;
	}

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
	public Integer getCodError() {
		return codError;
	}

	/**
	 * @param integer
	 */
	public void setCodError(Integer integer) {
		codError = integer;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj)
	{
		if ((! (obj instanceof EquipoDTO)) || (obj == null))
		   return (false) ;
        
		EquipoDTO otro = (EquipoDTO) obj ;
        if(idDeco==null || otro.getIdDeco()==null || idTarjeta==null || otro.getIdTarjeta()==null)
        	return false;
	   return (idDeco.equals(otro.getIdDeco()) && this.idTarjeta.equals(otro.getIdTarjeta()) ) ;
	}

	public String toString()
	{
		return "Deco:"+this.idDeco+" - Tarjeta:" +this.idTarjeta;
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
	public void setIdEstado(Integer integer) {
		idEstado = integer;
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
	 * @return
	 */
	public boolean isMarcaPrint() {
		return marcaPrint;
	}

	/**
	 * @param b
	 */
	public void setMarcaPrint(boolean b) {
		marcaPrint = b;
	}

//	 TODO: AT-1035 - PVR - Inicio - guido
	/**
	 * @return Returns the accion.
	 */
	public Integer getAccion() {
		return accion;
	}
	/**
	 * @param accion The accion to set.
	 */
	public void setAccion(Integer accion) {
		this.accion = accion;
	}
//	 guido AT-1035 - Fin

	/**
	 * @return Returns the marca.
	 */
	public String getMarca() {
		if (marca == null){
			return "";	 
		}
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
