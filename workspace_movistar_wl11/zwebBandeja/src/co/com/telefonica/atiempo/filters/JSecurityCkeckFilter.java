package co.com.telefonica.atiempo.filters;

import java.io.IOException;
import java.security.Principal;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import co.com.atiempo.sesion.SessionManager;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author cacano
 * 
 * Filtra a modo posterior cuando se ejecuta la seguridad basada en formulario
 * para colocar el usuario de sesi�n en la base de datos.
 */
public class JSecurityCkeckFilter implements Filter {
	private SessionManager session = null;

	private FilterConfig filterConfig;

	private transient Logger log = LoggerFactory.getLogger(getClass());

	private DataSource getDataSource() throws NamingException {
		DataSource ds = null;
		System.out.println("JSecurityCkeckFilter.getDataSource() ::: obteniendo el datasource");
		return (DataSource) new InitialContext().lookup("jdbc/vpistbba");
	}

	private SessionManager getSessionManager() throws NamingException {
		if (session == null) {
			this.session = new SessionManager(getDataSource());
		}
		return this.session;
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("JSecurityCkeckFilter.init() ::: "+arg0);
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
		
		System.out.println("JSecurityCkeckFilter.doFilter() ::: chain = "+chain);
		
		try {
			
			
			chain.doFilter(request, response);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		Principal principal = httpRequest.getUserPrincipal();
		
		
		
		if (principal != null) {
			try {
				log.info("Creando sesi�n para login " + principal.getName());
				getSessionManager().addSession(principal.getName());
				log.info("Sesi�n con login "+principal.getName()+" ha sido creada");
			} catch (Exception e) {
				e.printStackTrace();
				log.error("Error, se redireccionar� al homepage. "
						+ e.getMessage());
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				httpResponse.sendRedirect("./login.html");
			}
		}
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