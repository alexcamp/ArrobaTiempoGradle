package co.com.telefonica.atiempo.ejb.eb;
import java.sql.Timestamp;

import javax.ejb.CreateException;
/**
 * Local Home interface for Enterprise Bean: Tecnico_peticion
 */
public interface Tecnico_peticionLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Tecnico_peticion
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Tecnico_peticionLocalHome";
	
	/**
	 * Finds an instance using a key for Entity Bean: Tecnico_peticion
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Tecnico_peticionLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionLocal create(
		Long tepe_id,
		TecnicoLocal tecnicoLocal)
		throws javax.ejb.CreateException;
	public java.util.Collection finderByTecnicoAndFecha(java.lang.String codTecnico, java.sql.Timestamp fecha) throws javax.ejb.FinderException;
	public java.util.Collection finderByPeticionyAp(
		java.lang.Long nroPeticion,
		java.lang.Long apId)
		throws javax.ejb.FinderException;
	/**
	 * @param id
	 * @param idPeticion
	 * @param idTecnico
	 * @param fechaDia
	 * @param horaDesde
	 * @param horaHasta
	 */
	public co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionLocal create(Long id, Long idPeticion, TecnicoLocal tecnicoLocal)
	throws javax.ejb.CreateException;

	public co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionLocal create(
		Long tepe_id,
		Long idPeticion,
		TecnicoLocal tecnicoLocal,
		Integer estado,
		Timestamp fecha,
		String horaDesde,
		String horaHasta,
		RangoLocal rango)
		throws CreateException;
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Tecnico_peticionLocal findByPetiAsigAp(
		java.lang.Long nroPeticion,
		java.lang.Long apId)
		throws javax.ejb.FinderException;
}
