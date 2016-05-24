/*
 * Created on Apr 21, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.util.Collection;
import java.util.Date;
import java.text.SimpleDateFormat;
import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author 806713
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TR510E extends RequestHeaderAgendaSC{

	private String requestDate;
	private Long enhancementNumber;
	private Long atiempoRequestNumber;
	private Long zone;
	private Long sector;
	private Long block;
	private String blockSide;
	private Long spaceNumber;
	private String subCity;
	private Long requestPbx;
	private Long previousPhoneNumber;
	private Long numberClient;	
	private String clientDocument;
	private String clientName;
	private String installAddress;
	private Long comercialProductType;
	private Long comercialProductSubType;
	private Long productServiceCode;
	private Long comercialOperation;
	private String pathType;
	private Integer pathNumber;
	private String firstPathCharacters;
	private String secondPathCharacters;
	private Character pathZone;
	private String pathType2;
	private Integer pathNumber2;
	private String firstPathCharacters2;
	private String secondPathCharacters2;
	private Character pathZone2;
	private Long atisRequestNumber;
	private Integer phoneNumber;
	private String addressNumber;
	private Character addressCharacters;
	private Integer department;
	private Integer city;
	private Long useType;	
	private String placeType1;
	private String placeNumber1;
	private String placeType2;
	private String placeNumber2;
	private String placeType3;
	private String placeNumber3;
	private Integer odsNumber;
	private String productServiceFamily;
	private Collection specialServices;	
	private String projectCode;
	
	/**
	 * @return Returns the projectCode.
	 */
	public String getProjectCode() {
		return projectCode;
	}
	/**
	 * @param projectCode The projectCode to set.
	 */
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public int hashCode(){
		return super.hashCode();
	}
	
	
	public Integer getOdsNumber() {
		return odsNumber;
	}
	public void setOdsNumber(Integer odsNumber) {
		this.odsNumber = odsNumber;
	}
	/**
	 * @param addressCharacters The addressCharacters to set.
	 */
	public void setAddressCharacters(Character addressCharacters) {
		this.addressCharacters = addressCharacters;
	}
	/**
	 * @param addressNumber The addressNumber to set.
	 */
	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}
	/**
	 * @param atiempoRequestNumber The atiempoRequestNumber to set.
	 */
	public void setAtiempoRequestNumber(Long atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	/**
	 * @param atisRequestNumber The atisRequestNumber to set.
	 */
	public void setAtisRequestNumber(Long atisRequestNumber) {
		this.atisRequestNumber = atisRequestNumber;
	}
	/**
	 * @param block The block to set.
	 */
	public void setBlock(Long block) {
		this.block = block;
	}
	/**
	 * @param city The city to set.
	 */
	public void setCity(Integer city) {
		this.city = city;
	}
	/**
	 * @param comercialOperation The comercialOperation to set.
	 */
	public void setComercialOperation(Long comercialOperation) {
		this.comercialOperation = comercialOperation;
	}
	/**
	 * @param comercialProductSubType The comercialProductSubType to set.
	 */
	public void setComercialProductSubType(Long comercialProductSubType) {
		this.comercialProductSubType = comercialProductSubType;
	}
	/**
	 * @param comercialProductType The comercialProductType to set.
	 */
	public void setComercialProductType(Long comercialProductType) {
		this.comercialProductType = comercialProductType;
	}
	/**
	 * @param department The department to set.
	 */
	public void setDepartment(Integer department) {
		this.department = department;
	}
	/**
	 * @param enhancementNumber The enhancementNumber to set.
	 */
	public void setEnhancementNumber(Long enhancementNumber) {
		this.enhancementNumber = enhancementNumber;
	}
	/**
	 * @param numberClient The numberClient to set.
	 */
	public void setNumberClient(Long numberClient) {
		this.numberClient = numberClient;
	}
	/**
	 * @param pathNumber The pathNumber to set.
	 */
	public void setPathNumber(Integer pathNumber) {
		this.pathNumber = pathNumber;
	}
	/**
	 * @param pathNumber2 The pathNumber2 to set.
	 */
	public void setPathNumber2(Integer pathNumber2) {
		this.pathNumber2 = pathNumber2;
	}
	/**
	 * @param pathZone The pathZone to set.
	 */
	public void setPathZone(Character pathZone) {
		this.pathZone = pathZone;
	}
	/**
	 * @param pathZone2 The pathZone2 to set.
	 */
	public void setPathZone2(Character pathZone2) {
		this.pathZone2 = pathZone2;
	}
	/**
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @param previousPhoneNumber The previousPhoneNumber to set.
	 */
	public void setPreviousPhoneNumber(Long previousPhoneNumber) {
		this.previousPhoneNumber = previousPhoneNumber;
	}
	/**
	 * @param productServiceCode The productServiceCode to set.
	 */
	public void setProductServiceCode(Long productServiceCode) {
		this.productServiceCode = productServiceCode;
	}
	/**
	 * @param requestDate The requestDate to set.
	 */
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	/**
	 * @param requestPbx The requestPbx to set.
	 */
	public void setRequestPbx(Long requestPbx) {
		this.requestPbx = requestPbx;
	}
	/**
	 * @param sector The sector to set.
	 */
	public void setSector(Long sector) {
		this.sector = sector;
	}
	/**
	 * @param spaceNumber The spaceNumber to set.
	 */
	public void setSpaceNumber(Long spaceNumber) {
		this.spaceNumber = spaceNumber;
	}
	/**
	 * @param useType The useType to set.
	 */
	public void setUseType(Long useType) {
		this.useType = useType;
	}
	/**
	 * @param zone The zone to set.
	 */
	public void setZone(Long zone) {
		this.zone = zone;
	}
	
	public String getProductServiceFamily() {
		return productServiceFamily;
	}
	public void setProductServiceFamily(String productServiceFamily) {
		this.productServiceFamily = productServiceFamily;
	}
	
	/**
	 * @return
	 */
	public Collection getSpecialServices() {
		return specialServices;
	}
	
	/**
	 * @param collection
	 */
	public void setSpecialServices(Collection collection) {
		specialServices = collection;
	}
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR510E) {
				TR510E other = (TR510E) arg0;
				return super.equals(arg0)
					&& EqualityUtilities.equals(specialServices,other.specialServices)
					&& EqualityUtilities.equals(productServiceFamily,other.productServiceFamily)
					&& EqualityUtilities.equals(enhancementNumber,other.enhancementNumber)
					&& EqualityUtilities.equals(atiempoRequestNumber,other.atiempoRequestNumber)
					&& EqualityUtilities.equals(zone,other.zone)
					&& EqualityUtilities.equals(sector,other.sector)
					&& EqualityUtilities.equals(block,other.block)
					&& EqualityUtilities.equals(requestPbx,other.requestPbx)	
					&& EqualityUtilities.equals(numberClient,other.numberClient)
					&& EqualityUtilities.equals(spaceNumber,other.spaceNumber)
					&& EqualityUtilities.equals(previousPhoneNumber,other.previousPhoneNumber)
					&& EqualityUtilities.equals(comercialProductType,other.comercialProductType)
					&& EqualityUtilities.equals(comercialProductSubType,other.comercialProductSubType)
					&& EqualityUtilities.equals(productServiceCode,other.productServiceCode)
					&& EqualityUtilities.equals(comercialOperation,other.comercialOperation)
					&& EqualityUtilities.equals(pathNumber,other.pathNumber)
					&& EqualityUtilities.equals(pathNumber2,other.pathNumber2)
					&& EqualityUtilities.equals(pathNumber2,other.pathNumber2)
					&& EqualityUtilities.equals(pathZone2,other.pathZone2)
					&& EqualityUtilities.equals(atisRequestNumber,other.atisRequestNumber)
					&& EqualityUtilities.equals(phoneNumber,other.phoneNumber)
					&& EqualityUtilities.equals(addressNumber,other.addressNumber)
					&& EqualityUtilities.equals(addressCharacters,other.addressCharacters)
					&& EqualityUtilities.equals(department,other.department)
					&& EqualityUtilities.equals(city,other.city)
					&& EqualityUtilities.equals(useType,other.useType)				
					&& EqualityUtilities.equals(blockSide,other.blockSide)
					&& EqualityUtilities.equals(subCity,other.subCity)					
					&& EqualityUtilities.equals(requestDate,other.requestDate)					
					&& EqualityUtilities.equals(clientDocument,other.clientDocument)
					&& EqualityUtilities.equals(clientName,other.clientName)
					&& EqualityUtilities.equals(installAddress,other.installAddress)
					&& EqualityUtilities.equals(pathType,other.pathType)
					&& EqualityUtilities.equals(firstPathCharacters,other.firstPathCharacters)
					&& EqualityUtilities.equals(secondPathCharacters,other.secondPathCharacters)
					&& EqualityUtilities.equals(pathZone,other.pathZone)
					&& EqualityUtilities.equals(pathType2,other.pathType2)
					&& EqualityUtilities.equals(firstPathCharacters2,other.firstPathCharacters2)
					&& EqualityUtilities.equals(secondPathCharacters2,other.secondPathCharacters2)
					&& EqualityUtilities.equals(placeType1,other.placeType1)
					&& EqualityUtilities.equals(placeNumber1,other.placeNumber1)
					&& EqualityUtilities.equals(placeType2,other.placeType2)
					&& EqualityUtilities.equals(placeNumber2,other.placeNumber2)
					&& EqualityUtilities.equals(placeType3,other.placeType3)
					&& EqualityUtilities.equals(placeNumber3,other.placeNumber3)
					;
			}
			return false;
		}
	
	
	/**
	 * @return
	 */
	public Character getAddressCharacters() {
		return addressCharacters;
	}

	/**
	 * @return
	 */
	public String getAddressNumber() {
		return addressNumber;
	}

	/**
	 * @return
	 */
	public Long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}

	/**
	 * @return
	 */
	public Long getAtisRequestNumber() {
		return atisRequestNumber;
	}

	/**
	 * @return
	 */
	public Long getBlock() {
		return block;
	}

	/**
	 * @return
	 */
	public String getBlockSide() {
		return blockSide;
	}

	/**
	 * @return
	 */
	public Integer getCity() {
		return city;
	}

	/**
	 * @return
	 */
	public String getClientDocument() {
		return clientDocument;
	}

	/**
	 * @return
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * @return
	 */
	public Long getComercialOperation() {
		return comercialOperation;
	}

	/**
	 * @return
	 */
	public Long getComercialProductSubType() {
		return comercialProductSubType;
	}

	/**
	 * @return
	 */
	public Long getComercialProductType() {
		return comercialProductType;
	}

	/**
	 * @return
	 */
	public Integer getDepartment() {
		return department;
	}

	/**
	 * @return
	 */
	public Long getEnhancementNumber() {
		return enhancementNumber;
	}

	/**
	 * @return
	 */
	public String getFirstPathCharacters() {
		return firstPathCharacters;
	}

	/**
	 * @return
	 */
	public String getFirstPathCharacters2() {
		return firstPathCharacters2;
	}

	/**
	 * @return
	 */
	public String getInstallAddress() {
		return installAddress;
	}

	/**
	 * @return
	 */
	public Long getNumberClient() {
		return numberClient;
	}

	/**
	 * @return
	 */
	public Integer getPathNumber() {
		return pathNumber;
	}

	/**
	 * @return
	 */
	public Integer getPathNumber2() {
		return pathNumber2;
	}

	/**
	 * @return
	 */
	public String getPathType() {
		return pathType;
	}

	/**
	 * @return
	 */
	public String getPathType2() {
		return pathType2;
	}

	/**
	 * @return
	 */
	public Character getPathZone() {
		return pathZone;
	}

	/**
	 * @return
	 */
	public Character getPathZone2() {
		return pathZone2;
	}

	/**
	 * @return
	 */
	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return
	 */
	public String getPlaceNumber1() {
		return placeNumber1;
	}

	/**
	 * @return
	 */
	public String getPlaceNumber2() {
		return placeNumber2;
	}

	/**
	 * @return
	 */
	public String getPlaceNumber3() {
		return placeNumber3;
	}

	/**
	 * @return
	 */
	public String getPlaceType1() {
		return placeType1;
	}

	/**
	 * @return
	 */
	public String getPlaceType2() {
		return placeType2;
	}

	/**
	 * @return
	 */
	public String getPlaceType3() {
		return placeType3;
	}

	/**
	 * @return
	 */
	public Long getPreviousPhoneNumber() {
		return previousPhoneNumber;
	}

	/**
	 * @return
	 */
	public Long getProductServiceCode() {
		return productServiceCode;
	}

	/**
	 * @return
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

	public String getRequestDateString() {
		return requestDate;		
	}
	
	/**
	 * @return
	 */
	public Long getRequestPbx() {
		return requestPbx;
	}

	/**
	 * @return
	 */
	public String getSecondPathCharacters() {
		return secondPathCharacters;
	}

	/**
	 * @return
	 */
	public String getSecondPathCharacters2() {
		return secondPathCharacters2;
	}

	/**
	 * @return
	 */
	public Long getSector() {
		return sector;
	}

	/**
	 * @return
	 */
	public Long getSpaceNumber() {
		return spaceNumber;
	}

	/**
	 * @return
	 */
	public String getSubCity() {
		return subCity;
	}

	/**
	 * @return
	 */
	public Long getUseType() {
		return useType;
	}

	/**
	 * @return
	 */
	public Long getZone() {
		return zone;
	}

	/**
	 * @param c
	 */
	public void setAddressCharacters(char c) {
		addressCharacters = new Character(c);
	}

	/**
	 * @param i
	 */
//	public void setAddressNumber(int i) {
//		addressNumber = new Integer(i);
//	}

	/**
	 * @param l
	 */
	public void setAtiempoRequestNumber(long l) {
		atiempoRequestNumber = new Long(l);
	}

	/**
	 * @param l
	 */
	public void setAtisRequestNumber(long l) {
		atisRequestNumber = new Long(l);
	}

	/**
	 * @param l
	 */
	public void setBlock(long l) {
		block = new Long(l);
	}

	/**
	 * @param string
	 */
	public void setBlockSide(String string) {
		blockSide = string;
	}

	/**
	 * @param i
	 */
	public void setCity(int i) {
		city = new Integer(i);
	}

	/**
	 * @param string
	 */
	public void setClientDocument(String string) {
		clientDocument = string;
	}

	/**
	 * @param string
	 */
	public void setClientName(String string) {
		clientName = string;
	}

	/**
	 * @param l
	 */
	public void setComercialOperation(long l) {
		comercialOperation = new Long(l);
	}

	/**
	 * @param l
	 */
	public void setComercialProductSubType(long l) {
		comercialProductSubType = new Long(l);
	}

	/**
	 * @param l
	 */
	public void setComercialProductType(long l) {
		comercialProductType = new Long(l);
	}

	/**
	 * @param i
	 */
	public void setDepartment(int i) {
		department = new Integer(i);
	}

	/**
	 * @param l
	 */
	public void setEnhancementNumber(long l) {
		enhancementNumber = new Long(l);
	}

	/**
	 * @param string
	 */
	public void setFirstPathCharacters(String string) {
		firstPathCharacters = string;
	}

	/**
	 * @param string
	 */
	public void setFirstPathCharacters2(String string) {
		firstPathCharacters2 = string;
	}

	/**
	 * @param string
	 */
	public void setInstallAddress(String string) {
		installAddress = string;
	}

	/**
	 * @param l
	 */
	public void setNumberClient(long l) {
		numberClient = new Long(l);
	}

	/**
	 * @param i
	 */
	public void setPathNumber(int i) {
		pathNumber = new Integer(i);
	}

	/**
	 * @param i
	 */
	public void setPathNumber2(int i) {
		pathNumber2 = new Integer(i);
	}

	/**
	 * @param string
	 */
	public void setPathType(String string) {
		pathType = string;
	}

	/**
	 * @param string
	 */
	public void setPathType2(String string) {
		pathType2 = string;
	}

	/**
	 * @param string
	 */
	public void setPathZone(char c) {
		pathZone = new Character(c);
	}

	/**
	 * @param c
	 */
	public void setPathZone2(char c) {
		pathZone2 = new Character(c);
	}

	/**
	 * @param i
	 */
	public void setPhoneNumber(int i) {
		phoneNumber = new Integer(i);
	}

	/**
	 * @param string
	 */
	public void setPlaceNumber1(String string) {
		placeNumber1 = string;
	}

	/**
	 * @param string
	 */
	public void setPlaceNumber2(String string) {
		placeNumber2 = string;
	}

	/**
	 * @param string
	 */
	public void setPlaceNumber3(String string) {
		placeNumber3 = string;
	}

	/**
	 * @param string
	 */
	public void setPlaceType1(String string) {
		placeType1 = string;
	}

	/**
	 * @param string
	 */
	public void setPlaceType2(String string) {
		placeType2 = string;
	}

	/**
	 * @param string
	 */
	public void setPlaceType3(String string) {
		placeType3 = string;
	}

	/**
	 * @param l
	 */
	public void setPreviousPhoneNumber(long l) {
		previousPhoneNumber = new Long(l);
	}

	/**
	 * @param l
	 */
	public void setProductServiceCode(long l) {
		productServiceCode = new Long(l);
	}

	/**
	 * @param date
	 */
	public void setRequestDate(Date date) {
		
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		requestDate = formateador.format(date);
	}

	public void setRequestDateString (String date) {
		
		requestDate = date;
	}

	/**
	 * @param l
	 */
	public void setRequestPbx(long l) {
		requestPbx = new Long(l);
	}

	/**
	 * @param string
	 */
	public void setSecondPathCharacters(String string) {
		secondPathCharacters = string;
	}

	/**
	 * @param string
	 */
	public void setSecondPathCharacters2(String string) {
		secondPathCharacters2 = string;
	}

	/**
	 * @param l
	 */
	public void setSector(long l) {
		sector = new Long(l);
	}

	/**
	 * @param l
	 */
	public void setSpaceNumber(long l) {
		spaceNumber = new Long(l);
	}

	/**
	 * @param string
	 */
	public void setSubCity(String string) {
		subCity = string;
	}

	/**
	 * @param l
	 */
	public void setUseType(long l) {
		useType = new Long(l);
	}

	/**
	 * @param l
	 */
	public void setZone(long l) {
		zone = new Long(l);
	}

}
