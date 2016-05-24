
package co.com.telefonica.atiempo.vpistbba.serviciosba;


import java.util.ArrayList;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.PsVsOcVO;
import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baKey;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baLocal;
import co.com.telefonica.atiempo.ejb.eb.Mensaje_estado_baLocalHome;
import co.com.telefonica.atiempo.ejb.eb.PeticionKey;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocal;
import co.com.telefonica.atiempo.ejb.eb.PeticionLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocal;
import co.com.telefonica.atiempo.ejb.eb.Recursos_linea_basicaLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Traslado_solobaLocal;
import co.com.telefonica.atiempo.ejb.eb.Traslado_solobaLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.TR040S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;

import com.telefonica_chile.atiempo.actividades.TnProcesoExcepcion;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.atiempo.utiles.VpistbbaConfig;
import com.telefonica_chile.comun.ComunInterfaces;
import com.telefonica_chile.comun.ps.dto.ProductoDTO;


//CR4860 - Sigres - agonzalez- 16-05-2008
public class ConfiguracionServicioSigresCambioPlanOkServicio extends ServicioBasico {

	protected transient Logger log = LoggerFactory.getLogger(getClass()) ;
	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		TR040S tr040s = (TR040S) obj[0];
		RecursosBAInterfaces recursos = new RecursosBADelegate();
		recursos.recepcionConfiguracionSigresCambioPlanBAOk(tr040s);
		PeticionesDelegate peticionesDelegate = new PeticionesDelegate();
		
		try{
			Mensaje_estado_baLocalHome mensajeEstadoBALocalHome = (Mensaje_estado_baLocalHome) HomeFactory.getHome(Mensaje_estado_baLocalHome.JNDI_NAME);
			
			Mensaje_estado_baKey mensajeEstadoBAKey = new Mensaje_estado_baKey(new Long(tr040s.getId()));
			Mensaje_estado_baLocal mensajeEstadoBALocal = mensajeEstadoBALocalHome.findByPrimaryKey(mensajeEstadoBAKey);
			
			if (peticionesDelegate.tieneCodActividadNoAvance(mensajeEstadoBALocal.getCod_actividad_generadora())){
				Recursos_linea_basicaLocalHome linea_basicaHome= (Recursos_linea_basicaLocalHome) HomeFactory.getHome(Recursos_linea_basicaLocalHome.JNDI_NAME);
				Recursos_linea_basicaLocal linea_basicaLocal = linea_basicaHome.findByPeticion(new Long(tr040s.getAtiempoRequestNumber()));
				
				if (linea_basicaLocal.getReinstalacion_internet() != null || linea_basicaLocal.getReinstalacion_internet().equals("change")){
					configurarCuenta(new Long(tr040s.getAtiempoRequestNumber()));
				}
			}
		}catch(NamingException ex){
			log.error("Error de instacia del bean: ");
			ex.printStackTrace();
		}catch(FinderException ex2){
			log.error("Error de consulta del bean: ");
			ex2.printStackTrace();
		}
	}

	/**
	 * @see co.com.telefonica.atiempo.vpistbba.servicios.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		Object[] obj = new Object[1];
		TR040S tr040S = (TR040S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr040S;
		return obj;
	}

	private void configurarCuenta(Long numeroPeticion){
		try{
			RecursosBADelegate recursosBADelegate=new RecursosBADelegate();
			String numeroServicio=null; 
			boolean esTraslado = false;
			PeticionLocalHome peticionLocalHome = null ;
			ArrayList listadoPsOk = new ArrayList();
			
			esTraslado = recursosBADelegate.esTrasladoBa(numeroPeticion);
			
	        InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
	        ArrayList listadoPS = infoComunColombiaBI.getPsYTipoPcNoRealizado(numeroPeticion);
					
			for (int i= 0; i<listadoPS.size(); i++){
	        	ProductoDTO productoLocal = (ProductoDTO)listadoPS.get(i);
//	        	REQ BA NAKED adicion familia PC y PS naked
	        	if ((productoLocal.getIdFaps().intValue() == ComunInterfaces.familiaBandaAncha)
	        			|| (productoLocal.getIdFaps().intValue() == ComunInterfaces.familiaPcBA)
						|| (productoLocal.getIdFaps().intValue() == ComunInterfaces.familiaPcPsBANaked)
						|| (productoLocal.getIdFaps().intValue() == ComunInterfaces.familiaBandaAnchaNaked)){
	        		PsVsOcVO psVsTemp = new PsVsOcVO();
	        		
	        		psVsTemp.setPsId(productoLocal.getId());
	        		psVsTemp.setOpComId(productoLocal.getIdOpComercial());
	        		psVsTemp.setOpComTipo(productoLocal.getTipoOperacionComercial());
	        		psVsTemp.setFaPsId(productoLocal.getIdFaps());
	        		psVsTemp.setCorrelativo(productoLocal.getCorrelativo());
	        		psVsTemp.setOk(productoLocal.isEstaOK());
	        		
	        		
	        		listadoPsOk.add(psVsTemp);
	        	}
	        }
					
			if(esTraslado){
				Traslado_solobaLocalHome traslado_solobaLocalHome = (Traslado_solobaLocalHome) HomeFactory.getHome(Traslado_solobaLocalHome.JNDI_NAME);
				Traslado_solobaLocal traslado_solobaLocal=null;
				try{
					traslado_solobaLocal = traslado_solobaLocalHome.findByPetiAlta(numeroPeticion);
					numeroServicio=traslado_solobaLocal.getLinea_anterior();
					log.debug("Es traslado solo BA");
				} catch (FinderException e) {
				    log.debug("Es traslado LB y BA");
				    PeticionLocal petiLocal;
	                try {
	                   if(peticionLocalHome==null)
	                   		peticionLocalHome=(PeticionLocalHome) HomeFactory.getHome(PeticionLocalHome.JNDI_NAME);
	                        
	                        petiLocal = peticionLocalHome.findByPrimaryKey(new PeticionKey(numeroPeticion));
	                        numeroServicio=petiLocal.getIdentificadorOriLinea();
	                        //numeroServicio=petiLocal.getNum_ide_nu_stb();
	                    } catch (FinderException e1) {
	                        log.debug("Error en Actividad Obtener Cuenta Correo Sigres BA Bean No se pudo obtener el telefono de traslado",e);
	                    }

					}
				}

			PsVsOcVO psprim = ActividadEJBDTO.getPsPrimario(listadoPsOk);	
				
			//si es traslado alta, busco la cuenta de correo donde esta instalada la linea
			recursosBADelegate.obtenerCuentaCorreoSigres(numeroPeticion,numeroServicio, "Director de Flujos.Instalacion.Control_Instalacion",psprim);
		}catch(ATiempoAppEx e){
			e.printStackTrace();
		}catch(NamingException e1){
			e1.printStackTrace();
		}catch(TnProcesoExcepcion e2){
			e2.printStackTrace();
		}
		
	}
}
