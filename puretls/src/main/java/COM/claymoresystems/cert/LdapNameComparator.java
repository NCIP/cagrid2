package COM.claymoresystems.cert;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;

public class LdapNameComparator implements Comparator<LdapName> {

	private final static AttributeComparator attComparator = new AttributeComparator();

	@Override
	public int compare(LdapName name0, LdapName name1) {
		SortedSet<Attribute> atts0 = new TreeSet<Attribute>(attComparator);
		SortedSet<Attribute> atts1 = new TreeSet<Attribute>(attComparator);
		for (Rdn rdn0 : name0.getRdns()) {
			NamingEnumeration<? extends Attribute> ne0 = rdn0.toAttributes()
					.getAll();
			try {
				while (ne0.hasMore()) {
					Attribute att0 = ne0.next();
					atts0.add(att0);
				}
			} catch (NamingException ne) {
				// don't care
			}
		}
		for (Rdn rdn1 : name1.getRdns()) {
			NamingEnumeration<? extends Attribute> ne1 = rdn1.toAttributes()
					.getAll();
			try {
				while (ne1.hasMore()) {
					Attribute att1 = ne1.next();
					atts1.add(att1);
				}
			} catch (NamingException ne) {
				// don't care
			}
		}

		int rc = atts0.size() - atts1.size();
		if (rc == 0) {
			// If no attributes don't compare as equal.
			rc = -1;
			Iterator<Attribute> itr0 = atts0.iterator();
			Iterator<Attribute> itr1 = atts1.iterator();
			while (itr0.hasNext()) {
				Attribute att0 = itr0.next();
				Attribute att1 = itr1.next();
				rc = attComparator.compare(att0, att1);
				if (rc != 0)
					break;
			}
		}
		return rc;
	}

}
