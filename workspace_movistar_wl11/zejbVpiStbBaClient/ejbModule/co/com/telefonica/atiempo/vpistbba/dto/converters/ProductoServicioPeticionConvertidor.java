package co.com.telefonica.atiempo.vpistbba.dto.converters;

import javax.ejb.FinderException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.ejb.eb.Producto_servicio_peticionLocal;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisKey;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisLocal;
import co.com.telefonica.atiempo.ejb.eb.Subpeticion_atisLocalHome;
import co.com.telefonica.atiempo.interfaces.atiempo.SubRequest;

import com.telefonica_chile.atiempo.utiles.HomeFactory;

public class ProductoServicioPeticionConvertidor
{

	/**
	 * @param producto_servicio_peticionLocal
	 * @param subPeticionDTO
	 */
	private static Logger log=Logger.getLogger(ProductoServicioPeticionConvertidor.class);
	
	public static void setEntity(Producto_servicio_peticionLocal ps, SubRequest sdto,Long codPetCD,Integer codAgruSubNu) throws FinderException, NamingException
	{
		Subpeticion_atisLocalHome home=(Subpeticion_atisLocalHome) HomeFactory.getHome(Subpeticion_atisLocalHome.JNDI_NAME);
		Subpeticion_atisKey key=new Subpeticion_atisKey();
		key.fk_agru_sub_fk_pet_agrupacion_cod_pet_cd=codPetCD;
		key.fk_agru_sub_cod_agr_sub_nu=codAgruSubNu;
		key.cod_sub_cd=new Integer(new Long(sdto.getCode()).intValue());
		Subpeticion_atisLocal local=home.findByPrimaryKey(key);
		ps.setFk_01_subp_atis(local);
		
		ps.setPspe_cantidad(new Integer(local.getCan_pro_sub_nu().intValue()));
		ps.setNom_pro_ser_no(local.getNom_pro_ser_no());
		ps.setObs_sub_ds(local.getObs_sub_ds());
		ps.setTip_pro_ser_cd(local.getTip_pro_ser_cd());
		ps.setCod_tip_uso(local.getFk_agru_sub().getCod_tip_uso_cd());
		
		ps.setTip_pro_cmr_cd(local.getFk_agru_sub().getTip_pro_cmr_cd());
		ps.setSbt_pro_cmr_cd(local.getFk_agru_sub().getSbt_pro_cmr_cd());
		log.info("Relacion Producto Servicio Peticion a Subpeticion Atis creada Correctamente");
	}
}
