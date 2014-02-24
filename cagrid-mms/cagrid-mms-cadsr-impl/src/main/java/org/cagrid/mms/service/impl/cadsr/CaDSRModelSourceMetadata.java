package org.cagrid.mms.service.impl.cadsr;

import org.cagrid.mms.model.ModelSourceMetadata;
import org.cagrid.mms.model.SourceDescriptor;

public class CaDSRModelSourceMetadata extends ModelSourceMetadata {
	
	public CaDSRModelSourceMetadata() {
		super();
		this.setDefaultSourceIdentifier("caDSR Production");
		SupportedModelSources sources = new SupportedModelSources();
		SourceDescriptor descriptor = new SourceDescriptor();
		descriptor.setIdentifier("caDSR Production");
		descriptor.setDescription("The production instance of the National Cancer Institute's Cancer Data Standards Repository (caDSR).");
		sources.getSource().add(descriptor);
		this.setSupportedModelSources(sources);
	}

}
