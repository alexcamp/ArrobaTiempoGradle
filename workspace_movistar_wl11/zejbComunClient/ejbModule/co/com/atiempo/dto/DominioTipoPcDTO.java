/*
 * CR - 00027016 - Jul 1, 2009
 *	Clase dominio para combo de identificacion PC en buscadores - German P.
 */
package co.com.atiempo.dto;

/**
 * @author 810884
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DominioTipoPcDTO {

	private String value;
	private String label;
	
	/**
	 * @param value
	 * @param label
	 */
	public DominioTipoPcDTO(String value, String label) {
		super();
		this.value = value;
		this.label = label;
	}
	/**
	 * @return Returns the label.
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label The label to set.
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
