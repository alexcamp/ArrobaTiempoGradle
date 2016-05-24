package co.com.telefonica.atiempo.ejb.eb;

import java.util.Collection;

/**
 * Local Home interface for Enterprise Bean: Excepcion_tipo_agendamiento
 */
public interface Excepcion_tipo_agendamientoLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Excepcion_tipo_agendamiento
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Excepcion_tipo_agendamientoLocalHome";
	 
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Excepcion_tipo_agendamientoLocal create(
		java.lang.Long exta_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Excepcion_tipo_agendamiento
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Excepcion_tipo_agendamientoLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Excepcion_tipo_agendamientoKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * @param integer
	 * @param string
	 * @param idFamPS
	 * @param tipoTrabajo
	 * @param string2
	 * @param string3
	 * @param string4
	 * @param string5
	 * @return
	 */
	public Collection findTipoAgendamiento(Integer integer, String string, Long idFamPS, String tipoTrabajo, String string2, String string3, String string4, String string5) throws javax.ejb.FinderException;
}
