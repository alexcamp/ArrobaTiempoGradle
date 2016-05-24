package com.telefonica_chile.bandeja.web.acciones;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import co.com.atiempo.dto.BuscadorPeticionVpiDTO;
import co.com.atiempo.dto.PeticionDTO;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessDelegate;
import co.com.telefonica.atiempo.vpistbba.comun.business.InfoComunColombiaBusinessInterface;
import co.com.telefonica.atiempo.vpistbba.dto.InformacionEmpresaDTO;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesDelegate;
import co.com.telefonica.atiempo.vpistbba.peticiones.PeticionesInterfaces;

import com.tecnonautica.mvc.Accion;
import com.tecnonautica.mvc.Evento;
import com.tecnonautica.utiles.db.DBManager;
import com.telefonica_chile.atiempo.utiles.BuscadorPeticionUtiles;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.BandejaException;
import com.telefonica_chile.bandeja.torreControl.TorreControlSessionLocal;
import com.telefonica_chile.bandeja.torreControl.TorreControlSessionLocalHome;

public class VerBuscadorOutboundAcc extends Accion
{
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	protected void ejecutar(HttpServletRequest request) throws Evento
	{
		boolean error = false;
		BuscadorPeticionUtiles.cargarComboTipoPc(request);
		if(request.getParameter("folioAtis")==null && request.getParameter("rut")==null && 
				request.getParameter("rutDv")==null && request.getParameter("folioPc")==null)
			return;
		try
		{
			TorreControlSessionLocalHome home=(TorreControlSessionLocalHome) HomeFactory.getHome(TorreControlSessionLocalHome.JNDI_NAME);
			TorreControlSessionLocal local=home.create();
			Long folioAtis=null;
			if(request.getParameter("folioAtis")!=null && !request.getParameter("folioAtis").equals(""))
				folioAtis=new Long(request.getParameter("folioAtis"));
			
			String rut = request.getParameter("rut");
			String rutDv = request.getParameter("rutDv");
			
			// CR 00027016
			String folioPC = request.getParameter("folioPc");
			String typePc = request.getParameter("typePc");
			int idTypePc = 0;
			if (typePc != null && !typePc.equals("")){
				idTypePc = Integer.parseInt(typePc);
			}
			
			int codConsulta = BuscadorPeticionUtiles.obtenerCodigoConsulta(folioAtis,rut,rutDv,folioPC,idTypePc);
			log.debug("codConsulta = "+codConsulta);
			if (codConsulta!=-1){
				BuscadorPeticionVpiDTO buscadorPeticion = new BuscadorPeticionVpiDTO(folioAtis,rut,rutDv,folioPC,idTypePc);
				buscadorPeticion.setCodBusqueda(BuscadorPeticionUtiles.COD_BUSCADOR_OUTBOUND);
				buscadorPeticion.setJoinBIntegrada(true);
				buscadorPeticion.setIdBusqueda(codConsulta);
				
	//			ArrayList peticiones=local.listaBuscadorAvanzadoInBound(folioAtis,rut,rutDv);
				PeticionesInterfaces pInterface = new PeticionesDelegate();
				ArrayList peticiones = pInterface.buscarPeticiones(buscadorPeticion);
		
				request.setAttribute("peticiones",peticiones);
				
				if (peticiones.size() > 0){
					List listaSugerencias = BuscadorPeticionUtiles.mensajeSugerencias(buscadorPeticion);
					String msgResultado = BuscadorPeticionUtiles.mensajeResultado(buscadorPeticion);
					// Retornar Listado.
					request.setAttribute("msgResultado", msgResultado);
					request.setAttribute("listadoSugerencias", listaSugerencias);
					
					agregarCondiciones(peticiones,request);
				}else{
					String msg = BuscadorPeticionUtiles.mensajeResultadoVacio(buscadorPeticion);
					request.setAttribute("msgError",msg);				
				}
				
				cargarDatosEmpresa(request);
			}
			else{
				error=true;
			}

		}
		catch (SQLException e)
		{
			error = true;
			log.error("SqlException",e);
		}
		catch (NamingException e)
		{
			error = true;
			log.error("NamingException",e);
		}
		catch (CreateException e)
		{
			error = true;
			log.error("CreateException",e);
		}
		catch (ATiempoAppEx e)
		{
			error = true;
			log.error("ATiempoAppEx",e);
		}
		catch (NumberFormatException nf)
		{
			error = true;
			log.error("NumberFormat",nf);
		}finally{
			if (error){
				String msg = "Ha ocurrido un error en la ejecución de la busqueda.";
				request.setAttribute("msgError",msg);
			}
		}
		
	}
	
	private void agregarCondiciones(List peticiones,HttpServletRequest request) throws SQLException{
		String usuarioLogueado = (request.getUserPrincipal()==null?"":request.getUserPrincipal().getName()).toUpperCase();
		for (Iterator iter = peticiones.iterator(); iter.hasNext();) {
			PeticionDTO peticion = (PeticionDTO) iter.next();
			if(peticion.getPetiNumero()!=null){
				String usuarioReintetosPGC = buscarUsuarioReintentoPGC().toUpperCase();
				String usuarioBitacora = buscarUsuarioBitacora(peticion.getPetiNumero()).toUpperCase();
				//Sí todos los usuarios son iguales true
				//Sí todos los usuarios son diferentes true
				//Sí usuarioLogueado=usuarioBitacora y usuarioLogueado !=usuarioReintetosPGC
				//Sí el usuarioLogueado!=usuariobitacora y usuarioLoqueado=usuarioReintentosPGC
				boolean resultado = (usuarioLogueado.equals(usuarioReintetosPGC) && usuarioLogueado.equals(usuarioBitacora))
					|| (!usuarioLogueado.equals(usuarioReintetosPGC) && !usuarioLogueado.equals(usuarioBitacora) && ! usuarioReintetosPGC.equals(usuarioBitacora))
					|| (usuarioLogueado.equals(usuarioBitacora) && !usuarioLogueado.equals(usuarioReintetosPGC)
					|| (!usuarioLogueado.equals(usuarioBitacora) && usuarioLogueado.equals(usuarioReintetosPGC)));
				log.debug("resultado= "+ resultado+ ", Usuario logueado= "+ usuarioLogueado+", Usuario bitácora="+usuarioBitacora+", Usuario usuarioReintetosPGC= "+usuarioReintetosPGC);
				peticion.setEstaEnActividadPermitida(peticion.isEstaEnActividadPermitida() && resultado);
				log.debug("peticion.isEstaEnActividadPermitida="+peticion.isEstaEnActividadPermitida());
			}
		}
	}
	
	private String buscarUsuarioBitacora(Long petiNumero) throws SQLException{
		String sql = "SELECT U.USUA_LOGIN FROM VPISTBBA.BITACORA_PETICION BP INNER JOIN ATIEMPO.USUARIO U ON U.USUA_ID = BP.USUA_ID " +
		"WHERE BIPE_ID = (select max(BIPE_ID) from vpistbba.BITACORA_PETICION where PETI_NUMERO = " + petiNumero.longValue() +  " AND BIPE_FECHA_FIN IS NULL)";
		return buscarEscalar(sql);
	}
	
	private String buscarUsuarioReintentoPGC() throws SQLException{
		String sql = "select cast(VALOR as varchar(100)) from atiempo.PROPERTIES_BD where CODIGO = 'USUARIOS_REINTENTOS_PGC'";
		return buscarEscalar(sql);
	}
	
	private String buscarEscalar(String sql) throws SQLException{
		Connection con = null;
		String usuario = null;
		try{
			con = DBManager.getConnection(DBManager.JDBC_VPISTBBA);
			Statement stm = con.createStatement();
			log.debug("buscarEscalar con sql = "+sql);
			ResultSet rs = stm.executeQuery(sql);
			Object resultado = null;
			while(rs!=null && rs.next()){
				usuario = rs.getString(1);
			}
		}finally{
			if(con!=null){
				con.close();
			}
		}
		return usuario;
	}
	
	public void cargarDatosEmpresa(HttpServletRequest request)
	{
		if(request.getParameter("ID_USUARIO")!=null)
		{
			//log.debug("Cargando Datos de Empresa");
			try
			{
				Long idUsuario=new Long(request.getParameter("ID_USUARIO"));
				InfoComunColombiaBusinessInterface infoComunColombiaBI = new InfoComunColombiaBusinessDelegate();
				InformacionEmpresaDTO informacionEmpresaDTO = infoComunColombiaBI.obtenerInformacionEmpresa(idUsuario);
				request.setAttribute("datosEmpresa",informacionEmpresaDTO);
				//log.debug(informacionEmpresaDTO.toString());
			}
			catch (ATiempoAppEx e)
			{
				log.debug("NO Datos de Empresa Cargados");
				log.debug("ATiempoAppEx",e);
			}
	
			//log.debug("Datos de Empresa Cargados");
		}
		else
		{
			log.debug("No tengo el ID del usuario");
		}
	}
	
}
