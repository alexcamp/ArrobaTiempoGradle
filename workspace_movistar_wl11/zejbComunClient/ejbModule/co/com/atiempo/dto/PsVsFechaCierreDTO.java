package co.com.atiempo.dto;

public class PsVsFechaCierreDTO
{
	private Long idPs;
	private Long correlativoPs;
	public String fecha;
	
	/**
	 * @return
	 */
	public Long getCorrelativoPs() {
		return correlativoPs;
	}

	/**
	 * @return
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @return
	 */
	public Long getIdPs() {
		return idPs;
	}

	/**
	 * @param long1
	 */
	public void setCorrelativoPs(Long long1) {
		correlativoPs = long1;
	}

	/**
	 * @param string
	 */
	public void setFecha(String string) {
		fecha = string;
	}

	/**
	 * @param long1
	 */
	public void setIdPs(Long long1) {
		idPs = long1;
	}

}
