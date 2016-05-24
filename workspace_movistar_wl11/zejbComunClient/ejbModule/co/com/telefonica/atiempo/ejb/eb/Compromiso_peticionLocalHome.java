package co.com.telefonica.atiempo.ejb.eb;

import java.util.Collection;
import java.util.Date;
/**
 * Local Home interface for Enterprise Bean: Compromiso_peticion
 */
public interface Compromiso_peticionLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Compromiso_peticion
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Compromiso_peticionLocalHome";
	public co.com.telefonica.atiempo.ejb.eb.Compromiso_peticionLocal create(
		java.lang.Long compr_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Compromiso_peticion
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Compromiso_peticionLocal findByPrimaryKey(
			co.com.telefonica.atiempo.ejb.eb.Compromiso_peticionKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * Creates an instance from a key for Entity Bean: Compromiso_peticion
	 */
	public co.com.telefonica.atiempo.ejb.eb.Compromiso_peticionLocal create(
		java.lang.Long compr_id,
		java.lang.Integer grse_id)
		throws javax.ejb.CreateException;
	/**
	 * @param numPetico
	 * @return
	 */
	public Collection findByPeticion(Long numPetico) throws javax.ejb.FinderException;
	/**
	 * @param idPetico
	 * @return
	 */
	public Compromiso_peticionLocal findAgendamientoActivo(Long idPetico) throws javax.ejb.FinderException;
	public co.com.telefonica.atiempo.ejb.eb.Compromiso_peticionLocal create(
	Long idCompromiso,
		Long idTipoAgenda,
		Integer idRango,
		Long numPetico,
		String pComercial,
		Date fecCompromiso,
		String hDesde,
		String hHasta,
		String userMac,
		String codAgencia,
		Integer idGrupoSeg,
		Integer idCausaCierre)
		throws javax.ejb.CreateException;
}
