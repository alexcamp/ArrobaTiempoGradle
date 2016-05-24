package co.com.telefonica.atiempo.sigres.emu;
/**
 * Local interface for Enterprise Bean: Receptor
 */
public interface ReceptorLocal extends javax.ejb.EJBLocalObject {
	public void recibirMensaje(String mensaje);
}
