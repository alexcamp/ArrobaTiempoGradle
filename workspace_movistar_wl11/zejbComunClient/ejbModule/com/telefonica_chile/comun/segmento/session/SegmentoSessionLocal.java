package com.telefonica_chile.comun.segmento.session;
import javax.ejb.FinderException;

import com.telefonica_chile.comun.segmento.dto.SubSegmentoDto;
import com.telefonica_chile.comun.segmento.dto.segmentoDTO;
/**
 * Local interface for Enterprise Bean: SegmentoSession
 */
public interface SegmentoSessionLocal extends javax.ejb.EJBLocalObject {
	public SubSegmentoDto getSubSegmento(Long idSubSegmento)
		throws FinderException;
	/**
	 * Devuelve un DTO con los datos de UbicacionGeografica, ingresando por parámetro el PrimaryKey
	 */
	public segmentoDTO getSegmento(Long idSegmento) throws FinderException;
}
