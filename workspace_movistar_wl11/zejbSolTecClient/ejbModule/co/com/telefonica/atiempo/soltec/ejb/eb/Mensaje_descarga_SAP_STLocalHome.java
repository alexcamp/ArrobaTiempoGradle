package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Mensaje_descarga_SAP_ST
 */
public interface Mensaje_descarga_SAP_STLocalHome
	extends
		javax.ejb.EJBLocalHome {
	
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Mensaje_descarga_SAP_STLocalHome";
	/**
	 * Creates an instance from a key for Entity Bean: Mensaje_descarga_SAP_ST
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_descarga_SAP_STLocal create(
		java.lang.Long consecutivo) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Mensaje_descarga_SAP_ST
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_descarga_SAP_STLocal findByPrimaryKey(
		co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_descarga_SAP_STKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findByCod_ave_cd(java.lang.Long cod_ave_cd)
		throws javax.ejb.FinderException;
}