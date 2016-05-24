/*
 * Created on Mar 2, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.telefonica_chile.bandeja.web.acciones;

import java.util.ArrayList;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocal;
import com.telefonica_chile.bandeja.bintegrada.session.BIntegradaSessionLocalHome;

public class BuscarLocalidad extends Accion {

	protected transient Logger log = LoggerFactory.getLogger(getClass());

	protected void ejecutar(HttpServletRequest request) throws Evento {
		
		ArrayList listaDepartamento= getLista("DEPARTAMENTO");
		ArrayList listaMunicipio=null;
		ArrayList listaLocalidad=null;
		if(request.getParameter("departamento")!=null)
		{
			String cod_dpt=request.getParameter("departamento");
			if(!cod_dpt.equals("00"))
			{
				listaMunicipio=getListaMunicipio(cod_dpt);
				listaLocalidad=new ArrayList();
			}
			else
			{
				listaMunicipio=new ArrayList();
				listaLocalidad=new ArrayList();
			}
		}
		else
		{
			listaMunicipio=new ArrayList();
			listaLocalidad=new ArrayList();
		}
		if(request.getParameter("municipio")!=null)
		{
			String cod_mun=request.getParameter("municipio");
			if(!cod_mun.equals("00000"))
				listaLocalidad=getListaLocalidad(cod_mun);
			else
				listaLocalidad=new ArrayList();
		}
		else
			listaLocalidad=new ArrayList();
	}
	
	BIntegradaSessionLocal sbLocal = null;
	
	private ArrayList getLista( String tipo )
	{
		ArrayList listado = new ArrayList();
		try {
			if ( sbLocal == null )
				getSBLocal();
		} catch (NamingException e) {
			log.error("NamingException. No se pudo Cargar Listado[" + tipo + "]");
			return listado;
		} catch (CreateException e) {
			log.error("CreateException. No se pudo Cargar Listado[" + tipo + "]");
			return listado;
		}

		try {
			if ( "APLICACION".equals( tipo ) )
				listado = sbLocal.recuperarAplicaciones();
			if("SEGMENTO".equals(tipo))
				listado=sbLocal.recuperaSegmentos();

		} catch (Exception e) {
			log.error("BandejaException. No se pudo Cargar Listado [" + tipo + "]: " + e.getMessage());
		}

		return listado;
	}

	private void getSBLocal() throws NamingException, CreateException
	{
		BIntegradaSessionLocalHome biHome =  (BIntegradaSessionLocalHome) HomeFactory.getHome(BIntegradaSessionLocalHome.JNDI_NAME);			
		sbLocal = biHome.create();
	}
	
	private ArrayList getListaLocalidad(String cod_mun)
	{
		ArrayList listado = new ArrayList();
		try {
			if ( sbLocal == null )
				getSBLocal();
			listado = sbLocal.recuperaLocalidadesPorMunicipio(cod_mun);
		} catch (NamingException e) {
			log.info("NamingException. No se pudo Cargar Listado");
			return listado;
		} catch (CreateException e) {
			log.info("CreateException. No se pudo Cargar Listado");
			return listado;
		}
		return listado;
	}
	
	private ArrayList getListaMunicipio(String cod_dpt)
	{
		ArrayList listado = new ArrayList();
		try {
			if ( sbLocal == null )
				getSBLocal();
			listado = sbLocal.recuperaMunicipiosPorDepto(cod_dpt);
		} catch (NamingException e) {
			log.info("NamingException. No se pudo Cargar Listado");
			return listado;
		} catch (CreateException e) {
			log.info("CreateException. No se pudo Cargar Listado");
			return listado;
		}
		return listado;
	}
}
