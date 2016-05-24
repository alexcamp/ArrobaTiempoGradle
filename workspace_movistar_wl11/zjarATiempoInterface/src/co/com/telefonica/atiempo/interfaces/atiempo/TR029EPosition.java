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
public class TR029EPosition implements Serializable {
	
	private String posDocMat;
	private String material;
	private String plant;
	private String storage;
	private String matQuantity;
	private String numPurchasingDoc;
	private int numPositionPurchDoc;
	private String segment;
	/*Arreglo de Strings*/
	private Collection serials;
	
	
	public int hashCode(){
		return super.hashCode();
	}
	
	public boolean equals(Object arg0){
		if(arg0 instanceof TR029EPosition){
			TR029EPosition other = (TR029EPosition) arg0;
			return super.equals(arg0)
				&& posDocMat == other.posDocMat
				&& EqualityUtilities.equals(material,other.material)
				&& EqualityUtilities.equals(plant,other.plant)
				&& EqualityUtilities.equals(storage,other.storage)
				&& EqualityUtilities.equals(matQuantity,other.matQuantity)
				&& EqualityUtilities.equals(numPurchasingDoc,other.numPurchasingDoc)				
				&& numPositionPurchDoc == other.numPositionPurchDoc
				&& EqualityUtilities.equals(segment,other.segment)
				&& EqualityUtilities.equals(serials,other.serials);
		}else{
			return false;
		}
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
	 * @return Returns the numPositionPurchDoc.
	 */
	public int getNumPositionPurchDoc() {
		return numPositionPurchDoc;
	}
	/**
	 * @param numPositionPurchDoc The numPositionPurchDoc to set.
	 */
	public void setNumPositionPurchDoc(int numPositionPurchDoc) {
		this.numPositionPurchDoc = numPositionPurchDoc;
	}
	/**
	 * @return Returns the numPurchasingDoc.
	 */
	public String getNumPurchasingDoc() {
		return numPurchasingDoc;
	}
	/**
	 * @param numPurchasingDoc The numPurchasingDoc to set.
	 */
	public void setNumPurchasingDoc(String numPurchasingDoc) {
		this.numPurchasingDoc = numPurchasingDoc;
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
	 * @return Returns the posDocMat.
	 */
	public String getPosDocMat() {
		return posDocMat;
	}
	/**
	 * @param posDocMat The posDocMat to set.
	 */
	public void setPosDocMat(String posDocMat) {
		this.posDocMat = posDocMat;
	}
	/**
	 * @return Returns the segment.
	 */
	public String getSegment() {
		return segment;
	}
	/**
	 * @param segment The segment to set.
	 */
	public void setSegment(String segment) {
		this.segment = segment;
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
