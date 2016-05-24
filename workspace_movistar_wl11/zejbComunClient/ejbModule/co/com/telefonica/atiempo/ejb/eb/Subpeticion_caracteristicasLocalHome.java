package co.com.telefonica.atiempo.ejb.eb;
import co.com.atiempo.dto.SubpeticionCaracteristicasDTO;
/**
 * Local Home interface for Enterprise Bean: Subpeticion_caracteristicas
 */
public interface Subpeticion_caracteristicasLocalHome
	extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Subpeticion_caracteristicas
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Subpeticion_caracteristicasLocalHome";
	 
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Subpeticion_caracteristicasLocal create(
		java.lang.Long cod_crc_cd,
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Subpeticion_atisLocal argFk_01_subpetcar)
		throws javax.ejb.CreateException;
	/**
	 * Creates an instance from a key for Entity Bean: Subpeticion_caracteristicas
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Subpeticion_caracteristicasLocal create(
		java.lang.Long cod_crc_cd,
		java.lang.Integer fk_01_subpetcar_cod_sub_cd,
		java.lang.Integer fk_01_subpetcar_fk_agru_sub_cod_agr_sub_nu,
		java
			.lang
			.Long fk_01_subpetcar_fk_agru_sub_fk_pet_agrupacion_cod_pet_cd)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Subpeticion_caracteristicas
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Subpeticion_caracteristicasLocal findByPrimaryKey(
			co
				.com
				.telefonica
				.atiempo
				.ejb
				.eb
				.Subpeticion_caracteristicasKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * ejbCreate
	 */

	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Subpeticion_caracteristicasLocal create(
		Subpeticion_atisLocal argFk_01_subpetcar,
		SubpeticionCaracteristicasDTO dto)
		throws javax.ejb.CreateException;
}
