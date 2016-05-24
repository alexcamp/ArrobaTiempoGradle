package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;
import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author mfmendezs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR020ERequestHeader implements Serializable {
	
	private String atiempoRequestNumber;
	private String accountingDate;
	private String moveType;	
	/*Posiciones Peticion - Tr020EPositionHeader*/
	private Collection positionsHeader;
	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0){
		if(arg0 instanceof TR020ERequestHeader){
			TR020ERequestHeader other = (TR020ERequestHeader) arg0;
			return super.equals(arg0)				
				&& EqualityUtilities.equals(atiempoRequestNumber,other.getAtiempoRequestNumber())
				&& EqualityUtilities.equals(accountingDate,other.getAccountingDate())
				&& EqualityUtilities.equals(moveType,other.getMoveType())
				&& EqualityUtilities.equals(positionsHeader,other.getPositionsHeader());
		}else{
			return false;
		}
	}
		
	/**
	 * @return Returns the accountingDate.
	 */
	public String getAccountingDate() {
		return accountingDate;
	}
	/**
	 * @param accountingDate The accountingDate to set.
	 */
	public void setAccountingDate(String accountingDate) {
		this.accountingDate = accountingDate;
	}
	/**
	 * @return Returns the moveType.
	 */
	public String getMoveType() {
		return moveType;
	}
	/**
	 * @param moveType The moveType to set.
	 */
	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}
	/**
	 * @return Returns the atiempoRequestNumber.
	 */
	public String getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}
	/**
	 * @param atiempoRequestNumber The atiempoRequestNumber to set.
	 */
	public void setAtiempoRequestNumber(String atiempoRequestNumber) {
		this.atiempoRequestNumber = atiempoRequestNumber;
	}
	/**
	 * @return Returns the positionsHeader.
	 */
	public Collection getPositionsHeader() {
		return positionsHeader;
	}
	/**
	 * @param positionsHeader The positionsHeader to set.
	 */
	public void setPositionsHeader(Collection positionsHeader) {
		this.positionsHeader = positionsHeader;
	}
}
