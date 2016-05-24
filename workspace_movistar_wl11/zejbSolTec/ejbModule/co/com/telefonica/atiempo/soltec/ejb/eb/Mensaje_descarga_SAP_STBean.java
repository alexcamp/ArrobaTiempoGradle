package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Bean implementation class for Enterprise Bean: Mensaje_descarga_SAP_ST
 */
public abstract class Mensaje_descarga_SAP_STBean
	implements
		javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_descarga_SAP_STKey ejbCreate(
		java.lang.Long consecutivo) throws javax.ejb.CreateException {
		setConsecutivo(consecutivo);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long consecutivo)
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
	 * Get accessor for persistent attribute: consecutivo
	 */
	public abstract java.lang.Long getConsecutivo();
	/**
	 * Set accessor for persistent attribute: consecutivo
	 */
	public abstract void setConsecutivo(java.lang.Long newConsecutivo);
	/**
	 * Get accessor for persistent attribute: cod_ave_cd
	 */
	public abstract java.lang.Long getCod_ave_cd();
	/**
	 * Set accessor for persistent attribute: cod_ave_cd
	 */
	public abstract void setCod_ave_cd(java.lang.Long newCod_ave_cd);
	/**
	 * Get accessor for persistent attribute: est_pedido
	 */
	public abstract java.lang.String getEst_pedido();
	/**
	 * Set accessor for persistent attribute: est_pedido
	 */
	public abstract void setEst_pedido(java.lang.String newEst_pedido);
	/**
	 * Get accessor for persistent attribute: fecha_eje_sap
	 */
	public abstract java.lang.String getFecha_eje_sap();
	/**
	 * Set accessor for persistent attribute: fecha_eje_sap
	 */
	public abstract void setFecha_eje_sap(java.lang.String newFecha_eje_sap);
	/**
	 * Get accessor for persistent attribute: id_cod_mat
	 */
	public abstract java.lang.String getId_cod_mat();
	/**
	 * Set accessor for persistent attribute: id_cod_mat
	 */
	public abstract void setId_cod_mat(java.lang.String newId_cod_mat);
	/**
	 * Get accessor for persistent attribute: fecha
	 */
	public abstract java.lang.String getFecha();
	/**
	 * Set accessor for persistent attribute: fecha
	 */
	public abstract void setFecha(java.lang.String newFecha);
	/**
	 * Get accessor for persistent attribute: motivo
	 */
	public abstract java.lang.String getMotivo();
	/**
	 * Set accessor for persistent attribute: motivo
	 */
	public abstract void setMotivo(java.lang.String newMotivo);
	/**
	 * Get accessor for persistent attribute: doc_mat
	 */
	public abstract java.lang.String getDoc_mat();
	/**
	 * Set accessor for persistent attribute: doc_mat
	 */
	public abstract void setDoc_mat(java.lang.String newDoc_mat);
	/**
	 * Get accessor for persistent attribute: fecha_doc
	 */
	public abstract java.lang.String getFecha_doc();
	/**
	 * Set accessor for persistent attribute: fecha_doc
	 */
	public abstract void setFecha_doc(java.lang.String newFecha_doc);
	/**
	 * Get accessor for persistent attribute: hora_eje_inter
	 */
	public abstract java.lang.String getHora_eje_inter();
	/**
	 * Set accessor for persistent attribute: hora_eje_inter
	 */
	public abstract void setHora_eje_inter(java.lang.String newHora_eje_inter);
}