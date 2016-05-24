/*
 * Creado el 29/11/2013
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;


import co.com.telefonica.atiempo.util.EqualityUtilities;


/**
 * @author Administrador
 *
 * TODO Para cambiar la plantilla de este comentario generado, vaya a
 * Ventana - Preferencias - Java - Estilo de c�digo - Plantillas de c�digo
 */
public class EndPointTypeFTTC  implements Serializable{
	 private long rack;
	   private long subrack;
	   private long slot;
	   private String port;
	   private String desc;
	   private String vpi;
	   private String vci;

	   public boolean equals(Object arg0) {
		if (arg0 instanceof EndPointTypeFTTC) {
			EndPointTypeFTTC other = (EndPointTypeFTTC) arg0;
			return super.equals(arg0)
			&& (rack == other.rack)
			&& (subrack == other.subrack)
			&& (subrack == other.subrack)
			&& EqualityUtilities.equals(port, other.port)
			&& EqualityUtilities.equals(desc, other.desc)
			&& EqualityUtilities.equals(vpi, other.vpi)
			&& EqualityUtilities.equals(vci, other.vci)
				;
			}
		return false;
	}
	   

	   /**
	    * @return Devuelve desc.
	    */
	   public String getDesc() {
	   	return desc;
	   }
	   /**
	    * @param desc El desc a establecer.
	    */
	   public void setDesc(String desc) {
	   	this.desc = desc;
	   }  

	   /**
	    * @return Devuelve port.
	    */
	   public String getPort() {
	   	return port;
	   }
	   /**
	    * @param port El port a establecer.
	    */
	   public void setPort(String port) {
	   	this.port = port;
	   }

	   /**
	    * @return Devuelve vci.
	    */
	   public String getVci() {
	   	return vci;
	   }
	   /**
	    * @param vci El vci a establecer.
	    */
	   public void setVci(String vci) {
	   	this.vci = vci;
	   }
	   /**
	    * @return Devuelve vpi.
	    */
	   public String getVpi() {
	   	return vpi;
	   }
	   /**
	    * @param vpi El vpi a establecer.
	    */
	   public void setVpi(String vpi) {
	   	this.vpi = vpi;
	   }
	/**
	 * @return Devuelve rack.
	 */
	public long getRack() {
		return rack;
	}
	/**
	 * @param rack El rack a establecer.
	 */
	public void setRack(long rack) {
		this.rack = rack;
	}
	/**
	 * @return Devuelve slot.
	 */
	public long getSlot() {
		return slot;
	}
	/**
	 * @param slot El slot a establecer.
	 */
	public void setSlot(long slot) {
		this.slot = slot;
	}
	/**
	 * @return Devuelve subrack.
	 */
	public long getSubrack() {
		return subrack;
	}
	/**
	 * @param subrack El subrack a establecer.
	 */
	public void setSubrack(long subrack) {
		this.subrack = subrack;
	}
}
