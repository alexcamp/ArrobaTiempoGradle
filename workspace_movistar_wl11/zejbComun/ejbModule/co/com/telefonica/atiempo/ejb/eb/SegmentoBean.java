package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Segmento
 */
public abstract class SegmentoBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.SegmentoKey ejbCreate(
		java.lang.Long segm_id)
		throws javax.ejb.CreateException {
		setSegm_id(segm_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long segm_id)
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
	public co.com.telefonica.atiempo.ejb.eb.SegmentoKey ejbCreate(
		java.lang.Long segm_id,
		java.lang.String segm_descripcion,
		java.lang.String segm_codigo)
		throws javax.ejb.CreateException {
		setSegm_id(segm_id);
		setSegm_descripcion(segm_descripcion);
		setSegm_codigo(segm_codigo);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long segm_id,
		java.lang.String segm_descripcion,
		java.lang.String segm_codigo)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: segm_id
	 */
	public abstract java.lang.Long getSegm_id();
	/**
	 * Set accessor for persistent attribute: segm_id
	 */
	public abstract void setSegm_id(java.lang.Long newSegm_id);
	/**
	 * Get accessor for persistent attribute: fase_id
	 */
	public abstract java.lang.Long getFase_id();
	/**
	 * Set accessor for persistent attribute: fase_id
	 */
	public abstract void setFase_id(java.lang.Long newFase_id);
	/**
	 * Get accessor for persistent attribute: segm_descripcion
	 */
	public abstract java.lang.String getSegm_descripcion();
	/**
	 * Set accessor for persistent attribute: segm_descripcion
	 */
	public abstract void setSegm_descripcion(
		java.lang.String newSegm_descripcion);
	/**
	 * Get accessor for persistent attribute: segm_codigo
	 */
	public abstract java.lang.String getSegm_codigo();
	/**
	 * Set accessor for persistent attribute: segm_codigo
	 */
	public abstract void setSegm_codigo(java.lang.String newSegm_codigo);
	/**
	 * Get accessor for persistent attribute: segm_negocia
	 */
	public abstract java.lang.String getSegm_negocia();
	/**
	 * Set accessor for persistent attribute: segm_negocia
	 */
	public abstract void setSegm_negocia(java.lang.String newSegm_negocia);
	/**
	 * Get accessor for persistent attribute: segm_tipo
	 */
	public abstract java.lang.String getSegm_tipo();
	/**
	 * Set accessor for persistent attribute: segm_tipo
	 */
	public abstract void setSegm_tipo(java.lang.String newSegm_tipo);
	/**
	 * Get accessor for persistent attribute: segm_autoprueba
	 */
	public abstract java.lang.String getSegm_autoprueba();
	/**
	 * Set accessor for persistent attribute: segm_autoprueba
	 */
	public abstract void setSegm_autoprueba(
		java.lang.String newSegm_autoprueba);
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getBintegrada();
	/**
	 * This method was generated for supporting the relationship role named bintegrada.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setBintegrada(java.util.Collection aBintegrada);
	/**
	 * This method was generated for supporting the relationship role named subsegmento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getSubsegmento();
	/**
	 * This method was generated for supporting the relationship role named subsegmento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setSubsegmento(java.util.Collection aSubsegmento);
}
