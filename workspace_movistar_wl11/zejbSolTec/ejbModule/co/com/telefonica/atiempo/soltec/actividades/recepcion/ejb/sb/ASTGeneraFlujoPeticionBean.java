package co.com.telefonica.atiempo.soltec.actividades.recepcion.ejb.sb;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.soltec.director.algoritmo.CalculaFlujo;
import co.com.telefonica.atiempo.soltec.director.algoritmo.CalculaFlujoDatos;
import co.com.telefonica.atiempo.soltec.director.algoritmo.Flujo;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.soltec.estructura_flujo.DATOS_ATSTSTBBA;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.director.algoritmo.DirectorException;
import com.telefonica_chile.director.dto.ProductoServicio;
import com.telefonica_chile.vpistbba.servicios.dto.FlujoDto;

/**
 * Bean implementation class for Enterprise Bean: ASTGeneraFlujoPeticion
 */
public class ASTGeneraFlujoPeticionBean
	extends co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB {

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#incializaActividadST()
	 */
	protected void incializaActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
//		Esta actividad Termina de Inmediato al finalizar su Logica de inicio
		act.setRealizarTerminoInmediato(true);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onInicioActividadST()
	 */
	protected void onInicioActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("INICIO AUTOMATICO,  ASTGeneraFlujoPeticionBean");		
//		AgendamientoLocalHome agHome = null;
//		AgendamientoLocal agLocal = null;
		
		try {
			
			this.calcularNuevoFlujo(act.getNumeroPeticion());
			
			act.setDato(DATOS_ATSTSTBBA.RECEPCION.VERIFICACION_FLUJO.vefl_ok,"S"); 
			
//			agHome = (AgendamientoLocalHome)HomeFactory.getHome(AgendamientoLocalHome.JNDI_NAME);
//			agLocal = agHome.create();
//			boolean debeAgendar = agLocal.estaAgendada( this.getNumeroPeticion() );
//			if ( !debeAgendar )
//				this.setDato("RECEP_ES_OK_AGENDA","S");
//			else
//				this.setDato("RECEP_ES_OK_AGENDA","N");

		} catch (Exception e) {
			act.setDato(DATOS_ATSTSTBBA.RECEPCION.VERIFICACION_FLUJO.vefl_ok,"N");
			log.error("DirectorException No se Pudo CalCular Flujo. [" + act.getNumeroPeticion() + "]", e);
		}

		log.debug("FIN AUTOMATICO, ASTGeneraFlujoPeticionBean");		
	
	}
	private FlujoDto calcularNuevoFlujo(Long peticion) throws DirectorException {
		HashMap listaFlujos = new HashMap();
		FlujoDto flujoUnido = new FlujoDto();
		CalculaFlujoDatos cfd = new CalculaFlujoDatos();

		try {
			//Recoger datos de peticion
			cfd.setPeticion(peticion);
			Peticion_stLocalHome peticionLocalHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_stLocal peticionEntity = peticionLocalHome.findByPrimaryKey(new Peticion_stKey(peticion));

			Collection pss = peticionEntity.getProducto_servicio_peticion();
			for (Iterator iter = pss.iterator(); iter.hasNext();) {
				Producto_servicio_peticionLocal ppsocEntity = (Producto_servicio_peticionLocal) iter.next();
				
				//TODO No se toma ProductoServicio con valor Realizado 1, porque refleja que ya esta instalado
				
				if (ppsocEntity.getPspe_realizado().shortValue() != 1)
				{
					ProductoServicio ps = new ProductoServicio();
					ps.setIdProductoServicio(ppsocEntity.getIdProductoServicio());
					ps.setIdOperacionComercial(ppsocEntity.getIdOperacionComercial());
					cfd.setProducto(ps);
				}
			}

			// ARMADO DE LOS DATOS DEL FILTRO
			HashMap contextoFiltros = new HashMap();

//			try {
				//FILTRO AGENCIA
//				cfd.setMutacion(peticionEntity.getRequiereMutacion().toString());
				//Recuperar de la base de datos si la agencia tiene o
				//no actividad MDF
				//BandejaSessionHome home = (BandejaSessionHome) HomeFactory.getHome(BandejaSessionHome.JNDI_NAME, HomeFactory.REMOTO_BANDEJA);
				//BandejaSession remote = home.create();
				//AgenciaDTO agencia = remote.recuperaAgencia(new Long(peticionEntity.getIdAgencia().intValue()));
				//if (agencia != null) {
				//	cfd.setPasaPorMDF(agencia.isPasaPorMDF());
				//}
				
//				AgenciaLocalHome aHome = (AgenciaLocalHome) HomeFactory.getHome(AgenciaLocalHome.JNDI_NAME);
//				AgenciaLocal aLocal = aHome.findByPrimaryKey( peticionEntity.getIdAgencia() );
//				cfd.setPasaPorMDF( "S".equals(aLocal.getPasaPorMdf()) );
			cfd.setPasaPorMDF( false );
			flujoUnido = this.calcularNuevoFlujo(cfd);
//			} catch (NamingException e1) {
//				log.fatal("No encontro el home de la Tabla Peticion", e1);
//				throw new DirectorException("No encontro el home de la Tabla Peticion", e1);
//			} catch (FinderException e1) {
//				cfd.setPasaPorMDF( true );
//			} catch (CreateException e1) {
//				logger.error("No pudo crear una instancia de la Clase Agencia de la bandeja, para la agencia " + peticionEntity.getIdAgencia(), e1);
//				throw new DirectorException("No pudo crear una instancia de la Clase Agencia de la bandeja, para la agencia " + peticionEntity.getIdAgencia(), e1);
//			} catch (RemoteException e) {
//				logger.error("No pudo crear una instancia de la Clase Agencia de la bandeja, para la agencia " + peticionEntity.getIdAgencia(), e);
//				throw new DirectorException("No pudo crear una instancia de la Clase Agencia de la bandeja, para la agencia " + peticionEntity.getIdAgencia(), e);
//			}

		} catch (NamingException e) {
			log.fatal("No encontro el home de la Tabla Peticion", e);
			throw new DirectorException("No encontro el home de la Tabla Peticion", e);
		} catch (FinderException e) {
			log.error("FinderException", e);
			throw new DirectorException("No encontro la Peticion " + peticion + "en la base.", e);

		}

		return flujoUnido;
	}
	private FlujoDto calcularNuevoFlujo(CalculaFlujoDatos cfd) throws DirectorException {
	//TODO: REVISAR LA GENERACION del FLUJO y los FILTROS: Limpiar el Código
		HashMap listaFlujos = new HashMap();
		Flujo flujoUnido = new Flujo();

		CalculaFlujo calculaFlujo = new CalculaFlujo();
		calculaFlujo.setPeticion(cfd.getPeticion());
		HashSet psOpCom = cfd.getProductos();

		for (Iterator iter = psOpCom.iterator(); iter.hasNext();) {
			ProductoServicio element = (ProductoServicio) iter.next();
			calculaFlujo.setProducto(element);
		}
		calculaFlujo.setContextoFiltros(cfd.getContextoFiltros());
		if (cfd.tieneMutacion()) {
			calculaFlujo.setMutacion(true);
		} else {
			calculaFlujo.setMutacion(false);
		}
		
		if (cfd.tieneActividadMDF()) {
			calculaFlujo.setNoActividadMDF(true);
		} else {
			calculaFlujo.setNoActividadMDF(false);
		}
		flujoUnido = calculaFlujo.calcularNuevoFlujo();
		return flujoUnido.getDto();
	}	

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.actividades.ActividadAutomaticaSTEJB#onTerminoActividadST()
	 */
	protected void onTerminoActividadST(ActividadEJBDTO act) throws TnProcesoExcepcion {
	
	}

}
