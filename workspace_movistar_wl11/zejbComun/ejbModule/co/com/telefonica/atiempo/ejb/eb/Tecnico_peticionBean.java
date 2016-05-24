package co.com.telefonica.atiempo.ejb.eb;

import java.sql.Timestamp;

import javax.ejb.CreateException;


/**
 * Bean implementation class for Enterprise Bean: Tecnico_peticion
 */
public abstract class Tecnico_peticionBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionKey ejbCreate(Long tepe_id,TecnicoLocal tecnicoLocal) throws javax.ejb.CreateException
	{
		setTepe_id(tepe_id);
		return null;
	}
	
	public void ejbPostCreate(Long tepe_id,TecnicoLocal tecnicoLocal) throws javax.ejb.CreateException
	{
		setTecnico(tecnicoLocal);
	}

	public co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionKey ejbCreate(Long tepe_id, 
		Long idPeticion, 
		TecnicoLocal tecnicoLocal
		)
		
	
	throws javax.ejb.CreateException {
		setTepe_id(tepe_id);
		setPeti_numero( idPeticion );
		return null;
	}
	
	public co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionKey ejbCreate(Long tepe_id,Long idPeticion,TecnicoLocal tecnicoLocal,Integer estado,Timestamp fecha,String horaDesde,String horaHasta,RangoLocal rango) throws CreateException
	{
		setTepe_id(tepe_id);
		setPeti_numero( idPeticion );
		setEstado(estado);
		setFecha(fecha);
		setHora_desde(horaDesde);
		setHora_hasta(horaHasta);
		setRango(rango);
		return null;	
	}
	
	public void ejbPostCreate(Long tepe_id,Long idPeticion,TecnicoLocal tecnicoLocal,Integer estado,Timestamp fecha,String horaDesde,String horaHasta,RangoLocal rango) throws CreateException
	{
		setTecnico(tecnicoLocal);
	}
	


	public void ejbPostCreate(Long tepe_id, 
		Long idPeticion, 
		TecnicoLocal tecnicoLocal
	) throws javax.ejb.CreateException
	{
		setTecnico(tecnicoLocal);
	}
	
	public co.com.telefonica.atiempo.ejb.eb.Tecnico_peticionKey ejbCreate(
		java.lang.Long tepe_id)
		throws javax.ejb.CreateException {
		setTepe_id(tepe_id);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(java.lang.Long tepe_id)
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
	 * Get accessor for persistent attribute: tepe_id
	 */
	public abstract java.lang.Long getTepe_id();
	/**
	 * Set accessor for persistent attribute: tepe_id
	 */
	public abstract void setTepe_id(java.lang.Long newTepe_id);
	/**
	 * Get accessor for persistent attribute: peti_numero
	 */
	public abstract java.lang.Long getPeti_numero();
	/**
	 * Set accessor for persistent attribute: peti_numero
	 */
	public abstract void setPeti_numero(java.lang.Long newPeti_numero);
	/**
	 * Get accessor for persistent attribute: fecha
	 */
	public abstract java.sql.Timestamp getFecha();
	/**
	 * Set accessor for persistent attribute: fecha
	 */
	public abstract void setFecha(java.sql.Timestamp newFecha);
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
	 * Get accessor for persistent attribute: estado
	 */
	public abstract java.lang.Integer getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public abstract void setEstado(java.lang.Integer newEstado);
	/**
	 * This method was generated for supporting the relationship role named causa_reagendamiento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co
		.com
		.telefonica
		.atiempo
		.ejb
		.eb
		.Causa_reagendamientoLocal getCausa_reagendamiento();
	/**
	 * This method was generated for supporting the relationship role named causa_reagendamiento.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setCausa_reagendamiento(
		co
			.com
			.telefonica
			.atiempo
			.ejb
			.eb
			.Causa_reagendamientoLocal aCausa_reagendamiento);
	/**
	 * This method was generated for supporting the relationship role named rango.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.RangoLocal getRango();
	/**
	 * This method was generated for supporting the relationship role named rango.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setRango(
		co.com.telefonica.atiempo.ejb.eb.RangoLocal aRango);
	/**
	 * This method was generated for supporting the relationship role named tecnico.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract co.com.telefonica.atiempo.ejb.eb.TecnicoLocal getTecnico();
	/**
	 * This method was generated for supporting the relationship role named tecnico.
	 * It will be deleted/edited when the relationship is deleted/edited.
	 */
	public abstract void setTecnico(
		co.com.telefonica.atiempo.ejb.eb.TecnicoLocal aTecnico);
	/**
	 * Get accessor for persistent attribute: ap_id
	 */
	public abstract java.lang.Long getAp_id();
	/**
	 * Set accessor for persistent attribute: ap_id
	 */
	public abstract void setAp_id(java.lang.Long newAp_id);
	
	/**
	 * Get accessor for persistent attribute: nom_usua_logueado
	 */
	public abstract java.lang.String getNom_usua_logueado();
	/**
	 * Set accessor for persistent attribute: nom_usua_logueado
	 */
	public abstract void setNom_usua_logueado(
		java.lang.String newNom_usua_logueado);
	/**
	 * Get accessor for persistent attribute: fecha_agendamiento
	 */
	public abstract java.sql.Timestamp getFecha_agendamiento();
	/**
	 * Set accessor for persistent attribute: fecha_agendamiento
	 */
	public abstract void setFecha_agendamiento(
		java.sql.Timestamp newFecha_agendamiento);

}
