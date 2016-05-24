package co.com.telefonica.atiempo.sigres.emu;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;



/**
 * ServiceLocatorEmulator
 * 
 * @author Gonzalo Arreche
 *
 */
public class ServiceLocatorClienteEmulador_ST {
	private static Context context;

	/**
	 * Acceso al ClienteEmuladorBean
	 * 
	 * @param context
	 * @return
	 * @throws NamingException
	 * @throws CreateException
	 * @throws RemoteException
	 */
    public static ClienteEmulador_ST getClienteEmulador(InitialContext context) throws NamingException, CreateException, RemoteException {
        ClienteEmuladorHome_ST clienteEmuladorHome = (ClienteEmuladorHome_ST) context.lookup("ejb/co/com/telefonica/atiempo/sigres/emu/ClienteEmuladorHome_ST");
        ClienteEmulador_ST clienteEmulador = clienteEmuladorHome.create();
        return clienteEmulador;
    }

    private static Context getContext(){
    		if(context == null)
				try {
					context = new InitialContext();
				} catch (NamingException e) {
					e.printStackTrace();
				}
    		return context;
    }
}
