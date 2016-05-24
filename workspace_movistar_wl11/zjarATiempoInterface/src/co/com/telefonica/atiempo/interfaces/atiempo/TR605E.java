/*
 * Created on Apr 21, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author 806713
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TR605E extends RequestHeader{

	private String pcID;	
	private Long ps;	
	private Long oc;		
	private Long psAnterior;	
	private String identicationCard;
	private String email;

	
	public int hashCode(){
		return super.hashCode();
	}
	
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR605E) {
				TR605E other = (TR605E) arg0;
				return super.equals(arg0)
					&& EqualityUtilities.equals(pcID,other.pcID)
					&& EqualityUtilities.equals(ps,other.ps)
					&& EqualityUtilities.equals(oc,other.oc)
					&& EqualityUtilities.equals(psAnterior,other.psAnterior)
					&& EqualityUtilities.equals(identicationCard,other.identicationCard)
					&& EqualityUtilities.equals(email,other.email)				
					;
			}
			return false;
		}

	/**
	 * @return Devuelve email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email El email a establecer.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return Devuelve identicationCard.
	 */
	public String getIdenticationCard() {
		return identicationCard;
	}
	/**
	 * @param identicationCard El identicationCard a establecer.
	 */
	public void setIdenticationCard(String identicationCard) {
		this.identicationCard = identicationCard;
	}
	/**
	 * @return Devuelve oc.
	 */
	public Long getOc() {
		return oc;
	}
	/**
	 * @param oc El oc a establecer.
	 */
	public void setOc(Long oc) {
		this.oc = oc;
	}
	/**
	 * @return Devuelve pcID.
	 */
	public String getPcID() {
		return pcID;
	}
	/**
	 * @param pcID El pcID a establecer.
	 */
	public void setPcID(String pcID) {
		this.pcID = pcID;
	}
	/**
	 * @return Devuelve ps.
	 */
	public Long getPs() {
		return ps;
	}
	/**
	 * @param ps El ps a establecer.
	 */
	public void setPs(Long ps) {
		this.ps = ps;
	}
	/**
	 * @return Devuelve psAnterior.
	 */
	public Long getPsAnterior() {
		return psAnterior;
	}
	/**
	 * @param psAnterior El psAnterior a establecer.
	 */
	public void setPsAnterior(Long psAnterior) {
		this.psAnterior = psAnterior;
	}
}