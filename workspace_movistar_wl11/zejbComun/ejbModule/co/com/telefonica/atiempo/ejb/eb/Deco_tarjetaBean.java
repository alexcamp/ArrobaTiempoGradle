package co.com.telefonica.atiempo.ejb.eb;

import co.com.atiempo.dto.EquipoDTO;

import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: Deco_tarjeta
 */
public abstract class Deco_tarjetaBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Deco_tarjetaKey ejbCreate(
		java.lang.String id_tarjeta,
		java.lang.String id_deco,
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal argPeticion)
		throws javax.ejb.CreateException {
		setId_tarjeta(id_tarjeta);
		setId_deco(id_deco);
		co.com.telefonica.atiempo.ejb.eb.PeticionKey argPeticionPK =
			(co.com.telefonica.atiempo.ejb.eb.PeticionKey) argPeticion
				.getPrimaryKey();
		setPeticion_peti_numero(argPeticionPK.peti_numero);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.String id_tarjeta,
		java.lang.String id_deco,
		co.com.telefonica.atiempo.ejb.eb.PeticionLocal argPeticion)
		throws javax.ejb.CreateException {
		setPeticion(argPeticion);
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
	 * Get accessor for persistent attribute: estado
	 */
	public abstract java.lang.Integer getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public abstract void setEstado(java.lang.Integer newEstado);
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
	/**
	 * Get accessor for persistent attribute: id_tarjeta
	 */
	public abstract java.lang.String getId_tarjeta();
	/**
	 * Set accessor for persistent attribute: id_tarjeta
	 */
	public abstract void setId_tarjeta(java.lang.String newId_tarjeta);
	/**
	 * Get accessor for persistent attribute: id_deco
	 */
	public abstract java.lang.String getId_deco();
	/**
	 * Set accessor for persistent attribute: id_deco
	 */
	public abstract void setId_deco(java.lang.String newId_deco);
	/**
	 * Get accessor for persistent attribute: codigo_error
	 */
	public abstract java.lang.Integer getCodigo_error();
	/**
	 * Set accessor for persistent attribute: codigo_error
	 */
	public abstract void setCodigo_error(java.lang.Integer newCodigo_error);
	/**
	 * Get accessor for persistent attribute: mensaje_error
	 */
	public abstract java.lang.String getMensaje_error();
	/**
	 * Set accessor for persistent attribute: mensaje_error
	 */
	public abstract void setMensaje_error(java.lang.String newMensaje_error);
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Deco_tarjetaKey ejbCreate(
		java.lang.String id_tarjeta,
		java.lang.String id_deco,
		java.lang.Long peticion_peti_numero)
		throws javax.ejb.CreateException {
		setId_tarjeta(id_tarjeta);
		setId_deco(id_deco);
		setPeticion_peti_numero(peticion_peti_numero);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.String id_tarjeta,
		java.lang.String id_deco,
		java.lang.Long peticion_peti_numero)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: peticion_peti_numero
	 */
	public abstract java.lang.Long getPeticion_peti_numero();
	/**
	 * Set accessor for persistent attribute: peticion_peti_numero
	 */
	public abstract void setPeticion_peti_numero(
		java.lang.Long newPeticion_peti_numero);
	/**
	 * Get accessor for persistent attribute: accion
	 */
	public abstract java.lang.Integer getAccion();
	/**
	 * Set accessor for persistent attribute: accion
	 */
	public abstract void setAccion(java.lang.Integer newAccion);
	/**
	 * Get accessor for persistent attribute: opco_id
	 */
	public abstract java.lang.Long getOpco_id();
	/**
	 * Set accessor for persistent attribute: opco_id
	 */
	public abstract void setOpco_id(java.lang.Long newOpco_id);
	/**
	 * Get accessor for persistent attribute: empr_id
	 */
	public abstract java.lang.Long getEmpr_id();
	/**
	 * Set accessor for persistent attribute: empr_id
	 */
	public abstract void setEmpr_id(java.lang.Long newEmpr_id);
	
	public EquipoDTO toEquipo()
	{
		EquipoDTO equipoDTO=new EquipoDTO();
		equipoDTO.setIdDeco(this.getId_deco());
		equipoDTO.setIdTarjeta(this.getId_tarjeta());
		// TODO: AT-1035 - PVR - Inicio - guido
		equipoDTO.setAccion(this.getAccion());
		// AT-1035 - Fin
		return equipoDTO;
	}
	
	public boolean estaEnCas()
	{
		if(this.getAccion()!=null && this.getAccion().intValue()==ComunInterfaces.accionParActivoCas && this.getEstado()!=null && this.getEstado().intValue()==ComunInterfaces.estadoParOk)
			return true;
		return false;
	}
	public boolean estaActivo()
	{
		if(this.getAccion()!=null && this.getAccion().intValue()==ComunInterfaces.accionParActivar && this.getEstado()!=null && this.getEstado().intValue()==ComunInterfaces.estadoParOk)
			return true;
		return false;
	}
	
	public boolean estaEliminado()
	{
		if(this.getAccion()!=null && this.getAccion().intValue()==ComunInterfaces.accionParEliminar && this.getEstado()!=null && this.getEstado().intValue()==ComunInterfaces.estadoParOk)
			return true;
		return false;
	}
	
	public boolean esActivacionFallida()
	{
		if(this.getAccion()!=null && this.getAccion().intValue()==ComunInterfaces.accionParActivar && this.getEstado()!=null && this.getEstado().intValue()==ComunInterfaces.estadoParNoOk)
			return true;
		return false;
	}
	
	public boolean esEliminacionFallida()
	{
		if(this.getAccion()!=null && this.getAccion().intValue()==ComunInterfaces.accionParEliminar && this.getEstado()!=null && this.getEstado().intValue()==ComunInterfaces.estadoParNoOk)
			return true;
		return false;
	}
	
	public boolean esEsperaDeActivacion()
	{
		if(this.getAccion()!=null && this.getAccion().intValue()==ComunInterfaces.accionParActivar && this.getEstado()==null)
			return true;
		return false;
	}
	
	public boolean esEsperaDeEliminacion()
	{
		if(this.getAccion()!=null && this.getAccion().intValue()==ComunInterfaces.accionParEliminar && this.getEstado()==null)
			return true;
		return false;
	}
	
	public boolean esAccionDanhado()
	{
		if(this.getAccion()!=null && this.getAccion().intValue()==ComunInterfaces.accionParDanhado && this.getEstado()==null)
			return true;
		return false;
	}
	
	public boolean esAccionDanhadoResOk()
	{
		if(this.getAccion()!=null && this.getAccion().intValue()==ComunInterfaces.accionParDanhado && this.getEstado()!=null && this.getEstado().intValue()==ComunInterfaces.estadoParOk)
			return true;
		return false;
	}
	
	public boolean esAccionDanhadoResNoOk()
	{
		if(this.getAccion()!=null && this.getAccion().intValue()==ComunInterfaces.accionParDanhado && this.getEstado()!=null && this.getEstado().intValue()==ComunInterfaces.estadoParNoOk)
			return true;
		return false;
	}
	
	public boolean esAccionNoRecuperado()
	{
		if(this.getAccion()!=null && this.getAccion().intValue()==ComunInterfaces.accionParNoRecuperado && this.getEstado()==null)
			return true;
		return false;
	}
	
	public boolean esAccionNoRecuperadoResOk()
	{
		if(this.getAccion()!=null && this.getAccion().intValue()==ComunInterfaces.accionParNoRecuperado && this.getEstado()!=null && this.getEstado().intValue()==ComunInterfaces.estadoParOk)
			return true;
		return false;
	}
		
	public boolean esAccionNoRecuperadoResNoOk()
	{
		if(this.getAccion()!=null && this.getAccion().intValue()==ComunInterfaces.accionParNoRecuperado && this.getEstado()!=null && this.getEstado().intValue()==ComunInterfaces.estadoParNoOk)
			return true;
		return false;
	}
	
	
	public Integer getIdEstado()
	{
		if(estaEnCas())
			return new Integer(1);
		if(estaActivo())
			return new Integer(2);
		if(estaEliminado())
			return new Integer(3);
		if(esActivacionFallida())
			return new Integer(4);
		if(esEliminacionFallida())
			return new Integer(5);
		if(esEsperaDeActivacion())
			return new Integer(6);
		if(esEsperaDeEliminacion())
			return new Integer(7);
		if(esAccionDanhado())
			return new Integer(8);
		if(esAccionDanhadoResOk())
			return new Integer(9);
		if(esAccionDanhadoResNoOk())
			return new Integer(10);
		if(esAccionNoRecuperado())
			return new Integer(11);
		if(esAccionNoRecuperadoResOk())
			return new Integer(12);
		if(esAccionNoRecuperadoResNoOk())
			return new Integer(13);
		return new Integer(0);
	}
	public String getDescEstado()
	{
		if(estaEnCas())
			return "Par Activo En CAS";
		if(estaActivo())
			return "Par Activo";
		if(estaEliminado())
			return "Par Desactivado";
		if(esActivacionFallida())
			return "Activación Fallida";
		if(esEliminacionFallida())
			return "Desactivación Fallida";
		if(esEsperaDeActivacion())
			return "En Espera de Activación";
		if(esEsperaDeEliminacion())
			return "En Espera de Desactivación";
		if(esAccionDanhado())
			return "Par Deco Dañado.";
		if(esAccionDanhadoResOk())
			return "Par Deco Dañado. Actualización Inventario Enviada.";
		if(esAccionDanhadoResNoOk())
			return "Par Deco Dañado. Actualización Inventario No OK.";
		if(esAccionNoRecuperado())
			return "Par Deco No Recuperado";
		if(esAccionNoRecuperadoResOk())
			return "Par Deco No Recuperado. Actualización Inventario Enviada.";
		if(esAccionNoRecuperadoResNoOk())
			return "Par Deco No Recuperado. Actualización Inventario No OK.";
		return "Accion No Ingresada";
	}
	/**
	 * Get accessor for persistent attribute: marca_hora
	 */
	public abstract java.sql.Timestamp getMarca_hora();
	/**
	 * Set accessor for persistent attribute: marca_hora
	 */
	public abstract void setMarca_hora(java.sql.Timestamp newMarca_hora);
	/**
	 * Get accessor for persistent attribute: deco_reference
	 */
	public abstract java.lang.String getDeco_reference();
	/**
	 * Set accessor for persistent attribute: deco_reference
	 */
	public abstract void setDeco_reference(java.lang.String newDeco_reference);
	/**
	 * Get accessor for persistent attribute: deco_marca
	 */
	public abstract java.lang.String getDeco_marca();
	/**
	 * Set accessor for persistent attribute: deco_marca
	 */
	public abstract void setDeco_marca(java.lang.String newDeco_marca);
	/**
	 * Get accessor for persistent attribute: serial_deco
	 */
	public abstract java.lang.String getSerial_deco();
	/**
	 * Set accessor for persistent attribute: serial_deco
	 */
	public abstract void setSerial_deco(java.lang.String newSerial_deco);
	/**
	 * Get accessor for persistent attribute: serial_tarjeta
	 */
	public abstract java.lang.String getSerial_tarjeta();
	/**
	 * Set accessor for persistent attribute: serial_tarjeta
	 */
	public abstract void setSerial_tarjeta(java.lang.String newSerial_tarjeta);
	/**
	 * Get accessor for persistent attribute: codigo_deco
	 */
	public abstract java.lang.String getCodigo_deco();
	/**
	 * Set accessor for persistent attribute: codigo_deco
	 */
	public abstract void setCodigo_deco(java.lang.String newCodigo_deco);
	/**
	 * Get accessor for persistent attribute: flag_pet_curso
	 */
	public abstract java.lang.String getFlag_pet_curso();
	/**
	 * Set accessor for persistent attribute: flag_pet_curso
	 */
	public abstract void setFlag_pet_curso(java.lang.String newFlag_pet_curso);
	/**
	 * Get accessor for persistent attribute: serial_ddtv
	 */
	public abstract java.lang.String getSerial_ddtv();
	/**
	 * Set accessor for persistent attribute: serial_ddtv
	 */
	public abstract void setSerial_ddtv(java.lang.String newSerial_ddtv);
	/**
	 * Get accessor for persistent attribute: marca_ddtv
	 */
	public abstract java.lang.String getMarca_ddtv();
	/**
	 * Set accessor for persistent attribute: marca_ddtv
	 */
	public abstract void setMarca_ddtv(java.lang.String newMarca_ddtv);
	/**
	 * Get accessor for persistent attribute: modelo_ddtv
	 */
	public abstract java.lang.String getModelo_ddtv();
	/**
	 * Set accessor for persistent attribute: modelo_ddtv
	 */
	public abstract void setModelo_ddtv(java.lang.String newModelo_ddtv);
	/**
	 * Get accessor for persistent attribute: deco_adicionales
	 */
	public abstract java.lang.Long getDeco_adicionales();
	/**
	 * Set accessor for persistent attribute: deco_adicionales
	 */
	public abstract void setDeco_adicionales(java.lang.Long newDeco_adicionales);
	/**
	 * Get accessor for persistent att ribute: fec_ejec_atis
	 */
	public abstract java.sql.Timestamp getFec_ejec_atis();
	/**
	 * Set accessor for persistent attribute: fec_ejec_atis
	 */
	public abstract void setFec_ejec_atis(java.sql.Timestamp newFec_ejec_atis);
}
