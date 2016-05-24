package co.com.telefonica.acs;
import java.io.Serializable;

public class ModemDTO implements Serializable{

	
	private String serialNumber;
	private String OUI;
	private String productClass;
	private String suscriberID;
	private String suscriberIDNew;
	private String fatherEmail;
	private String velocidad;
	private String idAtiempo;
	private String operacion;
	
	
	
	public String getSuscriberIDNew() {
		return suscriberIDNew;
	}
	public void setSuscriberIDNew(String suscriberIDNew) {
		this.suscriberIDNew = suscriberIDNew;
	}
	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getOUI() {
		return OUI;
	}
	public void setOUI(String oUI) {
		OUI = oUI;
	}
	public String getProductClass() {
		return productClass;
	}
	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}
	public String getSuscriberID() {
		return suscriberID;
	}
	public void setSuscriberID(String suscriberID) {
		this.suscriberID = suscriberID;
	}
	public String getFatherEmail() {
		return fatherEmail;
	}
	public void setFatherEmail(String fatherEmail) {
		this.fatherEmail = fatherEmail;
	}

	public String getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(String velocidad) {
		this.velocidad = velocidad;
	}
	public String getIdAtiempo() {
		return idAtiempo;
	}
	public void setIdAtiempo(String idAtiempo) {
		this.idAtiempo = idAtiempo;
	}

	
	
}
