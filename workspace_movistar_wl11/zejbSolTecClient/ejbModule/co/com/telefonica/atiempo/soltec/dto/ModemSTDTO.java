/*
 * Created on 21-06-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.dto;

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ModemSTDTO {
	
	private Long num_peticion;
	private String serial;
	private Long telefono;
	private Integer accion;
	private Long tipo;
	private String marca;
	private String modelo;
	private String cod_material;	
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
	
	// DAVID: req 2172, agenda modifica mensajes, dic 9 de 2010
	public Long getTipo() {
		return tipo;
	}
	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}
	/*
	 *	CR - 00026148 - Jun 24, 2009
	 *		Se agrega atributo de Marca - German P.
	 */

	/**
	 * @return
	 */
	public Integer getAccion() {
		return accion;
	}

	/**
	 * @return
	 */
	public Long getNum_peticion() {
		return num_peticion;
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
	 * @param integer
	 */
	public void setAccion(Integer integer) {
		accion = integer;
	}

	/**
	 * @param long1
	 */
	public void setNum_peticion(Long long1) {
		num_peticion = long1;
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
	 * @return Returns the cod_material.
	 */
	public String getCod_material() {
		return cod_material;
	}
	/**
	 * @param cod_material The cod_material to set.
	 */
	public void setCod_material(String cod_material) {
		this.cod_material = cod_material;
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
}
