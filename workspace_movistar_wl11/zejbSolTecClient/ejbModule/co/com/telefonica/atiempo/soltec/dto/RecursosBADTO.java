/*
 * Created on 16-05-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.dto;

/**
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RecursosBADTO {

	private Long idConector;
	private Integer codError;
	private String puertoActual;
	private String postActual;
	private String adslActual;
	private String mascLanActual;
	private String dirIpDslamActual;
	private String dirIpWanActual;
	private String frameActual;
	private String puertoNvo;
	private String postNvo;
	private String adslNvo;
	private String mascLanNva;
	private String dirIpDslamNva;
	private String dirIpWanNva;
	private String frameNvo;
	private Long correlativo;
	private Long petiNumero;
	private String vpiVciActual;
	private String vpiVciRedActual;
	private String slotActual;
	private String vpiVciNvo;
	private String dirIpLanNva;
	private String vpiVciRedNvo;
	private String slotNvo;
	private String descError;
	private String fatherEmail;
	private String dirIpLanActual;//CR- 24100 agonz 30 junio 2009 correccion AT-2378
	
	/*
	 *	CR - 00026148 - Jun 29, 2009
	 *		- German P.
	 */
	private String modem_marca;
	private String serial;
	private String modelo;
	private String tipoModem;
	

	/**
     * @return Returns the dirIpLanActual.
     */
    public String getDirIpLanActual() {
        return dirIpLanActual;
    }
    /**
     * @param dirIpLanActual The dirIpLanActual to set.
     */
    public void setDirIpLanActual(String dirIpLanActual) {
        this.dirIpLanActual = dirIpLanActual;
    }
	/**
	 * @return
	 */
	public String getAdslActual() {
		return adslActual;
	}

	/**
	 * @return
	 */
	public String getAdslNvo() {
		return adslNvo;
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
	public Long getCorrelativo() {
		return correlativo;
	}

	/**
	 * @return
	 */
	public String getDescError() {
		return descError;
	}

	/**
	 * @return
	 */
	public String getDirIpDslamActual() {
		return dirIpDslamActual;
	}

	/**
	 * @return
	 */
	public String getDirIpDslamNva() {
		return dirIpDslamNva;
	}

	/**
	 * @return
	 */
	public String getDirIpWanActual() {
		return dirIpWanActual;
	}

	/**
	 * @return
	 */
	public String getDirIpWanNva() {
		return dirIpWanNva;
	}

	/**
	 * @return
	 */
	public String getFrameActual() {
		return frameActual;
	}

	/**
	 * @return
	 */
	public String getFrameNvo() {
		return frameNvo;
	}

	/**
	 * @return
	 */
	public Long getIdConector() {
		return idConector;
	}

	/**
	 * @return
	 */
	public String getMascLanActual() {
		return mascLanActual;
	}

	/**
	 * @return
	 */
	public String getMascLanNva() {
		return mascLanNva;
	}

	/**
	 * @return
	 */
	public Long getPetiNumero() {
		return petiNumero;
	}

	/**
	 * @return
	 */
	public String getPostActual() {
		return postActual;
	}

	/**
	 * @return
	 */
	public String getPostNvo() {
		return postNvo;
	}

	/**
	 * @return
	 */
	public String getPuertoActual() {
		return puertoActual;
	}

	/**
	 * @return
	 */
	public String getPuertoNvo() {
		return puertoNvo;
	}

	/**
	 * @return
	 */
	public String getSlotActual() {
		return slotActual;
	}

	/**
	 * @return
	 */
	public String getSlotNvo() {
		return slotNvo;
	}

	/**
	 * @return
	 */
	public String getVpiVciActual() {
		return vpiVciActual;
	}

	/**
	 * @return
	 */
	public String getVpiVciNvo() {
		return vpiVciNvo;
	}

	/**
	 * @return
	 */
	public String getVpiVciRedActual() {
		return vpiVciRedActual;
	}

	/**
	 * @return
	 */
	public String getVpiVciRedNvo() {
		return vpiVciRedNvo;
	}

	/**
	 * @param string
	 */
	public void setAdslActual(String string) {
		adslActual = string;
	}

	/**
	 * @param string
	 */
	public void setAdslNvo(String string) {
		adslNvo = string;
	}

	/**
	 * @param integer
	 */
	public void setCodError(Integer integer) {
		codError = integer;
	}

	/**
	 * @param long1
	 */
	public void setCorrelativo(Long long1) {
		correlativo = long1;
	}

	/**
	 * @param string
	 */
	public void setDescError(String string) {
		descError = string;
	}

	/**
	 * @param string
	 */
	public void setDirIpDslamActual(String string) {
		dirIpDslamActual = string;
	}

	/**
	 * @param string
	 */
	public void setDirIpDslamNva(String string) {
		dirIpDslamNva = string;
	}

	/**
	 * @param string
	 */
	public void setDirIpWanActual(String string) {
		dirIpWanActual = string;
	}

	/**
	 * @param string
	 */
	public void setDirIpWanNva(String string) {
		dirIpWanNva = string;
	}

	/**
	 * @param string
	 */
	public void setFrameActual(String string) {
		frameActual = string;
	}

	/**
	 * @param string
	 */
	public void setFrameNvo(String string) {
		frameNvo = string;
	}

	/**
	 * @param long1
	 */
	public void setIdConector(Long long1) {
		idConector = long1;
	}

	/**
	 * @param string
	 */
	public void setMascLanActual(String string) {
		mascLanActual = string;
	}

	/**
	 * @param string
	 */
	public void setMascLanNva(String string) {
		mascLanNva = string;
	}

	/**
	 * @param long1
	 */
	public void setPetiNumero(Long long1) {
		petiNumero = long1;
	}

	/**
	 * @param string
	 */
	public void setPostActual(String string) {
		postActual = string;
	}

	/**
	 * @param string
	 */
	public void setPostNvo(String string) {
		postNvo = string;
	}

	/**
	 * @param string
	 */
	public void setPuertoActual(String string) {
		puertoActual = string;
	}

	/**
	 * @param string
	 */
	public void setPuertoNvo(String string) {
		puertoNvo = string;
	}

	/**
	 * @param string
	 */
	public void setSlotActual(String string) {
		slotActual = string;
	}

	/**
	 * @param string
	 */
	public void setSlotNvo(String string) {
		slotNvo = string;
	}

	/**
	 * @param string
	 */
	public void setVpiVciActual(String string) {
		vpiVciActual = string;
	}

	/**
	 * @param string
	 */
	public void setVpiVciNvo(String string) {
		vpiVciNvo = string;
	}

	/**
	 * @param string
	 */
	public void setVpiVciRedActual(String string) {
		vpiVciRedActual = string;
	}

	/**
	 * @param string
	 */
	public void setVpiVciRedNvo(String string) {
		vpiVciRedNvo = string;
	}

	/**
	 * @return
	 */
	public String getDirIpLanNva() {
		return dirIpLanNva;
	}

	/**
	 * @param string
	 */
	public void setDirIpLanNva(String string) {
		dirIpLanNva = string;
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
	/**
	 * @return Returns the tipoModem.
	 */
	public String getTipoModem() {
		return tipoModem;
	}
	/**
	 * @param tipoModem The tipoModem to set.
	 */
	public void setTipoModem(String tipoModem) {
		this.tipoModem = tipoModem;
	}
}
