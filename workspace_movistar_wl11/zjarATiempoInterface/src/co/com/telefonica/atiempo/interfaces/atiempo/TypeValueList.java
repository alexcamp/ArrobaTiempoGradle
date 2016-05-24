/*
 * Created on Apr 30, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import co.com.telefonica.atiempo.util.EqualityUtilities;
import java.util.Collection;

/**
 * @author 806713
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TypeValueList {
	private Collection pssbaDataList;

	/**
	 * @return
	 */
	public Collection getPssbaDataList() {
		return pssbaDataList;
	}

	public int hashCode(){
		return super.hashCode();
	}

	/**
	 * @param collection
	 */
	public void setPssbaDataList(Collection collection) {
		pssbaDataList = collection;
	}

	public boolean equals(Object arg0){
		if (arg0 instanceof TypeValueList) {
			TypeValueList text = (TypeValueList) arg0;
			return 
				EqualityUtilities.equals(this.pssbaDataList,text.pssbaDataList)
			
				;
		}
		return false;
	}

}
