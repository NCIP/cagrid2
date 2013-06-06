package org.cagrid.security.ssl.proxy.trust;

import java.util.HashSet;
import java.util.Set;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.security.auth.x500.X500Principal;

public class DNDissector {

	public static Set<Attribute> dissect(X500Principal x500Name)
			throws NamingException {
		LdapName ldapName = new LdapName(x500Name.getName());
		return dissect(ldapName);
	}

	public static Set<Attribute> dissect(LdapName ldapName)
			throws NamingException {
		Set<Attribute> attributes = new HashSet<Attribute>();
		for (Rdn rdn : ldapName.getRdns()) {
			NamingEnumeration<? extends Attribute> ne = rdn.toAttributes()
					.getAll();
			while (ne.hasMore()) {
				Attribute att = ne.next();
				attributes.add(att);
			}
		}
		return attributes;
	}
}
