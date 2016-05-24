package com.tecnonautica.mvc;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jguridi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ManejadorDeFiltros extends HttpServlet{
	
	
	private static final String RUTA_FILTROS_XML = "/WEB-INF/com_tecnonautica_mvc.xml";
	
	private Vector filtros=new Vector();
		
	public void init(){
		
		URL recurso=null;
		//***********************************************************************
		//Cargar las definiciones de los filtros desde el archivo de configuracion
		//***********************************************************************
		try{		
			recurso = getServletContext().getResource(RUTA_FILTROS_XML);
			
		}
		catch(java.net.MalformedURLException ex){
			System.err.println("ManejadorDeFiltros: URL malformada exception: " + ex);
		}
		
		if(recurso!=null){
			Vector nombresDeFiltros = FiltrosDAO.CargarFiltros(recurso);
			Iterator it=nombresDeFiltros.iterator();
			String nombre;
			Filtro filtro;
			while (it.hasNext()){
				nombre=(String)it.next();
				System.out.println("Filtro: "+nombre);
				try {
                	filtro = (Filtro)getClass().getClassLoader().loadClass(nombre).newInstance();
                	filtros.add(filtro);
            	} catch (Exception ex) {
                	System.err.println("ManejadorDeFiltros: error cargando filtro: "+nombre+" - "+ ex);
            	}
			}
			
		}
		else{
			System.err.println("ManejadorDeFiltros: no se encontro "+RUTA_FILTROS_XML);
		}
	}
	
	
	
	/**
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest, HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		procesar(request,response);
	}
	
	/**
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		procesar(request,response);
	}
	
	
	protected void procesar(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		CadenaDeFiltros cadena=new CadenaDeFiltros(filtros);
		
		cadena.filtrar(request,response);
		
		String uri=request.getRequestURI();
		
		URL u = getServletContext().getResource(uri);
		
		if (uri.endsWith(".acc")){
			RequestDispatcher dispatcher = getServletContext().getNamedDispatcher("ControladorDeEntrada");
    	    dispatcher.forward(request, response);
		}
		
		
		
		//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(uri);
    	    	
    	//dispatcher.forward(request, response);
				
	}




}
