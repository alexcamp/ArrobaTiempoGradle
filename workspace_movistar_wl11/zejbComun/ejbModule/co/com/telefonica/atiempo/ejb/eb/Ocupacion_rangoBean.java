package co.com.telefonica.atiempo.ejb.eb;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Bean implementation class for Enterprise Bean: Ocupacion_rango
 */
public abstract class Ocupacion_rangoBean implements javax.ejb.EntityBean {
	private javax.ejb.EntityContext myEntityCtx;
	/**
	 * setEntityContext
	 */
	public void setEntityContext(javax.ejb.EntityContext ctx) {
		myEntityCtx = ctx;
	}
	/**
	 * getEntityContext
	 */
	public javax.ejb.EntityContext getEntityContext() {
		return myEntityCtx;
	}
	/**
	 * unsetEntityContext
	 */
	public void unsetEntityContext() {
		myEntityCtx = null;
	}
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Ocupacion_rangoKey ejbCreate(
		java.lang.Long ocup_id)
		throws javax.ejb.CreateException {
		setOcup_id(ocup_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long ocup_id)
		throws javax.ejb.CreateException {
	}

	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Ocupacion_rangoKey ejbCreate(
			Long idOcupacion, 
			Integer idRango, Long idFam, String pComercial, 
			Date fecCompromiso, Integer cantidad, Integer idGrupoSeg, String codAgencia)
		throws javax.ejb.CreateException {

		setOcup_id( idOcupacion );
		setCantidad_ocupada( cantidad );
		setCodigo_agencia( codAgencia );
		setCodigo_familia_ps( idFam );
		setCodigo_pcom( pComercial );
		setDia_especifico( new Timestamp(fecCompromiso.getTime()) );
		setGrse_id( idGrupoSeg );
		setId_rango( idRango );
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(Long idOcupacion, Integer idRango, Long idFam, String pComercial, 
	Date fecCompromiso, Integer idCantidad, Integer idGrupoSeg, String codAgencia)

		throws javax.ejb.CreateException {
	}

	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbLoad
	 */
	public void ejbLoad() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() throws javax.ejb.RemoveException {
	}
	/**
	 * ejbStore
	 */
	public void ejbStore() {
	}
	/**
	 * Get accessor for persistent attribute: ocup_id
	 */
	public abstract java.lang.Long getOcup_id();
	/**
	 * Set accessor for persistent attribute: ocup_id
	 */
	public abstract void setOcup_id(java.lang.Long newOcup_id);
	/**
	 * Get accessor for persistent attribute: id_rango
	 */
	public abstract java.lang.Integer getId_rango();
	/**
	 * Set accessor for persistent attribute: id_rango
	 */
	public abstract void setId_rango(java.lang.Integer newId_rango);
	/**
	 * Get accessor for persistent attribute: codigo_familia_ps
	 */
	public abstract java.lang.Long getCodigo_familia_ps();
	/**
	 * Set accessor for persistent attribute: codigo_familia_ps
	 */
	public abstract void setCodigo_familia_ps(
		java.lang.Long newCodigo_familia_ps);
	/**
	 * Get accessor for persistent attribute: codigo_pcom
	 */
	public abstract java.lang.String getCodigo_pcom();
	/**
	 * Set accessor for persistent attribute: codigo_pcom
	 */
	public abstract void setCodigo_pcom(java.lang.String newCodigo_pcom);
	/**
	 * Get accessor for persistent attribute: dia_especifico
	 */
	public abstract java.sql.Timestamp getDia_especifico();
	/**
	 * Set accessor for persistent attribute: dia_especifico
	 */
	public abstract void setDia_especifico(
		java.sql.Timestamp newDia_especifico);
	/**
	 * Get accessor for persistent attribute: cantidad_ocupada
	 */
	public abstract java.lang.Integer getCantidad_ocupada();
	/**
	 * Set accessor for persistent attribute: cantidad_ocupada
	 */
	public abstract void setCantidad_ocupada(
		java.lang.Integer newCantidad_ocupada);
	/**
	 * Get accessor for persistent attribute: grse_id
	 */
	public abstract java.lang.Integer getGrse_id();
	/**
	 * Set accessor for persistent attribute: grse_id
	 */
	public abstract void setGrse_id(java.lang.Integer newGrse_id);
	/**
	 * Get accessor for persistent attribute: codigo_agencia
	 */
	public abstract java.lang.String getCodigo_agencia();
	/**
	 * Set accessor for persistent attribute: codigo_agencia
	 */
	public abstract void setCodigo_agencia(java.lang.String newCodigo_agencia);
}
