package co.com.telefonica.atiempo.ejb.eb;
/**
 * Key class for Entity Bean: Usuario_token
 */
public class Usuario_tokenKey implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: usua_login
	 */
	public java.lang.String usua_login;
	/**
	 * Creates an empty key for Entity Bean: Usuario_token
	 */
	public Usuario_tokenKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey
			instanceof co.com.telefonica.atiempo.ejb.eb.Usuario_tokenKey) {
			co.com.telefonica.atiempo.ejb.eb.Usuario_tokenKey o =
				(co.com.telefonica.atiempo.ejb.eb.Usuario_tokenKey) otherKey;
			return ((this.usua_login.equals(o.usua_login)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (usua_login.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: Usuario_token
	 */
	public Usuario_tokenKey(java.lang.String usua_login) {
		this.usua_login = usua_login;
	}
}
