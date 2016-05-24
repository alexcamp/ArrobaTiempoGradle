/*
 * Created on 23-feb-07
 */
package co.com.telefonica.atiempo.soltec.recursos;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR012S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR021S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR054S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR057S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR514S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR516S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR709E;
import co.com.telefonica.atiempo.interfaces.atiempo.TR709S;
import co.com.telefonica.atiempo.soltec.dto.convertidores.InformacionTecnicaDTO;
import co.com.telefonica.atiempo.soltec.ejb.eb.Mensaje_estado_stLocal;
import co.com.telefonica.atiempo.soltec.mensajeria.entrada.RecursosServiciosLocal;
import co.com.telefonica.atiempo.soltec.mensajeria.entrada.RecursosServiciosLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author TCS
 */
public class RecursosDelegate implements RecursosInterfaces {

	private RecursosServiciosLocal servicios;

	public RecursosDelegate() throws ATiempoAppEx {
		try {
			servicios = ((RecursosServiciosLocalHome) HomeFactory.getHome(RecursosServiciosLocalHome.JNDI_NAME)).create();
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}
	}
	
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#envioPuntosRedSTB(java.lang.String, java.lang.String)
	 */
	public void envioPuntosRedSTB(String peticion, String idActividad, String codActividad) throws ATiempoAppEx {
		// TODO Auto-generated method stub
		
		servicios.envioPuntosRedSTB(peticion,idActividad, codActividad);
	}
	
	public void envioPuntosRedSTBGR(String peticion, String idActividad, String codActividad) throws ATiempoAppEx {
		// TODO Auto-generated method stub
		
		servicios.envioPuntosRedSTBGR(peticion,idActividad,codActividad);
	}

	public void consultaPuntosRedSTB(TR012S tr012s) throws ATiempoAppEx {
		// TODO Auto-generated method stub
		
		servicios.consultaPuntosRedSTB(tr012s);
		
	}
	
	public void consultaPuntosRedSTBGR(TR514S tr514s) throws ATiempoAppEx {		
		servicios.consultaPuntosRedSTBGR(tr514s);
		
	}

	/* (no Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces#envioMarcaLineaDefectuosa(java.lang.String, java.lang.String)
	 */
	public void envioMarcaLineaDefectuosa(String peticion, String idActividad) throws ATiempoAppEx {
		servicios.envioMarcaLineaDefectuosa(peticion, idActividad);
	}
	
	public void envioMarcaLineaDefectuosaGR(String peticion, String idActividad) throws ATiempoAppEx {
		servicios.envioMarcaLineaDefectuosaGR(peticion, idActividad);
	}

	/* (no Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces#envioMarcaLineaEnServicio(java.lang.String, java.lang.String)
	 */
	public void envioMarcaLineaEnServicio(String peticion, String idActividad) throws ATiempoAppEx {
		servicios.envioMarcaLineaEnServicio(peticion, idActividad);
	}
	
	public void envioMarcaLineaEnServicioGR(String peticion, String idActividad) throws ATiempoAppEx {
		servicios.envioMarcaLineaEnServicioGR(peticion, idActividad);
	}

	/* (no Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces#recibeMarcaLineaDefectuosa(co.com.telefonica.atiempo.interfaces.atiempo.TR021S)
	 */
	public void recibeMarcaLinea(TR021S tr021s) throws ATiempoAppEx {
		servicios.recibeMarcaLinea(tr021s);
	}
	
	public void recibeMarcaLineaGR(TR516S tr516s) throws ATiempoAppEx {
		servicios.recibeMarcaLineaGR(tr516s);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces#obtenerDatosTecnicosLB(java.lang.Long)
	 */
	public InformacionTecnicaDTO obtenerDatosTecnicosLB(Long codAveOri) throws ATiempoAppEx {
		// TODO Auto-generated method stub
		return servicios.obtenerDatosTecnicosLB(codAveOri);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces#esMarcadaAveriaSTB(java.lang.Long)
	 */
	public boolean esMarcadaAveriaSTB(Long idPeticion) throws ATiempoAppEx {
		// TODO Auto-generated method stub
		return servicios.esMarcadaAveriaSTB(idPeticion);
	}

	/**
	 * @param act
	 * @param numeroPeticion
	 * @param codigoActividad
	 */
	public void ConfigurarPresenciaDigital(ActividadEJBDTO act, Long numeroPeticion, String codigoActividad) {
		servicios.configurarPresenciaDigital( act,  numeroPeticion, codigoActividad);
		
	}

	/**
	 * @param tr054s
	 */
	public void procesarRespuestaConfiguracionPresenciaDigital(TR054S tr054s) {
		servicios.procesarRespuestaConfiguracionPresenciaDigital(tr054s);
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces#enviarRefrecarDatos(java.lang.Long, java.lang.String, java.lang.String)
	 */
	public void enviarRefrecarDatos(Long idPeticion) {
		// TODO Apéndice de método generado automáticamente
		servicios.enviarRefrecarDatos(idPeticion);
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.soltec.recursos.RecursosInterfaces#isIDPC(java.lang.Long)
	 */
	public boolean isIDPC(Long idPeticion) throws ATiempoAppEx {
		return servicios.isIDPC(idPeticion);
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#configurarCamaraZTE(co.com.telefonica.atiempo.actividades.ActividadEJBDTO)
	 */
	public void configurarCamaraZTE(Long numPeticion) throws ATiempoAppEx {
		servicios.configurarCamaraZTE(numPeticion);
	}

	/* (sin Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.recursos.RecursosInterfaces#respuestaCamaraZTE(co.com.telefonica.atiempo.interfaces.atiempo.TR612S)
	 */
	public void respuestaCamaraZTE(TR057S tr) throws ATiempoAppEx {
		servicios.respuestaCamaraZTE(tr);
	}

	public void recepcionActivarCamaraAgendaSC(TR709S tr) throws ATiempoAppEx {
		servicios.recepcionActivarCamaraAgendaSC(tr);
	}

	public void enviarActivarCamaraAgendaSC(TR709E tr) throws ATiempoAppEx {
		servicios.enviarActivarCamaraAgendaSC(tr);
	}
	//REQ TOA FASE III DCARDENA
	public void hayPGIAveria (String codigoError,String ErrorMesage,String codigoTR,String codigoAveria,String codigoActividadGeneradora,Mensaje_estado_stLocal meST)
	{
		servicios.hayPGIAveria(codigoError,ErrorMesage, codigoTR, codigoAveria, codigoActividadGeneradora, meST);	
	}
	//fin REQ TOA FASE III DCARDENA
}
