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
public class TR605S extends ResponseHeader{

	private Long codeError;	
	private String messageError;

	
	public int hashCode(){
		return super.hashCode();
	}
	
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR605S) {
				TR605S other = (TR605S) arg0;
				return super.equals(arg0)
					&& EqualityUtilities.equals(codeError,other.codeError)
					&& EqualityUtilities.equals(messageError,other.messageError)			
					;
			}
			return false;
		}
	/**
	 * @return Devuelve codeError.
	 */
	public Long getCodeError() {
		return codeError;
	}
	/**
	 * @param codeError El codeError a establecer.
	 */
	public void setCodeError(Long codeError) {
		this.codeError = codeError;
	}
	/**
	 * @return Devuelve messageError.
	 */
	public String getMessageError() {
		return messageError;
	}
	/**
	 * @param messageError El messageError a establecer.
	 */
	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}
}