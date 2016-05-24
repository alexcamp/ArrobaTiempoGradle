package co.com.telefonica.atiempo.ejb.eb;
import co.com.atiempo.dto.SubpeticionAtisDTO;
/**
 * Local Home interface for Enterprise Bean: Subpeticion_atis
 */
public interface Subpeticion_atisLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Subpeticion_atis
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Subpeticion_atisLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisLocal create(
		java.lang.Integer cod_sub_cd,
		co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisLocal argFk_agru_sub)
		throws javax.ejb.CreateException;
	/**
	 * Creates an instance from a key for Entity Bean: Subpeticion_atis
	 */
	public co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisLocal create(
		java.lang.Integer cod_sub_cd,
		java.lang.Integer fk_agru_sub_cod_agr_sub_nu,
		java.lang.Long fk_agru_sub_fk_pet_agrupacion_cod_pet_cd)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Subpeticion_atis
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Subpeticion_atisLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * ejbCreate
	 */

	public co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisLocal create(
		Agrupacion_atisLocal agrupacion_atisLocal,
		SubpeticionAtisDTO dto)
		throws javax.ejb.CreateException;
}
