/*
 * CR 00024805 - May 15, 2009 - 1
 *  	Objecto para el guardado de los filtros para la busqueda de peticiones
 */
package co.com.atiempo.dto;

/**
 * @author 810884
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BuscadorPeticionVpiDTO {
	
	public static final int COD_PETICION = 1;
	public static final int COD_IDENTIFICADOR_CLIENTE = 2;
	public static final int COD_IDENTIFICADOR_PC_LINEA = 3;
	public static final int COD_IDENTIFICADOR_PC_TV = 4;
	public static final int COD_IDENTIFICADOR_PC_IC = 5;

	private int idBusqueda;
	
	private Long idPeticionAtis;
	
	private String rutCli;
	
	private String rutDv;
	
	private String idPc;
	
	private int typePc;
	
	private boolean limiteAlcanzado;
	
	private int limitePeticiones;

	/*
	 * CR - 00027016 - Jul 2, 2009
	 *	Se agregan atributos para manejo de consultas - German P.
	 */
	private int codBusqueda;
	
	private boolean joinBIntegrada;
	
	
	/**
	 * @param idPeticionAtis
	 * @param rutCli
	 * @param rutDv
	 * @param idPc
	 * @param typePc
	 */
	public BuscadorPeticionVpiDTO(Long idPeticionAtis, String rutCli,
			String rutDv, String idPc, int typePc) {
		super();
		this.idPeticionAtis = idPeticionAtis;
		this.rutCli = rutCli;
		this.rutDv = rutDv;
		this.idPc = idPc;
		this.typePc = typePc;
	}	
	
	/**
	 * @return Returns the idBusqueda.
	 */
	public int getIdBusqueda() {
		return idBusqueda;
	}
	/**
	 * @param idBusqueda The idBusqueda to set.
	 */
	public void setIdBusqueda(int idBusqueda) {
		this.idBusqueda = idBusqueda;
	}
	/**
	 * @return Returns the idPc.
	 */
	public String getIdPc() {
		return idPc;
	}
	/**
	 * @param idPc The idPc to set.
	 */
	public void setIdPc(String idPc) {
		this.idPc = idPc;
	}
	/**
	 * @return Returns the idPeticionAtis.
	 */
	public Long getIdPeticionAtis() {
		return idPeticionAtis;
	}
	/**
	 * @param idPeticionAtis The idPeticionAtis to set.
	 */
	public void setIdPeticionAtis(Long idPeticionAtis) {
		this.idPeticionAtis = idPeticionAtis;
	}
	/**
	 * @return Returns the limiteAlcanzado.
	 */
	public boolean isLimiteAlcanzado() {
		return limiteAlcanzado;
	}
	/**
	 * @param limiteAlcanzado The limiteAlcanzado to set.
	 */
	public void setLimiteAlcanzado(boolean limiteAlcanzado) {
		this.limiteAlcanzado = limiteAlcanzado;
	}
	/**
	 * @return Returns the rutCli.
	 */
	public String getRutCli() {
		return rutCli;
	}
	/**
	 * @param rutCli The rutCli to set.
	 */
	public void setRutCli(String rutCli) {
		this.rutCli = rutCli;
	}
	/**
	 * @return Returns the rutDv.
	 */
	public String getRutDv() {
		return rutDv;
	}
	/**
	 * @param rutDv The rutDv to set.
	 */
	public void setRutDv(String rutDv) {
		this.rutDv = rutDv;
	}
	/**
	 * @return Returns the typePc.
	 */
	public int getTypePc() {
		return typePc;
	}
	/**
	 * @param typePc The typePc to set.
	 */
	public void setTypePc(int typePc) {
		this.typePc = typePc;
	}
	/**
	 * @return Returns the limitePeticiones.
	 */
	public int getLimitePeticiones() {
		return limitePeticiones;
	}
	/**
	 * @param limitePeticiones The limitePeticiones to set.
	 */
	public void setLimitePeticiones(int limitePeticiones) {
		this.limitePeticiones = limitePeticiones;
	}
	/**
	 * @return Returns the codBusqueda.
	 */
	public int getCodBusqueda() {
		return codBusqueda;
	}
	/**
	 * @param codBusqueda The codBusqueda to set.
	 */
	public void setCodBusqueda(int codBusqueda) {
		this.codBusqueda = codBusqueda;
	}
	
	/**
	 * @return Returns the joinBIntegrada.
	 */
	public boolean isJoinBIntegrada() {
		return joinBIntegrada;
	}
	/**
	 * @param joinBIntegrada The joinBIntegrada to set.
	 */
	public void setJoinBIntegrada(boolean joinBIntegrada) {
		this.joinBIntegrada = joinBIntegrada;
	}
}
