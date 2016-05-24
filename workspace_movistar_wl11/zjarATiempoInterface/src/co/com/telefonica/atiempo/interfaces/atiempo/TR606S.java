//- Copyright Notice
//-----------------------------------------------------------------------
//(C) Copyright 2007 Telefonica Colombia, http://www.telefonica.com.co
//Bogota, Colombia All Rights Reserved
//THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Telefonica Colombia
//The copyright notice above does not evidence any actual or intended
//publication of such source code.
//
//$Id: TR0606S.java 06/11/2014 14:46:28 dcardena Exp $
//-----------------------------------------------------------------------

package co.com.telefonica.atiempo.interfaces.atiempo;


/**
* @author dcardena
*
*/
public class TR606S extends ResponseHeader2{

	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR606S) {
			TR606S other = (TR606S) arg0;
			return super.equals(arg0);
				
			}
		return false;
	}

}
