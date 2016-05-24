package com.telefonica_chile.atiempo.utiles;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

//import com.telecom.security.PasswordServices;


public class ChangePass
{
	Properties prop;
//	private static final Logger log=Logger.getLogger(ChangePass.class);
	
//	public static void main(String[] args){
//		try{
//			Properties prop = new Properties();
//			prop.setProperty("HOST_LDAP","ldap://10.201.2.22");
//			prop.setProperty("DN_ADMIN", "CN=ADMIN VPI,OU=Usuarios,OU=Aplicaciones,OU=Telecom,DC=telecom,DC=esp");
//			prop.setProperty("PSWD_ADMIN", "Telecom2008");
//			prop.setProperty("BASE_NODE_USERS", "OU=USUARIOS,OU=Telecom,DC=telecom,DC=esp");
//			prop.setProperty("BASE_NODE_APP", "OU=Usuarios,OU=Aplicaciones,OU=Telecom,DC=telecom,DC=esp");
//			ChangePass changePass = new ChangePass();
//			changePass.setProperties(prop);
////			changePass.setPasword("gustavo.gomezjurado","Enero2009" , "Marzo2009");
//			changePass.setPasword("admin.vpi","Telecom2008" , "Telecom2008");
//	//		chP.changePswd("admin.tramitacion","Tcs1234" , "123");
//		}catch (javax.naming.AuthenticationException e) {
//			System.out.println("Authentication for password change failed.");
//			e.printStackTrace();
//		}
//		catch (Exception e){
//			e.printStackTrace();
//		}
//	}
	
	public ChangePass (String user,String claveOld,String claveNew) throws NamingException, UnsupportedEncodingException{
		this.setPasword(user,claveOld,claveNew);
	}
	
	public ChangePass (){
		
	}
	
	public void setProperties(Properties prop){
		this.prop = prop;
	}
		
	public void setPasword(String user,String claveOld,String claveNew)throws NamingException, UnsupportedEncodingException{
		String dnAdmin=prop.getProperty("DN_ADMIN");
		String pswdAdmin=prop.getProperty("PSWD_ADMIN");
		String hostLdap=prop.getProperty("HOST_LDAP");
		DirContext ctx = null;
		
		try{
			Hashtable env = new Hashtable();
		
			//*****************************************************
			env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.SECURITY_AUTHENTICATION,"simple");
			
			env.put(Context.PROVIDER_URL,hostLdap);//colombia
//			env.put(Context.PROVIDER_URL,"ldap://172.50.55.242:389");
			
			env.put(Context.SECURITY_PRINCIPAL,dnAdmin);//col
//			pswdAdmin = PasswordServices.getInstance().decrypt(pswdAdmin);
			env.put(Context.SECURITY_CREDENTIALS,pswdAdmin);//col
			
	//		env.put(Context.SECURITY_PRINCIPAL,"CN=GUSTAVO GOMEZJURADO PORTILLA,OU=USUARIOS,OU=Telecom,DC=telecom,DC=esp");
	//		env.put(Context.SECURITY_CREDENTIALS,"Enero2009");
			
//			env.put(Context.SECURITY_PRINCIPAL,"CN=loginUser,CN=Users,O=TCS,C=UY");
//			env.put(Context.SECURITY_CREDENTIALS,"login");//Enero2009
			
			
			// Create the initial directory context
			ctx = new InitialDirContext( env ) ;
//			System.out.println("realizó la conección");
		
			//Obtengo DistinguisedName
			String at = getDN(user, ctx);
	 	 	 
	 	 	//------------Imprimo todos los elementos------DEBUG------/
//		    Attributes answer = ctx.getAttributes(at);               
////			You can then print the contents of this answer as follows. 
//		    for (NamingEnumeration ae = answer.getAll(); ae.hasMore();) {
//		        Attribute attr = (Attribute)ae.next();
//		        System.out.println("attribute: " + attr.getID());
//		        /* Print each value */
//		        for (NamingEnumeration e = attr.getAll(); e.hasMore();
//		    	 System.out.println("value: " + e.next()))
//		    	;
//		    }
			//--------------------------------------------------------\
		    
		    
		    //------------Cambio Clave--------------------------------/
			String passwordAttributeName = "userPassword";
			final ModificationItem[] passwordChange = new ModificationItem[] {
					new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute(passwordAttributeName, claveNew))
			};
			// Perform the update
//			System.out.println("Realizando modificación de password");
			ctx.modifyAttributes(at, passwordChange);
//			System.out.println("Clave cambiada");
		    //--------------------------------------------------------\
		}
	    finally{
			if (ctx != null) {
				ctx.close();
			}
		} 
		
	}
	
	private String getDN(String user, DirContext ctx)throws NamingException{
	    //----------- get DN by sAMAccountName----------/
		String beseNdUsr=prop.getProperty("BASE_NODE_USERS");
		String baseNdApp=prop.getProperty("BASE_NODE_APP");
		
	     String at = "" ;
	 	 String BASE = beseNdUsr;
	 	 NamingEnumeration resulta = null;
	 	 SearchControls restric = null;
	 	 String FILTRO = "(sAMAccountName=" + user + ")";
	 	 
 	 	 for ( resulta = ctx.search( BASE , FILTRO , restric ) ; resulta != null && resulta.hasMore() ; ) {
 	 	 	 SearchResult sr = ( SearchResult ) resulta.next() ;
 	 	 	 String dn = sr.getName() ;
 	 	 	 at = dn + "," + BASE ;
 	 	 }

	 	 if(!at.equalsIgnoreCase("")){
	 	 	System.out.println("Distingished Name\n" + at);
	 	 }else{
	 	 	BASE = baseNdApp;
	 	 	for ( resulta = ctx.search( BASE , FILTRO , restric ) ; resulta != null && resulta.hasMore() ; ) {
	 	 	 	 SearchResult sr = ( SearchResult ) resulta.next() ;
	 	 	 	 String dn = sr.getName() ;
	 	 	 	 at = dn + "," + BASE ;
	 	 	 }
	 	 	if(!at.equalsIgnoreCase(""))
	 	 		System.out.println("Distinguished Name\n" + at);
	 	 	else
	 	 		System.out.println("Usuario no encontrado");
	 	 }
	    //----------------------------------------------\
	 	 return at;
	}

}
