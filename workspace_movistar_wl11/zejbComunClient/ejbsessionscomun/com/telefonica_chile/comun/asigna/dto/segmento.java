/*
 * Created on Feb 24, 2005
 */
package com.telefonica_chile.comun.asigna.dto;

/**
 * @author crferna
 *
 */

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;

public class segmento  implements Serializable{
	
	
	/**
	 * Obtencion de logger.
	 * @author amartoq
	 */
	protected static Logger log = LoggerFactory.getLogger(segmento.class);

	private Long segmentoId;
	private Long segmentoIdFamilia;
	private Long segmentoIdGrupo;	
	private String  segmentoNombre;
	private String  segmentoCodigo;
	private String  segmentoNombreFamilia;
	private String  segmentoNombreGrupo;
		
	public Long getSegmentoId() {
		return segmentoId;
	}

	public Long getSegmentoIdFamilia() {
		return segmentoIdFamilia;
	}
		
	public Long getSegmentoIdGrupo() {
		return segmentoIdGrupo;
	}
	
	public String getSegmentoNombre() {
		return segmentoNombre;
	}
	
	public String getSegmentoCodigo() {
		return segmentoCodigo;
	}
	
	public String getSegmentoNombreFamilia() {
		return segmentoNombreFamilia;
	}
	
	public String getSegmentoNombreGrupo() {
		return segmentoNombreGrupo;
	}
	
	public void setSegmentoId(Long id) {
		this.segmentoId = id;
	}
	
	public void setSegmentoIdFamilia(Long idFamilia) {
		this.segmentoIdFamilia = idFamilia;
	}
	
	public void setSegmentoIdGrupo(Long idGrupo) {
		this.segmentoIdGrupo = idGrupo;
	}
	
	public void setSegmentoNombre(String nombreSegmento) {
		this.segmentoNombre = nombreSegmento;
	}
	
	public void setSegmentoCodigo(String nombreCodigo) {
			this.segmentoCodigo = nombreCodigo;
		}
	
	public void setSegmentoNombreFamilia(String nombreFamilia) {
			this.segmentoNombreFamilia = nombreFamilia;
		}
	
	public void setSegmentoNombreGrupo(String nombreGrupo) {
			this.segmentoNombreGrupo = nombreGrupo;
		}
		
	/**
	 * OBJETO CON LA INFORMACION DEL SEGMENTO
	 * @param id
	 * @param idFamilia
	 * @param idGrupo
	 * @param nombreSegmento
	 * @param segmentoCodigo
	 * @param nombreFamilia
	 * @param nombreGrupo
	 */
	public segmento(Long id, Long idFamilia, Long idGrupo, String nombreSegmento, 
		String segmentoCodigo,	String nombreFamilia, String nombreGrupo) {
		this.segmentoId = id;
		this.segmentoIdFamilia = idFamilia;
		this.segmentoIdGrupo = idGrupo;
		this.segmentoNombre = nombreSegmento;
		this.segmentoCodigo = segmentoCodigo;
		this.segmentoNombreFamilia = nombreFamilia;
		this.segmentoNombreGrupo = nombreGrupo;
	}
	
	/**
	 * RETORNA UN OBJETO SEGMENTO CON EL GRUPO DEL SEGMENTO, A PARTIR DEL CODIGO DE SEGMENTO
	 * @param codigoSegmento
	 * @return segmento
	 */
	public static segmento recuperaGrupoSegmento(String codigoSegmento) {
		segmento grupoDelSegmento = null;
//		TODO: revisar pork falta la tabla familia segmento
//		SegmentoLocalHome home;
//		try {
//			home = (SegmentoLocalHome)
//					HomeFactory.getHome(SegmentoLocalHome.JNDI_NAME);
//		} catch (NamingException e) {
//			throw new NestedRuntimeException("Problemas recuperando jndi " + SegmentoLocalHome.JNDI_NAME, e);
//		}
//	
//		SegmentoLocal segmento = null;
//		try {
//			segmento = home.findByCodigo(codigoSegmento);
//			if (log.isDebugEnabled())
//				log.debug("Familia de Segmentos encontrada: <" + segmento.getIdFamiliaSegmento() +">");
//		} catch (FinderException e) {
//		log.info("No se encontraron items Segmento : " + e.getMessage());
//			return grupoDelSegmento;
//		}
//
//			FamiliaSegmentoLocal segmFamiEJB = segmento.getFamiliasegmento();
//			GrupoSegmentoLocal 	 grupoSegm	 = segmFamiEJB.getGruposegmento();
//		
//		if (log.isDebugEnabled())
//			log.debug("Familia-segmento: CODSEG: <" + segmento.getCodigo() + "> FASESEG: <" + segmento.getIdFamiliaSegmento()+ "> GRUPSEGM : <" + segmFamiEJB.getIdGrupoSegmento() + ">");
//		
//		grupoDelSegmento = new segmento(
//					null, 
//					segmento.getIdFamiliaSegmento(),
//					segmFamiEJB.getIdGrupoSegmento(), 
//					segmento.getDescripcion(),
//					segmento.getCodigo(),
//					segmFamiEJB.getNombre(),
//					grupoSegm.getNombre() );
		return grupoDelSegmento;
	}


}
