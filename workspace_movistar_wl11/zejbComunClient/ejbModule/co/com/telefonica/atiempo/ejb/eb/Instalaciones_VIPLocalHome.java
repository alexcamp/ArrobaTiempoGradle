package co.com.telefonica.atiempo.ejb.eb;


/**
 * Local Home interface for Enterprise Bean: Instalaciones_VIP
 */
public interface Instalaciones_VIPLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Instalaciones_VIP
	 */
	String JNDI_NAME = "ejb/co/com/telefonica/atiempo/ejb/eb/Instalaciones_VIPLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.Instalaciones_VIPLocal create(
		java.lang.Long atiempo, java.lang.Long atis,String idpc,String idpcTV,
		String codLocalidad,String codDPTO,Integer espeId, String ticaId) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Instalaciones_VIP
	 */
	public co.com.telefonica.atiempo.ejb.eb.Instalaciones_VIPLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Instalaciones_VIPKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Instalaciones_VIPLocal findByIdPcFecMax(
		java.lang.String idpc) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Instalaciones_VIPLocal findByIdPcTVFechMax(
		java.lang.String idpcTV) throws javax.ejb.FinderException;
}