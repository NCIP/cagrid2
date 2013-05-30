package COM.claymoresystems.cert;

import java.util.Comparator;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;

public class AttributeComparator implements Comparator<Attribute> {

	@Override
	public int compare(Attribute att1, Attribute att2) {
		String id1 = att1.getID();
		String id2 = att2.getID();
		int rc = id1.compareTo(id2);
		if (rc == 0) {
			String val1 = "";
			String val2 = "";
			try {
				val1 = att1.get().toString();
				val2 = att2.get().toString();
			} catch (NamingException ne) {
				// don't care
			}
			rc = val1.compareTo(val2);
		}
		return rc;
	}
}
