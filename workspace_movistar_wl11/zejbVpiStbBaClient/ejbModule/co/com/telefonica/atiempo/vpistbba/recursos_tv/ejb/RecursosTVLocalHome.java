
package co.com.telefonica.atiempo.vpistbba.recursos_tv.ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;


/**
 * This is the local-home interface for RecursosTV enterprise bean.
 */
public interface RecursosTVLocalHome extends EJBLocalHome
{
    public String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/recursos_tv/ejb/RecursosTVServiciosLocalHome";
    RecursosTVLocal create ()  throws CreateException;
    
    
}
