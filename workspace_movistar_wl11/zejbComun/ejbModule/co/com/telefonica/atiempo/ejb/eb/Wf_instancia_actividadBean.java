package co.com.telefonica.atiempo.ejb.eb;

import com.telefonica_chile.atiempo.utiles.Fecha;

/**
 * Bean implementation class for Enterprise Bean: Wf_instancia_actividad
 */
public abstract class Wf_instancia_actividadBean
	implements javax.ejb.EntityBean {
	private javax.ejb.EntityContext myEntityCtx;
	/**
	 * setEntityContext
	 */
	public void setEntityContext(javax.ejb.EntityContext ctx) {
		myEntityCtx = ctx;
	}
	/**
	 * getEntityContext
	 */
	public javax.ejb.EntityContext getEntityContext() {
		return myEntityCtx;
	}
	/**
	 * unsetEntityContext
	 */
	public void unsetEntityContext() {
		myEntityCtx = null;
	}
	/**
	 * ejbCreate
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Wf_instancia_actividadKey ejbCreate(
		java.lang.String id_instancia_actividad)
		throws javax.ejb.CreateException {
		setId_instancia_actividad(id_instancia_actividad);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.String id_instancia_actividad)
		throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbLoad
	 */
	public void ejbLoad() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() throws javax.ejb.RemoveException {
	}
	/**
	 * ejbStore
	 */
	public void ejbStore() {
	}
	/**
	 * ejbCreate method for a CMP entity bean.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Wf_instancia_actividadKey ejbCreate(
		java.lang.String id_instancia_actividad,
		java.lang.String id_proceso,
		java.lang.Long id_instancia_proceso,
		java.lang.String codigo_actividad,
		java.lang.String xmlparams)
		throws javax.ejb.CreateException {
		setId_instancia_actividad(id_instancia_actividad);
		setId_proceso(id_proceso);
		setId_instancia_proceso(id_instancia_proceso);
		setCodigo_actividad(codigo_actividad);
		setXmlparams(xmlparams);
		setEstado(new Integer(0));
		setFecha_activacion(new Fecha().getTimestamp());
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.String id_instancia_actividad,
		java.lang.String id_proceso,
		java.lang.Long id_instancia_proceso,
		java.lang.String codigo_actividad,
		java.lang.String xmlparams)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: estado
	 */
	public abstract java.lang.Integer getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public abstract void setEstado(java.lang.Integer newEstado);
	/**
	 * Get accessor for persistent attribute: id_proceso
	 */
	public abstract java.lang.String getId_proceso();
	/**
	 * Set accessor for persistent attribute: id_proceso
	 */
	public abstract void setId_proceso(java.lang.String newId_proceso);
	/**
	 * Get accessor for persistent attribute: id_instancia_proceso
	 */
	public abstract java.lang.Long getId_instancia_proceso();
	/**
	 * Set accessor for persistent attribute: id_instancia_proceso
	 */
	public abstract void setId_instancia_proceso(
		java.lang.Long newId_instancia_proceso);
	/**
	 * Get accessor for persistent attribute: codigo_actividad
	 */
	public abstract java.lang.String getCodigo_actividad();
	/**
	 * Set accessor for persistent attribute: codigo_actividad
	 */
	public abstract void setCodigo_actividad(
		java.lang.String newCodigo_actividad);
	/**
	 * Get accessor for persistent attribute: id_instancia_actividad
	 */
	public abstract java.lang.String getId_instancia_actividad();
	/**
	 * Set accessor for persistent attribute: id_instancia_actividad
	 */
	public abstract void setId_instancia_actividad(
		java.lang.String newId_instancia_actividad);
	/**
	 * Get accessor for persistent attribute: xmlparams
	 */
	public abstract java.lang.String getXmlparams();
	/**
	 * Set accessor for persistent attribute: xmlparams
	 */
	public abstract void setXmlparams(java.lang.String newXmlparams);
	/**
	 * Get accessor for persistent attribute: fecha_activacion
	 */
	public abstract java.sql.Timestamp getFecha_activacion();
	/**
	 * Set accessor for persistent attribute: fecha_activacion
	 */
	public abstract void setFecha_activacion(
		java.sql.Timestamp newFecha_activacion);
}
