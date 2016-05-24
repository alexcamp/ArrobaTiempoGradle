package co.com.telefonica.atiempo.soltec.actividades.cancelacion.ejb.sb;
import javax.ejb.FinderException;

import co.com.telefonica.atiempo.soltec.dto.CodigoStDto;
/**
 * Local interface for Enterprise Bean: CodigoSt
 */
public interface CodigoStLocal extends javax.ejb.EJBLocalObject {
	public CodigoStDto getCodigoSt(Long idCodSt) throws FinderException;
	public CodigoStDto getCodigoStByTipoAndCodigo(Integer tipo, String codigo)
		throws FinderException;
}
