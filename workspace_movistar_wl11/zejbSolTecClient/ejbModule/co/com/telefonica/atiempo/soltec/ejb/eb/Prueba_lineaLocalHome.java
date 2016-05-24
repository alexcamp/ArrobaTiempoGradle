package co.com.telefonica.atiempo.soltec.ejb.eb;
import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.Catalago_prueba_lineaLocal;
/**
 * Local Home interface for Enterprise Bean: Prueba_linea
 */
public interface Prueba_lineaLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Prueba_linea
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Prueba_lineaLocalHome";
	
	/**
	 * Finds an instance using a key for Entity Bean: Prueba_linea
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Prueba_lineaLocal findByPrimaryKey(
			co.com.telefonica.atiempo.soltec.ejb.eb.Prueba_lineaKey primaryKey)
		throws javax.ejb.FinderException;
	public java.util.Collection findAllPrueba_linea() throws javax.ejb.FinderException;
	public java.util.Collection findByPeticion(java.lang.Long idPeticion)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.soltec.ejb.eb.Prueba_lineaLocal create(
		java.lang.Long id_prueba,
		Peticion_stLocal peticion_stLocal,
		Catalago_prueba_lineaLocal cat,
		Long usua_id)
		throws javax.ejb.CreateException;
	public java.util.Collection findByPeticionOrderIdDesc(
		java.lang.Long idPeticion)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.soltec.ejb.eb.Prueba_lineaLocal findByMaxFecha(
		java.lang.Long codAve) throws javax.ejb.FinderException;
}
