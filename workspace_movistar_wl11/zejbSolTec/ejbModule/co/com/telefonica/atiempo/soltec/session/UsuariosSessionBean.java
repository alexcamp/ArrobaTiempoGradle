package co.com.telefonica.atiempo.soltec.session;

/**
 * Bean implementation class for Enterprise Bean: UsuariosSession
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 18, 2009
public class UsuariosSessionBean implements javax.ejb.SessionBean {
	private javax.ejb.SessionContext mySessionCtx;
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
	
	/*
	public List getAllUsuario(){
			String idUsuario = null;
			List listaUsuarios  =  new ArrayList(); 
			try {
				UsuarioLocalHome usuaHome = (UsuarioLocalHome)HomeFactory.getHome(UsuarioLocalHome.JNDI_NAME);
				Collection col;
			
				try {
					col = usuaHome.findAll();
					for (Iterator it = col.iterator(); it.hasNext(); ) {
					
						UsuarioLocal hsHome  = (UsuarioLocal) it.next();
						UsuarioDTO usDto = new UsuarioDTO();
						
						usDto.setUsuaNombre(hsHome.getUsua_nombre());	
											
					//	UsuarioKey usuarioKey=(UsuarioKey) hsHome.getPrimaryKey();
					//	usDto.setUsuaId(usuarioKey.usua_id);
					//	usDto.setUsuaNombre(hsHome.getUsua_nombre());
						listaUsuarios.add(usDto);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return listaUsuarios;
		}
		*/
}
