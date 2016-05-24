package com.telefonica_chile.comun.usuario.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.EmpresaKey;
import co.com.telefonica.atiempo.ejb.eb.Habilidad_usuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.Habilidad_usuarioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.TecnicoLocal;
import co.com.telefonica.atiempo.ejb.eb.TecnicoLocalHome;
import co.com.telefonica.atiempo.ejb.eb.UsuarioKey;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocal;
import co.com.telefonica.atiempo.ejb.eb.UsuarioLocalHome;
import co.com.telefonica.atiempo.ejb.eb.Usuario_rolLocal;
import co.com.telefonica.atiempo.ejb.eb.Usuario_rolLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;
import com.telefonica_chile.bandeja.datos.usuarios.RolDTO;
import com.telefonica_chile.bandeja.ejbutiles.UsuarioWeb;
import com.telefonica_chile.comun.asigna.dto.TecnicoDTO;
import com.telefonica_chile.comun.asigna.dto.usuario;
import com.telefonica_chile.comun.usuario.dto.UsuarioDTO;
import com.telefonica_chile.comun.vistaagencia.dto.vistaAgenciaDTO;

/**
 * Bean implementation class for Enterprise Bean: UsuarioSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class UsuarioSessionBean implements javax.ejb.SessionBean {
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

	HashMap hashCache = new HashMap();
	Date dateCache = null;

	public void refreshCache() {
		if (dateCache == null)
			dateCache = new Date();

		if ((new Date()).getTime() - dateCache.getTime() < 24 * 3600 * 1000)
			return;

		dateCache = null;
		hashCache = new HashMap();
	}

	public UsuarioDTO recuperaUsuario(Long idUsuario) {

		refreshCache();

		if (hashCache.containsKey("" + idUsuario))
			return (UsuarioDTO) hashCache.get("" + idUsuario);

		UsuarioDTO uDto = new UsuarioDTO();
		try {
			UsuarioLocalHome uHome = (UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			UsuarioKey uKey = new UsuarioKey(idUsuario);
			UsuarioLocal uLocal = uHome.findByPrimaryKey(uKey);
			uDto = new UsuarioDTO();
			uDto.setIdUsuario(idUsuario);
			uDto.setNombre(uLocal.getUsua_nombre());

			hashCache.put("" + idUsuario, uDto);
		} catch (NamingException e) {
		} catch (FinderException e) {
		}

		return uDto;
	}

	public ArrayList recuperaTecnicos(Long idUsuario, UsuarioWeb usuario, String nroPeticion){

		ArrayList listaTecnicos = new ArrayList();
		try {
			UsuarioLocalHome uHome = (UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			UsuarioKey uKey = new UsuarioKey(idUsuario);
			UsuarioLocal uLocal = uHome.findByPrimaryKey(uKey);
			if(uLocal.getEmpresa()==null)
				return listaTecnicos;
			TecnicoLocalHome tecnicoHome=(TecnicoLocalHome) HomeFactory.getHome(TecnicoLocalHome.JNDI_NAME);
			EmpresaKey empresaKey=(EmpresaKey) uLocal.getEmpresa().getPrimaryKey();
			Collection c=tecnicoHome.findByEmpresa(empresaKey.empr_id);
			
			//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 18, 2009
			TecnicoLocal tLocal = null;
			TecnicoDTO tDto = null;
			for (Iterator it = c.iterator(); it.hasNext();) {
				tLocal = (TecnicoLocal) it.next();
				tDto = new TecnicoDTO();
				tDto.setNombre( tLocal.getNombre() );
				tDto.setApellido( tLocal.getApellido() );
				tDto.setCodigo( tLocal.getCod_tecnico() );
				listaTecnicos.add(tDto);
			}
		} catch (NamingException e) {
		} catch (FinderException e) {
		}
		Collections.sort(listaTecnicos);
		return listaTecnicos;
	}

	public ArrayList obtenerUsuariosPorRol(Long idRol) {

		ArrayList usuarioList = new ArrayList();
		log.debug(" ArrayList obtenerUsuariosPorRol ");
		log.debug(" idRol  =>  " + idRol);

		//UsuarioDTO usuarioDto = new UsuarioDTO();	
		usuario usuarioDto = new usuario();

		Usuario_rolLocalHome usHome = null;
		Usuario_rolLocal usLocal = null;
		Collection c;

		try {
			usHome = (Usuario_rolLocalHome) HomeFactory.getHome(Usuario_rolLocalHome.JNDI_NAME);
			c = (Collection) usHome.findByRol(idRol);
			Usuario_rolLocal element = null;
			UsuarioKey usuarioKey = null;
			for (Iterator iter = c.iterator(); iter.hasNext();) {
				usuarioDto = new usuario();
				element = (Usuario_rolLocal) iter.next();
				usuarioKey = (UsuarioKey) element.getFk_usuarol_usua().getPrimaryKey();
				usuarioDto.setUsuarioId(usuarioKey.usua_id);
				usuarioDto.setUsuarioIdca(element.getFk_usuarol_usua().getUsua_idca());
				if (element.getFk_supe_2_usro() != null)
					usuarioDto.setUsuarioIdSupervisor((Long) element.getFk_supe_2_usro().getPrimaryKey());

				usuarioList.add(usuarioDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return usuarioList;
	}

	public ArrayList getIdNombreUsuarios(Long idRol) {

		ArrayList usuarioList = new ArrayList();
		log.debug(" ArrayList getIdNombreUsuarios ");
		log.debug(" idRol  =>  " + idRol);

		//UsuarioDTO usuarioDto = new UsuarioDTO();	
		usuario usuarioDto = new usuario();

		Usuario_rolLocalHome usHome = null;
		Usuario_rolLocal usLocal = null;
		Collection c;
		ArrayList lista = new ArrayList();
		try {
			usHome = (Usuario_rolLocalHome) HomeFactory.getHome(Usuario_rolLocalHome.JNDI_NAME);
			c = (Collection) usHome.findByRol(idRol);
			UsuarioLocalHome usuaHome = (UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			UsuarioLocal hsHome;
			Usuario_rolLocal element = null;
			UsuarioKey usuarioKey = null;
			UsuarioDTO usDto = null;
			for (Iterator iter = c.iterator(); iter.hasNext();) {
				usuarioDto = new usuario();
				element = (Usuario_rolLocal) iter.next();
				usuarioKey = (UsuarioKey) element.getFk_usuarol_usua().getPrimaryKey();
				hsHome = usuaHome.findByPrimaryKey(new UsuarioKey(usuarioKey.usua_id));
				usDto = new UsuarioDTO();
				usDto.setIdUsuario((Long) hsHome.getPrimaryKey());
				usDto.setNombre(hsHome.getUsua_nombre());
				lista.add(usDto);

			}
		} catch (Exception e) {
			log.warn("Exception",e);
			return null;
		}
		return lista;
	}

	public usuario getUsuarioByIdca(String idca) {
		Long idUsuario = null;
		usuario usDto = new usuario();
		try {
			UsuarioLocalHome usuaHome = (UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			UsuarioLocal usuaLocal = usuaHome.findByIdca(idca);
			UsuarioKey usuarioKey = (UsuarioKey) usuaLocal.getPrimaryKey();
			idUsuario = (usuarioKey.usua_id);
			usDto.setUsuarioId(idUsuario);
			usDto.setUsuarioIdca(idca);
			//TODO: Por revisar
			//usDto.setCodigoContratista(usuaLocal.getEmpresa().getCodigo());
		} catch (NamingException e1) {
			log.error("Problema para recuperar JNDI de entity UsuarioLocalHome. MENSAJE==>" + e1, e1);
		} catch (FinderException e1) {
			log.warn("No se encontró registro en tabla USUARIO para IDCA = " + idca);
			return null;
		}
		return usDto;
	}

	public ArrayList generarLista(ArrayList usrSupervisados, Long idHabilidad) {
		ArrayList lista = new ArrayList();
		Collection col;
		int cor = 0;
		try {
			Habilidad_usuarioLocalHome home = (Habilidad_usuarioLocalHome) HomeFactory.getHome(Habilidad_usuarioLocalHome.JNDI_NAME);
			RolDTO u = null;
			Habilidad_usuarioLocal hsHome = null;
			for (Iterator itSup = usrSupervisados.iterator(); itSup.hasNext();) {
				u = (RolDTO) itSup.next();
				try {
					col = home.findUsuarios(u.getId(), idHabilidad);
					if (col.size() > 0) {
						for (Iterator it = col.iterator(); it.hasNext();) {
							hsHome = (Habilidad_usuarioLocal) it.next();
							vistaAgenciaDTO vista = new vistaAgenciaDTO();
							vistaAgenciaDTO dto = new vistaAgenciaDTO();
							vista.setAgenciaId(hsHome.getHusu_valor());
							vista.setRolId(u.getId());
							vista.setUsuaNombre(u.getNombre());
							lista.add(vista);
						}
					}
				} catch (FinderException e1) {
					e1.printStackTrace();
				}

				cor++;
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return lista;
	}

	public String getnombreUsuario(String idca) {
		String idUsuario = null;

		try {
			UsuarioLocalHome usuaHome = (UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			UsuarioLocal usuaLocal = usuaHome.findByIdca(idca);

			idUsuario = usuaLocal.getUsua_nombre();

		} catch (NamingException e1) {
			log.error("Problema para recuperar JNDI de entity UsuarioLocalHome. MENSAJE==>" + e1, e1);
		} catch (FinderException e1) {
			log.warn("No se encontró registro en tabla USUARIO para IDCA = " + idca);
			return null;
		}
		return idUsuario;
	}

	public String getNombreById(String idUsuario) {
		String NombreUsuario = null;

		try {
			UsuarioLocalHome usuaHome = (UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			UsuarioLocal usuaLocal = usuaHome.findByPrimaryKey(new UsuarioKey(new Long(idUsuario)));

			NombreUsuario = usuaLocal.getUsua_nombre();

		} catch (NamingException e1) {
			log.error("Problema para recuperar JNDI de entity UsuarioLocalHome. MENSAJE==>" + e1, e1);
		} catch (FinderException e1) {
			log.warn("No se encontró registro en tabla USUARIO para ID = " + idUsuario, e1);
			return null;
		}
		return NombreUsuario;
	}

	public ArrayList generarHabilidad(ArrayList usrSupervisados, Long Habilidad) {
		ArrayList lista = new ArrayList();
		Collection col;
		int cor = 0;
		try {
			Habilidad_usuarioLocalHome home = (Habilidad_usuarioLocalHome) HomeFactory.getHome(Habilidad_usuarioLocalHome.JNDI_NAME);
			RolDTO u = null;
			Habilidad_usuarioLocal hsHome = null;
			vistaAgenciaDTO vista = null;
			vistaAgenciaDTO dto = null;
			ArrayList aux = null;
			vistaAgenciaDTO dtoAux = null;
			for (Iterator itSup = usrSupervisados.iterator(); itSup.hasNext();) {
				u = (RolDTO) itSup.next();
				try {
					col = home.findUsuariosHabilidad();
					if (col.size() > 0) {
						for (Iterator it = col.iterator(); it.hasNext();) {
							hsHome = (Habilidad_usuarioLocal) it.next();
							vista = new vistaAgenciaDTO();
							dto = new vistaAgenciaDTO();
							vista.setAgenciaId(hsHome.getHusu_valor());
							String name = hsHome.getHusu_valor();
							vista.setRolId(u.getId());
							vista.setUsuaNombre(u.getNombre());
							lista.add(vista);
							aux = new ArrayList();
							dtoAux = new vistaAgenciaDTO();
						}
					}
				} catch (FinderException e1) {
					log.warn("No se encontraron habilidades",e1);
				}

				cor++;
			}
		} catch (NamingException e) {
			log.error("NamingException",e);
		}

		return lista;
	}

	/*
	public ArrayList getAllUsuario() {
		String idUsuario = null;
		ArrayList listaUsuarios = new ArrayList();
		try {
			UsuarioLocalHome usuaHome = (UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			Collection col;
			try {
				col = usuaHome.findAll();
				for (Iterator it = col.iterator(); it.hasNext();) {

					UsuarioLocal hsHome = (UsuarioLocal) it.next();
					UsuarioDTO usDto = new UsuarioDTO();
					UsuarioKey usuarioKey = (UsuarioKey) hsHome.getPrimaryKey();
					usDto.setIdUsuario(usuarioKey.usua_id);
					usDto.setNombre(hsHome.getUsua_nombre());
					listaUsuarios.add(usDto);
				}
			} catch (FinderException e) {
				log.warn("No pudo rescatar todos los usuarios. MENSAJE==>" + e.toString(), e);
			}

		} catch (NamingException e1) {
			log.error("Problema para recuperar JNDI de entity UsuarioLocalHome. MENSAJE==>" + e1, e1);
		}

		return listaUsuarios;
	}
*/

	public ArrayList getUsuarios(ArrayList listaUsers) {
		Long idUsuario = null;
		ArrayList listaUsuarios = new ArrayList();

		try {
			UsuarioLocalHome usHome = (UsuarioLocalHome) HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
			UsuarioLocal usLocal = null;
			UsuarioDTO usDto = null;
			for (int i = 0; i < listaUsers.size(); i++) {
				try {
					idUsuario = (Long) listaUsers.get(i);
					usLocal = usHome.findByPrimaryKey(new UsuarioKey(idUsuario));
					usDto = new UsuarioDTO();
					usDto.setIdUsuario(idUsuario);
					usDto.setNombre(usLocal.getUsua_nombre());

					listaUsuarios.add(usDto);
				} catch (FinderException e) {
					log.warn("No pudo Obtener Usuario [" + idUsuario + "]",e);
				}
			}

		} catch (NamingException e) {
			log.error("NamingException. No pudo Obtener Usuario [" + idUsuario + "]");
		}

		return listaUsuarios;
	}

}
