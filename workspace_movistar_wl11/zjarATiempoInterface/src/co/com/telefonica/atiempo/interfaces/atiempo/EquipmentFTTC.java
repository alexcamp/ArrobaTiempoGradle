/*
 * Creado el 29/11/2013
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
public class EquipmentFTTC  implements Serializable{
	   private String vendor;
	   private String model;
	   private String network;
	   private String name;
	   private String ip;
	   private String password;
	   private String telnetuser;
	   private String telnetpassword;
	   
	   
	   
	   public boolean equals(Object arg0) {
			if (arg0 instanceof EquipmentFTTC) {
				EquipmentFTTC other = (EquipmentFTTC) arg0;
				return super.equals(arg0)
								
	&& EqualityUtilities.equals(vendor, other.vendor)
	&& EqualityUtilities.equals(model, other.model)
	&& EqualityUtilities.equals(network, other.network)
	&& EqualityUtilities.equals(name, other.name)
	&& EqualityUtilities.equals(ip, other.ip)
	&& EqualityUtilities.equals(password, other.password)
	&& EqualityUtilities.equals(telnetuser, other.telnetuser)
	&& EqualityUtilities.equals(telnetpassword, other.telnetpassword)
					;
				}
			return false;
		}
	   

	   /**
	    * @return Devuelve ip.
	    */
	   public String getIp() {
	   	return ip;
	   }
	   /**
	    * @param ip El ip a establecer.
	    */
	   public void setIp(String ip) {
	   	this.ip = ip;
	   }
	   /**
	    * @return Devuelve model.
	    */
	   public String getModel() {
	   	return model;
	   }
	   /**
	    * @param model El model a establecer.
	    */
	   public void setModel(String model) {
	   	this.model = model;
	   }
	   /**
	    * @return Devuelve name.
	    */
	   public String getName() {
	   	return name;
	   }
	   /**
	    * @param name El name a establecer.
	    */
	   public void setName(String name) {
	   	this.name = name;
	   }
	   /**
	    * @return Devuelve network.
	    */
	   public String getNetwork() {
	   	return network;
	   }
	   /**
	    * @param network El network a establecer.
	    */
	   public void setNetwork(String network) {
	   	this.network = network;
	   }
	   /**
	    * @return Devuelve password.
	    */
	   public String getPassword() {
	   	return password;
	   }
	   /**
	    * @param password El password a establecer.
	    */
	   public void setPassword(String password) {
	   	this.password = password;
	   }
	   

	   /**
	    * @return Devuelve telnetpassword.
	    */
	   public String getTelnetpassword() {
	   	return telnetpassword;
	   }
	   /**
	    * @param telnetpassword El telnetpassword a establecer.
	    */
	   public void setTelnetpassword(String telnetpassword) {
	   	this.telnetpassword = telnetpassword;
	   }
	   /**
	    * @return Devuelve telnetuser.
	    */
	   public String getTelnetuser() {
	   	return telnetuser;
	   }
	   /**
	    * @param telnetuser El telnetuser a establecer.
	    */
	   public void setTelnetuser(String telnetuser) {
	   	this.telnetuser = telnetuser;
	   }

	   /**
	    * @return Devuelve vendor.
	    */
	   public String getVendor() {
	   	return vendor;
	   }
	   /**
	    * @param vendor El vendor a establecer.
	    */
	   public void setVendor(String vendor) {
	   	this.vendor = vendor;
	   }
}



