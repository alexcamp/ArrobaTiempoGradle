package co.com.telefonica.atiempo.sigres.emu;

import co.com.telefonica.atiempo.sigres.emu.process.Despachador;

import java.rmi.RemoteException;

import javax.ejb.EJBHome;
import javax.ejb.EJBObject;
import javax.ejb.Handle;
import javax.ejb.RemoveException;
import javax.ejb.Stateless;

import com.tecnonautica.utiles.db.DBManager;

/**
 * Bean implementation class for Enterprise Bean: Receptor
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
@Stateless
public class ReceptorBean implements javax.ejb.SessionBean, Receptor {
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
	public void recibirMensaje(String mensaje){
		//String mensajeConId = cargoIdPeticion(mensaje);		
		Despachador d = new Despachador(mensaje);
		Thread t = new Thread(d);
		t.start();
	}
	
	String cargoIdPeticion(String mensaje) {
		return null;
	}
	
	String generoIdPeticion() {
		 // Creacion de DataSource
		 DBManager dbSeq = new DBManager ();
		 dbSeq.setDataSource(DBManager.JDBC_BANDEJA); // verificar que es el datasource correcto
		 Long idCorrelativo = new Long(dbSeq.seqNextValLong("CORRELATIVO_MENSAJE"));
		 return idCorrelativo.toString();  
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
	public boolean isIdentical(EJBObject obj) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void remove() throws RemoteException, RemoveException {
		// TODO Auto-generated method stub
		
	}	
}
