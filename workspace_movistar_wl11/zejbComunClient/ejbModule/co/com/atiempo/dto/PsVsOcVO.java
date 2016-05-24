package co.com.atiempo.dto;

public class PsVsOcVO  {

	private Long psId;
	private Long opComId;
	private String opComTipo;
	private Long opComRevId;
	private Long faPsId;
	private Long correlativo;
	private boolean ok;
	
	//Campos exclusivos para rev. conf. int. cambio prod.
	private boolean sacarFatherEmAlta;
	private Long psIdAlta;
	private Long opCoAlta;
	
	public PsVsOcVO()
	{
		sacarFatherEmAlta=false;	
	}

	/**
	 * @return
	 */
	public Long getOpComId() {
		return opComId;
	}

	/**
	 * @return
	 */
	public Long getPsId() {
		return psId;
	}

	/**
	 * @param long1
	 */
	public void setOpComId(Long long1) {
		opComId = long1;
	}

	/**
	 * @param long1
	 */
	public void setPsId(Long long1) {
		psId = long1;
	}

	/**
	 * @return
	 */
	public String getOpComTipo() {
		return opComTipo;
	}

	/**
	 * @param string
	 */
	public void setOpComTipo(String string) {
		opComTipo = string;
	}

	/**
	 * @return
	 */
	public boolean isOk() {
		return ok;
	}

	/**
	 * @param b
	 */
	public void setOk(boolean b) {
		ok = b;
	}

	/**
	 * @return
	 */
	public Long getOpComRevId() {
		return opComRevId;
	}

	/**
	 * @param long1
	 */
	public void setOpComRevId(Long long1) {
		opComRevId = long1;
	}

	/**
	 * @return
	 */
	public Long getFaPsId() {
		return faPsId;
	}

	/**
	 * @param long1
	 */
	public void setFaPsId(Long long1) {
		faPsId = long1;
	}

	
	public boolean equals(Object obj)
	{	
		if ((! (obj instanceof PsVsOcVO)) || (obj == null))
		   return false ;
        
		PsVsOcVO otro = (PsVsOcVO) obj ;
	
		if(this.psId==null || this.opComId==null || otro.getPsId()==null || otro.getOpComId()==null)
			return false;
		return (this.psId.longValue()==otro.getPsId().longValue() && this.opComId.longValue()==otro.getOpComId().longValue()) ;
	}

	/**
	 * @return
	 */
	public Long getCorrelativo() {
		return correlativo;
	}

	/**
	 * @param long1
	 */
	public void setCorrelativo(Long long1) {
		correlativo = long1;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return "psId:"+psId+" opCo:"+opComId+" opcoTipo:"+opComTipo+ " opComRevId:"+opComRevId+ " faPsId:"+faPsId+ " ok:"+ok;
	}

	/**
	 * @return
	 */
	public boolean isSacarFatherEmAlta() {
		return sacarFatherEmAlta;
	}

	/**
	 * @param b
	 */
	public void setSacarFatherEmAlta(boolean b) {
		sacarFatherEmAlta = b;
	}


	/**
	 * @return
	 */
	public Long getOpCoAlta() {
		return opCoAlta;
	}

	/**
	 * @return
	 */
	public Long getPsIdAlta() {
		return psIdAlta;
	}

	/**
	 * @param long1
	 */
	public void setOpCoAlta(Long long1) {
		opCoAlta = long1;
	}

	/**
	 * @param long1
	 */
	public void setPsIdAlta(Long long1) {
		psIdAlta = long1;
	}

}
