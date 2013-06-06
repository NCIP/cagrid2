package COM.claymoresystems.cert;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.ldap.LdapName;

public class LdapNameComparator implements Comparator<LdapName> {

	@Override
	public int compare(LdapName name0, LdapName name1) {
		Set<Attribute> atts0 = Collections.emptySet();
		try {
			atts0 = DNDissector.dissect(name0);
		} catch (NamingException ignored) {
		}
		Set<Attribute> atts1 = Collections.emptySet();
		try {
			atts1 = DNDissector.dissect(name1);
		} catch (NamingException ignored) {
		}

		int rc = atts0.size() - atts1.size();
		if (rc == 0) {
			// If no attributes don't compare as equal.
			if (atts0.size() == 0)
				rc = -1;
			else
				rc = atts0.containsAll(atts1) ? 0 : -1;
		}
		return rc;
	}
}
