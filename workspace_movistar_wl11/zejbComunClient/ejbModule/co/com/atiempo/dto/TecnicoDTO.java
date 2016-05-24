package co.com.atiempo.dto;


public class TecnicoDTO implements Comparable {

	private String codTecnico;
	private String nombre;
	private String apellido;
	private Long usuaId;

	/**
	 * @return
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @return
	 */
	public String getCodTecnico() {
		return codTecnico;
	}

	/**
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return
	 */
	public Long getUsuaId() {
		return usuaId;
	}

	/**
	 * @param string
	 */
	public void setApellido(String string) {
		apellido = string;
	}

	/**
	 * @param string
	 */
	public void setCodTecnico(String string) {
		codTecnico = string;
	}

	/**
	 * @param string
	 */
	public void setNombre(String string) {
		nombre = string;
	}

	/**
	 * @param long1
	 */
	public void setUsuaId(Long long1) {
		usuaId = long1;
	}
	
	public final String getNombreApellido(String del)
	{	// AT-1774 - performance suggestion - guido - 18-Nov-2008 - Start 
		//StringBuffer buf = new StringBuffer(nombre);
		// the parameter nombre coel be null
		StringBuffer buf = new StringBuffer(50);
		buf.append(nombre);
		buf.append(del);
		buf.append(apellido);
		return buf.toString();
	}

	public final int compareTo(Object o)
	{
		TecnicoDTO otro=(TecnicoDTO) o;
		String nom1 = this.getNombreApellido(" ");
		String nom2 = otro.getNombreApellido(" ");
		if (nom1 != null && nom2 != null) {
			return nom1.compareTo(nom2);
		}
		// AT-1774 - performance suggestion - guido - 18-Nov-2008 - END 
		return 0;
	}

}
