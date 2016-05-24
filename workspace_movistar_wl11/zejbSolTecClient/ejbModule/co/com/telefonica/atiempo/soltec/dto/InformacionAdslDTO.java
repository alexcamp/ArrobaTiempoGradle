/*
 * Created on 10-02-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.dto;

import co.com.telefonica.atiempo.utiles.DataTransferObject;

/**
 * @author Caudillo-Movil
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InformacionAdslDTO implements DataTransferObject {

	/**
	 * 
	 */
	public InformacionAdslDTO() {
		super();
	}
		
	private String puerto;
	private String post;
	private String adsl;
	private String mascaraLan;
	private String direcIpDslam;
	private String direcIpWan;
	private String direcIpLan;
	private String frame;
	private String numPc;
	private String numTarj;
	private String numUsb;
	private String sistOp;
//	private String cna;
//	private String descripcion;

	private String slot;
	private String vpiVciCliente;
	private String vpiVciRed;
	
	private String usuarioAcc;
	private String ram;
	private String discoDuro;
	
	private String fatherEmail;
	
	//Para CDS
	private String direccElectronica;
	private String serviceReferenceId;
	
	//Para Dr Speedy
	private String rangoHora;
	private String motivoVisita;
	
	/*
	 *	CR - 00026148 - Jun 29, 2009
	 *		- German P.
	 */
	private String modem_marca;
	private String serial;
	private String modelo;
	
	
	/**
	 * @return
	 */
	public String getAdsl() {
		return adsl;
	}

	/**
	 * @return
	 */
//	public String getCna() {
//		return cna;
//	}
//
//	/**
//	 * @return
//	 */
//	public String getDescripcion() {
//		return descripcion;
//	}

	/**
	 * @return
	 */
	public String getDirecIpDslam() {
		if (direcIpDslam != null){
			direcIpDslam=direcIpDslam.trim();
		}
		return direcIpDslam;
	}

	/**
	 * @return
	 */
	public String getDirecIpWan() {
		return direcIpWan;
	}

	/**
	 * @return
	 */
	public String getFrame() {
		return frame;
	}

	/**
	 * @return
	 */
	public String getMascaraLan() {
		return mascaraLan;
	}

	/**
	 * @return
	 */
	public String getNumPc() {
		return numPc;
	}

	/**
	 * @return
	 */
	public String getNumTarj() {
		return numTarj;
	}

	/**
	 * @return
	 */
	public String getNumUsb() {
		return numUsb;
	}

	/**
	 * @return
	 */
	public String getPost() {
		return post;
	}

	/**
	 * @return
	 */
	public String getPuerto() {
		return puerto;
	}

	/**
	 * @return
	 */
	public String getSistOp() {
		return sistOp;
	}

	/**
	 * @param string
	 */
	public void setAdsl(String string) {
		adsl = string;
	}

	/**
	 * @param string
	 */
//	public void setCna(String string) {
//		cna = string;
//	}
//
//	/**
//	 * @param string
//	 */
//	public void setDescripcion(String string) {
//		descripcion = string;
//	}

	/**
	 * @param string
	 */
	public void setDirecIpDslam(String string) {
		direcIpDslam = string;
	}

	/**
	 * @param string
	 */
	public void setDirecIpWan(String string) {
		direcIpWan = string;
	}

	/**
	 * @param string
	 */
	public void setFrame(String string) {
		frame = string;
	}

	/**
	 * @param string
	 */
	public void setMascaraLan(String string) {
		mascaraLan = string;
	}

	/**
	 * @param string
	 */
	public void setNumPc(String string) {
		numPc = string;
	}

	/**
	 * @param string
	 */
	public void setNumTarj(String string) {
		numTarj = string;
	}

	/**
	 * @param string
	 */
	public void setNumUsb(String string) {
		numUsb = string;
	}

	/**
	 * @param string
	 */
	public void setPost(String string) {
		post = string;
	}

	/**
	 * @param string
	 */
	public void setPuerto(String string) {
		puerto = string;
	}

	/**
	 * @param string
	 */
	public void setSistOp(String string) {
		sistOp = string;
	}

	/**
	 * @return
	 */
	public String getSlot() {
		return slot;
	}

	/**
	 * @return
	 */
	public String getVpiVciCliente() {
		return vpiVciCliente;
	}

	/**
	 * @return
	 */
	public String getVpiVciRed() {
		return vpiVciRed;
	}

	/**
	 * @param string
	 */
	public void setSlot(String string) {
		slot = string;
	}

	/**
	 * @param string
	 */
	public void setVpiVciCliente(String string) {
		vpiVciCliente = string;
	}

	/**
	 * @param string
	 */
	public void setVpiVciRed(String string) {
		vpiVciRed = string;
	}

	/**
	 * @return
	 */
	public String getDirecIpLan() {
		return direcIpLan;
	}

	/**
	 * @param string
	 */
	public void setDirecIpLan(String string) {
		direcIpLan = string;
	}

	/**
	 * @return
	 */
	public String getDiscoDuro() {
		return discoDuro;
	}

	/**
	 * @return
	 */
	public String getRam() {
		return ram;
	}

	/**
	 * @return
	 */
	public String getUsuarioAcc() {
		return usuarioAcc;
	}

	/**
	 * @param string
	 */
	public void setDiscoDuro(String string) {
		discoDuro = string;
	}

	/**
	 * @param string
	 */
	public void setRam(String string) {
		ram = string;
	}

	/**
	 * @param string
	 */
	public void setUsuarioAcc(String string) {
		usuarioAcc = string;
	}

	/**
	 * @return
	 */
	public String getFatherEmail() {
		return fatherEmail;
	}

	/**
	 * @param string
	 */
	public void setFatherEmail(String string) {
		fatherEmail = string;
	}

	/**
	 * @return
	 */
	public String getDireccElectronica() {
		return direccElectronica;
	}

	/**
	 * @param string
	 */
	public void setDireccElectronica(String string) {
		direccElectronica = string;
	}

	/**
	 * @return
	 */
	public String getServiceReferenceId() {
		return serviceReferenceId;
	}

	/**
	 * @param string
	 */
	public void setServiceReferenceId(String string) {
		serviceReferenceId = string;
	}

	/**
	 * @return
	 */
	public String getMotivoVisita() {
		return motivoVisita;
	}

	/**
	 * @return
	 */
	public String getRangoHora() {
		return rangoHora;
	}

	/**
	 * @param string
	 */
	public void setMotivoVisita(String string) {
		motivoVisita = string;
	}

	/**
	 * @param string
	 */
	public void setRangoHora(String string) {
		rangoHora = string;
	}

	/**
	 * @return Returns the modelo.
	 */
	public String getModelo() {
		return modelo;
	}
	/**
	 * @param modelo The modelo to set.
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	/**
	 * @return Returns the modem_marca.
	 */
	public String getModem_marca() {
		return modem_marca;
	}
	/**
	 * @param modem_marca The modem_marca to set.
	 */
	public void setModem_marca(String modem_marca) {
		this.modem_marca = modem_marca;
	}
	/**
	 * @return Returns the serial.
	 */
	public String getSerial() {
		return serial;
	}
	/**
	 * @param serial The serial to set.
	 */
	public void setSerial(String serial) {
		this.serial = serial;
	}
}
