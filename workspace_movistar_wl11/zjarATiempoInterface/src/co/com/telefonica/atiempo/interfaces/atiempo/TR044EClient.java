/*
 * Created on Jul 8, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.interfaces.atiempo;

import java.io.Serializable;

import co.com.telefonica.atiempo.util.EqualityUtilities;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TR044EClient implements Serializable {
	private String apellido;
	private String ciudad;
	private String codClienteAtis;
	private String codCuentaAtis;
	private String departamento;
	private String direccion;
	private String documento;
	private String nombre;
	private String telefono;
	private String tipoDocumento;
	private String email;
	
	
	public int hashCode() {
		return super.hashCode();
	}
	
	public boolean equals(Object arg0) {
		if (arg0 instanceof TR044EClient) {
			TR044EClient other = (TR044EClient) arg0;
			return super.equals(arg0)
				&& EqualityUtilities.equals(apellido, other.apellido)
				&& EqualityUtilities.equals(ciudad, other.ciudad)
				&& EqualityUtilities.equals(codClienteAtis, other.codClienteAtis)
				&& EqualityUtilities.equals(codCuentaAtis, other.codCuentaAtis)
				&& EqualityUtilities.equals(departamento, other.departamento)
				&& EqualityUtilities.equals(direccion, other.direccion)
				&& EqualityUtilities.equals(documento, other.documento)
				&& EqualityUtilities.equals(nombre, other.nombre)
				&& EqualityUtilities.equals(telefono, other.telefono)
				&& EqualityUtilities.equals(tipoDocumento, other.tipoDocumento)
				&& EqualityUtilities.equals(email, other.email);
			}
		return false;
	}

	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getCodClienteAtis() {
		return codClienteAtis;
	}
	public void setCodClienteAtis(String codClienteAtis) {
		this.codClienteAtis = codClienteAtis;
	}
	public String getCodCuentaAtis() {
		return codCuentaAtis;
	}
	public void setCodCuentaAtis(String codCuentaAtis) {
		this.codCuentaAtis = codCuentaAtis;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
