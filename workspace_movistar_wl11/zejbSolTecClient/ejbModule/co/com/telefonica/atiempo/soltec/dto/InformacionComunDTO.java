package co.com.telefonica.atiempo.soltec.dto;

import co.com.telefonica.atiempo.utiles.DataTransferObject;
/**
 * @author admin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InformacionComunDTO implements DataTransferObject {

	public Long clienteId;
	public String rut;
	public String nombre;
	public String primerApellido;
	public String segundoApellido;
	public Long codigoSegmento;
	public String direccion;
	public String estadoLinea;
	public String tipoLinea;
	public String telefono;
	public String idProdCom;
	public String prodCom;
	
	

	/**
	 * @return
	 */
	public Long getClienteId() {
		return clienteId;
	}
	
	public void setClienteId(Long clienteId){
		this.clienteId=clienteId;
	}

	/**
	 * @return
	 */
	public String getRut() {
		return rut;
	}
	
	public void setRut(String rut){
		this.rut=rut;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

	/**
	 * @return
	 */
	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	/**
	 * @return
	 */
	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	
	/**
	 * @return
	 */
	public Long getCodigoSegmento() {
		return codigoSegmento;
	}
	
	/**
	 * @param long1
	 */
	public void setCodigoSegmento(Long long1) {
		codigoSegmento = long1;
	}
	/**
	 * @return
	 */
	public String getDireccion() {
		return direccion;
	}
	
	/**
	 * @param long1
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}	

	/**
	 * @return
	 */
	public String getEstadoLinea() {
		return estadoLinea;
	}
	
	/**
	 * @param string
	 */
	public void setEstadoLinea(String estadoLinea) {
		this.estadoLinea = estadoLinea;
	}
	/**
	 * @return
	 */
	public String getTipoLinea() {
		return tipoLinea;
	}
	
	/**
	 * @param string
	 */
	public void setTipoLinea(String tipoLinea) {
		this.tipoLinea = tipoLinea;
	}

	/**
	 * @return
	 */
	public String getTelefono() {
		return telefono;
	}
	
	/**
	 * @param string
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return
	 */
	public String getIdProdCom() {
		return idProdCom;
	}
	
	/**
	 * @param string
	 */
	public void setIdProdCom(String idProdCom) {
		this.idProdCom = idProdCom;
	}

	/**
	 * @return
	 */
	public String getProdCom() {
		return prodCom;
	}
	
	/**
	 * @param string
	 */
	public void setProdCom(String prodCom) {
		this.prodCom = prodCom;
	}
	
	public InformacionComunDTO obtenerInformacionCliente(){
		
		InformacionComunDTO informacionCliente = new InformacionComunDTO();
		
				informacionCliente.setClienteId(Long.valueOf("10012976"));
				informacionCliente.setNombre("Maria Luisa");
				informacionCliente.setPrimerApellido("Castro");
				informacionCliente.setSegundoApellido("Pantoja");
				informacionCliente.setRut("48108388-5");
				informacionCliente.setCodigoSegmento(Long.valueOf("10012976"));
				informacionCliente.setDireccion("Ricardo Lyon 548");
				informacionCliente.setEstadoLinea("Abierto");
				informacionCliente.setTipoLinea("Reclamo");
				informacionCliente.setIdProdCom("12345");
				informacionCliente.setProdCom("Línea Básica");
				informacionCliente.setTelefono("4154352");
				
				return informacionCliente;
	}
	
}