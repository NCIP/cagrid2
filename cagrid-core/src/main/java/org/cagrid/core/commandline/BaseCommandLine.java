package org.cagrid.core.commandline;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;

public abstract class BaseCommandLine {

	public static final String KARAF_BASE = "${karaf.base}";
	public static final String SERVICE_MIX_ETC = "etc";
	public static final String SERVICE_MIX_PATH = "org.cagrid.service.mix.path";

	public static final String PROMPT = "org.cagrid.commandline.prompt";

	protected File serviceMix;
	protected File serviceMixEtc;

	protected Properties properties;
	private File propertiesFile;

	public BaseCommandLine(File propertiesFile) {
		this.propertiesFile = propertiesFile;
	}

	public BaseCommandLine(Properties properties) {
		this.properties = properties;
	}

	public abstract void execute() throws Exception;

	public Properties getProperties() {
		if (properties == null) {
			if ((this.propertiesFile != null) && (this.propertiesFile.exists())
					&& (propertiesFile.isFile())) {

				this.properties = loadProperties(this.propertiesFile);

				System.out.println("Using the properties file: "
						+ propertiesFile.getAbsolutePath());
			}

			if (this.properties == null) {
				File f = new File("src/main/resources/bootstrapper.properties");
				this.properties = loadProperties(f);
				System.out.println("Using the properties file: "
						+ f.getAbsolutePath());
			}
			if (this.properties == null) {
				System.out.println("WARNING no properties for "
						+ getClass().getName() + " were found.");
				properties = new Properties();
			}
		}
		return properties;
	}

	private void configureServiceMix() {

		if (serviceMix == null) {
			String serviceMixStr = System.getenv("SERVICE_MIX_HOME");
			if (serviceMixStr == null) {
				serviceMixStr = IOUtils.readLine(
						"Enter your Fuse|ServiceMix installation directory",
						true);
			}
			this.serviceMix = new File(serviceMixStr);
			if (!serviceMix.isDirectory()) {
				System.err.println("The service mix location specified, "
						+ serviceMixStr + " is not valid.");
				return;
			}

			System.out.println("Using service mix "
					+ this.serviceMix.getAbsolutePath());
			this.serviceMixEtc = new File(this.serviceMix.getAbsolutePath()
					+ File.separator + SERVICE_MIX_ETC);
		}
	}

	public File getServiceMix() {
		configureServiceMix();
		return serviceMix;
	}

	public void setServiceMix(String dir) {
		serviceMix = new File(dir);
		serviceMixEtc = new File(serviceMix.getAbsolutePath(), SERVICE_MIX_ETC);
	}

	public File getServiceMixEtc() {
		configureServiceMix();
		return serviceMixEtc;
	}

	public static Properties loadProperties(File propsFile) {
		try {
			Properties props = new Properties();
			FileInputStream fis = new FileInputStream(propsFile);
			props.load(fis);
			fis.close();
			return props;
		} catch (Exception e) {
			System.out.println("Properties file " + propsFile
					+ " was not found.");
			return null;
		}
	}

	public String getValue(String prompt, String property) {
		return getValue(prompt, property, false);
	}

	public String getValue(String prompt, String property, boolean force) {
		String val = getProperties().getProperty(property);
		if ((force) || (val == null) || (alwaysPrompt())) {
			val = IOUtils.readLine(prompt, val);
		}
		return val;

	}

	public String getValueWithDefault(String prompt, String property,
			String defaultValue) {
		String val = getProperties().getProperty(property);
		if ((val == null) || (alwaysPrompt())) {
			val = IOUtils.readLine(prompt, defaultValue);
		}
		return val;
	}

	public String getValueWithOptions(String prompt, String property,
			String[] options) {
		return getValueWithOptions(prompt, property, null, options);
	}

	public String getValueWithOptions(String prompt, String property,
			String defaultValue, String[] options) {
		String val = getProperties().getProperty(property);
		if ((val == null) || (alwaysPrompt())) {
			val = IOUtils.readLine(buildPrompt(prompt, defaultValue, options),
					defaultValue);
			boolean isValid = Arrays.asList(options).contains(val);
			while (!isValid) {
				System.out.println("Please enter a valid option!");
				val = IOUtils.readLine(
						buildPrompt(prompt, defaultValue, options),
						defaultValue);
				isValid = Arrays.asList(options).contains(val);
			}
		}
		return val;
	}

	private String buildPrompt(String prompt, String defaultValue,
			String[] options) {
		StringBuffer sb = new StringBuffer(prompt);
		sb.append(" (");
		for (int i = 0; i < options.length; i++) {
			if (i > 0) {
				sb.append(", ");
			}
			sb.append(options[i]);
		}
		sb.append(")");
		return sb.toString();
	}

	public boolean alwaysPrompt() {
		try {
			if (getProperties().containsKey(PROMPT)) {
				String val = getProperties().getProperty(PROMPT);
				boolean b = Boolean.valueOf(val);
				if (b) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception e) {
			return true;
		}
	}

	public void fail(Exception e, String msg) {
		System.err.println(msg);
		e.printStackTrace();
		System.exit(0);
	}
}
