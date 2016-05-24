package co.com.atiempo.dto;

public class DecoTarDTO
{
	private String deco;
	private String tarjeta;
	private Integer accion;
	private String deco_reference;
	
	private Long operationComercial;
	private String decoSerial;
	private String decoType;
	private String decoBrand;
	private Integer estado;
	private Integer codError;
	
	/*RQ.8595 - mfmendez*/
	private String decoPostingDateSAP;
	private String decoMoveTypeSAP;
	private String decoMaterialCodeSAP;
	private String decoMaterialSAP;
	private String decoPlantSAP;
	private String decoStorageSAP;
	private String decoBatchCodeSAP;	
	private String decoMeasurementUnitSAP;
	private String decoCostCenterSAP;
	private String decoFuncAreaLongSAP;
	private String decoPepElementSAP;
	private String decoFlagMatSAP;
	
	private String cardPostingDateSAP;
	private String cardMoveTypeSAP;
	private String cardMaterialCodeSAP;
	private String cardMaterialSAP;
	private String cardPlantSAP;
	private String cardStorageSAP;
	private String cardBatchCodeSAP;	
	private String cardMeasurementUnitSAP;
	private String cardCostCenterSAP;
	private String cardFuncAreaLongSAP;
	private String cardPepElementSAP;
	private String cardFlagMatSAP;
	/*FIN - RQ.8595 - mfmendez*/
	
	/*Datos del disco duro*/
	private String ddtvSerial;
	private String ddtvMarca;
	private String ddtvModelo;
	
	/*Datos de disco duro*/
	
	
	public DecoTarDTO(String deco,String tarjeta, String tipoDeco)
	{
		this.deco=deco;
		this.tarjeta=tarjeta;
		this.deco_reference=tipoDeco;
	}
	public DecoTarDTO(String deco,String tarjeta)
		{
			this.deco=deco;
			this.tarjeta=tarjeta;
			
		}
	/**
	 * @return
	 */
	public String getDeco() {
		return deco;
	}

	/**
	 * @return
	 */
	public String getTarjeta() {
		return tarjeta;
	}

	/**
	 * @param string
	 */
	public void setDeco(String string) {
		deco = string;
	}

	/**
	 * @param string
	 */
	public void setTarjeta(String string) {
		tarjeta = string;
	}

	public String toString()
	{
		return "Deco:"+deco+" - Tarjeta:"+tarjeta; 
	}

	public boolean equals(Object obj)
	{
		if ((! (obj instanceof DecoTarDTO)) || (obj == null))
		   return (false) ;
        
		DecoTarDTO otro = (DecoTarDTO) obj ;
		
//		if(deco==null || otro.getDeco()==null || tarjeta==null || otro.getTarjeta()==null||deco_reference==null || otro.getDeco_reference()==null  )
//			return false; 
//		return (this.deco.equals(otro.getDeco())  && this.tarjeta.equals(otro.getTarjeta()) && this.deco_reference.equals(otro.getDeco_reference())) ;
		if(deco==null || otro.getDeco()==null || tarjeta==null || otro.getTarjeta()==null||deco_reference==null  )
					return false; 
		return (this.deco.equals(otro.getDeco())  && this.tarjeta.equals(otro.getTarjeta()) ) ;
	}

	/**
	 * @return
	 */
	public Integer getAccion() {
		return accion;
	}

	/**
	 * @param integer
	 */
	public void setAccion(Integer integer) {
		accion = integer;
	}

	/**
	 * @return
	 */
	public String getDeco_reference() {
		return deco_reference;
	}

	/**
	 * @param string
	 */
	public void setDeco_reference(String string) {
		deco_reference = string;
	}

	public String getDecoBrand() {
		return decoBrand;
	}
	public void setDecoBrand(String decoBrand) {
		this.decoBrand = decoBrand;
	}
	public String getDecoSerial() {
		return decoSerial;
	}
	public void setDecoSerial(String decoSerial) {
		this.decoSerial = decoSerial;
	}
	public String getDecoType() {
		return decoType;
	}
	public void setDecoType(String decoType) {
		this.decoType = decoType;
	}
	public Long getOperationComercial() {
		return operationComercial;
	}
	public void setOperationComercial(Long operationComercial) {
		this.operationComercial = operationComercial;
	}
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public Integer getCodError() {
		return codError;
	}
	public void setCodError(Integer codError) {
		this.codError = codError;
	}
	/**
	 * @return Returns the cardBatchCodeSAP.
	 */
	public String getCardBatchCodeSAP() {
		return cardBatchCodeSAP;
	}
	/**
	 * @param cardBatchCodeSAP The cardBatchCodeSAP to set.
	 */
	public void setCardBatchCodeSAP(String cardBatchCodeSAP) {
		this.cardBatchCodeSAP = cardBatchCodeSAP;
	}
	/**
	 * @return Returns the cardCostCenterSAP.
	 */
	public String getCardCostCenterSAP() {
		return cardCostCenterSAP;
	}
	/**
	 * @param cardCostCenterSAP The cardCostCenterSAP to set.
	 */
	public void setCardCostCenterSAP(String cardCostCenterSAP) {
		this.cardCostCenterSAP = cardCostCenterSAP;
	}
	/**
	 * @return Returns the cardFuncAreaLongSAP.
	 */
	public String getCardFuncAreaLongSAP() {
		return cardFuncAreaLongSAP;
	}
	/**
	 * @param cardFuncAreaLongSAP The cardFuncAreaLongSAP to set.
	 */
	public void setCardFuncAreaLongSAP(String cardFuncAreaLongSAP) {
		this.cardFuncAreaLongSAP = cardFuncAreaLongSAP;
	}
	/**
	 * @return Returns the cardMaterialCodeSAP.
	 */
	public String getCardMaterialCodeSAP() {
		return cardMaterialCodeSAP;
	}
	/**
	 * @param cardMaterialCodeSAP The cardMaterialCodeSAP to set.
	 */
	public void setCardMaterialCodeSAP(String cardMaterialCodeSAP) {
		this.cardMaterialCodeSAP = cardMaterialCodeSAP;
	}
	/**
	 * @return Returns the cardMaterialSAP.
	 */
	public String getCardMaterialSAP() {
		return cardMaterialSAP;
	}
	/**
	 * @param cardMaterialSAP The cardMaterialSAP to set.
	 */
	public void setCardMaterialSAP(String cardMaterialSAP) {
		this.cardMaterialSAP = cardMaterialSAP;
	}
	/**
	 * @return Returns the cardMeasurementUnitSAP.
	 */
	public String getCardMeasurementUnitSAP() {
		return cardMeasurementUnitSAP;
	}
	/**
	 * @param cardMeasurementUnitSAP The cardMeasurementUnitSAP to set.
	 */
	public void setCardMeasurementUnitSAP(String cardMeasurementUnitSAP) {
		this.cardMeasurementUnitSAP = cardMeasurementUnitSAP;
	}
	/**
	 * @return Returns the cardMoveTypeSAP.
	 */
	public String getCardMoveTypeSAP() {
		return cardMoveTypeSAP;
	}
	/**
	 * @param cardMoveTypeSAP The cardMoveTypeSAP to set.
	 */
	public void setCardMoveTypeSAP(String cardMoveTypeSAP) {
		this.cardMoveTypeSAP = cardMoveTypeSAP;
	}
	/**
	 * @return Returns the cardPepElementSAP.
	 */
	public String getCardPepElementSAP() {
		return cardPepElementSAP;
	}
	/**
	 * @param cardPepElementSAP The cardPepElementSAP to set.
	 */
	public void setCardPepElementSAP(String cardPepElementSAP) {
		this.cardPepElementSAP = cardPepElementSAP;
	}
	/**
	 * @return Returns the cardPlantSAP.
	 */
	public String getCardPlantSAP() {
		return cardPlantSAP;
	}
	/**
	 * @param cardPlantSAP The cardPlantSAP to set.
	 */
	public void setCardPlantSAP(String cardPlantSAP) {
		this.cardPlantSAP = cardPlantSAP;
	}
	/**
	 * @return Returns the cardPostingDateSAP.
	 */
	public String getCardPostingDateSAP() {
		return cardPostingDateSAP;
	}
	/**
	 * @param cardPostingDateSAP The cardPostingDateSAP to set.
	 */
	public void setCardPostingDateSAP(String cardPostingDateSAP) {
		this.cardPostingDateSAP = cardPostingDateSAP;
	}
	/**
	 * @return Returns the cardStorageSAP.
	 */
	public String getCardStorageSAP() {
		return cardStorageSAP;
	}
	/**
	 * @param cardStorageSAP The cardStorageSAP to set.
	 */
	public void setCardStorageSAP(String cardStorageSAP) {
		this.cardStorageSAP = cardStorageSAP;
	}
	/**
	 * @return Returns the decoBatchCodeSAP.
	 */
	public String getDecoBatchCodeSAP() {
		return decoBatchCodeSAP;
	}
	/**
	 * @param decoBatchCodeSAP The decoBatchCodeSAP to set.
	 */
	public void setDecoBatchCodeSAP(String decoBatchCodeSAP) {
		this.decoBatchCodeSAP = decoBatchCodeSAP;
	}
	/**
	 * @return Returns the decoCostCenterSAP.
	 */
	public String getDecoCostCenterSAP() {
		return decoCostCenterSAP;
	}
	/**
	 * @param decoCostCenterSAP The decoCostCenterSAP to set.
	 */
	public void setDecoCostCenterSAP(String decoCostCenterSAP) {
		this.decoCostCenterSAP = decoCostCenterSAP;
	}
	/**
	 * @return Returns the decoFuncAreaLongSAP.
	 */
	public String getDecoFuncAreaLongSAP() {
		return decoFuncAreaLongSAP;
	}
	/**
	 * @param decoFuncAreaLongSAP The decoFuncAreaLongSAP to set.
	 */
	public void setDecoFuncAreaLongSAP(String decoFuncAreaLongSAP) {
		this.decoFuncAreaLongSAP = decoFuncAreaLongSAP;
	}
	/**
	 * @return Returns the decoMaterialCodeSAP.
	 */
	public String getDecoMaterialCodeSAP() {
		return decoMaterialCodeSAP;
	}
	/**
	 * @param decoMaterialCodeSAP The decoMaterialCodeSAP to set.
	 */
	public void setDecoMaterialCodeSAP(String decoMaterialCodeSAP) {
		this.decoMaterialCodeSAP = decoMaterialCodeSAP;
	}
	/**
	 * @return Returns the decoMaterialSAP.
	 */
	public String getDecoMaterialSAP() {
		return decoMaterialSAP;
	}
	/**
	 * @param decoMaterialSAP The decoMaterialSAP to set.
	 */
	public void setDecoMaterialSAP(String decoMaterialSAP) {
		this.decoMaterialSAP = decoMaterialSAP;
	}
	/**
	 * @return Returns the decoMeasurementUnitSAP.
	 */
	public String getDecoMeasurementUnitSAP() {
		return decoMeasurementUnitSAP;
	}
	/**
	 * @param decoMeasurementUnitSAP The decoMeasurementUnitSAP to set.
	 */
	public void setDecoMeasurementUnitSAP(String decoMeasurementUnitSAP) {
		this.decoMeasurementUnitSAP = decoMeasurementUnitSAP;
	}
	/**
	 * @return Returns the decoMoveTypeSAP.
	 */
	public String getDecoMoveTypeSAP() {
		return decoMoveTypeSAP;
	}
	/**
	 * @param decoMoveTypeSAP The decoMoveTypeSAP to set.
	 */
	public void setDecoMoveTypeSAP(String decoMoveTypeSAP) {
		this.decoMoveTypeSAP = decoMoveTypeSAP;
	}
	/**
	 * @return Returns the decoPepElementSAP.
	 */
	public String getDecoPepElementSAP() {
		return decoPepElementSAP;
	}
	/**
	 * @param decoPepElementSAP The decoPepElementSAP to set.
	 */
	public void setDecoPepElementSAP(String decoPepElementSAP) {
		this.decoPepElementSAP = decoPepElementSAP;
	}
	/**
	 * @return Returns the decoPlantSAP.
	 */
	public String getDecoPlantSAP() {
		return decoPlantSAP;
	}
	/**
	 * @param decoPlantSAP The decoPlantSAP to set.
	 */
	public void setDecoPlantSAP(String decoPlantSAP) {
		this.decoPlantSAP = decoPlantSAP;
	}
	/**
	 * @return Returns the decoPostingDateSAP.
	 */
	public String getDecoPostingDateSAP() {
		return decoPostingDateSAP;
	}
	/**
	 * @param decoPostingDateSAP The decoPostingDateSAP to set.
	 */
	public void setDecoPostingDateSAP(String decoPostingDateSAP) {
		this.decoPostingDateSAP = decoPostingDateSAP;
	}
	/**
	 * @return Returns the decoStorageSAP.
	 */
	public String getDecoStorageSAP() {
		return decoStorageSAP;
	}
	/**
	 * @param decoStorageSAP The decoStorageSAP to set.
	 */
	public void setDecoStorageSAP(String decoStorageSAP) {
		this.decoStorageSAP = decoStorageSAP;
	}
	/**
	 * @return Returns the cardFlagMatSAP.
	 */
	public String getCardFlagMatSAP() {
		return cardFlagMatSAP;
	}
	/**
	 * @param cardFlagMatSAP The cardFlagMatSAP to set.
	 */
	public void setCardFlagMatSAP(String cardFlagMatSAP) {
		this.cardFlagMatSAP = cardFlagMatSAP;
	}
	/**
	 * @return Returns the decoFlagMatSAP.
	 */
	public String getDecoFlagMatSAP() {
		return decoFlagMatSAP;
	}
	/**
	 * @param decoFlagMatSAP The decoFlagMatSAP to set.
	 */
	public void setDecoFlagMatSAP(String decoFlagMatSAP) {
		this.decoFlagMatSAP = decoFlagMatSAP;
	}
	/**
	 * @return Devuelve ddtvMarca.
	 */
	public String getDdtvMarca() {
		return ddtvMarca;
	}
	/**
	 * @param ddtvMarca El ddtvMarca a establecer.
	 */
	public void setDdtvMarca(String ddtvMarca) {
		this.ddtvMarca = ddtvMarca;
	}
	/**
	 * @return Devuelve ddtvModelo.
	 */
	public String getDdtvModelo() {
		return ddtvModelo;
	}
	/**
	 * @param ddtvModelo El ddtvModelo a establecer.
	 */
	public void setDdtvModelo(String ddtvModelo) {
		this.ddtvModelo = ddtvModelo;
	}
	/**
	 * @return Devuelve ddtvSerial.
	 */
	public String getDdtvSerial() {
		return ddtvSerial;
	}
	/**
	 * @param ddtvSerial El ddtvSerial a establecer.
	 */
	public void setDdtvSerial(String ddtvSerial) {
		this.ddtvSerial = ddtvSerial;
	}
}
