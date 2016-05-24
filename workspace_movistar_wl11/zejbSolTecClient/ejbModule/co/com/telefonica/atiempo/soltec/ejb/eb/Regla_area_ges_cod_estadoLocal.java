package co.com.telefonica.atiempo.soltec.ejb.eb;
/**
 * Local interface for Enterprise Bean: Regla_area_ges_cod_estado
 */
public interface Regla_area_ges_cod_estadoLocal
	extends javax.ejb.EJBLocalObject {
	/**
	 * This method was generated for supporting the relationship role named area_gestion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Area_gestionLocal getArea_gestion();
	/**
	 * This method was generated for supporting the relationship role named area_gestion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setArea_gestion(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Area_gestionLocal anArea_gestion);
	/**
	 * This method was generated for supporting the relationship role named codigo_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Codigo_stLocal getCodigo_st();
	/**
	 * This method was generated for supporting the relationship role named codigo_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setCodigo_st(
		co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_stLocal aCodigo_st);
	/**
	 * This method was generated for supporting the relationship role named regla.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.ReglaLocal getRegla();
	/**
	 * This method was generated for supporting the relationship role named regla.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setRegla(
		co.com.telefonica.atiempo.soltec.ejb.eb.ReglaLocal aRegla);
}
