/*
 * Created on 16-08-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.reglas;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.soltec.reglas.ejb.sb.ReglasSTLocal;
import co.com.telefonica.atiempo.soltec.reglas.ejb.sb.ReglasSTLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author VictoMan
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ReglasDelegate implements ReglasInterfaces {
	private ReglasSTLocal servicios;

	public ReglasDelegate() throws ATiempoAppEx {
		try {
			servicios = ((ReglasSTLocalHome) HomeFactory.getHome(ReglasSTLocalHome.JNDI_NAME)).create();
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.reglas.ReglasInterfaces#getRegla(java.lang.Long)
	 */
	public long getRegla(Long nroIncidente) {
		// TODO Auto-generated method stub
		return servicios.getRegla(nroIncidente);
	}

}
