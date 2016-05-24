package co.com.telefonica.atiempo.ejb.eb;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Bean implementation class for Enterprise Bean: Compromiso_peticion
 */
public abstract class Compromiso_peticionBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Compromiso_peticionKey ejbCreate(
		java.lang.Long compr_id)
		throws javax.ejb.CreateException {
		setCompr_id(compr_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long compr_id)
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
	 * ejbCreate method for a CMP entity bean.
	 */
	public co.com.telefonica.atiempo.ejb.eb.Compromiso_peticionKey ejbCreate(
		java.lang.Long compr_id,
		java.lang.Integer grse_id)
		throws javax.ejb.CreateException {
		setCompr_id(compr_id);
		setGrse_id(grse_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Long compr_id,
		java.lang.Integer grse_id)
		throws javax.ejb.CreateException {
	}
	
	public co.com.telefonica.atiempo.ejb.eb.Compromiso_peticionKey ejbCreate(Long idCompromiso, 
		Long idTipoAgenda, Integer idRango, Long numPetico, String pComercial, Date fecCompromiso,
		String hDesde, String hHasta, String userMac, String codAgencia, Integer idGrupoSeg, 
		Integer idCausaCierre) 
		throws javax.ejb.CreateException {

			setCompr_id( idCompromiso );
			setGrse_id( idGrupoSeg );
			setCare_id( idCausaCierre );
			setCodigo_agencia( codAgencia );
			setCodigo_pcom( pComercial );
			setDia_especifico( new Timestamp(fecCompromiso.getTime()) );
			setEstado( new Short("1") );
			setFecha( new Timestamp(new Date().getTime()) );
			setHora_desde( hDesde );
			setHora_hasta( hHasta );
			setId_rango( idRango );
			setPeti_numero( numPetico );
			setTiag_id( idTipoAgenda );

			return null;
		}
		
	public void ejbPostCreate(Long idCompromiso,
		Long idTipoAgenda, Integer idRango, Long numPetico, String pComercial, Date fecCompromiso,
		String hDesde, String hHasta, String userMac, String codAgencia, Integer idGrupoSeg, 
		Integer idCausaCierre) 
		throws javax.ejb.CreateException {
		}
	/**
	 * Get accessor for persistent attribute: compr_id
	 */
	public abstract java.lang.Long getCompr_id();
	/**
	 * Set accessor for persistent attribute: compr_id
	 */
	public abstract void setCompr_id(java.lang.Long newCompr_id);
	/**
	 * Get accessor for persistent attribute: tiag_id
	 */
	public abstract java.lang.Long getTiag_id();
	/**
	 * Set accessor for persistent attribute: tiag_id
	 */
	public abstract void setTiag_id(java.lang.Long newTiag_id);
	/**
	 * Get accessor for persistent attribute: id_rango
	 */
	public abstract java.lang.Integer getId_rango();
	/**
	 * Set accessor for persistent attribute: id_rango
	 */
	public abstract void setId_rango(java.lang.Integer newId_rango);
	/**
	 * Get accessor for persistent attribute: peti_numero
	 */
	public abstract java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public abstract void setPeti_numero(java.lang.Long newPeti_numero);
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
	 * Get accessor for persistent attribute: id_tecnico
	 */
	public abstract java.lang.Long getId_tecnico();
	/**
	 * Set accessor for persistent attribute: id_tecnico
	 */
	public abstract void setId_tecnico(java.lang.Long newId_tecnico);
	/**
	 * Get accessor for persistent attribute: user_mac
	 */
	public abstract java.lang.String getUser_mac();
	/**
	 * Set accessor for persistent attribute: user_mac
	 */
	public abstract void setUser_mac(java.lang.String newUser_mac);
	/**
	 * Get accessor for persistent attribute: hora_desde
	 */
	public abstract java.lang.String getHora_desde();
	/**
	 * Set accessor for persistent attribute: hora_desde
	 */
	public abstract void setHora_desde(java.lang.String newHora_desde);
	/**
	 * Get accessor for persistent attribute: hora_hasta
	 */
	public abstract java.lang.String getHora_hasta();
	/**
	 * Set accessor for persistent attribute: hora_hasta
	 */
	public abstract void setHora_hasta(java.lang.String newHora_hasta);
	/**
	 * Get accessor for persistent attribute: codigo_agencia
	 */
	public abstract java.lang.String getCodigo_agencia();
	/**
	 * Set accessor for persistent attribute: codigo_agencia
	 */
	public abstract void setCodigo_agencia(java.lang.String newCodigo_agencia);
	/**
	 * Get accessor for persistent attribute: estado
	 */
	public abstract java.lang.Short getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public abstract void setEstado(java.lang.Short newEstado);
	/**
	 * Get accessor for persistent attribute: grse_id
	 */
	public abstract java.lang.Integer getGrse_id();
	/**
	 * Set accessor for persistent attribute: grse_id
	 */
	public abstract void setGrse_id(java.lang.Integer newGrse_id);
	/**
	 * Get accessor for persistent attribute: fecha
	 */
	public abstract java.sql.Timestamp getFecha();
	/**
	 * Set accessor for persistent attribute: fecha
	 */
	public abstract void setFecha(java.sql.Timestamp newFecha);
	/**
	 * Get accessor for persistent attribute: care_id
	 */
	public abstract java.lang.Integer getCare_id();
	/**
	 * Set accessor for persistent attribute: care_id
	 */
	public abstract void setCare_id(java.lang.Integer newCare_id);
	/**
	 * Get accessor for persistent attribute: usua_id
	 */
	public abstract java.lang.Long getUsua_id();
	/**
	 * Set accessor for persistent attribute: usua_id
	 */
	public abstract void setUsua_id(java.lang.Long newUsua_id);
	/**
	 * Get accessor for persistent attribute: id_cita_previa
	 */
	public abstract java.lang.Long getId_cita_previa();
	/**
	 * Set accessor for persistent attribute: id_cita_previa
	 */
	public abstract void setId_cita_previa(java.lang.Long newId_cita_previa);
}
