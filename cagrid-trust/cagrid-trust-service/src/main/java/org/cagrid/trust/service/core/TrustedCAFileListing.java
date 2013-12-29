package org.cagrid.trust.service.core;

import java.io.File;

/**
 * @author <A href="mailto:langella@bmi.osu.edu">Stephen Langella </A>
 * @author <A href="mailto:oster@bmi.osu.edu">Scott Oster </A>
 * @author <A href="mailto:hastings@bmi.osu.edu">Shannon Hastings </A>
 * @version $Id: ArgumentManagerTable.java,v 1.2 2004/10/15 16:35:16 langella
 *          Exp $
 */
public class TrustedCAFileListing {
	private File certificate;
	private File crl;
	private File metadata;
	private String name;

	

	public TrustedCAFileListing(String name) {
		this.name = name;
	}

	public File getCertificate() {
		return certificate;
	}

	public void setCertificate(File certificate) {
		this.certificate = certificate;
	}

	public File getCRL() {
		return crl;
	}

	public void setCRL(File crl) {
		this.crl = crl;
	}

	public String getName() {
		return name;
	}

	public boolean isValid() {
		// TODO: We may want to make sure the files are valid as well.
		if (this.getCertificate() == null) {
			return false;
		}

		return true;
	}

	public String toPrintText() {
		StringBuffer sb = new StringBuffer();
		sb.append("Trusted CA [" + getName() + "] {\n");
		sb.append(" Certificate:" + getCertificate() + " \n");
		sb.append(" CRL:" + getCRL() + " \n");
		sb.append("}\n");

		return sb.toString();
	}

	public File getMetadata() {
		return metadata;
	}

	public void setMetadata(File metadata) {
		this.metadata = metadata;
	}

}
