package co.com.atiempo.dto;

public class EmpresaDTO implements Comparable
{
	private Long emprId;
	private String empresaNombre;
	private String empresaDescripcion;
	/**
	 * @return
	 */
	public String getEmpresaDescripcion()
	{
		return empresaDescripcion;
	}

	/**
	 * @return
	 */
	public String getEmpresaNombre()
	{
		return empresaNombre;
	}

	/**
	 * @return
	 */
	public Long getEmprId() {
		return emprId;
	}

	/**
	 * @param string
	 */
	public void setEmpresaDescripcion(String string)
	{
		empresaDescripcion = string;
	}

	/**
	 * @param string
	 */
	public void setEmpresaNombre(String string) {
		empresaNombre = string;
	}

	/**
	 * @param long1
	 */
	public void setEmprId(Long long1) {
		emprId = long1;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o)
	{
		EmpresaDTO otro=(EmpresaDTO) o;
		if(this.getEmpresaNombre()!=null && otro.getEmpresaNombre()!=null)
			return this.getEmpresaNombre().compareTo(otro.getEmpresaNombre());	
		return 0;
	}

}
