package co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import co.com.telefonica.atiempo.soltec.dto.TipoCodigoDto;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tipo_codigoKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tipo_codigoLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Tipo_codigoLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 18, 2009
/**
 * Bean implementation class for Enterprise Bean: TipoCodigoSt
 */
public class TipoCodigoStBean implements javax.ejb.SessionBean {
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
	
	public TipoCodigoDto getTipoCodigo(Integer idTipoCod) throws FinderException{
		
		TipoCodigoDto tipoCod = new TipoCodigoDto();
		
		try {
			
			Tipo_codigoLocalHome tipo_codigoLocalHome = (Tipo_codigoLocalHome) HomeFactory.getHome(Tipo_codigoLocalHome.JNDI_NAME);
			Tipo_codigoLocal tipo_codigoLocal = tipo_codigoLocalHome.findByPrimaryKey(new Tipo_codigoKey(idTipoCod));
			
			tipoCod.setTipo((Integer) tipo_codigoLocal.getPrimaryKey());
			tipoCod.setDescripcion(tipo_codigoLocal.getDescripcion());
			tipoCod.setAtributo(tipo_codigoLocal.getAtributo());
	
		} catch (NamingException e) {
		}
		
		return tipoCod;
	}
	
	public TipoCodigoDto getTipoCodigoByAtributo(String atributo)throws FinderException{
		
		TipoCodigoDto tipoCodigoDto = new TipoCodigoDto();
		
		try{
			
			Tipo_codigoLocalHome tipo_codigoLocalHome = (Tipo_codigoLocalHome) HomeFactory.getHome(Tipo_codigoLocalHome.JNDI_NAME);
			Tipo_codigoLocal tipo_codigoLocal = tipo_codigoLocalHome.findByAtributo(atributo);
			
			Tipo_codigoKey tipo_codigoKey = (Tipo_codigoKey)tipo_codigoLocal.getPrimaryKey();
			tipoCodigoDto.setTipo(tipo_codigoKey.tipo);
			tipoCodigoDto.setDescripcion(tipo_codigoLocal.getDescripcion());
			tipoCodigoDto.setAtributo(tipo_codigoLocal.getAtributo());
			
		}catch(Exception e){
		}
		
		return tipoCodigoDto;
		
	}
}
