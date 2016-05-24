package co.com.telefonica.atiempo.ejb.eb;
import javax.ejb.CreateException;

import co.com.atiempo.dto.DireccionAtisDTO;
/**
 * Local Home interface for Enterprise Bean: Direccion_atis
 */
public interface Direccion_atisLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Direccion_atis
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Direccion_atisLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Direccion_atisLocal create(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Agrupacion_atisLocal argFk_01_direc_atis)
		throws javax.ejb.CreateException;
	/**
	 * Creates an instance from a key for Entity Bean: Direccion_atis
	 */
	public co.com.telefonica.atiempo.ejb.eb.Direccion_atisLocal create(
		java.lang.Integer fk_01_direc_atis_cod_agr_sub_nu,
		java.lang.Long fk_01_direc_atis_fk_pet_agrupacion_cod_pet_cd)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Direccion_atis
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Direccion_atisLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Direccion_atisKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * ejbCreate
	 */

	public co.com.telefonica.atiempo.ejb.eb.Direccion_atisLocal create(
		Agrupacion_atisLocal atLocal,
		DireccionAtisDTO daDto,
		DepartamentoLocal dpLocal,
		LocalidadLocal locLocal)
		throws CreateException;
	public co.com.telefonica.atiempo.ejb.eb.Direccion_atisLocal findByDireccionDestino(java.lang.Long cod_pet_cd, java.lang.Long cod_arg_sub_nu) throws javax.ejb.FinderException;
}
