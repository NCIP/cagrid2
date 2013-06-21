package org.cagrid.dorian.systest;

import static org.apache.karaf.tooling.exam.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.CoreOptions.options;
import static org.ops4j.pax.exam.CoreOptions.vmOption;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;

@RunWith(JUnit4TestRunner.class)
public class DorianIT {

	/**
	 * Debug system property, set to any value to enable.
	 */
	public final static String DEBUG_PROPERTY = "systest.debug";

	/**
	 * Debug suspend system property, set to 'y' or 'n' (or leave unset).
	 */
	public final static String DEBUG_SUSPEND_PROPERTY = "systest.suspend";

	@Configuration
	public Option[] config() {
		List<Option> options = new ArrayList<Option>();

		if (System.getProperty(DEBUG_PROPERTY) != null) {
			options.add(vmOption("-agentlib:jdwp=transport=dt_socket,server=y,address=5005,suspend="
					+ System.getProperty(DEBUG_SUSPEND_PROPERTY, "n")));
			options.add(vmOption("-Dcom.sun.management.jmxremote.ssl=false"));
			options.add(vmOption("-Dcom.sun.management.jmxremote.authenticate=false"));
			options.add(vmOption("-Dcom.sun.management.jmxremote.port=5006"));
		}

		options.add(karafDistributionConfiguration()
				.frameworkUrl(
						maven().groupId("org.apache.servicemix")
								.artifactId("apache-servicemix")
								.version("4.5.1").type("tar.gz"))
				.name("Apache ServiceMix").karafVersion("2.2.10"));
		options.add(junitBundles());

		beforePAX();

		return options(options.toArray(new Option[options.size()]));
	}

	@Test
	public void testOK() {
		Assert.assertTrue(true);
	}

	protected void beforePAX() {
		try {
			DorianBootstrap dorianBootstrap = new DorianBootstrap();
			dorianBootstrap.createKeyAndTrustStores();
		} catch (Exception e) {
			throw new RuntimeException("Exception bootstrapping Dorian", e);
		}
	}
}
