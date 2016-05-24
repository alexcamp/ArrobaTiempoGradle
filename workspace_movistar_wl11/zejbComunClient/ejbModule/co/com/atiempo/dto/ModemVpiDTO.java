package co.com.atiempo.dto;

import com.telefonica_chile.comun.ComunInterfaces;

public class ModemVpiDTO
{
	private Long peti_numero;
	private String serial;
	//TCS-gquevedo May 18, 2009 CR.23338 INICIO
	private short tipo;
	private String descTipo;
	//TCS-gquevedo May 18, 2009 CR.23338 FIN
	private Long telefono;
	private short accion;
	private String descAccion;
	private String modelo;
	private String codMaterial;
	/*RQ.8595 - mfmendez*/
	private String postingDateSAP;
	private String moveTypeSAP;
	private String materialCodeSAP;
	private String materialSAP;
	private String plantSAP;
	private String storageSAP;
	private String batchCodeSAP;	
	private String measurementUnitSAP;
	private String costCenterSAP;
	private String funcAreaLongSAP;
	private String pepElementSAP;	
	private String flagMatSAP;
	
	private String fechaActivacion;

	private boolean marcaPrint;
	
	/*
	 *	CR - 00026148 - Jun 24, 2009
	 *		Se agrega atributo de Marca - German P.
	 */
	private String marca;
	

	public ModemVpiDTO()
	{
		marcaPrint=false;
	}
	/**
	 * @return
	 */
	public short getAccion() {
		return accion;
	}

	/**
	 * @return
	 */
	public Long getPeti_numero() {
		return peti_numero;
	}

	/**
	 * @return
	 */
	public String getSerial() {
		return serial;
	}

	/**
	 * @return
	 */
	public Long getTelefono() {
		return telefono;
	}

	/**
	 * @param i
	 */
	public void setAccion(short i)
	{
		accion = i;
		switch(accion)
		{
			case ComunInterfaces.accionModemNoAction:
				setDescAccion("No Accion");
				break;
			case ComunInterfaces.accionModemOcupar:
				setDescAccion("Ocupar Modem");
				break;
			case ComunInterfaces.accionModemLiberar:
				setDescAccion("Liberar Modem");
				break;
			case ComunInterfaces.accionModemNoRecuperado:
				setDescAccion("Modem No Recuperado");
				break;
			case ComunInterfaces.accionModemCambioNoRec:
				setDescAccion("Cambio de Modem No Recuperado");
				break;
			case ComunInterfaces.accionModemCambioAveriado:
				setDescAccion("Cambio de Modem Dañado");
				break;
			case ComunInterfaces.accionModemConfigurado:
				setDescAccion("Modem Configurado");
				break;
			case ComunInterfaces.accionModemNoConfigurado:
				setDescAccion("Modem No Configurado ACS");
				break;
			case ComunInterfaces.accionModemConfiguradoSOA:
				setDescAccion("Modem Configurado – Pend OSB");
				break;
			case ComunInterfaces.accionModemConfiguradoTOA:
				setDescAccion("Modem Configurado – Pend TOA");
				break;
		}
	}
	
	/**
	 * @return Returns the modelo.
	 */
	public String getModelo() {
		return modelo;
	}
	/**
	 * @param modelo The modelo to set.
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	/**
	 * @param long1
	 */
	public void setPeti_numero(Long long1) {
		peti_numero = long1;
	}

	/**
	 * @param string
	 */
	public void setSerial(String string) {
		serial = string;
	}

	/**
	 * @param long1
	 */
	public void setTelefono(Long long1) {
		telefono = long1;
	}

	/**
	 * @return
	 */
	public String getDescAccion() {
		return descAccion;
	}

	/**
	 * @param string
	 */
	public void setDescAccion(String string) {
		descAccion = string;
	}

	/**
	 * @return
	 */
	public boolean isMarcaPrint() {
		return marcaPrint;
	}

	/**
	 * @param b
	 */
	public void setMarcaPrint(boolean b) {
		marcaPrint = b;
	}
	
	//TCS-gquevedo May 18, 2009 CR.23338 INICIO
	/**
	 * @return Returns the tipo.
	 */
	public short getTipo() {
		return tipo;
	}
	/**
	 * @param tipo The tipo to set.
	 */
	public void setTipo(short tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return Returns the descTipo.
	 */
	public String getDescTipo() {
		return descTipo;
	}
	/**
	 * @param descTipo The descTipo to set.
	 */
	public void setDescTipo(String descTipo) {
		this.descTipo = descTipo;
	}
	//TCS-gquevedo May 18, 2009 CR.23338 FIN
	
	/**
	 * @return Returns the marca.
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * @param marca The marca to set.
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	
	/**
	 * @return Returns the codMaterial.
	 */
	public String getCodMaterial() {
		return codMaterial;
	}
	/**
	 * @param codMaterial The codMaterial to set.
	 */
	public void setCodMaterial(String codMaterial) {
		this.codMaterial = codMaterial;
	}
	/**
	 * @return Returns the batchCodeSAP.
	 */
	public String getBatchCodeSAP() {
		return batchCodeSAP;
	}
	/**
	 * @param batchCodeSAP The batchCodeSAP to set.
	 */
	public void setBatchCodeSAP(String batchCodeSAP) {
		this.batchCodeSAP = batchCodeSAP;
	}
	/**
	 * @return Returns the costCenterSAP.
	 */
	public String getCostCenterSAP() {
		return costCenterSAP;
	}
	/**
	 * @param costCenterSAP The costCenterSAP to set.
	 */
	public void setCostCenterSAP(String costCenterSAP) {
		this.costCenterSAP = costCenterSAP;
	}
	/**
	 * @return Returns the funcAreaLongSAP.
	 */
	public String getFuncAreaLongSAP() {
		return funcAreaLongSAP;
	}
	/**
	 * @param funcAreaLongSAP The funcAreaLongSAP to set.
	 */
	public void setFuncAreaLongSAP(String funcAreaLongSAP) {
		this.funcAreaLongSAP = funcAreaLongSAP;
	}
	/**
	 * @return Returns the materialCodeSAP.
	 */
	public String getMaterialCodeSAP() {
		return materialCodeSAP;
	}
	/**
	 * @param materialCodeSAP The materialCodeSAP to set.
	 */
	public void setMaterialCodeSAP(String materialCodeSAP) {
		if(materialCodeSAP != null && !materialCodeSAP.equals(""))
			this.materialCodeSAP = materialCodeSAP;
		else
			this.materialCodeSAP = "0";
	}
	/**
	 * @return Returns the materialSAP.
	 */
	public String getMaterialSAP() {
		return materialSAP;
	}
	/**
	 * @param materialSAP The materialSAP to set.
	 */
	public void setMaterialSAP(String materialSAP) {
		this.materialSAP = materialSAP;
	}
	/**
	 * @return Returns the measurementUnitSAP.
	 */
	public String getMeasurementUnitSAP() {
		return measurementUnitSAP;
	}
	/**
	 * @param measurementUnitSAP The measurementUnitSAP to set.
	 */
	public void setMeasurementUnitSAP(String measurementUnitSAP) {
		this.measurementUnitSAP = measurementUnitSAP;
	}
	/**
	 * @return Returns the moveTypeSAP.
	 */
	public String getMoveTypeSAP() {
		return moveTypeSAP;
	}
	/**
	 * @param moveTypeSAP The moveTypeSAP to set.
	 */
	public void setMoveTypeSAP(String moveTypeSAP) {
		this.moveTypeSAP = moveTypeSAP;
	}
	/**
	 * @return Returns the pepElementSAP.
	 */
	public String getPepElementSAP() {
		return pepElementSAP;
	}
	/**
	 * @param pepElementSAP The pepElementSAP to set.
	 */
	public void setPepElementSAP(String pepElementSAP) {
		this.pepElementSAP = pepElementSAP;
	}
	/**
	 * @return Returns the plantSAP.
	 */
	public String getPlantSAP() {
		return plantSAP;
	}
	/**
	 * @param plantSAP The plantSAP to set.
	 */
	public void setPlantSAP(String plantSAP) {
		this.plantSAP = plantSAP;
	}
	/**
	 * @return Returns the postingDateSAP.
	 */
	public String getPostingDateSAP() {
		return postingDateSAP;
	}
	/**
	 * @param postingDateSAP The postingDateSAP to set.
	 */
	public void setPostingDateSAP(String postingDateSAP) {
		this.postingDateSAP = postingDateSAP;
	}
	/**
	 * @return Returns the storageSAP.
	 */
	public String getStorageSAP() {
		return storageSAP;
	}
	/**
	 * @param storageSAP The storageSAP to set.
	 */
	public void setStorageSAP(String storageSAP) {
		this.storageSAP = storageSAP;
	}
	/**
	 * @return Returns the flagMatSAP.
	 */
	public String getFlagMatSAP() {
		return flagMatSAP;
	}
	/**
	 * @param flagMatSAP The flagMatSAP to set.
	 */
	public void setFlagMatSAP(String flagMatSAP) {
		this.flagMatSAP = flagMatSAP;
	}
	/**
	 * @return Devuelve fechaActivacion.
	 */
	public String getFechaActivacion() {
		return fechaActivacion;
	}
	/**
	 * @param fechaActivacion El fechaActivacion a establecer.
	 */
	public void setFechaActivacion(String fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}
}
