package org.cagrid.mms.service;

import gov.nih.nci.cadsr.umlproject.domain.Project;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;
import gov.nih.nci.system.applicationservice.ApplicationService;

import org.cagrid.mms.service.impl.cadsr.DomainModelBuilder;
import org.cagrid.mms.service.impl.cadsr.DomainModelGenerationException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class DomainModelGenerationTestCase {

	@Test
	public void testDomainModelGeneration(){
		ApplicationService appService = null;
		try {
			ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/application-config-client.xml");
			appService = org.cagrid.mms.service.impl.cadsr.ApplicationServiceProvider
			        .getApplicationServiceFromUrl(ctx,"http://cadsrapi.nci.nih.gov/cadsrapi40/");
		} catch (Exception e) {
			e.printStackTrace();
		}

            DomainModelBuilder builder = new DomainModelBuilder(appService);

            Project project = new Project();
            project.setVersion("3.2");
            project.setShortName("caCORE 3.2");
            System.out.println("Creating domain model for project: " + project.getShortName() + " (version:"
                + project.getVersion() + ")");

            try {
				DomainModel domainModel = builder.createDomainModelForPackages(project,
				    new String[]{"gov.nih.nci.cabio.domain"});
			} catch (DomainModelGenerationException e) {
				e.printStackTrace();
				Assert.fail(e.getMessage());
			}
	}
}
