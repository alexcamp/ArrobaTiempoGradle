package co.com.telefonica.atiempo.soltec.ejb.eb;

import java.sql.Timestamp;
import java.util.Date;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.naming.NamingException;

import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * Bean implementation class for Enterprise Bean: Bitacora_peticion
 */
public abstract class Bitacora_peticionBean implements javax.ejb.EntityBean {
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
	public co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Bitacora_peticionKey ejbCreate(
		java.lang.Long bipe_id)
		throws javax.ejb.CreateException {
		setBipe_id(bipe_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long bipe_id)
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
	 * Get accessor for persistent attribute: bipe_id
	 */
	public abstract java.lang.Long getBipe_id();
	/**
	 * Set accessor for persistent attribute: bipe_id
	 */
	public abstract void setBipe_id(java.lang.Long newBipe_id);
	/**
	 * Get accessor for persistent attribute: act_id
	 */
	public abstract java.lang.Long getAct_id();
	/**
	 * Set accessor for persistent attribute: act_id
	 */
	public abstract void setAct_id(java.lang.Long newAct_id);
	/**
	 * Get accessor for persistent attribute: caus_id
	 */
	public abstract java.lang.Long getCaus_id();
	/**
	 * Set accessor for persistent attribute: caus_id
	 */
	public abstract void setCaus_id(java.lang.Long newCaus_id);
	/**
	 * Get accessor for persistent attribute: usua_id
	 */
	public abstract java.lang.Long getUsua_id();
	/**
	 * Set accessor for persistent attribute: usua_id
	 */
	public abstract void setUsua_id(java.lang.Long newUsua_id);
	/**
	 * Get accessor for persistent attribute: bipe_fecha_inicio
	 */
	public abstract java.sql.Timestamp getBipe_fecha_inicio();
	/**
	 * Set accessor for persistent attribute: bipe_fecha_inicio
	 */
	public abstract void setBipe_fecha_inicio(
		java.sql.Timestamp newBipe_fecha_inicio);
	/**
	 * Get accessor for persistent attribute: bipe_fecha_fin
	 */
	public abstract java.sql.Timestamp getBipe_fecha_fin();
	/**
	 * Set accessor for persistent attribute: bipe_fecha_fin
	 */
	public abstract void setBipe_fecha_fin(
		java.sql.Timestamp newBipe_fecha_fin);
	/**
	 * Get accessor for persistent attribute: bipe_observacion
	 */
	public abstract java.lang.String getBipe_observacion();
	/**
	 * Set accessor for persistent attribute: bipe_observacion
	 */
	public abstract void setBipe_observacion(
		java.lang.String newBipe_observacion);
	/**
	 * Get accessor for persistent attribute: usua_id_cierre
	 */
	public abstract java.lang.Long getUsua_id_cierre();
	/**
	 * Set accessor for persistent attribute: usua_id_cierre
	 */
	public abstract void setUsua_id_cierre(java.lang.Long newUsua_id_cierre);
	/**
	 * Get accessor for persistent attribute: bipe_es_reversa
	 */
	public abstract java.lang.Short getBipe_es_reversa();
	/**
	 * Set accessor for persistent attribute: bipe_es_reversa
	 */
	public abstract void setBipe_es_reversa(java.lang.Short newBipe_es_reversa);
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Peticion_stLocal getPeticion_st();
	/**
	 * This method was generated for supporting the relationship role named peticion_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPeticion_st(
		co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal aPeticion_st);
		
	public Bitacora_peticionKey ejbCreate(
									Long idActividad,
									Long idCausa,
									Long peticion,
									Long usuaId,
									Date bipeFechaInicio,
									Date bipeFechaFin,
									String bipeObservacion,
									Short esReversa
									) throws CreateException {
			DBManager manager=new DBManager();
			manager.setDataSource(DBManager.JDBC_SOLTEC);
			Long bipe_id=new Long(manager.seqNextValLong("SOLTEC.CORRELATIVO_BITACORA_PETICION_ST"));
			setBipe_id(bipe_id);
			setUsua_id(usuaId);
			if ( bipeFechaInicio != null )
				setBipe_fecha_inicio( new Timestamp( bipeFechaInicio.getTime() ) );
			if ( bipeFechaFin != null )
				setBipe_fecha_fin( new Timestamp( bipeFechaFin.getTime()) );
			setBipe_observacion(bipeObservacion);
			setBipe_es_reversa(esReversa);
			return null;
	}	
		
	public void ejbPostCreate(
				Long idActividad,
				Long idCausa,
				Long peticion,
				Long usuaId,
				Date bipeFechaInicio,
				Date bipeFechaFin,
				String bipeObservacion,
				Short esReversa
			) throws CreateException {
										
			try {
				setAct_id(idActividad);
				Peticion_stLocalHome petiHome = (Peticion_stLocalHome) HomeFactory.getHome( Peticion_stLocalHome.JNDI_NAME ); 
				Peticion_stKey petiKey = new Peticion_stKey(peticion);
				setPeticion_st( petiHome.findByPrimaryKey(petiKey) );
//				CausaLocalHome causaHome = (CausaLocalHome) HomeFactory.getHome( CausaLocalHome.JNDI_NAME ); 
//				CausaKey causaKey = new CausaKey( idCausa );
//				setFk_caus_2_bita( causaHome.findByPrimaryKey(causaKey) );
				setIdCausa(idCausa);
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (FinderException e) {
				e.printStackTrace();
			}
	}
	
	public void setIdCausa(Long idCausa) {
		//Si el IdCausa = 0 es por que no se ha elegido IdCausa	
		if ( (idCausa == null) || (idCausa.longValue()== 0) ){ 
			return;
		}
//TODO: Todavia no existes la Causa en ST
//		try {
//			CausaLocalHome causaHome = (CausaLocalHome) HomeFactory.getHome( CausaLocalHome.JNDI_NAME ); 
//			CausaKey causaKey = new CausaKey( idCausa );
//			setFk_caus_2_bita( causaHome.findByPrimaryKey(causaKey) );
//		} catch (NamingException e) {
//			e.printStackTrace();
//		} catch (FinderException e) {
//			e.printStackTrace();
//		}
	}
	
		
}
