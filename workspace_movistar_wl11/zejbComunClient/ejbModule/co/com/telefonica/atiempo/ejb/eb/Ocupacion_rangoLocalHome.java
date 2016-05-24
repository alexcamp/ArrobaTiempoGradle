package co.com.telefonica.atiempo.ejb.eb;

import java.util.Collection;
import java.util.Date;

/**
 * Local Home interface for Enterprise Bean: Ocupacion_rango
 */
public interface Ocupacion_rangoLocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Ocupacion_rango
	 */
	 static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/ejb/eb/Ocupacion_rangoLocalHome";
	 
	public co.com.telefonica.atiempo.ejb.eb.Ocupacion_rangoLocal create(
		java.lang.Long ocup_id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Ocupacion_rango
	 */
	public co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Ocupacion_rangoLocal findByPrimaryKey(
		co.com.telefonica.atiempo.ejb.eb.Ocupacion_rangoKey primaryKey)
		throws javax.ejb.FinderException;
	/**
	 * @param idFam
	 * @param string
	 * @param fecMin
	 * @param fecMax
	 * @return
	 */
	public Collection findByFamiliaAgenciaFecha(Long idFam, String string, Date fecMin, Date fecMax) throws javax.ejb.FinderException;;
	/**
	 * @param idRango
	 * @param idFam
	 * @param codAgencia
	 * @param fecCompromiso
	 * @param idGrupoSeg
	 * @return
	 */
	public Ocupacion_rangoLocal findByRangoFamiliaAgenciaDiaSegmento(Integer idRango, Long idFam, String codAgencia, Date fecCompromiso, Integer idGrupoSeg) throws javax.ejb.FinderException;
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Ocupacion_rangoLocal create(
		Long idOcupacion,
		Integer idRango,
		Long idFam,
		String pComercial,
		Date fecCompromiso,
		Integer cantidad,
		Integer idGrupoSeg,
		String codAgencia)
		throws javax.ejb.CreateException;
}
