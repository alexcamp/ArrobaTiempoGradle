package co.com.telefonica.atiempo.vpistbba.actividades.recepcion.ejb.sb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.vpistbba.estructura_flujo.DATOS_ATVPISTBBA;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;
import com.telefonica_chile.director.algoritmo.CalculaFlujo;
import com.telefonica_chile.director.algoritmo.CalculaFlujoDatos;
import com.telefonica_chile.director.algoritmo.DirectorException;
import com.telefonica_chile.director.algoritmo.Flujo;
import com.telefonica_chile.director.dto.ProductoServicio;
import com.telefonica_chile.vpistbba.servicios.dto.FlujoDto;

/**
 * Bean implementation class for Enterprise Bean: AGeneraFlujoPeticion
 */
public class AGeneraFlujoPeticionBean
	extends co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB
	implements ComunInterfaces 
	{

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onInicioActividadVPI()
	 */
	protected void onInicioActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		log.debug("INICIO AUTOMATICO, AGeneraFlujoPeticion");		
		
		try {
			
			this.calcularNuevoFlujo(act.getNumeroPeticion());
			act.setDato(DATOS_ATVPISTBBA.RECEPCION.VERIFICACION_FLUJO.vefl_ok,"S"); 

		} catch (Exception e) {
			act.setDato(DATOS_ATVPISTBBA.RECEPCION.VERIFICACION_FLUJO.vefl_ok,"N");
			log.error("DirectorException No se Pudo CalCular Flujo. [" + act.getNumeroPeticion() + "]", e);
		}

		log.debug("FIN AUTOMATICO, AGeneraFlujoPeticion");		

		
	}
	
	private FlujoDto calcularNuevoFlujo(Long peticion) throws DirectorException {
		HashMap listaFlujos = new HashMap();
		FlujoDto flujoUnido = new FlujoDto();
		CalculaFlujoDatos cfd = new CalculaFlujoDatos();

		try {
			//Recoger datos de peticion
			cfd.setPeticion(peticion);

			InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
			ArrayList listadoPS = infoComunColombiaBI.getProductoServicio( peticion );
			// Cr20948 - Altamira -  Implementación de cambios de plan de cuenta controlada por logica de cambio de producto Pablo L y Pablo Cawen - Abr/2009 
			// Cr20948 - Altamira - Correccion para que funcione cambio de producto con servicios suplementarios - 22/May/2009 . Fernando M, Pabloc Cawen & Guido.
			boolean hayUnPcLinea = false;
			List psPlanCta = new ArrayList(5);
			for (int i=0; listadoPS!=null && i<listadoPS.size(); i++) {
				ProductoDTO psDto = (ProductoDTO) listadoPS.get(i);
				log.debug("Revisando PC: [" + psDto.getId() + "," + psDto.getTipoOperacionComercial()+ "," + psDto.getIdFaps() + "] peticion=" + peticion);
				ProductoServicio ps = new ProductoServicio();
				ps.setIdProductoServicio( psDto.getId() );
				ps.setIdOperacionComercial( psDto.getIdOpComercial() );
				ps.setTipoOperacionComercial( psDto.getTipoOperacionComercial() );

				if ( psDto.getIdFaps().intValue() == familiaPcLinea || psDto.getIdFaps().intValue() == familiaIP ) {
					ps.setCodigoTipoPC( "PC-LINEA" );
					 hayUnPcLinea = true;
//					REQ BA NAKED adicion familia PC naked
				} else if ( psDto.getIdFaps().intValue() == familiaPcBA || psDto.getIdFaps().intValue() == familiaPcBANaked)
					ps.setCodigoTipoPC( "PC-BA" );
				else if ( psDto.getIdFaps().intValue()== familiaPcTV )
					ps.setCodigoTipoPC( "PC-TV" );				
//				CR20948 Altamira modicacion para CP - AT-1865  - Pablo L - 29/04/2009 (Para generar flujo para ps de altamira)
				else if ( psDto.getIdFaps().intValue()== familiaLinea ) {
					//Posible plan de cuenta controlada
					//ps.setCodigoTipoPC( "PLAN-CTA" ); // Decidimos mas tarde
					psPlanCta.add(ps);
				}
				//Fin CR20948
				cfd.setProducto(ps);
			}
			if (!hayUnPcLinea) { // Si no hay un PC de linea entonces, entonces evalua si no hay planes de cuentas controlado complementario
				Long tipoACP = null;
				Long tipoBCP = null;
				Iterator it = psPlanCta.iterator();
				boolean unoAUno = true;
				while (it.hasNext()) {
					ProductoServicio ps = (ProductoServicio) it.next();
					String tipoOp = ps.getTipoOperacionComercial();
					if (tipoOp == null) {
						continue;
					}
					if (tipoOp.equals("ACP")) {
						if (tipoACP != null) {// tiene que haber UNO y UNO (PSs Linea para complementarse)
							unoAUno = false;
							break;
						}
						tipoACP = ps.getIdProductoServicio();
					} else if (tipoOp.equals("BCP")) {
						if (tipoBCP != null) {// tiene que haber UNO y UNO (PSs Linea para complementarse)
							unoAUno = false;
							break;
						}
						tipoBCP = ps.getIdProductoServicio();
					}
				}
				if (tipoACP != null && tipoBCP != null && unoAUno) {
					log.debug("calcularNuevoFlujo() peticion=" + peticion + " Estamos ante un cambio de PLAN CUENTA CONTROL tipoACP=" + tipoACP + " tipoBCP=" + tipoBCP);
					Iterator it2 = psPlanCta.iterator();
					while (it2.hasNext()) {
						ProductoServicio ps = (ProductoServicio) it2.next();
						ps.setCodigoTipoPC( "PLAN-CTA" );
					}
				} else {
					log.debug("calcularNuevoFlujo() peticion=" + peticion + " NO HAY un cambio de PLAN CUENTA CONTROL idTipoACP=" + tipoACP + " idTipoBCP=" + tipoBCP  + " unoAUno=" + unoAUno);
				}
			}
//			PeticionLocalHome peticionLocalHome = (PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
//			PeticionLocal peticionEntity = peticionLocalHome.findByPrimaryKey(new PeticionKey(peticion));
//
//			Collection pss = peticionEntity.getProducto_servicio_peticion();
//			for (Iterator iter = pss.iterator(); iter.hasNext();) {
//				Producto_servicio_peticionLocal ppsocEntity = (Producto_servicio_peticionLocal) iter.next();
//				
//				//TODO No se toma ProductoServicio con valor Realizado 1, porque refleja que ya esta instalado
//				
//				if (ppsocEntity.getPspe_realizado().shortValue() != 1)
//				{
//					ProductoServicio ps = new ProductoServicio();
//					ps.setIdProductoServicio(ppsocEntity.getIdProductoServicio());
//					ps.setIdOperacionComercial(ppsocEntity.getIdOperacionComercial());
//					cfd.setProducto(ps);
//				}
//			}

			// ARMADO DE LOS DATOS DEL FILTRO
			HashMap contextoFiltros = new HashMap();

			cfd.setPasaPorMDF( false );
			flujoUnido = this.calcularNuevoFlujo(cfd);
//		} catch (NamingException e) {
//			log.fatal("NamingException :", e);
//			throw new DirectorException("No encontro el home de la Tabla Peticion", e);
//		} catch (FinderException e) {
//			log.error("FinderException :", e);
//			throw new DirectorException("No encontro la Peticion " + peticion + "en la base.", e);
		} catch (ATiempoAppEx e) {
			log.error("ATiempoAppEx :", e);
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
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#onTerminoActividadVPI()
	 */
	protected void onTerminoActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
		
	}
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.actividades.ActividadAutomaticaEJB#incializaActividadVPI()
	 */
	protected void incializaActividadVPI(ActividadEJBDTO act) throws TnProcesoExcepcion {
//		Esta actividad Termina de Inmediato al finalizar su Logica de inicio
		act.setRealizarTerminoInmediato(true);	
	}
}
