package co.com.telefonica.atiempo.ejb.eb;
import co.com.atiempo.dto.AgrupacionAtisDTO;
/**
 * Local Home interface for Enterprise Bean: Agrupacion_atis
 */
public interface Agrupacion_atisLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Agrupacion_atis
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Agrupacion_atisLocalHome";
	
	public co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisLocal create(
		java.lang.Integer cod_agr_sub_nu,
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Peticion_atisLocal argFk_pet_agrupacion)
		throws javax.ejb.CreateException;
	/**
	 * Creates an instance from a key for Entity Bean: Agrupacion_atis
	 */
	public co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisLocal create(
		java.lang.Integer cod_agr_sub_nu,
		java.lang.Long fk_pet_agrupacion_cod_pet_cd)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Agrupacion_atis
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Agrupacion_atisLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Agrupacion_atisLocal create(
		Peticion_atisLocal paLocal,
		AgrupacionAtisDTO atDto)
		throws javax.ejb.CreateException;
}
