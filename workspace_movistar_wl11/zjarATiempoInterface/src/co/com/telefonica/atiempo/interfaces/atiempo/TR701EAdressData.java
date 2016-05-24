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
public class TR701EAdressData implements Serializable {
	private String address;
	private String city;
	private String state;
	private String subCity;
	private String location;
	private String agencyName;
	private String neighborhood;
	private String coordinateX;
	private String coordinateY;
	
	public int hashCode() {
		return coordinateX.hashCode()+coordinateY.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR701EAdressData) {
			TR701EAdressData other = (TR701EAdressData) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(address, other.address)
				&& EqualityUtilities.equals(city, other.city)
				&& EqualityUtilities.equals(state, other.state)
				&& EqualityUtilities.equals(subCity, other.subCity)
				&& EqualityUtilities.equals(location, other.location)
				&& EqualityUtilities.equals(agencyName, other.agencyName)
				&& EqualityUtilities.equals(neighborhood, other.neighborhood)
				&& EqualityUtilities.equals(coordinateX, other.coordinateX)
				&& EqualityUtilities.equals(coordinateY, other.coordinateY);
			}
		return false;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCoordinateX() {
		return coordinateX;
	}
	public void setCoordinateX(String coordinateX) {
		this.coordinateX = coordinateX;
	}
	public String getCoordinateY() {
		return coordinateY;
	}
	public void setCoordinateY(String coordinateY) {
		this.coordinateY = coordinateY;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSubCity() {
		return subCity;
	}
	public void setSubCity(String subCity) {
		this.subCity = subCity;
	}
}
