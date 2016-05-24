package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;
import java.util.Collection;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author mfmendezs
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR020EPositionHeader implements Serializable {
	
	private String sapMaterialCode;
	private String material;
	private String plant;
	private String storage;
	private String batchCode;	
	private String matQuantity;
	private String measurementUnit;
	private String costCenter;
	private String funcAreaLong;
	private String pepElement;
	/*Arreglo de Strings (Seriales de equipos)*/
	private Collection serials;
	
	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0){
		if(arg0 instanceof TR020EPositionHeader){
			TR020EPositionHeader other = (TR020EPositionHeader) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(sapMaterialCode,other.getSapMaterialCode())
				&& EqualityUtilities.equals(material,other.getMaterial())
				&& EqualityUtilities.equals(plant,other.getPlant())
				&& EqualityUtilities.equals(storage,other.getStorage())
				&& EqualityUtilities.equals(batchCode,other.getBatchCode())
				&& EqualityUtilities.equals(measurementUnit,other.getMeasurementUnit())				
				&& EqualityUtilities.equals(costCenter,other.getCostCenter())
				&& EqualityUtilities.equals(funcAreaLong,other.getFuncAreaLong())
				&& EqualityUtilities.equals(pepElement,other.getPepElement())
				&& EqualityUtilities.equals(serials,other.getSerials());
		}else{
			return false;
		}
	}

	/**
	 * @return Returns the batchCode.
	 */
	public String getBatchCode() {
		return batchCode;
	}
	/**
	 * @param batchCode The batchCode to set.
	 */
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	/**
	 * @return Returns the costCenter.
	 */
	public String getCostCenter() {
		return costCenter;
	}
	/**
	 * @param costCenter The costCenter to set.
	 */
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}
	/**
	 * @return Returns the funcAreaLong.
	 */
	public String getFuncAreaLong() {
		return funcAreaLong;
	}
	/**
	 * @param funcAreaLong The funcAreaLong to set.
	 */
	public void setFuncAreaLong(String funcAreaLong) {
		this.funcAreaLong = funcAreaLong;
	}
	/**
	 * @return Returns the material.
	 */
	public String getMaterial() {
		return material;
	}
	/**
	 * @param material The material to set.
	 */
	public void setMaterial(String material) {
		this.material = material;
	}
	/**
	 * @return Returns the matQuantity.
	 */
	public String getMatQuantity() {
		return matQuantity;
	}
	/**
	 * @param matQuantity The matQuantity to set.
	 */
	public void setMatQuantity(String matQuantity) {
		this.matQuantity = matQuantity;
	}
	/**
	 * @return Returns the measurementUnit.
	 */
	public String getMeasurementUnit() {
		return measurementUnit;
	}
	/**
	 * @param measurementUnit The measurementUnit to set.
	 */
	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
	/**
	 * @return Returns the pepElement.
	 */
	public String getPepElement() {
		return pepElement;
	}
	/**
	 * @param pepElement The pepElement to set.
	 */
	public void setPepElement(String pepElement) {
		this.pepElement = pepElement;
	}
	/**
	 * @return Returns the plant.
	 */
	public String getPlant() {
		return plant;
	}
	/**
	 * @param plant The plant to set.
	 */
	public void setPlant(String plant) {
		this.plant = plant;
	}
	/**
	 * @return Returns the sapMaterialCode.
	 */
	public String getSapMaterialCode() {
		return sapMaterialCode;
	}
	/**
	 * @param sapMaterialCode The sapMaterialCode to set.
	 */
	public void setSapMaterialCode(String sapMaterialCode) {
		this.sapMaterialCode = sapMaterialCode;
	}
	/**
	 * @return Returns the serials.
	 */
	public Collection getSerials() {
		return serials;
	}
	/**
	 * @param serials The serials to set.
	 */
	public void setSerials(Collection serials) {
		this.serials = serials;
	}
	/**
	 * @return Returns the storage.
	 */
	public String getStorage() {
		return storage;
	}
	/**
	 * @param storage The storage to set.
	 */
	public void setStorage(String storage) {
		this.storage = storage;
	}
}
