package co.com.telefonica.atiempo.interfaces.atiempo;

/**
 * @author cacano
 * 
 * Representa la tr-612-s para Configuración Mediación Móvil
 */
public class TR612S extends ResponseHeaderAgendaSC {
	private String response;

	/**
	 * @param response
	 *            El response a establecer.
	 */
	public void setResponse(String response) {
		this.response = response;
	}

	/**
	 * @return Devuelve response.
	 */
	public String getResponse() {
		return response;
	}
}