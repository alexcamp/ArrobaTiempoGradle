/*
 * Created on Mar 23, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.atiempo.dto;

import com.telefonica_chile.comun.ComunInterfaces;

/**
 * @author 807793
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Elemento_PeticionVpiDTO {
	private Long peti_numero;
	private String serial;
	private Long telefono;
	private short accion;
	private String descAccion;
	private String tipo_inventario;
	private Long tipo_elemento;
	private String tipo_equipo;
	private Integer ps_id;
	/*fmendez - rq6895 - interfaz SAP*/
	private String numCelular;
	private String materialSAP;
	private String centroSAP;
	private String almacenSAP;
	private String numDocComprasSAP;
	private int numPosDocComprasSAP;
	/*Fin fmendez - rq6895 - interfaz SAP*/
    /**
     * @return Returns the descAccion.
     */
    public String getDescAccion() {
        return descAccion;
    }
    /**
     * @param descAccion The descAccion to set.
     */
    public void setDescAccion(String descAccion) {
        this.descAccion = descAccion;
    }
    /**
     * @return Returns the peti_numero.
     */
    public Long getPeti_numero() {
        return peti_numero;
    }
    /**
     * @param peti_numero The peti_numero to set.
     */
    public void setPeti_numero(Long peti_numero) {
        this.peti_numero = peti_numero;
    }
    /**
     * @return Returns the serial.
     */
    public String getSerial() {
        return serial;
    }
    /**
     * @param serial The serial to set.
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }
    /**
     * @return Returns the telefono.
     */
    public Long getTelefono() {
        return telefono;
    }
    /**
     * @param telefono The telefono to set.
     */
    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
    /**
     * @return Returns the accion.
     */
    public short getAccion() {
        return accion;
    }

	
	public void setAccion(short i)
	{
		accion = i;
		switch(accion)
		{
			case ComunInterfaces.accionModemNoAction:
				setDescAccion("No Accion");
				break;
			case ComunInterfaces.accionModemOcupar:
				setDescAccion("Ocupar Modem");
				break;
			case ComunInterfaces.accionModemLiberar:
				setDescAccion("Liberar Modem");
				break;
			case ComunInterfaces.accionModemNoRecuperado:
				setDescAccion("Modem No Recuperado");
				break;
			case ComunInterfaces.accionModemCambioNoRec:
				setDescAccion("Cambio de Modem No Recuperado");
				break;
			case ComunInterfaces.accionModemCambioAveriado:
				setDescAccion("Cambio de Modem Dañado");
				break;
		}
	}

    /**
     * @return Returns the tipo_elemento.
     */
    public Long getTipo_elemento() {
        return tipo_elemento;
    }
    /**
     * @param tipo_elemento The tipo_elemento to set.
     */
    public void setTipo_elemento(Long tipo_elemento) {
        this.tipo_elemento = tipo_elemento;
    }
    /**
     * @return Returns the tipo_equipo.
     */
    public String getTipo_equipo() {
        return tipo_equipo;
    }
    /**
     * @param tipo_equipo The tipo_equipo to set.
     */
    public void setTipo_equipo(String tipo_equipo) {
        this.tipo_equipo = tipo_equipo;
    }
    /**
     * @return Returns the tipo_inventario.
     */
    public String getTipo_inventario() {
        return tipo_inventario;
    }
    /**
     * @param tipo_inventario The tipo_inventario to set.
     */
    public void setTipo_inventario(String tipo_inventario) {
        this.tipo_inventario = tipo_inventario;
    }
    
	/**
	 * @return Returns the ps_id.
	 */
	public Integer getPs_id() {
		return ps_id;
	}
	/**
	 * @param ps_id The ps_id to set.
	 */
	public void setPs_id(Integer ps_id) {
		this.ps_id = ps_id;
	}
	
	/**
	 * @return Returns the almacenSAP.
	 */
	public String getAlmacenSAP() {
		return almacenSAP;
	}
	/**
	 * @param almacenSAP The almacenSAP to set.
	 */
	public void setAlmacenSAP(String almacenSAP) {
		this.almacenSAP = almacenSAP;
	}
	/**
	 * @return Returns the centroSAP.
	 */
	public String getCentroSAP() {
		return centroSAP;
	}
	/**
	 * @param centroSAP The centroSAP to set.
	 */
	public void setCentroSAP(String centroSAP) {
		this.centroSAP = centroSAP;
	}
	/**
	 * @return Returns the materialSAP.
	 */
	public String getMaterialSAP() {
		return materialSAP;
	}
	/**
	 * @param materialSAP The materialSAP to set.
	 */
	public void setMaterialSAP(String materialSAP) {
		this.materialSAP = materialSAP;
	}
	/**
	 * @return Returns the numCelular.
	 */
	public String getNumCelular() {
		return numCelular;
	}
	/**
	 * @param numCelular The numCelular to set.
	 */
	public void setNumCelular(String numCelular) {
		this.numCelular = numCelular;
	}
	/**
	 * @return Returns the numDocComprasSAP.
	 */
	public String getNumDocComprasSAP() {
		return numDocComprasSAP;
	}
	/**
	 * @param numDocComprasSAP The numDocComprasSAP to set.
	 */
	public void setNumDocComprasSAP(String numDocComprasSAP) {
		this.numDocComprasSAP = numDocComprasSAP;
	}
	/**
	 * @return Returns the numPosDocComprasSAP.
	 */
	public int getNumPosDocComprasSAP() {
		return numPosDocComprasSAP;
	}
	/**
	 * @param numPosDocComprasSAP The numPosDocComprasSAP to set.
	 */
	public void setNumPosDocComprasSAP(int numPosDocComprasSAP) {
		this.numPosDocComprasSAP = numPosDocComprasSAP;
	}
}
