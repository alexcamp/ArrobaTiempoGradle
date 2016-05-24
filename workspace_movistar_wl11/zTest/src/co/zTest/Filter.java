package co.zTest;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * @author cacano
 * 
 * Filtra a modo posterior cuando se ejecuta la seguridad basada en formulario
 * para colocar el usuario de sesi�n en la base de datos.
 */
public class Filter implements javax.servlet.Filter {

	private FilterConfig filterConfig;





	/*
	 * (sin Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("Filter.init() ::: "+arg0);
		this.filterConfig = arg0;
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		this.filterConfig = null;
	}

}