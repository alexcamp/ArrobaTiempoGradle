package co.com.telefonica.atiempo.soltec.reglas.ejb.sb;

import javax.ejb.FinderException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.TipoCodigoStLocal;
import co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb.TipoCodigoStLocalHome;
import co.com.telefonica.atiempo.soltec.dto.TipoCodigoDto;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Peticion_stLocalHome;
import co.com.telefonica.atiempo.soltec.ejb.eb.ReglaKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Regla_area_ges_cod_estadoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Regla_area_ges_cod_estadoLocalHome;
import co.com.telefonica.atiempo.soltec.reglas.ReglasInterfaces;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * Bean implementation class for Enterprise Bean: ReglasST
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class ReglasSTBean
	extends co.com.telefonica.atiempo.utiles.SessionBeanAdapter
	implements ReglasInterfaces{
		
	protected transient Logger log = LoggerFactory.getLogger (getClass ()) ;

	public long getRegla(Long nroIncidente){
		long regla=0;
		try {
			Peticion_stLocalHome peticionStHome = (Peticion_stLocalHome) HomeFactory.getHome(Peticion_stLocalHome.JNDI_NAME);
			Peticion_stKey key=new Peticion_stKey(nroIncidente);
			Peticion_stLocal peticionLocal=peticionStHome.findByPrimaryKey(key);
			
			//Si viene con estado terminado, regla =-1 solo se ocupa en verifica peticion para enviar a cierre de inmediato
			if (codigoEstadoAveriaTerminada.equals(peticionLocal.getCod_est_ave_cd())){
				regla=-1;
				return regla;
			}

			//Obtengo el tipo de codigo Estado averia
			TipoCodigoStLocalHome tipoCodigoStLocalHome = (TipoCodigoStLocalHome) HomeFactory.getHome(TipoCodigoStLocalHome.JNDI_NAME);
			TipoCodigoStLocal tipoCodigoStLocal = tipoCodigoStLocalHome.create();
			TipoCodigoDto tipoCodigoDtoCod_est_ave_cd = tipoCodigoStLocal.getTipoCodigoByAtributo("COD_EST_AVE_CD");
		
			//Obtengo la familia de la peticion
			//Obtengo Tipo Producto
			String ide_pro_cmr = peticionLocal.getIde_pro_cmr_cd();
			Long fId= new  Long(0); 
			if ("L".equals(ide_pro_cmr)) {
				fId= new  Long(1); 
			} else if ("BA".equals(ide_pro_cmr)) {
				fId= new  Long(2);
			} else if ("TV".equals(ide_pro_cmr)) {
				fId= new  Long(3);
			}
			Regla_area_ges_cod_estadoLocalHome regla_area_ges_cod_estadoLocalHome = (Regla_area_ges_cod_estadoLocalHome)HomeFactory.getHome(Regla_area_ges_cod_estadoLocalHome.JNDI_NAME);
			try{
				Regla_area_ges_cod_estadoLocal regla_area_ges_cod_estadoLocal = regla_area_ges_cod_estadoLocalHome.findByAreaEstado(peticionLocal.getCod_are_ges_cd(),fId,tipoCodigoDtoCod_est_ave_cd.getTipo(),peticionLocal.getCod_est_ave_cd()); 
				regla=((ReglaKey)regla_area_ges_cod_estadoLocal.getRegla().getPrimaryKey()).id_regla.longValue();
			}catch(FinderException f){
				log.debug("No hay regla asociada para esa agencia y estado");
			}

		}catch(Exception e){
			log.error("Error al buscar la regla",e);
		}
		return regla;
	}

}
