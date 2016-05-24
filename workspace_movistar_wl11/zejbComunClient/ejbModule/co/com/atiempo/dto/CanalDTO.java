/*
 * Created on Jan 22, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.atiempo.dto;

/**
 * @author 804226
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CanalDTO implements Comparable {

	/**
	 * 
	 */
	public CanalDTO(){
		super();
	}
	
	private Long codCanal;
	private String DescCanal;
	

	public Long getCodCanal() {
		return codCanal;
	}
	public void setCodCanal(Long codCanal) {
		this.codCanal = codCanal;
	}
	public String getDescCanal() {
		return DescCanal;
	}
	public void setDescCanal(String descCanal) {
		DescCanal = descCanal;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		CanalDTO otro=(CanalDTO) o;
		if(this.getDescCanal()!=null && otro.getDescCanal()!=null)
			return this.getDescCanal().compareTo(otro.getDescCanal());
		return 0;
	}

}
