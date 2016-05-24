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
public class SippstDataFTTC  implements Serializable{
	   private String phonenumber;
	   private String authuser;
	   private String authpass;
	   private String callhold;
	   private String threeparty;
	   private String cw;
	   private String ct;
	   private String dc;
	   private String conf;
	   private String hottime;
	   private String hotlinenum;
	   private String hotline;
	  	   
	   public boolean equals(Object arg0) {
		if (arg0 instanceof SippstDataFTTC) {
			SippstDataFTTC other = (SippstDataFTTC) arg0;
			return super.equals(arg0)

&& EqualityUtilities.equals(phonenumber, other.phonenumber)
&& EqualityUtilities.equals(authuser, other.authuser)
&& EqualityUtilities.equals(authpass, other.authpass)
&& EqualityUtilities.equals(callhold, other.callhold)
&& EqualityUtilities.equals(threeparty, other.threeparty)
&& EqualityUtilities.equals(cw, other.cw)
&& EqualityUtilities.equals(hottime, other.hottime)
&& EqualityUtilities.equals(hotlinenum, other.hotlinenum)
&& EqualityUtilities.equals(ct, other.ct)
&& EqualityUtilities.equals(dc, other.dc)
&& EqualityUtilities.equals(conf, other.conf)
&& EqualityUtilities.equals(hotline, other.hotline)
				;
			}
		return false;
	}

	   /**
	    * @return Devuelve authpassM.
	    */
	   public String getAuthpass() {
	   	return authpass;
	   }
	   /**
	    * @param authpassM El authpassM a establecer.
	    */
	   public void setAuthpass(String authpass) {
	   	this.authpass = authpass;
	   }
	   /**
	    * @return Devuelve authuser.
	    */
	   public String getAuthuser() {
	   	return authuser;
	   }
	   /**
	    * @param authuser El authuser a establecer.
	    */
	   public void setAuthuser(String authuser) {
	   	this.authuser = authuser;
	   }
	   /**
	    * @return Devuelve callhold.
	    */
	   public String getCallhold() {
	   	return callhold;
	   }
	   /**
	    * @param callhold El callhold a establecer.
	    */
	   public void setCallhold(String callhold) {
	   	this.callhold = callhold;
	   }
	   /**
	    * @return Devuelve conf.
	    */
	   public String getConf() {
	   	return conf;
	   }
	   /**
	    * @param conf El conf a establecer.
	    */
	   public void setConf(String conf) {
	   	this.conf = conf;
	   }
	   /**
	    * @return Devuelve ct.
	    */
	   public String getCt() {
	   	return ct;
	   }
	   /**
	    * @param ct El ct a establecer.
	    */
	   public void setCt(String ct) {
	   	this.ct = ct;
	   }
	   /**
	    * @return Devuelve cw.
	    */
	   public String getCw() {
	   	return cw;
	   }
	   /**
	    * @param cw El cw a establecer.
	    */
	   public void setCw(String cw) {
	   	this.cw = cw;
	   }
	   /**
	    * @return Devuelve dc.
	    */
	   public String getDc() {
	   	return dc;
	   }
	   /**
	    * @param dc El dc a establecer.
	    */
	   public void setDc(String dc) {
	   	this.dc = dc;
	   }
	   /**
	    * @return Devuelve phonenumber.
	    */
	   public String getPhonenumber() {
	   	return phonenumber;
	   }
	   /**
	    * @param phonenumber El phonenumber a establecer.
	    */
	   public void setPhonenumber(String phonenumber) {
	   	this.phonenumber = phonenumber;
	   }/**
	    * @return Devuelve threeparty.
	    */
	/**
	 * @return Devuelve threeparty.
	 */
	public String getThreeparty() {
		return threeparty;
	}
	/**
	 * @param threeparty El threeparty a establecer.
	 */
	public void setThreeparty(String threeparty) {
		this.threeparty = threeparty;
	}
	/**
	 * @return Devuelve hotline.
	 */
	public String getHotline() {
		return hotline;
	}
	/**
	 * @param hotline El hotline a establecer.
	 */
	public void setHotline(String hotline) {
		this.hotline = hotline;
	}
	/**
	 * @return Devuelve hotlinenum.
	 */
	public String getHotlinenum() {
		return hotlinenum;
	}
	/**
	 * @param hotlinenum El hotlinenum a establecer.
	 */
	public void setHotlinenum(String hotlinenum) {
		this.hotlinenum = hotlinenum;
	}
	/**
	 * @return Devuelve hottime.
	 */
	public String getHottime() {
		return hottime;
	}
	/**
	 * @param hottime El hottime a establecer.
	 */
	public void setHottime(String hottime) {
		this.hottime = hottime;
	}
}
