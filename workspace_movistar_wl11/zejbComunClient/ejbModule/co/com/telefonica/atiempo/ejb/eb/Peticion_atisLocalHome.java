package co.com.telefonica.atiempo.ejb.eb;

import co.com.atiempo.dto.PeticionAtisDTO;

/**
 * Local Home interface for Enterprise Bean: Peticion_atis
 */
public interface Peticion_atisLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Peticion_atis
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Peticion_atisLocalHome";
	
	/**
	 * Finds an instance using a key for Entity Bean: Peticion_atis
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Peticion_atisLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Peticion_atisKey primaryKey)
		throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocal create(
		PeticionAtisDTO dto)
		throws javax.ejb.CreateException;
	/**
	 * Creates an instance from a key for Entity Bean: Peticion_atis
	 */
	public co.com.telefonica.atiempo.ejb.eb.Peticion_atisLocal create(
		java.lang.Long cod_pet_cd) throws javax.ejb.CreateException;
}
