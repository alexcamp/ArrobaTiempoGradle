/*
 * Created on 12-04-2007
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package co.com.telefonica.atiempo.soltec.serviciostv;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.interfaces.atiempo.TR016S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR017S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR018S;
import co.com.telefonica.atiempo.interfaces.atiempo.TR019S;
import co.com.telefonica.atiempo.soltec.mensajeria.tv.ejb.sb.RecursosTVLocal;
import co.com.telefonica.atiempo.soltec.mensajeria.tv.ejb.sb.RecursosTVLocalHome;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

/**
 * @author Karlo
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RecursosTVDelegate implements RecursosTVInterfaces{

	RecursosTVLocal servicios=null;

		public RecursosTVDelegate() throws ATiempoAppEx {
				try {
					servicios = ((RecursosTVLocalHome) HomeFactory.getHome(RecursosTVLocalHome.JNDI_NAME)).create();
				} catch (NamingException nex) {
					throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
				} catch (CreateException cex) {
					throw new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
				}
			}

		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.soltec.recursos.RecursosTVInterfaces#solicitudInformacionTecnica(java.lang.String, java.lang.String)
		 */
		public void solicitudInformacionTecnica(String peticion, String id_actividad) throws ATiempoAppEx {
			// TODO POR IMPLEMENTAR
			servicios.solicitudInformacionTecnica(peticion,id_actividad);
		}

		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.soltec.recursos.RecursosTVInterfaces#recepcionInformacionTecnica(co.com.telefonica.atiempo.interfaces.atiempo.TR019S)
		 */
		public void recepcionInformacionTecnica(TR019S tr019s) throws ATiempoAppEx {
			// TODO POR IMPLEMENTAR
			servicios.recepcionInformacionTecnica(tr019s);
		}

		/* (no Javadoc)
		 * @see co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVInterfaces#enviaActualizacionInventarioTV(long, java.lang.String)
		 */
		public boolean enviaActualizacionInventarioTV(long idPeticion, String idActividad) throws ATiempoAppEx {
			return servicios.enviaActualizacionInventarioTV(idPeticion,idActividad);
		}

		/* (no Javadoc)
		 * @see co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVInterfaces#recibeActualizacionInventarioTV(co.com.telefonica.atiempo.interfaces.atiempo.TR018S)
		 */
		public void recibeActualizacionInventarioTV(TR018S tr018s) throws ATiempoAppEx {
			servicios.recibeActualizacionInventarioTV(tr018s);
		}
//TODO PVR se agrego el tipo de deco
		public Long enviaDecoTarjetaSTPorUtilizar (long idPeticion, String ult4digitosTarjeta, String ult4digitosDeco, long idContratista,String tipoDeco) throws ATiempoAppEx
		{
			return servicios.enviaDecoTarjetaSTPorUtilizar(idPeticion, ult4digitosTarjeta, ult4digitosDeco, idContratista,tipoDeco) ;
		}

		public TR016S buscarRespuestaMensaje (Long idPeticion, Long idMensaje) throws ATiempoAppEx
		{
			return servicios.buscarRespuestaMensaje (idPeticion, idMensaje) ;
		}

		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVInterfaces#getListaDecoTarjetas(java.lang.Long)
		 */
		public Collection getListaDecoTarjetas(Long idPeticion) throws ATiempoAppEx {
			// TODO Auto-generated method stub
			return servicios.getListaDecoTarjetas(idPeticion);
		}

		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVInterfaces#enviarActivacionCI(java.lang.Long, java.util.ArrayList, java.util.ArrayList, java.util.ArrayList)
		 */
		public Long enviarActivacionCI(Long idPeticion, ArrayList listaActivar,ArrayList listaEliminar) throws ATiempoAppEx{
//		public Long enviarActivacionCI(Long idPeticion, ArrayList listaEq, ArrayList listaParesxel, ArrayList listaParesyael) throws ATiempoAppEx {
			return servicios.enviarActivacionCI(idPeticion,listaActivar,listaEliminar);
		}

		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVInterfaces#enviaRefrescoPares(java.lang.Long)
		 */
		public void enviaRefrescoPares(Long nroPeticion) throws ATiempoAppEx {
			servicios.enviaRefrescoPares(nroPeticion);
			
		}

		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVInterfaces#actualizaDecoTarjetaSTPorUtilizar(co.com.telefonica.atiempo.interfaces.atiempo.TR016S)
		 */
		public void actualizaDecoTarjetaSTPorUtilizar(TR016S tr016s) throws ATiempoAppEx {
			servicios.actualizaDecoTarjetaSTPorUtilizar(tr016s);
		}

		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVInterfaces#actualizaConfiguracionServiciosTV(co.com.telefonica.atiempo.interfaces.atiempo.TR017S)
		 */
		public void actualizaConfiguracionServiciosTV(TR017S tr017s) throws ATiempoAppEx {
			servicios.actualizaConfiguracionServiciosTV(tr017s);
		}

		/* (non-Javadoc)
		 * @see co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVInterfaces#hayListaDecoTarjetasConfiguradas(java.lang.Long)
		 */
//		public boolean hayListaDecoTarjetasConfiguradas(Long idPeticion) throws ATiempoAppEx {
//			// TODO Auto-generated method stub
//			return servicios.hayListaDecoTarjetasConfiguradas(idPeticion);
//		}
		
		//DAVID: Se adiciona el siguiente método para agenda SC (Ya estaba en el bean)
		public Long enviaConfiguracionServiciosTVSoloEq(long idPeticion) throws ATiempoAppEx{
			return servicios.enviaConfiguracionServiciosTVSoloEq(idPeticion);
		}
		//	DAVID: Se adiciona el siguiente método para agenda SC
		public Long enviaConfiguracionServiciosTVSoloEqAgendaSC(long idPeticion) throws ATiempoAppEx{
			return servicios.enviaConfiguracionServiciosTVSoloEqAgendaSC(idPeticion);
		}
		
		public Long enviaConfiguracionServiciosTVSoloEqAgendaSC(long idPeticion, ArrayList decosAInstalar) throws ATiempoAppEx{
			return servicios.enviaConfiguracionServiciosTVSoloEqAgendaSC(idPeticion, decosAInstalar);
		}

		/* (sin Javadoc)
		 * @see co.com.telefonica.atiempo.soltec.serviciostv.RecursosTVInterfaces#isTV(java.lang.Long)
		 */
		public boolean isIdpcTV(Long idPeticion) throws ATiempoAppEx {
			return servicios.isIdpcTV(idPeticion);
		}

}
