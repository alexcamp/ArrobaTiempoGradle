package co.com.telefonica.atiempo.filters;

import java.io.IOException;

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
 * Filtro que permite la administraci�n de sesiones de las aplicaciones de
 * atiempo.
 */
public class SessionManagerFilter implements Filter {

	private SessionManager session = null;

	private FilterConfig filterConfig;

	private transient Logger log = LoggerFactory.getLogger(getClass());

	private DataSource getDataSource() throws NamingException {
		DataSource ds = null;
		System.out.println("SessionManagerFilter.getDataSource() ::: ");
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
		System.out.println("SessionManagerFilter.init() ::: "+arg0);
		this.filterConfig = arg0;
	}

	/*
	 * (sin Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		System.out.println("SessionManagerFilter.doFilter() :::");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String login = httpRequest.getUserPrincipal() == null ? "DUMMY"
				: httpRequest.getUserPrincipal().getName();
		int maxTimeSessionMinutes = Integer.parseInt(this.filterConfig
				.getServletContext().getInitParameter("MaxTimeSessionMinutes"));
		SessionManager sesion;
		try {
			sesion = getSessionManager();
			if (sesion.isEnabledApplication()) {
				if (!sesion.isValidSession(login, maxTimeSessionMinutes)) {
					log.info("Sesi�n de usuario inv�lida, redireccionando a login");
					httpResponse.sendRedirect("./login.html");
				} else {
					getSessionManager().refreshSession(login);
					filterChain.doFilter(request, response);
				}
			} else {
				log.info("Vulnerabilidades inactivo.");
				filterChain.doFilter(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error, se redireccionar� al homepage. "
							+ e.getMessage());
			httpResponse.sendRedirect("./login.html");
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