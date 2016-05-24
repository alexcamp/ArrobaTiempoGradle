/*
 * Created on Aug 24, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR701ETechnicalData implements Serializable {
	private String centralCode;
	private String phoneNumber;
	private String len;
	private String horizontalPosicion;
	private String distributorCode;
	private String distributorDescription;
	private String distributorAddress;
	private String strip;
	private String stripPair;
	private String cable;
	private String cablePair;
	private String closet;
	private String closetAddress;
	private String box;
	private String boxPair;
	private String boxAddress;
	private String boxType;
	private String boxDistance;
	private String latitude;
	private String longitude;
	private String zone;
	private String speed;
	private String ipType;
	private String portId;
	private String pots;
	private String adsl;
	private String ipDslam;
	private String ipWan;
	private String ipLan;
	private String ipLanMask;
	private String frame;
	private String card;
	private String rack;
	private String subrack;
	private String vpiVci;
	private String vpiVciNetwork;
	private String userAccess;
	private String packages;
	private String decosType;
	private String decosNumber;
	private String parDedicated;
	private String nomInterlocutor;
	private String telInterlocutor;
	private String tel1Contacto;
	private String tel2Contacto;
	private String tel3Contacto;
	
	public int hashCode() {
		return phoneNumber.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR701ETechnicalData) {
			TR701ETechnicalData other = (TR701ETechnicalData) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(centralCode, other.centralCode)
				&& EqualityUtilities.equals(phoneNumber, other.phoneNumber)
				&& EqualityUtilities.equals(len, other.len)
				&& EqualityUtilities.equals(horizontalPosicion, other.horizontalPosicion)
				&& EqualityUtilities.equals(distributorCode, other.distributorCode)
				&& EqualityUtilities.equals(distributorAddress, other.distributorAddress)
				&& EqualityUtilities.equals(strip, other.strip)
				&& EqualityUtilities.equals(stripPair, other.stripPair)
				&& EqualityUtilities.equals(cable, other.cable)
				&& EqualityUtilities.equals(cablePair, other.cablePair)
				&& EqualityUtilities.equals(closet, other.closet)
				&& EqualityUtilities.equals(closetAddress, other.closetAddress)
				&& EqualityUtilities.equals(box, other.box)
				&& EqualityUtilities.equals(boxPair, other.boxPair)
				&& EqualityUtilities.equals(boxAddress, other.boxAddress)
				&& EqualityUtilities.equals(boxType, other.boxType)
				&& EqualityUtilities.equals(boxDistance, other.boxDistance)
				&& EqualityUtilities.equals(latitude, other.latitude)
				&& EqualityUtilities.equals(longitude, other.longitude)
				&& EqualityUtilities.equals(zone, other.zone)
				&& EqualityUtilities.equals(speed, other.speed)
				&& EqualityUtilities.equals(ipType, other.ipType)
				&& EqualityUtilities.equals(portId, other.portId)
				&& EqualityUtilities.equals(pots, other.pots)
				&& EqualityUtilities.equals(adsl, other.adsl)
				&& EqualityUtilities.equals(ipDslam, other.ipDslam)
				&& EqualityUtilities.equals(ipWan, other.ipWan)
				&& EqualityUtilities.equals(ipLan, other.ipLan)
				&& EqualityUtilities.equals(ipLanMask, other.ipLanMask)
				&& EqualityUtilities.equals(frame, other.frame)
				&& EqualityUtilities.equals(card, other.card)
				&& EqualityUtilities.equals(rack, other.rack)
				&& EqualityUtilities.equals(subrack, other.subrack)
				&& EqualityUtilities.equals(vpiVci, other.vpiVci)
				&& EqualityUtilities.equals(vpiVciNetwork, other.vpiVciNetwork)
				&& EqualityUtilities.equals(userAccess, other.userAccess)
				&& EqualityUtilities.equals(packages, other.packages)
				&& EqualityUtilities.equals(decosType, other.decosType)
				&& EqualityUtilities.equals(decosNumber, other.decosNumber);
			}
		return false;
	}
	public String getAdsl() {
		return adsl;
	}
	public void setAdsl(String adsl) {
		this.adsl = adsl;
	}
	public String getBox() {
		return box;
	}
	public void setBox(String box) {
		this.box = box;
	}
	public String getBoxAddress() {
		return boxAddress;
	}
	public void setBoxAddress(String boxAddress) {
		this.boxAddress = boxAddress;
	}
	public String getBoxDistance() {
		return boxDistance;
	}
	public void setBoxDistance(String boxDistance) {
		this.boxDistance = boxDistance;
	}
	public String getBoxPair() {
		return boxPair;
	}
	public void setBoxPair(String boxPair) {
		this.boxPair = boxPair;
	}
	public String getBoxType() {
		return boxType;
	}
	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}
	public String getCable() {
		return cable;
	}
	public void setCable(String cable) {
		this.cable = cable;
	}
	public String getCablePair() {
		return cablePair;
	}
	public void setCablePair(String cablePair) {
		this.cablePair = cablePair;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getCentralCode() {
		return centralCode;
	}
	public void setCentralCode(String centralCode) {
		this.centralCode = centralCode;
	}
	public String getCloset() {
		return closet;
	}
	public void setCloset(String closet) {
		this.closet = closet;
	}
	public String getClosetAddress() {
		return closetAddress;
	}
	public void setClosetAddress(String closetAddress) {
		this.closetAddress = closetAddress;
	}
	public String getDecosNumber() {
		return decosNumber;
	}
	public void setDecosNumber(String decosNumber) {
		this.decosNumber = decosNumber;
	}
	public String getDecosType() {
		return decosType;
	}
	public void setDecosType(String decosType) {
		this.decosType = decosType;
	}
	public String getDistributorAddress() {
		return distributorAddress;
	}
	public void setDistributorAddress(String distributorAddress) {
		this.distributorAddress = distributorAddress;
	}
	public String getDistributorCode() {
		return distributorCode;
	}
	public void setDistributorCode(String distributorCode) {
		this.distributorCode = distributorCode;
	}
	public String getDistributorDescription() {
		return distributorDescription;
	}
	public void setDistributorDescription(String distributorDescription) {
		this.distributorDescription = distributorDescription;
	}
	public String getFrame() {
		return frame;
	}
	public void setFrame(String frame) {
		this.frame = frame;
	}
	public String getHorizontalPosicion() {
		return horizontalPosicion;
	}
	public void setHorizontalPosicion(String horizontalPosicion) {
		this.horizontalPosicion = horizontalPosicion;
	}
	public String getIpDslam() {
		return ipDslam;
	}
	public void setIpDslam(String ipDslam) {
		this.ipDslam = ipDslam;
	}
	public String getIpLan() {
		return ipLan;
	}
	public void setIpLan(String ipLan) {
		this.ipLan = ipLan;
	}
	public String getIpLanMask() {
		return ipLanMask;
	}
	public void setIpLanMask(String ipLanMask) {
		this.ipLanMask = ipLanMask;
	}
	public String getIpType() {
		return ipType;
	}
	public void setIpType(String ipType) {
		this.ipType = ipType;
	}
	public String getIpWan() {
		return ipWan;
	}
	public void setIpWan(String ipWan) {
		this.ipWan = ipWan;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLen() {
		return len;
	}
	public void setLen(String len) {
		this.len = len;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getPackages() {
		return packages;
	}
	public void setPackages(String packages) {
		this.packages = packages;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPortId() {
		return portId;
	}
	public void setPortId(String portId) {
		this.portId = portId;
	}
	public String getPots() {
		return pots;
	}
	public void setPots(String pots) {
		this.pots = pots;
	}
	public String getRack() {
		return rack;
	}
	public void setRack(String rack) {
		this.rack = rack;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getStrip() {
		return strip;
	}
	public void setStrip(String strip) {
		this.strip = strip;
	}
	public String getStripPair() {
		return stripPair;
	}
	public void setStripPair(String stripPair) {
		this.stripPair = stripPair;
	}
	public String getSubrack() {
		return subrack;
	}
	public void setSubrack(String subrack) {
		this.subrack = subrack;
	}
	public String getUserAccess() {
		return userAccess;
	}
	public void setUserAccess(String userAccess) {
		this.userAccess = userAccess;
	}
	public String getVpiVci() {
		return vpiVci;
	}
	public void setVpiVci(String vpiVci) {
		this.vpiVci = vpiVci;
	}
	public String getVpiVciNetwork() {
		return vpiVciNetwork;
	}
	public void setVpiVciNetwork(String vpiVciNetwork) {
		this.vpiVciNetwork = vpiVciNetwork;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getParDedicated(){
		return parDedicated;
	}
	public void setParDedicated(String parDedicated){
		this.parDedicated = parDedicated;
	}
	/**
	 * @return Devuelve nomInterlocutor.
	 */
	public String getNomInterlocutor() {
		return nomInterlocutor;
	}
	/**
	 * @param nomInterlocutor El nomInterlocutor a establecer.
	 */
	public void setNomInterlocutor(String nomInterlocutor) {
		this.nomInterlocutor = nomInterlocutor;
	}
	/**
	 * @return Devuelve tel1Contacto.
	 */
	public String getTel1Contacto() {
		return tel1Contacto;
	}
	/**
	 * @param tel1Contacto El tel1Contacto a establecer.
	 */
	public void setTel1Contacto(String tel1Contacto) {
		this.tel1Contacto = tel1Contacto;
	}
	/**
	 * @return Devuelve tel2Contacto.
	 */
	public String getTel2Contacto() {
		return tel2Contacto;
	}
	/**
	 * @param tel2Contacto El tel2Contacto a establecer.
	 */
	public void setTel2Contacto(String tel2Contacto) {
		this.tel2Contacto = tel2Contacto;
	}
	/**
	 * @return Devuelve tel3Contacto.
	 */
	public String getTel3Contacto() {
		return tel3Contacto;
	}
	/**
	 * @param tel3Contacto El tel3Contacto a establecer.
	 */
	public void setTel3Contacto(String tel3Contacto) {
		this.tel3Contacto = tel3Contacto;
	}
	/**
	 * @return Devuelve telInterlocutor.
	 */
	public String getTelInterlocutor() {
		return telInterlocutor;
	}
	/**
	 * @param telInterlocutor El telInterlocutor a establecer.
	 */
	public void setTelInterlocutor(String telInterlocutor) {
		this.telInterlocutor = telInterlocutor;
	}
}
