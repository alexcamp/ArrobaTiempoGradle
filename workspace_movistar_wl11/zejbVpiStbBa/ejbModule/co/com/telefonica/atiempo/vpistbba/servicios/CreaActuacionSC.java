/*
 * Created on Aug 27, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package co.com.telefonica.atiempo.vpistbba.servicios;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.ActividadKey;
import co.com.telefonica.atiempo.ejb.eb.ActividadLocal;
import co.com.telefonica.atiempo.ejb.eb.Agenda_scKey;
import co.com.telefonica.atiempo.ejb.eb.Agenda_scLocal;
import co.com.telefonica.atiempo.ejb.eb.Agenda_scLocalHome;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocal;
import co.com.telefonica.atiempo.ejb.eb.BintegradaLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.TR701S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.ServicioBasico;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.servicioba.RecursosBADelegate;
import co.com.telefonica.atiempo.vpistbba.servicioba.naked.AgendaServicioDelegate;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.comun.ComunInterfaces;

/**
 * @author damartinezv
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CreaActuacionSC extends ServicioBasico {
	private Logger log = LoggerFactory.getLogger (getClass ()) ;
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#procesar(java.lang.Object[])
	 */
	protected void procesar(Object[] obj) throws ATiempoAppEx {
		// TODO Auto-generated method stub
		TR701S tr701S = (TR701S) obj[0];		
		RecursosBADelegate delegate=new RecursosBADelegate();
		try{
			//DAVID: lo siguiente para saber si viene en reversa y est� en intalar. Si es as� se llama al m�todo cl�sico de 
			//recepcionCreacionActuacionAgendaSC y se evita llamar a recepcionCreacionActuacionAgendaSCEnPGACS.
			Agenda_scLocalHome agenda_scLocalHome = (Agenda_scLocalHome) HomeFactory.getHome(Agenda_scLocalHome.JNDI_NAME);
			BintegradaLocalHome bintegradaLocalHome=(BintegradaLocalHome)HomeFactory.getHome(BintegradaLocalHome.JNDI_NAME);
			
			Agenda_scKey agenda_scKey = new Agenda_scKey(tr701S.getIdSchedule());
			Agenda_scLocal agenda_scLocal =agenda_scLocalHome.findByPrimaryKey(agenda_scKey);
			Long petiNumero=agenda_scLocal.getPeti_numero();
			
			BintegradaLocal bintegradaLocalLastDate=bintegradaLocalHome.findLastDateByPetiNum(petiNumero);
			ActividadLocal actividadLocalLastDate =bintegradaLocalLastDate.getFk_bi_act();
			ActividadKey actividadKeyLastDate = (ActividadKey)actividadLocalLastDate.getPrimaryKey();
			Long acIdLastDate=actividadKeyLastDate.act_id;
			
			Integer estadoActividadInstalar=bintegradaLocalLastDate.getBi_estado_peticion();

			Long idActInstalar=new Long(ComunInterfaces.idActividadInstalar);
			
			boolean esReversaEnInstalar=false;
			if(acIdLastDate.equals(idActInstalar)&&estadoActividadInstalar.intValue()==ComunInterfaces.estadoPeticionCancelada){
				esReversaEnInstalar=true;
			}			
			boolean dePgacsAAutoinst = delegate.validarEnvioTrEnPGACS( tr701S.getIdSchedule() );			
			
			if( dePgacsAAutoinst&&!esReversaEnInstalar ){
				delegate.recepcionCreacionActuacionAgendaSCEnPGACS(tr701S);
			}else{
				AgendaServicioDelegate agendaDelegate=new AgendaServicioDelegate();
				agendaDelegate.recepcionCreacionActuacionAgendaSC(tr701S);
			}					
			
		}catch(FinderException e){
			log.debug("Error FinderException en CreaActuacionSC.."+e);
		}catch(NamingException e){
			log.debug("Error NamingException en CreaActuacionSC.."+e);
		}
		
		

	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.utiles.ServicioBasico#convertirMensaje(java.lang.String)
	 */
	protected Object[] convertirMensaje(String mensaje) throws ATiempoAppEx {
        TR701S tr701S = (TR701S) XMLUtilities.unmarshall(mensaje);
        Object[] obj = new Object[1];
        obj[0] = tr701S;

        return obj;
	}

}
