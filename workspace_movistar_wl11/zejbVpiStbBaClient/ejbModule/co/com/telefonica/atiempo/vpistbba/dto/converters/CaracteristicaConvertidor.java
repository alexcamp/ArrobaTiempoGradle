/*
 * Created on 07-mar-07
 */
package co.com.telefonica.atiempo.vpistbba.dto.converters;

import co.com.telefonica.atiempo.ejb.eb.Subpeticion_caracteristicasLocal;
import co.com.telefonica.atiempo.interfaces.atiempo.Characteristic;

/**
 * @author TCS
 */
public class CaracteristicaConvertidor {
	
	public static void setEntity(Subpeticion_caracteristicasLocal entity, Characteristic dto)
	{
		entity.setCod_tip_dat_cd(dto.getDataType());
		entity.setCod_val_crc_cd(new Long(dto.getCodeValue()));
		entity.setFmt_vdo_crc_no(dto.getFormat());
		entity.setLng_crc_nu(new Long(dto.getLength()));
		entity.setMom_inf_crc_in(dto.getMoment());
		entity.setNom_crc_no(dto.getName());
		entity.setVal_fin_crc_no(dto.getFinalValue());
		entity.setVal_ini_crc_no(dto.getInitialValue());
		entity.setVal_ral_crc_cd(dto.getRealValue());
	}

}
