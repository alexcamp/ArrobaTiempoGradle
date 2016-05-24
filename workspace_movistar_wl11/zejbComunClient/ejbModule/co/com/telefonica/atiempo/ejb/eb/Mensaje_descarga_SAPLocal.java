package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Mensaje_descarga_SAP
 */
public interface Mensaje_descarga_SAPLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: id_atis
	 */
	public java.lang.Long getId_peti();
	
	public void setId_peti(java.lang.Long newId_peti);
	
	public java.lang.Long getId_atis();
	/**
	 * Set accessor for persistent attribute: id_atis
	 */
	public void setId_atis(java.lang.Long newId_atis);
	/**
	 * Get accessor for persistent attribute: est_pedido
	 */
	public java.lang.String getEst_pedido();
	/**
	 * Set accessor for persistent attribute: est_pedido
	 */
	public void setEst_pedido(java.lang.String newEst_pedido);
	/**
	 * Get accessor for persistent attribute: fecha_eje_sap
	 */
	public java.lang.String getFecha_eje_sap();
	/**
	 * Set accessor for persistent attribute: fecha_eje_sap
	 */
	public void setFecha_eje_sap(java.lang.String newFecha_eje_sap);
	/**
	 * Get accessor for persistent attribute: id_cod_mat
	 */
	public java.lang.String getId_cod_mat();
	/**
	 * Set accessor for persistent attribute: id_cod_mat
	 */
	public void setId_cod_mat(java.lang.String newId_cod_mat);
	/**
	 * Get accessor for persistent attribute: fecha
	 */
	public java.lang.String getFecha();
	/**
	 * Set accessor for persistent attribute: fecha
	 */
	public void setFecha(java.lang.String newFecha);
	/**
	 * Get accessor for persistent attribute: motivo
	 */
	public java.lang.String getMotivo();
	/**
	 * Set accessor for persistent attribute: motivo
	 */
	public void setMotivo(java.lang.String newMotivo);
	/**
	 * Get accessor for persistent attribute: doc_mat
	 */
	public java.lang.String getDoc_mat();
	/**
	 * Set accessor for persistent attribute: doc_mat
	 */
	public void setDoc_mat(java.lang.String newDoc_mat);
	/**
	 * Get accessor for persistent attribute: fecha_doc
	 */
	public java.lang.String getFecha_doc();
	/**
	 * Set accessor for persistent attribute: fecha_doc
	 */
	public void setFecha_doc(java.lang.String newFecha_doc);
	/**
	 * Get accessor for persistent attribute: hora_eje_inter
	 */
	public java.lang.String getHora_eje_inter();
	/**
	 * Set accessor for persistent attribute: hora_eje_inter
	 */
	public void setHora_eje_inter(java.lang.String newHora_eje_inter);
}