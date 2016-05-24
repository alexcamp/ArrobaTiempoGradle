package co.com.telefonica.atiempo.soltec.ejb.eb;

import com.telefonica_chile.comun.ComunInterfaces;

/**
 * Bean implementation class for Enterprise Bean: Deco_tarjeta
 */
public abstract class Deco_tarjetaBean implements javax.ejb.EntityBean {
	private javax.ejb.EntityContext myEntityCtx;
	/**
	 * setEntityContext
	 */
	static final String JNDI_NAME="ejb/co/com/telefonica/atiempo/soltec/ejb/eb/Deco_tarjetaLocalHome";
	
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
	public co.com.telefonica.atiempo.soltec.ejb.eb.Deco_tarjetaKey ejbCreate(
		java.lang.String id_tarjeta,
		java.lang.String id_deco,
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Peticion_stLocal argPeticion_st)
		throws javax.ejb.CreateException {
		setId_tarjeta(id_tarjeta);
		setId_deco(id_deco);
		co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey argPeticion_stPK =
			(co
				.com
				.telefonica
				.atiempo
				.soltec
				.ejb
				.eb
				.Peticion_stKey) argPeticion_st
				.getPrimaryKey();
		setPeticion_st_cod_ave_cd(argPeticion_stPK.cod_ave_cd);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.String id_tarjeta,
		java.lang.String id_deco,
		co
			.com
			.telefonica
			.atiempo
			.soltec
			.ejb
			.eb
			.Peticion_stLocal argPeticion_st)
		throws javax.ejb.CreateException {
		setPeticion_st(argPeticion_st);
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
	 * Get accessor for persistent attribute: estado
	 */
	public abstract java.lang.Integer getEstado();
	/**
	 * Set accessor for persistent attribute: estado
	 */
	public abstract void setEstado(java.lang.Integer newEstado);
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
	 * Get accessor for persistent attribute: accion
	 */
	public abstract java.lang.Integer getAccion();
	/**
	 * Set accessor for persistent attribute: accion
	 */
	public abstract void setAccion(java.lang.Integer newAccion);
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
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.soltec.ejb.eb.Deco_tarjetaKey ejbCreate(
		java.lang.String id_tarjeta,
		java.lang.String id_deco,
		java.lang.Long peticion_st_cod_ave_cd)
		throws javax.ejb.CreateException {
		setId_tarjeta(id_tarjeta);
		setId_deco(id_deco);
		setPeticion_st_cod_ave_cd(peticion_st_cod_ave_cd);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.String id_tarjeta,
		java.lang.String id_deco,
		java.lang.Long peticion_st_cod_ave_cd)
		throws javax.ejb.CreateException {
	}
	/**
	 * Get accessor for persistent attribute: peticion_st_cod_ave_cd
	 */
	public abstract java.lang.Long getPeticion_st_cod_ave_cd();
	/**
	 * Set accessor for persistent attribute: peticion_st_cod_ave_cd
	 */
	public abstract void setPeticion_st_cod_ave_cd(
		java.lang.Long newPeticion_st_cod_ave_cd);

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
	
	public boolean esEsperaDeEliminacionDanhado()
	{
		if(this.getAccion()!=null && this.getAccion().intValue()==ComunInterfaces.accionParDanhado && this.getEstado()==null)
			return true;
		return false;
	}
	
	public boolean estaEliminadoDanhado()
	{
		if(this.getAccion()!=null && this.getAccion().intValue()==ComunInterfaces.accionParDanhado && this.getEstado()!=null && this.getEstado().intValue()==ComunInterfaces.estadoParOk)
			return true;
		return false;
	}
	
	public boolean esEliminacionDanhadoFallida()
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
		if(esEsperaDeEliminacionDanhado())
			return new Integer(8);
		if(estaEliminadoDanhado())
			return new Integer(9);
		if(esEliminacionDanhadoFallida())
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
			return "Activo";
		if(estaActivo())
			return "Activo";
		if(estaEliminado())
			return "Eliminado";
		if(esActivacionFallida())
			return "Activacion Fallida";
		if(esEliminacionFallida())
			return "Eliminacion Fallida";
		if(esEsperaDeActivacion())
			return "En Espera de Activación";
		if(esEsperaDeEliminacion())
			return "En Espera de Eliminación";
		if(esEsperaDeEliminacionDanhado())
			return "En Espera de Eliminación(Deco dañado)";
		if(estaEliminadoDanhado())
			return "Par Deco Dañado. Eliminacion OK.";
		if(esEliminacionDanhadoFallida())
			return "Par Deco Dañado. Eliminacion No OK.";
		if(esAccionNoRecuperado())
			return "Par Deco No Recuperado";
		if(esAccionNoRecuperadoResOk())
			return "Par Deco No Recuperado. Actualización Inventario OK.";
		if(esAccionNoRecuperadoResNoOk())
			return "Par Deco No Recuperado. Actualización Inventario No OK.";
		return "Accion No Ingresada";
	}
		
	public boolean isOriginal(){
		boolean esOri=false;
		if(this.getOriginal()!=null)
		{
			if(this.getOriginal().longValue()==1){
				esOri=true;
			}
		}
		return esOri;
	}
	/**
	 * Get accessor for persistent attribute: original
	 */
	public abstract java.lang.Integer getOriginal();
	/**
	 * Set accessor for persistent attribute: original
	 */
	public abstract void setOriginal(java.lang.Integer newOriginal);
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
}
