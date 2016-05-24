package co.com.telefonica.atiempo.ejb.eb;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * Bean implementation class for Enterprise Bean: Peticion_flujo
 */
public abstract class Peticion_flujoBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Peticion_flujoKey ejbCreate(
		java.lang.Integer pefl_id)
		throws javax.ejb.CreateException {
		setPefl_id(pefl_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Integer pefl_id)
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
	
	public co.com.telefonica.atiempo.ejb.eb.Peticion_flujoKey ejbCreate(
		java.lang.Integer pefl_id,
		java.lang.Integer pefl_orden,
		Long prse_id,
		String pefl_estado,
		PeticionLocal argFk_peti_2_pefl,
		Actividad_flujoLocal argFk_acti_2_pefl,
		Operacion_comercialLocal fk_opco)
		throws javax.ejb.CreateException {
		setPefl_id(pefl_id);
		setPefl_orden(pefl_orden);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.Integer pefl_id,
		java.lang.Integer pefl_orden,
		Long prse_id,
		String pefl_estado,
		PeticionLocal argFk_peti_2_pefl,
		Actividad_flujoLocal argFk_acti_2_pefl,
		Operacion_comercialLocal fk_opco)
		throws javax.ejb.CreateException {
		setFk_peti_2_pefl(argFk_peti_2_pefl);
		setFk_acti_2_pefl(argFk_acti_2_pefl);
		setFk_opco_2_pefl(fk_opco);
	}
	/**
	 * Get accessor for persistent attribute: pefl_id
	 */
	public abstract java.lang.Integer getPefl_id();
	/**
	 * Set accessor for persistent attribute: pefl_id
	 */
	public abstract void setPefl_id(java.lang.Integer newPefl_id);
	/**
	 * Get accessor for persistent attribute: prse_id
	 */
	public abstract java.lang.Long getPrse_id();
	/**
	 * Set accessor for persistent attribute: prse_id
	 */
	public abstract void setPrse_id(java.lang.Long newPrse_id);
	/**
	 * Get accessor for persistent attribute: pefl_orden
	 */
	public abstract java.lang.Integer getPefl_orden();
	/**
	 * Set accessor for persistent attribute: pefl_orden
	 */
	public abstract void setPefl_orden(java.lang.Integer newPefl_orden);
	/**
	 * Get accessor for persistent attribute: pefl_estado
	 */
	public abstract java.lang.String getPefl_estado();
	/**
	 * Set accessor for persistent attribute: pefl_estado
	 */
	public abstract void setPefl_estado(java.lang.String newPefl_estado);
	/**
	 * This method was generated for supporting the relationship role named fk_peti_2_pefl.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.PeticionLocal getFk_peti_2_pefl();
	/**
	 * This method was generated for supporting the relationship role named fk_peti_2_pefl.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_peti_2_pefl(co.com.telefonica.atiempo.ejb.eb.PeticionLocal aFk_peti_2_pefl);
	/**
	 * This method was generated for supporting the relationship role named fk_opco_2_pefl.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.Operacion_comercialLocal getFk_opco_2_pefl();
	/**
	 * This method was generated for supporting the relationship role named fk_opco_2_pefl.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_opco_2_pefl(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Operacion_comercialLocal aFk_opco_2_pefl);
	/**
	 * This method was generated for supporting the relationship role named fk_acti_2_pefl.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Actividad_flujoLocal getFk_acti_2_pefl();
	/**
	 * This method was generated for supporting the relationship role named fk_acti_2_pefl.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setFk_acti_2_pefl(co.com.telefonica.atiempo.ejb.eb.Actividad_flujoLocal aFk_acti_2_pefl);

	/**
	 * ejbCreate
	 */
	public Peticion_flujoKey ejbCreate(Integer pefl_id,
		java.lang.Long newIdPeticion,
		java.lang.Long newIdPRSE,
		java.lang.Long newIdOperacionComercial,
		java.lang.Integer newIdActividad,
		java.lang.Integer newOrden)
		throws javax.ejb.CreateException {
		setPefl_id(pefl_id);
		setPrse_id(newIdPRSE);
		setPefl_orden(newOrden);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(Integer pefl_id,
	java.lang.Long newIdPeticion,
	java.lang.Long newIdPRSE,
	java.lang.Long newIdOperacionComercial,
	java.lang.Integer newIdActividad,
	java.lang.Integer newOrden)
		throws javax.ejb.CreateException {
			try {
				Actividad_flujoLocalHome aflh =(Actividad_flujoLocalHome) HomeFactory.getHome(Actividad_flujoLocalHome.JNDI_NAME);
				Actividad_flujoKey actiKey = new Actividad_flujoKey( newIdActividad );
				this.setFk_acti_2_pefl( aflh.findByPrimaryKey( actiKey ) );

				PeticionLocalHome plh = (PeticionLocalHome)HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
				PeticionKey petiKey = new PeticionKey(newIdPeticion);
				this.setFk_peti_2_pefl(plh.findByPrimaryKey( petiKey ));

				Operacion_comercialLocalHome ocH = (Operacion_comercialLocalHome)HomeFactory.getHome(Operacion_comercialLocalHome.JNDI_NAME);
				Operacion_comercialKey ocKey = new Operacion_comercialKey(newIdOperacionComercial);
				this.setFk_opco_2_pefl(ocH.findByPrimaryKey( ocKey ));
				
			} catch (NamingException e) {
			} catch (FinderException e) {
			}
			
	}
	
	public Integer getIdActividad() {
		Actividad_flujoKey actiKey = (Actividad_flujoKey) getFk_acti_2_pefl().getPrimaryKey();
		
		return actiKey.acti_id;
	}
}
