package com.tecnonautica.seguridad;

import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @author jguridi
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class TNSegTagControlar extends BodyTagSupport {
	
	private String idRecurso;
	/**
	 * @see javax.servlet.jsp.tagext.BodyTagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		ControladorDeSeguridad controladorDeSeguridad = (ControladorDeSeguridad)pageContext.getServletContext().getAttribute("controladorDeSeguridad");
		if (controladorDeSeguridad == null){
			controladorDeSeguridad=cargarControladorDeSeguridad();
		}
		
		HttpSession sesion=pageContext.getSession();
		ServletRequest request = pageContext.getRequest();

		
		if (controladorDeSeguridad.autoriza(idRecurso,request)){
			return (EVAL_BODY_INCLUDE) ;
		}
		else{
			return (SKIP_BODY);
		}
	}
	
	public void setId(String id){
		this.idRecurso=id;
	}

	private ControladorDeSeguridad cargarControladorDeSeguridad(){
		ControladorDeSeguridad controlador;
		controlador = new ControladorDeSeguridad();		
		
		URL recursos;
		try {
			recursos = pageContext.getServletContext().getResource("/WEB-INF/tn-seguridad.xml");
			controlador.setRecursos(MapasDeRecursoDAO.cargarMapaDeRecursos(recursos));
			pageContext.getServletContext().setAttribute("controladorDeSeguridad",controlador);
			return controlador;
		} 
		catch (MalformedURLException e) {
			System.err.println("No se pueden cargar los recursos de seguridad  "+e);	
			return null;
		}
		
		
					
		
	}
}
