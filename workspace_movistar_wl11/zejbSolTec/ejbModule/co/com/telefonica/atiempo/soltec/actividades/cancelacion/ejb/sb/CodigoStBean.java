package co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb;

import javax.ejb.FinderException;
import javax.ejb.ObjectNotFoundException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.soltec.dto.CodigoStDto;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_stKey;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_stLocal;
import co.com.telefonica.atiempo.soltec.ejb.eb.Codigo_stLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;



/**
 * Bean implementation class for Enterprise Bean: CodigoSt
 */
//TCS - CR25138 - Eliminacion de Repetitive calls of same method for same information - dregueira - May 20, 2009
public class CodigoStBean implements javax.ejb.SessionBean {
	
	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
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
	
	public CodigoStDto getCodigoSt(Long idCodSt) throws FinderException{
		CodigoStDto codigoStDto = new CodigoStDto();
		
		try {
			Codigo_stLocalHome codigo_stLocalHome = (Codigo_stLocalHome)HomeFactory.getHome(Codigo_stLocalHome.JNDI_NAME);
			Codigo_stLocal codigo_stLocal = codigo_stLocalHome.findByPrimaryKey(new Codigo_stKey(idCodSt));
			
			codigoStDto.setCorrelativo((Long) codigo_stLocal.getPrimaryKey());
			codigoStDto.setTipo(codigo_stLocal.getTipo());
			codigoStDto.setDescripcion(codigo_stLocal.getDescripcion());
			codigoStDto.setCodigo(codigo_stLocal.getCodigo());

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return codigoStDto;
	}
	
	public CodigoStDto getCodigoStByTipoAndCodigo(Integer tipo,String codigo)throws FinderException{
		
		CodigoStDto codigoStDto = new CodigoStDto();
		
		try{
			Codigo_stLocalHome codigo_stLocalHome = (Codigo_stLocalHome)HomeFactory.getHome(Codigo_stLocalHome.JNDI_NAME);
			Codigo_stLocal codigo_stLocal = null;
			
			try{
				
				codigo_stLocal = codigo_stLocalHome.findByTipoAndAtributo(tipo,codigo);
				
			}catch (ObjectNotFoundException e) {
				log.debug("No se encontro Codigo ST");
			}
			
			if(codigo_stLocal != null){
			
			Codigo_stKey codigo_stKey = (Codigo_stKey) codigo_stLocal.getPrimaryKey();
			
			codigoStDto.setCorrelativo(codigo_stKey.correlativo);
			codigoStDto.setTipo(codigo_stLocal.getTipo());
			codigoStDto.setDescripcion(codigo_stLocal.getDescripcion());
			codigoStDto.setCodigo(codigo_stLocal.getCodigo());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return codigoStDto;		
		
	}
}
