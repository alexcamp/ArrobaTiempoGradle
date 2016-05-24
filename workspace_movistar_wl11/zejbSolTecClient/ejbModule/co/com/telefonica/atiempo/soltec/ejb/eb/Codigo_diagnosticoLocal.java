package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Codigo_diagnostico
 */
public interface Codigo_diagnosticoLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: cod_tipo_def
	 */
	public java.lang.String getCod_tipo_def();
	/**
	 * Set accessor for persistent attribute: cod_tipo_def
	 */
	public void setCod_tipo_def(java.lang.String newCod_tipo_def);
	/**
	 * Get accessor for persistent attribute: desc_tipo_def
	 */
	public java.lang.String getDesc_tipo_def();
	/**
	 * Set accessor for persistent attribute: desc_tipo_def
	 */
	public void setDesc_tipo_def(java.lang.String newDesc_tipo_def);
	/**
	 * Get accessor for persistent attribute: desc_def_aper
	 */
	public java.lang.String getDesc_def_aper();
	/**
	 * Set accessor for persistent attribute: desc_def_aper
	 */
	public void setDesc_def_aper(java.lang.String newDesc_def_aper);
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public java.util.Collection getPeticion_st();
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setPeticion_st(java.util.Collection aPeticion_st);
}
