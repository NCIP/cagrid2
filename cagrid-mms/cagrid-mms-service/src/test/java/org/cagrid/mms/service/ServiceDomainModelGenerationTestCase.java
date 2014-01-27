package org.cagrid.mms.service;

import static org.junit.Assert.assertNotNull;
import gov.nih.nci.cagrid.metadata.common.SemanticMetadata;
import gov.nih.nci.cagrid.metadata.common.UMLAttribute;
import gov.nih.nci.cagrid.metadata.common.UMLClass.UmlAttributeCollection;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel;
import gov.nih.nci.cagrid.metadata.dataservice.DomainModel.ExposedUMLClassCollection;
import gov.nih.nci.cagrid.metadata.dataservice.ObjectFactory;
import gov.nih.nci.cagrid.metadata.dataservice.UMLClass;

import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;

import org.cagrid.mms.model.UMLProjectIdentifer;
import org.cagrid.mms.service.impl.MMS;
import org.cagrid.mms.test.SpringTestApplicationContextConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations={
                SpringTestApplicationContextConstants.MMS_BASE_LOCATION
        })
public class ServiceDomainModelGenerationTestCase{

	@Autowired
    public MMS mms;

    @Before
    public void onSetup() {
        assertNotNull(this.mms);
    }

	@Test
	public void test() {
		try {

			long start = System.currentTimeMillis();

			UMLProjectIdentifer project = new UMLProjectIdentifer();
			project.setIdentifier("caCORE 3.2");
			project.setVersion("3.2");

			System.out.println("Creating domain model for project: "
					+ project.getIdentifier() + " (version:"
					+ project.getVersion() + ")");

			// UNCOMMENT FOR: Whole project
			// DomainModel domainModel =
			// mms.generateDomainModelForProject(project);

			// UNCOMMENT FOR: a single package
			DomainModel domainModel = null;
			try {
				domainModel = mms
						.generateDomainModelForPackages(
								project,
								(List<String>) Arrays
										.asList(new String[] { "gov.nih.nci.cabio.domain" }));
			} catch (InvalidUMLProjectIndentifier e) {
				e.printStackTrace();
			}

			// UNCOMMENT FOR: a specific set of classes
			// String classNames[] = new String[]{Gene.class.getName(),
			// Taxon.class.getName()};
			// DomainModel domainModel =
			// mms.generateDomainModelForClasses(project, classNames);

			// UNCOMMENT FOR: a specific set of classes, with excluded
			// associations
			// String classNames[] = new String[]{Gene.class.getName(),
			// Chromosome.class.getName(), Taxon.class.getName(),
			// Tissue.class.getName()};
			// UMLAssociationExclude exclude1 = new
			// UMLAssociationExclude(Chromosome.class.getName(), "chromosome",
			// Gene.class.getName(), "geneCollection");
			// UMLAssociationExclude exclude2 = new
			// UMLAssociationExclude(Tissue.class.getName(), "*", "*", "*");
			// UMLAssociationExclude associationExcludes[] = new
			// UMLAssociationExclude[]{exclude1, exclude2};
			// DomainModel domainModel =
			// mms.generateDomainModelForClassesWithExcludes(project,
			// classNames,
			// associationExcludes);

			// work around for people getting the "illegal character" problem
			// for smart quotes
			replaceIllegalCharacters(domainModel);

			// Writer writer = new
			// FileWriter(project.getIdentifier().replace(" ", "_") + "-" +
			// project.getVersion()
			// + "_DomainModel.xml");
			OutputStreamWriter writer = new OutputStreamWriter(System.out);
			// MetadataUtils.serializeDomainModel(domainModel, writer);
			// JAXBElement<DomainModel> element = new
			// JAXBElement<DomainModel>(MetadataConstants.CAGRID_DATA_MD_QNAME,
			// domainModel.getClass(), domainModel);
			JAXBContext jaxbContext = JAXBContext.newInstance(domainModel
					.getClass());
			JAXBElement<DomainModel> el = new ObjectFactory()
					.createDomainModel(domainModel);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(el, writer);

			writer.close();

			double duration = (System.currentTimeMillis() - start) / 1000.0;
			System.out.println("Domain Model generation took:" + duration
					+ " seconds.");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @param domainModel
	 */
	private static void replaceIllegalCharacters(DomainModel domainModel) {
		StringBuilder sb = new StringBuilder();
		char[] badChars = { 0x18, 0x19 };
		ExposedUMLClassCollection classColl = domainModel
				.getExposedUMLClassCollection();
		UMLClass[] classes = (UMLClass[]) classColl.getUMLClass().toArray(
				new UMLClass[0]);
		for (UMLClass klass : classes) {
			String classDesc = klass.getDescription();
			if (classDesc != null) {
				for (char element : badChars) {
					if (classDesc.indexOf(element) > -1) {
						sb.append("Class description " + klass.getClassName()
								+ " contains bad character: " + classDesc
								+ "\n");
						classDesc = classDesc.replace(element, ' ');
					}
				}
				klass.setDescription(classDesc);
			}
			UmlAttributeCollection attColl = klass.getUmlAttributeCollection();
			UMLAttribute[] atts = (UMLAttribute[]) attColl.getUMLAttribute()
					.toArray(new UMLAttribute[0]);
			for (UMLAttribute att : atts) {
				String desc = att.getDescription();
				if (desc != null) {
					for (char element : badChars) {
						if (desc.indexOf(element) > -1) {
							sb.append("Attribute description "
									+ klass.getClassName() + "."
									+ att.getName()
									+ " contains bad character: " + desc + "\n");
							desc = desc.replace(element, ' ');
						}
					}
					att.setDescription(desc);
				}
				att.getSemanticMetadata().addAll(
						Arrays.asList((fixSemanticMetadata(sb,
								klass.getClassName() + "." + att.getName(),
								(SemanticMetadata[]) att.getSemanticMetadata()
										.toArray(new SemanticMetadata[0]),
								badChars))));
			}
			klass.getSemanticMetadata()
					.addAll(Arrays.asList(fixSemanticMetadata(sb, klass
							.getClassName(),
							(SemanticMetadata[]) klass.getSemanticMetadata()
									.toArray(new SemanticMetadata[0]), badChars)));
		}

		if (sb.length() > 0) {
			System.out
					.println("\n\n\n========== CHARACTER PROBLEMS WITH MODEL ==========");
			System.out.println(sb.toString());
		} else {
			System.out.println("No illegal characters found in model");
		}
	}

	private static SemanticMetadata[] fixSemanticMetadata(StringBuilder sb,
			String name, SemanticMetadata[] semMetColl, char[] badChars) {
		for (SemanticMetadata element : semMetColl) {
			String desc = element.getConceptDefinition();
			if (desc != null) {
				for (char element2 : badChars) {
					if (desc.indexOf(element2) > -1) {
						sb.append("SemanticMetadata " + name
								+ " contains bad character: " + desc + "\n");
						desc = desc.replace(element2, ' ');
					}
				}
				element.setConceptDefinition(desc);
			}
		}
		return semMetColl;
	}

}
