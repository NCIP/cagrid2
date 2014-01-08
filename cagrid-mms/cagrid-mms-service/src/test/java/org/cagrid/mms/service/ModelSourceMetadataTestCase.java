package org.cagrid.mms.service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;
import gov.nih.nci.cagrid.metadata.dataservice.UMLClass;
import junit.framework.Assert;

import org.cagrid.mms.model.UMLProjectIdentifer;
import org.cagrid.mms.service.impl.MMSGeneralException;
import org.cagrid.mms.test.MMSAbstractBaseTestCaseBase;

public class ModelSourceMetadataTestCase extends MMSAbstractBaseTestCaseBase {

	public void testGenerateDomainModel() {

		UMLProjectIdentifer project = new UMLProjectIdentifer();
		project.setIdentifier("caCORE 3.2");
		project.setVersion("3.2");

		try {
			DomainModel model = mms.generateDomainModelForProject(project);
			System.out.println(model.getProjectShortName());
			System.out.println(model.getProjectVersion());
			for (Iterator iterator = model.getExposedUMLClassCollection().getUMLClass().iterator(); iterator.hasNext();) {
				UMLClass type = (UMLClass) iterator.next();
				System.out.println("\t" + type.getClassName());
				
			}
		} catch (MMSGeneralException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (InvalidUMLProjectIndentifier e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	public void testGenerateDomainModelForSubPackages() {

		UMLProjectIdentifer project = new UMLProjectIdentifer();
		project.setIdentifier("caCORE 3.2");
		project.setVersion("3.2");

		try {
			DomainModel model = mms
					.generateDomainModelForPackages(
							project,
							(List<String>) Arrays
									.asList(new String[] { "gov.nih.nci.cabio.domain" }));
			System.out.println(model.getProjectShortName());
			System.out.println(model.getProjectVersion());
			for (Iterator iterator = model.getExposedUMLClassCollection().getUMLClass().iterator(); iterator.hasNext();) {
				UMLClass type = (UMLClass) iterator.next();
				System.out.println("\t" + type.getClassName());
				
			}
		} catch (MMSGeneralException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch (InvalidUMLProjectIndentifier e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	


}
