/*
 * RecursosTVDelegate.java
 *
 * Created on 11 de abril de 2007, 03:21 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package co.com.telefonica.atiempo.vpistbba.recursos_tv;

import java.util.ArrayList;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.actividades.ActividadEJBDTO;
import co.com.telefonica.atiempo.interfaces.atiempo.TR016S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR017S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR018S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR019S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR605S;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.dto.InfoInvTvDTO;
import co.com.telefonica.atiempo.vpistbba.dto.InfoPostVentaTV;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.ejb.RecursosTVLocal;
import co.com.telefonica.atiempo.vpistbba.recursos_tv.ejb.RecursosTVLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 *
 * @author francois
 */

public class RecursosTVDelegate
    implements RecursosTVInterfaces
{
    private RecursosTVLocal servicios ;
    
    
    /**
     */
    
    public RecursosTVDelegate () throws ATiempoAppEx
    {
        try
        {
            servicios = ((RecursosTVLocalHome) HomeFactory.getHome (RecursosTVLocalHome.JNDI_NAME)).create ();
        }
        catch (NamingException nex)
        {
            throw new ATiempoAppEx (ATiempoAppEx.NAMING, nex);
        }
        catch (CreateException cex)
        {
            throw new ATiempoAppEx (ATiempoAppEx.CREATE, cex);
        }
    }
    
    public Long enviaDecoTarjetaPorUtilizar (long idPeticion, String ult4digitosTarjeta, String ult4digitosDeco, long idContratista,String tipoDeco) throws ATiempoAppEx
    {
        return servicios.enviaDecoTarjetaPorUtilizar (idPeticion, ult4digitosTarjeta, ult4digitosDeco, idContratista,tipoDeco) ;
    }
    
    public TR016S buscarRespuestaMensaje (Long idPeticion, Long idMensaje) throws ATiempoAppEx
    {
        return servicios.buscarRespuestaMensaje (idPeticion, idMensaje) ;
    }
    
    public void actualizaDecoTarjetaPorUtilizar (TR016S tr016s) throws ATiempoAppEx
    {
        servicios.actualizaDecoTarjetaPorUtilizar (tr016s) ;
    }
    
    public Long enviaConfiguracionServiciosTV (long idPeticion) throws ATiempoAppEx
    {
        return (servicios.enviaConfiguracionServiciosTV (idPeticion)) ;
    }
    
    public void actualizaConfiguracionServiciosTV (TR017S tr017s, boolean esAgendaSC) throws ATiempoAppEx
    {
        servicios.actualizaConfiguracionServiciosTV (tr017s, esAgendaSC) ;
    }

	public Long enviarActivacion(Long idPeticion, ArrayList listaPares) throws ATiempoAppEx
	{
		return servicios.enviarActivacion(idPeticion, listaPares);
	}
	public Long enviarActivacionMigracion(Long idPeticion, ArrayList listaPares) throws ATiempoAppEx
	{
			return servicios.enviarActivacionMigracion(idPeticion, listaPares);
	}
	
	public ArrayList getListaDecoTarjetas(Long idPeticion) throws ATiempoAppEx 
	{
		return servicios.getListaDecoTarjetas(idPeticion);
	}

    public Long enviaActualizacionInventarioTV (long idPeticion, String idActividad) throws ATiempoAppEx
    {
        return servicios.enviaActualizacionInventarioTV(idPeticion, idActividad);
    }

    public void recibeActualizacionInventarioTV (TR018S tr018s) throws ATiempoAppEx
    {
        servicios.recibeActualizacionInventarioTV (tr018s) ;
    }

    public Long enviaSolicitudInformacionTecnicaTV (long idPeticion, String idActividad) throws ATiempoAppEx
    {
        return servicios.enviaSolicitudInformacionTecnicaTV (idPeticion, idActividad) ;
    }

    public void recibeSolicitudInformacionTecnicaTV (TR019S tr019s) throws ATiempoAppEx
    {
        servicios.recibeSolicitudInformacionTecnicaTV (tr019s) ;
    }

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVInterfaces#enviarActivacionSoloEq(java.lang.Long, java.util.ArrayList)
	 */
	public Long enviarActivacionSoloEq(Long idPeticion, ArrayList listaPares) throws ATiempoAppEx
	{
		return servicios.enviarActivacionSoloEq(idPeticion,listaPares);
	}

	public Long enviarActivacionCI(Long idPeticion, ArrayList listaActivar,ArrayList listaEliminar) throws ATiempoAppEx
	{
		return servicios.enviarActivacionCI(idPeticion,listaActivar,listaEliminar);
	}

	public ArrayList getListaDecoTarjetasActivas(Long idPeticion) throws ATiempoAppEx
	{
		return servicios.getListaDecoTarjetasActivas(idPeticion);
	}

	public ArrayList getListaDecoTarjetasEnCas(Long idPeticion) throws ATiempoAppEx
	{
		return servicios.getListaDecoTarjetasEnCas(idPeticion);
	}


	public Long enviaConfiguracionServiciosTVAuto(long idPeticion, String idActividad,Integer idActividadFlujo) throws ATiempoAppEx
	{
		return servicios.enviaConfiguracionServiciosTVAuto(idPeticion,idActividad,idActividadFlujo);
	}

	public InfoPostVentaTV recuperaInfoTvPostVenta(Long nroPeticion) throws ATiempoAppEx
	{
		return servicios.recuperaInfoTvPostVenta(nroPeticion);
	}

	public void grabaDecosPostVenta(Long nroPeticion, ArrayList listaPares, long opCoPostPar) throws ATiempoAppEx
	{
		servicios.grabaDecosPostVenta(nroPeticion, listaPares, opCoPostPar);
	}

	public ArrayList recuperaDecosPostVenta(Long nroPeticion) throws ATiempoAppEx
	{
		return servicios.recuperaDecosPostVenta(nroPeticion);
	}
		
	public void actualizaAccionParesPostVenta(Long nroPeticion,ArrayList listaPares) throws ATiempoAppEx
	{
		servicios.actualizaAccionParesPostVenta(nroPeticion,listaPares);
	}

	public boolean tienePcEnAlta(Long nroPeticion) throws ATiempoAppEx
	{
		return servicios.tienePcEnAlta(nroPeticion);
	}

	public void enviaRefrescoPares(Long nroPeticion) throws ATiempoAppEx
	{
		servicios.enviaRefrescoPares(nroPeticion);		
	}

	public ArrayList getListaTematicos(Long idPeticion) throws ATiempoAppEx
	{
		return servicios.getListaTematicos(idPeticion);
	}

	public Long enviaConfiguracionServiciosTVDesactivacion(long idPeticion, String idActividad,Integer idActividadFlujo) throws ATiempoAppEx
	{
		return servicios.enviaConfiguracionServiciosTVDesactivacion(idPeticion,idActividad,idActividadFlujo);		
	}

	public InfoInvTvDTO recuperaInfoInvTv(Long idPeticion) throws ATiempoAppEx
	{
		return servicios.recuperaInfoInvTv(idPeticion);
	}

	public Long enviarActivacionEqYPares(Long idPeticion, ArrayList listaEq) throws ATiempoAppEx
	{
		return servicios.enviarActivacionEqYPares(idPeticion,listaEq);
	}

	public Long enviaConfiguracionServiciosTVEqYPares(long idPeticion) throws ATiempoAppEx
	{
		return servicios.enviaConfiguracionServiciosTVEqYPares(idPeticion);
	}

	public void limpiarInventarioDTH(Long id_peticion) throws ATiempoAppEx
	{
		servicios.limpiarInventarioDTH(id_peticion);
	}

	public boolean tieneAltaPcYaEcha(Long idPeticion) throws ATiempoAppEx
	{
		return servicios.tieneAltaPcYaEcha(idPeticion);
	}

	public void revisaYMarcaNovedadAutoTv(Long petiNumero) throws ATiempoAppEx
	{
		servicios.revisaYMarcaNovedadAutoTv(petiNumero);		
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVInterfaces#cargarInfoMigracionDeco2(java.lang.Long)
	 */
	public String cargarInfoMigracionDeco2(Long idPeticion) throws ATiempoAppEx {
		
		return servicios.cargarInfoMigracionDeco2(idPeticion);
	}

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVInterfaces#cargarInfoMigracionDeco(java.lang.Long)
	 */
	public int cargarInfoMigracionDeco(Long idPeticion) throws ATiempoAppEx {
		
		return servicios.cargarInfoMigracionDeco(idPeticion);
	}
//	<!--TODO: Defecto AT-1138 Se agrega metodo que retorna la cantidad de Ps de alta PVR- PVR- Inicio - Juan-->
	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVInterfaces#retornarCantidadDecosAltaPvr(java.lang.Long)
	 */
	public int retornarCantidadDecosAltaPvr(Long idPeticion) throws ATiempoAppEx {
	
		return servicios.retornarCantidadDecosAltaPvr( idPeticion);
	}
	public int pasoPorInstalacion(Long idPeticion) throws ATiempoAppEx {
	
		return servicios.pasoPorInstalacion(idPeticion);
	}
	
    public Long enviaConfiguracionServiciosTVEnVuelo (long idPeticion, boolean elementosViejos, boolean tienefamiliaPaqueteTV, boolean tienefamiliaTematicoTV) throws ATiempoAppEx{
        return (servicios.enviaConfiguracionServiciosTVEnVuelo (idPeticion, elementosViejos, tienefamiliaPaqueteTV, tienefamiliaTematicoTV)) ;
    }
    
    public Long enviaConfiguracionServiciosTVAgendaSC (long idPeticion, ArrayList decosAInstalar, boolean seInstalanPaquetes,  String idAgenda) throws ATiempoAppEx{
    	return servicios.enviaConfiguracionServiciosTVAgendaSC (idPeticion, decosAInstalar, seInstalanPaquetes,  idAgenda);
    }

	/* (non-Javadoc)
	 * @see co.com.telefonica.atiempo.vpistbba.recursos_tv.RecursosTVInterfaces#marcarNovedadQuiebrePeticionesVueloAgendaSC(java.lang.Long, java.util.ArrayList)
	 */
	public void marcarNovedadQuiebrePeticionesVueloAgendaSC(Long petiNumero, ArrayList listaDecosTarjetasAgendaSC) throws ATiempoAppEx {
		// TODO Auto-generated method stub
		servicios.marcarNovedadQuiebrePeticionesVueloAgendaSC(petiNumero,listaDecosTarjetasAgendaSC);
	}

	/**
	 * @param l
	 * @param act
	 * @throws ATiempoAppEx
	 */
	public void enviaConfiguracionMovistarPlay(long petiNumero, ActividadEJBDTO act) throws ATiempoAppEx {
		// TODO Apéndice de método generado automáticamente
		servicios.enviaConfiguracionMovistarPlay(petiNumero,act);
	}

	/**
	 * @param tr605s
	 */
	public void respuestaConfigMovistarPlay(TR605S tr605s)throws ATiempoAppEx{
		// TODO Apéndice de método generado automáticamente
		servicios.respuestaConfigMovistarPlay(tr605s);
	}
}
