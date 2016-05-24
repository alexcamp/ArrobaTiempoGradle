/*
 * Created on 09-03-2007
 */
package co.com.atiempo.dto;

/**
 * @author Respinoza
 */
public class DslamConec9ApscDTO {

	/**
	 * 
	 */
	public DslamConec9ApscDTO() {
		super();
	}
	
	private Long idDslam;
	private Long idConector;
	private String dslam;
	
	

	/**
	 * @return
	 */
	public String getDslam() {
		return dslam;
	}

	/**
	 * @return
	 */
	public Long getIdConector() {
		return idConector;
	}

	/**
	 * @return
	 */
	public Long getIdDslam() {
		return idDslam;
	}

	/**
	 * @param string
	 */
	public void setDslam(String string) {
		dslam = string;
	}

	/**
	 * @param long1
	 */
	public void setIdConector(Long long1) {
		idConector = long1;
	}

	/**
	 * @param long1
	 */
	public void setIdDslam(Long long1) {
		idDslam = long1;
	}

}
