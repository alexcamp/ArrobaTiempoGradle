package co.com.telefonica.atiempo.ejb.eb;
/**
 * Local Home interface for Enterprise Bean: Peticion
 */
public interface PeticionLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Peticion
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/PeticionLocalHome";
	 
	/**
	 * Finds an instance using a key for Entity Bean: Peticion
	 */
	public co.com.telefonica.atiempo.ejb.eb.PeticionLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.PeticionKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * ejbCreate
	 */

	public co.com.telefonica.atiempo.ejb.eb.PeticionLocal create(
		long petinumero,
		Peticion_atisLocal peticion_atisLocal,
		LocalidadLocal localidadLocal,
		DepartamentoLocal departamentoLocal)
		throws javax.ejb.CreateException;
	public java.util.Collection findByCliente(java.lang.String rutCli, java.lang.String rutDv) throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Peticion
	 */
	public co.com.telefonica.atiempo.ejb.eb.PeticionLocal create(
		java.lang.Long peti_numero)
		throws javax.ejb.CreateException;
	public java.util.Collection findbyidps(java.lang.String num_ide_nu_stb)
		throws javax.ejb.FinderException;
	public java.util.Collection findbypetitv(java.lang.String num_ide_nu_tv)
		throws javax.ejb.FinderException;
	public java.util.Collection findByPetOpenAndIdPC(java.lang.Integer espe_id, java.lang.String num_ide_nu_stb) throws javax.ejb.FinderException;

	public co.com.telefonica.atiempo.ejb.eb.PeticionLocal findByPetiNumero(java.lang.Long peti_numero) throws javax.ejb.FinderException;
}
