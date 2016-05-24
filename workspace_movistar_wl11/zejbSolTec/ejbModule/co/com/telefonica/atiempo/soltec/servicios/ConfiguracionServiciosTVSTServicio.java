/*
 * Created on 22-08-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.servicios;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.interfaces.atiempo.TR017S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR708S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR711S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR801S;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_deco_tarjetaLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tmp_deco_tarjetaLocalHome;
import co.com.telefonica.atiempo.soltec.servicios.TOA.ServicioTOASTDelegate;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBADelegate;
import co.com.telefonica.atiempo.soltec.serviciosba.RecursosBAInterfaces;
import co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVDelegate;
import co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVInterfaces;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;


/**
 * @author Victor
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ConfiguracionServiciosTVSTServicio extends ServicioBasico {
	protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		
		TR017S tr017s = (TR017S) obj[0];
		boolean esAgendaSC = false;
		
		RecursosTVInterfaces recursosTV = new RecursosTVDelegate();
		recursosTV.actualizaConfiguracionServiciosTV(tr017s);
		
		RecursosBAInterfaces recursosBA = new RecursosBADelegate();
		esAgendaSC = recursosBA.esAgendaSC(new Long(tr017s.getAtisRequestNumber()));
		
		if (esAgendaSC){
			try{
				Tmp_deco_tarjetaLocalHome tmp_deco_tarjetaLocalHome = (Tmp_deco_tarjetaLocalHome)HomeFactory.getHome (Tmp_deco_tarjetaLocalHome.JNDI_NAME);
				Collection tmpDecoTarList = tmp_deco_tarjetaLocalHome.findByNroPeticion(new Long(tr017s.getAtisRequestNumber()));
				Iterator tmpDecoTarListIt711=null;
				TR711S tr711s = null;
				String idMensaje="";
				boolean yaTieneTR711=false;
				for(tmpDecoTarListIt711=tmpDecoTarList.iterator();tmpDecoTarListIt711.hasNext();){
					Tmp_deco_tarjetaLocal tmp_deco_tarjetaLocal = (Tmp_deco_tarjetaLocal)tmpDecoTarListIt711.next();			
					try{
						tr711s = (TR711S) XMLUtilities.unmarshall(tmp_deco_tarjetaLocal.getXml());
						yaTieneTR711=true;
						/**
						 * DAVID: Si ya tiene TR711S se debe cerrar la actividad.
						 */
						recursosBA.cierreActividadYActuacionAgendaSC(tr711s);
					}catch(ClassCastException ex){
						log.debug("Se esperaba una tr711S pero no es.");
						continue;
					}
				}
				/**
				 * DAVID: Si todavía no tiene TR711S registrada, entonces se envía la TR708E porque está en activación de recursos de agenda y no en cierre de
				 * actuación de agenda. Pero antes hay que evaluar si ya tiene tr708s, que en este caso llega antes de enviar la tr708e.
				 * 
				 * DAVID: DIC 10 2010, LO DE ARRIBA NO ESTABA FUNCIONANDO, ALGUIEN LO QUITO, SE VUELVE A UTILIZAR...
				 * 
				 */
				if(!yaTieneTR711){
					TR708S tr708s = null;
					Iterator tmpDecoTarListIt708=null;
					for(tmpDecoTarListIt708=tmpDecoTarList.iterator();tmpDecoTarListIt708.hasNext();){
						Tmp_deco_tarjetaLocal tmp_deco_tarjetaLocal = (Tmp_deco_tarjetaLocal)tmpDecoTarListIt708.next();
						
						try{
							ServicioTOASTDelegate toaDelegate = new ServicioTOASTDelegate();
							TR801S tr801s = (TR801S) XMLUtilities.unmarshall(tmp_deco_tarjetaLocal.getXml());
							toaDelegate.enviaActivarDecosTarjetasTOA(tr801s.getIdSchedule(),tr801s.getId());
							break;
						}catch(ClassCastException ex){
							log.debug("Se esperaba una TR801S pero no es.");
						}
						try{
							/**
							 * DAVID: Si ya tiene tr708s, entonces se puede enviar la tr708e. Se envía el codAve y el id del mensaje agendaSC de la trS para 
							 * utilizarlo en la trE.
							 */
							tr708s = (TR708S) XMLUtilities.unmarshall(tmp_deco_tarjetaLocal.getXml());
							recursosBA.enviaActivarDecosTarjetasAgendaSC(new Long(tr017s.getAtisRequestNumber()),tr708s.getId());
							break;
						}catch(ClassCastException ex){
							log.debug("Se esperaba una tr708S pero no es.");
							continue;
						}
						
					}					
				}
			}catch(NamingException e){
				log.debug("Error en naming, en ConfiguracionServiciosTVSTServicio: "+e.toString());
			}
			catch(FinderException e){
				log.debug("Error en finder, en ConfiguracionServiciosTVSTServicio: "+e.toString());
			}

		}
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		Object[] obj = new Object[1];
		TR017S tr017s = (TR017S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr017s;
		
		return obj;
	}

}
