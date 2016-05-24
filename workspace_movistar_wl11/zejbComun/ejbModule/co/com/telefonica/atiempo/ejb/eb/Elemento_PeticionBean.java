package co.com.telefonica.atiempo.ejb.eb;


/**
 * Bean implementation class for Enterprise Bean: Elemento_Peticion
 */
public abstract class Elemento_PeticionBean implements javax.ejb.EntityBean {
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
	public co.com.telefonica.atiempo.ejb.eb.Elemento_PeticionKey ejbCreate(java.lang.String serial,
			co.com.telefonica.atiempo.ejb.eb.PeticionLocal argPeticion,			
			java.lang.Long nroTel,
			java.lang.Short accion,
			java.lang.String tipo_equipo,
			java.lang.String tipo_inventario,
			java.lang.Long tipo_elemento,
			java.lang.Long ps_id
			)
			throws javax.ejb.CreateException {
			setSerial(serial);
			co.com.telefonica.atiempo.ejb.eb.PeticionKey argPeticionPK =
				(co.com.telefonica.atiempo.ejb.eb.PeticionKey) argPeticion
					.getPrimaryKey();
			setPeticion_peti_numero(argPeticionPK.peti_numero);
			setAccion(accion);
			setTipo_equipo(tipo_equipo);
			setTelefono(nroTel);
			setTipo_inventario(tipo_inventario);
			setTipo_elemento(tipo_elemento);
			return null;
		}
		/**
		 * ejbPostCreate
		 */
		public void ejbPostCreate(
			java.lang.String serial,
			co.com.telefonica.atiempo.ejb.eb.PeticionLocal argPeticion,
			java.lang.Long nroTel,
			java.lang.Short accion,	
			java.lang.String tipo_equipo,
			java.lang.String tipo_inventario,
			java.lang.Long tipo_elemento,
			java.lang.Long ps_id)
			throws javax.ejb.CreateException {
		    setSerial(serial);
	         setPeticion(argPeticion);
				setAccion(accion);
				setTipo_equipo(tipo_equipo);
				setTelefono(nroTel);
				setTipo_inventario(tipo_inventario);
				setTipo_elemento(tipo_elemento);
				setPs_id(ps_id);
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
     * Get accessor for persistent attribute: serial
     */
    public abstract java.lang.String getSerial();
    /**
     * Set accessor for persistent attribute: serial
     */
    public abstract void setSerial(java.lang.String newSerial);
    /**
     * Get accessor for persistent attribute: telefono
     */
    public abstract java.lang.Long getTelefono();
    /**
     * Set accessor for persistent attribute: telefono
     */
    public abstract void setTelefono(java.lang.Long newTelefono);
    /**
     * Get accessor for persistent attribute: accion
     */
    public abstract java.lang.Short getAccion();
    /**
     * Set accessor for persistent attribute: accion
     */
    public abstract void setAccion(java.lang.Short newAccion);
    /**
     * Get accessor for persistent attribute: tipo_equipo
     */
    public abstract java.lang.String getTipo_equipo();
    /**
     * Set accessor for persistent attribute: tipo_equipo
     */
    public abstract void setTipo_equipo(java.lang.String newTipo_equipo);
    /**
     * Get accessor for persistent attribute: tipo_elemento
     */
    public abstract java.lang.Long getTipo_elemento();
    /**
     * Set accessor for persistent attribute: tipo_elemento
     */
    public abstract void setTipo_elemento(java.lang.Long newTipo_elemento);
    /**
     * Get accessor for persistent attribute: tipo_inventario
     */
    public abstract java.lang.String getTipo_inventario();
    /**
     * Set accessor for persistent attribute: tipo_inventario
     */
    public abstract void setTipo_inventario(java.lang.String newTipo_inventario);
	/**
	 * ejbCreate
	 */
	public co.com.telefonica.atiempo.ejb.eb.Elemento_PeticionKey ejbCreate(String serial,PeticionLocal peticion,Long nroTel,Short accion) throws javax.ejb.CreateException
	{
		setSerial(serial);
		PeticionKey argPeticionPK =(PeticionKey) peticion.getPrimaryKey();
		setPeticion_peti_numero(argPeticionPK.peti_numero);
		setTelefono(nroTel);
		setAccion(accion);
		return null;
	}
	
	public void ejbPostCreate(String serial,PeticionLocal peticion,Long nroTel,Short accion) throws javax.ejb.CreateException
	{
		setPeticion(peticion);
	}
	
	public co.com.telefonica.atiempo.ejb.eb.Elemento_PeticionKey ejbCreate(
		java.lang.String serial,
		java.lang.Long peticion_peti_numero)
		throws javax.ejb.CreateException {
		setSerial(serial);
		setPeticion_peti_numero(peticion_peti_numero);
		return null;
	}
	/**
	 * ejbPostCreate
	 */
	public void ejbPostCreate(
		java.lang.String serial,
		java.lang.Long peticion_peti_numero)
		throws javax.ejb.CreateException {
	}
    /**
     * This method was generated for supporting the relationship role named peticion.
     * It will be deleted/edited when the relationship is deleted/edited.
     */
    public abstract co.com.telefonica.atiempo.ejb.eb.PeticionLocal getPeticion();
    /**
     * This method was generated for supporting the relationship role named peticion.
     * It will be deleted/edited when the relationship is deleted/edited.
     */
    public abstract void setPeticion(
        co.com.telefonica.atiempo.ejb.eb.PeticionLocal aPeticion);
    
	public abstract void setPeticion_peti_numero(
			java.lang.Long newPeticion_peti_numero);
    /**
     * Get accessor for persistent attribute: ps_id
     */
    public abstract java.lang.Long getPs_id();
    /**
     * Set accessor for persistent attribute: ps_id
     */
    public abstract void setPs_id(java.lang.Long newPs_id);
	/**
	 * Get accessor for persistent attribute: marca
	 */
	public abstract java.lang.String getMarca();
	/**
	 * Set accessor for persistent attribute: marca
	 */
	public abstract void setMarca(java.lang.String newMarca);
	/**
	 * Get accessor for persistent attribute: modelo
	 */
	public abstract java.lang.String getModelo();
	/**
	 * Set accessor for persistent attribute: modelo
	 */
	public abstract void setModelo(java.lang.String newModelo);
	/**
	 * Get accessor for persistent attribute: num_celular
	 */
	public abstract java.lang.String getNum_celular();
	/**
	 * Set accessor for persistent attribute: num_celular
	 */
	public abstract void setNum_celular(java.lang.String newNum_celular);
	/**
	 * Get accessor for persistent attribute: num_doc_sap
	 */
	public abstract java.lang.String getNum_doc_sap();
	/**
	 * Set accessor for persistent attribute: num_doc_sap
	 */
	public abstract void setNum_doc_sap(java.lang.String newNum_doc_sap);
	/**
	 * Get accessor for persistent attribute: fec_cont_sap
	 */
	public abstract java.lang.String getFec_cont_sap();
	/**
	 * Set accessor for persistent attribute: fec_cont_sap
	 */
	public abstract void setFec_cont_sap(java.lang.String newFec_cont_sap);
	/**
	 * Get accessor for persistent attribute: clase_mov_sap
	 */
	public abstract java.lang.String getClase_mov_sap();
	/**
	 * Set accessor for persistent attribute: clase_mov_sap
	 */
	public abstract void setClase_mov_sap(java.lang.String newClase_mov_sap);
	/**
	 * Get accessor for persistent attribute: pos_doc_sap
	 */
	public abstract int getPos_doc_sap();
	/**
	 * Set accessor for persistent attribute: pos_doc_sap
	 */
	public abstract void setPos_doc_sap(int newPos_doc_sap);
	/**
	 * Get accessor for persistent attribute: num_material_sap
	 */
	public abstract java.lang.String getNum_material_sap();
	/**
	 * Set accessor for persistent attribute: num_material_sap
	 */
	public abstract void setNum_material_sap(
		java.lang.String newNum_material_sap);
	/**
	 * Get accessor for persistent attribute: centro_sap
	 */
	public abstract java.lang.String getCentro_sap();
	/**
	 * Set accessor for persistent attribute: centro_sap
	 */
	public abstract void setCentro_sap(java.lang.String newCentro_sap);
	/**
	 * Get accessor for persistent attribute: almacen_sap
	 */
	public abstract java.lang.String getAlmacen_sap();
	/**
	 * Set accessor for persistent attribute: almacen_sap
	 */
	public abstract void setAlmacen_sap(java.lang.String newAlmacen_sap);
	/**
	 * Get accessor for persistent attribute: cod_lote_sap
	 */
	public abstract java.lang.String getCod_lote_sap();
	/**
	 * Set accessor for persistent attribute: cod_lote_sap
	 */
	public abstract void setCod_lote_sap(java.lang.String newCod_lote_sap);
	/**
	 * Get accessor for persistent attribute: und_medida_sap
	 */
	public abstract java.lang.String getUnd_medida_sap();
	/**
	 * Set accessor for persistent attribute: und_medida_sap
	 */
	public abstract void setUnd_medida_sap(java.lang.String newUnd_medida_sap);
	/**
	 * Get accessor for persistent attribute: centr_cost_sap
	 */
	public abstract java.lang.String getCentr_cost_sap();
	/**
	 * Set accessor for persistent attribute: centr_cost_sap
	 */
	public abstract void setCentr_cost_sap(java.lang.String newCentr_cost_sap);
	/**
	 * Get accessor for persistent attribute: area_func_sap
	 */
	public abstract java.lang.String getArea_func_sap();
	/**
	 * Set accessor for persistent attribute: area_func_sap
	 */
	public abstract void setArea_func_sap(java.lang.String newArea_func_sap);
	/**
	 * Get accessor for persistent attribute: element_pep_sap
	 */
	public abstract java.lang.String getElement_pep_sap();
	/**
	 * Set accessor for persistent attribute: element_pep_sap
	 */
	public abstract void setElement_pep_sap(java.lang.String newElement_pep_sap);
	/**
	 * Get accessor for persistent attribute: flag_pet_curso
	 */
	public abstract java.lang.String getFlag_pet_curso();
	/**
	 * Set accessor for persistent attribute: flag_pet_curso
	 */
	public abstract void setFlag_pet_curso(java.lang.String newFlag_pet_curso);
	/**
	 * Get accessor for persistent attribute: flag_mat_sap
	 */
	public abstract java.lang.String getFlag_mat_sap();
	/**
	 * Set accessor for persistent attribute: flag_mat_sap
	 */
	public abstract void setFlag_mat_sap(java.lang.String newFlag_mat_sap);
	/**
	 * Get accessor for persistent attribute: caracteristica
	 */
	public abstract java.lang.String getCaracteristica();
	/**
	 * Set accessor for persistent attribute: caracteristica
	 */
	public abstract void setCaracteristica(java.lang.String newCaracteristica);
	/**
	 * Get accessor for persistent attribute: guiaAutoInst
	 */
	public abstract java.lang.String getGuiaAutoInst();
	/**
	 * Set accessor for persistent attribute: guiaAutoInst
	 */
	public abstract void setGuiaAutoInst(java.lang.String newGuiaAutoInst);
	/**
	 * Get accessor for persistent attribute: fecha_guia_auto_inst
	 */
	public abstract java.sql.Timestamp getFecha_guia_auto_inst();
	/**
	 * Set accessor for persistent attribute: fecha_guia_auto_inst
	 */
	public abstract void setFecha_guia_auto_inst(
		java.sql.Timestamp newFecha_guia_auto_inst);
}
