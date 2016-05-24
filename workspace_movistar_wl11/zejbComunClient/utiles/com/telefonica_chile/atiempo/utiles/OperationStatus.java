/*
 * Created on Jul 15, 2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.atiempo.utiles;

/**
 * @author 807793
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class OperationStatus {
	private short status = 0;
    private String mensaje = "";
	public OperationStatus(short st){
		this.status = st;
	}    
    
	public boolean isError() {
		return (status == STATUS_ERROR_PGI || status == STATUS_ERROR_PGC);
	}
	public boolean isOk() {
		return (status == STATUS_OP_EXITOSA);
	}
	
	public boolean isErrorPGI() {
		return (status == STATUS_ERROR_PGI);
	}
	
	public boolean isEspera() {
		return (status == STATUS_EN_ESPERA);
	}

	public String getIdPlataformaGestion()throws RuntimeException{
		if (status == STATUS_ERROR_PGI) {
			return "IDPGI";
		} else if (status == STATUS_ERROR_PGC) {
			return "IDPGC";
		} else {
			throw new RuntimeException("Status '"+status+"' no tiene una plataforma de gestion asociada");
		}
	}
	
    public void setMensaje(String m){
    	this.mensaje = m;
    }
    public String getMensaje(){
    	return this.mensaje;
    }
    
	public static short STATUS_ERROR_PGI = 1;
	public static short STATUS_ERROR_PGC = 2;
	public static short STATUS_EN_ESPERA = 3;
	public static short STATUS_OP_EXITOSA = 4;
}