/*
 * Created on 08-01-2013
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Tmp_agenda_scLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.TR719S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosDelegate;
import co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBAInterfaces;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * @author 
 *
 */
public class RefrescarRecursosSTBServicio extends ServicioBasico
{
	protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;

	protected void procesar(Object[] obj) throws ATiempoAppEx {
		
		TR719S tr719s= (TR719S) obj[0];
		/*Llamado al Delegate indicado para procesar la petición de Agenda SC*/
		String nroPeticion = tr719s.getId_actuacion();
		log.debug("Me llega en peticion NP:"+nroPeticion);
		Long idPeticion = new Long(nroPeticion.substring(2,nroPeticion.indexOf("-")));			
		RecursosInterfaces recursosInterfaces =  null;
		RecursosBAInterfaces recursosBAInterfaces = null;
		
		PeticionesInterfaces pI = null;				
		boolean esGranite = false;
		
//		 SE VALIDA QUE TIPO DE LINEA ES
		try {
			pI = new PeticionesDelegate();				
			esGranite = pI.usaGranite(idPeticion);
		}
		catch (ATiempoAppEx e)
		{
			log.error(e);
		}	
//		  CR-14657 Granite - adocarmo - fin		
		try
		{
			recursosInterfaces = new RecursosDelegate();
			
//			  CR-14657 Granite - adocarmo - inicio	
			if(tr719s.getInterfaz().equals(ComunInterfaces.INTERFAZ_LB)){
				if (esGranite) {
					recursosInterfaces.consultaRecursoGraniteSTB_BA(idPeticion,"F",null);

				}
				else {
					recursosInterfaces.consultaRecursoSTB_BA(idPeticion, "F", null);			
				}
			}
			
			
			if(tr719s.getInterfaz().equals(ComunInterfaces.INTERFAZ_BA)){
				recursosBAInterfaces = new RecursosBADelegate();
				// SIGRES 
				recursosInterfaces.consultaRecursoSTB_BA(idPeticion, "",null);
				recursosBAInterfaces.enviaRefrescoBA(idPeticion.toString(),"RF",null);
				//fin sigres
			}
			
			Tmp_agenda_scLocalHome tmpAgendaSCLocalHome = (Tmp_agenda_scLocalHome)HomeFactory.getHome(Tmp_agenda_scLocalHome.JNDI_NAME);
			Tmp_agenda_scLocal tmpAgendaSCLocal = tmpAgendaSCLocalHome.create(new Long (tr719s.getId()));
			tmpAgendaSCLocal.setId_schedule(tr719s.getId_actuacion());
			tmpAgendaSCLocal.setPeti_numero(idPeticion);
			tmpAgendaSCLocal.setXml(XMLUtilities.marshall (tr719s));
		}
		catch (ATiempoAppEx e)
		
		{
			log.error(e);
		} catch (NamingException e) {
			// TODO Bloque catch generado automáticamente
			log.error(e);
		}  catch (NumberFormatException e) {
			// TODO Bloque catch generado automáticamente
			log.error(e);
		} catch (CreateException e) {
			// TODO Bloque catch generado automáticamente
			log.error(e);
		}
		
	}


	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
		Object[] obj = new Object[1];
		TR719S tr719s = (TR719S) XMLUtilities.unmarshall(mensaje);
		obj[0] = tr719s;
		return obj;
	}
	
	
}
