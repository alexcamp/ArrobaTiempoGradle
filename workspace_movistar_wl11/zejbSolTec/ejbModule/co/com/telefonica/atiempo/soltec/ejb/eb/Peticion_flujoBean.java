package co.com.telefonica.atiempo.soltec.ejb.eb;

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
	public co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_flujoKey ejbCreate(
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
	 * Get accessor for persistent attribute: pefl_id
	 */
	public abstract java.lang.Integer getPefl_id();
	/**
	 * Set accessor for persistent attribute: pefl_id
	 */
	public abstract void setPefl_id(java.lang.Integer newPefl_id);
	/**
	 * Get accessor for persistent attribute: pefl_estado
	 */
	public abstract java.lang.String getPefl_estado();
	/**
	 * Set accessor for persistent attribute: pefl_estado
	 */
	public abstract void setPefl_estado(java.lang.String newPefl_estado);
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Producto_servicio_stLocal getProducto_servicio_st();
	/**
	 * This method was generated for supporting the relationship role named producto_servicio_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setProducto_servicio_st(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Producto_servicio_stLocal aProducto_servicio_st);
	/**
	 * This method was generated for supporting the relationship role named actividad_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Actividad_flujoLocal getActividad_flujo();
	/**
	 * This method was generated for supporting the relationship role named actividad_flujo.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setActividad_flujo(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Actividad_flujoLocal anActividad_flujo);
	/**
	 * This method was generated for supporting the relationship role named operacion_comercial_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.soltec
		.ejb
		.eb
		.Operacion_comercial_stLocal getOperacion_comercial_st();
	/**
	 * This method was generated for supporting the relationship role named operacion_comercial_st.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setOperacion_comercial_st(
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Operacion_comercial_stLocal anOperacion_comercial_st);
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
	/**
	 * Get accessor for persistent attribute: pefl_orden
	 */
	public abstract java.lang.Integer getPefl_orden();
	/**
	 * Set accessor for persistent attribute: pefl_orden
	 */
	public abstract void setPefl_orden(java.lang.Integer newPefl_orden);

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
//		setPrse_id(newIdPRSE);
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
				this.setActividad_flujo( aflh.findByPrimaryKey( actiKey ) );

				Peticion_stLocalHome plh = (Peticion_stLocalHome)HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
				Peticion_stKey petiKey = new Peticion_stKey(newIdPeticion);
				this.setPeticion_st(plh.findByPrimaryKey( petiKey ));

				Operacion_comercial_stLocalHome ocH = (Operacion_comercial_stLocalHome)HomeFactory.getHome(Operacion_comercial_stLocalHome.JNDI_NAME);
				Operacion_comercial_stKey ocKey = new Operacion_comercial_stKey(newIdOperacionComercial);
				this.setOperacion_comercial_st(ocH.findByPrimaryKey( ocKey ));
				
				Producto_servicio_stLocalHome ps = (Producto_servicio_stLocalHome) HomeFactory.getHome(Producto_servicio_stLocalHome.JNDI_NAME);
				Producto_servicio_stKey psKey = new Producto_servicio_stKey(newIdPRSE);
				this.setProducto_servicio_st(ps.findByPrimaryKey(psKey));				
				
			} catch (NamingException e) {
			} catch (FinderException e) {
			}
			
	}
	
	
}
