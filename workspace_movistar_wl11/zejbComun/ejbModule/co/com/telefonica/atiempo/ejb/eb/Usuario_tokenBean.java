package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Usuario_token
 */
public abstract class Usuario_tokenBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Usuario_tokenKey ejbCreate(
		java.lang.String usua_login)
		throws javax.ejb.CreateException {
		setUsua_login(usua_login);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.String usua_login)
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
	 * Get accessor for persistent attribute: usua_login
	 */
	public abstract java.lang.String getUsua_login();
	/**
	 * Set accessor for persistent attribute: usua_login
	 */
	public abstract void setUsua_login(java.lang.String newUsua_login);
	/**
	 * Get accessor for persistent attribute: usua_token
	 */
	public abstract java.lang.String getUsua_token();
	/**
	 * Set accessor for persistent attribute: usua_token
	 */
	public abstract void setUsua_token(java.lang.String newUsua_token);
	/**
	 * Get accessor for persistent attribute: marca_hora
	 */
	public abstract java.sql.Timestamp getMarca_hora();
	/**
	 * Set accessor for persistent attribute: marca_hora
	 */
	public abstract void setMarca_hora(java.sql.Timestamp newMarca_hora);
}
