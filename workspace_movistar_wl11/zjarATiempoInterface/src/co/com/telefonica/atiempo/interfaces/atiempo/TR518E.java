/*
 * Creado el 24/12/2013
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class TR518E extends RequestHeaderAgendaSC{

	private long atiempoRequestNumber;
	private long atisRequestNumber;
	private long newCategory;
	private long newSubCategory;
	private long odsNumber;
	private long productServiceCode;
	private int operationType;
	private long phoneNumber;
	private long previousNumber;
	private String requestDate;
	private long requestPbx;
	private String specialService;
	private String actionCode;
	private Collection specialServicesType;		
	private String centralDescription;
	private String remoteDescription;		
	private String len;
	private String primaryDistributor;
	private long city;
	private long newPhoneNumber;
	private long numberClient;
	private long previousCategory;
	private long previousSubCategory;

	public int hashCode(){
		return super.hashCode();
	}
	
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR518E) {
				TR518E other = (TR518E) arg0;
				return super.equals(arg0)
					&& (atiempoRequestNumber == other.atiempoRequestNumber)
					&& (atisRequestNumber == other.atisRequestNumber)
					&& (newCategory == other.newCategory)
					&& (newSubCategory == other.newSubCategory)
					&& (odsNumber == other.odsNumber)
					&& (productServiceCode == other.productServiceCode)
					&& (operationType == other.operationType)
					&& (phoneNumber == other.phoneNumber)
					&& (previousNumber == other.previousNumber)
					&& (requestPbx == other.requestPbx)
					&& (city == other.city)
					&& (newPhoneNumber == other.newPhoneNumber)
					&& (numberClient == other.numberClient)
					&& (previousCategory == other.previousCategory)
					&& (previousSubCategory == other.previousSubCategory)
						&& (specialServicesType == other.specialServicesType)
					&& EqualityUtilities.equals(requestDate,other.requestDate)
					&& EqualityUtilities.equals(specialService,other.specialService)
					&& EqualityUtilities.equals(actionCode,other.actionCode)
					&& EqualityUtilities.equals(centralDescription,other.centralDescription)
					&& EqualityUtilities.equals(remoteDescription,other.remoteDescription)
					&& EqualityUtilities.equals(len,other.len)
					&& EqualityUtilities.equals(primaryDistributor,other.primaryDistributor)
					;

			}
			return false;
		}


	public long getProductServiceCode() {
		return productServiceCode;
	}
	public void setProductServiceCode(long productServiceCode) {
		this.productServiceCode = productServiceCode;
	}
	/**
	 * @return Devuelve actionCode.
	 */
	public String getActionCode() {
		return actionCode;
	}
	/**
	 * @param actionCode El actionCode a establecer.
	 */
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	/**
	 * @return Devuelve atiempoRequestNumber.
	 */
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	/**
	 * @param atiempoRequestNumber El atiempoRequestNumber a establecer.
	 */
	public void setAtiempoRequestNumber(long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	/**
	 * @return Devuelve atisRequestNumber.
	 */
	public long getAtisRequestNumber() {
		return atisRequestNumber;
	}
	/**
	 * @param atisRequestNumber El atisRequestNumber a establecer.
	 */
	public void setAtisRequestNumber(long atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
	/**
	 * @return Devuelve centralDescription.
	 */
	public String getCentralDescription() {
		return centralDescription;
	}
	/**
	 * @param centralDescription El centralDescription a establecer.
	 */
	public void setCentralDescription(String centralDescription) {
		this.centralDescription = centralDescription;
	}
	/**
	 * @return Devuelve city.
	 */
	public long getCity() {
		return city;
	}
	/**
	 * @param city El city a establecer.
	 */
	public void setCity(long city) {
		this.city = city;
	}
	/**
	 * @return Devuelve len.
	 */
	public String getLen() {
		return len;
	}
	/**
	 * @param len El len a establecer.
	 */
	public void setLen(String len) {
		this.len = len;
	}
	/**
	 * @return Devuelve newCategory.
	 */
	public long getNewCategory() {
		return newCategory;
	}
	/**
	 * @param newCategory El newCategory a establecer.
	 */
	public void setNewCategory(long newCategory) {
		this.newCategory = newCategory;
	}
	/**
	 * @return Devuelve newPhoneNumber.
	 */
	public long getNewPhoneNumber() {
		return newPhoneNumber;
	}
	/**
	 * @param newPhoneNumber El newPhoneNumber a establecer.
	 */
	public void setNewPhoneNumber(long newPhoneNumber) {
		this.newPhoneNumber = newPhoneNumber;
	}
	/**
	 * @return Devuelve newSubCategory.
	 */
	public long getNewSubCategory() {
		return newSubCategory;
	}
	/**
	 * @param newSubCategory El newSubCategory a establecer.
	 */
	public void setNewSubCategory(long newSubCategory) {
		this.newSubCategory = newSubCategory;
	}
	/**
	 * @return Devuelve numberClient.
	 */
	public long getNumberClient() {
		return numberClient;
	}
	/**
	 * @param numberClient El numberClient a establecer.
	 */
	public void setNumberClient(long numberClient) {
		this.numberClient = numberClient;
	}
	/**
	 * @return Devuelve odsNumber.
	 */
	public long getOdsNumber() {
		return odsNumber;
	}
	/**
	 * @param odsNumber El odsNumber a establecer.
	 */
	public void setOdsNumber(long odsNumber) {
		this.odsNumber = odsNumber;
	}
	/**
	 * @return Devuelve operationType.
	 */
	public int getOperationType() {
		return operationType;
	}
	/**
	 * @param operationType El operationType a establecer.
	 */
	public void setOperationType(int operationType) {
		this.operationType = operationType;
	}
	/**
	 * @return Devuelve phoneNumber.
	 */
	public long getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber El phoneNumber a establecer.
	 */
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return Devuelve previousCategory.
	 */
	public long getPreviousCategory() {
		return previousCategory;
	}
	/**
	 * @param previousCategory El previousCategory a establecer.
	 */
	public void setPreviousCategory(long previousCategory) {
		this.previousCategory = previousCategory;
	}
	/**
	 * @return Devuelve previousNumber.
	 */
	public long getPreviousNumber() {
		return previousNumber;
	}
	/**
	 * @param previousNumber El previousNumber a establecer.
	 */
	public void setPreviousNumber(long previousNumber) {
		this.previousNumber = previousNumber;
	}
	/**
	 * @return Devuelve previousSubCategory.
	 */
	public long getPreviousSubCategory() {
		return previousSubCategory;
	}
	/**
	 * @param previousSubCategory El previousSubCategory a establecer.
	 */
	public void setPreviousSubCategory(long previousSubCategory) {
		this.previousSubCategory = previousSubCategory;
	}
	/**
	 * @return Devuelve primaryDistributor.
	 */
	public String getPrimaryDistributor() {
		return primaryDistributor;
	}
	/**
	 * @param primaryDistributor El primaryDistributor a establecer.
	 */
	public void setPrimaryDistributor(String primaryDistributor) {
		this.primaryDistributor = primaryDistributor;
	}
	/**
	 * @return Devuelve remoteDescription.
	 */
	public String getRemoteDescription() {
		return remoteDescription;
	}
	/**
	 * @param remoteDescription El remoteDescription a establecer.
	 */
	public void setRemoteDescription(String remoteDescription) {
		this.remoteDescription = remoteDescription;
	}
	/**
	 * @return Devuelve requestDate.
	 */
	public Date getRequestDate() {
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			return formateador.parse(requestDate);
		}
		catch (Exception e)
		{
			return new Date();
		}
	}
	/**
	 * @param requestDate El requestDate a establecer.
	 */
	public void setRequestDate(Date date) {
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		requestDate = formateador.format(date);
	}
	public String getRequestDateString() {
		return requestDate;		
	}
	public void setRequestDateString(String string) {
		requestDate = string;
	}
	/**
	 * @return Devuelve requestPbx.
	 */
	public long getRequestPbx() {
		return requestPbx;
	}
	/**
	 * @param requestPbx El requestPbx a establecer.
	 */
	public void setRequestPbx(long requestPbx) {
		this.requestPbx = requestPbx;
	}

	/**
	 * @return Devuelve specialService.
	 */
	public String getSpecialService() {
		return specialService;
	}
	/**
	 * @param specialService El specialService a establecer.
	 */
	public void setSpecialService(String specialService) {
		this.specialService = specialService;
	}
	/**
	 * @return Devuelve specialServicesType.
	 */
	public Collection getSpecialServicesType() {
		return specialServicesType;
	}
	/**
	 * @param specialServicesType El specialServicesType a establecer.
	 */
	public void setSpecialServicesType(Collection specialServicesType) {
		this.specialServicesType = specialServicesType;
	}
}
