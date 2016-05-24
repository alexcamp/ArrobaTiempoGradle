package co.com.telefonica.atiempo.soltec.dto;


/**
 * @author LCA
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InformacionEmpresaDTO {

	private Long emprId;
	private String emprNombre;
	private String emprDescripcion;
	
	public InformacionEmpresaDTO()
	{
		emprId=new Long(0);
		emprNombre="";
		emprDescripcion="";
	}
	/**
	 * @return
	 */
	public String getEmprDescripcion() {
		return emprDescripcion;
	}

	/**
	 * @return
	 */
	public Long getEmprId() {
		return emprId;
	}

	/**
	 * @return
	 */
	public String getEmprNombre() {
		return emprNombre;
	}

	/**
	 * @param string
	 */
	public void setEmprDescripcion(String string) {
		emprDescripcion = string;
	}

	/**
	 * @param long1
	 */
	public void setEmprId(Long long1) {
		emprId = long1;
	}

	/**
	 * @param string
	 */
	public void setEmprNombre(String string) {
		emprNombre = string;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return emprId+" "+emprNombre+" "+emprDescripcion;
	}

}
