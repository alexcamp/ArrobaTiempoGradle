package co.com.telefonica.atiempo.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Mapeo_loc_apsc
 */
public abstract class Mapeo_loc_apscBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Mapeo_loc_apscKey ejbCreate(
		java.lang.Long cod_loc,
		java.lang.String nom_sub_loc) throws javax.ejb.CreateException {
		setCod_loc(cod_loc);
		setNom_sub_loc(nom_sub_loc);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long cod_loc,
		java.lang.String nom_sub_loc) throws javax.ejb.CreateException {
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
	 * Get accessor for persistent attribute: cod_loc
	 */
	public abstract java.lang.Long getCod_loc();
	/**
	 * Set accessor for persistent attribute: cod_loc
	 */
	public abstract void setCod_loc(java.lang.Long newCod_loc);
	/**
	 * Get accessor for persistent attribute: cod_loc_construida
	 */
	public abstract java.lang.Long getCod_loc_construida();
	/**
	 * Set accessor for persistent attribute: cod_loc_construida
	 */
	public abstract void setCod_loc_construida(
		java.lang.Long newCod_loc_construida);
	/**
	 * Get accessor for persistent attribute: desc_loc_construida
	 */
	public abstract java.lang.String getDesc_loc_construida();
	/**
	 * Set accessor for persistent attribute: desc_loc_construida
	 */
	public abstract void setDesc_loc_construida(
		java.lang.String newDesc_loc_construida);
	/**
	 * Get accessor for persistent attribute: cod_sub_loc
	 */
	public abstract java.lang.Long getCod_sub_loc();
	/**
	 * Set accessor for persistent attribute: cod_sub_loc
	 */
	public abstract void setCod_sub_loc(java.lang.Long newCod_sub_loc);
	/**
	 * Get accessor for persistent attribute: nom_sub_loc
	 */
	public abstract java.lang.String getNom_sub_loc();
	/**
	 * Set accessor for persistent attribute: nom_sub_loc
	 */
	public abstract void setNom_sub_loc(java.lang.String newNom_sub_loc);
	/**
	 * Get accessor for persistent attribute: cod_loc_apsc
	 */
	public abstract java.lang.Long getCod_loc_apsc();
	/**
	 * Set accessor for persistent attribute: cod_loc_apsc
	 */
	public abstract void setCod_loc_apsc(java.lang.Long newCod_loc_apsc);
	/**
	 * Get accessor for persistent attribute: nom_sloc_apsc
	 */
	public abstract java.lang.String getNom_sloc_apsc();
	/**
	 * Set accessor for persistent attribute: nom_sloc_apsc
	 */
	public abstract void setNom_sloc_apsc(java.lang.String newNom_sloc_apsc);
}
