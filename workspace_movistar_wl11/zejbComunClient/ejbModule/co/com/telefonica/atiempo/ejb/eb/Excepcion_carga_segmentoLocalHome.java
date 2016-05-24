package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Excepcion_carga_segmento
 */
public interface Excepcion_carga_segmentoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Excepcion_carga_segmento
	 */
	static final String JNDI_NAME = "ejb/co/com/telefonica/atiempo/ejb/eb/Excepcion_carga_segmentoLocalHome";

	public co.com.telefonica.atiempo.ejb.eb.Excepcion_carga_segmentoLocal create(java.lang.Long excs_id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Excepcion_carga_segmento
	 */
	public co.com.telefonica.atiempo.ejb.eb.Excepcion_carga_segmentoLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Excepcion_carga_segmentoKey primaryKey)
		throws javax.ejb.FinderException;

	public java.util.Collection findByPlantaComercial(String pCom) throws javax.ejb.FinderException;
}
