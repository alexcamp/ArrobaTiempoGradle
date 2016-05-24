package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Horario
 */
public interface HorarioLocal extends javax.ejb.EJBLocalObject {
	/**
	 * Get accessor for persistent attribute: hr_hora_ini
	 */
	public java.lang.Integer getHr_hora_ini();
	/**
	 * Set accessor for persistent attribute: hr_hora_ini
	 */
	public void setHr_hora_ini(java.lang.Integer newHr_hora_ini);
	/**
	 * Get accessor for persistent attribute: hr_minu_ini
	 */
	public java.lang.Integer getHr_minu_ini();
	/**
	 * Set accessor for persistent attribute: hr_minu_ini
	 */
	public void setHr_minu_ini(java.lang.Integer newHr_minu_ini);
	/**
	 * Get accessor for persistent attribute: hr_hora_fin
	 */
	public java.lang.Integer getHr_hora_fin();
	/**
	 * Set accessor for persistent attribute: hr_hora_fin
	 */
	public void setHr_hora_fin(java.lang.Integer newHr_hora_fin);
	/**
	 * Get accessor for persistent attribute: hr_minu_fin
	 */
	public java.lang.Integer getHr_minu_fin();
	/**
	 * Set accessor for persistent attribute: hr_minu_fin
	 */
	public void setHr_minu_fin(java.lang.Integer newHr_minu_fin);
	/**
	 * Get accessor for persistent attribute: hr_hh_trabajo
	 */
	public java.lang.Integer getHr_hh_trabajo();
	/**
	 * Set accessor for persistent attribute: hr_hh_trabajo
	 */
	public void setHr_hh_trabajo(java.lang.Integer newHr_hh_trabajo);
	/**
	 * This method was generated for supporting the relationship role named aplicacion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.AplicacionLocal getAplicacion();
	/**
	 * This method was generated for supporting the relationship role named aplicacion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setAplicacion(
		co.com.telefonica.atiempo.ejb.eb.AplicacionLocal anAplicacion);
	/**
	 * This method was generated for supporting the relationship role named rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.RolLocal getRol();
	/**
	 * This method was generated for supporting the relationship role named rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setRol(co.com.telefonica.atiempo.ejb.eb.RolLocal aRol);
}
