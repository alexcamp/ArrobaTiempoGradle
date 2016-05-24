package com.telefonica_chile.bandeja.web.utiles.msgbroker;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.telefonica_chile.atiempo.utiles.LoggerFactory;

import msgbroker.CallService;

public abstract class MBrokerService {
	private String cfgPath = "";
	
	private Hashtable _encabezado = new Hashtable();
	private Hashtable _resultSet = new Hashtable();
	private int _largoEncabezado;
	private int _largoCuerpo;
	
	protected MBInput _mbInput;
	protected MBOutput _mbOutput;
	protected MBCampo[] _tplHead;
	protected MBCampo[] _tplBody;

	private String _nombre;
	protected boolean _moreExecutions = true; // Siempre se puede ejecutar al menos una vez
	private boolean _resultExecution = true; // Estado execution de MBroker.
	private String _codeExecution = "00"; // Codigo que retorna el mensaje

	private String _rawOutput;
	private String _serviceName;
	protected boolean _debug = false;

	public MBrokerService(String nombre, int largoEncabezado, int largoCuerpo) {
		_nombre = nombre;
		_largoEncabezado = largoEncabezado;
		_largoCuerpo = largoCuerpo;
	}

	public void setCfgPath(String path) {
		cfgPath = path;
	}

	public void setData( String key, String value ) {
		_mbInput.setData(key, value);
	}

	public void setMBInput(MBInput input) {
		_mbInput = input;
	}

	public MBInput getMBInput() {
		return _mbInput;
	}

	public MBOutput getMBOutput() {
		return _mbOutput;
	}

	public MBCampo[] getTplBody() {
		return _tplBody;
	}

	public String getRawOutput() {
		return _rawOutput;
	}

	public boolean getResultExecution() {
		return _resultExecution;
	}

	public String getCodeExecution() {
		return _codeExecution;
	}

	public String getMessageInput() {
		return _mbInput.toString();
	}

	
	/* Retorna la data completa */
	public void execute() {
		CallService _callService = null;
		Logger log = Logger.getLogger(MBrokerService.class);
		if (_debug)
			log.debug("Archivo Servicio = '" + cfgPath + _nombre + "'");
		try {
			_callService = new CallService(cfgPath + _nombre);
			if (_debug)
				log.debug("_mbInput = '" + _mbInput + "'");
			String sData = _mbInput.toString();

			_callService.setData(sData.getBytes());

			_callService.exec();

			byte[] resp = _callService.getResp();
			int largoResp = resp.length;
			if (_debug)
				log.debug("largoResp = " + largoResp);

			_rawOutput = new String(resp);

			if (_debug)
				log.debug("OUTPUT = '" + _rawOutput + "'");

			// Error
			if (sData != null && sData.equals(_rawOutput))
				_rawOutput = null;
		
		} catch (Exception e) {
			_resultExecution = false;
			_codeExecution = "-1";
			log.debug("Error en Execution del MBroker. Servicio: '" + _nombre + "' " + e.getMessage());
			if (_debug)
				e.printStackTrace();
		}
		
		// lleno los hashing's de encabezado y body
		fillData();
	}

	/**
	 * Llena _mbOutput con lo que hay en _rawOutput
	 */
	private void fillData() {
		Logger log = Logger.getLogger(MBrokerService.class);
		if (_rawOutput == null)
			return;
		if ( _debug )
			log.debug("largoHead=" + _largoEncabezado);
		String head = _rawOutput.substring(0, _largoEncabezado);
		String body = _rawOutput.substring(_largoEncabezado, _rawOutput.length());
		int lenBody = body.length();

		// Lleno primero el header
		if (_tplHead != null) {
			for (int i = 0, nextInit = 0; i < _tplHead.length; i++) {
				int lenCell = _tplHead[i].getLargo();
				String val = head.substring(nextInit, nextInit + lenCell);

				// probablemente la siguiente linea no es necesaria
				_tplHead[i].setValue(val);

				nextInit += lenCell;
				if ( _debug )
					log.debug("MB - HEAD: " + _tplHead[i].getNombre() + " = '" + val + "'");
				_encabezado.put(_tplHead[i].getNombre(), _tplHead[i].getValue() );
			}

		}

		// Ahora el body
		String[][] strBody = null;
		if (_tplBody != null && lenBody > 0 && _largoCuerpo > 0) {
			int nRegistros = nRegistros = lenBody / _largoCuerpo;

			strBody = new String[nRegistros][_tplBody.length];
			for (int i = 0, nextInit = 0; i < strBody.length; i++) {

				for (int j = 0; j < _tplBody.length; j++) {
					String nombre = _tplBody[j].getNombre();
					if (!_resultSet.containsKey(nombre))
						_resultSet.put(nombre, new String[nRegistros]);
					String[] d = (String[]) _resultSet.get(nombre);

					int lenCell = _tplBody[j].getLargo();
					String val = _tplBody[j].trimmed(body.substring(nextInit, nextInit + lenCell));
					strBody[i][j] = val;
					nextInit += lenCell;

					d[i] = val;
					
				}
			}
		}

		// Ahora creo el MBOutput
		_mbOutput = new MBOutput(_tplHead, _tplBody, strBody);
	}

	/**
	 *  Recibe el nobre y la data entregada por el servicio Host.
	 * 
	 */
	public void setService(String sName, String data) {
		_serviceName = sName;
		_rawOutput = data;
		fillData();
	}	
	/**
	 * Las implementaciones de este metodo deben:
	 *
	 * 1.-	Llamar a super.execute()
	 * 2.-	Definir el MBOutput (al final lo deberia *llenar*
	 *		esta misma clase, con fillData)
	 * 3.-	Actualizar el mbInput o bien setear el _nextExecution a false
	 */
	public abstract void nextExecution();

	public boolean hasMoreExecutions() {
		return _moreExecutions;
	}


	public Hashtable getEncabezado() {
		return _encabezado;
	}

	public Hashtable getResultSet() {
		return _resultSet;
	}
	
}
