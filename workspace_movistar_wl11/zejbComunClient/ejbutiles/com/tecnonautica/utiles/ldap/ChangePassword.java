package com.tecnonautica.utiles.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.log4j.Logger;

public class ChangePassword {

	private Logger log=Logger.getLogger(ChangePassword.class);
	
	//private String userName = "CN=leonardo,CN=ldapadm,OU=intranet,OU=Workflow,O=ctc,C=cl";
	//private String oldPassword = "leonardo";
	//private String newPassword = "leonardo2";

	public void cambioClave()
	{
		String test = ",CN=ldapadm,OU=intranet,OU=Workflow,O=ctc,C=cl";
		Hashtable env = new Hashtable();
		env.put(Context.SECURITY_PROTOCOL, "ssl");
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://192.168.1.98:389");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL,test);
		env.put(Context.SECURITY_CREDENTIALS, "leonardo");
		try
		{ 
			LdapContext ctx = new InitialLdapContext(env,null);
//			SearchControls constraints = new SearchControls();
//			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
//			String[] strAttributes = { "sAMAccountName", "memberOf" };
//			String FILTER = "(&(objectClass=user)(sAMAccountName=X))";
//			String searchBase = "OU=intranet,OU=Workflow,O=ctc,C=cl";
//			constraints.setReturningAttributes(strAttributes);
//			NamingEnumeration results = ctx.search(searchBase, FILTER, constraints);
//			System.out.println("results : " + results);
//			while (results != null && results.hasMore())
//			{
//				SearchResult sr = (SearchResult) results.next();
//				String dn = sr.getName();
//				System.out.println("Distinguised Name : " + dn);
//				userName = dn+test;
//				break;
//			}
			log.debug("Pase por aqui");
		}
		catch (NamingException e)
		{
			e.printStackTrace();
			log.debug("Problem resetting password: " + e);
		}
//		catch (UnsupportedEncodingException e)
//		{
//			e.printStackTrace();
//			System.out.println("Problem encoding password: " + e);
//		}
	}
}
