package co.com.telefonica.atiempo.vpistbba.genera_archivo.ejb.sb;
/**
 * Local Home interface for Enterprise Bean: GeneraArchivo
 */
public interface GeneraArchivoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates a default instance of Session Bean: GeneraArchivo
	 */
	
	public String JNDI_NAME = "ejb/co/com/telefonica/atiempo/vpistbba/genera_archivo/ejb/sb/GeneraArchivoLocalHome";
	public co.com.telefonica.atiempo.vpistbba.genera_archivo.ejb.sb.GeneraArchivoLocal create() throws javax.ejb.CreateException;
}
