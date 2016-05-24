/*
 * Created on Mar 20, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.atiempo.dto;

import java.util.Comparator;

/**
 * @author 807793
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ElementoDTO implements Comparator{
    
	private int idElemento = 0;
	private String desElemento = "";
	private String desPs = "";
	private String peti_numero = "";
	private String marca = "";
	private String modelo = "";
	private String serial = "";
	private Long ps = new Long(0);
	private String tipo_equipo = "";
	private String tipo_inventario = "";
	private short accion;
	private Long tipo_elemento;
	/*fmendez - rq6895 - interfaz SAP*/
	private String numCelular = "";
	private String materialSAP = "";
	private String centroSAP = "";
	private String almacenSAP = "";
	private String numDocComprasSAP = "";
	private int numPosDocComprasSAP = 0;
	/*Fin fmendez - rq6895 - interfaz SAP*/
	
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
     * @return Returns the accion.
     */
    public short getAccion() {
        return accion;
    }
    /**
     * @param accion The accion to set.
     */
    public void setAccion(short accion) {
        this.accion = accion;
    }
    /**
     * @return Returns the desElemento.
     */
    public String getDesElemento() {
        return desElemento;
    }
    /**
     * @param desElemento The desElemento to set.
     */
    public void setDesElemento(String desElemento) {
        this.desElemento = desElemento;
    }
    /**
     * @return Returns the idElemento.
     */

    /**
     * @return Returns the peti_numero.
     */
    public String getPeti_numero() {
        return peti_numero;
    }
    /**
     * @param peti_numero The peti_numero to set.
     */
    public void setPeti_numero(String peti_numero) {
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
     * @return Returns the marca.
     */
    public String getMarca() {
        return marca;
    }
    /**
     * @param marca The marca to set.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
    /**
     * @return Returns the modelo.
     */
    public String getModelo() {
        return modelo;
    }
    /**
     * @param modelo The modelo to set.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    /**
     * @return Returns the ps.
     */
    public Long getPs() {
        return ps;
    }
    /**
     * @param ps The ps to set.
     */
    public void setPs(Long ps) {
        this.ps = ps;
    }
    
    /**
     * @param idElemento The idElemento to set.
     */
    public void setIdElemento(int idElemento) {
        this.idElemento = idElemento;
    }
    /**
     * @return Returns the idElemento.
     */
    public int getIdElemento() {
        return idElemento;
    }
    

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
   /* public int compare(Object o1, Object o2) {
       int id1 = ((ElementoDTO)o1).getIdElemento();
       int id2 = ((ElementoDTO)o2).getIdElemento();
       if(id1 <= id2)
           return 0;
       else
           return 1;
    }*/
    public int compare(Object o1, Object o2) {
        int id1=0;
        int id2 =0;
        if(((ElementoDTO)o1).getPs().intValue() != 0){
        id1 = ((ElementoDTO)o1).getPs().intValue();        
        id2 = ((ElementoDTO)o2).getPs().intValue();
        }else{
            id1 = ((ElementoDTO)o1).getIdElemento();        
            id2 = ((ElementoDTO)o2).getIdElemento();            
        }
        if(id1 <= id2)
            return 0;
        else
            return 1;
     }
    
    /**
     * @param ps The ps to set.
     */
 
    /**
     * @return Returns the desPs.
     */
    public String getDesPs() {
        return desPs;
    }
    /**
     * @param desPs The desPs to set.
     */
    public void setDesPs(String desPs) {
        this.desPs = desPs;
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
