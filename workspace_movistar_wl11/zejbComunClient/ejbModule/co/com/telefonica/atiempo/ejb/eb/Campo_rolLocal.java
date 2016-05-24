package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local interface for Enterprise Bean: Campo_rol
 */
public interface Campo_rolLocal extends javax.ejb.EJBLocalObject {
	/**
	 * This method was generated for supporting the relationship role named fk_camrol_2_cam.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Campo_variableLocal getFk_camrol_2_cam();
	/**
	 * This method was generated for supporting the relationship role named fk_camrol_2_cam.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_camrol_2_cam(
		co.com.telefonica.atiempo.ejb.eb.Campo_variableLocal aFk_camrol_2_cam);
	/**
	 * This method was generated for supporting the relationship role named fk_camrol_2_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public co.com.telefonica.atiempo.ejb.eb.RolLocal getFk_camrol_2_rol();
	/**
	 * This method was generated for supporting the relationship role named fk_camrol_2_rol.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public void setFk_camrol_2_rol(
		co.com.telefonica.atiempo.ejb.eb.RolLocal aFk_camrol_2_rol);
}
