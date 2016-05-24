package co.com.atiempo.dto;

import java.util.ArrayList;

/**
 * @author lcaldera
 *
 */
public class MasivoODSVpiDTO
{
	private Long nroPeticion;
	private Long actId;
	private Integer espeId;
	private ArrayList listaPsInvoca;
	
	public MasivoODSVpiDTO()
	{
		listaPsInvoca=new ArrayList();
	}
	/**
	 * @return
	 */
	public Long getActId() {
		return actId;
	}

	/**
	 * @return
	 */
	public Integer getEspeId() {
		return espeId;
	}

	/**
	 * @return
	 */
	public Long getNroPeticion() {
		return nroPeticion;
	}

	/**
	 * @param long1
	 */
	public void setActId(Long long1) {
		actId = long1;
	}

	/**
	 * @param integer
	 */
	public void setEspeId(Integer integer) {
		espeId = integer;
	}

	/**
	 * @param long1
	 */
	public void setNroPeticion(Long long1) {
		nroPeticion = long1;
	}

	/**
	 * @param ps
	 * @param opco
	 */
	public void addPsInvoca(Long ps, Long opco)
	{
		MasivoODSVpiChildDTO childDTO=new MasivoODSVpiChildDTO(ps,opco);
		listaPsInvoca.add(childDTO);
	}

	/**
	 * @return
	 */
	public ArrayList getListaPsInvoca() {
		return listaPsInvoca;
	}

}
