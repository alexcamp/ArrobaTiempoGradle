package com.telefonica_chile.bandeja.accionesMasivas;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.ejb.FinderException;
import javax.ejb.Handle;
import javax.ejb.Local;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.AplicacionKey;
import co.com.telefonica.atiempo.ejb.eb.AplicacionLocal;
import co.com.telefonica.atiempo.ejb.eb.AplicacionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.BandejaException;

/**
 * Bean implementation class for Enterprise Bean: AccionesMasivasSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
@Stateless
@Local
public class AccionesMasivasSessionBean implements javax.ejb.SessionBean, AccionesMasivasSession {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -626129575752659527L;

	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	private javax.ejb.SessionContext mySessionCtx;
	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}
	
	public boolean updateUsuarioBIntegrada(Long nuevoUsuario,Long idPeticion , Long idActividad , Long antiguoUsuario) throws BandejaException {
		boolean retorno = false;	
		try {				
			//Seteo el nuevo Usuario en la BINTEGRADA
			BintegradaLocal bIntegradaLocal = getBintegradaLocal(antiguoUsuario,idPeticion,idActividad);
			if ( bIntegradaLocal.getBi_visible()==null || bIntegradaLocal.getBi_visible().intValue()!=1 )
				return false;
		
			bIntegradaLocal.setFk_bi_usuario( getUsuarioLocal(nuevoUsuario) );
			retorno = true;
		} catch (Exception e) {
			log.warn("No se pudo Reasignar Peticion en BI [" + idPeticion + "," + idActividad 
				+ "," + antiguoUsuario + "," + nuevoUsuario + "] :" + e.getMessage());

			return false;				
		}
		return retorno;
	}
	
	

	private static BintegradaLocal getBintegradaLocal(Long idUsuario,Long idPeticion , Long idActividad) throws NamingException, RemoteException, CreateException, FinderException {		
		BintegradaLocalHome home = (BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);									
		BintegradaLocal local = home.findPeticionActividadUsuario(idPeticion,idActividad,idUsuario);
		return local;	
	}

	private static UsuarioLocal getUsuarioLocal(Long idUsuario) throws NamingException, RemoteException, CreateException, FinderException {
		UsuarioLocalHome home = (UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
		UsuarioKey usuarioKey=new UsuarioKey(idUsuario);									
		UsuarioLocal local = home.findByPrimaryKey(usuarioKey);
		return local;				
	}
	
	public String getNombreAplicacionPeticion(Long idPeticion){
		String nombreAplicacion="";
		try {
			BintegradaLocalHome home = (BintegradaLocalHome) HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			//BintegradaEntityLocal local = home.findByNroPeticion(idPeticion);
			
			
			Collection c = home.findListByPeticion(idPeticion);
			Long idAplicacion = null;
			for (Iterator it=c.iterator(); it.hasNext(); ) {
				BintegradaLocal local = (BintegradaLocal) it.next(); 
				AplicacionKey aplicacionKey=(AplicacionKey) local.getFk_bintegrada_ap().getPrimaryKey();				
				idAplicacion = aplicacionKey.ap_id;
			}

			if (idAplicacion == null)
				return "";												

			AplicacionLocalHome aplicacionEntityLocalHome = (AplicacionLocalHome) HomeFactory.getHome(AplicacionLocalHome.JNDI_NAME);
			AplicacionKey aplicacionKey=new AplicacionKey(idAplicacion);
			AplicacionLocal aplicacionEntityLocal = aplicacionEntityLocalHome.findByPrimaryKey(aplicacionKey);
			nombreAplicacion=(String)aplicacionEntityLocal.getAp_nombre();

		} catch (Exception e) {
			log.debug("Problema para obtener nombre de aplicacion Bandeja_AccionesMasivasSession!",e);
		}
		return nombreAplicacion;
	}
	
	
	
	@Override
	public EJBHome getEJBHome() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Handle getHandle() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getPrimaryKey() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isIdentical(EJBObject arg0) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void remove() throws RemoteException, RemoveException {
		// TODO Auto-generated method stub
		
	}
	
}
