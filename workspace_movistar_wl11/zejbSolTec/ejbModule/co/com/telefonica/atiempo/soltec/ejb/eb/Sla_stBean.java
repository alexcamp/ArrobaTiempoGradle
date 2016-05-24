package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Sla_st
 */
public abstract class Sla_stBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.soltec.ejb.eb.Sla_stKey ejbCreate(
		java.lang.Long cod_sgm,
		java.lang.Long cod_sb_sgm,
		java.lang.String ide_pro_cmr,
		java.lang.Long tipo_loc) throws javax.ejb.CreateException {
		setCod_sgm(cod_sgm);
		setCod_sb_sgm(cod_sb_sgm);
		setIde_pro_cmr(ide_pro_cmr);
		setTipo_loc(tipo_loc);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long cod_sgm,
		java.lang.Long cod_sb_sgm,
		java.lang.String ide_pro_cmr,
		java.lang.Long tipo_loc) throws javax.ejb.CreateException {
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
	 * Get accessor for persistent attribute: cod_sgm
	 */
	public abstract java.lang.Long getCod_sgm();
	/**
	 * Set accessor for persistent attribute: cod_sgm
	 */
	public abstract void setCod_sgm(java.lang.Long newCod_sgm);
	/**
	 * Get accessor for persistent attribute: cod_sb_sgm
	 */
	public abstract java.lang.Long getCod_sb_sgm();
	/**
	 * Set accessor for persistent attribute: cod_sb_sgm
	 */
	public abstract void setCod_sb_sgm(java.lang.Long newCod_sb_sgm);
	/**
	 * Get accessor for persistent attribute: ide_pro_cmr
	 */
	public abstract java.lang.String getIde_pro_cmr();
	/**
	 * Set accessor for persistent attribute: ide_pro_cmr
	 */
	public abstract void setIde_pro_cmr(java.lang.String newIde_pro_cmr);
	/**
	 * Get accessor for persistent attribute: tipo_loc
	 */
	public abstract java.lang.Long getTipo_loc();
	/**
	 * Set accessor for persistent attribute: tipo_loc
	 */
	public abstract void setTipo_loc(java.lang.Long newTipo_loc);
	/**
	 * Get accessor for persistent attribute: sla1
	 */
	public abstract java.lang.Integer getSla1();
	/**
	 * Set accessor for persistent attribute: sla1
	 */
	public abstract void setSla1(java.lang.Integer newSla1);
	/**
	 * Get accessor for persistent attribute: sla2
	 */
	public abstract java.lang.Integer getSla2();
	/**
	 * Set accessor for persistent attribute: sla2
	 */
	public abstract void setSla2(java.lang.Integer newSla2);
	/**
	 * Get accessor for persistent attribute: prioridad
	 */
	public abstract java.lang.Integer getPrioridad();
	/**
	 * Set accessor for persistent attribute: prioridad
	 */
	public abstract void setPrioridad(java.lang.Integer newPrioridad);
}
