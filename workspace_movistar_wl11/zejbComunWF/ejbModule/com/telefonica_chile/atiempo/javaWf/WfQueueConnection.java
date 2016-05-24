/*
 * Creado el 6/09/2012
 *
 * TODO Para cambiar la plantilla de este archivo generado, vaya a
 * Ventana - Preferencias - Java - Estilo de código - Plantillas de código
 */
package com.telefonica_chile.atiempo.javaWf;

import com.telefonica_chile.atiempo.utiles.Fecha;

/**
 * @author cacano
 *
 * Representa una estructura para la agrupación de información en
 * el envío de mensajes a workflow
 */
class WfQueueConnection {
	private Fecha dateImplWF;
	private Fecha dateImplWFPos;
	private String queueConnectionFactory;
	private String queue;
	private String errorQueue;
	private String source;
	private Long id;
	/**
	 * @param dateImplWF El dateImplWF a establecer.
	 */
	void setDateImplWF(Fecha dateImplWF) {
		this.dateImplWF = dateImplWF;
	}
	/**
	 * @return Devuelve dateImplWF.
	 */
	Fecha getDateImplWF() {
		return dateImplWF;
	}
	/**
	 * @param queueConnectionFactory El queueConnectionFactory a establecer.
	 */
	void setQueueConnectionFactory(String queueConnectionFactory) {
		this.queueConnectionFactory = queueConnectionFactory;
	}
	/**
	 * @return Devuelve queueConnectionFactory.
	 */
	String getQueueConnectionFactory() {
		return queueConnectionFactory;
	}
	/**
	 * @param queue El queue a establecer.
	 */
	void setQueue(String queue) {
		this.queue = queue;
	}
	/**
	 * @return Devuelve queue.
	 */
	String getQueue() {
		return queue;
	}
	/**
	 * @param errorQueue El errorQueue a establecer.
	 */
	void setErrorQueue(String errorQueue) {
		this.errorQueue = errorQueue;
	}
	/**
	 * @return Devuelve errorQueue.
	 */
	String getErrorQueue() {
		return errorQueue;
	}
	/**
	 * @param source El source a establecer.
	 */
	void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return Devuelve source.
	 */
	String getSource() {
		return source;
	}
	/**
	 * @param id El id a establecer.
	 */
	void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return Devuelve id.
	 */
	Long getId() {
		return id;
	}
	/**
	 * @param dateImplWFPos El dateImplWFPos a establecer.
	 */
	void setDateImplWFPos(Fecha dateImplWFPos) {
		this.dateImplWFPos = dateImplWFPos;
	}
	/**
	 * @return Devuelve dateImplWFPos.
	 */
	Fecha getDateImplWFPos() {
		return dateImplWFPos;
	}
}
