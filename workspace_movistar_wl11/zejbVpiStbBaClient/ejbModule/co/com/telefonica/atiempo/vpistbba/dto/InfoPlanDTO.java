package co.com.telefonica.atiempo.vpistbba.dto;

public class InfoPlanDTO {

	private String planTV;
	private String planBA;
	
	public InfoPlanDTO()
	{
		planBA="";
		planTV="";
	}

	/**
	 * @return
	 */
	public String getPlanBA() {
		return planBA;
	}

	/**
	 * @return
	 */
	public String getPlanTV() {
		return planTV;
	}

	/**
	 * @param string
	 */
	public void setPlanBA(String string) {
		planBA = string;
	}

	/**
	 * @param string
	 */
	public void setPlanTV(String string) {
		planTV = string;
	}

}
