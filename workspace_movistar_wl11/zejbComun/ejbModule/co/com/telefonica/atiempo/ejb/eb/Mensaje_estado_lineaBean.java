package co.com.telefonica.atiempo.ejb.eb;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * Bean implementation class for Enterprise Bean: Mensaje_estado_linea
 */
public abstract class Mensaje_estado_lineaBean
	implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_lineaKey ejbCreate(
		java.lang.Long correlativo)
		throws javax.ejb.CreateException {
		setCorrelativo(correlativo);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long correlativo)
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
	 * Get accessor for persistent attribute: correlativo
	 */
	public abstract java.lang.Long getCorrelativo();
	/**
	 * Set accessor for persistent attribute: correlativo
	 */
	public abstract void setCorrelativo(java.lang.Long newCorrelativo);
	/**
	 * Get accessor for persistent attribute: cod_familia_ps
	 */
	public abstract java.lang.Integer getCod_familia_ps();
	/**
	 * Set accessor for persistent attribute: cod_familia_ps
	 */
	public abstract void setCod_familia_ps(java.lang.Integer newCod_familia_ps);
	/**
	 * Get accessor for persistent attribute: fecha_envio
	 */
	public abstract java.lang.String getFecha_envio();
	/**
	 * Set accessor for persistent attribute: fecha_envio
	 */
	public abstract void setFecha_envio(java.lang.String newFecha_envio);
	/**
	 * Get accessor for persistent attribute: fecha_cierre
	 */
	public abstract java.lang.String getFecha_cierre();
	/**
	 * Set accessor for persistent attribute: fecha_cierre
	 */
	public abstract void setFecha_cierre(java.lang.String newFecha_cierre);
	/**
	 * Get accessor for persistent attribute: cod_actividad_generadora
	 */
	public abstract java.lang.String getCod_actividad_generadora();
	/**
	 * Set accessor for persistent attribute: cod_actividad_generadora
	 */
	public abstract void setCod_actividad_generadora(
		java.lang.String newCod_actividad_generadora);
	/**
	 * Get accessor for persistent attribute: telefono
	 */
	public abstract java.lang.Long getTelefono();
	/**
	 * Set accessor for persistent attribute: telefono
	 */
	public abstract void setTelefono(java.lang.Long newTelefono);
	/**
	 * Get accessor for persistent attribute: area
	 */
	public abstract java.lang.Integer getArea();
	/**
	 * Set accessor for persistent attribute: area
	 */
	public abstract void setArea(java.lang.Integer newArea);
	/**
	 * This method was generated for supporting the relationship role named respuesta_conect2_apsc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getRespuesta_conect2_apsc();
	/**
	 * This method was generated for supporting the relationship role named respuesta_conect2_apsc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setRespuesta_conect2_apsc(
		java.util.Collection aRespuesta_conect2_apsc);
	/**
	 * This method was generated for supporting the relationship role named f_reference_13.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.ConectorLocal getF_reference_13();
	/**
	 * This method was generated for supporting the relationship role named f_reference_13.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setF_reference_13(
		co.com.telefonica.atiempo.ejb.eb.ConectorLocal aF_reference_13);
	/**
	 * This method was generated for supporting the relationship role named f_reference_14.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Mensaje_estadoLocal getF_reference_14();
	/**
	 * This method was generated for supporting the relationship role named f_reference_14.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setF_reference_14(
		co.com.telefonica.atiempo.ejb.eb.Mensaje_estadoLocal aF_reference_14);
	/**
	 * This method was generated for supporting the relationship role named respuesta_conec6_apsc_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getRespuesta_conec6_apsc_linea();
	/**
	 * This method was generated for supporting the relationship role named respuesta_conec6_apsc_linea.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setRespuesta_conec6_apsc_linea(
		java.util.Collection aRespuesta_conec6_apsc_linea);
	/**
	 * This method was generated for supporting the relationship role named respuesta_conect4_apsc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getRespuesta_conect4_apsc();
	/**
	 * This method was generated for supporting the relationship role named respuesta_conect4_apsc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setRespuesta_conect4_apsc(
		java.util.Collection aRespuesta_conect4_apsc);
	/**
	 * This method was generated for supporting the relationship role named respuesta_conect7_apsc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getRespuesta_conect7_apsc();
	/**
	 * This method was generated for supporting the relationship role named respuesta_conect7_apsc.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setRespuesta_conect7_apsc(
		java.util.Collection aRespuesta_conect7_apsc);
	/**
	 * Get accessor for persistent attribute: id_error
	 */
	public abstract java.lang.String getId_error();
	/**
	 * Set accessor for persistent attribute: id_error
	 */
	public abstract void setId_error(java.lang.String newId_error);
	/**
	 * Get accessor for persistent attribute: desc_error
	 */
	public abstract java.lang.String getDesc_error();
	/**
	 * Set accessor for persistent attribute: desc_error
	 */
	public abstract void setDesc_error(java.lang.String newDesc_error);
	/**
	 * This method was generated for supporting the relationship role named recursos_linea_basica.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract java.util.Collection getRecursos_linea_basica();
	/**
	 * This method was generated for supporting the relationship role named recursos_linea_basica.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setRecursos_linea_basica(
		java.util.Collection aRecursos_linea_basica);
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.PeticionLocal getPeticion();
	/**
	 * This method was generated for supporting the relationship role named peticion.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setPeticion(
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal aPeticion);
		
	public Long getPeti_numero()
	{
		if(getPeticion()!=null)
		{
			PeticionKey peticionKey=(PeticionKey) getPeticion().getPrimaryKey();
			return peticionKey.peti_numero;
		}
		return null;
	}
	
	public void setPeti_numero(Long long1) throws FinderException, NamingException
	{
		PeticionLocalHome peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
		PeticionLocal local=peticionLocalHome.findByPrimaryKey(new PeticionKey(long1));
		setPeticion(local);
	}
	
	/**
	 * Get accessor for persistent attribute: accion
	 */
	public abstract java.lang.String getAccion();
	/**
	 * Set accessor for persistent attribute: accion
	 */
	public abstract void setAccion(java.lang.String newAccion);
}
