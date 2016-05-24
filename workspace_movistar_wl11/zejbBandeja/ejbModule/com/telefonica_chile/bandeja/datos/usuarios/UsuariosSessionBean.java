package com.telefonica_chile.bandeja.datos.usuarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.FinderException;
import javax.ejb.Stateless;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.AplicacionLocal;
import co.com.telefonica.atiempo.ejb.eb.Campo_variableKey;
import co.com.telefonica.atiempo.ejb.eb.Campo_variableLocal;
import co.com.telefonica.atiempo.ejb.eb.Habilidad_usuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.Habilidad_usuarioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Jer_usuario_rolLocal;
import co.com.telefonica.atiempo.ejb.eb.Jer_usuario_rolLocalHome;
import co.com.telefonica.atiempo.ejb.eb.RolKey;
import co.com.telefonica.atiempo.ejb.eb.RolLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Usuario_rolLocal;
import co.com.telefonica.atiempo.ejb.eb.Usuario_tokenKey;
import co.com.telefonica.atiempo.ejb.eb.Usuario_tokenLocal;
import co.com.telefonica.atiempo.ejb.eb.Usuario_tokenLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Valor_variableKey;
import co.com.telefonica.atiempo.ejb.eb.Valor_variableLocal;
import co.com.telefonica.atiempo.ejb.eb.Valor_variableLocalHome;

import com.telefonica_chile.atiempo.utiles.ApplicationConfig;
import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * Bean implementation class for Enterprise Bean: UsuariosSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class UsuariosSessionBean implements javax.ejb.SessionBean
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2322529950461716149L;
	private UsuarioLocalHome uhome;
	private Usuario_tokenLocalHome uthome;
	private javax.ejb.SessionContext mySessionCtx;
	
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected transient Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * getSessionContext
	 */
	public javax.ejb.SessionContext getSessionContext() {
		return mySessionCtx;
	}
	/**
	 * setSessionContext
	 */
	public void setSessionContext(javax.ejb.SessionContext ctx) {
		mySessionCtx = ctx;
	}
	/**
	 * ejbCreate
	 */
	public void ejbCreate() throws javax.ejb.CreateException {
	}
	/**
	 * ejbActivate
	 */
	public void ejbActivate() {
	}
	/**
	 * ejbPassivate
	 */
	public void ejbPassivate() {
	}
	/**
	 * ejbRemove
	 */
	public void ejbRemove() {
	}

	public ArrayList recuperarRolesUsuario(Long usuaId) throws UsuariosException {
		UsuarioHelper helper = new UsuarioHelper();
		return helper.recuperarRoles(usuaId);
	}
	
	/**
	 * Recupera la lista de supervisores de un rol dado.
	 * 
	 * @param rolId codigo del rol
	 * @return una lista de UsuarioDTO, o una lista vacia si no se encontraron supervisores para el rol.
	 * @throws UsuariosException en caso de algun error.
	 * @author amartoq
	 */
	public List recuperarSupervisoresRol(Long rolId) throws UsuariosException {
		UsuarioHelper helper = new UsuarioHelper();
		return helper.recuperarSupervisoresRol(rolId);
	}

	public ArrayList recuperarMenusUsuario(Long usuaId) throws UsuariosException {
		UsuarioHelper helper = new UsuarioHelper();
		return helper.recuperaMenues(usuaId);
	}


	public HashMap getUsuariosByHabilidad(Long idHabilidad) {
		ArrayList lista = new ArrayList();
		HashMap hUsers = new HashMap();
		
		try {
			Habilidad_usuarioLocalHome hHome = (Habilidad_usuarioLocalHome) HomeFactory.getHome(Habilidad_usuarioLocalHome.JNDI_NAME);
			Collection c = hHome.findUsuariosByHabilidad( idHabilidad );
			//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 18, 2009
			Habilidad_usuarioLocal hLocal = null;
			UsuarioKey usuarioKey = null;
			HashMap hAux = null;
			for (Iterator it=c.iterator(); it.hasNext(); ) {
				hLocal = (Habilidad_usuarioLocal) it.next();
				if ( hLocal.getHusu_valor() == null || "".equals( hLocal.getHusu_valor() ) )
					continue;
				usuarioKey=(UsuarioKey) hLocal.getFk_usua_2_husa().getPrimaryKey();
				hAux = (HashMap) hUsers.get ( usuarioKey.usua_id );
				if ( hAux == null )
					hAux = new HashMap();
			
				hAux.put( hLocal.getHusu_valor(), "1" );
				
				hUsers.put( usuarioKey.usua_id, hAux );
			}
		} catch (NamingException e) {
			log.error("NamingException. Buscando Usuarios para Habilidad [" + idHabilidad + "].");
		} catch (FinderException e) {
			log.error("FinderException. Buscando Usuarios para Habilidad [" + idHabilidad + "]: " + e.getMessage());
		}

		return hUsers;
		
	}

	public ArrayList recuperarValoresVariables(Long usuaId, ArrayList listIdBi) throws UsuariosException {
		UsuarioHelper helper = new UsuarioHelper();
////		return helper.recuperarValoresVariables(usuaId);		
		ArrayList listadoCampos = new ArrayList();
		
		UsuarioLocal usuarioLocal;
		try {
			UsuarioLocalHome usuarioHome = (UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			UsuarioKey usuarioKey=new UsuarioKey(usuaId);
			usuarioLocal = usuarioHome.findByPrimaryKey(usuarioKey);
			
			Valor_variableLocalHome vvHome = (Valor_variableLocalHome)HomeFactory.getHome(Valor_variableLocalHome.JNDI_NAME);
			Collection campos = usuarioLocal.getCampo_usuario();
		
			//Por cada uno de los roles del usuario busco si tienen campos variables asociados (AL ROL)
			// Si tiene, rescato la lista y compruebo que cada elemento no exista previamente entre los campos variables asociados al usuario
			// Si no existe previamente, lo agrego a la collection maestra de campos.
			Collection camposRoles = null;
			Collection roles = usuarioLocal.getUsuario_rol();
			Usuario_rolLocal uRolLocal = null;
			RolLocal rolEntityLocal = null;
			Campo_variableLocal campoVariableEntityLocal = null;
			for (Iterator it = roles.iterator(); it.hasNext();) {
				uRolLocal = (Usuario_rolLocal)it.next();
				rolEntityLocal = uRolLocal.getFk_usuarol_rol();

				if (rolEntityLocal.getCampovariableentity()!=null){
					camposRoles = rolEntityLocal.getCampovariableentity();
					for (Iterator it2 = camposRoles.iterator(); it2.hasNext(); ){
						campoVariableEntityLocal = (Campo_variableLocal)it2.next();
						if (!campos.contains(campoVariableEntityLocal)) campos.add(campoVariableEntityLocal);
					}
				}
			}
				 // Fin bloque extra�o		
		
			Campo_variableLocal campoEjb = null;
			ValorVariableDTO vv = null;
			Campo_variableKey key = null;
			Collection vvCollection = null;
			Valor_variableLocal valorEjb = null;
			Valor_variableKey valorKey = null;
			for (Iterator iter = campos.iterator(); iter.hasNext();) {
				campoEjb = (Campo_variableLocal) iter.next();

				vv = new ValorVariableDTO();
				vv.setNombre(campoEjb.getCv_nombre_ext());
				
				int i=0;
				int j;
				for (j=0;j<listIdBi.size();j++){
					key = (Campo_variableKey)campoEjb.getPrimaryKey();
					vvCollection = vvHome.findByNombreAndIdBi(key.cv_id.intValue(),(Long)listIdBi.get(j));
					for (Iterator iterator = vvCollection.iterator();iterator.hasNext();) {				
						valorEjb = (Valor_variableLocal) iterator.next();
						valorKey = (Valor_variableKey)valorEjb.getPrimaryKey();
						vv.setValorPorPeticion(valorKey.fk_valor_bi_bi_id,valorEjb.getValor());
						i++;
					}
				}	 
				listadoCampos.add(vv);				
			}		
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (FinderException e) {
			log.info("No se encontr� registro para Usuario con ID = "+usuaId);
		}
		return listadoCampos;
	}
	
	public ArrayList recuperarValoresVariables(Long usuaId) throws UsuariosException {
//		UsuarioHelper helper = new UsuarioHelper();
//		return helper.recuperarValoresVariables(usuaId);
		ArrayList listadoCampos = new ArrayList();
		
		UsuarioLocal usuarioLocal;
		try {
			UsuarioLocalHome usuarioHome = (UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			usuarioLocal = usuarioHome.findByPrimaryKey(new UsuarioKey(usuaId));
			Collection campos = usuarioLocal.getCampos(); 
		
			Campo_variableLocal campoEjb = null;
			ValorVariableDTO vv = null;
			Collection vvCollection = null;
			Valor_variableLocal valorEjb = null;
			Valor_variableKey valorKey = null;
			for (Iterator iter = campos.iterator(); iter.hasNext();) {
				campoEjb = (Campo_variableLocal) iter.next();

				vv = new ValorVariableDTO();
				vv.setNombre(campoEjb.getCv_nombre_ext());
//				log.debug("NOMBRE VALOR VARIABLE=>"+vv.getNombre());
				
				vvCollection = campoEjb.getValor_variable();
			
				int i = 0;
				for (Iterator iterator = vvCollection.iterator();iterator.hasNext();) {
				
//					log.debug("INTERATOR N�"+i+" VV ");
					valorEjb = (Valor_variableLocal) iterator.next();
					valorKey = (Valor_variableKey)valorEjb.getPrimaryKey();
					vv.setValorPorPeticion(valorKey.fk_valor_bi_bi_id,valorEjb.getValor());
					i++;
				}

				listadoCampos.add(vv);				
			}		
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (FinderException e) {
			log.info("No se encontr� registro para Usuario con ID = "+usuaId);
		}
		return listadoCampos;	
	}

	public UsuarioDTO retornaUsuario(Long usuaId) throws UsuariosException {
		UsuarioHelper helper = new UsuarioHelper();
		return helper.recuperarUsuario(usuaId);
	}

	public UsuarioDTO retornaUsuario(String login,String newToken) throws UsuariosException {
		try {
			if(uhome==null)
				uhome = (UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			if(uthome==null)
				uthome=(Usuario_tokenLocalHome) HomeFactory.getHome(Usuario_tokenLocalHome.JNDI_NAME);
			UsuarioLocal uLocal = uhome.findByLogin(login);
			Usuario_tokenLocal token=null;
			UsuarioKey userKey = (UsuarioKey) uLocal.getPrimaryKey();
			UsuarioDTO usuario = new UsuarioDTO( userKey.usua_id );
			try
			{
				String validaUsuarioToKen=ApplicationConfig.getVariable("VAL_USUA_TOKEN");
				if(validaUsuarioToKen!=null && validaUsuarioToKen.equals("Y"))
				{
					token=uthome.findByPrimaryKey(new Usuario_tokenKey(login.toUpperCase()));
					String tok=login+"_"+newToken;
					if(!tok.equals(token.getUsua_token()))
					{
						//log.debug("no es igual");
					usuario.setTokenValido(false);
					}
					else
					{
					  //log.debug("es igual");
					  usuario.setTokenValido(true);
					}
				}
				else
					usuario.setTokenValido(true);
			}
			catch(FinderException fe)
			{
				//log.debug("NO SE ENCONTRO TOKEN:"+login+" "+newToken);
				try
				{
					token=uthome.create(login.toUpperCase());
					token.setUsua_token(login+"_"+newToken);
					usuario.setTokenValido(true);
				}
				catch(CreateException e1)
				{
					log.error("CreateException",e1);
					throw new UsuariosException(e1);
				}
			}
			usuario.setUsername( uLocal.getUsua_login());			
			usuario.setRut( uLocal.getUsua_rut());
			usuario.setEmail( uLocal.getUsua_email());
			usuario.setCargo( uLocal.getUsua_cargo());
			usuario.setNombre( uLocal.getUsua_nombre());
			//log.debug("FIN RETORNA USUARIO");
			return usuario;
		} catch (EJBException e) {
			log.error("Error tratando de recuperar el usuario Loggeado " + login, e);
			throw new UsuariosException("Error tratando de recuperar el usuario Loggeado " + login, e);
		} catch (NamingException e) {
			log.error("Error tratando de recuperar el usuario Loggeado " + login, e);
			throw new UsuariosException("Error tratando de recuperar el usuario Loggeado " + login, e);
		} catch (FinderException e) {
			log.error("Error tratando de recuperar el usuario Loggeado " + login, e);
			throw new UsuariosException("Error tratando de recuperar el usuario Loggeado " + login, e);
		}
		
	}

	/*
	public ArrayList recuperarSegmentos() throws UsuariosException {
		Collection listadoSegmentosEjb = new UsuarioHelper().getSegmentosEntity();

		ArrayList listadoSegmentos = new ArrayList();
		for (Iterator iter = listadoSegmentosEjb.iterator(); iter.hasNext();) {
			SegmentoLocal objSegmentoEjb = (SegmentoLocal) iter.next();

			SegmentoDTO objSegmento = new SegmentoDTO();
			SegmentoKey segmentoKey=(SegmentoKey) objSegmentoEjb.getPrimaryKey();
			objSegmento.setSegmId(segmentoKey.segm_id );
			objSegmento.setSegmDescripcion(objSegmentoEjb.getSegm_descripcion());

			listadoSegmentos.add(objSegmento);
		}
		return listadoSegmentos;

	}
	*/

	public boolean actualizarEstadoUsuario(Long idUsuario,String habilitado) {
		UsuarioHelper helper = new UsuarioHelper();
		return (helper.updateEstadoUsuario(idUsuario, habilitado));
	}
	public boolean actualizarEstadoRolUsuario(Long idUsuario, Long rol,String habilitado) {
		UsuarioHelper helper = new UsuarioHelper();
		return (helper.updateEstadoRolUsuario(idUsuario,rol,habilitado));
	}	

	public void reasignarPeticion(Long idUsuario, Long idPeticion) {
		UsuarioHelper helper = new UsuarioHelper();

	}

	/**
	 * Lista Usuarios de acuerdo al Rol entregado por par�metro 
	 **/
	
	/*
	public Collection obtenerUsuariosPorRol(Long idRol) throws UsuariosException {
		
		//ArrayList listadoUsuarios = new ArrayList();
		Collection listadoUsuarios = new Vector();
		try {
			UsuarioLocalHome home =
				(UsuarioLocalHome) HomeFactory.getHome(
			UsuarioLocalHome.JNDI_NAME);
			Collection usuarios = home.findAll();
		
			for (Iterator iter = usuarios.iterator(); iter.hasNext();) {
				UsuarioLocal usuario = (UsuarioLocal) iter.next();
				Collection rolesUsuario = usuario.getUsuario_rol();
				boolean ok = false;
				for (Iterator iterator = rolesUsuario.iterator();iterator.hasNext();) {
					RolLocal rol = (RolLocal) iterator.next();
					RolKey rolKey=(RolKey) rol.getPrimaryKey();
					if (rolKey.rol_id.equals(idRol)){
						ok = true;
					}
				}
				if (ok){
					UsuarioDTO usuarioDTO = new UsuarioDTO();
					UsuarioKey usuarioKey=(UsuarioKey) usuario.getPrimaryKey();
					usuarioDTO.setId(usuarioKey.usua_id );
					usuarioDTO.setNombre(usuario.getUsua_nombre());
					listadoUsuarios.add(usuarioDTO);
				}
			}
		} catch (NamingException e) {
			log.error("Error tratando de recuperar los usuarios con Rol " + idRol, e);
		} catch (FinderException e) {
			log.error("Error tratando de recuperar los usuarios con Rol " + idRol, e);
		}
		
		return listadoUsuarios;
	}
	*/
	
	public ArrayList recuperaRolesUsuariosQueSuperviso(Long usuarioSupId) throws UsuariosException
	{
		try
		{
			ArrayList retorno=new ArrayList();
			ArrayList roles=new ArrayList();
			Jer_usuario_rolLocalHome home=(Jer_usuario_rolLocalHome) HomeFactory.getHome(Jer_usuario_rolLocalHome.JNDI_NAME);
			Collection listaRoles=home.findSupervisados(usuarioSupId);
			Jer_usuario_rolLocal local =  null;
			RolLocal rolLocal = null;
			RolKey rolKey = null;
			RolDTO rol = null;
			AplicacionLocal aplicacionLocal = null;
			for(Iterator iterator=listaRoles.iterator();iterator.hasNext();)
			{
				local =(Jer_usuario_rolLocal) iterator.next();
				rolLocal=local.getFk_jerrol_rol();
				rolKey=(RolKey) rolLocal.getPrimaryKey();
				rol = new RolDTO();
				rol.setId(rolKey.rol_id);
				rol.setCodigo(rolLocal.getRol_codigo());
				rol.setNombre(rolLocal.getRol_nombre());				
				aplicacionLocal = rolLocal.getFk_ap_2_rol();
				if (aplicacionLocal != null)
				{
					rol.setPushApp(aplicacionLocal.getAp_nombre());
				}
				if(!roles.contains(rolKey.rol_id))
				{
					retorno.add(rol);
					roles.add(rolKey.rol_id);
				}
			}
			Collections.sort(retorno);
			return retorno;
		}
		catch(Exception e)
		{
			log.debug(e);
			return new ArrayList();
		}
	}
	
	public void deleteToKen(String subTok) throws UsuariosException
	{
		try
		{	
			if(uthome==null)
				uthome=(Usuario_tokenLocalHome) HomeFactory.getHome(Usuario_tokenLocalHome.JNDI_NAME);
			Collection lista=uthome.findBySubTok("%"+subTok);
			for(Iterator iterator=lista.iterator();iterator.hasNext();)
			{
				Usuario_tokenLocal local=(Usuario_tokenLocal) iterator.next();
				local.remove();
			}
		}
		catch(FinderException exception)
		{
			log.debug("No se puede encontrar el subTok:"+subTok);
		}
		catch(Exception lo)
		{
			throw new UsuariosException(lo);
		}
	}
	
	public void updateToKen(String string,String newToken) throws UsuariosException
	{
		try
		{	
			if(uthome==null)
				uthome=(Usuario_tokenLocalHome) HomeFactory.getHome(Usuario_tokenLocalHome.JNDI_NAME);
			Usuario_tokenLocal local=uthome.findByPrimaryKey(new Usuario_tokenKey(string));
			String tok=string+"_"+newToken;
			local.setUsua_token(tok);
		}
		catch(Exception lo)
		{
			throw new UsuariosException(lo);
		}
	}
}
