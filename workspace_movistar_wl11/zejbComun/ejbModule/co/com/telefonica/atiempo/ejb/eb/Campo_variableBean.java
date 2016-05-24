package co.com.telefonica.atiempo.ejb.eb;

import com.tecnonautica.utiles.db.DBManager;

/**
 * Bean implementation class for Enterprise Bean: Campo_variable
 */
public abstract class Campo_variableBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Campo_variableKey ejbCreate(
		java.lang.Short cv_id)
		throws javax.ejb.CreateException {
		setCv_id(cv_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Short cv_id)
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
	public Campo_variableKey ejbCreate(String cv_nombre_int,String cv_nombre_ext) throws javax.ejb.CreateException
	{
		DBManager manager=new DBManager();
		manager.setDataSource(DBManager.JDBC_VPISTBBA);
		setCv_id(new Short(manager.seqNextValShort("ATIEMPO.CORRELATIVO_CAMPO_VARIABLE")));
		setCv_nombre_int(cv_nombre_int);
		setCv_nombre_ext(cv_nombre_ext);
		return null;
	}
	
	public void ejbPostCreate(String cv_nombre_int,String cv_nombre_ext) throws javax.ejb.CreateException
	{
		
	}
	
	public co.com.telefonica.atiempo.ejb.eb.Campo_variableKey ejbCreate(
		java.lang.Short cv_id,
		java.lang.String cv_nombre_int,
		java.lang.String cv_nombre_ext)
		throws javax.ejb.CreateException {
		setCv_id(cv_id);
		setCv_nombre_int(cv_nombre_int);
		setCv_nombre_ext(cv_nombre_ext);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Short cv_id,
		java.lang.String cv_nombre_int,
		java.lang.String cv_nombre_ext)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: cv_id
	 */
	public abstract java.lang.Short getCv_id();
	/**
	 * Set accessor for persistent attribute: cv_id
	 */
	public abstract void setCv_id(java.lang.Short newCv_id);
	/**
	 * Get accessor for persistent attribute: cv_nombre_int
	 */
	public abstract java.lang.String getCv_nombre_int();
	/**
	 * Set accessor for persistent attribute: cv_nombre_int
	 */
	public abstract void setCv_nombre_int(java.lang.String newCv_nombre_int);
	/**
	 * Get accessor for persistent attribute: cv_nombre_ext
	 */
	public abstract java.lang.String getCv_nombre_ext();
	/**
	 * Set accessor for persistent attribute: cv_nombre_ext
	 */
	public abstract void setCv_nombre_ext(java.lang.String newCv_nombre_ext);
	/**
	 * This method was generated for supporting the relationship role named campo_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCampo_rol();
	/**
	 * This method was generated for supporting the relationship role named campo_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCampo_rol(java.util.Collection aCampo_rol);
	/**
	 * This method was generated for supporting the relationship role named campo_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getCampo_usuario();
	/**
	 * This method was generated for supporting the relationship role named campo_usuario.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCampo_usuario(java.util.Collection aCampo_usuario);
	/**
	 * This method was generated for supporting the relationship role named valor_variable.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getValor_variable();
	/**
	 * This method was generated for supporting the relationship role named valor_variable.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setValor_variable(
		java.util.Collection aValor_variable);
}
