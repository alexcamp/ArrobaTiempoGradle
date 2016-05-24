/*
 * Created on 12-feb-07
 */
package co.com.telefonica.atiempo.vpistbba.mensajeria.util;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import co.com.telefonica.atiempo.sigres.emu.Receptor;
import co.com.telefonica.atiempo.sigres.emu.util.ServiceLocatorEmulator;
import co.com.telefonica.atiempo.sigres.emu.util.XMLProcessor;
import co.com.telefonica.atiempo.utiles.ATiempoAppEx;
import co.com.telefonica.atiempo.utiles.XMLUtilities;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.QMATiempoManagerLocal;
import co.com.telefonica.atiempo.vpistbba.mensajeria.salida.QMATiempoManagerLocalHome;

import com.telefonica_chile.atiempo.utiles.HomeFactory;
import com.telefonica_chile.atiempo.utiles.LoggerFactory;

/**
 * @author TCS
 */
public abstract class QManagerService {

	private String queue;

	protected transient Logger log = LoggerFactory.getLogger(getClass());

	QMATiempoManagerLocal qmat;

	public QManagerService(String queue) throws ATiempoAppEx {
		this.queue = queue;
		initialize();
	}

	protected abstract Object procesarDatos(Object obj);

	public char filtraInvalidaCharacter(char c) {
		char[] arrChar = new char[1];
		arrChar[0] = c;
		String strOut = filtraInvalidaCharacter( new String( arrChar ) );
		
		if ( strOut == null || strOut.length()==0 )
			return '0';

		return strOut.charAt(0);
	}

	public String filtraInvalidaCharacter(String str) {
		if (str == null || str.length() == 0)
			return str;
//		log.info("Limpiando String[" + str + "]");
		byte[] b = str.getBytes();
		
		
		byte[] bAux = new byte[b.length];
		byte[] bExt = new byte[4];
		bExt[0] = 0;
		bExt[1] = 10;
		bExt[2] = 13;
		bExt[3] = 9;

		int j = 0;
		boolean err = false;
		for (int i = 0; i < b.length; i++) {
			err = false;
			for (int k = 0; k < bExt.length; k++) {
				if (bExt[k] == b[i]) {
					err = true;
					break;
				}
			}
			if (!err)
				bAux[j++] = b[i];
		}
		String strOut = "";
		if (j > 0)
			strOut = new String(bAux, 0, j);

		//log.info("Limpiando String['" + str + "','" + strOut + "']");

		return  strOut;
	}

	private void initialize() throws ATiempoAppEx {
		try {
			InitialContext ic = new InitialContext();
			QMATiempoManagerLocalHome home = (QMATiempoManagerLocalHome) HomeFactory.getHome(QMATiempoManagerLocalHome.JNDI_NAME);
			qmat = home.create();
		} catch (NamingException nex) {
			throw new ATiempoAppEx(ATiempoAppEx.NAMING, nex);
		} catch (CreateException cex) {
			throw new ATiempoAppEx(ATiempoAppEx.CREATE, cex);
		}
	}

	public void enviarMensaje(Object dto) throws ATiempoAppEx {
		String mensajeSalida = XMLUtilities.marshall(procesarDatos(dto));
		//String mensajeSalida = XMLUtilities.marshall(dto); //Se pasa directamente el TR
		
		//invocar receptor

		XMLProcessor xml = new XMLProcessor();
		InitialContext context = null;
		String mensaje = null;
		Receptor r = null;
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			r = ServiceLocatorEmulator.getReceptor(context);
			
			if (ServiceLocatorEmulator.emuladorActivado() && ServiceLocatorEmulator.isTREmulada(mensajeSalida)) {
				r.recibirMensaje(mensajeSalida);
			} else {				
				qmat.enviarMensaje(queue, mensajeSalida);
			}

		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CreateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void enviarMensaje(Object dto, String correlationID) throws ATiempoAppEx {
		log.debug("El correlation ID que llevo es:"+correlationID);
		String mensajeSalida = XMLUtilities.marshall(procesarDatos(dto));
		//String mensajeSalida = XMLUtilities.marshall(dto); //Se pasa directamente el TR
		
		//invocar receptor

		XMLProcessor xml = new XMLProcessor();
		InitialContext context = null;
		String mensaje = null;
		Receptor r = null;
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			r = ServiceLocatorEmulator.getReceptor(context);

			if (false && ServiceLocatorEmulator.emuladorActivado() && ServiceLocatorEmulator.isTREmulada(mensajeSalida)) {
				r.recibirMensaje(mensajeSalida);
			} else {				
				qmat.enviarMensajeWhitCorrelId(queue, mensajeSalida, correlationID);
			}

		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (CreateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}