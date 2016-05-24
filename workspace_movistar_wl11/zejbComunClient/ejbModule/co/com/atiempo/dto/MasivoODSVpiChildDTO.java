package co.com.atiempo.dto;

/**
 * @author lcaldera
 *
 */
public class MasivoODSVpiChildDTO
{
	private Long ps;
	private Long opco;

	/**
	 * @param ps
	 * @param opco
	 */
	
	public MasivoODSVpiChildDTO(Long ps, Long opco)
	{
		this.ps=ps;
		this.opco=opco;
	}

	/**
	 * @return
	 */
	public Long getOpco() {
		return opco;
	}

	/**
	 * @return
	 */
	public Long getPs() {
		return ps;
	}

	/**
	 * @param long1
	 */
	public void setOpco(Long long1) {
		opco = long1;
	}

	/**
	 * @param long1
	 */
	public void setPs(Long long1) {
		ps = long1;
	}

}
