package msgbroker;

import java.util.Calendar;

// RHC 04/09/2002
//     Todo el bloque relativo a MQ se rodea de try {} para asegurar el
//     cierre de las colas y desconexion del queue manager al producirse
//     alguna excepcion llamando a disconnect de MQConnection (mqc)

public class MQCallService extends BasicCallService {

    public MQCallService( Config aConfig ) throws MBException {
        super( aConfig );

    }

    public void exec() throws MBException {
    	
        MQMsg mqMsg;
        String qOutputName, qInputName, outputQManager;
        String cicsTrx;
        String messageType;
        
        Config config = getConfig();
        qOutputName = config.getValue( "outputQ" );
        if( qOutputName == null )
            throw new MBException( MBException.ERROR_ARCHIVO_CFG,
                                   "parametro outputQ no definido" );

        qInputName = config.getValue( "inputQ" );
        if( qInputName == null )
            throw new MBException( MBException.ERROR_ARCHIVO_CFG,
                                   "parametro inputQ no definido" );

        outputQManager = config.getValue( "outputQManager" );
        if( outputQManager == null )
            throw new MBException( MBException.ERROR_ARCHIVO_CFG,
                                   "parametro outputQManager no definido" );

        cicsTrx = config.getValue( "cicsTrx" );
        if( cicsTrx == null )
            cicsTrx = "TTTT";

        messageType = getMessageType();

		/* Reviso si se debe Mandar el Mensaje por la Hora */
		// Formato : HHMM

		try {
			int offLineIni = -1;
			int offLineFin = -1;
			offLineIni = Integer.parseInt( config.getValue( "offLineIni" ) );
			offLineFin = Integer.parseInt( config.getValue( "offLineFin" ) );
			
			int hActual = Integer.parseInt( getHoraActual() );
			System.out.println("Calculo Hora: [" + offLineIni + "," + offLineFin + "," + hActual + "]");
			
			if ( offLineIni > offLineFin ) {
				if ( (offLineIni<hActual && hActual<2400 ) || (0<hActual && hActual<offLineFin) ) {
					// Esta OffLinea
					throw new MBException( MBException.ERROR_TIMEOUT, 
										   "waitServiceRequest: timeout Auto" );
				}
			} else {
				if ( offLineIni<hActual && hActual<offLineFin ) {
					// Esta OffLinea
					throw new MBException( MBException.ERROR_TIMEOUT, 
										   "waitServiceRequest: timeout Auto" );
				}
			}
		} catch (Exception e) {
		}
		
		/**/
        try {
            mqMsg = new MQMsg();
            mqMsg.setHeaderVersion( "1" );
            mqMsg.setMessageType( messageType );
            mqMsg.setService( getService() );
            mqMsg.setTimeout( getTimeout() );
            mqMsg.setRetcode( 0 );
            mqMsg.setServerUser( getUserid() );
            mqMsg.setServerPassword( getPassword() );
            mqMsg.setCicsTrx( cicsTrx );
            mqMsg.setServerAppl( getServerAppl() );
            mqMsg.setData( new String( getData() ) );
        }
        catch( Exception e ) {
           throw new MBException( "creacion de mqMsg" + e );
        }
        if( messageType.equals( "U" ) ) {
            MQUConnection mqc = null;
            try {
                try {
                    qInputName = QPoolManager.reserveQueue();
                    if( qInputName == null )
                        throw new MBException( "No hay cola disponible en el pool" );
                }
                catch( Exception e ) {
                    throw new MBException( "reserveQueue()" + e );
                }

                mqc = new MQUConnection( config,
                                         outputQManager,
                                         qOutputName,
                                         qInputName );

                CallService.trace( "MQUConnection " + qOutputName + 
                                   " " + qInputName );
                mqc.setMessageType( messageType );
                mqc.setTimeout( getTimeout() );
                mqc.putMessage( new String( mqMsg.getMQMessage() ) );
                mqMsg = new MQMsg( mqc.getReply().getBytes() );
                setResp( mqMsg.getData().getBytes() );
                mqc.close();
                QPoolManager.freeQueue( qInputName );
            }
            catch( Exception e ) {
                try {
                    QPoolManager.freeQueue( qInputName );
                }
                catch( Exception e1 ) {
                }
                if( mqc != null )
                    mqc.close();
                throw new MBException( "MQUConnection " + e );
            }

        }
        else {
            MQConnection mqc = null;
            try {
                mqc = new MQConnection( config,
                                        outputQManager,
                                        qOutputName,
                                        qInputName );
                mqc.setMessageType( messageType );
                mqc.setTimeout( getTimeout() );
                mqc.putMessage( new String( mqMsg.getMQMessage() ) );
                if( messageType.equals( "I" ) || messageType.equals( "U" ) ) {
                    mqMsg = new MQMsg( mqc.getReply().getBytes() );
                    setResp( mqMsg.getData().getBytes() );
                }
                else
                    setResp( (new String("sin respuesta" )).getBytes() );
                mqc.disconnect();
            }
            catch( Exception e ) {
                if( mqc != null )
                    mqc.disconnect();
                   throw new MBException( "MQConnection " + e );
            }
        }

    }

	/**
	 * @return
	 */
	private String getHoraActual() {
		String hActual = "";
		Calendar c = Calendar.getInstance();
		String hh = rellena( ""+c.get(Calendar.HOUR_OF_DAY), "0", 2);
		String mm = rellena( ""+c.get(Calendar.MINUTE), "0", 2);

		return ( hh + mm );
	}

	/**
	 * @param string
	 * @param string2
	 * @param i
	 * @return
	 */
	private String rellena(String str, String c, int l) {
		if ( str == null )
			str = "";
		while (str.length() < l)
			str = c + str;
		return str;
	}

}
