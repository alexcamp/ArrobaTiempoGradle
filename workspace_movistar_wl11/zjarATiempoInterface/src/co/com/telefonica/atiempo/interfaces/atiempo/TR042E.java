/*
 * Created on Apr 22, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**

/**
 * @author 808026
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TR042E extends RequestHeader {
	
	private long atiempoRequestNumber;
	private String serviceNumber;
	private long comercialOperation;
	private TypeValueList others;
	
	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
			if (arg0 instanceof TR042E) {
				TR042E other = (TR042E) arg0;
				return super.equals(arg0)
					&& (atiempoRequestNumber == other.atiempoRequestNumber)
					&& EqualityUtilities.equals(serviceNumber, other.serviceNumber)
					&& EqualityUtilities.equals(others, other.others)
					&& (comercialOperation ==  other.comercialOperation)
					;
			}
			return false;
		}
	



	/**
	 * @return
	 */
	public String getServiceNumber() {
		return serviceNumber;
	}

	/**
	 * @return
	 */
	public TypeValueList getOthers() {
		return others;
	}



	/**
	 * @param string
	 */
	public void setServiceNumber(String string) {
		serviceNumber = string;
	}

	/**
	 * @param collection
	 */
	public void setOthers(TypeValueList collection) {
		others = collection;
	}

	/**
	 * @return
	 */
	public long getComercialOperation() {
		return comercialOperation;
	}

	/**
	 * @param l
	 */
	public void setComercialOperation(long l) {
		comercialOperation = l;
	}

	/**
	 * @return
	 */
	public long getAtiempoRequestNumber() {
		return atiempoRequestNumber;
	}

	/**
	 * @param l
	 */
	public void setAtiempoRequestNumber(long l) {
		atiempoRequestNumber = l;
	}

}
