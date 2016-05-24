/**
 * MBService que realiza la Consulta de Datos de un  PS
 * 
 * @author: Jorge Velasco
 */

package com.telefonica_chile.bandeja.web.utiles.msgbroker;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;

public class AvisoAgendaService extends MBrokerService {

	protected transient Logger log = LoggerFactory.getLogger(getClass());
	
	public AvisoAgendaService(DatosAvisoAgenda bean) {

		// servicio, largo encabezado, largo cuerpo
		super("AvisoCompromiso", 13, 0);
		setCfgPath( "/home/atiempo/etc/cfg/" );

		// Seteo el debug
		_debug = false;

		// seteo los datos de entrada iniciales
		_mbInput =
			new MBInput(
				new MBCampo[] { 
					new MBCampo("NP", 12, "0", MBCampo.RIGHT, bean.getNroPeticion()), 
					new MBCampo("FC", 10, "0", MBCampo.LEFT, bean.getFechaCompromiso()), 
					new MBCampo("HD", 8, "0", MBCampo.LEFT, bean.getHoraDesde()), 
					new MBCampo("HH", 8, "0", MBCampo.LEFT, bean.getHoraHasta()), 
					new MBCampo("TH", 3, " ", MBCampo.LEFT, bean.getTipoHora()), 
					new MBCampo("TC", 60, " ", MBCampo.LEFT, bean.getNombreTecnico()),
					new MBCampo("ERR", 5, "0", MBCampo.RIGHT, bean.getError()),
					new MBCampo("DESC", 60, " ", MBCampo.LEFT, bean.getDescError()),
					new MBCampo("FILLER", 134, " ", MBCampo.LEFT),
				}
			);

		// formato del header de salida (_mbOutput)
		_tplHead =
			new MBCampo[] {
				new MBCampo("OUTPUT", 13, " ", MBCampo.RIGHT)};
 
		/* formato del body de salida (_mbOutput) */
		_tplBody = new MBCampo[] {};

	}

	public void nextExecution() {
		super.execute();
		log.info("Enviando Mensaje: '" + this.getMessageInput() + "'");
		_moreExecutions = false;
	}

}
