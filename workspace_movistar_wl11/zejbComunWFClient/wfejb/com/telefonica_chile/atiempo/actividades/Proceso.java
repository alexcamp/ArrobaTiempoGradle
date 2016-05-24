/*
 * Creado el Feb 3, 2005
 *
 */
package com.telefonica_chile.atiempo.actividades;

import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.javaWf.WFSessionLocal;
import com.telefonica_chile.atiempo.javaWf.WFSessionLocalHome;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author Pa-T! (Richard Andreu)
 *
 */
public abstract class Proceso implements IProceso {
	
	protected String idTemplateWf = null;
	protected String idInstancia = null;
	protected String nombreEstructuraDatos = null;
	protected Map datos = null;
	
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	public Proceso(String idTemplateWf, String idInstancia, Map datos) {
		this.idTemplateWf = idTemplateWf;
		this.idInstancia = idInstancia;
		this.datos = datos;
	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.actividades.IProceso#iniciar()
	 */
	public final void iniciar() throws TnProcesoExcepcion {
		if(this.idTemplateWf == null || this.idInstancia == null || this.datos == null) {
			final String mensaje = "No se puede iniciar un Proceso sin haber definido el id del proceso (Template), el id de la instancia (numero de peticion o servicio) y los datos del phi: idTemplate=" + this.idTemplateWf + ", idInstancia=" + this.idInstancia  + ", datos=" + this.datos;
			throw new TnProcesoExcepcion(mensaje);	
		} else {
			try {
				log.debug("iniciando proceso, idTemplateWf = " + this.idTemplateWf + ", idInstancia=" + this.idInstancia + ", datos=" + this.datos );
				//Aqui hago el inicio en el Workflow!!
				WFSessionLocalHome wfHome = (WFSessionLocalHome)HomeFactory.getHome(WFSessionLocalHome.JNDI_NAME);
				WFSessionLocal wfSession = (WFSessionLocal)wfHome.create();   
				wfSession.iniciarProceso(this);
				log.debug("proceso iniciado");
			} catch (NamingException e) {
				throw new TnProcesoExcepcion("NamingException al obtener home de WFSession, Proceso.iniciar(): " + e.getMessage());
			} catch (CreateException e) {
				throw new TnProcesoExcepcion("CreateExeption al obtener home de WFSession, Proceso.iniciar(): " + e.getMessage());
			} 		
		}
	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.actividades.IProceso#getIdProceso()
	 */
	public String getIdProceso() {
		return this.idTemplateWf;
	}
	
	public String getIdInstancia() {
		return this.idInstancia;
	}

	/**
	 * @return
	 */
	public Map getDatos() {
		return datos;
	}

	/* (non-Javadoc)
	 * @see com.telefonica_chile.atiempo.actividades.IProceso#preInicio()
	 */
	public void preInicio() throws TnProcesoExcepcion {
		//Por defecto no haccemos nada antes de iniciar el flujo...
	}

	/**
	 * @param map
	 */
	public void setDatos(Map map) {
		datos = map;
	}

	/**
	 * @return
	 */
	public String getNombreEstructuraDatos() {
		return nombreEstructuraDatos;
	}

	/**
	 * @param string
	 */
	public void setNombreEstructuraDatos(String string) {
		nombreEstructuraDatos = string;
	}

}
