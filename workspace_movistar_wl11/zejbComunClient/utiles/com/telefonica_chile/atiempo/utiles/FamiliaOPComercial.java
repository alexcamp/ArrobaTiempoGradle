/*
 * Created on 30/07/2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.telefonica_chile.atiempo.utiles;



/**
 * @author 808026
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FamiliaOPComercial {
    
	private String familia = "";
    private String opComercial = "";
    

    public FamiliaOPComercial(String familia, String opComercial) {
        this.familia = familia;
        this.opComercial = opComercial;     
    }
    
    
	public boolean equals(Object obj)
	{
		if ((!(obj instanceof FamiliaOPComercial)) || (obj == null)){
		   return (false) ;
		}
		
		FamiliaOPComercial otro = (FamiliaOPComercial) obj ;
        if(this.familia==null || this.opComercial== null ||otro.familia==null ||otro.opComercial==null ){
            return false;
        }
        return (this.familia.equals(otro.familia )&& this.opComercial.equals(otro.opComercial )) ;
	}
	

    public int hashCode() {
        return familia.hashCode()+ opComercial.hashCode();
    }
}
