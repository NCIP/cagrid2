package edu.internet2.middleware;

import net.sf.hibernate.tool.hbm2ddl.SchemaExportTask;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;
import edu.internet2.middleware.grouper.RegistryInstall;

import java.io.File;

public class GrouperInit {
    public static void main(String[] args) {
        System.out.println("********************************************************************");
        System.out.println("Initializing grouper...");
        System.out.println("********************************************************************");

        if (args.length != 3) {
            System.err.println("Unexpected number of parameters were provided. Parameters should be:");
            System.err.println("output file");
            System.err.println("properties file");
            System.err.println("directory to hibernate mapping files");
            System.exit(1);
        }

        String outputFileStr = args[0];
        String propertiesFileStr = args[1];
        String hibernateMappingsDirStr = args[2];

        System.out.println("Output file: " + outputFileStr);
        System.out.println("Hibernate properties: " + propertiesFileStr);
        System.out.println("Hibernate mappings dir: " + hibernateMappingsDirStr);

        File propertiesFile = new File(propertiesFileStr);
        if (!propertiesFile.exists()) {
            System.err.println("Hibernate properties not found: " + propertiesFile.getAbsolutePath());
            System.exit(1);
        }

        File hibernateMappingsDir = new File(hibernateMappingsDirStr);
        if (!hibernateMappingsDir.exists()) {
            System.err.println("Hibernate mappings directory not found: " + hibernateMappingsDir.getAbsolutePath());
            System.exit(1);
        }
        if (!hibernateMappingsDir.isDirectory()) {
            System.err.println("Not a directory: " + hibernateMappingsDir.getAbsolutePath());
            System.exit(1);
        }

        try {
            SchemaExportTask se = new SchemaExportTask();
            se.setProject(new Project());
            se.getProject().setBaseDir(new File("."));
            se.setQuiet(false);
            se.setText(false);
            se.setDrop(false);
            se.setDelimiter(";");
            se.setOutput(outputFileStr);
            se.setProperties(propertiesFile);

            FileSet fileSet = new FileSet();
            fileSet.setDir(hibernateMappingsDir);
            fileSet.setIncludes("**/*.hbm.xml");
            se.addFileset(fileSet);
            se.execute();

            edu.internet2.middleware.grouper.RegistryInstall.main(new String[0]);
        } catch(Throwable e) {
            e.printStackTrace();
        }
    }
}
