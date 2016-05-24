package com.tecnonautica.mvc;

import java.util.Iterator;
import java.util.Vector;

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
public class CadenaDeFiltros {
	
	private Vector filtros;
	private Iterator iterador;
	
	
	
	public CadenaDeFiltros(Vector filtros){
		this.filtros=filtros;
		iterador=filtros.iterator();
	}
	
	public void filtrar(HttpServletRequest request, HttpServletResponse response ){

		if(iterador.hasNext()){
			Filtro filtro=(Filtro)iterador.next();
			filtro.filtrar(request,response,this);
		}
		else{
			
    		

			
		}
		
		
	}
	
	
}
